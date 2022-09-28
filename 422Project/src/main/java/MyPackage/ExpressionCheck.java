package MyPackage;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCheck extends AbstractCheck {

	//initialize count at 0
		public int count = 0;
		
		@Override
		public int[] getAcceptableTokens() {
			// each EXPR
			return new int[] {TokenTypes.EXPR};
		}
		
		@Override
		public int[] getRequiredTokens() {
			return new int[0];
		}
		
		@Override
	    public int[] getDefaultTokens() {
			return new int[] {TokenTypes.EXPR};
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
			//report the number of expressions
	        log(rootAST.getLineNo(), "There are "+count+" expressions");
	    }
}