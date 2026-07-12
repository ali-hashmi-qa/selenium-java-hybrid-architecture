package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(css=".app_logo") private WebElement appLogo;
	@FindBy(css=".bm-burger-button") private WebElement menuBtn;
	@FindBy(id="logout_sidebar_link") private WebElement logoutBtn;
	
	public boolean isAppLogoDisplayed() {
		return elementIsDisplayed(appLogo);
	}
	
	public LoginPage logout() {
		click(menuBtn);
		click(logoutBtn);
		return new LoginPage(); 
	}		

}
