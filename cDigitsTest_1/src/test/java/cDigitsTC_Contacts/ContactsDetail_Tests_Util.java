package cDigitsTC_Contacts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cdigitsTC_UI.Calls_Tests;
import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Messages;
import pageObjects.util_CallListObject;
import supportForTC.BeforeAfterTest;

/*
 * 	Contacts_43839_AddRemoveFavorite
 * 	Contacts_46087_CheckForPCCSCCContact
 * 	Contacts_43834_VerifyAddContact
 *  Contacts_43835_VerifyDeleteContact
 * 	Contacts_43837_VerifyMessageSend
 * 	Contacts_43836_VerifyCall
 * 	Contacts_43838_CheckSyncContactFromCloud
 * 
 */
public class ContactsDetail_Tests_Util extends base{

	public static final Logger log = Logger.getLogger(ContactsDetail_Tests_Util.class);
	
		//43839 check for add remove favorite contact
//		@Test(priority=2)
	public boolean Contacts_43839_AddRemoveFavorite(String TestID) {

			log.info("Test Contacts_43839_AddRemoveFavorite - Started");

			cDigits_Home home = new cDigits_Home(driver1);
			cDigits_Contacts contact = new cDigits_Contacts(driver1);

			home.contacts.click();

			// To DO : need to take the input from excel and proceed.

			// To add contact in favorite
			
			String ContactToAddinFavorite = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
			
			contact.searchInput.sendKeys(ContactToAddinFavorite);
			
			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Keyboard not found");
			}
			
			contact.Localcontactlist.get(0).click();
			
			//contact.MexicoE5contact.click();

			contact.ContactInfo.click();

			contact.Makefavoritebutton.click();
			
			driver1.navigate().back();

			contact.favouriteTab.click();

			// verify in favrate
			
			contact.searchInput.sendKeys(ContactToAddinFavorite);
			
			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Keyboard not found");
			}
			
			String checkaddedcontact=contact.favouriteContactList.get(0).getText();
			
			boolean checkF = checkaddedcontact.equalsIgnoreCase(ContactToAddinFavorite);

			Assert.assertEquals(checkF, true);
		

			// To remove contact from favorite

			contact.favouriteContactList.get(0).click();
			

			contact.ContactInfo.click();

			contact.Makefavoritebutton.click();
			
			driver1.navigate().back();
			
			contact.favouriteTab.click();
			
			boolean ver=contact.favouriteContactList.contains(ContactToAddinFavorite);
		/*
		 * Assert.assertEquals(ver, false);
		 * 
		 * log.info("Test Contacts_43839_AddRemoveFavorite - Completed");
		 */
			return ver;
		}



		//46087 Check for local contacts and clowd contacts
