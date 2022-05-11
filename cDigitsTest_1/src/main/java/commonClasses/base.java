package commonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class base {

	public static AppiumDriverLocalService service1;
	public static AndroidDriver<AndroidElement>  driver1;
	public static WebDriverWait wait1;
	
	
	public static AppiumDriverLocalService service2;
	public static AndroidDriver<AndroidElement>  driver2;
	public static WebDriverWait wait2;
	
	public static AppiumDriverLocalService service3;
	public static AndroidDriver<AndroidElement>  driver3;
	public static WebDriverWait wait3;
	
	public static AppiumDriverLocalService service4;
	public static AndroidDriver<AndroidElement>  driver4;
	public static WebDriverWait wait4;
	
	private AppiumServiceBuilder builder;
	
	public static int noOfDevicesInParallel;
	
	public static  ReadWriteExcelData excelFile= new ReadWriteExcelData();
	
	
	public void startServers(int noServers)
	{
		//
		for(int i = 0; i < noServers; i++) {
			int port = 4723 + i*2;
			boolean flag=	checkIfServerIsRunnning(port);
			if(!flag)
			{
				System.out.println("Starting the Appium server " + port);
				startServer(i+1, port);
	
			}
		}
	}
	
	private void startServer(int ServerNo, int port) {
		
		//get current time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		
		String strDateTime = dtf.format(now);
		File logFile = new File("C:\\cDigits_Tests\\AppiumLogs\\cDigits_Appium_" + ServerNo + "_" + strDateTime + ".txt");
		
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		builder.withLogFile(logFile);
		
		if(ServerNo==1) {
			service1 = AppiumDriverLocalService.buildService(builder);
			service1.start();
		}
		
		if(ServerNo==2) {
			service2 = AppiumDriverLocalService.buildService(builder);
			service2.start();
		}
		
		if(ServerNo==3) {
			service3 = AppiumDriverLocalService.buildService(builder);
			service3.start();
		}
		
		if(ServerNo==4) {
			service4 = AppiumDriverLocalService.buildService(builder);
			service4.start();
		}
		
		
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	
	public static  void setCapabilities(String device) throws IOException
	{
   		DesiredCapabilities capabilities = new DesiredCapabilities();
   				
		String deviceName = excelFile.readCell("Configuration", device, excelFile.CONFIG_DEVICE_NAME_COL);
		String appname=excelFile.readCell("Configuration", device, excelFile.CONFIG_APP_NAME_COL);
		String appactivity=excelFile.readCell("Configuration", device, excelFile.CONFIG_APP_ACTIVITY_COL);
   				
		capabilities.setCapability(MobileCapabilityType.UDID, deviceName);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,600);
		
		capabilities.setCapability("appPackage",appname);
		capabilities.setCapability("appActivity", appactivity);
		capabilities.setCapability(MobileCapabilityType.NO_RESET,"true");

		if(device.equalsIgnoreCase("Device1")) {
			driver1 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wait1 = new WebDriverWait(driver1, 120);
		}
		
		if(device.equalsIgnoreCase("Device2")) {
			driver2 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
			wait2 = new WebDriverWait(driver2, 120);
		}
		
		if(device.equalsIgnoreCase("Device3")) {
			driver3 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4727/wd/hub"), capabilities);
			wait3 = new WebDriverWait(driver3, 120);
		}
		
		if(device.equalsIgnoreCase("Device4")) {
			driver4 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), capabilities);
			wait4 = new WebDriverWait(driver4, 120);
		}
	}

	
	public static  void setCapabilitiesNew(int NoDevice, String deviceName, String UDID, String phoneType, String network) throws IOException
	{
   		DesiredCapabilities capabilities = new DesiredCapabilities();
   				
		String appname=excelFile.readCell("Configuration", deviceName, excelFile.CONFIG_APP_NAME_COL);
		String appactivity=excelFile.readCell("Configuration", deviceName, excelFile.CONFIG_APP_ACTIVITY_COL);
   				
		capabilities.setCapability(MobileCapabilityType.UDID, UDID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, UDID);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,600);
		
		capabilities.setCapability("appPackage",appname);
		capabilities.setCapability("appActivity", appactivity);
		capabilities.setCapability(MobileCapabilityType.NO_RESET,"true");

		if(NoDevice==0) {
			driver1 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wait1 = new WebDriverWait(driver1, 120);
		}
		
		if(NoDevice==1) {
			driver2 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
			wait2 = new WebDriverWait(driver2, 120);
		}
		
		if(NoDevice==2) {
			driver3 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4727/wd/hub"), capabilities);
			wait3 = new WebDriverWait(driver3, 120);
		}
		
		if(NoDevice==3) {
			driver4 = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4729/wd/hub"), capabilities);
			wait4 = new WebDriverWait(driver4, 120);
		}
	}


}
