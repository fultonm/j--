import java.lang.System;

public class UnaryOperationTest {
    public static void main(String[] args) {
        String a = true || false ? "it's true" : "it's false";
        System.out.println("Should be true: " + a);

        a = false || true ? "it's true" : "it's false";
        System.out.println("Should be true: " + a);

        a = false || false ? "it's true" : "it's false";
        System.out.println("Should be false: " + a);

        a = true || true ? "it's true" : "it's false";
        System.out.println("Should be true: " + a);

        a = function1() || function2() ? "it's true" : "it's false";
        System.out.println("Should not call function 2 (and should be true): " + a);
    }

    public static boolean function1() {
        boolean val = true;
        System.out.println("Function 1 returning " + val);
        return val;
    }

    public static boolean function2() {
        boolean val = false;
        System.out.println("Function 2 returning " + val);
        return val;
    }
}