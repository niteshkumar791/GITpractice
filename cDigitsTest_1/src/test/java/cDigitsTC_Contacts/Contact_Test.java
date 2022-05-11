package cDigitsTC_Contacts;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cDigitsTC_Misc.Misc_TEST_Tablet;
import cDigitsTC_Misc.Misc_TabletTests_Util;
import supportForTC.BeforeAfterTest;

public class Contact_Test {
	public static final Logger log = Logger.getLogger(Misc_TEST_Tablet.class);
	
	
	@Test
	public void Contacts_43839_AddRemoveFavorite() throws Exception
	{
			log.info("Misc_26_VerifyLineFilterForCallsTab - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43839_AddRemoveFavorite("43839");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43839_AddRemoveFavorite - Completed");
	         
	}
	
	
	@Test
	public void Contacts_46087_CheckForPCCSCCContact() throws Exception
	{
			log.info("Contacts_46087_CheckForPCCSCCContact - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_46087_CheckForPCCSCCContact("46087");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_46087_CheckForPCCSCCContact - Completed");
	         
	}
	
	@Test
	@Parameters("deviceName")
	public void Contacts_43834_VerifyAddContact() throws Exception
	{
			log.info("Contacts_43834_VerifyAddContact - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43834_VerifyAddContact("43834", "deviceName");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43834_VerifyAddContact - Completed");
	         
	}
	
	@Test
	public void Contacts_43835_VerifyDeleteContact() throws Exception
	{
			log.info("Contacts_43835_VerifyDeleteContact - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43835_VerifyDeleteContact("43835");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43835_VerifyDeleteContact - Completed");
	         
	}
	
	@Test
	public void Contacts_43837_VerifyMessageSend() throws Exception
	{
			log.info("Contacts_43837_VerifyMessageSend - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43837_VerifyMessageSend("43837");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43837_VerifyMessageSend - Completed");
	         
	}
	
	
	@Test
	public void Contacts_43836_VerifyCall() throws Exception
	{
			log.info("Contacts_43836_VerifyCall - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43836_VerifyCall("43836");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43836_VerifyCall - Completed");
	         
	}
	
	
	@Test
	public void Contacts_43838_CheckSyncContactFromCloud() throws Exception
	{
			log.info("Contacts_43838_CheckSyncContactFromCloud - Started");
		
			ContactsDetail_Tests_Util contactDetailsutil = new ContactsDetail_Tests_Util();
			boolean flagMsgReceived = contactDetailsutil.Contacts_43838_CheckSyncContactFromCloud("43838");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Contacts_43838_CheckSyncContactFromCloud - Completed");
	         
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
