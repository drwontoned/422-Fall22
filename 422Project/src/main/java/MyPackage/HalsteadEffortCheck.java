package MyPackage;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadEffortCheck extends AbstractCheck {
	 
		//initialize counts at 0
			public int n = 0;
			public int N = 0;
	
			public int uniqueOperatorCount = 0;
			public int uniqueOperandCount = 0;
			public int operandCount = 0;
			public int effort = 0;
			
			ArrayList<String> vocab = new ArrayList<String>();
			ArrayList<String> unique = new ArrayList<String>();
			
			ArrayList<Integer> operatorArrayList  = new ArrayList<Integer>();
			ArrayList<Integer> operandArrayList  = new ArrayList<Integer>();
			
			int[] operatorArray = new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
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
			
			int[] operandArray = new int[] {TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.NUM_INT,
					TokenTypes.NUM_LONG,TokenTypes.NUM_FLOAT, TokenTypes.NUM_DOUBLE};
			
			
			
			
			@Override
			public int[] getAcceptableTokens() {
				// all of the Class TokenTypes on checkstyle.org that are considered operators
				// VARIABLE_DEF, STRING_LITERAL, NUM_INT, NUM_DOUBLE, NUM_FLOAT, and NUM_LONG are used to get the operands
				return new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
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
			}
			
			@Override
			public int[] getRequiredTokens() {
				return new int[0];
			}
			
			@Override
		    public int[] getDefaultTokens() {
				return new int[] {TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
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
		    }
			
			@Override
		    public void visitToken(DetailAST aAST) {
				N++;
				String token = aAST.getText();
				if(!vocab.contains(token))
				{
					n++;
					vocab.add(token);
				}			
				
				int type = aAST.getType();
				if(operatorArrayList.contains(type)) {
					if(!unique.contains(token))
					{
						uniqueOperatorCount++;
						unique.add(token);
					}
				}
				else if(operandArrayList.contains(type)) {
					if(!unique.contains(token))
					{
						uniqueOperandCount++;
						unique.add(token);
					}
					operandCount++;
				}
		    }
			
			@Override
		    public void beginTree(DetailAST rootAST) {
				//count set back to 0 at the start of each new tree
				n = 0;
				N = 0;
				uniqueOperatorCount = 0;
				uniqueOperandCount = 0;
				operandCount = 0;
				for(int i = 0; i<operatorArray.length; i++) {
					operatorArrayList.add(operatorArray[i]);
				}
				for(int i = 0; i<operandArray.length; i++) {
					operandArrayList.add(operandArray[i]);
				}				
		    }
			
			@Override
		    public void finishTree(DetailAST rootAST) {
				//report the Halstead effort				
				int log = (int)(Math.log(n) / Math.log(2));
				int volume = N * log;
				int difficulty = ((uniqueOperatorCount/2)*operandCount)/uniqueOperandCount;
				effort = volume * difficulty;
				log(rootAST.getLineNo(), "The Halstead effort is "+effort);
		    }
	}