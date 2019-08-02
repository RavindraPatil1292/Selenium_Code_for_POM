package pom.MaximoHome;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.surveysetup.MainPage;
import utilities.ExcelUtils;

public class MaximoLogin extends MainPage {
	// WebDriver driver;
	// private static String fileSeperator = System.getProperty("file.seperator");
	@FindBy(id = "username")
	WebElement username;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[@id='loginbutton']")
	WebElement loginButton;

	public MaximoLogin() {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginToMaximo(String loginSheet, String excelName) {

		try {
			ExcelUtils.setExcelFile(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Test Data\\Survey Setup\\", excelName);
			int rows = ExcelUtils.getRowCount(loginSheet);
			System.out.println("Rows:" + rows);
			for (int i = 1; i <= rows; i++) {
				String execute = ExcelUtils.getCellData(loginSheet, i, 1);
				if ("Yes".equalsIgnoreCase(execute)) {
					String uname = ExcelUtils.getCellData(loginSheet, i, 2);
					String pword = ExcelUtils.getCellData(loginSheet, i, 3);
					username.sendKeys(uname);
					password.sendKeys(pword);
					loginButton.click();
					System.out.println("Login method successfull");

					break;

				}

			}

		} catch (IOException e) {
			System.out.println("setExcelFile Failed. " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Failed to fetch data from excel. " + e);
			e.printStackTrace();
		}
	}

	public String getLoginWindowTitle() {
		return driver.getTitle();
	}

}