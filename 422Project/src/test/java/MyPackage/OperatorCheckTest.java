package MyPackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import MyPackage.OperatorCheck;
import antlr.Token;

public class OperatorCheckTest {
	public OperatorCheck testCheck = new OperatorCheck();
	
	@Test
	void testCount() {
	
		assertEquals(
				0,
				testCheck.count);
	}
	
	@Test
	void testGetAcceptableTokens()
	{
		int[] testAcceptable = new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
						TokenTypes.BNOT, TokenTypes.BOR,TokenTypes.BOR_ASSIGN, TokenTypes.BSR, 
						TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, 
						TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
						TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, 
						TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, 
						TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, 
						TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
						TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
						TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN,
						TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN,
						TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};

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
		int[] testDefault = new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
						TokenTypes.BNOT, TokenTypes.BOR,TokenTypes.BOR_ASSIGN, TokenTypes.BSR, 
						TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, 
						TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, 
						TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, 
						TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND, TokenTypes.LE, 
						TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, 
						TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
						TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC,
						TokenTypes.POST_INC, TokenTypes.QUESTION, TokenTypes.SL, TokenTypes.SL_ASSIGN,
						TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN,
						TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS};
		
		assertEquals(
				testDefault,
				testCheck.getDefaultTokens());
	}
	
	@Test
	void testVisitToken()
	{
		DetailAstImpl aAST = new DetailAstImpl();
		Token testToken = new Token();
		testToken.setType(TokenTypes.PLUS);
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
		testTokenA.setType(TokenTypes.PLUS);
		testTokenB.setType(TokenTypes.PLUS);
		aAST.initialize(testTokenA);
		bAST.initialize(testTokenB);
		aAST.addChild(bAST);
		
		testCheck.finishTree(aAST);
		
		assertEquals(
				2,
				testCheck.count);
    }
}
