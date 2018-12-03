import java.lang.System;

public class TernTest {
    public static void main(String[] args) {
        String a = 5 < 4 ? "a true value" : "a false value";
        System.out.println("A false statement returned: " + a);

        String b = 1 == 1 ? "a true value" : "a false value";
        System.out.println("A true statement returned: " + b);

        String c = b == "an even truer value" ? "a true value" : "a false value";
        System.out.println("A false statement returned: " + c);
    }
}