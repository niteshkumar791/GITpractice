package cdigitsTC_UI;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commonClasses.base;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Menu;
import pageObjects.cDigits_Menu_AppSettings;
import supportForTC.BeforeAfterTest;

/*
 * 	UI_46093_TabsUnderHomeHighlighting
 * 
 * 
 */
public class Tablet_Home_Tests extends base {

	public static final Logger log = Logger.getLogger(Contacts_Tests.class);

	@Test
	public void UI_46093_TabsUnderHomeHighlighting() throws Exception {

		log.info("Test UI_46093_TabsUnderHomeHighlighting - Started");

		boolean checkTab = true;

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contacts = new cDigits_Contacts(driver1);
		home.contacts.click();
	
		// Contact Tab Highlight
		if (checkTab)
			checkTab = home.contacts.isSelected();
		Thread.sleep(2000);

		// Messaging Tab Highlight
		home.messages.click();
		if (checkTab)
			checkTab = home.messages.isSelected();
		Thread.sleep(2000);

		// Calls Tab Highlight
		home.calls.click();
		if (checkTab)
			checkTab = home.calls.isSelected();
		Thread.sleep(2000);

		if(checkTab){
			log.debug(" All Home page tabs working");
		}

		Assert.assertEquals(checkTab, true);
		log.info("UI_46093_TabsUnderHomeHighlighting - Completed");
		
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
