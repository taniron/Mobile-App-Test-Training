package tests;


import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {

        if (Platform.getInstance().isAndroid()){
            return;
        }

    }
}
