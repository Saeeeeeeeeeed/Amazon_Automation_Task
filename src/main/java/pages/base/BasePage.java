package pages.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    public WebDriver driver;
    JavascriptExecutor jse;

    public Logger log = LogManager.getLogger(LoginPage.class);
    private static final Duration waitTime = Duration.ofSeconds(30);

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
    }

    public WebElement element(By locator) {
        return driver.findElement(locator);
    }

    public void waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForTextToBePresentInElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, driver.findElement(locator).getText()));
    }

    public void waitForElementToBePresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void scrollToElementJSE(By locator) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", element(locator));
    }

    public void clickJSE(By locator) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element(locator));
    }

    public void scrollIntoViewAndClickJSE(By locator) {
        //scroll down the page by JSE
        scrollToElementJSE(locator);
        //click by JSE
        clickJSE(locator);
    }

    public void scrollDownJSE() {
        //to scroll down the page
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,200)", "");
    }



    public void scrollIntoView(By locator) {
        //to scroll down the page
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element(locator));
    }

    public void setText(By locator, String text) throws Exception {
        if (locator != null) {
            scrollToElementJSE(locator);
            waitForVisibility(locator);
            click(locator);
            clear(locator);
            element(locator).sendKeys(text);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getText(By locator) throws Exception {
        if (locator != null) {
            waitForVisibility(locator);
            return element(locator).getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void clear(By locator) throws Exception {
        if (locator != null) {
            waitForVisibility(locator);
            element(locator).clear();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");

    }

    public void click(By locator) throws Exception {

        if (locator != null) {
            waitForElementToBeClickable(locator);
            element(locator).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void navigateToApp(By AppLocator) throws Exception {
        if (AppLocator != null) {
            waitForVisibility(AppLocator);
            click(AppLocator);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

}