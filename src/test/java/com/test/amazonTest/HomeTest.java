package com.test.amazonTest;

import com.test.base.TestBase;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.amazonPages.HomePage;
import pages.constants.GeneralConstants;
import utilities.PropertiesReader;

import java.util.Properties;

public class HomeTest extends TestBase {

    HomePage homePage;

    private final PropertiesReader propertiesReader = new PropertiesReader();
    Properties testData = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);
    private final String userEmail = testData.getProperty(GeneralConstants.LOGIN_USERNAME);

    @BeforeMethod
    public void setUp (){
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        homePage.navigateToAmazonHomePage();
    }

    @Test(description = "#1",priority = 1)
    public void VerifyThatUserCanNotLogInWithValidButNotRegisteredEmail() {
        try{
            homePage.clickOnSignInButton();
            homePage.enterEmailAddress(userEmail);
            homePage.clickOnContinueBtn();
            softAssert.assertTrue(homePage.assertOnRegistrationPageTitle());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test(description = "#2",priority = 2)
    public void VerifyThatItemsAreAddedToCartCorrectly() throws Exception {
        try {
            homePage.clickOnAllTab();
            homePage.clickOnTodayDeals();
            homePage.clickOnSecondCategory();
            homePage.clickOnFirstProduct();
            homePage.clickOnSecondItemProduct();
            String expectedPrice = homePage.getItemPriceInItemPage();
            String expectedName = homePage.getItemNameInItemPage();
            String expectedSubTotal = homePage.calculateSubTotal(expectedPrice);
            homePage.addItemToCartWithQtyTwo();
            homePage.clickOnCart();

            softAssert.assertTrue(homePage.getItemPriceInCardList().contains(expectedPrice));
            softAssert.assertTrue(homePage.getItemNameInCardList().contains(expectedName));
            softAssert.assertEquals(homePage.getItemQuantityInCartList(),GeneralConstants.ITEM_QUANTITY);
            softAssert.assertTrue(homePage.getSubTotalFromCartList().contains(expectedSubTotal));
            softAssert.assertAll();

        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test(description = "#3",priority = 3)
    public void VerifyThatYouCanNotSeeYourOrders() throws Exception {
        try {
            homePage.hoverOnList();
            homePage.clickOnOrders();

            softAssert.assertTrue(homePage.getLoginPageTitle().contains(GeneralConstants.LOGIN_PAGE_TITLE));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }

    }

    @Test(description = "#4",priority = 4)
    public void VerifyThatYouCanNotSeeYourAddresses() throws Exception {
        try {
            homePage.hoverOnList();
            homePage.clickOnAddress();

            softAssert.assertTrue(homePage.newAccountTitle().contains(GeneralConstants.LOGIN_PAGE_TITLE));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }

    }

    @Test(description = "#5",priority = 5)
    public void VerifyThatYouCanSeeYourLists() throws Exception {
        try {
            homePage.hoverOnList();
            homePage.clickOnLists();

            softAssert.assertTrue(homePage.getListTitle().contains(GeneralConstants.LISTS_PAGE_TITLE));
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }


}
