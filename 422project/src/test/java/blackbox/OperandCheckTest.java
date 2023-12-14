package blackbox;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import myPackage.OperandCheck;

public class OperandCheckTest {
	@Test
	void test() throws IOException, CheckstyleException {
		// file setup
		String filePath = System.getProperty("user.dir") + "/src/test/java/blackboxInput/";
		File file = new File(filePath + "OperandOperatorTestInput.java");
		FileText fileText = new FileText(file,"UTF-8");
		FileContents fileContents = new FileContents(fileText);
		
		// fill AST with the file contents
		DetailAST ast = JavaParser.parse(fileContents);
		ast = JavaParser.appendHiddenCommentNodes(ast);
		
		// initialize and configure Check
		OperandCheck check = new OperandCheck();
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// setup hash set of usable tokens
		HashSet<Integer> tokens = new HashSet<Integer>();
		for(int i: check.getAcceptableTokens()) {
			tokens.add(i);
		}
		
		// begin tree
		check.beginTree(ast);
		
		// use helper to visit tokens in tree
		helper(check, ast, tokens);
		
		// finish tree
		check.finishTree(ast);
		
		// set up message
		Hashtable<String,Integer> results = new Hashtable<String,Integer>();
		String message;
		String[] resultValue;
		
		for(LocalizedMessage lm : check.getMessages()) {
			message = lm.getMessage();
			resultValue = message.split("[^\\d]+");
			results.put(message.substring(0, message.indexOf(':') + 1), Integer.parseInt(resultValue[1]));
			System.out.println("\n"+message);
		}
		
		// verify results
		assertTrue(results.get("Operands:") == 22);
		System.out.println("Operand Check Test Complete\n");		
	}
	
	
	public void helper(AbstractCheck check, DetailAST ast, HashSet<Integer> tokens) {
		while(ast != null) {
			if(tokens.contains(ast.getType())) {
				check.visitToken(ast);
			}
			helper(check, ast.getFirstChild(), tokens);
			ast = ast.getNextSibling();
		}
	}
}