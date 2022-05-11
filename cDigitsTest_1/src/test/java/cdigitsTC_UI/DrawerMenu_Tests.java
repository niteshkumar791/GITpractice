package cdigitsTC_UI;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Login;
import pageObjects.cDigits_Menu;
import pageObjects.cDigits_Menu_AppSettings;
import pageObjects.cDigits_Menu_Help;
import supportForTC.BeforeAfterTest;

/*
 * UI_43821_VerifyHelpAboutVersion
 * UI_43814_CheckVoicemailPinModifiable
 * UI_43815_CheckDeviceNameOption  (Not valid in v9.3.63 and later)
 * UI_43823_CheckSignout
 * 
 */
public class DrawerMenu_Tests extends base {

	public static final Logger log = Logger.getLogger(DrawerMenu_Tests.class);

	//43821 Verify Help About Version
	@Test
	public void UI_43821_VerifyHelpAboutVersion() throws InterruptedException {

		log.info("Test UI_43821_VerifyHelpAboutVersion - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();
		
		Thread.sleep(2000);
		
		//scroll from bottom to top
		Dimension size = driver1.manage().window().getSize();
		int height = size.getHeight();
		int width = size.getWidth();
		TouchAction ta = new TouchAction(driver1);
		Utilities util = new Utilities();
		util.ScrollBottomToTop(ta,height,width);
		Thread.sleep(2000);

		cDigits_Menu drawerMenu = new cDigits_Menu(driver1);
		drawerMenu.help.click();

		cDigits_Menu_Help helpMenu = new cDigits_Menu_Help(driver1);
		helpMenu.aboutDigits.click();

		Thread.sleep(2000);

		String versionActual = helpMenu.aboutVersionDisplayed.getText();
		String versionExpected = excelFile.readCell("Configuration", "cDigits Version", excelFile.CONFIG_DEVICE_NAME_COL);

		log.debug("Act : " + versionActual + " exp: " + versionExpected);

		Assert.assertEquals(versionActual, versionExpected);

		driver1.navigate().back();  //to ABout
		driver1.navigate().back();  //to help menu
		driver1.navigate().back();  //to home

		log.info("Test UI_43821_VerifyHelpAboutVersion - Completed");
	}

	//43814_Check Voice mail Pin Modifiable
	@Test
	public void UI_43814_CheckVoicemailPinModifiable() throws InterruptedException {

		log.info("Test UI_43814_CheckVoicemailPinModifiable - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();

		String getActivatedLine = excelFile.readCell("UI", "43814", excelFile.INPUT1_COL);
		
		cDigits_Menu drawerMenu = new cDigits_Menu(driver1);
		
		for(int i=0; i<drawerMenu.linesOnAccount.size(); i++) {
			if(drawerMenu.linesOnAccount.get(i).getText().contains(getActivatedLine)) {
				drawerMenu.linesOnAccount.get(i).click();
				Thread.sleep(2000);
				break;			
			}
		}
		

		cDigits_Menu_AppSettings appSettingsMenu = new cDigits_Menu_AppSettings(driver1);
		appSettingsMenu.voicemailPin.click();

		Thread.sleep(1000);

		appSettingsMenu.oldPin.click();

		driver1.navigate().back();  //to voicemail pin
		driver1.navigate().back();  //to active line click
		driver1.navigate().back();  //to help menu
		driver1.navigate().back();  //to home

		log.info("Test UI_43814_CheckVoicemailPinModifiable - Completed");
	}

	// 43815 CheckDeviceName option
	//Not valid test in 9.3.63 version
	//@Test
	public void UI_43815_CheckDeviceNameOption() {

		log.info("Test UI_43815_CheckDeviceNameOption - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();

		cDigits_Menu menu = new cDigits_Menu(driver1);
		menu.appSettings.click();

		cDigits_Menu_AppSettings appsetting = new cDigits_Menu_AppSettings(driver1);
		appsetting.DeviceName.click();

		boolean dname=false;
		try {
			dname=appsetting.DeviceName.isEnabled();
		}
		catch(Exception e) {
			
		}
		
		assertEquals(dname, true);

		driver1.navigate().back(); // menu
		driver1.navigate().back();//home

		log.info("Test UI_43815_CheckDeviceNameOption - Completed");
	}

	// 43823 signout option
	//@Test
	public void UI_43823_CheckSignout() {

		log.info("Test UI_43823_CheckSignout - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();
		cDigits_Menu dm = new cDigits_Menu(driver1);
		cDigits_Login login = new cDigits_Login(driver1);

		dm.signOut.click();

		dm.signoutconfirm.click();

		wait1.until(ExpectedConditions.visibilityOf(login.signInButton));

		boolean loginemail = login.loginEmail.isDisplayed();
		Assert.assertEquals(loginemail, true);
		log.info("Test UI_43823_CheckSignout - Completed");
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
