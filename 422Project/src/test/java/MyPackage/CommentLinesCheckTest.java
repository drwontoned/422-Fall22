package MyPackage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

import MyPackage.CommentLinesCheck;

public class CommentLinesCheckTest {
	public CommentLinesCheck testCheck = new CommentLinesCheck();
	
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
		int[] testAcceptable = CommonUtil.EMPTY_INT_ARRAY;

		assertEquals(
				testAcceptable,
				testCheck.getAcceptableTokens());
	}
	
	@Test
	void testGetRequiredTokens()
	{
		int[] testRequired = CommonUtil.EMPTY_INT_ARRAY;
		
		assertEquals(
				testRequired,
				testCheck.getRequiredTokens());
	}
	
	@Test
	void testGetDefaultTokens()
	{
		int[] testDefault = CommonUtil.EMPTY_INT_ARRAY;
		
		assertEquals(
				testDefault,
				testCheck.getDefaultTokens());
	}

}
