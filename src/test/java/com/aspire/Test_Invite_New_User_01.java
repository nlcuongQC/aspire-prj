package com.aspire;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Endpoints;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;

import static commons.PageGeneratorManager.*;

public class Test_Invite_New_User_01 extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     fullname, email, otp, nation, phone;

    BasePage       basePage;
    ProfilePage    profilePage;
    UserAccessPage userAccessPage;
    HomePage       homePage;
    DashboardPage  dashboardPage;

    @Parameters({"browser", "url"})
    @BeforeClass(description = "Open profile page")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        phone  = "989677728";
        nation = "United States (+1)";
        otp    = "135790";

        homePage = getHomePage(driver);
        homePage.verifyPageIsOpened();
        homePage.chooseNation(nation).inputToPhoneTxtbx(phone).clickToLoginBtn().inputOTP(otp);

        dashboardPage = getDashboardPage(driver);
        dashboardPage.verifyPageIsOpened();

        basePage = getBasePage(driver);
        basePage.clickToDynamicMenu("Profile");
    }

    @BeforeMethod(description = "Go to Users and Access")
    public void beforeMethod() {
        basePage = getBasePage(driver);
        basePage.navigateToDynamicPage(Endpoints.PROFILE_PAGE);

        profilePage = getProfilePage(driver);
        profilePage.clickToDynamicMenu("Users and Access");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Invite 01: Invite new user", dataProvider = "data", alwaysRun = true)
    public void TC_01_Invite_New_User(String role, String access) {
        fullname = data.getFullname();
        email    = data.getEmail();


        userAccessPage = getUserAccessPage(driver);
        userAccessPage.clickToInviteNewUserBtn()
                      .inputToFullnameTxtbx(fullname)
                      .inputToEmailTxtbx(email)
                      .clickToDynamicRoleRadioBtn(role)
                      .clickToChooseTheirAccessBtn()
                      .clickToDynamicAccess(access)
                      .verifyInviteFormTitleEqualTo("Sign Authorisation Letter");

        basePage = getBasePage(driver);
        basePage.navigateToDynamicPage(Endpoints.USERS_AND_ACCESS_PAGE);

        userAccessPage = getUserAccessPage(driver);
        userAccessPage.verifyInvitedNewUserInfo(fullname, email);
    }

    @DataProvider(name = "data")
    public Object[][] NewUserData() {
        return new Object[][]{{"Director", "Admin"},
                              {"Director", "View and transact"},
                              {"Director", "View and submit"},
                              {"Employee", "View and transact"},
                              {"Employee", "View and submit"},
                              {"Other", "View and transact"},
                              {"Other", "View and submit"}};
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
