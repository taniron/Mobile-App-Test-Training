package task2;

import org.junit.Assert;
import org.junit.Test;
import task2.MainClass;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("The value is less or equal 45",  getClassNumber()>45);
    }

}
