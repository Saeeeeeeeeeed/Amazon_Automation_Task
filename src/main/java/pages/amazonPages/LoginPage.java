package pages.amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.PageBase;
import pages.constants.GeneralConstants;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By signInButton = By.id("nav-link-accountList");
    By emailAddress = By.xpath("//input[contains(@id,'ap_email')]");
    By continueButton = By.xpath("//span[@id='continue']");
    By registrationPageTitle = By.xpath("//h1[@class='a-spacing-small moa_desktop_signup']");
    By invalidEmailError = By.xpath("//h1[@class='a-size-medium-plus a-spacing-small']");
    By categoryItems = By.xpath("//button[@class='Bubble-module__bubble_HMAsFLoooPgV9bEqZsLu']");
    By allTabBtn = By.id("nav-hamburger-menu");
    By todayDeals = By.xpath("(//a[text()='عروض اليوم'])[2]");
    By itemsList = By.xpath("//div[@class='GridItem-module__container_PW2gdkwTj1GQzdwJjejN']");
    By productItemsList = By.xpath("//li[@class='swatchAvailable']");
    By productSize = By.id("native_dropdown_selected_size_name");
    By productPrice = By.xpath("//div[@id='apex_offerDisplay_desktop']/descendant::span[@class='a-price-whole']");
    By productName = By.id("productTitle");
    



    public void clickOnSignInButton() throws Exception {
        click(signInButton);
    }

    public void enterEmailAddress(String email) throws Exception {
        waits.waitForVisibility(emailAddress);
        setText(emailAddress,email);
    }

    public void clickOnContinueBtn() throws Exception {
        click(continueButton);
    }

    public boolean assertOnRegistrationPageTitle() throws Exception {
        return getText(registrationPageTitle).equals(GeneralConstants.REGISTRATION_PAGE_TITLE) || getText(invalidEmailError).contains(GeneralConstants.REGISTRATION_WITH_INVALID_EMAIL);
    }

    public void clickOnAllTab() throws Exception {
        click(allTabBtn);
    }

    public void clickOnTodayDeals() throws Exception {
        click(todayDeals);
    }


}
