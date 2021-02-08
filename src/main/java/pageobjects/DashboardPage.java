package pageobjects;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static interfaces.DashboardPageUI.WELCOME_TXT;

public class DashboardPage extends AbstractPage {
    WebDriver driver;
    VerifyHelper verify;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        verify = VerifyHelper.getVerify();
        verifyPageIsOpened();
    }

    @Step("Verify Dashboard page is opened")
    @Override
    public void verifyPageIsOpened() {
        waitElementVisible(driver, WELCOME_TXT);
        verify.verifyTrue(isElementDisplayed(driver, WELCOME_TXT));
    }
}
