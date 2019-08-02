package pom.surveysetup;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SurveySetupHome extends MainPage{
	//WebDriver driver;
	@FindBy(id="m6a7dfd2f_tfrow_[C:4]_txt-tb") WebElement vesselName;
	@FindBy(id="m6a7dfd2f_tfrow_[C:2]_txt-tb") WebElement classNum;
	@FindBy(xpath="//img[@id='m6a7dfd2f-ti2_img']") WebElement searchIcon;
	//@FindBy(xpath="//table[@id='m6a7dfd2f_tbod-tbd']/tbody/tr/td[@class='tc cd']/span[contains(text(),'03112312')]") WebElement searchResult;
	@FindBy(xpath="//a[@title='Survey Setup']") WebElement SSTab;
	//@FindBy(xpath="//table[@id='me791671c_tbod-tbd']/tbody/tr/td[contains(text(),'"+serviceType+"'])") WebElement selectService;

	
  public SurveySetupHome() {
	  //this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  
  public void searchVessel(String classnum) throws InterruptedException
  {
	  classNum.sendKeys(classnum);
	  searchIcon.click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//table[@id='m6a7dfd2f_tbod-tbd']/tbody/tr/td[@class='tc cd']/span[contains(text(),'"+classnum+"')]")).click(); 
	  Thread.sleep(3000);
	  SSTab.click();
	  Thread.sleep(3000);
  }
  
  public void selectService(String serviceType)
  {
	driver.findElement(By.xpath("//*[contains(text(),'"+serviceType+"')]")).click();
	
  }
}
