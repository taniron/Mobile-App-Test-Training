package Task5;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SearchPresenceTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "6.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "C:\\Git\\Mobile-App-Test-Training\\Mobile-App-Test-Training\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void findSearchTextBoxTest() {
        WebElement elementToInitSearch = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        elementToInitSearch.click();

        waitForElementPresentByXpath("//*[contains(@text, 'Search…')]", "Cannot find search input", 5);
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
