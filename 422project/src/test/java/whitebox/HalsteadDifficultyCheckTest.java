package whitebox;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.HalsteadDifficultyCheck;

public class HalsteadDifficultyCheckTest {
    private int[] testArray = {
        TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
            TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT,

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

    @Test
    public void testGetAcceptableTokens() {
        final HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        HalsteadDifficultyCheck check = spy(new HalsteadDifficultyCheck());
        DetailAST ast = mock(DetailAST.class);
        DetailAST astParent = mock(DetailAST.class);

		// first IDENT case where nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.IDENT);
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.TYPE);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.uniqueOperators.size());
        assertEquals(0, check.uniqueOperands.size());
        assertEquals(0, check.operandCount);

		// second IDENT case where it is a METHOD_CALL
		// unique operators size should increase
        when(ast.getType()).thenReturn(TokenTypes.IDENT);
        when(ast.getText()).thenReturn("test");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.METHOD_CALL);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.uniqueOperators.size());
        assertEquals(true, check.uniqueOperators.contains("test"));
        assertEquals(0, check.uniqueOperands.size());
        assertEquals(0, check.operandCount);

		// third IDENT case where it is a METHOD_CALL
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.IDENT);
        when(ast.getText()).thenReturn("test");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.METHOD_CALL);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.uniqueOperators.size());
        assertEquals(0, check.uniqueOperands.size());
        assertEquals(0, check.operandCount);

		// fourth IDENT case where it is a VARIABLE_DEF
		// unique operands size and operand count should increase
		when(ast.getType()).thenReturn(TokenTypes.IDENT);
		when(ast.getText()).thenReturn("test");
		when(ast.getParent()).thenReturn(astParent); 
		when(astParent.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
		check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.uniqueOperators.size());
        assertEquals(true, check.uniqueOperands.contains("test"));
        assertEquals(1, check.uniqueOperands.size());
        assertEquals(1, check.operandCount);

		// fifth IDENT case where it is a VARIABLE_DEF
		// only operand count should increase
		when(ast.getType()).thenReturn(TokenTypes.IDENT);
		when(ast.getText()).thenReturn("test");
		when(ast.getParent()).thenReturn(astParent); 
		when(astParent.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
		check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.uniqueOperators.size());
        assertEquals(1, check.uniqueOperands.size());
        assertEquals(2, check.operandCount);
        
		// first OBJBLOCK case where operators does not contain {
		// unique operators size should increase
		when(ast.getType()).thenReturn(TokenTypes.OBJBLOCK);
		when(ast.getParent()).thenReturn(astParent);
		when(astParent.getType()).thenReturn(TokenTypes.CLASS_DEF);
		check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(2, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains("{"));
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// second OBJBLOCK case where operators does not contain {
		// nothing should increase
		when(ast.getType()).thenReturn(TokenTypes.OBJBLOCK);
		when(ast.getParent()).thenReturn(astParent);
		when(astParent.getType()).thenReturn(TokenTypes.CLASS_DEF);
		check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(2, check.uniqueOperators.size());
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// first RPAREN case when parentheses are part of a declaration statement
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.RPAREN);
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.LITERAL_NEW);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(2, check.uniqueOperators.size());
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// second RPAREN case when parentheses are not part of a declaration statement
		// unique operators size should increase
        when(ast.getType()).thenReturn(TokenTypes.RPAREN);
        when(ast.getText()).thenReturn(")");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.DIV);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(3, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains(")"));
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// third RPAREN case when parentheses are not part of a declaration statement
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.RPAREN);
        when(ast.getText()).thenReturn(")");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.DIV);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(3, check.uniqueOperators.size());
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		//reset
		check = spy(new HalsteadDifficultyCheck());

		// first SLIST case where the brackets are part of a specified block
		// unique operators size should increase
        when(ast.getType()).thenReturn(TokenTypes.SLIST);
        when(ast.getText()).thenReturn("{");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.METHOD_DEF);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(1, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains("{"));
		assertEquals(0, check.uniqueOperands.size());
		assertEquals(0, check.operandCount);
		
		// second SLIST case where the brackets are part of a specified block
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.SLIST);
        when(ast.getText()).thenReturn("{");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.METHOD_DEF);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(1, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains("{"));
		assertEquals(0, check.uniqueOperands.size());
		assertEquals(0, check.operandCount);
		
		// third SLIST case where the brackets are part of a control block
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.SLIST);
        when(ast.getText()).thenReturn("{");
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.LITERAL_IF);
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(1, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains("{"));
		assertEquals(0, check.uniqueOperands.size());
		assertEquals(0, check.operandCount);
		
		// other operand cases like string, int, etc.
		// unique operands size and operand count should increase
        when(ast.getType()).thenReturn(TokenTypes.STRING_LITERAL);
        when(ast.getText()).thenReturn("test");
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(1, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperands.contains("test"));
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(1, check.operandCount);
		
		// duplicate other operand cases like string, int, etc.
		// only operand count should increase
        when(ast.getType()).thenReturn(TokenTypes.STRING_LITERAL);
        when(ast.getText()).thenReturn("test");
        check.visitToken(ast);
		verify(check, atLeast(1)).visitToken(ast);
		assertEquals(1, check.uniqueOperators.size());
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// other operators case
		// unique operators size should increase
        when(ast.getType()).thenReturn(TokenTypes.STAR);
        when(ast.getText()).thenReturn("*");
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
		assertEquals(2, check.uniqueOperators.size());
		assertEquals(true, check.uniqueOperators.contains("*"));
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
		
		// duplicate other operators case
		// nothing should increase
        when(ast.getType()).thenReturn(TokenTypes.STAR);
        when(ast.getText()).thenReturn("*");
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
		assertEquals(2, check.uniqueOperators.size());
		assertEquals(1, check.uniqueOperands.size());
		assertEquals(2, check.operandCount);
    }

    @Test
    public void testBeginTree() {
        HalsteadDifficultyCheck check = spy(new HalsteadDifficultyCheck());
        DetailAST ast = mock(DetailAST.class);
        check.beginTree(ast);
        verify(check).beginTree(ast);
    }

    @Test
    public void testFinishTree() {
        // mock the log?
    }
}