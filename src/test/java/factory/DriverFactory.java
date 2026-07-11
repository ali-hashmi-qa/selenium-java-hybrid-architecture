package factory;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver() {
		String browser = ConfigReader.get("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}
		else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
				Integer.parseInt(ConfigReader.get("timeout"))));
		
		getDriver().get(ConfigReader.get("url"));
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void quitDriver() {
		if(getDriver()!=null) {
		getDriver().quit();
		driver.remove();
		}
	}
}
