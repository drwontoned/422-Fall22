package MyPackage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class CommentLinesCheck extends AbstractCheck {

	//initialize count at 0
	public int count = 0;	
	public int startLine = 0;
	
    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }
	
    @Override
    public int[] getDefaultTokens() {
        return getRequiredTokens();
    }
	
    
	
    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }
	
    @Override
    public void visitToken(DetailAST ast) {
        throw new IllegalStateException("visitToken() shouldn't be called.");
    }
	
    @Override
    public void beginTree(DetailAST rootAST) {
    	count = 0;
        final Map<Integer, TextBlock> lineComments = getFileContents()
                .getSingleLineComments();
        final Map<Integer, List<TextBlock>> blockComments = getFileContents()
                .getBlockComments();
        final Set<Integer> lines = new HashSet<>();
        lines.addAll(lineComments.keySet());
        lines.addAll(blockComments.keySet());
        for (Integer lineNo : lines) {
        	final TextBlock comment;
        	if (lineComments.containsKey(lineNo)) {
        		count++;
        	} 
        	else {
        		final List<TextBlock> commentList = blockComments.get(lineNo);
        		comment = commentList.get(commentList.size() - 1);
        		int start = comment.getStartLineNo();
        		int end = comment.getEndLineNo();
        		count = count + (end + 1 - start);
        	}
        }
    }
	
    @Override
    public void finishTree(DetailAST rootAST) {
		//report the number of comments
        log(rootAST.getLineNo(), "There are "+count+" lines of comments");
    }
}
