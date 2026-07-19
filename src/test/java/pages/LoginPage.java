package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(id="user-name") private WebElement usernameInput;
	@FindBy(id="password") private WebElement pwInput;
	@FindBy(id="login-button") private WebElement loginBtn;
	@FindBy(css="h3[data-test='error']") private WebElement errorMsg;
	
	public LoginPage enterUsername(String username) {
		type(usernameInput, username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(pwInput, password);
		return this;
	}
	
	public HomePage clickLoginBtn() {
		click(loginBtn);
		return new HomePage();
	}
	
	public HomePage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginBtn();
		return new HomePage();
	}
	
	public boolean errorMsgIsDisplayed() {
		return elementIsDisplayed(errorMsg);
		
	}
	
	public String getErrorMsgText() {
		return getElementText(errorMsg);
	}

}
