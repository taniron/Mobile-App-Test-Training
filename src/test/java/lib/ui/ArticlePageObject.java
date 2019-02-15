package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            JAVASCRIPT_SUBTITLE,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            SECOND_ARTICLE;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public WebElement waitForJavaScriptTitleElement() {
        return this.waitForElementPresent(JAVASCRIPT_SUBTITLE, "Cannot find article title on page", 15);
    }


    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public String getJavaScriptArticleSubTitle() {
        WebElement titleElement = waitForJavaScriptTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public WebElement getArticleElement() {
        return waitForJavaScriptTitleElement();
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 15);

        //add the first article to list
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it' tip overlay", 15);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Cannot find input to set name of articles folder", 15);

        //String nameOfFolder = "Learning programming";
        this.waitForElementSendKeys(MY_LIST_NAME_INPUT, nameOfFolder, "Cannot put text into articles folder input", 15);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot press 'Ok' button", 15);

    }

    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(OPTIONS_BUTTON, "Cannot find button to open article options", 15);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
        //this.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 15);

    }

    public void clickMyArticle() {
        System.out.println("going to click " + SECOND_ARTICLE);
        this.waitForElementAndClick(SECOND_ARTICLE, "Cannot find saved article", 15);
    }

    //public void waitForSecondArticleAppear(){
    //  WebElement secondArticle = this.waitForElementPresent(By.xpath(SECOND_ARTICLE), "Cannot find saved articles", 15);
    //int amountOfSearchResult = getAmountOfElements(By.xpath(SECOND_ARTICLE));
    //Assert.assertTrue("We found wrong number of articles", amountOfSearchResult == 1);
    //}

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article, cannot find X link", 15);
    }

    public List<WebElement> getArticlesTitle() {

        return this.waitForElementsPresent(SECOND_ARTICLE, "Cannot find all articles", 15);


    }
}
