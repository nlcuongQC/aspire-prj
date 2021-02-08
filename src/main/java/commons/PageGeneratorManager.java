package commons;

import org.openqa.selenium.WebDriver;
import pageobjects.*;

public class PageGeneratorManager {
    public static HomePage getHomePage(WebDriver driver) {
        return new HomePage(driver);
    }

    public static DashboardPage getDashboardPage(WebDriver driver) {
        return new DashboardPage(driver);
    }

    public static BasePage getBasePage(WebDriver driver) {
        return new BasePage(driver);
    }

    public static ProfilePage getProfilePage(WebDriver driver) {
        return new ProfilePage(driver);
    }

    public static UserAccessPage getUserAccessPage(WebDriver driver) {
        return new UserAccessPage(driver);
    }
}
