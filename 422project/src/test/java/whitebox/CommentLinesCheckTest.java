package whitebox;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.CommentLinesCheck;

public class CommentLinesCheckTest {
    private int[] testArray = {
    	TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END
    };

    @Test
    public void testGetAcceptableTokens() {
        final CommentLinesCheck check = new CommentLinesCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final CommentLinesCheck check = new CommentLinesCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final CommentLinesCheck check = new CommentLinesCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        CommentLinesCheck check = spy(new CommentLinesCheck());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.ABSTRACT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.count);
        assertEquals(0, check.startLine);

        // test if count increase when single line comment is reached
        when(ast.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.count);

        // test if startLine matches when block comment begin is reached
        when(ast.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(ast.getLineNo(), check.startLine);

        // test if count increase based on block comment length and that startLine is reset
        int testStart = check.startLine;
        when(ast.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_END);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1 + ((ast.getLineNo() - testStart) + 1), check.count);
        assertEquals(0, check.startLine);
    }


    @Test
    public void testBeginTree() {
        CommentLinesCheck check = spy(new CommentLinesCheck());
        DetailAST ast = mock(DetailAST.class);
        check.beginTree(ast);
        verify(check).beginTree(ast);
    }

    @Test
    public void testFinishTree() {
    	// mock the log?
    }

    @Test
    public void testIsCommentNodesRequired() {
        final CommentLinesCheck check = new CommentLinesCheck();
        assertEquals(true, check.isCommentNodesRequired(), "Comment nodes need to be required");
    }
}