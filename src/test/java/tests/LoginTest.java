package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest{
	private LoginPage loginPage;
	private HomePage homePage;
	
	@Test
	public void userLoginWithValidCreds() {
		loginPage = new LoginPage();
		homePage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
		Assert.assertTrue(homePage.isAppLogoDisplayed(), "Dashboard is not displayed after login");
		homePage.logout(); 				
	}
	
}

