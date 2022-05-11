package cDigitsTC_Misc;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import supportForTC.BeforeAfterTest;

public class Misc_TEST_Tablet {

	public static final Logger log = Logger.getLogger(Misc_TEST_Tablet.class);
	
	@Test
	public void Misc_219_CheckSelectNumberOptionMSGTab() throws Exception
	{
			log.info("Misc_219_CheckSelectNumberOptionMSGTab - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_219_CheckSelectNumberOptionMSGTab("219");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_219_CheckSelectNumberOptionMSGTab - Completed");
	         
	}
	
	@Test
	public void Misc_220_CheckSelectNumberOptionCALLTab() throws Exception
	{
			log.info("Misc_220_CheckSelectNumberOptionCALLTab - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_220_CheckSelectNumberOptionCALLTab("220");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_220_CheckSelectNumberOptionCALLTab - Completed");
	         
	}
	
	@Test
	public void Misc_95_CheckTabSearchContactByFirstName() throws Exception
	{
			log.info("Misc_95_CheckTabSearchContactByFirstName - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_95_CheckTabSearchContactByFirstName("95");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_95_CheckTabSearchContactByFirstName - Completed");
	         
	}
	
	@Test
	public void Misc_96_CheckTabSearchContactByLastName() throws Exception
	{
			log.info("Misc_96_CheckTabSearchContactByLastName - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_96_CheckTabSearchContactByLastName("96");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_96_CheckTabSearchContactByLastName - Completed");
	         
	}
	
	@Test
	public void Misc_97_CheckTabSearchContactByNumber() throws Exception
	{
			log.info("Misc_97_CheckTabSearchContactByNumber - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_97_CheckTabSearchContactByNumber("97");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_97_CheckTabSearchContactByNumber - Completed");
	         
	}
	
	@Test
	public void Misc_25_VerifyLineFilterForMessagesTab() throws Exception
	{
			log.info("Misc_25_VerifyLineFilterForMessagesTab - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_25_VerifyLineFilterForMessagesTab("25");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_25_VerifyLineFilterForMessagesTab - Completed");
	         
	}
	
	@Test
	public void Misc_26_VerifyLineFilterForCallsTab() throws Exception
	{
			log.info("Misc_26_VerifyLineFilterForCallsTab - Started");
		
			Misc_TabletTests_Util misctabutil = new Misc_TabletTests_Util();
			boolean flagMsgReceived = misctabutil.Misc_26_VerifyLineFilterForCallsTab("26");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_26_VerifyLineFilterForCallsTab - Completed");
	         
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
