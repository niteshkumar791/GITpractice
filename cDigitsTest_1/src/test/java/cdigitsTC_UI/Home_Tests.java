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
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import supportForTC.BeforeAfterTest;

/*
 * UI_43801_contact_Message_Call_Higlitining
 * UI_43802_Dailpad_Allcall_missedcall_voicemail_Higlitining
 * 
 */
public class Home_Tests extends base{

	public static final Logger log = Logger.getLogger(Home_Tests.class);

	// 43801 Calls message contacts switch check highlighting
	@Test
	public void UI_43801_TabsUnderHomeHighlighting() throws Exception {

		log.info("Test UI_43801_TabsUnderHomeHighlighting - Started");

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

		// Dialpad Tab Highlight
		home.dialPad.click();
		if (checkTab)
			checkTab = home.dialPad.isSelected();
		Thread.sleep(2000);
				
		if(checkTab){
			log.debug(" All Home page tabs working");
		}

		Assert.assertEquals(checkTab, true);
		log.info("Test UI_43801_TabsUnderHomeHighlighting - Completed");

	}

	// 43802 Calls Dailpad_All call_missed call_voicemail switch check
	@Test
	public void UI_43802_TabsUnderCallsHighlighting() throws Exception {

		log.info("Test UI_43802_TabsUnderCallsHighlighting - Started");
		boolean checkTab= true;
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call = new cDigits_Calls(driver1);
		// Calls Tab Highlight

		home.calls.click();
		if (checkTab)
			checkTab = home.calls.isSelected();
		Thread.sleep(2000);

		// All calls Tab Highlight
		call.all.click();
		if (checkTab)
			checkTab =call.all.isSelected();
		Thread.sleep(2000);

		// Missed call Tab Highlight
		call.missed.click();
		if (checkTab)
			checkTab = call.missed.isSelected();
		Thread.sleep(2000);

		// Voicemail Tab Highlight
		call.voicemail.click();
		if (checkTab)
			checkTab = call.voicemail.isSelected();
		Thread.sleep(2000);


		if (checkTab) {
			log.debug(" All Calls tabs working");
		}
		Assert.assertEquals(checkTab, true);
		
		driver1.navigate().back(); // tapping the back button
		Thread.sleep(5000);
		log.info("Test UI_43802_TabsUnderCallsHighlighting - Started");
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
