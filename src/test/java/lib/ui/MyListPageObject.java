package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{

    public static final String
        FOLDER_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL =  "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String nameOfFolder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSaveArticleXpathByTitle(String articleTitle){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }

    public void openFolderByName(String nameOfFolder){

        String folderNameXpath = getFolderXpathByName(nameOfFolder);
        this.waitForElementPresent(By.xpath(folderNameXpath), "Cannot find folder by name", 15);
        this.waitForElementAndClick(By.xpath(folderNameXpath), "Cannot find folder by name", 15);

    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        String articleXpath = getSaveArticleXpathByTitle(articleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Cannot find saved article by title " + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        String articleXpath = getFolderXpathByName(articleTitle);
        this.waitForElementNorPresent(By.xpath(articleXpath), "Saved article still present with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getFolderXpathByName(articleTitle);
        this.swipeElementToLeft(By.xpath(articleXpath), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(articleTitle);

    }

    public void swipeBySecondArticleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getSaveArticleXpathByTitle(articleTitle);
        this.swipeElementToLeft(By.xpath(articleXpath), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(articleTitle);

    }




}
