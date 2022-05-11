package commonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelData {
	
	public static String TestOutputFile;
	
	public static final int CONFIG_DEVICE_NAME_COL = 1;
	public static final int CONFIG_PORT_NO_COL = 2;
	public static final int CONFIG_APP_NAME_COL = 3;
	public static final int CONFIG_APP_ACTIVITY_COL = 4;
	public static final int CONFIG_LOGIN_EMAIL_COL = 5;
	public static final int CONFIG_PASSWORD_COL = 6;
	public static final int CONFIG_SECURITY_ANS_COL = 7;
	public static final int CONFIG_PHONETYPE_COL = 8;
	public static final int CONFIG_LINES_ACTIVATE_COL = 9;
	
	public static final int TEST_ID_COL = 0;
	public static final int TEST_PERFORMED_COL = 1;
	public static final int INPUT1_COL = 2;
	public static final int INPUT2_COL = 3;
	public static final int INPUT3_COL = 4;
	public static final int INPUT4_COL = 5;
	public static final int INPUT5_COL = 6;
	public static final int INPUT6_COL = 7;
	public static final int RESULT_COL = 8;
			

	public void createNewFileForOutput() throws FileNotFoundException {
		
		System.out.println("createNewFileForOutput Called");
		//Source File
		File srcFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\cDigits_TestData.xlsx");
		
		//get current time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		
		String strDateTime = dtf.format(now);
		File destFile = new File("C:\\cDigits_Tests\\cDigits_TestData_" + strDateTime + ".xlsx");
		
		TestOutputFile = "cDigits_TestData_" + strDateTime + ".xlsx";  //return the filename of new file create
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(TestOutputFile + " File created");
	}
	
	public void writeCell(String worksheetName, String getRowSearch, int colNo, String inputString) {
		System.out.println("writeCell : " + worksheetName + " row: " + getRowSearch);
		try {
			
			FileInputStream fis = new FileInputStream("C:\\cDigits_Tests\\" + TestOutputFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(worksheetName);
			
			int rowVal = findRow(sheet, getRowSearch);
			System.out.println("rowVal : " + rowVal + " colNo: " + colNo + " update: " + inputString);
			//write the content
			sheet.getRow(rowVal).getCell(colNo).setCellValue(inputString);
				
			FileOutputStream outFile =new FileOutputStream(new File("C:\\cDigits_Tests\\" + TestOutputFile));
			workbook.write(outFile);
			outFile.close();
			
			workbook.close();
			fis.close();
			

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			
		}
	}

	public static int findRow(XSSFSheet sheet, String cellContent) {
		int rowNum = 0;
		System.out.println("findRow : " + cellContent);
		
		for(Row row:sheet) {
			if(row.getCell(0) != null) {
				String actContent = row.getCell(0).toString();
				if(actContent.equalsIgnoreCase(cellContent)) {
					rowNum = row.getRowNum();
					System.out.println("found at row  : " + rowNum);
					return rowNum;
				}
			}
		}
		return rowNum;
	}
	
	public String readCell(String worksheetName, String getRowSearch, int colNo) {
		System.out.println("readCell called " + getRowSearch +  " " + TestOutputFile);
		try {
		File file = new File("C:\\cDigits_Tests\\" + TestOutputFile);

		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(worksheetName);
		
		int rowVal = findRow(sheet, getRowSearch);

		String value = sheet.getRow(rowVal).getCell(colNo).getStringCellValue();
		System.out.println("value returned " + rowVal + " " + value);
		return value;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			return "error";
		}

	}
	
	public int readNoOfParallelDevices() {
		System.out.println("readNoOfParallelDevices called ");
		try {
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\cDigits_TestData.xlsx");

		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Configuration");
		
		String value = String.valueOf(sheet.getRow(1).getCell(1).getNumericCellValue());
		int valueInt = (int) Double.parseDouble(value);
		System.out.println("value returned " + valueInt );
		return valueInt;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception " + e.getMessage());
			return 0;
		}

	}
}
