package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        SECOND_ARTICLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){

        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public WebElement getTitleElement(){

        return driver.findElement(By.id(TITLE));
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void addArticleToMyList(String nameOfFolder){
       this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON), "Cannot find button to open article options", 15);

        //add the first article to list
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Cannot find option to add article to reading list", 15);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY), "Cannot find 'Got it' tip overlay", 15);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT), "Cannot find input to set name of articles folder", 15);

        //String nameOfFolder = "Learning programming";
        this.waitForElementSendKeys(By.id(MY_LIST_NAME_INPUT), nameOfFolder, "Cannot put text into articles folder input", 15);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Cannot press 'Ok' button", 15);

    }

    public void addSecondArticleToMyList(){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON), "Cannot find button to open article options", 15);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Cannot find option to add article to reading list", 15);

        //this.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot close article, cannot find X link", 15);

    }

    public void clickMyArticle(){
        this.waitForElementAndClick(By.xpath(SECOND_ARTICLE), "Cannot find saved article", 15);
    }

    //public void waitForSecondArticleAppear(){
      //  WebElement secondArticle = this.waitForElementPresent(By.xpath(SECOND_ARTICLE), "Cannot find saved articles", 15);
        //int amountOfSearchResult = getAmountOfElements(By.xpath(SECOND_ARTICLE));
        //Assert.assertTrue("We found wrong number of articles", amountOfSearchResult == 1);
    //}

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Cannot close article, cannot find X link", 15);
    }
}
