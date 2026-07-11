package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import factory.DriverFactory;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeClass
	public void setup() {
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
	}
	
	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
