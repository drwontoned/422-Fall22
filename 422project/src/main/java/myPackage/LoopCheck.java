package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class LoopCheck extends AbstractCheck {

    // initialize count at 0
    public int count = 0;

    @Override
    public int[] getAcceptableTokens() {
        // the starting of each new loop
        return new int[] {
            TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO
        };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        //each acceptable token visited increases count
        if (aAST.getType() == TokenTypes.LITERAL_FOR || aAST.getType() == TokenTypes.LITERAL_WHILE ||
            aAST.getType() == TokenTypes.LITERAL_DO) {
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
        //report the number of Loops
        log(rootAST.getLineNo(), "Loops: " + count + " - KS");
    }
}