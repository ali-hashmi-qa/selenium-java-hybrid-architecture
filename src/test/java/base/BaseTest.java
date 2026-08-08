package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import factory.DriverFactory;


public class BaseTest {
	private static final Logger logger = LogManager.getLogger(BaseTest.class);	
	protected WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		logger.info("Starting browser: {}", browser);
		DriverFactory.initDriver(browser);
		driver = DriverFactory.getDriver();
	}
	
	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
