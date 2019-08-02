package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class BrowserLib {
	public static WebDriver driver;
	public static String driverPath = System.getProperty("user.dir") + ".\\src\\test\\resources\\Drivers\\";

	public static WebDriver setDriver(String browser) {
		if ("CHROME".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
			
			//code for disabling browser dialogues such as save password and location access
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");	//disable message--automation software dubbging browser
			options.addArguments("--disable-infobars");
			
			driver = new ChromeDriver(options);
		} else if ("FIREFOX".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if("Edge".equalsIgnoreCase(browser)){
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", driverPath+"MicrosodtWebDriver.exe");
			driver = new EdgeDriver();
		}
		else if("IE".equalsIgnoreCase(browser)){
			//WebDriverManager.iedriver().setup();
			//System.setProperty("webdriver.ie.driver", driverPath+"\\src\\test\\resources\\Drivers\\IEDriver.exe");
			driver = new InternetExplorerDriver();
		}
		else {
			Reporter.log("Browser does not found");
		}
		driver.manage().window().maximize();
		return driver;

	}

}
