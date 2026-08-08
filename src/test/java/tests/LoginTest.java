package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest{
	
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	@Test
	public void userLoginWithValidCreds() {
		logger.info("Starting test: userLoginWithValidCreds");
		LoginPage loginPage = new LoginPage();
		logger.info("Attempting login with valid credentials");
		HomePage homePage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
		logger.info("Verifying successful login");
		logger.info("Login successful, logging out");
		Assert.assertTrue(homePage.isAppLogoDisplayed(), "Dashboard is not displayed after login");
		homePage.logout();
		logger.info("Test completed: userLoginWithValidCreds");
	}
	
	@Test
	public void userLoginWithInvalidCreds() {
		logger.info("Starting test: userLoginWithInvalidCreds");
		LoginPage loginPage = new LoginPage();
		logger.info("Attempting login with invalid credentials");
		loginPage.login(ConfigReader.get("username"), ConfigReader.get("invalidPassword"));
		Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "Error msg is not displayed");
		String errorMsgText = "Username and password do not match any user in this service";
		logger.info("Capturing error message: {}", errorMsgText);
		Assert.assertTrue(loginPage.getErrorMsgText().contains(errorMsgText), "Expected error message not displayed for invalid login" );
		logger.info("Test completed: userLoginWithInvalidCreds");
	}
	
	@Test
	public void loginWithLockedUser() {
		logger.info("Starting test: loginWithLockedUser");
		LoginPage loginPage = new LoginPage();
		logger.info("Attempting login with locked user");
		loginPage.login(ConfigReader.get("lockedUser"), ConfigReader.get("password"));
		Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "Error msg is not displayed");
		String errorMsgText = "Sorry, this user has been locked out.";
		logger.info("Capturing error message: {}", errorMsgText);
		Assert.assertTrue(loginPage.getErrorMsgText().contains(errorMsgText), "Expected error message not displayed for locked-out user" );
		logger.info("Intentionally failing the test to verify screenshot capture");
	    Assert.fail("Intentional failure to verify screenshot capture");
	}
	
}

