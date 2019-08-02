package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pom.surveysetup.MainPage;
import utilities.CaptureScreenshot;

public class TestListener extends MainPage implements ITestListener{ 

	//WebDriver driver;
	//CaptureScreenshot objCaptureScreenshot = new CaptureScreenshot();
	final Logger logger = Logger.getLogger(TestListener.class);
	public static String dateStart;
	//private String screenshotFilePath;
	//private String screenshotFileName;
	String testName;
	
	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getName().toUpperCase().toString());
		Reporter.log("Test  Started--"+result.getName());
		logger.info("Started");
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		testName = result.getName().toString().toUpperCase();
		String screenshotFileName = "testpass_"+result.getName();
		CaptureScreenshot.captureScreenshot(driver, screenshotFileName);
		String screenshotFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+CaptureScreenshot.curDate+"\\"+CaptureScreenshot.curTime+"\\"+screenshotFileName+"_"+CaptureScreenshot.curTime1+".png";
		
		//For extent report
		test.log(Status.PASS, MarkupHelper.createLabel(testName+":-Passed", ExtentColor.GREEN));
		
		try {
			test.addScreenCaptureFromPath(screenshotFilePath);
		} catch (IOException e) {
			test.info("Exception-Screenshot not found at path specified.<Br>"+e);
		}
		//End for extent report
		
		//For ReportNG report
		System.setProperty("org.uncommons.reportng.escape-output", "false");//for attaching the screenshot to report
		Reporter.log("<Br>Test Passed- "+testName);
		Reporter.log("<br><a href=\""+System.getProperty("user.dir")+"\\Screenshots\\"+CaptureScreenshot.curDate+"\\"+CaptureScreenshot.curTime+"\\"+screenshotFileName+"_"+CaptureScreenshot.curTime1+".png\" target=\"_blank\">Screenshot Link</a>");
		//End for reportng report
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName().toString().toUpperCase();
		String screenshotFileName = "testFail_"+result.getName();
		CaptureScreenshot.captureScreenshot(driver, screenshotFileName);
		String screenshotFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+CaptureScreenshot.curDate+"\\"+CaptureScreenshot.curTime+"\\"+screenshotFileName+"_"+CaptureScreenshot.curTime1+".png";
		
		//For extent report
		test.log(Status.FAIL, MarkupHelper.createLabel(testName+":-Failed", ExtentColor.RED));
		test.log(Status.FAIL, result.getThrowable());
		try {
			test.addScreenCaptureFromPath(screenshotFilePath);
		} catch (IOException e) {
			test.info("Exception-Screenshot not found at path specified.<Br>"+e);
		}
		//End For extent report
		
		//For ReportNG report
		System.setProperty("org.uncommons.reportng.escape-output", "false");//for attaching the screenshot to report
		Reporter.log("<br>Test Failed---"+testName);
		Reporter.log("<br><a href=\""+System.getProperty("user.dir")+"\\Screenshots\\"+CaptureScreenshot.curDate+"\\"+CaptureScreenshot.curTime+"\\"+screenshotFileName+"_"+CaptureScreenshot.curTime1+".png\" target=\"_blank\">Screenshot Link</a>");
		//Reporter.log("<br><a href=\"D:\\Ravindra\\Automation_Selenium\\DataDrivenFramework\\Screenshots\\"+CaptureScreenshot.curDate+"\\"+CaptureScreenshot.curTime+"\\"+screenshotFileName+"_"+CaptureScreenshot.curTime1+".png\" target=\"_blank\">Screenshot Link</a>");
		//End for reportng report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName().toUpperCase().toString();
		test.log(Status.SKIP, MarkupHelper.createLabel("Skipped test:-"+testName+" as run mode is not set as \"Y\"", ExtentColor.BLUE));
		System.out.println("Test Skipped---"+result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed with success percent---"+result.getName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution Started---"+context.getStartDate());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution Ended---"+context.getEndDate());
		
	}

}
