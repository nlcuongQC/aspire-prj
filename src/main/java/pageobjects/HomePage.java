package pageobjects;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.awt.event.KeyEvent;

import static interfaces.HomePageUI.*;

public class HomePage extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify();
        verifyPageIsOpened();
    }

    @Step("Verify Homepage is opened")
    @Override
    public void verifyPageIsOpened() {
        waitElementVisible(driver, NATION_DDL_PARENT);
        verify.verifyTrue(isElementDisplayed(driver, NATION_DDL_PARENT));
    }

    @Step("Input {0} to Phone textbox")
    public HomePage inputToPhoneTxtbx(String value) {
        waitElementVisible(driver, PHONE_TXTBX);
        sendkeyToElement(driver, PHONE_TXTBX, value);
        return this;
    }

    @Step("Click Login button")
    public HomePage clickToLoginBtn() {
        waitElementClickable(driver, LOGIN_BTN);
        clickToElement(driver, LOGIN_BTN);
        return this;
    }

    @Step("Verify Phone error text equal to {0}")
    public void verifyPhoneErrorTxtEqualTo(String errFormatTxt) {
        waitElementVisible(driver, PHONE_ERROR_TXT);
        verify.verifyEquals(getElementText(driver, PHONE_ERROR_TXT), errFormatTxt);
    }

    @Step("Input otp is {0}")
    public void inputOTP(String otp) {
        waitElementClickable(driver, OTP_TXTBX);
        clickToElement(driver, OTP_TXTBX);
        pasteText(otp);
    }

    @Step("Press Tab")
    public HomePage pressTab() {
        sendKeyboardToElement(driver, PHONE_TXTBX, Keys.TAB);
        return this;
    }

    @Step("Choose nation {0}")
    public HomePage chooseNation(String nation) {
        waitElementVisible(driver, NATION_DDL_PARENT);
        //        selectItemsInCustomDropdown(driver, NATION_DDL_PARENT, NATION_DDL_ITEMS, nation);
        waitElementClickable(driver, NATION_DDL_PARENT);
        clickToElement(driver, NATION_DDL_PARENT);
        pressKeys(KeyEvent.VK_U);
        waitElementClickable(driver, NATION_DDL_ITEM, nation);
        clickToElement(driver, NATION_DDL_ITEM, nation);
        return this;
    }
}
