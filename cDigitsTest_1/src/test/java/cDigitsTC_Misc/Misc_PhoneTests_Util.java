package cDigitsTC_Misc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import commonClasses.base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Messages;
import supportForTC.BeforeAfterTest;

/*
 * Misc_211_CheckSelectNumberOptionMSG		//pass
 * Misc_212_CheckSelectNumberOptionCALL		//pass
 * Misc_69_checkCallLogOnAndroidPhone		//pass
 * Misc_70_checkCallLogOnTab				//pass
 * Misc_73_checkMsgLogAndroidPhone			//pass
 * Misc_74_checkMsgLogAndroidTab			//Not tested
 * Misc_9_checkPNSMsgLoginAndroidPhone (Not Possible)
 * Misc_11_checkPNSMsgLineActivatedAndroidPhone (Not Possible)
 * Misc_80_checkPNSMsgLoginLineAndroidPhone (Not Possible)
 * Misc_81_checkPNSMsgLoginLineAndroidTablet (Not Possible)
 * Misc_92_CheckSearchContactByFirstName							//pass
 * Misc_93_CheckSearchContactByLastName								//pass
 * Misc_94_CheckSearchContactByNumber								//pass
 * Misc_37_CheckAllCallLogs											//pass
 * Misc_38_CheckMIssedCallLogs										//pass
 * Misc_39_CheckAllCallLogs											//pass
 * Misc_40_CheckMIssedCallLogs										//pass									
 * Misc_195_VerifyGroupMessagePNSAndIndication
 * Misc_196_VerifyGroupMessagePNSAndIndicationTab
 * Misc_199_VerifyPictureRecevied
 * Misc_200_VerifyPictureReceviedTab
 * Misc_203_VerifySenderParticipitant
 * Misc_204_VerifySenderParticipitantTab
 * Misc_88_VerifyEveryLineReceivedMessage
 * Misc_89_VerifyEveryLineReceivedMessageTab
 */
public class Misc_PhoneTests_Util extends base{

	public static final Logger log = Logger.getLogger(Misc_PhoneTests_Util.class);

	@Test
	public boolean Misc_211_CheckSelectNumberOptionMSG(String TestID) throws Exception{

		cDigits_Home home= new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contactsTab.click();

		contact.favouriteTab.click();

		String Contactnum=excelFile.readCell("Misc", TestID, excelFile.INPUT5_COL);
		
		//get favorite cont list
		
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
				ActualList.add(Contactnum);
	
		boolean verFavavailability=callListString.containsAll(ActualList);
		
		if(verFavavailability==false) {
			contact.localTab.click();
			contact.searchInput.sendKeys(Contactnum);
			contact.Localcontactlist.get(0).click();
			contact.ContactInfo.click();
			contact.Makefavoritebutton.click();
			driver1.navigate().back();
			contact.favouriteTab.click();
			contact.searchInput.sendKeys(Contactnum);
			
			contact.favouritecontactlist.get(0).click();
		}
		
		else {
			
			contact.favouriteTab.click();
			contact.searchInput.sendKeys(Contactnum);
			contact.favouritecontactlist.get(0).click();
		}
		
		contact.MessageAction.click();

		Thread.sleep(2000);

		String Type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String Type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String Type3=excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		String Type4=excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL);

		List<String> Typeofcontacts = new ArrayList<String>();
		List<String> Explist = new ArrayList<String>();

		Explist.add(Type1);
		Explist.add(Type2);
		Explist.add(Type3);
		Explist.add(Type4);

		int size = contact.TypeOfContactNumber.size();
		String textVal = null;

		for (int i = 0; i <= size-1; i++) {

			textVal = contact.TypeOfContactNumber.get(i).getText();

			Typeofcontacts.add(textVal);

		}

		System.out.println(Typeofcontacts);
		System.out.println(Explist);


		boolean ver=Explist.containsAll(Typeofcontacts);

		//boolean veralerttochoosenum=contact.Alertfoselectline.isDisplayed();

//		driver1.navigate().back();   // To close number option
		//driver1.navigate().back();  // To home

