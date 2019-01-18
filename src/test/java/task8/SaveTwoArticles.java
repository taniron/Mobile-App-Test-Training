package task8;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class SaveTwoArticles {

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
    public void saveTwoArticlesTest() {
        String textSearch = "Java";

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input", 15);
        waitForElementSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), textSearch, "Cannot find search input", 15);

        //open the first article
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"), "Cannot find 'Object-oriented programming language' title", 15);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find button to open article options", 15);

        //add the first article to list
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find option to add article to reading list", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Cannot find 'Got it' tip overlay", 15);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Cannot find input to set name of articles folder", 15);

        String nameOfFolder = "Learning programming";
        waitForElementSendKeys(By.id("org.wikipedia:id/text_input"), nameOfFolder, "Cannot put text into articles folder input", 15);
        waitForElementAndClick(By.xpath("//*[@text='OK']"), "Cannot press 'Ok' button", 15);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 15);

        //add the second article
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find 'Search Wikipedia' input", 15);
        waitForElementSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), textSearch, "Cannot find search input", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"), "Cannot find 'JavaScript' title", 15);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find article title", 15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Cannot find button to open article options", 15);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find option to add article to reading list", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='Learning programming']"), "Cannot find option  to add article to 'Learning programming' directory", 15);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 15);

        //open list with articles
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Cannot find navigation button to My List", 15);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + nameOfFolder + "']"), "Cannot find created folder", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + nameOfFolder + "']"), "Cannot find created folder", 15);

        //delete "Java programming lang" article" and check it
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot find saved article");
        waitForElementNorPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot delete saved article", 15);

        //check that the second article presences
        String searchArticleLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        WebElement searchResult = waitForElementPresent(By.xpath(searchArticleLocator), "Cannot find saved articles", 15);

        int amountOfSearchResult = getAmountOfElements(By.xpath(searchArticleLocator));
        Assert.assertTrue("We found wrong number of articles", amountOfSearchResult == 1);

        //go to remaind article and check title
        waitForElementAndClick(By.xpath(searchArticleLocator), "Cannot find saved articles", 15);

        String searchArticleTitleLocator = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";
        WebElement searchTitle = waitForElementPresent(By.xpath(searchArticleTitleLocator), "Cannot find saved articles", 15);
        Assert.assertTrue("The article title doesn't match", searchTitle.getText().compareTo("JavaScript") == 0);
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

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        driver.findElements(by);
        driver.findElements(by).size();

        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean waitForElementNorPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 15);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }
}
