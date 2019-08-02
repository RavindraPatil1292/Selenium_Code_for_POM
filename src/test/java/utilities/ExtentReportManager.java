package utilities;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports reports;
	
	public static ExtentReports getInstance() {
		//File file = new File(System.getProperty("user.dir")+"\\test-output\\html\\ExtentReport_"+CaptureScreenshot.curDate+"_"+CaptureScreenshot.curTime+".html");
		File file = new File(System.getProperty("user.dir")+"\\test-output\\html\\ExtentReport.html");
		
		if(htmlReporter==null) {
			htmlReporter = new ExtentHtmlReporter(file);
		}
		if(reports==null) {
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);
			
			//Setting configuration for html extent report file
			/*htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setEncoding("UTF-8");
			htmlReporter.config().setProtocol(Protocol.HTTPS);
			htmlReporter.config().setDocumentTitle("Automation Reports");
			htmlReporter.config().setReportName("Automation Report for Survey Setup");
			htmlReporter.config().setTimeStampFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
			*/
			htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ExtentReportsConfig.xml"), true);
		}
		return reports;
	}
	
}
