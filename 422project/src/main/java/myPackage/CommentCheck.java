package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentCheck extends AbstractCheck {

    // initialize count at 0
    public int count = 0;

    @Override
    public int[] getAcceptableTokens() {
        // the starting of each new comment
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN
        };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        //each acceptable token visited increases count
        if (aAST.getType() == TokenTypes.SINGLE_LINE_COMMENT || aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
            count++;
        }
    }

    @Override
    public void beginTree(DetailAST rootAST) {
        //count set back to 0 at the start of each new tree
        count = 0;
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        //report the number of comments
        log(rootAST.getLineNo(), "Comments: " + count + " - KS");
    }

    @Override
    public boolean isCommentNodesRequired() {
        return true;

    }
}