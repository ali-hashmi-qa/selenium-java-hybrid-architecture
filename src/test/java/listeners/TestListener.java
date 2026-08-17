package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	
	private final static ExtentReports extent = ExtentManager.getInstacnce();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = null;
		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
		Object[] params = result.getParameters();
		if (params.length == 4) {
			
		    String testCaseId = String.valueOf(params[0]);
		    String username = String.valueOf(params[1]);
		    String expectedResult = String.valueOf(params[3]);
		    test = extent.createTest(testCaseId + " - "
		                             + result.getMethod().getMethodName()+" ["+browser+"]"
		                             + "[" + username + " - " + expectedResult + "]");
		} else {
			test = extent.createTest(result.getMethod().getMethodName()+" ["+browser+"]");
		}
		
		test.assignCategory("Browser: " + browser);
		ExtentTestManager.setTest(test);		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test Passed Successfully....");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail(result.getThrowable());
		String screenshotPath = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
		ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
	}
	
	@Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
