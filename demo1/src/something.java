//1
//2

/*this
 * is
 * a
 * 6
 * line
 comment*/

public class something {
int x = 0; // 1 operator 2 operands
	
public void test(){	
	x = 1 * 3 + 5 - 9 / 9 + 7; // 6 operators 7 operands
	x = 1; // 1 operator 2 operands
	x = 2; // 1 operator 2 operands
	
}
public void test2() {
	while(x == 0) { // 1 operator 2 operands
		x = 0; // 1 operator 2 operands
	}
	
	for(int i = 0; i<1; i++) { // 3 operator 5 operands
		x = 0; // 1 operator 2 operands
	}
	
	do {
		x = 0; // 1 operator 2 operands
	} while (x == 0); // 1 operator 2 operands
}
}
