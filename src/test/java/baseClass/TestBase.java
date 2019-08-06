package baseClass;


import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pom.MaximoHome.MaximoLogin;
import pom.surveysetup.MainPage;
import utilities.CaptureScreenshot;


/*
 In base class we will initialize:
 1. WebDriver-Done
 2. Properties-Done
 3. Logs-Done
 4. ExtentReports
 5. DB
 6. Excel-Done
 7. Mail 
 8. Jenkins
 */
public class TestBase extends MainPage{
//Testing GIT CI
	@BeforeSuite
	public void setUp(){
		
	}

	@AfterSuite
	public void tearDown() {
		MainPage.quit();
	}

	@BeforeTest
	public void login() throws InterruptedException {
		try {
			String excelName = "service.xlsx";
			String loginSheet = "Login";
			logger.info("In test login");
			MaximoLogin objMaximoLogin = new MaximoLogin();
			objMaximoLogin.loginToMaximo(loginSheet, excelName);
			CaptureScreenshot.captureScreenshot(driver, "Login");
		} catch (Exception e) {
			logger.info("Login to maximo failed");
		}
	}

	@AfterTest
	public void logout() {
		driver.findElement(By.xpath("//img[@alt='Sign Out ALT+S']")).click();
		//Unless you flush the reports, no extent report will be generated
		reports.flush();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	
	public void click(String locator) {
		driver.findElement(By.xpath(locator));
		test.info("Clicked on-"+locator);
	}
	
	public void type(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value);
		test.info("Value entered in-"+locator+"-Value="+value);
	}
	

}
