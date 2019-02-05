package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone SE");
        desiredCapabilities.setCapability("platformVersion", "11.3");

        desiredCapabilities.setCapability("app", "/Users/yurironin/apk/Wikipedia.app");
        driver = new IOSDriver(new URL(AppiumURL), desiredCapabilities);
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.setUp();
    }

}
