package task9;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;

public class AssertTitlePresent extends CoreTestCase {

    public void testAssertElementPresent() {
        String textSearch = "Java";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        assertTrue("Cannot find article title", null != ArticlePageObject.getTitleElement());
    }
}