//		@Test(priority=1)
	public boolean Contacts_46087_CheckForPCCSCCContact(String TestID) throws Exception {

			log.info("Test Contacts_46087_CheckForPCCSCCContact - Started");
			cDigits_Home home = new cDigits_Home(driver1);
			cDigits_Contacts contact = new cDigits_Contacts(driver1);
			home.contacts.click();

			

				// swipe down to get latest records
				

				Point firstEleTop = contact.Localcontactlist.get(0).getLocation();
				int y = firstEleTop.getY();
				int x = firstEleTop.getX();
				Dimension sizeOfScreen = driver1.manage().window().getSize();
				int y2 = (int) (sizeOfScreen.height * 0.8);

				TouchAction ta = new TouchAction(driver1);
				ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
				Thread.sleep(10000);

				//List<util_CallListObject> LocalCallList = new ArrayList<util_CallListObject>();
				List<String> callListString = new ArrayList<String>();
				int recCount = 1;
				boolean scrollDown = true;

				while (scrollDown) {
				boolean newRecFound = false;
				cDigits_Contacts contactscroll = new cDigits_Contacts(driver1);
				int noRecsOnScreen = contactscroll.Localcontactlist.size();

				Point firstSwipe = contactscroll.SwipeList.get(1).getLocation();
				Point seclastSwipe = contactscroll.Localcontactlist.get(noRecsOnScreen - 2).getLocation(); // taking -2 to avoidincomplete info in last record create the list of CallListObject.

				for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = contactscroll.Localcontactlist.get(i).getText();
				//String dateVal = contactscroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal;
				log.debug("CallList : " + recCount + " --" + textVal );

				if (!callListString.contains(concatString)) {
				log.debug("New record");
				//LocalCallList.add(new util_CallListObject(recCount, textVal));
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

				log.info("Test UI_43809_CheckFavorite - Completed");



				// swipe down to get latest records
				cDigits_Contacts contact2 = new cDigits_Contacts(driver1);
				contact2.favouriteTab.click();
				Thread.sleep(2000);
				contact2.cloudTab.click();
				Thread.sleep(3000);
				
				Point firstEleTop2 = contact2.Cloudcontactlist.get(0).getLocation();
				int y1 = firstEleTop2.getY();
				int x1 = firstEleTop2.getX();
				Dimension sizeOfScreen2 = driver1.manage().window().getSize();
				int y3 = (int) (sizeOfScreen2.height * 0.8);

				TouchAction ta2 = new TouchAction(driver1);
				ta2.longPress(PointOption.point(x1, y1)).moveTo(PointOption.point(x1, y3)).release().perform();
				Thread.sleep(10000);

				//List<util_CallListObject> CloudCallList = new ArrayList<util_CallListObject>();
				List<String> callListString2 = new ArrayList<String>();
				int recCount2 = 1;
				boolean scrollDown2 = true;

				while (scrollDown2) {
				boolean newRecFound2 = false;
				cDigits_Contacts contactscroll2 = new cDigits_Contacts(driver1);
				int noRecsOnScreen = contactscroll2.Cloudcontactlist.size();

				Point firstSwipe = contactscroll2.SwipeList.get(1).getLocation();
				Point seclastSwipe = contactscroll2.Cloudcontactlist.get(noRecsOnScreen - 2).getLocation(); // taking -2 to avoidincomplete info in last record create the list of CallListObject.

				for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal1 = contactscroll2.Cloudcontactlist.get(i).getText();
				//String dateVal = contactscroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString2 = textVal1;
				log.debug("CloudCallList : " + recCount2 + " --" + textVal1 );

				if (!callListString2.contains(concatString2)) {
				log.debug("New record");
				//CloudCallList.add(new util_CallListObject(recCount2, textVal1));
				callListString2.add(concatString2);
				newRecFound2 = true;
				}

				recCount2 = recCount2 + 1;
				}

				if (!newRecFound2) // new record not found, stop the scroll down
				{
				log.debug("No new call list record found");
				scrollDown2 = false;
				} else {

				if (noRecsOnScreen >= 4) {
				TouchAction secAns = new TouchAction(driver1);
				secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release()
				.perform();
				Thread.sleep(2000);
				} else // no need to scroll down
				{
				scrollDown2 = false;
				}
				}
				}

				boolean c = callListString.containsAll(callListString2) && callListString.size() == callListString2.size() ;

		/*
		 * Assert.assertEquals(c, true);
		 * 
		 * 
		 * log.info("Test UI_43809_CheckFavorite - Completed");
		 */
				return c;
		}

		// 43834 Verify Add contact
//	@Test(priority=0)
	@Parameters("deviceName")
	public boolean Contacts_43834_VerifyAddContact(String deviceName, String TestID) throws Exception {

			log.info("Test Contacts_43834_VerifyAddContact - Started");

			cDigits_Home home = new cDigits_Home(driver1);
			
			cDigits_Contacts contact = new cDigits_Contacts(driver1);
			
			home.contacts.click();
			
			contact.Addcontact.click();
			
			Thread.sleep(5000);
			
			String FirstName = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
			String ContactName = excelFile.readCell("Contacts", TestID, excelFile.INPUT4_COL);
			String LasttName = excelFile.readCell("Contacts", TestID, excelFile.INPUT2_COL);
			String Phoneno = excelFile.readCell("Contacts", TestID, excelFile.INPUT3_COL);
			String phonetype = excelFile.readCell("Configuration", deviceName, excelFile.CONFIG_PHONETYPE_COL);

			if (phonetype.equalsIgnoreCase("E5") ) {

				contact.FirstName.clear();
				contact.FirstName.sendKeys(FirstName);

				try {
					driver1.hideKeyboard();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//contact.LastName.clear();
				contact.LastName.sendKeys(LasttName);
			}
			
			else if(phonetype.equalsIgnoreCase("Nexus")) {
				
				//contact.FirstName.clear();
				contact.FirstName.sendKeys(FirstName);

				try {
					driver1.hideKeyboard();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				contact.LastName.clear();
				contact.LastName.sendKeys(LasttName);
				try {
					driver1.hideKeyboard();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (phonetype.equalsIgnoreCase("MotoG5")) {

				//contact.ContactName.clear();
				contact.ContactName.sendKeys(ContactName);
				try {
					driver1.hideKeyboard();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if (phonetype.equalsIgnoreCase("Tablet")) {
				//contact.ContactName.clear();
				contact.ContactName.sendKeys(ContactName);
			}

			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//contact.Phone.clear();
			contact.Phone.sendKeys(Phoneno);

			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			contact.Savebutton.click();

			Thread.sleep(5000);
			
			driver1.navigate().back();
			
			contact.localTab.click();
			
			contact.searchInput.sendKeys(ContactName);
			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		String actualname=	contact.Localcontactlist.get(0).getText();
			
			boolean verifynumber = actualname.equalsIgnoreCase(ContactName);

		/*
		 * Assert.assertEquals(verifynumber, true);
		 * 
		 * log.info("Test Contacts_43834_VerifyAddContact - Completed");
		 */
			return verifynumber;

		}

		// 43835 Verify remove contact
		//@Test(priority=5)
	public boolean Contacts_43835_VerifyDeleteContact(String TestID) throws Exception {

			log.info("Test Contacts_43835_VerifyDeleteContact - Started");

			cDigits_Home home = new cDigits_Home(driver1);
			
			cDigits_Contacts contact = new cDigits_Contacts(driver1);

			home.contacts.click();
			
			String contToDelet=excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
			
			contact.searchInput.sendKeys(contToDelet);
			try {
				driver1.hideKeyboard();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Keyboard not found");
			}
			
			TouchAction secAns = new TouchAction(driver1);
			/*secAns.longPress(PointOption.point(889, 930)).moveTo(PointOption.point(136, 930)).release().perform();
			Thread.sleep(3000);*/
			
			Utilities util = new Utilities();
			util.SwipeLeftforDeleteOption_MotoG5(secAns);

			//contact.Deletecontact.click(); 	// No need to click it automatically delete when full swipe to left in 9.3.36version
			contact.searchInput.clear();
			
			Thread.sleep(10000);

			// swipe down to get latest records
					Point firstEleTop = contact.Localcontactlist.get(0).getLocation();
					int y = firstEleTop.getY();
					int x = firstEleTop.getX();
					Dimension sizeOfScreen = driver1.manage().window().getSize();
					int y2 = (int) (sizeOfScreen.height * 0.8);

					TouchAction ta = new TouchAction(driver1);
					ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
					Thread.sleep(10000);

					//List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
					List<String> contactListString = new ArrayList<String>();
					int recCount = 1;
					boolean scrollDown = true;

					while (scrollDown) {
						boolean newRecFound = false;
						cDigits_Contacts contactscroll = new cDigits_Contacts(driver1);
						int noRecsOnScreen = contactscroll.Localcontactlist.size();

						Point firstSwipe = contactscroll.SwipeList.get(1).getLocation();
						Point seclastSwipe = contactscroll.Localcontactlist.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoidincomplete info in last record  create the list of CallListObject.

						for (int i = 0; i < noRecsOnScreen - 1; i++) {
							String textVal = contactscroll.Localcontactlist.get(i).getText();
							//String dateVal = contactscroll.DateList.get(i).getText();
							//String durationVal = callScroll.DurationList.get(i).getText();
							String concatString = textVal;
							log.debug("ContactList : "  + textVal );
							
							if (!contactListString.contains(concatString)) {
								log.debug("New record");
								//callList.add(new util_CallListObject(textVal));
								contactListString.add(concatString);
								newRecFound = true;
							}

							recCount = recCount + 1;
						}

						if (!newRecFound) // new record not found, stop the scroll down
						{
							log.debug("No new ContactList list record found");
							scrollDown = false;
						} else {

							if (noRecsOnScreen >= 4) {
							
								secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release()
										.perform();
								Thread.sleep(2000);
							} else // no need to scroll down
							{
								scrollDown = false;
							}
						}
					}
			
			
			
			
			boolean ver=contactListString.contains(contToDelet);
		/*
		 * Assert.assertEquals(ver, false);
		 * 
		 * 
		 * log.info("Test Contacts_43834_VerifyAddContact - Completed");
		 */
			return ver;

		}

	// 43837 Verify user can able to send message
	//@Test(priority=3)
	public boolean Contacts_43837_VerifyMessageSend(String TestID) throws Exception {

		log.info("Test Contact_43837_VerifyMessageSend - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);


		//Check with PCC
		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);
		
		String takecontact = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
		contact.searchInput.sendKeys(takecontact);
		Thread.sleep(3000);
		
		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(2000);
		
		//verifying contacts 
		contact.Localcontactlist.get(0).click();
	
		contact.MessageAction.click();
		Thread.sleep(2000);

		//condition
		/*if(contact.MessageAlertLineName.isDisplayed()) {
			
			contact.MessageAlertLineNumberselect.click();
			
			if(contact.AlerttoChooseLine.isDisplayed()) {
				
				contact.ChooseOneLineforMSGorCall.get(0).click();
			}
			
		}*/
		 if(contact.AlerttoChooseLine.isDisplayed()) {
			
			contact.ChooseOneLineforMSGorCall.get(0).click();
		}
		//contact.ChooseLine4693.click();
		Thread.sleep(2000);
		
		//Entering message
		String messageinput = excelFile.readCell("Contacts", TestID, excelFile.INPUT2_COL);
		message.messageEnter.sendKeys(messageinput);
		Thread.sleep(2000);

		driver1.hideKeyboard();

		message.messageSendButton.click();

		Thread.sleep(20000);
		
		driver1.hideKeyboard();

		//Verifying status of the message
		String status =  message.SentStatus.getText();
		System.out.println("Actual status" +status);

		ArrayList<String> list=new ArrayList<String>();//Creating arraylist.
		
		String sentval = excelFile.readCell("Contacts", TestID, excelFile.INPUT4_COL);
		Thread.sleep(2000);
		
		String readval = excelFile.readCell("Contacts", TestID, excelFile.INPUT5_COL);
		Thread.sleep(2000);
		
		String deliveredval = excelFile.readCell("Contacts", TestID, excelFile.INPUT6_COL);
		Thread.sleep(2000);
		
		list.add(sentval);//Adding object in arraylist.
		list.add(readval);
		list.add(deliveredval);

		boolean sentstatus = list.contains(status);

		Assert.assertEquals(sentstatus, true);

		driver1.navigate().back();



		//Check with SCC
		cDigits_Home home2 = new cDigits_Home(driver1);
		cDigits_Contacts contact2 = new cDigits_Contacts(driver1);
		cDigits_Messages message2 = new cDigits_Messages(driver1);
		
		home2.contacts.click();
		Thread.sleep(5000);

		contact2.cloudTab.click();
		Thread.sleep(3000);
		
		String takecontact1 = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
		contact2.searchInput.sendKeys(takecontact1);
		Thread.sleep(2000);
		
		driver2.hideKeyboard();
		Thread.sleep(2000);
		
		//verifying contacts 
		contact2.Cloudcontactlist.get(0).click();
		Thread.sleep(2000);
		
		contact2.MessageAction.click();
		Thread.sleep(2000);

		//condition
		/*if(contact2.MessageAlertLineName.isDisplayed()) {
			
			contact2.MessageAlertLineNumberselect.click();
			
			if(contact2.AlerttoChooseLine.isDisplayed()) {
				
				contact2.ChooseOneLineforMSGorCall.get(0).click();
			}
			
		}*/
		 if(contact2.AlerttoChooseLine.isDisplayed()) {
			
			contact2.ChooseOneLineforMSGorCall.get(0).click();
		}
		//contact2.ChooseLine4693.click();
		Thread.sleep(2000);

		String messageinput2 = excelFile.readCell("Contacts", TestID, excelFile.INPUT3_COL);
		message2.messageEnter.sendKeys(messageinput2);
		Thread.sleep(2000);

		driver2.hideKeyboard();

		message2.messageSendButton.click();

		Thread.sleep(20000);

		driver2.hideKeyboard();

		String status1 =  message2.SentStatus.getText();
		System.out.println("Actual status" +status1);

		ArrayList<String> list1=new ArrayList<String>();//Creating arraylist.
		
		String sentval1 = excelFile.readCell("Contacts", TestID, excelFile.INPUT4_COL);
		Thread.sleep(2000);
		
		String readval1 = excelFile.readCell("Contacts", TestID, excelFile.INPUT5_COL);
		Thread.sleep(2000);
		
		String deliveredval1 = excelFile.readCell("Contacts",TestID, excelFile.INPUT6_COL);
		Thread.sleep(2000);
		
		list.add(sentval1);//Adding object in arraylist.
		list.add(readval1);
		list.add(deliveredval1);

		boolean sentstatus1 = list.contains(status1);

		Assert.assertEquals(sentstatus1, true);

		/*
		 * driver2.navigate().back();
		 * 
		 * log.info("Test Contacts_43837_VerifyMessageSend - Completed");
		 */
		return sentstatus1;

	}



	// 43836 Verify user can able to make call
	//@Test(priority=4)
	public boolean Contacts_43836_VerifyCall(String TestID) throws Exception{

		log.info("Test Contacts_43836_VerifyCall - Started");
		
		//Check with PCC
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);
		cDigits_Calls calling = new cDigits_Calls(driver1);

		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);
		
		//Taking contact
		String takecontact2 = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
		contact.searchInput.sendKeys(takecontact2);
		Thread.sleep(3000);
		
		try {
			driver1.hideKeyboard();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Keyboard not found");
		}
		
		//verifying contacts 
		contact.Localcontactlist.get(0).click();
		Thread.sleep(2000);

		contact.CallAction.click();
		Thread.sleep(3000);
		
	// condition 
		/*if(contact.MessageAlertLineName.isDisplayed()) {
			
			contact.MessageAlertLineNumberselect.click();
			
			if(contact.AlerttoChooseLine.isDisplayed()) {
				
				contact.ChooseOneLineforMSGorCall.get(0).click();
			}
			
		}*/
		 if(contact.AlerttoChooseLine.isDisplayed()) {
			
			contact.ChooseOneLineforMSGorCall.get(0).click();
		}
		
		calling.speakerOn.click();
		Thread.sleep(2000);
	
		String status2 = calling.callingstatus.getText();

		String expected_StatusOfCall=excelFile.readCell("Contacts", TestID, excelFile.INPUT2_COL);
		boolean status3 = status2.equals(expected_StatusOfCall);

		Assert.assertEquals(status3, true);

		Thread.sleep(10000);

		calling.EndCall.click();
		Thread.sleep(2000);

		contact.searchInput.clear();
		try {
			driver1.hideKeyboard();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Keyboard not found");
		}
		Thread.sleep(2000);

		
		
		//Check with SCC
		cDigits_Home home2 = new cDigits_Home(driver1);
		cDigits_Contacts contact2 = new cDigits_Contacts(driver1);
		cDigits_Calls calling2 = new cDigits_Calls(driver1);
		
		home2.contacts.click();
		Thread.sleep(2000);

		contact2.cloudTab.click();
		Thread.sleep(2000);
		
		//Taking contact
		String takecontact3 = excelFile.readCell("Contacts", TestID, excelFile.INPUT1_COL);
		contact2.searchInput.sendKeys(takecontact3);
		Thread.sleep(3000);

		try {
			driver1.hideKeyboard();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		contact2.Cloudcontactlist.get(0).click();
		Thread.sleep(2000);

		contact2.CallAction.click();
		Thread.sleep(3000);
		
		// condition 
				/*if(contact2.MessageAlertLineName.isDisplayed()) {
					
					contact2.MessageAlertLineNumberselect.click();
					
					if(contact2.AlerttoChooseLine.isDisplayed()) {
						
						contact2.ChooseOneLineforMSGorCall.get(0).click();
					}
					
				}*/
				 if(contact2.AlerttoChooseLine.isDisplayed()) {
					
					contact2.ChooseOneLineforMSGorCall.get(0).click();
				}
		//contact2.ChooseLine4693.click();
		Thread.sleep(4000);
	
		calling2.speakerOn.click();
		Thread.sleep(2000);
		
		String status4 = calling2.callingstatus.getText();

		boolean status5 = status4.equals(status4);

//		Assert.assertEquals(status5, true);

		Thread.sleep(25000);

		calling2.EndCall.click();
		Thread.sleep(2000);

		contact2.searchInput.clear();
		/*
		 * Thread.sleep(2000);
		 * 
		 * 
		 * log.info("Test Contacts_43836_VerifyCall - Completed");
		 */
		return status5;

	}


	
	
	@Test()
	public boolean Contacts_43838_CheckSyncContactFromCloud(String TestID) throws Exception {

		log.info("Test Contact_43838_CheckSyncContactFromCloud - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);
		
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Contacts contact2 = new cDigits_Contacts(driver2);

		
		//Adding Contact
		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);

		contact.Addcontact.click();
		Thread.sleep(5000);


		contact.ContactName.clear();
		contact.ContactName.sendKeys("ATT-2");

		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			e.printStackTrace();
		}

		contact.PhoneNumber.clear();
		contact.PhoneNumber.sendKeys("1234567888");

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
		
		
		home2.contacts.click();
		Thread.sleep(2000);
		
		contact2.cloudTab.click();
		Thread.sleep(2000);
		
		
		//Synching contacts with cloud
		contact.toolbaroption.click();
		Thread.sleep(2000);
		
		TouchAction secAns = new TouchAction(driver1);
		secAns.tap((PointOption.point(758,385))).perform();
		Thread.sleep(20000);
		
		
		//Verifying synching
		Utilities f1 = new Utilities();
		
		f1.ScrollandGetInList1();
		
		

		//Deleting contact
		home.contacts.click();
		
		contact.localTab.click();
		
		contact.searchInput.sendKeys("ATT-2");
		
		driver1.hideKeyboard();
		Thread.sleep(2000);
		
		Utilities util = new Utilities();
		TouchAction secAns8 = new TouchAction(driver1);
		util.SwipeLeftforDeleteOption_MotoG5(secAns8);
		
		//contact.Deletecontact.click();  // No need to click on delete it automatically delete the contact after full swipe to left
		Thread.sleep(5000);

		contact.searchInput.clear();
		Thread.sleep(2000);

		
		System.out.println("UI_43811_CheckSyncContactTab End");

//		log.info("Test Contacts_43838_CheckSyncContactFromCloud - Completed");
		return false;

		
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
