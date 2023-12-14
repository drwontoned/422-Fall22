package blackboxInput;

public class LoopTestInput {
    boolean test = false;

    public void method() {

        // loop 1
        for (int i = 0; i < 1; i++) {
            test = true;
        }

        // loop 2
        while (test == true) {
            test = false;
        }

        // loop 3
        do {
            test = true;
        } while (test == false);
    }
}