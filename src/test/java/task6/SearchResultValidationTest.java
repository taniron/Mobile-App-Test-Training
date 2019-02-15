package task6;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;

public class SearchResultValidationTest extends CoreTestCase {

    public void testSearchResultValidation() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultList();
        assertTrue("Search result should contain more than zero elements", SearchPageObject.checkAmountOfArticles() > 0);

        SearchPageObject.waitForCancleButtonToAppear();
        SearchPageObject.clickCancelToSearch();
        SearchPageObject.waitForSearchResultListDisappear();
    }
}
