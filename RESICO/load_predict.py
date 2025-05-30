import joblib
import warnings
import subprocess
import numpy as np
from gensim.models import Word2Vec

warnings.filterwarnings("ignore")

# # Code snippet: https://stackoverflow.com/questions/29749627
# code = '''
# Table<String, Long, Person> personsByNameAndId = HashBasedTable.create();
# Person bob = ...;

# personsByNameAndId.put(bob.getName(), bob.getId(), bob);

# Person bobWithId1 = personsByNameAndId.get("Bob", 1l);

# Collection<Person> allPersonsNamedBob = personsByNameAndId.row("bob").values();

# Person personWithId2 = personsByNameAndId.column(2l).values().iterator().next();
# '''

# Code snippet: https://stackoverflow.com/questions/50148523
code = '''
PDDocument doc=PDDocument.load(new File("filepath/sample.pdf"));
PDFRenderer pdfRenderer = new PDFRenderer(doc);
BufferedImage bffim = pdfRenderer.renderImageWithDPI(pageNo, 300, ImageType.RGB);
String fileName = "image-" + page + ".png";
ImageIOUtil.writeImage(bim, fileName, 300);
'''

# Parse the Java code snippet
context_codes = []

result = subprocess.run(['java', '-jar', 'islandParser.jar', '-s', code], stdout=subprocess.PIPE)
contexts = result.stdout.decode('utf-8').split('\n')
contexts = list(filter(lambda element: len(element.strip()) > 0, contexts))

if len(contexts) > 0:
    context_codes.append(contexts)

MODELS_FOLDER = "models"

print("Loading the w2v models ...")
apis_w2v = Word2Vec.load("{}/w2v/data_apis.model".format(MODELS_FOLDER))
contexts_w2v = Word2Vec.load("{}/w2v/data_contexts.model".format(MODELS_FOLDER))

keys_apis = list(apis_w2v.wv.key_to_index.keys())
keys_context = list(contexts_w2v.wv.key_to_index.keys())
print("Done!")

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

print("Loading the trained classifier ...")
knn_model = joblib.load("{}/cls/knn_github.joblib".format(MODELS_FOLDER))
print("Done!")

print("Loading map of numbers to FQNs ...")
mapping_lines = []
with open("{}/w2v/data_mapping.txt".format(MODELS_FOLDER)) as f:
    while True:
        line = f.readline()
        if not line:
            break
        else:
            line = line.strip()
            mapping_lines.append(line)
mapping_lines = list(set(mapping_lines))
divided_lines = [element.split(":") for element in mapping_lines]
divided_lines_int = [(int(element_tuple[0]), element_tuple[1]) for element_tuple in divided_lines]
mapping = dict(divided_lines_int)
print("Done!")

print()
print("Predicted FQNs:")
predictions = set()
for api, transfomerd_input in zip(saved_apis, transformed_inputs):
    print(f"[INFO] Trying to resolve API token: {api})")
    prediction = int(knn_model.predict([transfomerd_input])[0])
    model_prediction = mapping[prediction]

    if model_prediction.split('.')[-1] == api.split('.')[0]:
        predictions.add(model_prediction)

for prediction in predictions:
    print(prediction)
