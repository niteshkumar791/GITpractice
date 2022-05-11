package cDigitsTC_Misc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonClasses.base;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Messages;

/*
 * Misc_219_CheckSelectNumberOptionMSGTab
 * Misc_220_CheckSelectNumberOptionCALLTab
 * Misc_95_CheckTabSearchContactByFirstName
 * Misc_96_CheckTabSearchContactByLastName
 * Misc_97_CheckTabSearchContactByNumber
 * Misc_25_VerifyLineFilterForMessagesTab
 * Misc_26_VerifyLineFilterForCallsTab
 * 
 */
public class Misc_TabletTests_Util extends base{

	public static final Logger log = Logger.getLogger(Misc_TabletTests_Util.class);

	//@Test
	public boolean Misc_219_CheckSelectNumberOptionMSGTab(String TestID) throws Exception{


		cDigits_Home home= new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contactsTab.click();

		contact.favouriteTab.click();

		contact.G5contact.click();

		//contact.MessageActionTab.get(0).click();

		Thread.sleep(2000);

		String Type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String Type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String Type3=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String Type4=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		List<String> Typeofcontacts = new ArrayList<String>();
		List<String> Explist = new ArrayList<String>();

		Explist.add(Type1);
		Explist.add(Type2);
		Explist.add(Type3);
		Explist.add(Type4);

		int size = contact.TypeOfContactNumberTab.size();
		String textVal = null;

		for (int i = 0; i <= size-1; i++) {

			textVal = contact.TypeOfContactNumberTab.get(i).getText();

			Typeofcontacts.add(textVal);

		}

		System.out.println(Typeofcontacts);
		System.out.println(Explist);


		boolean ver=Explist.containsAll(Typeofcontacts);



//		driver1.navigate().back();  // To home


//		Assert.assertEquals(ver, true);
		return ver;

	}


	//@Test
	public boolean Misc_220_CheckSelectNumberOptionCALLTab(String TestID) throws Exception{

		cDigits_Home home= new cDigits_Home(driver1);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		home.contactsTab.click();

		contact.favouriteTab.click();

		contact.G5contact.click();

		//contact.CallActionTab.get(0).click();

		Thread.sleep(2000);

		String Type1=excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		String Type2=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String Type3=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);
		String Type4=excelFile.readCell("Misc", TestID, excelFile.INPUT2_COL);

		List<String> Typeofcontacts = new ArrayList<String>();
		List<String> Explist = new ArrayList<String>();

		Explist.add(Type1);
		Explist.add(Type2);
		Explist.add(Type3);
		Explist.add(Type4);

		int size = contact.TypeOfContactNumberTab.size();
		String textVal = null;

		for (int i = 0; i <= size-1; i++) {

			textVal = contact.TypeOfContactNumberTab.get(i).getText();

			Typeofcontacts.add(textVal);

		}

		System.out.println(Typeofcontacts);
		System.out.println(Explist);


		boolean ver=Typeofcontacts.containsAll(Explist);



