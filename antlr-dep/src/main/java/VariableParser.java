
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.google.gson.Gson;

import impl.CustomErrorListener;
import impl.DependencyListener;
import parser.JavaSnippetLexer;
import parser.JavaSnippetParser;

public class VariableParser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No code snippet given to the variable parser for dependency graph.");
            System.exit(1);
        }

        String code = args[0];

        try {
            // Create lexer and parser
            JavaSnippetLexer lexer = new JavaSnippetLexer(CharStreams.fromString(code));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaSnippetParser parser = new JavaSnippetParser(tokens);
            
            // Remove default error listener and add custom one
            parser.removeErrorListeners();
            CustomErrorListener errorListener = new CustomErrorListener();
            parser.addErrorListener(errorListener);
            
            // Parse and walk the tree
            JavaSnippetParser.ProgramContext tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            DependencyListener listener = new DependencyListener();
            walker.walk(listener, tree);

            // Convert the result to a json file to return. 
            List<Map<String, Object>> jsonList = listener.getEntriesAsJsonObjects();
            String json = new Gson().toJson(jsonList);
            System.out.println(json);
            
        } catch (Exception e) {
            System.err.println("Parser exception: " + e.getMessage());
            e.printStackTrace();
        }    
    }
}
