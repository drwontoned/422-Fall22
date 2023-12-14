package myPackage;

import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadEffortCheck extends AbstractCheck {

    // initialize counts at 0
    public int count = 0;
    public int operandCount = 0;

    // initialize HashSets to record unique operands and operators
    public HashSet < String > uniqueOperands = new HashSet < String > ();
    public HashSet < String > uniqueOperators = new HashSet < String > ();

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {
            // operands
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT,

                // operators
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
                TokenTypes.SLIST
        };
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
            // operands
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT,

                // operators
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
                TokenTypes.SLIST
        };
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            // operands
            TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
                TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT,

                // operators
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
                TokenTypes.SLIST
        };
    }

    @Override
    public void visitToken(DetailAST aAST) {
        int parentType = aAST.getParent().getType();
        int tokenType = aAST.getType();
        String current = aAST.getText();

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

                // if method call check if the operator does not exists
            } else if (parentType == TokenTypes.METHOD_CALL) {
                count++;

                // if it does not then add
                if (!uniqueOperators.contains(current)) {
                    uniqueOperators.add(current);
                }

            } else {
                count++;
                operandCount++;
                
                // if operand does not exists and add
                if (!uniqueOperands.contains(current)) {
                    uniqueOperands.add(current);
                }
            }
            // if object block which is the {} for things like classes
        } else if (tokenType == TokenTypes.OBJBLOCK) {
            count++;

            // check if exists then add
            if (!uniqueOperators.contains("{")) {
                uniqueOperators.add("{");
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

                // if operator does not exists and add
                if (!uniqueOperators.contains(current)) {
                    uniqueOperators.add(current);
                }
            }

            // if token type is a list of statements
        } else if (tokenType == TokenTypes.SLIST) {

            // count the brackets in specific blocks
            // count looping and control statements as singular operators
            if (parentType == TokenTypes.METHOD_DEF || parentType == TokenTypes.CTOR_DEF ||
                parentType == TokenTypes.RECORD_DEF || parentType == TokenTypes.LITERAL_SYNCHRONIZED ||
                parentType == TokenTypes.STATIC_INIT || parentType == TokenTypes.LITERAL_TRY ||
                parentType == TokenTypes.LITERAL_FINALLY || parentType == TokenTypes.LITERAL_CATCH) {

                count++;
                
                // if operator does not exists and add
                if (!uniqueOperators.contains(current)) {
                    uniqueOperators.add(current);
                }
            }

            // if specific operand instances    
        } else if (tokenType == TokenTypes.CHAR_LITERAL || tokenType == TokenTypes.STRING_LITERAL ||
            tokenType == TokenTypes.NUM_INT || tokenType == TokenTypes.NUM_LONG ||
            tokenType == TokenTypes.NUM_FLOAT || tokenType == TokenTypes.NUM_DOUBLE) {

            count++;
            operandCount++;
            
            // if operand does not exists and add
            if (!uniqueOperands.contains(current)) {
                uniqueOperands.add(current);
            }

        } else {
            count++;

            if (!uniqueOperators.contains(current)) {
                uniqueOperators.add(current);
            }
        }
    }

    @Override
    public void beginTree(DetailAST rootAST) {
        count = 0;
        operandCount = 0;
        uniqueOperands = new HashSet < String > ();
        uniqueOperators = new HashSet < String > ();
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        //report the Halstead effort				
    	
    	int vocabulary = uniqueOperands.size() + uniqueOperators.size();
        double volume = count * (Math.log10(vocabulary) / Math.log10(2));
        double difficulty = 0;
        if(uniqueOperands.size()!= 0) {
        	difficulty = ((double)uniqueOperators.size() / 2.0) * ((double)operandCount / (double)uniqueOperands.size());
    	}
        double effort = volume * difficulty;
        log(rootAST.getLineNo(), "Halstead Effort: " + effort + " -KS");
    }
}