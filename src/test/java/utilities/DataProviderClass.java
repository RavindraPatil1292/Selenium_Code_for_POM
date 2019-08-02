package utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import baseClass.TestBase;

public class DataProviderClass extends TestBase {
	public static String epicName = "Survey Setup";
	public static String excelName = "service.xlsx";
	public static String loginSheet = "Login";
	public static String masterSheet = "Master";
	public static String tcSheet;

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) throws Exception {
		//String testName = m.getName();
		tcSheet= "TC";
		String excelPath = ExcelUtils.getTestDataFolderPath(epicName);
		ExcelUtils.setExcelFile(excelPath, excelName);
		//int rows = CommonUtils.getRowCount(tcSheet);
		int rows=1;
		int cols = ExcelUtils.getColumnCount(tcSheet);
		// List<String> columnNames= CommonUtils.getColumnNames(epicName, excelName,
		// tcSheet);
		Object[][] data = new Object[rows][1];
		Hashtable<String, String> table = null;
		for (int i = 1; i <= rows; i++) {
			table = new Hashtable<String, String>();
			for (int j = 0; j < cols; j++) {
				table.put(ExcelUtils.getCellData(tcSheet, 0, j), ExcelUtils.getCellData(tcSheet, i, j));
				data[i - 1][0] = table;
			}
		}
		return data;
	}
}
