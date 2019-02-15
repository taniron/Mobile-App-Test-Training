package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;


public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    //private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";
    //protected Platform Platform;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomePageForIOSApp();

        //this.Platform = new Platform();
       // driver = this.Platform.getDriver();

        //DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        //driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.setUp();
    }

    private void skipWelcomePageForIOSApp(){

        if(Platform.getInstance().isIOS()){
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }





}
