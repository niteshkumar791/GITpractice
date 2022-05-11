package cdigitsTC_UI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import supportForTC.BeforeAfterTest;

/*
 * 	UI_46100_CheckTabSelectedContactTab
 * 	UI_46101_CheckTabFavorite
 * 	UI_46103_CheckTabSyncContactTab
 * 
 */
public class Tablet_Contacts_Tests extends base {

	public static final Logger log = Logger.getLogger(Contacts_Tests.class);

	@Test
	public void UI_46100_CheckTabSelectedContactTab() throws InterruptedException{
		
		log.info("Test UI_46100_CheckTabSelectedContactTab - Started");
		try {
			cDigits_Home home = new cDigits_Home(driver1);
			cDigits_Contacts contact = new cDigits_Contacts(driver1);
	        home.contacts.click();
	        boolean checkTab = true;
	        
	        // Local Tab Highlight
	        contact.localTab.click();
			Thread.sleep(3000);
			if (checkTab)
				checkTab = contact.localTabHighlight.isDisplayed();
			Thread.sleep(2000);

	        // favourite Tab Highlight
			contact.favouriteTab.click();
			Thread.sleep(3000);
			if (checkTab)
				checkTab = contact.favouriteTabHighlight.isDisplayed();
			Thread.sleep(2000);
	        
	        contact.cloudTab.click();
	        Thread.sleep(3000);
	        if (checkTab)
				checkTab = contact.cloudTabHighlight.isDisplayed();
			Thread.sleep(2000);

			if (checkTab) {
				log.info("All Contacts Tabs working");
			}

			Assert.assertEquals(checkTab, true);
			driver1.navigate().back(); // tapping the back button
	        Thread.sleep(5000);
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(" Exception :  " + e);
		}	

        log.info("Test UI_46100_CheckTabSelectedContactTab - Completed");
     
	}
	
	@Test
	public void UI_46101_CheckTabFavorite() throws Exception {

		log.info("Test UI_46101_CheckTabFavorite - Started");
		try {
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
	
				Point firstSwipe = contactscroll.SwipeList.get(1).getLocation();
				Point seclastSwipe = contactscroll.favouriteContactList.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoidincomplete info in last record  create the list of CallListObject.
	
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
						secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release()
								.perform();
						Thread.sleep(2000);
					} else // no need to scroll down
					{
						scrollDown = false;
					}
				}
			}
	
			List<String> ActualList1 = new ArrayList<String>();
			String n1= excelFile.readCell("UI", "43809", excelFile.INPUT1_COL);
			String n2= excelFile.readCell("UI", "43809", excelFile.INPUT2_COL);
			String n3= excelFile.readCell("UI", "43809", excelFile.INPUT3_COL);
			String n4= excelFile.readCell("UI", "43809", excelFile.INPUT4_COL);
			String n5= excelFile.readCell("UI", "43809", excelFile.INPUT5_COL);
			String n6= excelFile.readCell("UI", "43809", excelFile.INPUT6_COL);
			ActualList1.add(n1);
			ActualList1.add(n2);
			ActualList1.add(n3);
			ActualList1.add(n4);
			ActualList1.add(n5);
			ActualList1.add(n6);
			// boolean verifyFavoriteList=callList.contains(ActualList);
			// boolean verifyFavoritecontactList= callListString.containsAll(ActualList1) && callListString.size() == ActualList1.size() ;
			 String List1=null;
			 String List2=null;
			 
			 for(int i=1;i<=callListString.size();i++){
				 
				  List1=callListString.get(i);
				 
			 }
			 
			 for(int i=1;i<=ActualList1.size(); i++){
				 
				 List2=callListString.get(i);
				
			 }
			boolean ver= List1.equalsIgnoreCase(List2);
		
			Assert.assertEquals(ver, true);
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(" Exception :  " + e);
		}
		log.info("Test UI_46101_CheckTabFavorite - Completed");
	}

	
	@Test
	public void UI_46103_CheckTabSyncContactTab() throws Exception {

		log.info("Test UI_46103_CheckTabSyncContactTab - Started");
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		//Adding Contact
		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);

		contact.Addcontact.click();
		Thread.sleep(5000);

		String contactName1=excelFile.readCell("UI", "46103", excelFile.INPUT1_COL);
		contact.ContactName.clear();
		contact.ContactName.sendKeys(contactName1);

//		try {
//			driver1.hideKeyboard();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String contactnumber=excelFile.readCell("UI", "46103", excelFile.INPUT3_COL);
		String contactName2=excelFile.readCell("UI", "46103", excelFile.INPUT2_COL);
		
		
		String devicename=excelFile.readCell("Configuration", "Device1", excelFile.CONFIG_PHONETYPE_COL);
		
		if(devicename.equalsIgnoreCase("Nexus") || devicename.equalsIgnoreCase("E5")){
			
			contact.FirstName.clear();
			contact.FirstName.sendKeys(contactName1);
			contact.LastName.clear();
			contact.LastName.sendKeys(contactName2);
		}
		else {
			
			contact.ContactName.clear();
			contact.ContactName.sendKeys(contactName1);
		}
				
			contact.PhoneNumberTab.click();
			contact.PhoneNumberTab.clear();
			contact.PhoneNumberTab.sendKeys(contactnumber);
	 

		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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


		TouchAction secAns = new TouchAction(driver1);
		secAns.tap((PointOption.point(555,114))).perform();


		Thread.sleep(15000);

		/*WebDriverWait wait = new WebDriverWait(driver1,100);

	    wait.until(ExpectedConditions.visibilityOf(contact.Ameencontact));*/


		contact.searchInput.sendKeys(contactName1);
		driver1.hideKeyboard();
		Thread.sleep(2000);

		boolean verifycontact = contact.ATTContact1.isDisplayed();

		Assert.assertEquals(verifycontact, true);
		Thread.sleep(2000);

		Utilities util = new Utilities();

		TouchAction secAns7 = new TouchAction(driver1);
		util.SwipeLeftforDeleteOption_MotoG5(secAns7);

		contact.Deletecontact.click();
		Thread.sleep(10000);

		contact.searchInput.clear();
		Thread.sleep(2000);
		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info("Test UI_46103_CheckTabSyncContactTab - Completed");

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
