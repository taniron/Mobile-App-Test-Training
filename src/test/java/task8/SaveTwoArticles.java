package task8;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;

public class SaveTwoArticles extends CoreTestCase{

    public void testSaveTwoArticles()  {

        String textSearch = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String articleTitle = ArticlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";

        ArticlePageObject.addArticleToMyList(nameOfFolder);
        ArticlePageObject.closeArticle();

        //open the second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);
        SearchPageObject.clickByArticleWithSubstring("JavaScript");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addSecondArticleToMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(nameOfFolder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject.openFolderByName(nameOfFolder);
        MyListPageObject.swipeBySecondArticleToDelete(articleTitle);

        //check that the second article presences
        int amountOfSearchResult = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found wrong number of articles", amountOfSearchResult == 1);

        //go to remaind article and check title
        ArticlePageObject.clickMyArticle();
        String secondArticleTitle = ArticlePageObject.getArticleTitle();
        assertTrue("The article title doesn't match", secondArticleTitle.compareTo("JavaScript") == 0);
    }
}
