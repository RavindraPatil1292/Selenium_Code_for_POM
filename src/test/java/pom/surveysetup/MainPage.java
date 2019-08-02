package pom.surveysetup;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import pom.MaximoHome.MaximoLogin;
import utilities.BrowserLib;
import utilities.ExtentReportManager;
import utilities.ReadPropertyFile;

public class MainPage {

	// 1. WebDriver-Done
	public static WebDriver driver;

	// 2. Properties-Done
	public static String propertyFilePath = ".\\src\\test\\resources\\Test Data\\";

	// 3. Logs-Done
	public static Logger logger = Logger.getLogger("devpinoyLogger");

	// 4. ExtentReports-Done
	public ExtentReports reports = ExtentReportManager.getInstance();
	public static ExtentTest test;

	// 5. DB

	// 6. Excel-Done
	public static String testDataFolderPath = System.getProperty("user.dir")+ ".\\src\\test\\resources\\Test Data\\<epicName>";

	// Mail

	// jenkins

	// 9. POM objects
	MaximoLogin objMaximoLogin;

	public MainPage() {

		ReadPropertyFile urlPropertyFile = new ReadPropertyFile(propertyFilePath + "URL.properties");
		if (driver == null) {
			Reporter.log("Driver is null. Hence creating driver instance");
			driver = BrowserLib.setDriver(urlPropertyFile.getProp("browser"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.navigate().to(urlPropertyFile.getProp("INT_Maximo_url"));
			logger.info("Navigated to URL");
		}
	}

	public void click(String locator) {
		driver.findElement(By.xpath(locator));
		test.info("Clicked on-" + locator);
	}

	public void type(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value);
		test.info("Value entered in-" + locator + "-Value=" + value);
	}
	
	public static void quit() {
		driver.close();
		driver.quit();
		logger.info("Closing browser");
		Reporter.log("All browsers closed!!!");
	}

}