		return ver;


	}


	@Test
	public boolean Misc_212_CheckSelectNumberOptionCALL(String TestID) throws Exception{

		cDigits_Home home= new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contactsTab.click();

		contact.favouriteTab.click();

		String Contactnum=excelFile.readCell("Misc", TestID, excelFile.INPUT5_COL);
		
		//get favorite cont list
		
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
				ActualList.add(Contactnum);
	
		boolean verFavavailability=callListString.containsAll(ActualList);
		
		if(verFavavailability==false) {
			contact.localTab.click();
			contact.searchInput.sendKeys(Contactnum);
			contact.Localcontactlist.get(0).click();
			contact.ContactInfo.click();
			contact.Makefavoritebutton.click();
			driver1.navigate().back();
			contact.favouriteTab.click();
			contact.searchInput.sendKeys(Contactnum);
			
			contact.favouritecontactlist.get(0).click();
		}
		
		else {
			
			contact.favouriteTab.click();
			contact.searchInput.sendKeys(Contactnum);
			contact.favouritecontactlist.get(0).click();
		}
		
		contact.CallAction.click();

		Thread.sleep(2000);

		String Type1=excelFile.readCell("Misc", "211", excelFile.INPUT1_COL);
		String Type2=excelFile.readCell("Misc", "211", excelFile.INPUT2_COL);
		String Type3=excelFile.readCell("Misc", "211", excelFile.INPUT3_COL);
		String Type4=excelFile.readCell("Misc", "211", excelFile.INPUT4_COL);

		List<String> Typeofcontacts = new ArrayList<String>();
		List<String> Explist = new ArrayList<String>();

		Explist.add(Type1);
		Explist.add(Type2);
		Explist.add(Type3);
		Explist.add(Type4);

		int size = contact.TypeOfContactNumber.size();
		String textVal = null;

		for (int i = 0; i <= size-1; i++) {

			textVal = contact.TypeOfContactNumber.get(i).getText();

			Typeofcontacts.add(textVal);

		}

		System.out.println(Typeofcontacts);
		System.out.println(Explist);


		boolean ver=Explist.containsAll(Typeofcontacts);

		//boolean veralerttochoosenum=contact.Alertfoselectline.isDisplayed();

//		driver1.navigate().back();   // To close number option
		//driver1.navigate().back();  // To home

		return ver;


	}

	//Misc_69 Check call log on other Phone
	@Test
	public boolean Misc_69_checkCallLogOnAndroidPhone(String TestID) throws Exception{


		cDigits_Home homeDR1= new cDigits_Home(driver1);
		cDigits_Contacts contactDR1 = new cDigits_Contacts(driver1);
		cDigits_Calls callDR1= new cDigits_Calls(driver1);
		cDigits_Home homeDR2= new cDigits_Home(driver2);
		cDigits_Calls callDR2= new cDigits_Calls(driver2);

		String callNumber=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String verifyNumber=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		homeDR1.contactsTab.click();

		contactDR1.searchInput.sendKeys(callNumber);

		contactDR1.Localcontactlist.get(0).click();

		contactDR1.CallAction.click();


		if(contactDR1.Alertfoselectline.isDisplayed()){

			contactDR1.SelectLineforphone.get(0).click();
			Thread.sleep(15000);
		}

		Thread.sleep(15000);

		callDR1.EndCall.click();

		Thread.sleep(5000);

		homeDR1.calls.click();
		callDR1.all.click();

		callDR1.PhonesList.get(0).click();
		String ActualNumber1=callDR1.CallDetailNumber.getText();

		boolean verName1=ActualNumber1.equalsIgnoreCase(verifyNumber);

		String ActualTime=callDR1.CallDetailNumberTime.get(0).getText();

		// Checking on second device

		homeDR2.calls.click();
		callDR2.PhonesList.get(0).click();

		String ActualNumber2=callDR2.CallDetailNumber.getText();

		String ActualTime2=callDR2.CallDetailNumberTime.get(0).getText();

		boolean verName2=verifyNumber.equalsIgnoreCase(ActualNumber2);

		boolean verTime2=ActualTime.equalsIgnoreCase(ActualTime2);

		boolean finl=verName1 && verName2 && verTime2;

		/*
		 * Assert.assertEquals(finl, true);
		 * 
		 * 
		 * driver1.navigate().back(); // to home screen driver2.navigate().back(); // to
		 * home screen
		 */		return finl;

	}

	//Misc_70_check Call Log On Tab
	//@Test
	public boolean Misc_70_checkCallLogOnTab(String TestID) throws Exception{

		cDigits_Home homeDR1= new cDigits_Home(driver1);
		cDigits_Contacts contactDR1 = new cDigits_Contacts(driver1);
		cDigits_Calls callDR1= new cDigits_Calls(driver1);
		cDigits_Home homeDR2= new cDigits_Home(driver2);
		cDigits_Calls callDR2= new cDigits_Calls(driver2);

		String callNumber=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String verifyNumber=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		homeDR1.contactsTab.click();

		contactDR1.searchInput.sendKeys(callNumber);

		contactDR1.Localcontactlist.get(0).click();

		contactDR1.CallAction.click();


		if(contactDR1.Alertfoselectline.isDisplayed()){

			contactDR1.SelectLineforphone.get(0).click();
			Thread.sleep(15000);
		}

		Thread.sleep(15000);

		callDR1.EndCall.click();

		Thread.sleep(3000);

		homeDR1.calls.click();
		callDR1.all.click();

		callDR1.PhonesList.get(0).click();
		String ActualNumber1=callDR1.CallDetailNumber.getText();

		System.out.println("Actual number is: - "+ActualNumber1);

		boolean verName1=ActualNumber1.equalsIgnoreCase(verifyNumber);

		String ActualTime=callDR1.CallDetailNumberTime.get(0).getText();



		// Checking on second device

		homeDR2.calls.click();
		callDR2.PhonesList.get(0).click();

		String ActualNumber2=callDR2.CallDetailNumber.getText();

		String ActualTime2=callDR2.CallDetailNumberTime.get(0).getText();

		boolean verName2=verifyNumber.equalsIgnoreCase(ActualNumber2);

		boolean verTime2=ActualTime.equalsIgnoreCase(ActualTime2);

		boolean finl=verName1 && verName2 && verTime2;

		/*
		 * Assert.assertEquals(finl, true);
		 * 
		 * driver1.navigate().back(); // to home screen
		 */		return finl;


	}


	//Misc_73 check Msg Log Android Phone
	@Test
	public boolean Misc_73_checkMsgLogAndroidPhone(String TestID) throws Exception{

		cDigits_Home homeDR1= new cDigits_Home(driver1);
		cDigits_Contacts contactDR1 = new cDigits_Contacts(driver1);
		cDigits_Messages msgDR1= new cDigits_Messages(driver1);

		cDigits_Calls callDR1= new cDigits_Calls(driver1);
		cDigits_Home homeDR2= new cDigits_Home(driver2);
		cDigits_Calls callDR2= new cDigits_Calls(driver2);
		cDigits_Messages msgDR2= new cDigits_Messages(driver2);

		String Message=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String MSGnumber=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		homeDR1.contactsTab.click();

		contactDR1.searchInput.sendKeys(MSGnumber);

		contactDR1.Localcontactlist.get(0).click();

		contactDR1.MessageAction.click();

		if(contactDR1.Alertfoselectline.isDisplayed()){

			contactDR1.SelectLineforphone.get(0).click();

		}

		Thread.sleep(10000);

		msgDR1.messageEnter.sendKeys(Message);

		msgDR1.messageSendButton.click();

		WebDriverWait wait = new WebDriverWait(driver1,150);

		wait.until(ExpectedConditions.visibilityOf(msgDR1.SentStatus));

		Thread.sleep(15000);

		homeDR2.messages.click();

		msgDR2.MsgList.get(0).click();

		Thread.sleep(5000);

		int size=msgDR2.TxtMsgList.size();

		String CheckMSG2=msgDR2.TxtMsgList.get(size-1).getText();

		boolean ver=CheckMSG2.equalsIgnoreCase(Message);

		/*
		 * Assert.assertEquals(ver, true);
		 * 
		 * driver1.navigate().back(); // to home screen driver2.navigate().back(); // to
		 * home screen
		 */		return ver;



	}

	//Misc_74 check Msg Log Android Tablet
	@Test
	public boolean Misc_74_checkMsgLogAndroidTab(String TestID) throws Exception{

		cDigits_Home homeDR1= new cDigits_Home(driver1);
		cDigits_Contacts contactDR1 = new cDigits_Contacts(driver1);
		cDigits_Messages msgDR1= new cDigits_Messages(driver1);

		cDigits_Calls callDR1= new cDigits_Calls(driver1);
		cDigits_Home homeDR2= new cDigits_Home(driver2);
		cDigits_Calls callDR2= new cDigits_Calls(driver2);
		cDigits_Messages msgDR2= new cDigits_Messages(driver2);

		String Message=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String MSGnumber=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		homeDR1.contactsTab.click();

		contactDR1.searchInput.sendKeys(MSGnumber);

		contactDR1.Localcontactlist.get(0).click();

		contactDR1.MessageAction.click();

		if(contactDR1.Alertfoselectline.isDisplayed()){

			contactDR1.SelectLineforphone.get(0).click();

		}

		Thread.sleep(15000);

		msgDR1.messageEnter.sendKeys(Message);

		msgDR1.messageSendButton.click();

		WebDriverWait wait = new WebDriverWait(driver1,150);

		wait.until(ExpectedConditions.visibilityOf(msgDR1.SentStatus));

		Thread.sleep(5000);

		homeDR2.messages.click();

		msgDR2.MsgList.get(0).click();

		Thread.sleep(5000);

		int size=msgDR2.TxtMsgList.size();

		String CheckMSG2=msgDR2.TxtMsgList.get(size-1).getText();

		boolean ver=CheckMSG2.equalsIgnoreCase(Message);

//		Assert.assertEquals(ver, true);
		return ver;



	}



	//Misc_9 check PNS Msg after Login to another device with same account
	@Test
	public boolean Misc_9_checkPNSMsgLoginAndroidPhone(String TestID) throws Exception{
		
		return false;


	}

	//Misc_11 check PNS Msg after Line activated to another device with same account
	@Test
	public boolean Misc_11_checkPNSMsgLineActivatedAndroidPhone(String TestID) throws Exception{
		return false;


	}

	//Misc_80 check PNS Msg after Login and line activated to another device with same account Anddroid Phone
	@Test
	public boolean Misc_80_checkPNSMsgLoginLineAndroidPhone(String TestID) throws Exception{

		return false;
	}

	//Misc_81 check PNS Msg after Login and line activated to another device with same account Anddroid Tablet
	@Test
	public boolean Misc_81_checkPNSMsgLoginLineAndroidTablet(String TestID) throws Exception{

		return false;
	}



	@Test
	public boolean Misc_92_CheckSearchContactByFirstName(String TestID) throws Exception {

		log.info("Test Misc_92_CheckSearchContactByFirstName - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contacts.click();

		String n1= excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String n2= excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		contact.searchInput.sendKeys(n1);

		String firstname=contact.Localcontactlist.get(0).getText();

		boolean contact1 = firstname.equalsIgnoreCase(n2);
		/*
		 * Assert.assertEquals(contact1, true);
		 * 
		 * log.info("Test Misc_92_CheckSearchContactByFirstName - Completed");
		 */
		return contact1;


	}

	@Test
	public boolean Misc_93_CheckSearchContactByLastName(String TestID) throws Exception {

		log.info("Test Misc_93_CheckSearchContactByLastName - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contacts.click();

		String n1= excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String n2= excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		contact.searchInput.sendKeys(n1);

		String lastname=contact.Localcontactlist.get(0).getText();

		boolean contact1 = lastname.equalsIgnoreCase(n2);
		/*
		 * Assert.assertEquals(contact1, true);
		 * 
		 * log.info("Test Misc_93_CheckSearchContactByLastName - Completed");
		 */
		return contact1;


	}

	@Test
	public boolean Misc_94_CheckSearchContactByNumber(String TestID) throws Exception {

		log.info("Test Misc_94_CheckSearchContactByNumber - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contacts.click();

		String n1= excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String n2= excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		contact.searchInput.sendKeys(n1);

		String number=contact.Localcontactlist.get(0).getText();

		boolean contact1 = number.equalsIgnoreCase(n2);
		/*
		 * Assert.assertEquals(contact1, true);
		 * 
		 * log.info("Test Misc_94_CheckSearchContactByNumber - Completed");
		 */
		return contact1;


	}

	//Check all call logs in CM
	@Test
	public boolean Misc_37_CheckAllCallLogs(String TestID) throws Exception {

		log.info("Test Misc_37_CheckAllCallLogs - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call= new cDigits_Calls(driver1);

		home.calls.click();
		call.all.click();


		//swipe down to get latest records
		Point firstEleTop = call.calllogolist.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		List<String> logolist = new ArrayList<String>();

		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Calls callScroll= new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.allcalllist.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.allcalllist.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
			//create the list of CallListObject

			for(int i=0; i<noRecsOnScreen-1; i++)
			{
				String textVal = callScroll.calllogolist.get(i).getAttribute("content-desc");
				String text = callScroll.allcalllist.get(i).getText();
				String concatString = text+textVal;

				//log.debug("CallList : " + recCount + " --" + textVal);
				if(! callListString.contains(concatString))
				{
					log.debug("New record");
					//callList.add(new util_CallListObject(recCount,textVal));

					//					if(logolist.contains(text))
					//					{
					//						newRecFound = true;
					//					}
					//					else
					//					{
					callListString.add(concatString);
					logolist.add(textVal);
					newRecFound = true;
					//}

				}

				recCount = recCount + 1;
			}

			if(! newRecFound)   //new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown = false;
			}
			else {

				if(noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release().perform();
					Thread.sleep(2000);
				}
				else  //no need to scroll down
				{
					scrollDown = false;
				}
			}


		}
		System.out.println(logolist);


		List<String> typelist = new ArrayList<String>();
		String type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String type3=excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		typelist.add(type1);
		typelist.add(type2);
		typelist.add(type3);
		System.out.println(typelist);

		boolean v=logolist.containsAll(typelist);

//		Assert.assertEquals(v, true);
		System.out.println(logolist);
		log.info("*****************"+logolist+"***********************");

//		log.info("Test Misc_37_CheckAllCallLogs - Completed");
		return v;


	}

	//Check Missed call logs in DM
	@Test
	public boolean Misc_38_CheckMIssedCallLogs(String TestID) throws Exception {

		log.info("Test Misc_38_CheckMIssedCallLogs - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call= new cDigits_Calls(driver1);

		home.calls.click();
		call.missed.click();


		//swipe down to get latest records
		Point firstEleTop = call.calllogolist.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		List<String> logolist = new ArrayList<String>();

		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Calls callScroll= new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.allcalllist.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.allcalllist.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
			//create the list of CallListObject

			for(int i=0; i<noRecsOnScreen-1; i++)
			{
				String textVal = callScroll.calllogolist.get(i).getAttribute("content-desc");
				String text = callScroll.allcalllist.get(i).getText();
				String concatString = text+textVal;

				//log.debug("CallList : " + recCount + " --" + textVal);
				if(! callListString.contains(concatString))
				{
					log.debug("New record");
					//callList.add(new util_CallListObject(recCount,textVal));

					//					if(logolist.contains(text))
					//					{
					//						newRecFound = true;
					//					}
					//					else
					//					{
					callListString.add(concatString);
					logolist.add(textVal);
					newRecFound = true;
					//}

				}

				recCount = recCount + 1;
			}

			if(! newRecFound)   //new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown = false;
			}
			else {

				if(noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release().perform();
					Thread.sleep(2000);
				}
				else  //no need to scroll down
				{
					scrollDown = false;
				}
			}


		}
		System.out.println(logolist);


		List<String> typelist = new ArrayList<String>();
		List<String> typelist1 = new ArrayList<String>();
		String type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String type3=excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		typelist.add(type1);
		typelist.add(type2);
		typelist1.add(type3);

		boolean v=logolist.containsAll(typelist);
		Assert.assertFalse(v, "Foud Outgoing/Incoming Calls in Log");
		boolean v1=logolist.containsAll(typelist1);

//		Assert.assertEquals(v, false);
		System.out.println(logolist);
		log.info("*****************"+logolist+"***********************");

//		log.info("Test Misc_38_CheckMIssedCallLogs - Completed");
		return v1;


	}


	//Check all call logs in DM
	@Test
	public boolean Misc_39_CheckAllCallLogs(String TestID) throws Exception {

		log.info("Test Misc_39_CheckAllCallLogs - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call= new cDigits_Calls(driver1);

		home.calls.click();
		call.all.click();


		//swipe down to get latest records
		Point firstEleTop = call.calllogolist.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		List<String> logolist = new ArrayList<String>();

		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Calls callScroll= new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.allcalllist.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.allcalllist.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
			//create the list of CallListObject

			for(int i=0; i<noRecsOnScreen-1; i++)
			{
				String textVal = callScroll.calllogolist.get(i).getAttribute("content-desc");
				String text = callScroll.allcalllist.get(i).getText();
				String concatString = text+textVal;

				//log.debug("CallList : " + recCount + " --" + textVal);
				if(! callListString.contains(concatString))
				{
					log.debug("New record");
					//callList.add(new util_CallListObject(recCount,textVal));

					//					if(logolist.contains(text))
					//					{
					//						newRecFound = true;
					//					}
					//					else
					//					{
					callListString.add(concatString);
					logolist.add(textVal);
					newRecFound = true;
					//}

				}

				recCount = recCount + 1;
			}

			if(! newRecFound)   //new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown = false;
			}
			else {

				if(noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release().perform();
					Thread.sleep(2000);
				}
				else  //no need to scroll down
				{
					scrollDown = false;
				}
			}


		}
		System.out.println(logolist);


		List<String> typelist = new ArrayList<String>();
		String type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String type3=excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		typelist.add(type1);
		typelist.add(type2);
		typelist.add(type3);
		System.out.println(typelist);

		boolean v=logolist.containsAll(typelist);

//		Assert.assertEquals(v, true);
		System.out.println(logolist);
		log.info("*****************"+logolist+"***********************");

//		log.info("Test Misc_39_CheckAllCallLogs - Completed");
		return v;


	}


	//Check Missed call logs in CM

	@Test
	public boolean Misc_40_CheckMIssedCallLogs(String TestID) throws Exception {

		log.info("Test Misc_40_CheckMIssedCallLogs - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call= new cDigits_Calls(driver1);

		home.calls.click();
		call.missed.click();


		//swipe down to get latest records
		Point firstEleTop = call.calllogolist.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		List<String> logolist = new ArrayList<String>();

		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Calls callScroll= new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.allcalllist.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.allcalllist.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
			//create the list of CallListObject

			for(int i=0; i<noRecsOnScreen-1; i++)
			{
				String textVal = callScroll.calllogolist.get(i).getAttribute("content-desc");
				String text = callScroll.allcalllist.get(i).getText();
				String concatString = text+textVal;

				//log.debug("CallList : " + recCount + " --" + textVal);
				if(! callListString.contains(concatString))
				{
					log.debug("New record");
					//callList.add(new util_CallListObject(recCount,textVal));

					//					if(logolist.contains(text))
					//					{
					//						newRecFound = true;
					//					}
					//					else
					//					{
					callListString.add(concatString);
					logolist.add(textVal);
					newRecFound = true;
					//}

				}

				recCount = recCount + 1;
			}

			if(! newRecFound)   //new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown = false;
			}
			else {

				if(noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release().perform();
					Thread.sleep(2000);
				}
				else  //no need to scroll down
				{
					scrollDown = false;
				}
			}


		}
		System.out.println(logolist);


		List<String> typelist = new ArrayList<String>();
		List<String> typelist1 = new ArrayList<String>();
		String type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String type3=excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		typelist.add(type1);
		typelist.add(type2);
		typelist1.add(type3);

		boolean v=logolist.containsAll(typelist);
		
		Assert.assertFalse(v, "Foud Outgoing/Incoming Calls in Log");
		
		boolean v1=logolist.containsAll(typelist1);

