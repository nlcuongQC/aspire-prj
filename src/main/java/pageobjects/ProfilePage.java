package pageobjects;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static commons.AbstractPageUI.DYNAMIC_TITLE;
import static interfaces.ProfilePageUI.DYNAMIC_MENU;

public class ProfilePage extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify();
        verifyPageIsOpened();
    }

    @Step("Verify Profile page is opened")
    @Override
    public void verifyPageIsOpened() {
        waitElementVisible(driver, DYNAMIC_TITLE, "Profile");
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_TITLE, "Profile"));
    }

    @Step("Click to {0} menu")
    public void clickToDynamicMenu(String menu) {
        waitElementVisible(driver, DYNAMIC_MENU, menu);
        clickToElement(driver, DYNAMIC_MENU,menu);
    }


}
