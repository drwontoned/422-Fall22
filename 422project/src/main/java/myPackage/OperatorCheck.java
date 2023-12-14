package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorCheck extends AbstractCheck {

    // initialize count at 0
    public int count = 0;

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {
            TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
                TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
                TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON,
                TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
                TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT,
                TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE,
                TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
                TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
                TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
                TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN,
                TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN,
                TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,
                TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_SUPER,
                TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT,
                TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY,
                TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_BREAK,
                TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_RETURN, TokenTypes.SEMI,
                TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.INDEX_OP, TokenTypes.ARRAY_DECLARATOR,
                TokenTypes.RPAREN, TokenTypes.OBJBLOCK, TokenTypes.ARRAY_INIT, TokenTypes.ANNOTATION_ARRAY_INIT,
                TokenTypes.SLIST, TokenTypes.IDENT
        };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
                TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
                TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON,
                TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
                TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT,
                TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE,
                TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
                TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
                TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
                TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN,
                TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN,
                TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,
                TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_SUPER,
                TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT,
                TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY,
                TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_BREAK,
                TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_RETURN, TokenTypes.SEMI,
                TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.INDEX_OP, TokenTypes.ARRAY_DECLARATOR,
                TokenTypes.RPAREN, TokenTypes.OBJBLOCK, TokenTypes.ARRAY_INIT, TokenTypes.ANNOTATION_ARRAY_INIT,
                TokenTypes.SLIST, TokenTypes.IDENT
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
                TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
                TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON,
                TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN,
                TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT,
                TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE,
                TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT,
                TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN,
                TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
                TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN,
                TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN,
                TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS,
                TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_SUPER,
                TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_ASSERT,
                TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_FINALLY,
                TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_BREAK,
                TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_RETURN, TokenTypes.SEMI,
                TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.INDEX_OP, TokenTypes.ARRAY_DECLARATOR,
                TokenTypes.RPAREN, TokenTypes.OBJBLOCK, TokenTypes.ARRAY_INIT, TokenTypes.ANNOTATION_ARRAY_INIT,
                TokenTypes.SLIST, TokenTypes.IDENT
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        int parentType = aAST.getParent().getType();
        int tokenType = aAST.getType();

        // if token type is and identifier
        if ((tokenType == TokenTypes.IDENT)) {

            // name of anything in the definition of functions, classes, and types should not be counted
            if (parentType == TokenTypes.TYPE || parentType == TokenTypes.METHOD_DEF ||
                parentType == TokenTypes.CLASS_DEF || parentType == TokenTypes.INTERFACE_DEF ||
                parentType == TokenTypes.CTOR_DEF || parentType == TokenTypes.PARAMETER_DEF ||
                parentType == TokenTypes.ANNOTATION_DEF || parentType == TokenTypes.RECORD_DEF ||
                parentType == TokenTypes.ENUM_CONSTANT_DEF || parentType == TokenTypes.ENUM_DEF ||
                parentType == TokenTypes.EXTENDS_CLAUSE || parentType == TokenTypes.IMPLEMENTS_CLAUSE ||
                parentType == TokenTypes.TYPE_LOWER_BOUNDS || parentType == TokenTypes.TYPE_UPPER_BOUNDS ||
                parentType == TokenTypes.TYPE_PARAMETER) {

                // the method call should be counted
            } else if (parentType == TokenTypes.METHOD_CALL) {

                count++;
            }

            // if token type is a right parenthesis
        } else if (tokenType == TokenTypes.RPAREN) {

            // when parentheses are part of a statement these are already counted
            if (parentType == TokenTypes.LITERAL_NEW || parentType == TokenTypes.LITERAL_IF ||
                parentType == TokenTypes.LITERAL_FOR || parentType == TokenTypes.LITERAL_WHILE ||
                parentType == TokenTypes.LITERAL_SWITCH || parentType == TokenTypes.LITERAL_SYNCHRONIZED ||
                parentType == TokenTypes.DO_WHILE || parentType == TokenTypes.METHOD_CALL ||
                parentType == TokenTypes.SUPER_CTOR_CALL || parentType == TokenTypes.CTOR_CALL ||
                parentType == TokenTypes.METHOD_DEF || parentType == TokenTypes.CLASS_DEF ||
                parentType == TokenTypes.INTERFACE_DEF || parentType == TokenTypes.CTOR_DEF ||
                parentType == TokenTypes.PARAMETER_DEF || parentType == TokenTypes.ANNOTATION_DEF ||
                parentType == TokenTypes.RECORD_DEF || parentType == TokenTypes.ENUM_CONSTANT_DEF ||
                parentType == TokenTypes.ANNOTATION_FIELD_DEF) {


            } else {

                count++;
            }

            // if token type is a list of statements
        } else if (tokenType == TokenTypes.SLIST) {

            // count the brackets in specific blocks
            // count looping and control statements as singular operators
            // count 
            if (parentType == TokenTypes.METHOD_DEF || parentType == TokenTypes.CTOR_DEF ||
                parentType == TokenTypes.RECORD_DEF || parentType == TokenTypes.LITERAL_SYNCHRONIZED ||
                parentType == TokenTypes.STATIC_INIT || parentType == TokenTypes.LITERAL_TRY ||
                parentType == TokenTypes.LITERAL_FINALLY || parentType == TokenTypes.LITERAL_CATCH) {

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
        //report the number of operators
        log(rootAST.getLineNo(), "Operators: " + count + " - KS");
    }
}