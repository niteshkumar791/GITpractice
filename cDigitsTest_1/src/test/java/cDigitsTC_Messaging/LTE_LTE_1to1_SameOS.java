package cDigitsTC_Messaging;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cdigitsTC_UI.Calls_Tests;
import commonClasses.base;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Messages;
import supportForTC.BeforeAfterTest;

/*
 *  	Messaging_48282_SendTextMessageMOReceivedMT1to1
 * 
 */
public class LTE_LTE_1to1_SameOS  extends base{

	public static final Logger log = Logger.getLogger(LTE_LTE_1to1_SameOS.class);
	
	//@Test
	public void Messaging_48282_SendTextMessageMOReceivedMT1to1() throws IOException, InterruptedException
	{
			log.info("Messaging_48282_SendTextMessageMOReceivedMT1to1 - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.SendTextMessage1to1("48282");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_48282_SendTextMessageMOReceivedMT1to1 - Completed");
	         
	}
	
	
	//@Test
	public void Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1() throws IOException, InterruptedException
	{
			log.info("Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1 - Started");
		
			MessagesUtil messageutil = new MessagesUtil();
			boolean flagMsgReceived = messageutil.SendTextMessage1to1("48958");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Messaging_48958_SendLargeModeTextMessageMOReceivedMT1to1 - Completed");
	         
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
