package cDigitsTC_Messaging;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import supportForTC.BeforeAfterTest;

public class MessagingTC_AndroidPhone {
	
	public static final Logger log = Logger.getLogger(MessagingTC_AndroidPhone.class);
	
	
	@Test
	public void Messaging_48282_SendTextMessageMOReceivedMT1to1() throws IOException, InterruptedException
	{
			log.info("Messaging_48282_SendTextMessageMOReceivedMT1to1 - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.SendTextMessage1to1("48282");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_48282_SendTextMessageMOReceivedMT1to1 - Completed");
	         
	}
	
	@Test
	public void Messaging_49661_SendPageModeTextMessageMOReceivedMT1to1() throws IOException, InterruptedException
	{
			log.info("Messaging_49661_SendPageModeTextMessageMOReceivedMT1to1 - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.SendPageModeTextMessage1to1("49661");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_49661_SendPageModeTextMessageMOReceivedMT1to1 - Completed");
	         
	}
	
	@Test
	public void Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1() throws IOException, InterruptedException
	{
			log.info("Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1 - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.SendLargeModeTextMessage1to1("48958");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1 - Completed");
	         
	}
	
	
	@Test
	public void Messaging_44939_Verify_PictureFormat_MO() throws Exception
	{
			log.info("Messaging_44939_Verify_PictureFormat_MO - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.Messaging_44939_VerifyMOPictureFormat("44939");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_44939_Verify_PictureFormat_MO - Completed");
	         
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
