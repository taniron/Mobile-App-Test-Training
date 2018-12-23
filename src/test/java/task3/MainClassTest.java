package task3;

import org.junit.Assert;
import org.junit.Test;
import task3.MainClass;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {
        Assert.assertTrue("The string doesn't contain word 'hello' or 'Hello'",  getClassString().contains("Hello") || getClassString().contains("hello"));
    }

}
