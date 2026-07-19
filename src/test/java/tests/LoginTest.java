package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest{
	
	@Test
	public void userLoginWithValidCreds() {
		LoginPage loginPage = new LoginPage();
		HomePage homePage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
		Assert.assertTrue(homePage.isAppLogoDisplayed(), "Dashboard is not displayed after login");
		homePage.logout(); 				
	}
	
	@Test
	public void userLoginWithInvalidCreds() {
		LoginPage loginPage = new LoginPage();
		loginPage.login(ConfigReader.get("username"), ConfigReader.get("invalidPassword"));
		Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "Error msg is not displayed");
		String errorMsgText = "Username and password do not match any user in this service";
		Assert.assertTrue(loginPage.getErrorMsgText().contains(errorMsgText), "Expected error message not displayed for invalid login" );
	}
	
	@Test
	public void loginWithLockedUser() {
		LoginPage loginPage = new LoginPage();
		loginPage.login(ConfigReader.get("lockedUser"), ConfigReader.get("password"));
		Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "Error msg is not displayed");
		String errorMsgText = "Sorry, this user has been locked out.";
		Assert.assertTrue(loginPage.getErrorMsgText().contains(errorMsgText), "Expected error message not displayed for locked-out user" );
	}
	
}

