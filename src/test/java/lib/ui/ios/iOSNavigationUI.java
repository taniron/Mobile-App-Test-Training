package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {

    static{
        MY_LIST_LINK = "id:Saved";
    }

    public iOSNavigationUI(AppiumDriver driver){
         super(driver);

    }
}
