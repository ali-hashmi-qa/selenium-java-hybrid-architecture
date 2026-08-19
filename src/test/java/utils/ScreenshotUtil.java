package utils;

import constants.FrameworkConstants;
import factory.DriverFactory;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {
	
	public static String captureScreenshot(String testName) {
		
		String screenshotPath = FrameworkConstants.Screenshots_Folder + testName
				+ DateUtil.getTimeStamp() + ".png";
		File destination = new File(screenshotPath);
		TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return screenshotPath;	
	}
	
	 public static String captureScreenshotAsBase64() {
	        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
	        return ts.getScreenshotAs(OutputType.BASE64);
	    }

}