//		Assert.assertEquals(v, false);
		System.out.println(logolist);
		log.info("*****************"+logolist+"***********************");

//		log.info("Test Misc_40_CheckMIssedCallLogs - Completed");
		return v1;


	}
	
	
	/*** From Akshay  ***/
	@Test
	public boolean Misc_195_VerifyGroupMessagePNSAndIndication(String TestID) throws Exception {
		
		log.info("Test Misc_195_VerifyGroupMessagePNSAndIndication - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		

		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(4000);
		
		driver2.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver2.findElements(By.id("android:id/title"));
		  
		   String groupind = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   
		   boolean isNotificationFound=false;
		   for (MobileElement webElement : allnotifications) {
		       System.out.println(webElement.getText());
		       if(webElement.getText().contains(groupind)){
		    	   isNotificationFound=true;
		          System.out.println("Notification found");
		          break;
		   }
		}
		return isNotificationFound;
		   
//		Thread.sleep(3000);      
//		driver2.navigate().back();  
//		
//		driver1.navigate().back();
//		
//		log.info("Test Misc_195_VerifyGroupMessagePNSAndIndication - Completed");
	}
	
	
	
	
	@Test
	public boolean Misc_196_VerifyGroupMessagePNSAndIndicationTab(String TestID) throws Exception {
		
		log.info("Test Misc_196_VerifyGroupMessagePNSAndIndicationTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		

		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(4000);
		
		driver3.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver3.findElements(By.id("android:id/title"));
		  
		   String groupind2 = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   boolean flag=false;
		   for (MobileElement webElement : allnotifications) {
		       System.out.println(webElement.getText());
		       if(webElement.getText().contains(groupind2)){
		    	   flag=true;
		          System.out.println("Notification found");
		          break;
		   }
		}
		   
//		Thread.sleep(3000);      
//		driver3.navigate().back();  
//		
//		driver1.navigate().back();
//		
//		log.info("Test Misc_196_VerifyGroupMessagePNSAndIndicationTab - Completed");
		return flag;
	}

	
	
	
	@Test
	public boolean Misc_199_VerifyPictureRecevied(String TestID) throws Exception{
		
		log.info("Test Misc_199_VerifyPictureRecevied - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		cDigits_Messages message2 = new cDigits_Messages(driver2);
		
		home.messages.click();
		Thread.sleep(1000);
		
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(10000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		driver2.navigate().back();
		
		
		
		message.AttachFileButton.click();
		Thread.sleep(1000);
		
		message.SelectPhotoButton.click();
		Thread.sleep(2000);
		
		message.SelectImage.get(1).click();
		Thread.sleep(2000);
		
		message.SendMedia.click();
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver1, 70000);
		wait.until(ExpectedConditions.visibilityOf(message.DeliveredStatus));
		
		boolean delivery = message.DeliveredStatus.isDisplayed();
		Assert.assertEquals(delivery, true);	
		
		
		Thread.sleep(5000);
		driver2.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver2.findElements(By.id("android:id/title"));
		  
		   String groupind = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   boolean flag= false;
		   for (MobileElement webElement : allnotifications) {
		       System.out.println(webElement.getText());
		       if(webElement.getText().contains(groupind)){
		    	   flag=true;
		          System.out.println("Notification found");
		          break;
		   }
		}
		   
//		Thread.sleep(3000);      
//		driver2.navigate().back();  
//		
//		driver1.navigate().back();
//		
//		log.info("Test Misc_199_VerifyPictureRecevied - Completed");
		return flag;
		
	}
	
	
	
	
	@Test
	public boolean Misc_200_VerifyPictureReceviedTab(String TestID) throws Exception{
		
		log.info("Test Misc_200_VerifyPictureReceviedTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		cDigits_Messages message2 = new cDigits_Messages(driver3);
		
		home.messages.click();
		Thread.sleep(1000);
		
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(10000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		
		
		message.AttachFileButton.click();
		Thread.sleep(1000);
		
		message.SelectPhotoButton.click();
		Thread.sleep(2000);
		
		message.SelectImage.get(1).click();
		Thread.sleep(2000);
		
		message.SendMedia.click();
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver1, 70000);
		wait.until(ExpectedConditions.visibilityOf(message.DeliveredStatus));
		
		boolean delivery = message.DeliveredStatus.isDisplayed();
		Assert.assertEquals(delivery, true);	
		
		
		Thread.sleep(5000);
		driver3.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver3.findElements(By.id("android:id/title"));
		  
		   String groupind = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   boolean flag=false;
		   for (MobileElement webElement : allnotifications) {
		       System.out.println(webElement.getText());
		       if(webElement.getText().contains(groupind)){
		    	   flag=true;
		          System.out.println("Picture Notification found");
		          break;
		   }
		}
		   
		/*
		 * Thread.sleep(3000); driver3.navigate().back();
		 * 
		 * driver1.navigate().back();
		 * 
		 * log.info("Test Misc_200_VerifyPictureReceviedTab - Completed");
		 */
		return flag;
		
	}

	
	
	
	
	@Test
	public boolean Misc_203_VerifySenderParticipitant(String TestID) throws Exception{

		log.info("Misc_203_VerifySenderParticipitant- Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message2 = new cDigits_Messages(driver2);
		
		home.messages.click();
		Thread.sleep(1000);
		
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		/*message.receipentNamesList.get(0).click();*/
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		Thread.sleep(3000);
		
		message.AddContact.click();
		Thread.sleep(2000);
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		/*message.receipentNamesList.get(0).click();*/
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(15000);
		
		home2.messages.click();
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		driver2.navigate().back();
		
		message.AttachFileButton.click();
		Thread.sleep(1000);
		
		message.SelectPhotoButton.click();
		Thread.sleep(2000);
		
		message.SelectImage.get(2).click();
		Thread.sleep(2000);
		
		message.SendMedia.click();
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver1, 70000);
		wait.until(ExpectedConditions.visibilityOf(message.DeliveredStatus));
		

		Thread.sleep(5000);
		driver2.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver2.findElements(By.id("android:id/text"));
		  
		   String groupind = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   
		   boolean flag=false;
		   for (MobileElement webElement : allnotifications) {
			   String cd = webElement.getText();
		       System.out.println(cd);
		      
		       if(cd.contains(groupind)){
		    	   flag=true;
		          System.out.println("Participant is :"+groupind);
		          break;
		   }
		}
		   
		/*
		 * Thread.sleep(3000); driver2.navigate().back();
		 * 
		 * driver1.navigate().back();
		 * 
		 * log.info("Misc_203_VerifySenderParticipitant - Completed");
		 */
		return flag;
		   
	}
	
	
	
	
	
	
	@Test
	public boolean Misc_204_VerifySenderParticipitantTab(String TestID) throws Exception{

		log.info("Test Misc_204_VerifySenderParticipitantTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message2 = new cDigits_Messages(driver2);
		
		home.messages.click();
		Thread.sleep(1000);
		
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(10000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
	
		
		

		message.AttachFileButton.click();
		Thread.sleep(1000);
		
		message.SelectPhotoButton.click();
		Thread.sleep(2000);
		
		message.SelectImage.get(2).click();
		Thread.sleep(2000);
		
		message.SendMedia.click();
		Thread.sleep(2000);
		
		message.messageSendButton.click();
		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver1, 70000);
		wait.until(ExpectedConditions.visibilityOf(message.DeliveredStatus));
		

		Thread.sleep(5000);
		driver3.openNotifications();
		Thread.sleep(3000);
		   List<AndroidElement> allnotifications = driver3.findElements(By.id("android:id/text"));
		  
		   String groupind = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL); 
		   boolean flag=false;
		   for (MobileElement webElement : allnotifications) {
			   String cd = webElement.getText();
		       System.out.println(cd);
		      
		       if(cd.contains(groupind)){
		    	   flag=true;
		          System.out.println("Participant is :"+groupind);
		          break;
		   }
		}
		
		driver1.navigate().back();
		
		driver3.navigate().back();
		
		log.info("Misc_204_VerifySenderParticipitantTab - Completed");
		return flag;
		   
	}
	
	
	
	
	@Test
	public boolean Misc_88_VerifyEveryLineReceivedMessage(String TestID) throws Exception {
		
		log.info("Test Misc_88_VerifyEveryLineReceivedMessage - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message2 = new cDigits_Messages(driver2);

	
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		//message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		//message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(20000);
		
		//home2.messages.click();
		//Thread.sleep(2000);
		
		
		//Select 1st Line
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Selectlinefromdropdown.get(0).click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);
		int msgsNo = messagesDev2.textMessagesReceived.size();		
		boolean msg = false; 
		
		for(int i=0; i<msgsNo-1; i++) {
			
			String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived);	
			msg = msgToSend.equalsIgnoreCase(msgReceived);
		}
		
		driver2.navigate().back();
		Thread.sleep(2000);
		
		
		
		//Select 2nd Line
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Selectlinefromdropdown.get(1).click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev21 = new cDigits_Messages(driver2);
		int msgsNo1 = messagesDev21.textMessagesReceived.size();
		boolean msg2 = false;
		
		for(int i=0; i<msgsNo1-1; i++) {
			
			String msgReceived2 = messagesDev21.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived2);	
			msg2 = msgToSend.equalsIgnoreCase(msgReceived2);
		}
		
		boolean line = msg && msg2;
		
		/*
		 * Assert.assertEquals(line, true);
		 * 
		 * driver2.navigate().back();
		 * 
		 * driver1.navigate().back();
		 * 
		 * log.info("Test Misc_88_VerifyEveryLineReceivedMessage - Completed");
		 */
		return line;
	}

	
	
	
	
	@Test
	public boolean Misc_89_VerifyEveryLineReceivedMessageTab(String TestID) throws Exception {
		
		log.info("Test Misc_89_VerifyEveryLineReceivedMessageTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		cDigits_Home home2 = new cDigits_Home(driver3);
		cDigits_Messages message2 = new cDigits_Messages(driver3);

	
		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		message.AddContact.click();
		message.AddContactButton.click();
		Thread.sleep(2000);
		
		String receipient2 = excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		
		message.enterRecepient.sendKeys(receipient2);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		
		String msgToSend = excelFile.readCell("Misc",TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(20000);
		
		//home2.messages.click();
		//Thread.sleep(2000);
		
		
		//Select 1st Line
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Line4303.click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev2 = new cDigits_Messages(driver3);
		int msgsNo = messagesDev2.textMessagesReceived.size();		
		boolean msg = false; 
		
		for(int i=0; i<msgsNo-1; i++) {
			
			String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived);	
			msg = msgToSend.equalsIgnoreCase(msgReceived);
		}
		
		
		
		//Select 2nd Line
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Line9832.click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev21 = new cDigits_Messages(driver3);
		int msgsNo1 = messagesDev21.textMessagesReceived.size();
		boolean msg2 = false;
		
		for(int i=0; i<msgsNo1-1; i++) {
			
			String msgReceived2 = messagesDev21.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived2);	
			msg2 = msgToSend.equalsIgnoreCase(msgReceived2);
		}
		
		boolean line = msg && msg2;
		
		/*
		 * Assert.assertEquals(line, true);
		 * 
		 * 
		 * 
		 * //driver3.navigate().back();
		 * 
		 * driver1.navigate().back();
		 * 
		 * log.info("Test Misc_89_VerifyEveryLineReceivedMessageTab - Completed");
		 */
		return line;
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


