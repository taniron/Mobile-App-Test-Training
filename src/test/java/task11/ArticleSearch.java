package task11;


import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class ArticleSearch extends CoreTestCase {

    public void testSearchArticleByTitleAndDescription() {

        String textSearch = "Java";
        String title = "Java (programming language)";
        String description = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);


        final WebElement searchArticleResult = SearchPageObject.waitForElementByTitleAndDescription(title, description);
        assertTrue("Cannot find article with requested title and description", searchArticleResult != null);

    }

    public void testSearchThreeFirstArticles() {

        String textSearch = "Java";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(textSearch);

        int amountOfSearchArticles = searchPageObject.checkAmountOfArticles();
        assertTrue("Amount of articles is less than 3", amountOfSearchArticles >= 3);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        for (int i = 0; i < articlePageObject.getSearchResultArticlesTitle().size() && i < 3; i++) {
            Assert.assertTrue("The article doesn't match the searched word", articlePageObject.getSearchResultArticlesTitle().get(i).getText().contains(textSearch));
        }
    }
}

