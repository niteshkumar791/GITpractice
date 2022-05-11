package cDigitsTC_Misc;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cDigitsTC_Messaging.MessagesUtil;
import cDigitsTC_Messaging.MessagingTC_AndroidPhone;
import supportForTC.BeforeAfterTest;

public class Misc_TEST_AndroidPhone {
	public static final Logger log = Logger.getLogger(Misc_TEST_AndroidPhone.class);
	
	@Test
	public void Misc_211_CheckSelectNumberOptioninMSG()throws Exception
	{
			log.info("Misc_211_CheckSelectNumberOptioninMSG() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_211_CheckSelectNumberOptionMSG("211");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_211_CheckSelectNumberOptioninMSG - Completed");
	         
	}
	
	@Test
	public void Misc_212_CheckSelectNumberOptioninCALL()throws Exception
	{
			log.info("Misc_212_CheckSelectNumberOptioninCALL() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_212_CheckSelectNumberOptionCALL("212");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_212_CheckSelectNumberOptioninCALL - Completed");
	         
	}
	
	@Test
	public void Misc_69_checkCallLogOnAndroidPhone()throws Exception
	{
			log.info("Misc_69_checkCallLogOnAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_69_checkCallLogOnAndroidPhone("69");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_69_checkCallLogOnAndroidPhone - Completed");
	         
	}
	
	@Test
	public void Misc_70_checkCallLogOnTab()throws Exception
	{
			log.info("Misc_69_checkCallLogOnAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_70_checkCallLogOnTab("70");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_70_checkCallLogOnTab - Completed");
	         
	}
	
	@Test
	public void Misc_73_checkMsgLogAndroidPhone()throws Exception
	{
			log.info("Misc_73_checkMsgLogAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_73_checkMsgLogAndroidPhone("73");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_73_checkMsgLogAndroidPhone - Completed");
	         
	}
	
	@Test
	public void Misc_74_checkMsgLogAndroidTab()throws Exception
	{
			log.info("Misc_74_checkMsgLogAndroidTab() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_74_checkMsgLogAndroidTab("74");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_74_checkMsgLogAndroidTab - Completed");
	         
	}
	
	@Test
	public void Misc_9_checkPNSMsgLoginAndroidPhone()throws Exception
	{
			log.info("Misc_9_checkPNSMsgLoginAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_9_checkPNSMsgLoginAndroidPhone("9");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_9_checkPNSMsgLoginAndroidPhone - Completed");
	         
	}
	
	@Test
	public void Misc_11_checkPNSMsgLineActivatedAndroidPhone()throws Exception
	{
			log.info("Misc_11_checkPNSMsgLineActivatedAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_11_checkPNSMsgLineActivatedAndroidPhone("11");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_11_checkPNSMsgLineActivatedAndroidPhone - Completed");
	         
	}
	
	@Test
	public void Misc_80_checkPNSMsgLoginLineAndroidPhone()throws Exception
	{
			log.info("Misc_80_checkPNSMsgLoginLineAndroidPhone() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_80_checkPNSMsgLoginLineAndroidPhone("80");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_80_checkPNSMsgLoginLineAndroidPhone - Completed");
	         
	}
	
	@Test
	public void Misc_81_checkPNSMsgLoginLineAndroidTablet()throws Exception
	{
			log.info("Misc_81_checkPNSMsgLoginLineAndroidTablet() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_81_checkPNSMsgLoginLineAndroidTablet("81");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_81_checkPNSMsgLoginLineAndroidTablet - Completed");
	         
	}
	
	@Test
	public void Misc_92_CheckSearchContactByFirstName()throws Exception
	{
			log.info("Misc_92_CheckSearchContactByFirstName() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_92_CheckSearchContactByFirstName("92");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_92_CheckSearchContactByFirstName - Completed");
	         
	}
	
	@Test
	public void Misc_93_CheckSearchContactByLastName()throws Exception
	{
			log.info("Misc_93_CheckSearchContactByLastName() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_93_CheckSearchContactByLastName("93");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_93_CheckSearchContactByLastName - Completed");
	         
	}
	
