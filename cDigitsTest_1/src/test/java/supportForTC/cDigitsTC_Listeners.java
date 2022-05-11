package supportForTC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commonClasses.base;
import io.appium.java_client.AppiumDriver;

public class cDigitsTC_Listeners extends base implements ITestListener {

	public static final Logger log = Logger.getLogger(cDigitsTC_Listeners.class);
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestStart");
		String funcName = result.getMethod().getMethodName();
		System.out.println("onTestStart : " + funcName);
		String[] funcNameParams = funcName.split("_");
		
		//write the adb log file
		if(noOfDevicesInParallel==1) {
			driver1.manage().logs().get("logcat");
		}
		
		if(noOfDevicesInParallel==2) {
			driver1.manage().logs().get("logcat");
			driver2.manage().logs().get("logcat");
		}	
		
		
		
		try {
			base.excelFile.writeCell(funcNameParams[0], funcNameParams[1], base.excelFile.TEST_PERFORMED_COL, "Started");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		try {
			// TODO Auto-generated method stub
			String funcName = result.getMethod().getMethodName();
			
			//write the adb log file
			if(noOfDevicesInParallel==1) {
				captureLog(driver1, funcName, "PASS");
			}
			
			if(noOfDevicesInParallel==2) {
				captureLog(driver1, funcName, "PASS");
				captureLog(driver2, funcName, "PASS");
			}	
			
			System.out.println("onTestSuccess : " + funcName);
			String[] funcNameParams = funcName.split("_");
			base.excelFile.writeCell(funcNameParams[0], funcNameParams[1], base.excelFile.RESULT_COL, "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		try {
			// TODO Auto-generated method stub
			String funcName = result.getMethod().getMethodName();
			System.out.println("onTestFailure : " + funcName);
			String[] funcNameParams = funcName.split("_");
			
			//write the adb log file
			if(noOfDevicesInParallel==1) {
				captureLog(driver1, funcName, "FAIL");
			}
			
			if(noOfDevicesInParallel==2) {
				captureLog(driver1, funcName, "FAIL");
				captureLog(driver2, funcName, "FAIL");
			}	
			
			base.excelFile.writeCell(funcNameParams[0], funcNameParams[1], base.excelFile.RESULT_COL, "FAIL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void captureLog(AppiumDriver driver, String testName, String result) throws FileNotFoundException {
		DateFormat df = new SimpleDateFormat("yyyyddMM_HHmmss");
	    Date today = Calendar.getInstance().getTime();
	    String reportDate = df.format(today);
	    String logPath = "C:\\cDigits_Tests\\ADB_Logs\\";
	    logPath = logPath + result + "\\";
	    log.info(driver.getSessionId() + ": Saving device log...");
	    List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
	    File logFile = new File(logPath + reportDate + "_" + testName + ".txt");
	    PrintWriter log_file_writer = new PrintWriter(logFile);
	    for(int i=0; i<logEntries.size(); i++) {
	    	log_file_writer.println(logEntries.get(i));
	    }
	    log_file_writer.flush();
	    log.info(driver.getSessionId() + ": Saving device log - Done.");
	}
}
