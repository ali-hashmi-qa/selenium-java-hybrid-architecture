package factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver(String browser) {
		
		if(browser==null) {
		 browser = ConfigReader.get("browser");
		}
		
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", ConfigReader.get("headless")));
		String executionEnv = System.getProperty("execution_env", ConfigReader.get("execution_env"));
		if (executionEnv.equalsIgnoreCase("grid")) {
			initRemoteDriver(browser, isHeadless);
		}
		else {
			initLocalDriver(browser, isHeadless);
		}
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
				Integer.parseInt(ConfigReader.get("timeout"))));
		
		getDriver().get(ConfigReader.get("url"));
	}
	
	private static void initLocalDriver(String browser, boolean isHeadless) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            if (isHeadless) {
                chromeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
            }
            driver.set(new ChromeDriver(chromeOptions));
		}
            else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
             if (isHeadless) {
                 firefoxOptions.addArguments("-headless", "--window-size=1920,1080");
                }
             driver.set(new FirefoxDriver(firefoxOptions));
            }
            else {
            	throw new RuntimeException("Unsupported browser: " + browser);
            }
	}
		
	private static void initRemoteDriver(String browser, boolean isHeadless) {
		String gridUrl = ConfigReader.get("grid_url");
		if (browser.equalsIgnoreCase("chrome")) {
	        ChromeOptions chromeOptions = new ChromeOptions();
	         if (isHeadless) {
	             chromeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
	         }
	         try {
				driver.set(new RemoteWebDriver(new URL(gridUrl), chromeOptions));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	         else if (browser.equalsIgnoreCase("firefox")) {
	            FirefoxOptions firefoxOptions = new FirefoxOptions();
	             if (isHeadless) {
	                 firefoxOptions.addArguments("-headless", "--window-size=1920,1080");
	                }
	             try {
					driver.set(new RemoteWebDriver(new URL(gridUrl), firefoxOptions));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
	            }
	            else {
	            	throw new RuntimeException("Unsupported browser: " + browser);
	            }
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
