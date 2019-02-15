package task8;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class SaveTwoArticles extends CoreTestCase{

    private static final String nameOfFolder = "Learning programming";

    public void testSaveTwoArticles()  {

        String textSearch = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        ArticlePageObject.waitForTitleElement();

        String articleTitle = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(nameOfFolder);
        } else{
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        //open the second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);
        SearchPageObject.clickByArticleWithSubstring("JavaScript");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addSecondArticleToMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(nameOfFolder);
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();


        if (Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(nameOfFolder);
        }

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
