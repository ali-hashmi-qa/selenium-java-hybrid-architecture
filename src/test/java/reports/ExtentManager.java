package reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import constants.FrameworkConstants;
import utils.DateUtil;

public final class ExtentManager {
	
	private static ExtentReports extent;
	
	private ExtentManager() {};
	
	 public static synchronized ExtentReports getInstacnce() {
		 
		 if(extent==null) {
			 
			 createReportDirectory();
			 String reportPath = FrameworkConstants.Extent_Report_Folder
					 + FrameworkConstants.Extent_Report_Name + DateUtil.getTimeStamp()
					 + FrameworkConstants.Report_Extension;
			 
			 ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			 spark.config().setTheme(Theme.DARK);
			 spark.config().setDocumentTitle("Automation Test Report");
	         spark.config().setReportName("Selenium Hybrid Framework Execution");
			 
	         extent = new ExtentReports();
	         extent.attachReporter(spark);
	         extent.setSystemInfo("Framework", "Hybrid Selenium Framework");
	         extent.setSystemInfo("Author", "Ali Hashmi");
	         extent.setSystemInfo("OS", System.getProperty("os.name"));
	         extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		 }
		 
		 return extent;
	 }
	 
	 private static void createReportDirectory() {	 
	     new File(FrameworkConstants.Extent_Report_Folder).mkdirs();
	     new File(FrameworkConstants.Screenshots_Folder).mkdirs();
	     new File(FrameworkConstants.Logs_Folder).mkdirs();
	    }

}
