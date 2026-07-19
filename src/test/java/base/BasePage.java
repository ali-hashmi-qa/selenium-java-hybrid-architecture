package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import factory.DriverFactory;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage() {
		driver = DriverFactory.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);		
	}
	
	protected void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	protected void type(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(value);
	}
	
	protected boolean elementIsDisplayed(WebElement element ) {
		return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}
	
	protected String getTitleText() {
		return driver.getTitle();
	}
	
	protected String getElementText(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).getText();		
	}

}
