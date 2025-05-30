# Use FLASK framework for building the API: https://flask.palletsprojects.com/en/stable/quickstart/
from flask_cors import CORS
from flask import Flask, request, jsonify, Blueprint

# Taken from load_predict.py; everything needed to run the models
import joblib
import warnings
import subprocess
import numpy as np
from gensim.models import Word2Vec
import networkx as nx
import json

# Create instance of the Flask class
app = Flask(__name__)
CORS(app)

# Global variables for models
apis_w2v = None
contexts_w2v = None
knn_model = None
mapping = None

def load_mapping():
    #Load the mapping from numbers to FQNs
    global mapping
    mapping_lines = []
    with open("models/w2v/data_mapping.txt") as f:
        for line in f:
            line = line.strip()
            if line:
                num, fqn = line.split(":")
                mapping_lines.append((int(num), fqn))
    mapping = dict(mapping_lines)

def initialize_models():
    global apis_w2v, contexts_w2v, knn_model
    
    print("Loading the w2v models...")
    apis_w2v = Word2Vec.load("models/w2v/data_apis.model")
    contexts_w2v = Word2Vec.load("models/w2v/data_contexts.model")
    knn_model = joblib.load("models/cls/knn_github.joblib")
    load_mapping()
    print("All models loaded successfully!")

# Parse using custom antlr parser for variabel dependencies
def call_variable_parser(code_snippet):
    jar_path = "/Users/yousrabarhdadi/Desktop/vub/CWBA3/thesis/antlr-dep/target/antlr-dep-1.0-SNAPSHOT-jar-with-dependencies.jar"
    command = [ "java", "-jar", jar_path, code_snippet]
    result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    output = result.stdout.decode("utf-8")
    return json.loads(output)

# Buid the dependency graph
def build_dependency_graph(parsed_output):
    # Initialise the empty directed graph
    G = nx.DiGraph()
    
    for entry in parsed_output:
        var = entry["var"]
        for dep in entry["dependsOn"]:
            G.add_edge(dep, var)
    return G

# Print out to see graph for debugging
def print_graph_edges(G):
    print("Dependency edges (from → to):")
    for edge in G.edges:
        print(f"{edge[0]} → {edge[1]}")

# Return leaf nodes
def find_leaf_nodes(G):
    return [node for node in G.nodes if G.in_degree(node) == 0]

# Check if there is a path between source node to at least one of the targets.
def is_reachable(G, source, targets):
    return any(nx.has_path(G, source, target) for target in targets)



# Prediction logic (similar to the logic in load_predict.py)
def predict_from_code(code_snippet, dep_graph):
    context_codes = []
    
    # Parse the code snippet
    result = subprocess.run(['java', '-jar', 'islandParser.jar', '-s', code_snippet], stdout=subprocess.PIPE)
    contexts = result.stdout.decode('utf-8').split('\n')
    contexts = list(filter(lambda element: len(element.strip()) > 0, contexts))

    if len(contexts) > 0:
        context_codes.append(contexts)

    keys_apis = list(apis_w2v.wv.key_to_index.keys())
    keys_context = list(contexts_w2v.wv.key_to_index.keys())
    
    saved_apis = []
    transformed_inputs = []

    for index_contexts, contexts in enumerate(context_codes):
        if len(contexts) > 0:
            for index_context, context in enumerate(contexts):
                divided_context = context.split(",")

                if len(divided_context) == 2:
                    api = divided_context[0].replace("|", ".")
                    internal_context = divided_context[1].split("|")

                    # Vectorise
                    if api in keys_apis:
                        transformed_api = apis_w2v.wv[api]
                        transformed_tokens = []

                        for token in internal_context:
                            if token in keys_context:
                                transformed_token = contexts_w2v.wv[token]
                                transformed_tokens.append(transformed_token)

                        if len(transformed_tokens) > 0:
                            transformed_context = np.mean(transformed_tokens)
                            transformed_input = (transformed_api + transformed_context) / 2

                            saved_apis.append(api)
                            transformed_inputs.append(transformed_input)
    
    predictions = set()
    resolved_vars = set()
    leaf_nodes = find_leaf_nodes(dep_graph)
    for api, transformed_input in zip(saved_apis, transformed_inputs):
        print(f"[DEBUG] Trying to resolve this API token: {api}")
        var = api.split('.')[0]

        # Check if variable has been resolved already and skip.
        if var in resolved_vars:
            continue 
            
        # Check if the variable is in the graph first, if not then just resolve
        if var not in dep_graph:
            prediction = int(knn_model.predict([transformed_input])[0])
            model_prediction = mapping[prediction]
            print(f"[DEBUG] Resolving API token: {api} -> {model_prediction}")
            if model_prediction.split('.')[-1] == var:
                predictions.add(model_prediction)
                resolved_vars.add(var)

        if var in leaf_nodes:
            prediction = int(knn_model.predict([transformed_input])[0])
            model_prediction = mapping[prediction]
            print(f"[DEBUG] Resolving API token: {api} -> {model_prediction}")

            if model_prediction.split('.')[-1] == var:
                predictions.add(model_prediction)
                resolved_vars.add(var) 

    return list(predictions)

# API POST method endpoint to receive code and return predictions
# We could work like the PLangRec model with a get request, but there could be issues with special characters etc.
@app.route('/predict', methods=['POST'])
def predict():
    try:
        data = request.get_json()  # Get JSON data from POST request
        code_snippet = data['code']
        
        # Parse the code snippet and create a dependency graph
        parsed_output = call_variable_parser(code_snippet)
        G = build_dependency_graph(parsed_output)
        print_graph_edges(G)
        
        # Get predictions based on the code
        predictions = predict_from_code(code_snippet, G)
        
        return jsonify({'predictions': predictions}), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 400


if __name__ == "__main__":
    # Load the models and run the flask app
    initialize_models()
    app.run(host="0.0.0.0", port=5000, debug=True)
 