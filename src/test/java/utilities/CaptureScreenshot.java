package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;

public class CaptureScreenshot {
	private static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat timeFormat = new SimpleDateFormat("hh-mm-ss a");
	public static String fileSeparator = System.getProperty("file.separator");
	public static File screenshotSubFolder;
	public static String curDate;
	public static String curTime;
	public static String curTime1;
	static {
		curDate = dateFormat.format(date);
		curTime = timeFormat.format(date);
		
		File screenshotFolder = new File("Screenshots" + fileSeparator + curDate);
		if (!screenshotFolder.exists())
			screenshotFolder.mkdirs();

		screenshotSubFolder = new File(screenshotFolder + fileSeparator + curTime);
		if (!screenshotSubFolder.exists())
			screenshotSubFolder.mkdirs();
	}

	public static void captureScreenshot(WebDriver driver,String img_name) {
		//Below date1, timeFormat2 and curTime1 are created to capture exact screenshot time
		Date date1 = new Date();
		DateFormat timeFormat2 = new SimpleDateFormat("hh-mm-ss a");
		curTime1 = timeFormat2.format(date1);
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(screenshotSubFolder + fileSeparator + img_name +"_"+ curTime1+".png");

			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//log.info("Exception while capturing screenshot");
		}
	}

}
