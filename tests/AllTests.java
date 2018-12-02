import java.lang.System;

public class AllTests {
    public static void main(String[] args) {
        System.out.println("Starting all tests...");
        System.out.println(plusAssignTest());
        System.out.println(minusAssignTest());
        System.out.println(multAssignTest());
        System.out.println(divAssignTest());
        System.out.println(modAssignTest());

    }

    public static String plusAssignTest() {
        int a = 0;
        a += 1;
        if (a == 1) {
            return "Plus assign pass";
        } else {
            return "Plus assign fail";
        }
    }

    public static String minusAssignTest() {
        int a = 0;
        a -= 1;
        if (a == -1) {
            return "Minus assign pass";
        } else {
            return "Minus assign fail";
        }
    }

    public static String multAssignTest() {
        int a = 2;
        a = 2;
        if (a == 4) {
            return "Mult assign pass";
        } else {
            return "Mult assign fail";
        }
    }

    public static String divAssignTest() {
        int a = 4;
        a /= 2;
        if (a == 2) {
            return "Div assign pass";
        } else {
            return "Div assign fail";
        }
    }

    public static String modAssignTest() {
        int a = 11;
        a %= 3;
        if (a == 2) {
            return "Mod assign pass";
        } else {
            return "Mod assign fail";
        }
    }
}
