package pom.MaximoHome;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.surveysetup.MainPage;
import pom.surveysetup.SurveySetupHome;

public class MaximoHome extends MainPage{
	//WebDriver driver;
	@FindBy(xpath="//*[@id='titlebar-tb_gotoButton']") WebElement home;
	@FindBy(xpath="//span[@id='menu0_ASSET_MODULE_a_tnode']") WebElement assets;
	@FindBy(xpath="//*[@id='menu0_ASSET_MODULE_sub_changeapp_ABSSURVEYSETUP_a']") WebElement SSVessel;
	@FindBy(xpath="//*[@id='menu0_PLANS_MODULE_sub_changeapp_ABSSERAGREEMENT_a_tnode']") WebElement SAModule;
	
	public MaximoHome()
	{
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
  public SurveySetupHome navigateToSSVessel() throws Exception {
	  //CommonUtils.waitForElementToBeClickable(home);
	  Actions action = new Actions(driver);
	  Thread.sleep(3000);
	  action.moveToElement(home).click().perform();
	  //home.click();
	  assets.click();
	  SSVessel.click();
	return new SurveySetupHome();
  }
  
  public void navigateToSAModule() {
	  home.click();
	  assets.click();
	  SAModule.click();
  }
  
  public String getWindowTitle()
  {
	  return driver.getTitle();
  }
  
  public void performModuleNavigation() throws Exception
  {
	  this.navigateToSSVessel();
	  this.getWindowTitle();
  }
}