	@Test
	public void Misc_94_CheckSearchContactByNumber()throws Exception
	{
			log.info("Misc_94_CheckSearchContactByNumber() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_94_CheckSearchContactByNumber("93");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_94_CheckSearchContactByNumber - Completed");
	         
	}
	
	@Test
	public void Misc_37_CheckAllCallLogs1()throws Exception
	{
			log.info("Misc_37_CheckAllCallLogs() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_37_CheckAllCallLogs("37");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_37_CheckAllCallLogs - Completed");
	         
	}
	
	@Test
	public void Misc_38_CheckMIssedCallLogs()throws Exception
	{
			log.info("Misc_38_CheckMIssedCallLogs() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_38_CheckMIssedCallLogs("38");
			Assert.assertEquals(flagMsgReceived, true, "Missed Call Not Found");
			
			log.info("Misc_38_CheckMIssedCallLogs - Completed");
	         
	}
	
	@Test
	public void Misc_39_CheckAllCallLogs()throws Exception
	{
			log.info("Misc_39_CheckAllCallLogs() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_39_CheckAllCallLogs("39");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_39_CheckAllCallLogs - Completed");
	         
	}
	
	@Test
	public void Misc_40_CheckMIssedCallLogs()throws Exception
	{
			log.info("Misc_40_CheckMIssedCallLogs() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_40_CheckMIssedCallLogs("40");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_40_CheckMIssedCallLogs - Completed");
	         
	}
	
	@Test
	public void Misc_195_VerifyGroupMessagePNSAndIndication()throws Exception
	{
			log.info("Misc_40_CheckMIssedCallLogs() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_195_VerifyGroupMessagePNSAndIndication("195");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_195_VerifyGroupMessagePNSAndIndication - Completed");
	         
	}
	
	@Test
	public void Misc_196_VerifyGroupMessagePNSAndIndicationTab()throws Exception
	{
			log.info("Misc_196_VerifyGroupMessagePNSAndIndicationTab() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_196_VerifyGroupMessagePNSAndIndicationTab("196");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_196_VerifyGroupMessagePNSAndIndicationTab - Completed");
	         
	}
	
	@Test
	public void Misc_199_VerifyPictureRecevied()throws Exception
	{
			log.info("Misc_199_VerifyPictureRecevied() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_199_VerifyPictureRecevied("199");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_199_VerifyPictureRecevied - Completed");
	         
	}
	
	@Test
	public void Misc_200_VerifyPictureReceviedTab()throws Exception
	{
			log.info("Misc_200_VerifyPictureReceviedTab() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_200_VerifyPictureReceviedTab("200");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_200_VerifyPictureReceviedTab - Completed");
	         
	}
	
	@Test
	public void Misc_203_VerifySenderParticipitant()throws Exception
	{
			log.info("Misc_203_VerifySenderParticipitant() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_203_VerifySenderParticipitant("203");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_203_VerifySenderParticipitant - Completed");
	         
	}
	
	@Test
	public void Misc_204_VerifySenderParticipitantTab()throws Exception
	{
			log.info("Misc_204_VerifySenderParticipitantTab() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_204_VerifySenderParticipitantTab("204");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_204_VerifySenderParticipitantTab - Completed");
	         
	}
	
	@Test
	public void Misc_88_VerifyEveryLineReceivedMessage()throws Exception
	{
			log.info("Misc_88_VerifyEveryLineReceivedMessage() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_88_VerifyEveryLineReceivedMessage("88");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_88_VerifyEveryLineReceivedMessage - Completed");
	         
	}
	
	@Test
	public void Misc_89_VerifyEveryLineReceivedMessageTab()throws Exception
	{
			log.info("Misc_89_VerifyEveryLineReceivedMessageTab() - Started");
		
			Misc_PhoneTests_Util miscutil = new Misc_PhoneTests_Util();
			boolean flagMsgReceived = miscutil.Misc_89_VerifyEveryLineReceivedMessageTab("89");
			Assert.assertEquals(flagMsgReceived, true);
			
			log.info("Misc_89_VerifyEveryLineReceivedMessageTab - Completed");
	         
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
