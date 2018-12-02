import java.lang.System;

public class DoWhileTest {
	public static void main(String[] args) {
		int a = 0;
		System.out.println("a before do-while is: " + a);
		do {
			a = a + 1;
		} while (a < 5);
		System.out.println("a after do-while is: " + a);
	}
}
