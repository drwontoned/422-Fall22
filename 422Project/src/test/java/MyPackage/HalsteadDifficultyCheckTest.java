package MyPackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import MyPackage.HalsteadDifficultyCheck;
import antlr.Token;

public class HalsteadDifficultyCheckTest {
public HalsteadDifficultyCheck testCheck = new HalsteadDifficultyCheck();
	
	@Test
	void testCount()
	{
		assertEquals(
				0,
				testCheck.uniqueOperatorCount);
		assertEquals(
				0,
				testCheck.uniqueOperandCount);
		assertEquals(
				0,
				testCheck.operandCount);
		assertEquals(
				0,
				testCheck.difficulty);
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
				TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
				
				TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};


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
				TokenTypes.UNARY_MINUS, TokenTypes.UNARY_PLUS, 
				
				TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};
		
		assertEquals(
				testDefault,
				testCheck.getDefaultTokens());
	}
	
	@Test
	void testVisitToken()
	{
		DetailAstImpl aAST = new DetailAstImpl();
    	DetailAstImpl bAST = new DetailAstImpl();
    	Token testTokenA = new Token();
    	Token testTokenB = new Token();
		testTokenA.setType(TokenTypes.PLUS);
		testTokenB.setType(TokenTypes.CHAR_LITERAL);
		aAST.initialize(testTokenA);
		bAST.initialize(testTokenB);
		
		//Operator checks
		assertEquals(
				0,
				testCheck.uniqueOperatorCount);
		
		testCheck.visitToken(aAST);
		
		assertEquals(
				1,
				testCheck.uniqueOperatorCount);
		
		testCheck.visitToken(aAST);
		
		assertEquals(
				1,
				testCheck.uniqueOperatorCount);
		
		
		// Operand checks
		assertEquals(
				0,
				testCheck.uniqueOperandCount);
		assertEquals(
				0,
				testCheck.operandCount);
		
		testCheck.visitToken(bAST);
		
		assertEquals(
				1,
				testCheck.uniqueOperandCount);
		assertEquals(
				1,
				testCheck.operandCount);
		
		testCheck.visitToken(bAST);
		
		assertEquals(
				1,
				testCheck.uniqueOperandCount);
		assertEquals(
				2,
				testCheck.operandCount);
	}
	
	@Test
	void testBeginTree()
	{
		DetailAstImpl aAST = new DetailAstImpl();
		assertEquals(
				1,
				testCheck.uniqueOperatorCount);
		assertEquals(
				1,
				testCheck.uniqueOperandCount);
		assertEquals(
				2,
				testCheck.operandCount);
		
		testCheck.beginTree(aAST);;
		
		assertEquals(
				0,
				testCheck.uniqueOperatorCount);
		assertEquals(
				0,
				testCheck.uniqueOperandCount);
		assertEquals(
				0,
				testCheck.operandCount);
		assertEquals(
				0,
				testCheck.difficulty);
	}

	@Test
	void testFinishTree() 
	{
    	DetailAstImpl aAST = new DetailAstImpl();
    	DetailAstImpl bAST = new DetailAstImpl();
    	Token testTokenA = new Token();
    	Token testTokenB = new Token();
    	testTokenA.setType(TokenTypes.PLUS);
		testTokenB.setType(TokenTypes.CHAR_LITERAL);
		aAST.initialize(testTokenA);
		bAST.initialize(testTokenB);
		aAST.addChild(bAST);
		
		testCheck.finishTree(aAST);
		
		assertEquals(
				((testCheck.uniqueOperatorCount/2)*testCheck.operandCount)/testCheck.uniqueOperandCount,
				testCheck.difficulty);
    }
}
