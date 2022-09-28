package MyPackage;
 
import com.puppycrawl.tools.checkstyle.api.*;
 
public class OperandCheck extends AbstractCheck {
 
	//initialize count at 0
		public int count = 0;
		
		@Override
		public int[] getAcceptableTokens() {
			
			return new int[] {TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL,
					TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};
		}
		
		@Override
		public int[] getRequiredTokens() {
			return new int[0];
		}
		
		@Override
	    public int[] getDefaultTokens() {
			return new int[] {TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL,
					TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};
	    }
		
		@Override
	    public void visitToken(DetailAST aAST) {
			//each acceptable token visited increases count
	        count++;
	    }
		
		@Override
	    public void beginTree(DetailAST rootAST) {
			//count set back to 0 at the start of each new tree
	        count = 0;
	    }
		
		@Override
	    public void finishTree(DetailAST rootAST) {
			//report the number of operands
			log(rootAST.getLineNo(), "There are "+count+" Operands");
	    }
}
