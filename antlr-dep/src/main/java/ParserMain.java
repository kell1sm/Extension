
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import impl.CustomErrorListener;
import impl.DependencyListener;
import parser.JavaSnippetLexer;
import parser.JavaSnippetParser;

public class ParserMain {
    public static void main(String[] args) {
        String code = """
            PDDocument doc = PDDocument.load(new File("filepath/sample.pdf"));
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            BufferedImage bffim = pdfRenderer.renderImageWithDPI(pageNo, 300, ImageType.RGB);
            String fileName = "image-" + page + ".png";
            """;

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

            // Print results
            System.out.println("Parsing completed. Entries: " + listener.getEntries().size());
            for (DependencyListener.VarEntry entry : listener.getEntries()) {
                System.out.println("VarEntry: " + entry);
            }

            // Report syntax errors
            if (errorListener.hasErrors()) {
                System.out.println("Syntax errors detected:");
                for (String error : errorListener.getErrors()) {
                    System.out.println(error);
                }
            } else {
                System.out.println("No syntax errors detected.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}