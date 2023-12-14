package blackboxInput;

public class ExpressionTestInput {
    // expression 1
    int i = 0;

    public void method() {

        // expression 2 
        if (i == 0) {

            // expression 3
            i++;

            // expression 4
        } else if (i > 0) {

        } else {

            // expression 5
            none(i);
        }
    }

    // expression 6
    public void none(int i) {

        // expression 7
        String s = "s" + i;

    }
}