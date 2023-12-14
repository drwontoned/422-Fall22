package whitebox;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import myPackage.CommentCheck;

public class CommentCheckTest {
    private int[] testArray = {
        TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN
    };

    @Test
    public void testGetAcceptableTokens() {
        final CommentCheck check = new CommentCheck();
        assertArrayEquals(testArray, check.getAcceptableTokens(),
            "Acceptable tokens do not match");
    }

    @Test
    public void testGetRequiredTokens() {
        final CommentCheck check = new CommentCheck();
        assertArrayEquals(testArray, check.getRequiredTokens(),
            "Required tokens do not match");
    }

    @Test
    public void testGetDefaultTokens() {
        final CommentCheck check = new CommentCheck();
        assertArrayEquals(testArray, check.getDefaultTokens(),
            "Default tokens do not match");
    }

    @Test
    public void testVisitToken() {
        CommentCheck check = spy(new CommentCheck());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.ABSTRACT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(0, check.count);

        // test if count increase when single line comment is reached
        when(ast.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(1, check.count);

        // test if count increase when block comment begin is reached
        when(ast.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
        check.visitToken(ast);
        verify(check, atLeast(1)).visitToken(ast);
        assertEquals(2, check.count);
    }

    @Test
    public void testBeginTree() {
        CommentCheck check = spy(new CommentCheck());
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
        CommentCheck check = new CommentCheck();
        assertEquals(true, check.isCommentNodesRequired(), "Comment nodes need to be required");
    }
}