		/*
		 * driver1.navigate().back(); // To home
		 * 
		 * 
		 * Assert.assertEquals(ver, true);
		 */
		return ver;

	}
	
	
	@Test
	public boolean Misc_95_CheckTabSearchContactByFirstName(String TestID) throws Exception {

		log.info("Test Misc_95_CheckTabSearchContactByFirstName - Started");

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
		 * log.info("Test Misc_95_CheckTabSearchContactByFirstName- Completed");
		 */
		return contact1;


	}

	@Test
	public boolean Misc_96_CheckTabSearchContactByLastName(String TestID) throws Exception {

		log.info("Test Misc_96_CheckTabSearchContactByLastName - Started");

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
		 * log.info("Test Misc_96_CheckTabSearchContactByLastName - Completed");
		 */
		return contact1;


	}

	@Test
	public boolean Misc_97_CheckTabSearchContactByNumber(String TestID) throws Exception {

		log.info("Test Misc_97_CheckTabSearchContactByNumber - Started");

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
		 * log.info("Test Misc_97_CheckTabSearchContactByNumber - Completed");
		 */
		return contact1;


	}
	
	
	@Test
	public boolean Misc_25_VerifyLineFilterForMessagesTab(String TestID) throws Exception {
		
		log.info("Test Misc_25_VerifyLineFilterForMessagesTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages message = new cDigits_Messages(driver1);
		cDigits_Messages message2 = new cDigits_Messages(driver3);

		home.newMessageIcon.click();
		
		String receipient1 = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		
		message.enterRecepient.sendKeys(receipient1);
		Thread.sleep(2000);
		
		message.receipentNamesList.get(0).click();
		Thread.sleep(1000);
		
		message.nextButton.click();
		Thread.sleep(1000);
		
		String msgToSend = excelFile.readCell("Misc", TestID, excelFile.INPUT3_COL);
		
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(2000);
			
		message.messageSendButton.click();
		Thread.sleep(15000);
		
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Line4303.click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev2 = new cDigits_Messages(driver3);
		int msgsNo1 = messagesDev2.textMessagesReceived.size();
		boolean msg2 = false;
		
		for(int i=0; i<msgsNo1-1; i++) {
			
			String msgReceived2 = messagesDev2.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived2);	
			msg2 = msgToSend.equalsIgnoreCase(msgReceived2);
		}
		
		Assert.assertEquals(msg2, true);   
		Thread.sleep(2000);      
		
		message2.LinesSelection.click();
		Thread.sleep(2000);
		
		message2.Line9832.click();
		Thread.sleep(2000);
		
		message2.MsgList.get(0).click();
		Thread.sleep(2000);
		
		cDigits_Messages messagesDev21 = new cDigits_Messages(driver3);
		int msgsNo = messagesDev21.textMessagesReceived.size();
		boolean msg21 = false;
		
		for(int i=0; i<msgsNo-1; i++) {
			
			String msgReceived2 = messagesDev21.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived2);	
			msg21 = msgToSend.equalsIgnoreCase(msgReceived2);
		}
		
		/*
		 * Assert.assertEquals(msg21, false); Thread.sleep(2000);
		 * 
		 * driver1.navigate().back(); Thread.sleep(1000); driver1.navigate().back();
		 * Thread.sleep(2000);
		 * 
		 * log.info("Test Misc_25_VerifyLineFilterForMessagesTab - Completed");
		 */
		return msg21;
	}
	
	
	@Test
	public boolean Misc_26_VerifyLineFilterForCallsTab(String TestID) throws Exception {
		
		log.info("Test Misc_26_VerifyLineFilterForCallsTab - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Home home2 = new cDigits_Home(driver3);
		cDigits_Contacts contact = new cDigits_Contacts(driver1);
		cDigits_Calls calling = new cDigits_Calls(driver1);
		cDigits_Calls call = new cDigits_Calls(driver3);
		
		home.contacts.click();
		Thread.sleep(2000);

		contact.localTab.click();
		Thread.sleep(2000);
		
		//Taking contact
		String takecontact = excelFile.readCell("Misc", TestID, excelFile.INPUT1_COL);
		contact.searchInput.sendKeys(takecontact);
		Thread.sleep(2000);
		
		try {
			driver1.hideKeyboard();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//verifying contacts 
		contact.Localcontactlist.get(0).click();
		Thread.sleep(2000);

		contact.CallAction.click();
		Thread.sleep(20000);
		
		calling.EndCall.click();
		Thread.sleep(2000);

		contact.searchInput.clear();
		Thread.sleep(1000);
		
		driver1.navigate().back();
		Thread.sleep(2000);
		
		home2.calls.click();
		Thread.sleep(2000);
		
		call.LinesSelection.click();
		Thread.sleep(1000);
		
		call.Line4303.click();
		Thread.sleep(2000);  
		
		call.PhonesList.get(0).click();
		Thread.sleep(2000);
		
		String CallingNo = excelFile.readCell("Misc", TestID, excelFile.INPUT4_COL);
		String CallLogNo = call.CallNo.getText();
		System.out.println("Call Log Number : " + CallLogNo);
		
		boolean calllog = false;
		
		calllog = CallLogNo.equalsIgnoreCase(CallingNo);
		
		/*
		 * Assert.assertEquals(calllog, true); Thread.sleep(25000);
		 * 
		 * driver1.navigate().back();
		 * 
		 * log.info("Test Misc_26_VerifyLineFilterForCallsTab - Completed");
		 */
		return calllog;
		 

	}
}
