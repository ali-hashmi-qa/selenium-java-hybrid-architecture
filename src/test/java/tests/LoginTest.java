package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.TestDataProviders;

public class LoginTest extends BaseTest{
	
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	@Test(dataProvider = "loginData", dataProviderClass = TestDataProviders.class)
    public void loginTest(String testCaseId, String username, String password, String expectedResult) {

		logger.info("Executing {} for user: {} | Expected Result: {}", testCaseId, username, expectedResult);

        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("valid")) {

            logger.info("Verifying successful login for user: {}", username);

            HomePage homePage = new HomePage();
            Assert.assertTrue(homePage.isAppLogoDisplayed(),
                    "Valid login failed for user: " + username);

            logger.info("Login successful for user: {}", username);
            homePage.logout();

        } else if (expectedResult.equalsIgnoreCase("invalid")) {

            logger.info("Verifying invalid login error for user: {}", username);

            Assert.assertTrue(loginPage.errorMsgIsDisplayed(),
                    "Error message not displayed for invalid login");

            String expectedError = "Username and password do not match any user in this service";
            Assert.assertTrue(loginPage.getErrorMsgText().contains(expectedError),
                    "Invalid login error message mismatch");

            logger.info("Invalid login error verified for user: {}", username);

        } else if (expectedResult.equalsIgnoreCase("locked")) {

            logger.info("Verifying locked user error for user: {}", username);

            Assert.assertTrue(loginPage.errorMsgIsDisplayed(),
                    "Error message not displayed for locked user");
            logger.warn("Intentionally failing the test to verify screenshot on failure");
            Assert.fail("Intentional failure to test screenshot capture functionality");

        } else {
            Assert.fail("Invalid expectedResult value in test data: " + expectedResult);
        }

        logger.info("Completed login test with Test Case ID : {}", testCaseId);
    }
}

