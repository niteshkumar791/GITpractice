package cdigitsTC_UI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commonClasses.base;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Menu;
import pageObjects.cDigits_Menu_AppSettings;
import pageObjects.cDigits_Menu_Help;
import supportForTC.BeforeAfterTest;

/*
 * 	UI_46106_CheckTabVoicemailPinChange
 * 	UI_46113_VerifyHelpAboutVersion
 * 
 * 
 */
public class Tablet_DrawerMenu_Tests extends base {

	public static final Logger log = Logger.getLogger(Contacts_Tests.class);

	
	@Test
	public void UI_46106_CheckTabVoicemailPinChange() throws InterruptedException {

		log.info("Test UI_46106_CheckTabVoicemailPinChange - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();

		cDigits_Menu drawerMenu = new cDigits_Menu(driver1);
		drawerMenu.appSettings.click();

		cDigits_Menu_AppSettings appSettingsMenu = new cDigits_Menu_AppSettings(driver1);
//		appSettingsMenu.activatedLine.click();
		
		
		
		for(int i=0; i<=drawerMenu.linesOnAccount.size(); i++) {
			
			if(drawerMenu.lineToggle.get(i).getText().equalsIgnoreCase("ON"))
			{
				drawerMenu.linesOnAccount.get(i).click();
				break;
			}
					
			
		}
		

		Thread.sleep(1000);

		appSettingsMenu.voicemailPin.click();

		Thread.sleep(1000);

		appSettingsMenu.oldPin.click();
		appSettingsMenu.oldPin.sendKeys("1234");
		Thread.sleep(2000);		
		
		appSettingsMenu.newPin.click();
		appSettingsMenu.newPin.sendKeys("5678");
		Thread.sleep(2000);
		
		appSettingsMenu.ConfirmnewPin.click();
		appSettingsMenu.ConfirmnewPin.sendKeys("5678");
		Thread.sleep(2000);

		driver1.navigate().back();  //to voicemail pin
		driver1.navigate().back();  //to active line click
		driver1.navigate().back();  //to help menu
		driver1.navigate().back();  //to home

		log.info("Test UI_46106_CheckTabVoicemailPinChange - Completed");
	}
	
	@Test
	public void UI_46113_VerifyHelpAboutVersion() throws InterruptedException {

		log.info("Test UI_46113_VerifyHelpAboutVersion - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		home.drawerHandle.click();

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
		//driver1.navigate().back();  //to home

		log.info("Test UI_46113_VerifyHelpAboutVersion - Completed");
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
