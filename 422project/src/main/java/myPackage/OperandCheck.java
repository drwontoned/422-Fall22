package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandCheck extends AbstractCheck {

    // initialize count at 0
    public int count = 0;

    @Override
    public int[] getAcceptableTokens() {

        return new int[] {
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT
        };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        int parentType = aAST.getParent().getType();
        int tokenType = aAST.getType();

        // if token type is and identifier
        if ((tokenType == TokenTypes.IDENT)) {

            // only variable names need to be counted
            if (parentType == TokenTypes.TYPE || parentType == TokenTypes.METHOD_DEF ||
                parentType == TokenTypes.CLASS_DEF || parentType == TokenTypes.INTERFACE_DEF ||
                parentType == TokenTypes.CTOR_DEF || parentType == TokenTypes.PARAMETER_DEF ||
                parentType == TokenTypes.ANNOTATION_DEF || parentType == TokenTypes.RECORD_DEF ||
                parentType == TokenTypes.ENUM_CONSTANT_DEF || parentType == TokenTypes.ENUM_DEF ||
                parentType == TokenTypes.EXTENDS_CLAUSE || parentType == TokenTypes.IMPLEMENTS_CLAUSE ||
                parentType == TokenTypes.TYPE_LOWER_BOUNDS || parentType == TokenTypes.TYPE_UPPER_BOUNDS ||
                parentType == TokenTypes.TYPE_PARAMETER || parentType == TokenTypes.METHOD_CALL) {

            } else {

                count++;
            }
        } else {
        	
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
        //report the number of operands
        log(rootAST.getLineNo(), "Operands: " + count + " -KS");
    }
}