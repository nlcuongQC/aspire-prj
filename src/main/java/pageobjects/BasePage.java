package pageobjects;

import commons.AbstractPage;
import commons.Endpoints;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

import static interfaces.BasePageUI.*;

public class BasePage extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify();
        verifyPageIsOpened();
    }

    @Override
    public void verifyPageIsOpened() {
        waitElementVisible(driver, ASPIRE_LOGO);
        verify.verifyTrue(isElementDisplayed(driver, ASPIRE_LOGO));
    }

    @Step("Click to {0} menu")
    public void clickToDynamicMenu(String menu) {
        waitElementClickable(driver, DYNAMIC_MENU, menu);
        clickToElement(driver, DYNAMIC_MENU, menu);
    }

    @Step("Navigate to page with endpoint {0}")
    public void navigateToDynamicPage(String endpoint) {
        openPageUrl(driver, GlobalConstants.BASE_URL + endpoint);
    }

    @Step("Navigate to homepage")
    public void navigateToDynamicPage() {
        openPageUrl(driver, GlobalConstants.BASE_URL);
    }
}
