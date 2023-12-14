package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentLinesCheck extends AbstractCheck {

    // initialize count at 0
    public int count = 0;
    
    // initialize a startLine for counting lines in a block
    public int startLine = 0;

    @Override
    public int[] getAcceptableTokens() {
        // the starting of a new comment and end of comment block
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END
        };
    }
    
    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        //if line comment increase count normally
        if (aAST.getType() == TokenTypes.SINGLE_LINE_COMMENT) {

            count++;

            // if start of block set starting line number
        } else if (aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {

            startLine = aAST.getLineNo();

            // if end of block increase count using line number difference from start
        } else if (aAST.getType() == TokenTypes.BLOCK_COMMENT_END) {

            count += (aAST.getLineNo() - startLine) + 1;
            startLine = 0;
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
        log(rootAST.getLineNo(), "Comment Lines: " + count + " - KS");
    }
    
    @Override
    public boolean isCommentNodesRequired() {
        return true;

    }
}