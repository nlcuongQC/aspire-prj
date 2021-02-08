package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.DELETE;
import static org.openqa.selenium.Keys.LEFT_CONTROL;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfAllElements;

public abstract class AbstractPage {
    private WebElement         element;
    private WebDriverWait      wait;
    private JavascriptExecutor js;
    private List<WebElement>   elements;
    private Actions            actions;

    protected void openPageUrl(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    protected By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    protected WebElement find(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    protected List<WebElement> finds(WebDriver driver, String xpathValue) {
        return driver.findElements(byXpath(xpathValue));
    }

    protected void clickToElement(WebDriver driver, String xpathValue) {
        try {
            find(driver, xpathValue).click();
        } catch (StaleElementReferenceException e) {
            find(driver, xpathValue).click();
        }
    }

    protected void clickToElement(WebElement element) {
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            element.click();
        }
    }

    protected void clickToElement(WebDriver driver, String xpathValue, String... values) {
        try {
            find(driver, castToObject(xpathValue, values)).click();
        } catch (StaleElementReferenceException ex) {
            find(driver, castToObject(xpathValue, values)).click();
        }
    }

    protected void sendkeyToElement(WebDriver driver, String xpathValue, String text) {
        element = find(driver, xpathValue);
        element.clear();
        element.sendKeys(text);

    }

    protected String getElementText(WebDriver driver, String xpathValue) {
        try {
            return find(driver, xpathValue).getText();
        } catch (StaleElementReferenceException e) {
            return find(driver, xpathValue).getText();
        }
    }

    protected String getElementText(WebDriver driver, String xpathValue, String... values) {
        return find(driver, castToObject(xpathValue, values)).getText();
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue) {
        try {
            element = find(driver, xpathValue);
            return element.isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
            return false;
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
        try {
            return find(driver, castToObject(xpathValue, values)).isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.getMessage();
            return false;
        }
    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys keys) {
        actions = new Actions(driver);
        actions.sendKeys(find(driver, xpathValue), keys).perform();
    }

    protected void pressKeys(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
        } catch (AWTException ignored) {

        }
    }

    protected void pasteText(String value) {
        StringSelection text = new StringSelection(value);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(text, null);

        Robot robot;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            sleepInSeconds(1);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    protected void clickToElementByJS(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, xpathValue));
    }

    protected void clickToElementByJS(WebDriver driver, String xpathValue, String values) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, castToObject(xpathValue, values)));
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(xpathValue, values))));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(xpathValue, values))));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void waitTextElementVisible(WebDriver driver, String xpathValue, String text) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(byXpath(xpathValue), text));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    protected void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void verifyPageIsOpened();
}
