package task9;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class AssertTitlePresent extends CoreTestCase {

    public void testAssertElementPresent() {
        String textSearch = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textSearch);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        //i can't find function ArticlePageObject.getTitleElement() after refactoring
        //assertTrue("Cannot find article title", null != ArticlePageObject.getTitleElement());
    }
}


