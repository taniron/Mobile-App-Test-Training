package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";

        //SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCLE_BUTTON = "id:Close";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeCollectionView";
        SEARCH_ARTICLES = "xpath://XCUIElementTypeCell";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";

    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
