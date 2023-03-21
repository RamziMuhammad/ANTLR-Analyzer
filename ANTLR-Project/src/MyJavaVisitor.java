import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyJavaVisitor extends JavaParserBaseVisitor{
    public MyJavaVisitor(TokenStreamRewriter rewriter){
        this.rewriter = rewriter;
    }
    TokenStreamRewriter rewriter;
    int blockCount = 0;

    @Override
    public Object visitBlock(JavaParser.BlockContext ctx) {
        rewriter.insertAfter(ctx.getStart(), " // Block number " + blockCount++);
        return super.visitBlock(ctx);
    }
}
