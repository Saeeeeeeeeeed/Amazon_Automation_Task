package pages.amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.PageBase;
import pages.constants.GeneralConstants;

import java.math.BigDecimal;
import java.util.Properties;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    Properties urlProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);

    By signInButton = By.id("nav-link-accountList");
    By emailAddress = By.xpath("//input[contains(@id,'ap_email')]");
    By continueButton = By.xpath("//span[@id='continue']");
    By registrationPageTitle = By.xpath("//h1[@class='a-spacing-small moa_desktop_signup']");
    By errorMessageInLogin = By.xpath("//div[@id='auth-error-message-box']//h4[@class='a-alert-heading']");
    By invalidEmailError = By.xpath("//h1[@class='a-size-medium-plus a-spacing-small']");
    By categoryItems = By.xpath("//button[@class='Bubble-module__bubble_HMAsFLoooPgV9bEqZsLu']");
    By allTabBtn = By.id("nav-hamburger-menu");
    By todayDeals = By.xpath("(//a[text()='عروض اليوم'])[2]");
    By itemsList = By.xpath("//div[contains(@class,'ProductCard-module__imageWrapper_ytp7KTuzmF4mDTFCQLzr')]");
    By productItemsList = By.xpath("//li[@class='swatchAvailable']");
    By productSize = By.id("native_dropdown_selected_size_name");
    By productPrice = By.xpath("//div[@id='apex_offerDisplay_desktop']/descendant::span[@class='a-price-whole' or @class='a-price-decimal' or @class='a-price-fraction']");
    By productName = By.id("productTitle");
    By sizeList = By.id("native_dropdown_selected_size_name");
    By sizeListItems = By.xpath("//option[@class='dropdownAvailable']");
    By quantityList = By.id("a-autoid-22-announce");
    By quantityListItems = By.xpath("//a[contains(@class,'a-dropdown-link')]");
    By addToCartBtn = By.id("add-to-cart-button");
    By cartItems = By.xpath("//a[@id='nav-cart']");
    By itemPriceInCartList = By.xpath("//div[@class='a-section a-spacing-mini']/span");
    By itemNameInCartList = By.xpath("//li[@class='a-spacing-mini sc-item-product-title-cont']/span");
    By itemQuantityInCardList = By.xpath("//span[@class='a-button-text a-declarative']/span[@class='a-dropdown-prompt']");
    By subTotalInCardList = By.xpath("//span[@id='sc-subtotal-amount-activecart']/span");
    By ordersInList = By.xpath("//a[@class='nav-link nav-item']/span[text()='مشترياتك']");
    By addressInList = By.xpath("//a[@class='nav-link nav-item']/span[text()='عناوينك']");
    By listsInList = By.xpath("//a[@class='nav-link nav-item']/span[text()='قوائمك']");
    By loginPageTitle = By.xpath("//h1[@class='a-spacing-small']");
    By listPageTitle = By.xpath("//span[@class='al-intro-banner-header']");
    By incorrectLoginMessage = By.xpath("//h1[@class='a-spacing-small moa_desktop_signup'] | //div[@id='auth-error-message-box']//h4[@class='a-alert-heading'] | //h1[@class='a-size-medium-plus a-spacing-small']");



    public void navigateToAmazonHomePage() {
        navigateTo(urlProperties.getProperty(GeneralConstants.AMAZON_HOME_PAGE_URL));
        if (findElements(signInButton).isEmpty()){
            navigateTo(urlProperties.getProperty(GeneralConstants.AMAZON_HOME_PAGE_URL));
            waits.waitForVisibility(signInButton);
        }
    }

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
        return getText(incorrectLoginMessage).contains(GeneralConstants.REGISTRATION_PAGE_TITLE)
                || getText(incorrectLoginMessage).contains(GeneralConstants.REGISTRATION_WITH_INVALID_EMAIL)
                || getText(incorrectLoginMessage).contains(GeneralConstants.LOGIN_ERROR_MESSAGE);

    }

    public void clickOnAllTab() throws Exception {
        waits.waitForVisibility(signInButton);
        click(allTabBtn);
    }

    public void clickOnTodayDeals() throws Exception {
        click(todayDeals);
    }

    public void clickOnSecondCategory(){
        getElementFromListByIndex(categoryItems,1).click();
    }

    public void clickOnFirstProduct() throws Exception {
        scrollToElement(itemsList);
        getElementFromListByIndex(itemsList,0).click();
    }

    public void clickOnSecondItemProduct() throws Exception {
        getElementFromListByIndex(productItemsList,1).click();
        click(productSize);
        selectFromListByIndex(sizeListItems,0);

    }

    public void addItemToCartWithQtyTwo() throws Exception {
        click(quantityList);
        selectFromListByIndex(quantityListItems,1);
        click(addToCartBtn);

    }

    public void clickOnCart() throws Exception {
        click(cartItems);
    }

    public String getItemNameInItemPage() throws Exception {
        String[] words = getText(productName).split(" ");

        return words[0]; //+" "+words[1];
    }

    public String getItemPriceInItemPage() throws Exception {
        String value = getElementFromListByIndex(productPrice,0).getText()+"."
                +getElementFromListByIndex(productPrice,2).getText();
        System.out.println(value);
        return value;
    }

    public String getItemPriceInCardList() throws Exception {
        return getText(itemPriceInCartList);
    }

    public String getItemNameInCardList() throws Exception {
        return getText(itemNameInCartList);
//        String[] words = getText(itemNameInCartList).split(" ");
//
//        return words[0]+" "+words[1];
    }

    public String getItemQuantityInCartList() throws Exception {
        return getText(itemQuantityInCardList);
    }

    public String calculateSubTotal(String total){
        BigDecimal number = new BigDecimal(total);
        return (number.multiply(BigDecimal.valueOf(2))).toString();
    }

    public String getSubTotalFromCartList() throws Exception {
        return getText(subTotalInCardList);
    }

    public void hoverOnList(){
        hover(signInButton);
    }

    public void clickOnOrders() throws Exception {
        click(ordersInList);
    }

    public void clickOnAddress() throws Exception {
        click(addressInList);
    }

    public void clickOnLists() throws Exception {
        click(listsInList);
    }

    public String getLoginPageTitle() throws Exception {
        return getText(loginPageTitle);
    }

    public String newAccountTitle() throws Exception {
        return getText(incorrectLoginMessage);
    }

    public String getListTitle () throws Exception {
        return getText(listPageTitle);
    }

}
