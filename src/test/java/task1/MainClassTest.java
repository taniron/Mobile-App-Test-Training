package task1;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Expected value is 14", 14, getLocalNumber());
    }

}
