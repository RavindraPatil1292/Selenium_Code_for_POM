package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelUtils {
	private static String fileSeperator = System.getProperty("file.separator");
	private static String testDataFolderPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\Test Data\\<epicName>";
	private static Workbook oWb = null;
	private static Sheet sheet = null;
	private static Cell cell = null;

	public static void setExcelFile(String excelPath, String excelName) throws IOException {
		try {
			String fileFormat = excelName.substring(excelName.indexOf("."));
			FileInputStream fisExl = new FileInputStream(excelPath + "\\" + excelName);
			if (fileFormat.equalsIgnoreCase(".xlsx")) {
				oWb = new XSSFWorkbook(fisExl);
			} else if (fileFormat.equalsIgnoreCase(".xls")) {
				oWb = new HSSFWorkbook(fisExl);
			}

		} catch (FileNotFoundException e) {
			throw (e);
		}

	}

	public static String getCellData(String sheetName, int row, int col) throws Exception {
		try {
			sheet = oWb.getSheet(sheetName);
			String cellValue = sheet.getRow(row).getCell(col).getStringCellValue();
			return cellValue;
		} catch (Exception e) {
			return "";
		}
	}

	public static void setCellData(int row, int col, String value, String filePath, String sheetName) {
		sheet = oWb.getSheet(sheetName);
		cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value);
		try {
			FileOutputStream fosExl = new FileOutputStream(filePath);
			oWb.write(fosExl);
			fosExl.flush();
			fosExl.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int getRowCount(String sheetName) {
		int rowCount = oWb.getSheet(sheetName).getLastRowNum();
		return rowCount + 1;
	}

	public static int getColumnCount(String sheetName) {
		return oWb.getSheet(sheetName).getRow(0).getLastCellNum();

	}

//As of now not used
	public Map<String, String> readPropertiesFile1(String propertyFilePath) {
		Map<String, String> prop = new HashMap<String, String>();
		try {
			String propFile = propertyFilePath;
			File file = new File(propFile);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			for (String key : properties.stringPropertyNames()) {
				prop.put(key, properties.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			// logger.error("Properties file not found at location-" + propertyFilePath + ".
			// " + e);
		} catch (IOException e) {
			// logger.error("Unable to read properties file. " + e);
		}
		return prop;

	}

	public Map<String, Object> getTestData(String epicName, String excelName, String dataSheet, int rownum)
			throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		testDataFolderPath = getTestDataFolderPath(epicName);
		List<String> columnNames = getColumnNames(epicName, excelName, dataSheet);
		try {
			File file = new File(testDataFolderPath + fileSeperator + excelName);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(dataSheet);
			// int rowCount =sheet.getLastRowNum()-sheet.getFirstRowNum();
			// for(int rownum=0;rownum<rowCount;rownum++)
			// {
			Row row = sheet.getRow(rownum);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				@SuppressWarnings("deprecation")
				CellType cell = row.getCell(j).getCellTypeEnum();
				switch (cell) {
				case STRING:
					data.put(columnNames.get(j), row.getCell(j).getStringCellValue());
					break;
				case NUMERIC:
					data.put(columnNames.get(j), row.getCell(j).getNumericCellValue());
					break;
				case BOOLEAN:
					data.put(columnNames.get(j), row.getCell(j).getBooleanCellValue());
					break;
				default:
				}
			}

			// }
			workbook.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static List<String> getColumnNames(String epicName, String excelName, String dataSheet) {
		testDataFolderPath = getTestDataFolderPath(epicName);
		List<String> columnNames = new ArrayList<String>();
		try {
			File file = new File(testDataFolderPath + fileSeperator + excelName);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(dataSheet);
			Row row = sheet.getRow(0);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				columnNames.add(row.getCell(j).getStringCellValue());
			}
			workbook.close();
			fis.close();
		} catch (FileNotFoundException e) {
			Reporter.log("Exception occured in getColumnNames- Test data File not found" + e.getMessage());
		} catch (IOException e) {
			Reporter.log("Exception occured in getColumnNames - Exception while reading sheet" + e.getMessage());
		}
		return columnNames;

	}

	public static String[][] readExecutionManager(String epicName, String testDataFile, String inputDataSheet,
			int totalNoOfCols) {
		String[][] arrayExcelData = null;
		testDataFolderPath = getTestDataFolderPath(epicName);
		System.out.println("Test Data Path is " + testDataFolderPath);
		System.out.println("Test Data Path is " + testDataFolderPath + fileSeperator + testDataFile);
		try {
			// String fileFormat =testDataFile.substring(testDataFile.indexOf("."));
			File file = new File(testDataFolderPath + fileSeperator + testDataFile);
			FileInputStream inputStream = new FileInputStream(file);
			System.out.println("Input stream created");
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			System.out.println("workbook object");
			XSSFSheet sheet = workbook.getSheet(inputDataSheet);
			System.out.println("sheet object");
			int totalNoOfRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
			System.out.println("No of rows in " + inputDataSheet + " is " + totalNoOfRows);
			// Ankush: total rows increased 4 to 5 to get vessel type at index 3 and role at
			// 4
			arrayExcelData = new String[totalNoOfRows][totalNoOfCols - 1];

			for (int i = 1; i < totalNoOfRows + 1; i++) {
				Row row = sheet.getRow(i);
				System.out.println("Row no- " + i + "Data- " + row.getCell(1).getStringCellValue());
				if ("Yes".equalsIgnoreCase(row.getCell(1).getStringCellValue())) {
					for (int j = 0; j < totalNoOfCols; j++) {
						if (j != 1) // not taking vlaue of Execute column
						{
							if (j >= 1) {
								arrayExcelData[i - 1][j - 1] = row.getCell(j).getStringCellValue();
							} else {
								arrayExcelData[i - 1][j] = row.getCell(j).getStringCellValue();
							}
						}
					}
				}
			}
			arrayExcelData = Arrays.stream(arrayExcelData).filter(s -> (s[0] != null && s.length > 0))
					.toArray(String[][]::new);

			workbook.close();
			inputStream.close();

		} catch (FileNotFoundException e) {
			// logger.error("Test Data excel file not found" +e);
		} catch (Exception e) {
			// logger.error("Workbook/Worksheet not found in data excel file" +e);
		}

		return arrayExcelData;
	}

	public static String getTestDataFolderPath(String epicName) {
		System.out.println(testDataFolderPath.replace("<epicName>", epicName));
		return testDataFolderPath.replace("<epicName>", epicName);

	}

	public static void waitForElementToBevisible(String xpathExpression) {
		// WebElement DynamicElement = (new
		// WebDriverWait(driver,60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));

	}

	public static boolean isRunnable(String testName) throws Exception {
		//String excelPath = ExcelUtils.getTestDataFolderPath("Survey Setup");
		//ExcelUtils.setExcelFile(excelPath, "service.xlsx");
		String sheetName= "TestSuite";
		int rows = ExcelUtils.getRowCount(sheetName);
		for(int i=1;i<=rows;i++) {
			String tcName = ExcelUtils.getCellData(sheetName, i, 0);
			if(tcName.equalsIgnoreCase(testName)) {
				String runMode = ExcelUtils.getCellData(sheetName, i, 1);
				if(runMode.equalsIgnoreCase("Y")) 
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
