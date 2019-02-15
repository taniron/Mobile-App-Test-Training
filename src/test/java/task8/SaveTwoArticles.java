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

public class SaveTwoArticles extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming";

    public void testSaveTwoArticles() {

        String textSearch = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        //open the second article
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);
        SearchPageObject.clickByArticleWithSubstring("JavaScript");
        articlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addSecondArticleToMyList();
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(nameOfFolder);
        }
        articlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();


        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(nameOfFolder);
        }

        MyListPageObject.swipeBySecondArticleToDelete(articleTitle);

        //check that the second article presences
        int amountOfSearchResult = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found wrong number of articles", amountOfSearchResult == 1);

        //go to remained article and check title
        articlePageObject.clickMyArticle();
        String secondArticleSubTitle = articlePageObject.getJavaScriptArticleSubTitle();
        assertTrue("The article subtitle doesn't match", secondArticleSubTitle.compareTo("Programming language") == 0);


    }
}
