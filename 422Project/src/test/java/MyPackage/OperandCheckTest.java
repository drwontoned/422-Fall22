package MyPackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import antlr.Token;

public class OperandCheckTest {
	public OperandCheck testCheck = new OperandCheck();
	
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
		int[] testAcceptable = new int[] {TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL,
					TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};

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
		int[] testDefault = new int[] {TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL,
					TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};
		
		assertEquals(
				testDefault,
				testCheck.getDefaultTokens());
	}
	
	@Test
	void testVisitToken()
	{
		DetailAstImpl aAST = new DetailAstImpl();
		Token testToken = new Token();
		testToken.setType(TokenTypes.NUM_INT);
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
		testTokenA.setType(TokenTypes.CHAR_LITERAL);
		testTokenB.setType(TokenTypes.NUM_LONG);
		aAST.initialize(testTokenA);
		bAST.initialize(testTokenB);
		aAST.addChild(bAST);
		
		testCheck.finishTree(aAST);
		
		assertEquals(
				2,
				testCheck.count);
    }
}
