package com.aspire;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.BasePage;
import pageobjects.DashboardPage;
import pageobjects.HomePage;

import static commons.PageGeneratorManager.*;

public class Test_Login_01 extends AbstractTest {
    WebDriver  driver;
    DataHelper data;

    String phone, invalidShortPhone, invalidFormatPhone, errShortTxt, errFormatTxt, otp, nation;

    HomePage      homePage;
    BasePage      basePage;
    DashboardPage dashboardPage;

    @Parameters({"browser", "url"})
    @BeforeClass(description = "Open homepage")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        invalidShortPhone  = "1234567";
        invalidFormatPhone = "123456789A";
        phone              = "989677728";
        nation             = "United States (+1)";
        errShortTxt        = "Registered phone number must be at least 11 characters.";
        errFormatTxt       = "Incorrect phone format for phone.";
        otp                = "135790";

        homePage = getHomePage(driver);
    }

    @BeforeMethod(description = "Go to Homepage")
    public void beforeMethod() {
        basePage = getBasePage(driver);
        basePage.navigateToDynamicPage();

        homePage = getHomePage(driver);
        homePage.verifyPageIsOpened();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login 01: Phone numbers smaller than 11 characters")
    public void TC_01_Short_Phone() {
        homePage.inputToPhoneTxtbx(invalidShortPhone).pressTab().verifyPhoneErrorTxtEqualTo(errShortTxt);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login 02: Phone numbers wrong format")
    public void TC_02_Wrong_Format() {
        homePage.inputToPhoneTxtbx(invalidFormatPhone).clickToLoginBtn().verifyPhoneErrorTxtEqualTo(errFormatTxt);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login 03: Valid login")
    public void TC_03_Valid_Login() {
        homePage.chooseNation(nation).inputToPhoneTxtbx(phone).clickToLoginBtn().inputOTP(otp);

        dashboardPage = getDashboardPage(driver);
        dashboardPage.verifyPageIsOpened();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
