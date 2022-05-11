package cdigitsTC_UI;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.util_CallListObject;
import supportForTC.BeforeAfterTest;

/*
 * UI_43808_CheckContactsTabs
 * UI_43809_CheckFavorite
 * UI_43811_CheckSyncContactTab
 * 
 */
public class Contacts_Tests extends base {

	public static final Logger log = Logger.getLogger(Contacts_Tests.class);

	//@Test
	public void UI_43808_CheckContactsTabs() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		log.info("Test UI_43808_CheckContactsTabs - Started");
		boolean checkTab = true;

		cDigits_Home home = new cDigits_Home(driver1);
		home.contacts.click();

		cDigits_Contacts contacts = new cDigits_Contacts(driver1);
		contacts.localTab.click();

		// Local Tab Highlight
		Thread.sleep(3000);
		if (checkTab)
			checkTab = contacts.localTabHighlight.isDisplayed();
		Thread.sleep(2000);

		// favourite Tab Highlight
		contacts.favouriteTab.click();
		Thread.sleep(3000);
		if (checkTab)
			checkTab = contacts.favouriteTabHighlight.isDisplayed();
		Thread.sleep(2000);

		// cloud Tab Highlight
		contacts.cloudTab.click();
		Thread.sleep(3000);
		if (checkTab)
			checkTab = contacts.cloudTabHighlight.isDisplayed();
		Thread.sleep(2000);

		if (checkTab) {
			log.info("All Contacts Tabs working");
		}

		Assert.assertEquals(checkTab, true);
		driver1.navigate().back(); // tapping the back button
		Thread.sleep(5000);

		log.info("Test UI_43808_CheckContactsTabs - Completed");
	}

	//@Test
	public void UI_43809_CheckFavorite() throws Exception {

		log.info("Test UI_43809_CheckFavorite - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contacts.click();
		Thread.sleep(5000);
		contact.favouriteTab.click();


		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//TO DO: need to take input values from excel, which are the favourite contacts and then loop through and check if all are present

		// swipe down to get latest records
		Point firstEleTop = contact.favouriteContactList.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);
		

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Contacts contactscroll = new cDigits_Contacts(driver1);
			int noRecsOnScreen = contactscroll.favouriteContactList.size();
			log.debug("noRecsOnScreen : "  + noRecsOnScreen );
			Point firstSwipe = contactscroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = contactscroll.SwipeList.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoidincomplete info in last record  create the list of CallListObject.

			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = contactscroll.favouriteContactList.get(i).getText();
				//String dateVal = contactscroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal;
				log.debug("CallList : "  + textVal );

				if (!callListString.contains(concatString)) {
					log.debug("New record");
					//callList.add(new util_CallListObject(textVal));
					callListString.add(concatString);
					newRecFound = true;
				}

				recCount = recCount + 1;
			}

			if (!newRecFound) // new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown = false;
			} else {

				if (noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(x,seclastSwipe.y)).moveTo(PointOption.point(x,firstSwipe.y)).release()
					.perform();
					Thread.sleep(2000);
				} else // no need to scroll down
				{
					scrollDown = false;
				}
			}
		}

		List<String> ActualList = new ArrayList<String>();
		String n1= excelFile.readCell("UI", "43809", excelFile.INPUT1_COL);
		log.info("List to check : " + n1);
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(n1);

		ActualList = (List<String>) json.get("namelist");
				
		boolean verifyFavoritecontactList= callListString.containsAll(ActualList);

		Assert.assertEquals(verifyFavoritecontactList, true);
		log.info("Favourite List match result : " + verifyFavoritecontactList);

		driver1.navigate().back(); // tapping the back button
		Thread.sleep(5000);
		
		log.info("Test UI_43809_CheckFavorite - Completed");
	}


	@Test
	public void UI_43811_CheckSyncContactTab() throws Exception {

		log.info("Test UI_43811_CheckSyncContactTab - Started");
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		//Adding Contact
		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);

		contact.Addcontact.click();
		Thread.sleep(5000);

		String contactobject=excelFile.readCell("UI", "43811", excelFile.INPUT1_COL);
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(contactobject);

		String devicename=excelFile.readCell("Configuration", "Device1", excelFile.CONFIG_PHONETYPE_COL);
		String newContact = "";

		if(devicename.equalsIgnoreCase("Nexus") || devicename.equalsIgnoreCase("E5")){

			log.info("Found nexus/E5 ");

			contact.FirstName.sendKeys(json.get("firstname").toString());
			contact.LastName.sendKeys(json.get("lastname").toString());
			
			newContact = json.get("firstname").toString();
		}
		else {

			log.info("Found G5/Tab ");

			contact.ContactName.sendKeys(json.get("name").toString());
			newContact = json.get("name").toString();
		}

		contact.PhoneNumber.sendKeys(json.get("phone").toString());
		contact.ContactSave.click();

		Thread.sleep(5000);

		driver1.navigate().back();
		Thread.sleep(2000);


		//Syncing contact from cloud
		home.contacts.click();
		Thread.sleep(5000);

		contact.cloudTab.click();
		Thread.sleep(3000);

		contact.toolbaroption.click();
		Thread.sleep(3000);


		Utilities util = new Utilities();
		TouchAction secAns = new TouchAction(driver1);
		util.ClickOnSyncContacts(devicename, secAns);

		Thread.sleep(15000);

		contact.searchInput.sendKeys(newContact);
		driver1.hideKeyboard();
		Thread.sleep(2000);

		int y = 0;
		int x = 0;
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int x2 = (int) (sizeOfScreen.width * 0.8);
		
		boolean verifycontact = false;
		for(int i=0; i<contact.Cloudcontactlist.size(); i++) {
			if(contact.Cloudcontactlist.get(i).getText().contains(newContact)) {
				verifycontact=true;
				Point firstEle = contact.Cloudcontactlist.get(i).getLocation();
				x=firstEle.getX();
				y=firstEle.getY();
				break;			
			}
		}

		Assert.assertEquals(verifycontact, true);
		Thread.sleep(2000);

		if(verifycontact) {
			TouchAction secAns7 = new TouchAction(driver1);
			util.SwipeLeftforDeleteOption(x2,y,x,y,secAns7);
	
			contact.Deletecontact.click();
			Thread.sleep(10000);
		}
		else {
			//delet the contact from the local tab
			contact.localTab.click();
			Thread.sleep(2000);
			
			contact.searchInput.sendKeys(newContact);
			driver1.hideKeyboard();
			Thread.sleep(2000);
			
			Point firstEle = contact.Localcontactlist.get(0).getLocation();
			x=firstEle.getX();
			y=firstEle.getY();
			
			TouchAction secAns7 = new TouchAction(driver1);
			util.SwipeLeftforDeleteOption(x2,y,x,y,secAns7);
	
			contact.Deletecontact.click();
			Thread.sleep(10000);

		}

		contact.searchInput.clear();
		Thread.sleep(2000);
		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver1.navigate().back(); // tapping the back button
		Thread.sleep(5000);
		log.info("Test UI_43811_CheckSyncContactTab - Completed");

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
