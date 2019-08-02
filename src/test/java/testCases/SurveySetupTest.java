package testCases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import baseClass.TestBase;
import pom.MaximoHome.MaximoHome;
import pom.surveysetup.SurveySetupHome;
import utilities.CaptureScreenshot;
import utilities.DataProviderClass;
import utilities.ExcelUtils;

public class SurveySetupTest extends TestBase{
	//MaximoHome objMaximoHome;
	//SurveySetupHome objSurveySetupHome;

  @Test(dataProvider = "dp", dataProviderClass = DataProviderClass.class)
  public void surveySetupTest(Hashtable<String, String> data) throws Exception {
	  if(!ExcelUtils.isRunnable("surveySetupTest")) {
		  throw new SkipException("Skipping the test surveySetupTest as run mode is not set to \"Y\"");
	  }
	  MaximoHome objMaximoHome= new MaximoHome();
	  SurveySetupHome objSurveySetupHome= objMaximoHome.navigateToSSVessel();
	  objSurveySetupHome.searchVessel(data.get("ClassNumber"));
	  Thread.sleep(3000);
	  objSurveySetupHome.selectService(data.get("Service"));
	  CaptureScreenshot.captureScreenshot(driver, "Service Selected");
	  Thread.sleep(3000);
  }
}
