package cdigitsTC_UI;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Activate;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Initial;
import pageObjects.cDigits_Login;
import pageObjects.cDigits_Menu;
import pageObjects.cDigits_SecurityCheck;
import pageObjects.cDigits_SecurityQuestions;

/*
 * 
 * 
 * 
 */
public class Login_Tests extends base {
	
	public static final Logger log = Logger.getLogger(Login_Tests.class);

	@BeforeTest
	public void performLogins() throws IOException, InterruptedException {
		
		int noParallelServers = excelFile.readNoOfParallelDevices();
		log.debug("   No of parallel devices to login    " + noParallelServers);
		for(int i=0; i<noParallelServers; i++) {
			initiateCapabilities(i);
			performValidLoginDevice(i);
		}
	}
	
	public void initiateCapabilities(int serveNo) throws IOException {
		if(serveNo==0) {
			log.debug("   Setting Capabilities of Device 1    ");
			setCapabilities("Device1");
			driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		if(serveNo==1) {
			log.debug("   Setting Capabilities of Device 2    ");
			setCapabilities("Device2");
			driver2.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		if(serveNo==2) {
			log.debug("   Setting Capabilities of Device 3    ");
			setCapabilities("Device3");
			driver3.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		if(serveNo==3) {
			log.debug("   Setting Capabilities of Device 4    ");
			setCapabilities("Device4");
			driver4.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	}
	
	public void performValidLoginDevice(int serverNo) throws IOException, InterruptedException {
		
		log.debug(" Login Activity Device " + serverNo + " Started ");
		
		cDigits_Initial initial =null;
		cDigits_Login login = null;
		cDigits_SecurityCheck securitycheck = null;
		cDigits_SecurityQuestions securityquestions = null;
		cDigits_Activate activate = null;
		cDigits_Home home = null;
		int height = 0; 
		int width = 0;
		TouchAction ta = null;
		String currentActivity = null;
		
		String phoneTypeVal = excelFile.readCell("Configuration", "Device"+(serverNo+1), excelFile.CONFIG_PHONETYPE_COL);
		String linesActivateVal = excelFile.readCell("Configuration", "Device"+(serverNo+1), excelFile.CONFIG_LINES_ACTIVATE_COL);
		
		if(serverNo==0) {
			initial = new cDigits_Initial(driver1);
			login = new cDigits_Login(driver1);
			securitycheck = new cDigits_SecurityCheck(driver1);
			securityquestions = new cDigits_SecurityQuestions(driver1);
			activate = new cDigits_Activate(driver1);
			home = new cDigits_Home(driver1);
			ta = new TouchAction(driver1);
			
			Dimension size1 = driver1.manage().window().getSize();
			height = size1.getHeight();
			width = size1.getWidth();
			
			currentActivity = driver1.currentActivity();
		}
		if(serverNo==1) {
			initial = new cDigits_Initial(driver2);
			login = new cDigits_Login(driver2);
			securitycheck = new cDigits_SecurityCheck(driver2);
			securityquestions = new cDigits_SecurityQuestions(driver2);
			activate = new cDigits_Activate(driver2);
			home = new cDigits_Home(driver2);
			ta = new TouchAction(driver2);
			
			Dimension size2 = driver2.manage().window().getSize();
			height = size2.getHeight();
			width = size2.getWidth();
			
			currentActivity = driver2.currentActivity();
		}
		if(serverNo==2) {
			initial = new cDigits_Initial(driver3);
			login = new cDigits_Login(driver3);
			securitycheck = new cDigits_SecurityCheck(driver3);
			securityquestions = new cDigits_SecurityQuestions(driver3);
			activate = new cDigits_Activate(driver3);
			home = new cDigits_Home(driver3);
			ta = new TouchAction(driver3);
			
			Dimension size3 = driver3.manage().window().getSize();
			height = size3.getHeight();
			width = size3.getWidth();
			
			currentActivity = driver3.currentActivity();
		}
		if(serverNo==3) {
			initial = new cDigits_Initial(driver4);
			login = new cDigits_Login(driver4);
			securitycheck = new cDigits_SecurityCheck(driver4);
			securityquestions = new cDigits_SecurityQuestions(driver4);
			activate = new cDigits_Activate(driver4);
			home = new cDigits_Home(driver4);
			ta = new TouchAction(driver4);
			
			Dimension size4 = driver4.manage().window().getSize();
			height = size4.getHeight();
			width = size4.getWidth();
			
			currentActivity = driver4.currentActivity();
		}
		
		if(currentActivity.equalsIgnoreCase("com.mavenir.ucc.ui.activity.DashBoardActivity")) {
			
			//Need to do nothing
		}
		else if(currentActivity.equalsIgnoreCase("com.mavenir.ucc.esflow.ESSetupActivity")) {
			
			if(serverNo==0) {
				wait1.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==1) {
				wait2.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==2) {
				wait3.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==3) {
				wait4.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			
			
			String[] activateList = linesActivateVal.split(",");
			for(int i=0; i<activateList.length; i++)
			{
				int indexVal = Integer.parseInt(activateList[i]);
				activate.namesList.get(indexVal).click();
				Thread.sleep(1000);
			}
			
			activate.activateButton.click();
			
		}
		else {
			
			if(currentActivity.equalsIgnoreCase("com.tmobile.tmoid.sdk.impl.inbound.nal.NalActivity")) {
				
				try {
					login.notMe.click();
				}
				catch (Exception e) {
					// do nothing if the element is not found
				}
			}
			else {
				initial.walkthroughContinueButton.click();
				initial.walkthroughSkipButton.click();
				initial.acceptButton.click();
				initial.walkthroughNextButton.click();
				
				for(int i=0; i<6; i++) {
					initial.walkthroughAllowButton.click();
					Thread.sleep(1000);
				}
				
				if(serverNo==0) {
					wait1.until(ExpectedConditions.visibilityOf(login.signInButton));
				}
				if(serverNo==1) {
					wait2.until(ExpectedConditions.visibilityOf(login.signInButton));
				}
				if(serverNo==2) {
					wait3.until(ExpectedConditions.visibilityOf(login.signInButton));
				}
				if(serverNo==3) {
					wait4.until(ExpectedConditions.visibilityOf(login.signInButton));
				}
			}
			
			//Thread.sleep(20000);
			String loginEmailVal = excelFile.readCell("Configuration", "Device"+(serverNo+1), excelFile.CONFIG_LOGIN_EMAIL_COL);
			String passwordVal = excelFile.readCell("Configuration", "Device"+(serverNo+1), excelFile.CONFIG_PASSWORD_COL);
			String secAnswerVal = excelFile.readCell("Configuration", "Device"+(serverNo+1), excelFile.CONFIG_SECURITY_ANS_COL);
			
			
			login.loginEmail.sendKeys(loginEmailVal);
			login.password.sendKeys(passwordVal);
					
			login.signInButton.click();
			log.debug(" login and password entered ");
			
			//Thread.sleep(50000);
			if(serverNo==0) {
				wait1.until(ExpectedConditions.visibilityOf(securitycheck.continueButton));
			}
			if(serverNo==1) {
				wait2.until(ExpectedConditions.visibilityOf(securitycheck.continueButton));
			}
			if(serverNo==2) {
				wait3.until(ExpectedConditions.visibilityOf(securitycheck.continueButton));
			}
			if(serverNo==3) {
				wait4.until(ExpectedConditions.visibilityOf(securitycheck.continueButton));
			}
			securitycheck.continueButton.click();
			
			Thread.sleep(5000);
			
			//get height and width of device
			
			Utilities util = new Utilities();
			if(! phoneTypeVal.equalsIgnoreCase("Tablet")) {
				util.ScrollBottomToTop(ta,height,width);
				Thread.sleep(5000);
			}
			
			TapOnFirstSecurityAnswer(phoneTypeVal, ta);
			Thread.sleep(1000);
			
			util.typeSecAnswerKeyboard(phoneTypeVal,secAnswerVal,ta);
					
			Thread.sleep(2000);
			
			TapOnSecondSecurityAnswer(phoneTypeVal, ta);
			Thread.sleep(1000);
			
			util.typeSecAnswerKeyboard(phoneTypeVal,secAnswerVal,ta);
			Thread.sleep(2000);
			
			if(serverNo==0) {
				driver1.hideKeyboard();
			}
			if(serverNo==1) {
				driver2.hideKeyboard();
			}
			if(serverNo==2) {
				driver3.hideKeyboard();
			}
			if(serverNo==3) {
				driver4.hideKeyboard();
			}
			
			
			log.debug(" security answers entered ");
			securityquestions.continueButton.click();
				
			
			//Thread.sleep(50000);
			if(serverNo==0) {
				wait1.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==1) {
				wait2.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==2) {
				wait3.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			if(serverNo==3) {
				wait4.until(ExpectedConditions.visibilityOf(activate.activateButton));
			}
			
			
			String[] activateList = linesActivateVal.split(",");
			for(int i=0; i<activateList.length; i++)
			{
				int indexVal = Integer.parseInt(activateList[i]);
				activate.namesList.get(indexVal).click();
				Thread.sleep(1000);
			}
			
			activate.activateButton.click();
		}
		
		
		if(serverNo==0) {
			wait1.until(ExpectedConditions.visibilityOf(home.contacts));
		}
		if(serverNo==1) {
			wait2.until(ExpectedConditions.visibilityOf(home.contacts));
		}
		if(serverNo==2) {
			wait3.until(ExpectedConditions.visibilityOf(home.contacts));
		}
		if(serverNo==3) {
			wait4.until(ExpectedConditions.visibilityOf(home.contacts));
		}
		
		log.debug(" lines activation done ");
		
		Thread.sleep(10000);
		log.debug(" Login Activity Device Completed ");
		
		
		
	}
	
	public void TapOnFirstSecurityAnswer(String phoneType, TouchAction ta) {
		
		if(phoneType.equalsIgnoreCase("G5")) {
			ta.tap(PointOption.point(400,400)).perform();
		}
		if(phoneType.equalsIgnoreCase("E5")) {
			ta.tap(PointOption.point(400,400)).perform();
		}
		if(phoneType.equalsIgnoreCase("Tablet")) {
			ta.tap(PointOption.point(100,410)).perform();
		}
	}
	
	public void TapOnSecondSecurityAnswer(String phoneType, TouchAction ta) {
		
		if(phoneType.equalsIgnoreCase("G5")) {
			ta.tap(PointOption.point(400,1000)).perform();
		}
		if(phoneType.equalsIgnoreCase("E5")) {
			ta.tap(PointOption.point(400,800)).perform();
		}
		if(phoneType.equalsIgnoreCase("Tablet")) {
			ta.tap(PointOption.point(100,560)).perform();
		}
	}

	@AfterTest
	public void afterTestCompleted() throws InterruptedException {
		
		log.debug(" AfterTest Called ");
		
		int noParallelServers = excelFile.readNoOfParallelDevices();
		for(int i=0; i<noParallelServers; i++) {
			if(i==0) {
				driver1.close();
			}
			if(i==1) {
				driver2.close();
			}
			if(i==2) {
				driver3.close();
			}
			if(i==3) {
				driver4.close();
			}
		}
	}
}
