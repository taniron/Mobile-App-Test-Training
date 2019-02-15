package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='{FOLDER_NAME}']";
    }

    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
