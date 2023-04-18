import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.ObjectInputStream;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws IOException {

        HashMap<Integer, Integer> checkedBlocks = null;

        System.out.print("Enter the input file path: ");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();

        // Open file
        File file = new File(filePath);
        FileInputStream inputFile = new FileInputStream(file);
        // fis stands for file stream

        // Create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(inputFile);

        // Create a lexer that feeds off of input CharStream
        JavaLexer lexer = new JavaLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create token stream rewriter to inject code snippets
        TokenStreamRewriter interCodeRewriter = new TokenStreamRewriter(tokens);

        // Create a parser that feeds off the tokens buffer
        JavaParser parser = new JavaParser(tokens);

        // Begin parsing at init rule
        ParseTree tree = parser.compilationUnit();

        IntermediateCodeVisitor intermediateCodeVisitor = new IntermediateCodeVisitor(interCodeRewriter);
        intermediateCodeVisitor.visit(tree);

        System.out.println(interCodeRewriter.getText() + "\n/* Hence, there are " + --intermediateCodeVisitor.blockCount + " code blocks */\n");

        String outputFileName = "Intermediate-Code.java";
        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            fileWriter.write(interCodeRewriter.getText() + "\n/* Hence, there are " + --intermediateCodeVisitor.blockCount + " code blocks */\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the Hash-Map path: ");
        String hashMapPath = sc.nextLine();

        try {
            FileInputStream fileIn = new FileInputStream(hashMapPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            checkedBlocks = (HashMap<Integer, Integer>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        TokenStreamRewriter htmlWriterRewriter = new TokenStreamRewriter(tokens);

        HTMLWriterVisitor htmlWriterVisitor = new HTMLWriterVisitor(htmlWriterRewriter, checkedBlocks);
        htmlWriterVisitor.visit(tree);

        String htmlFileName = "index.html";
        try (FileWriter htmlWriter = new FileWriter(htmlFileName)) {
            htmlWriter.write(htmlWriterRewriter.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
