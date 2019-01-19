package task10;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class RotationScreenSavedTest {
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

        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void rotateScreenSaveTest() {
        String textSearch = "Java";

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input", 15);
        waitForElementSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), textSearch, "Cannot find search input", 15);

        //open the article
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"), "Cannot find 'Object-oriented programming language' title", 15);

        String titleBeforeRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find title of article", 15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find title of article", 15);

        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = waitForElementAndGetAttribute(By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find title of article", 15);

        Assert.assertEquals("Article title have been changed after screen rotation", titleBeforeRotation, titleAfterSecondRotation);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
