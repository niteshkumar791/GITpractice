package cdigitsTC_UI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_DialPad;
import pageObjects.cDigits_Home;
import supportForTC.BeforeAfterTest;

/*
 * UI_43803_CheckDialPad
 * 
 */
public class DialPad_Tests extends base {

	public static final Logger log = Logger.getLogger(DialPad_Tests.class);

	@Test
	public void UI_43803_CheckDialPad() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		log.info("Test UI_43803_CheckDialPad - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls calls = new cDigits_Calls(driver1);
		//home.dialPad.click();
		home.calls.click();
		calls.dialPad.click();
		
		Thread.sleep(2000);
		log.info("Dialpad clicked");
		cDigits_DialPad dialPad = new cDigits_DialPad(driver1);
		dialPad.one.click();
		Thread.sleep(2000);
		dialPad.two.click();
		Thread.sleep(2000);
		dialPad.three.click();
		Thread.sleep(2000);
		dialPad.four.click();
		Thread.sleep(2000);
		dialPad.five.click();
		Thread.sleep(2000);
		dialPad.six.click();
		Thread.sleep(2000);
		dialPad.seven.click();
		Thread.sleep(2000);
		dialPad.eight.click();
		Thread.sleep(2000);
		dialPad.nine.click();
		Thread.sleep(2000);
		dialPad.zero.click();
		Thread.sleep(2000);
		dialPad.star.click();
		Thread.sleep(2000);
		dialPad.hash.click();

		String verifyInput = dialPad.dialPadEnteredValues.getText();
		
		String expectesearchdata= excelFile.readCell("UI", "43803", excelFile.INPUT1_COL); //verifying searched input with excell data
		
		Assert.assertEquals(verifyInput, expectesearchdata);

		driver1.navigate().back();   //tapping the back button
	
		wait1.until(ExpectedConditions.visibilityOf(home.contacts));
		
		log.info("Test UI_43803_CheckDialPad - Completed");

	}
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "noOfDevices","deviceName","UDID","network","phoneType" })
	public void runBeforeMethod(String noOfDevices, String deviceNameList, String UDIDList, String phoneTypeList, String networkList, Method method) throws IOException, InterruptedException {
		log.info(" ************** START NEW METHOD ****************   " + method.getName());
		BeforeAfterTest supportTC = new BeforeAfterTest();
		supportTC.runBeforeMethod(noOfDevices, deviceNameList, UDIDList, phoneTypeList, networkList);
	}

	@AfterMethod(alwaysRun = true)
	@Parameters({ "noOfDevices","deviceName","UDID","network","phoneType" })
	public void runAfterMethod(String noOfDevices, String deviceNameList, String UDIDList, String phoneTypeList, String networkList, Method method) throws IOException, InterruptedException {
		log.info(" ************** END OF METHOD ****************   "+ method.getName());
		String testName = method.getName();
		BeforeAfterTest supportTC = new BeforeAfterTest();
		supportTC.runAfterMethod(noOfDevices, deviceNameList, UDIDList, phoneTypeList, networkList, testName);
	}

}
