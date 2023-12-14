package whitebox;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.OperandCheck;

public class OperandCheckTest {
    private int[] testArray = {
    	TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
            TokenTypes.NUM_LONG, TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE, TokenTypes.IDENT
    };

    @Test
    public void testGetAcceptableTokens() {
        final OperandCheck check = new OperandCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final OperandCheck check = new OperandCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final OperandCheck check = new OperandCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        OperandCheck check = spy(new OperandCheck());
        DetailAST ast = mock(DetailAST.class);
        DetailAST astParent = mock(DetailAST.class);

        // first IDENT case where count should not increase
        when(ast.getType()).thenReturn(TokenTypes.IDENT);
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.TYPE);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.count);

        // second IDENT case where it is a VARIABLE_DEF and count should increase
        when(ast.getType()).thenReturn(TokenTypes.IDENT);
        when(ast.getParent()).thenReturn(astParent);
        when(astParent.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.count);

        // other operand cases like string, int, etc.
        when(ast.getType()).thenReturn(TokenTypes.STRING_LITERAL);
        when(ast.getText()).thenReturn("test");
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(2, check.count);
    }

    @Test
    public void testBeginTree() {
        OperandCheck check = spy(new OperandCheck());
        DetailAST ast = mock(DetailAST.class);
        check.beginTree(ast);
        verify(check).beginTree(ast);
    }

    @Test
    public void testFinishTree() {
    	/*
    	OperandCheck check = spy(new OperandCheck());
        DetailAST ast = mock(DetailAST.class);
    	check.finishTree(ast);
    	verify(check).finishTree(ast);
    	*/
    	
    	// mock the log?
    }
}