// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package tests.junit;

import junit.framework.TestCase;
import tests.pass.Factorial;

public class FactorialTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFactorial() {
        this.assertEquals(Factorial.factorial(5), 120);
    }

}
