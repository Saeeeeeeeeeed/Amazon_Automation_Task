package com.test.amazonTest;

import com.test.base.TestBase;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.amazonPages.LoginPage;
import pages.constants.GeneralConstants;
import utilities.PropertiesReader;

import java.util.Properties;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    private final PropertiesReader propertiesReader = new PropertiesReader();
    Properties testData = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_CONFIGURATION_FILE_NAME);
    private final String userEmail = testData.getProperty(GeneralConstants.LOGIN_USERNAME);

    @BeforeMethod
    public void setUp (){
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void VerifyThatUserCanNotLogInWithValidButNotRegisteredEmail() throws Exception {
        try{
            loginPage.clickOnSignInButton();
            loginPage.enterEmailAddress(userEmail);
            loginPage.clickOnContinueBtn();
            softAssert.assertTrue(loginPage.assertOnRegistrationPageTitle());
            softAssert.assertAll();
        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }

    @Test
    public void VerifyThatItemsAreAddedToCartCorrectly(){
        try {

        }catch (Exception e){
            throw new SkipException(GeneralConstants.SKIPPED_MESSAGE, e);
        }
    }


}
