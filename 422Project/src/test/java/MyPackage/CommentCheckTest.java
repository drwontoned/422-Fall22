package MyPackage;
import org.junit.jupiter.api.Test;

import MyPackage.CommentCheck;
import antlr.Token;

import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.*;

public class CommentCheckTest {
	public CommentCheck testCheck = new CommentCheck();
	
	@Test
	void testCount()
	{
		assertEquals(
				0,
				testCheck.count);
	}
	
	@Test
	void testGetAcceptableTokens()
	{
		int[] testAcceptable = new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};

		assertEquals(
				testAcceptable,
				testCheck.getAcceptableTokens());
	}
	
	@Test
	void testGetRequiredTokens()
	{
		int[] testRequired = new int[0];
		
		assertEquals(
				testRequired,
				testCheck.getRequiredTokens());
	}
	
	@Test
	void testGetDefaultTokens()
	{
		int[] testDefault = new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
		
		assertEquals(
				testDefault,
				testCheck.getDefaultTokens());
	}
	
	@Test
	void testVisitToken()
	{
		DetailAstImpl aAST = new DetailAstImpl();
		Token testToken = new Token();
		testToken.setType(TokenTypes.SINGLE_LINE_COMMENT);
		aAST.initialize(testToken);
		assertEquals(
				0,
				testCheck.count);
		
		testCheck.visitToken(aAST);
		
		assertEquals(
				1,
				testCheck.count);
	}
	
	@Test
	void testBeginTree()
	{
		DetailAstImpl aAST = new DetailAstImpl();
		assertEquals(
				1,
				testCheck.count);
		
		testCheck.beginTree(aAST);;
		
		assertEquals(
				0,
				testCheck.count);
	}
	
	@Test
    void testFinishTree() 
	{
    	DetailAstImpl aAST = new DetailAstImpl();
    	DetailAstImpl bAST = new DetailAstImpl();
    	Token testTokenA = new Token();
    	Token testTokenB = new Token();
		testTokenA.setType(TokenTypes.SINGLE_LINE_COMMENT);
		testTokenB.setType(TokenTypes.BLOCK_COMMENT_BEGIN);
		aAST.initialize(testTokenA);
		bAST.initialize(testTokenB);
		aAST.addChild(bAST);
		
		testCheck.finishTree(aAST);
		
		assertEquals(
				2,
				testCheck.count);
    }
	
	@Test
	void testIsCommentNodesRequired()
	{
		assertEquals(
				true,
				testCheck.isCommentNodesRequired());
	}
}