package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{



    private static final String
            SEARCH_INIT_ELEENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL ="//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCLE_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LIST = "//*[@resource-id='org.wikipedia:id/search_results_list']",
            SEARCH_ARTICLES = "//*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";


    WebElement searchResultList;
    List articles;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

//Tempates Methods
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEENT),"Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEENT), "Cannot find search input after clicking search init element", 5);
    }


    public void waitForCancleButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCLE_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancleButtonToDisappear(){
        this.waitForElementPresent(By.id(SEARCH_CANCLE_BUTTON), "Search cancel button is still present!", 5);
    }

public void clickCancelToSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCLE_BUTTON), "Cannot find and click search cancel button", 5);
}

    public void typeSearchLine(String searchLine){
        this.waitForElementSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring){
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL), "Cannot find search result with substring" + substring , 5);
    }



    public void waitForSearchResultList(){
        searchResultList = this.waitForElementPresent(By.xpath(SEARCH_RESULT_LIST), "Cannot find search results list", 5);
    }

    public void waitForSearchResultListDisappear(){
         this.waitForElementNorPresent(By.xpath(SEARCH_RESULT_LIST), "Search result should contain zero elements", 5);
    }

    public int checkAmountOfArticles(){
        articles =  searchResultList.findElements(By.xpath(SEARCH_ARTICLES));
        return articles.size();
    }

    public int getAmountOfFoundArticles(){
       this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find saved articles", 15);

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

    }




    public void clickByArticleWithSubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Cannot find and click search result with substring " + substring , 5);
    }

}

