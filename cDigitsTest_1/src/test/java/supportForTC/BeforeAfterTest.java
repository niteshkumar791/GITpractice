package supportForTC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commonClasses.base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class BeforeAfterTest extends base {
	
	public static final Logger log = Logger.getLogger(BeforeAfterTest.class);
	
	@BeforeTest
	public void runBeforeTest(final ITestContext testContext) throws IOException, InterruptedException {
		log.info(" ************** START NEW TEST ****************   " + testContext.getName());
		
		excelFile.createNewFileForOutput();
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void runAfterTest(final ITestContext testContext) throws IOException, InterruptedException {
		log.info(" ************** END OF TEST ****************   " + testContext.getName());
		
			
				
	}
	
	public void runBeforeMethod(String noOfDevices, String deviceNameList, String UDIDList, String phoneTypeList, String networkList) throws IOException, InterruptedException {
		log.info(" In runBeforeMethod   ");
		
		System.out.println("Killing the appium nodes");
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
				
		int noParallelServers = Integer.parseInt(noOfDevices);
		noOfDevicesInParallel = noParallelServers;
		log.info(" noOfDevicesInParallel  " + noOfDevicesInParallel);
		startServers(noParallelServers);
		
		String[] deviceNames = deviceNameList.split(",");
		String[] UDIDs = UDIDList.split(",");
		String[] phoneTypes = phoneTypeList.split(",");
		String[] networks = networkList.split(",");
		
		for( int i=0; i<noParallelServers; i++)
		{
			setCapabilitiesOfDevice(i,deviceNames[i],UDIDs[i],phoneTypes[i],networks[i]);
			/*if(i==0)
			{
				startDigitsApplication(driver1, "DIGITS");
			}
			if(i==1)
			{
				startDigitsApplication(driver2, "DIGITS");
			}*/
		}
		
		
	}
	
	public void runAfterMethod(String noOfDevices, String deviceNameList, String UDIDList, String phoneTypeList, String networkList, String testName) throws IOException, InterruptedException {
		log.info(" In runAfterMethod   " + noOfDevicesInParallel);
		
		if(noOfDevicesInParallel==1) {
			driver1.quit();
			service1.stop();
		}
		
		if(noOfDevicesInParallel==2) {
			driver1.quit();
			driver2.quit();
			service1.stop();
			service2.stop();
		}	
				
	}
	
	
	
	public void setCapabilitiesOfDevice(int NoDevice, String deviceName, String UDID, String phoneType, String network) throws IOException {
		log.info(" Inside setCapabilitiesOfDevice  "+ deviceName + " UDID: " + UDID);
		setCapabilitiesNew(NoDevice, deviceName, UDID, phoneType, network);
	}

	public boolean startDigitsApplication(AndroidDriver<AndroidElement> driver, String application) {
		log.info(" Inside startDigitsApplication  "); 
		boolean result=false;
		String command = "mobile:application:open";
		Map<String, Object> params = new HashMap<>();
		params.put("name", application);
		String openApp = "false";
		try {
			
			openApp = (String) driver.executeScript(command, params);
		}
		catch (Exception e) {
			
		}
		
		if(openApp.equalsIgnoreCase("false")) {
			log.info(" Failed to start application  "); 
			result = false;
		}
		else {
			log.info(" Application started success "); 
			result = true;
		}
		return result;
		
	}
}
