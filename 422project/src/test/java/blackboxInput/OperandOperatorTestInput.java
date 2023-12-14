// 1 variable operand
// 1 terminator operator
package blackboxInput;

// 1 set of brackets operator
public class OperandOperatorTestInput {
	
    // 1 variable operand, 1 number operand
    // 1 assignment operator, 1 terminator operator
    int i = 0;
    
    // 1 variable operand, 1 string operand, 
    // 1 assignment operator, 1 terminator operator
    String s = "";

    
    // 1 set of brackets operator
    public void method() {

        // 1 variable operand
        // 1 unary operator, 1 terminator operator
        i++;

        // 1 variable operand, 1 number operand
        // 1 assignment operator, 1 terminator operator
        i *= 1;

        // 1 variable operand, 4 number operands
        // 2 bracket operators, 1 assignment operator, 3 comma operators, 1 terminator operator
        double[] iArray = {0.0,1.0,2.0,3.0};

        // 1 variable operand, 2 number operands
        // 1 set of brackets operator, 1 assignment operator, 1 terminator operator
        iArray[0] = 0.0;
        
        // 1 variable operand
        // 1 method call operator, 1 terminator operator
        none(s);
    }
    
    
    // 1 set of brackets operator
    public void none(String s) {
    	
    	// 1 variable operand, 1 number operands
    	// 1 block operator, 1 set of parenthesis operator, 1 logic operator
    	while (i <= 0) {
    		
    		// 2 variable operands, 1 string operand
    		// 1 assignment operator, 1 arithmetic operator, 1 terminator operator
    		s += "a" + s;
    	}
    }
    
    // 22 operands
    // 12 unique operands
    // 29 operators
    // 12 unique operands
}