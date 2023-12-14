package whitebox;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.LoopCheck;

public class LoopCheckTest {
    private int[] testArray = {
        TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO
    };

    @Test
    public void testGetAcceptableTokens() {
        final LoopCheck check = new LoopCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final LoopCheck check = new LoopCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final LoopCheck check = new LoopCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        LoopCheck check = spy(new LoopCheck());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.ABSTRACT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.count);

        // test if count increase when for is reached
        when(ast.getType()).thenReturn(TokenTypes.LITERAL_FOR);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.count);
        
        // test if count increase when while is reached
        when(ast.getType()).thenReturn(TokenTypes.LITERAL_WHILE);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(2, check.count);
        
        // test if count increase when do is reached
        when(ast.getType()).thenReturn(TokenTypes.LITERAL_DO);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(3, check.count);
    }

    @Test
    public void testBeginTree() {
        LoopCheck check = spy(new LoopCheck());
        DetailAST ast = mock(DetailAST.class);
        check.beginTree(ast);
        verify(check).beginTree(ast);
    }

    @Test
    public void testFinishTree() {
    	// mock the log?
    }
}