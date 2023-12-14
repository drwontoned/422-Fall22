package whitebox;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.ExpressionCheck;

public class ExpressionCheckTest {
    private int[] testArray = {
        TokenTypes.EXPR
    };

    @Test
    public void testGetAcceptableTokens() {
        final ExpressionCheck check = new ExpressionCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final ExpressionCheck check = new ExpressionCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final ExpressionCheck check = new ExpressionCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        ExpressionCheck check = spy(new ExpressionCheck());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.ABSTRACT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.count);

        // test if count increase when expression is reached
        when(ast.getType()).thenReturn(TokenTypes.EXPR);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.count);
    }

    @Test
    public void testBeginTree() {
        ExpressionCheck check = spy(new ExpressionCheck());
        DetailAST ast = mock(DetailAST.class);
        check.beginTree(ast);
        verify(check).beginTree(ast);
    }

    @Test
    public void testFinishTree() {
    	// mock the log?
    }
}