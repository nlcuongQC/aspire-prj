package pageobjects;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static commons.AbstractPageUI.DYNAMIC_TITLE;
import static interfaces.UserAccessPageUI.*;

public class UserAccessPage extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public UserAccessPage(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify();
        verifyPageIsOpened();
    }

    @Step("Verify User Access page is opened")
    @Override
    public void verifyPageIsOpened() {
        waitElementVisible(driver, DYNAMIC_TITLE, "Users and Access");
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_TITLE, "Users and Access"));
    }

    @Step("Click Invite new user button")
    public UserAccessPage clickToInviteNewUserBtn() {
        waitElementVisible(driver, INVITE_NEW_USER_BTN);
        clickToElementByJS(driver, INVITE_NEW_USER_BTN);
        return this;
    }

    @Step("Input {0} to Fullname textbox")
    public UserAccessPage inputToFullnameTxtbx(String fullname) {
        waitElementVisible(driver, FULLNAME_TXTBX);
        sendkeyToElement(driver, FULLNAME_TXTBX, fullname);
        return this;
    }

    @Step("Input {0} to Email textbox")
    public UserAccessPage inputToEmailTxtbx(String email) {
        waitElementVisible(driver, EMAIL_TXTBX);
        sendkeyToElement(driver, EMAIL_TXTBX, email);
        return this;
    }

    @Step("Click {0} role")
    public UserAccessPage clickToDynamicRoleRadioBtn(String role) {
        waitElementClickable(driver, DYNAMIC_ROLE_RADIO_BTN, role);
        clickToElementByJS(driver, DYNAMIC_ROLE_RADIO_BTN, role);
        return this;
    }

    @Step("Click Choose their access button")
    public UserAccessPage clickToChooseTheirAccessBtn() {
        waitElementVisible(driver, CHOOSE_THEIR_ACCESS_BTN);
        clickToElementByJS(driver, CHOOSE_THEIR_ACCESS_BTN);
        waitElementInvisible(driver, CHOOSE_THEIR_ACCESS_BTN);
        return this;
    }

    @Step("Click {0} access")
    public UserAccessPage clickToDynamicAccess(String access) {
        waitElementClickable(driver, DYNAMIC_ACCESS, access);
        clickToElement(driver, DYNAMIC_ACCESS, access);
        return this;
    }

    @Step("Verify new invited user is displayed with name: {fullname} and email: {email}")
    public void verifyInvitedNewUserInfo(String fullname, String email) {
        waitElementVisible(driver, INVITED_NEW_USER_INFO, fullname, email);
        verify.verifyTrue(isElementDisplayed(driver, INVITED_NEW_USER_INFO, fullname, email));
    }

    @Step("Verify Invite form title is {0}")
    public void verifyInviteFormTitleEqualTo(String txt) {
        waitTextElementVisible(driver, INVITE_FORM_TITLE, txt);
        verify.verifyEquals(getElementText(driver, INVITE_FORM_TITLE), txt);
    }
}