import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyJavaVisitor extends JavaParserBaseVisitor{
    public MyJavaVisitor(TokenStreamRewriter rewriter){
        this.rewriter = rewriter;
    }
    TokenStreamRewriter rewriter;
    int blockCount = 0;

    String imports = "import java.util.ArrayList;\n" +
            "import java.io.FileWriter;\n" +
            "import java.io.IOException;\n";

    String listDeclaration = "\nstatic ArrayList<Integer> executedBlocks = new ArrayList<Integer>();\n";

    String blockNumInsertion = " /* Block number " + blockCount + " */\n" +
            "executedBlocks.add(" + blockCount + ");";

    String writingOutputFile = "\nString outputFileName = \"Visited-Blocks.txt\";\n" +
            "try (FileWriter fileWriter = new FileWriter(outputFileName)) {\n" +
            "    int previous = executedBlocks.get(0);\n" +
            "    int count = 1;\n" +
            "    for (int i = 1; i < executedBlocks.size(); i++) {\n" +
            "        if (executedBlocks.get(i) == previous) {\n" +
            "            count++;\n" +
            "        } else {\n" +
            "            if(count == 1){\n" +
            "                fileWriter.write(\"Block number \" + previous + \" is visited only once\\n\");\n" +
            "            } else {\n" +
            "                fileWriter.write(\"Block number \" + previous + \" is visited \" + count + \" times\\n\");\n" +
            "            }\n" +
            "            count = 1;\n" +
            "        }\n" +
            "        previous = executedBlocks.get(i);\n" +
            "    }\n" +
            "    if(count == 1){\n" +
            "        fileWriter.write(\"Block number \" + previous + \" is visited only once\\n\");\n" +
            "    } else {\n" +
            "        fileWriter.write(\"Block number \" + previous + \" is visited \" + count + \" times\\n\");\n" +
            "    }\n" +
            "} catch (IOException e) {\n" +
            "    e.printStackTrace();\n" +
            "}\n";

    @Override
    public Object visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), imports);
        return super.visitCompilationUnit(ctx);
    }

    @Override
    public Object visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        rewriter.insertBefore(ctx.getStart(), listDeclaration);
        rewriter.insertBefore(ctx.getStop(), writingOutputFile);
        return super.visitClassBodyDeclaration(ctx);
    }

    @Override
    public Object visitBlock(JavaParser.BlockContext ctx) {
        rewriter.insertAfter(ctx.getStart(), " /* Block number " + blockCount + " */\n" +
                "executedBlocks.add(" + blockCount++ + ");");
        return super.visitBlock(ctx);
    }
}
