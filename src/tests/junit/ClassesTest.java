// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package tests.junit;

import junit.framework.TestCase;
import tests.pass.Classes;

public class ClassesTest extends TestCase {

    public void testMessage() {
        this.assertEquals(Classes.message(), "Hello, World!");
    }

}
