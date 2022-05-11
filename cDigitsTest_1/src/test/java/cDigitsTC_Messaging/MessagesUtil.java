package cDigitsTC_Messaging;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.google.common.collect.ImmutableMap;

import cdigitsTC_UI.Calls_Tests;
import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Messages;

public class MessagesUtil extends base {

	public static final Logger log = Logger.getLogger(Calls_Tests.class);

	
	
	
	//Delete previous message conversation
	
	public void DeleteConversation(String receipientName1, String receipientName2, AndroidDriver<AndroidElement> driver1, AndroidDriver<AndroidElement> driver2 ) {
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message= new cDigits_Messages(driver1);
		cDigits_Messages message2= new cDigits_Messages(driver2);
		
		// deleteing previuos messages Dev1 
		try {
			home.searchInput.sendKeys(receipientName1);
			
			AndroidTouchAction touch = new AndroidTouchAction (driver1);
			touch.longPress(LongPressOptions.longPressOptions()
					.withElement (ElementOption.element (message.MsgList.get(0))))
			.perform ();

			Thread.sleep(2000);
			
			
				message.deleteIcon.click();
			
			home.searchInput.clear();
			Thread.sleep(2000);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			home.searchInput.clear();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
// deleteing previuos messages Dev2
		
		try {
			home2.searchInput.sendKeys(receipientName2);
			
			AndroidTouchAction touch1 = new AndroidTouchAction (driver2);
			touch1.longPress(LongPressOptions.longPressOptions()
					.withElement (ElementOption.element (message2.MsgList.get(0))))
			.perform ();

			Thread.sleep(2000);
			
			
				message2.deleteIcon.click();
			
				
			
			home2.searchInput.clear();
			Thread.sleep(2000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Already Deleted");
		}
		
		try {
			home2.searchInput.clear();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

	}
	/*@Test
	public void Messaging_43899_TestSendMessage() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub

		System.out.println("UI_43808_CheckContactsTabs Start");

		cDigits_Home home = new cDigits_Home(driver1);
		home.newMessageIcon.click();

		String receipient = excelFile.readCell("Messaging", "43899", excelFile.INPUT1_COL);
		String msgToSend = excelFile.readCell("Messaging", "43899", excelFile.INPUT2_COL);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String strDateTime = dtf.format(now);
		msgToSend = msgToSend + " " + strDateTime;

		cDigits_Messages messages = new cDigits_Messages(driver1);
		//messages.enterRecepient.sendKeys(receipient);
		driver1.navigate().back();  //to hide keyboard
		Thread.sleep(2000);
		driver1.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + receipient +"\"));");
		Thread.sleep(2000);

		messages.verveba56_2Receipient.click();
		Thread.sleep(1000);
		messages.nextButton.click();
		Thread.sleep(1000);
		messages.messageEnter.sendKeys(msgToSend);
		Thread.sleep(1000);

		messages.messageSendButton.click();
		Thread.sleep(5000);

		cDigits_Home homeDev2 = new cDigits_Home(driver2);
		homeDev2.messagesFrom3986List.get(0).click();

		cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);
		int msgsNo = messagesDev2.textMessagesReceived.size();
		boolean flagMsgReceived = false;


		for(int i=0; i<msgsNo; i++) {

			String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
			System.out.println("msgReceived : " + msgReceived);

			if(msgToSend.equalsIgnoreCase(msgReceived))
			{
				flagMsgReceived = true;
				break;
			}
		}




		driver1.navigate().back();  
		//driver1.navigate().back();

		driver2.navigate().back();


		Thread.sleep(5000);
		String currAct = driver1.currentActivity();
		System.out.println("currAct : " + currAct);

		String currAct2 = driver2.currentActivity();
		System.out.println("currAct2 : " + currAct2);

	}*/


	public boolean SendTextMessage1to1(String TestID) throws IOException, InterruptedException
	{
		log.info("SendTextMessage1to1");
		boolean result = false;
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message= new cDigits_Messages(driver1);
		cDigits_Messages message2= new cDigits_Messages(driver2);
		
		String receipient = excelFile.readCell("Messaging", TestID, excelFile.INPUT1_COL);
		String msgToSend = excelFile.readCell("Messaging", TestID, excelFile.INPUT2_COL);
		
		String receipientName1=excelFile.readCell("Messaging", TestID, excelFile.INPUT4_COL);
		String receipientName2=excelFile.readCell("Messaging", TestID, excelFile.INPUT5_COL);

			
		//Delete previus conversation from dev1 and dev2....
		
		DeleteConversation(receipientName1, receipientName2, driver1, driver2);
		
		/*
		 * // deleteing previuos messages Dev1 try {
		 * home.searchInput.sendKeys(receipientName1);
		 * 
		 * AndroidTouchAction touch = new AndroidTouchAction (driver1);
		 * touch.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message.deleteIcon.click();
		 * 
		 * home.searchInput.clear(); Thread.sleep(2000); } catch (Exception e2) { //
		 * TODO Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * try { home.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * // deleteing previuos messages Dev2
		 * 
		 * try { home2.searchInput.sendKeys(receipientName2);
		 * 
		 * AndroidTouchAction touch1 = new AndroidTouchAction (driver2);
		 * touch1.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message2.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message2.deleteIcon.click();
		 * 
		 * 
		 * 
		 * home2.searchInput.clear(); Thread.sleep(2000); } catch (Exception e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace();
		 * System.out.println("Already Deleted"); }
		 * 
		 * try { home2.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 */
				
	
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String strDateTime = dtf.format(now);
		msgToSend = msgToSend + " " + strDateTime;


		cDigits_Messages messages = new cDigits_Messages(driver1);
		home.messages.click();
		messages.newMessageIcon.click();
		Thread.sleep(2000);

		messages.enterRecepient.sendKeys(receipient);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

		messages.nextButton.click();
		Thread.sleep(2000);
		messages.messageEnter.sendKeys(msgToSend);
		messages.messageSendButton.click();
		Thread.sleep(10000);
		
		home2.messages.click();

		cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

		messagesDev2.MsgList.get(0).click();
		boolean flagMsgReceived= false;
		int msgno = messagesDev2.textMessagesReceived.size();
		log.info("msgR cnt : " + msgno);
		for(int i=0; i<msgno; i++) {

			String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
			log.info("msgReceived 1 : " + msgReceived);

			if(msgToSend.equalsIgnoreCase(msgReceived))
			{
				log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
				result = true;
				/*Thread.sleep(5000);
					//delete the message that was sent
					messagesDev2.textMessagesReceived.get(i).getLocation();

					Thread.sleep(2000);
					messagesDev2.deleteIcon.click();
					driver2.navigate().back(); */
				break;
			}
		}

		//delete message from MO
		/*int msgnoMO = messages.textMessagesReceived.size();
	        messages.textMessagesReceived.get(msgnoMO-1).click();
			Thread.sleep(2000);
			messages.deleteIcon.click();*/

		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//driver1.navigate().back();
		System.out.println("SendTextMessage1to1 returns "+result);
		return result;


	}


	//@Test
	public boolean SendPageModeTextMessage1to1(String TestID) throws IOException, InterruptedException{

		log.info("SendPageModeTextMessage1to1");
		boolean result = false;
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Home home2 = new cDigits_Home(driver2);
		cDigits_Messages message = new cDigits_Messages(driver1);	
		cDigits_Messages message2= new cDigits_Messages(driver2);	
		String receipient = excelFile.readCell("Messaging", TestID, excelFile.INPUT1_COL);
		String msgToSend = excelFile.readCell("Messaging", TestID, excelFile.INPUT2_COL);

		
		String receipientName1=excelFile.readCell("Messaging", TestID, excelFile.INPUT4_COL);
		String receipientName2=excelFile.readCell("Messaging", TestID, excelFile.INPUT5_COL);

			
		//Delete previus conversation from dev1 and dev2....
		
				DeleteConversation(receipientName1, receipientName2, driver1, driver2);
		
		/*
		 * // deleteing previuos messages Dev1 try {
		 * home.searchInput.sendKeys(receipientName1);
		 * 
		 * AndroidTouchAction touch = new AndroidTouchAction (driver1);
		 * touch.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message.deleteIcon.click();
		 * 
		 * home.searchInput.clear(); Thread.sleep(2000); } catch (Exception e2) { //
		 * TODO Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * try { home.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * // deleteing previuos messages Dev2
		 * 
		 * try { home2.searchInput.sendKeys(receipientName2);
		 * 
		 * AndroidTouchAction touch1 = new AndroidTouchAction (driver2);
		 * touch1.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message2.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message2.deleteIcon.click();
		 * 
		 * 
		 * 
		 * home2.searchInput.clear(); Thread.sleep(2000); } catch (Exception e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace();
		 * System.out.println("Already Deleted"); }
		 * 
		 * try { home2.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 */
				
		
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String strDateTime = dtf.format(now);
		msgToSend = msgToSend + " " + strDateTime;

		
		home.messages.click();
		message.newMessageIcon.click();
		Thread.sleep(2000);
		
		message.enterRecepient.sendKeys(receipient);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		Thread.sleep(1000);
		
		message.nextButton.click();
		Thread.sleep(2000);
		message.messageEnter.sendKeys(msgToSend);
		Thread.sleep(1000);

		message.messageSendButton.click();
		Thread.sleep(10000);

		cDigits_Home homeDev2 = new cDigits_Home(driver2);
		homeDev2.messages.click();
		cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);
		
		messagesDev2.MsgList.get(0).click();
		int msgsNo = messagesDev2.textMessagesReceived.size();

		String Viewmore="view more";
		int size=messagesDev2.textMessagesReceived.size();
		Point firstEleTop = messagesDev2.textMessagesReceived.get(size-1).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver2);
		ta.longPress(PointOption.point(x, y2)).moveTo(PointOption.point(x, y)).release().perform();
		Thread.sleep(10000);

		messagesDev2.viewMore_PMmessage.click();
		Thread.sleep(3000);
		String msgReceived=messagesDev2.viewPMmessage.getText();
		System.out.println("Reeived msg is ***:   "+msgReceived);

		result=msgToSend.equalsIgnoreCase(msgReceived);

	
		try {
			driver1.hideKeyboard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver1.navigate().back();

		driver2.navigate().back();


		Thread.sleep(5000);	
		return result;

	}
	
	//@Test
	public boolean SendLargeModeTextMessage1to1(String TestID) throws IOException, InterruptedException
	{
			log.info("In SendLargeModeTextMessage1to1");

			cDigits_Home home = new cDigits_Home(driver1);
			cDigits_Home home2 = new cDigits_Home(driver2);
			cDigits_Messages message = new cDigits_Messages(driver1);
			cDigits_Messages message2 = new cDigits_Messages(driver2);
			String receipient = excelFile.readCell("Messaging", TestID, excelFile.INPUT1_COL);
			String msgToSend = excelFile.readCell("Messaging", TestID, excelFile.INPUT2_COL);

			String receipientName1=excelFile.readCell("Messaging", TestID, excelFile.INPUT4_COL);
			String receipientName2=excelFile.readCell("Messaging", TestID, excelFile.INPUT5_COL);

			//Delete previus conversation from dev1 and dev2....
			
			DeleteConversation(receipientName1, receipientName2, driver1, driver2);
				
		/*
		 * // deleteing previuos messages Dev1 try {
		 * home.searchInput.sendKeys(receipientName1);
		 * 
		 * AndroidTouchAction touch = new AndroidTouchAction (driver1);
		 * touch.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message.deleteIcon.click();
		 * 
		 * home.searchInput.clear(); Thread.sleep(2000); } catch (Exception e2) { //
		 * TODO Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * try { home.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * // deleteing previuos messages Dev2
		 * 
		 * try { home2.searchInput.sendKeys(receipientName2);
		 * 
		 * AndroidTouchAction touch1 = new AndroidTouchAction (driver2);
		 * touch1.longPress(LongPressOptions.longPressOptions() .withElement
		 * (ElementOption.element (message2.MsgList.get(0)))) .perform ();
		 * 
		 * Thread.sleep(2000);
		 * 
		 * 
		 * message2.deleteIcon.click();
		 * 
		 * 
		 * 
		 * home2.searchInput.clear(); Thread.sleep(2000); } catch (Exception e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace();
		 * System.out.println("Already Deleted"); }
		 * 
		 * try { home2.searchInput.clear(); } catch (Exception e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 */



			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			String strDateTime = dtf.format(now);
			msgToSend = msgToSend + " " + strDateTime;


			
			home.messages.click();
			message.newMessageIcon.click();
			Thread.sleep(2000);

			message.enterRecepient.sendKeys(receipient);
			driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

			message.nextButton.click();
			Thread.sleep(2000);
			message.messageEnter.sendKeys(msgToSend);
			message.messageSendButton.click();
			Thread.sleep(10000);
			//driver1.navigate().back(); 

			cDigits_Home home1 = new cDigits_Home(driver2);
			home1.messages.click();

			cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

			messagesDev2.MsgList.get(0).click();
			boolean flagMsgReceived= false;
			int msgno = messagesDev2.textMessagesReceived.size();
			log.info("msgR cnt : " + msgno);
	         for(int i=0; i<msgno; i++) {

				String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
				log.info("msgReceived 1 : " + msgReceived);

				if(msgToSend.equalsIgnoreCase(msgReceived))
				{
					log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
					flagMsgReceived = true;
					break;
				}
			}
	         System.out.println("****************************"+flagMsgReceived);
			 assertEquals(flagMsgReceived,true);
			return flagMsgReceived;


	}
	
	public boolean Messaging_44939_VerifyMOPictureFormat(String TestID) throws Exception{	

		log.info("Messaging_44939_VerifyMOPictureFormat - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		
		String receipient = excelFile.readCell("Messaging", TestID, excelFile.INPUT1_COL);
		String imageNo = excelFile.readCell("Messaging", TestID, excelFile.INPUT2_COL);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String strDateTime = dtf.format(now);

		cDigits_Messages messages = new cDigits_Messages(driver1);
		home.messages.click();
		messages.newMessageIcon.click();
		Thread.sleep(2000);
		messages.enterRecepient.sendKeys(receipient);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		Thread.sleep(1000);
		messages.nextButton.click();
		Thread.sleep(1000);

		
		Thread.sleep(1000);
		messages.ATTACHFILE.click();
		Thread.sleep(3000);

		messages.ATTACHFILEpicture.click();

		Thread.sleep(3000);

		messages.ImageList.get(Integer.parseInt(imageNo)).click();

		Thread.sleep(2000);

		messages.FileSelectedSend.click();

		Thread.sleep(5000);
		messages.messageSendButton.click();
		Thread.sleep(30000);

		int listmsg = messages.MsgImageList.size();
		WebElement i1=  messages.MsgImageList.get(listmsg-1);
		
		Thread.sleep(10000);
		cDigits_Home homeDev2 = new cDigits_Home(driver2);
		homeDev2.messages.click();
		cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);
		messagesDev2.MsgList.get(0).click();
		Thread.sleep(5000);
		int msgsNo = messagesDev2.MsgImageList.size();
		
		boolean flagMsgReceived = false;

		WebElement i2= messagesDev2.MsgImageList.get(msgsNo-1);

		Utilities utils= new Utilities();
		boolean result = utils.ImageComparision(i1,i2);
		
		return result;
	}

	/*
	@Test
	public void Messaging_48345_TestSendMessageMOReceivedMT1toN() throws IOException, InterruptedException
	{
			log.info("Messaging_48345_TestSendMessageMOReceivedMT");

			cDigits_Home home = new cDigits_Home(driver1);

			String receipient1 = excelFile.readCell("Messaging", "48345", excelFile.INPUT1_COL);
			String receipient2 = excelFile.readCell("Messaging", "48345", excelFile.INPUT2_COL);
			String msgToSend = excelFile.readCell("Messaging", "48345", excelFile.INPUT3_COL);


			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			String strDateTime = dtf.format(now);
			msgToSend = msgToSend + " " + strDateTime;


			cDigits_Messages messages = new cDigits_Messages(driver1);
			home.messages.click();
			messages.newMessageIcon.click();
			Thread.sleep(2000);

			messages.enterRecepient.sendKeys(receipient1);
			driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

			messages.enterRecepient.sendKeys(receipient2);
			driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

			messages.nextButton.click();
			Thread.sleep(2000);
			messages.messageEnter.sendKeys(msgToSend);
			messages.messageSendButton.click();
			Thread.sleep(10000);
			driver1.navigate().back(); 

			cDigits_Home home1 = new cDigits_Home(driver2);
			home1.messages.click();

			cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

			messagesDev2.MsgList.get(0).click();
			boolean flagMsgReceived= false;
			int msgno = messagesDev2.textMessagesReceived.size();
			log.info("msgR cnt : " + msgno);
	         for(int i=0; i<msgno; i++) {

				String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
				log.info("msgReceived 1 : " + msgReceived);

				if(msgToSend.equalsIgnoreCase(msgReceived))
				{
					log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
					flagMsgReceived = true;
					break;
				}
			}

	         driver2.navigate().back(); 
	         Thread.sleep(2000);

	         cDigits_Home home3 = new cDigits_Home(driver3);
				home3.messages.click();

				cDigits_Messages messagesDev3 = new cDigits_Messages(driver3);

	         messagesDev3.MsgList.get(0).click();
				boolean flagMsgReceived1= false;
				int msgno1 = messagesDev3.textMessagesReceived.size();
				log.info("msgR cnt : " + msgno1);
		         for(int i=0; i<msgno1; i++) {

					String msgReceived1 = messagesDev3.textMessagesReceived.get(i).getText();
					log.info("msgReceived 1 : " + msgReceived1);

					if(msgToSend.equalsIgnoreCase(msgReceived1))
					{
						log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived1);
						flagMsgReceived1 = true;
						break;
					}
				}

		         boolean flagMsgReceived3= false;

		         if(flagMsgReceived==true && flagMsgReceived1==true )
		         {
		        	 flagMsgReceived3=true;
		         }


	         System.out.println("****************************"+flagMsgReceived3);
			 assertEquals(flagMsgReceived3,true);


	}

	@Test
	public void Messaging_44959_FileSizeLargerThanMaximumAllowed() throws IOException, InterruptedException
	{
		log.info("Messaging_44959_FileSizeLargerThanMaximumAllowed ");
		cDigits_Home home = new cDigits_Home(driver1);

		String receipient1 = excelFile.readCell("Messaging", "44959", excelFile.INPUT1_COL);
		String ToastMsg = excelFile.readCell("Messaging", "44959", excelFile.INPUT1_COL);

		cDigits_Messages messages = new cDigits_Messages(driver1);
		home.messages.click();
		messages.newMessageIcon.click();
		Thread.sleep(2000);

		messages.enterRecepient.sendKeys(receipient1);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		messages.nextButton.click();
		Thread.sleep(2000);
		messages.MsgAttachmentfilebutton.click();
		Thread.sleep(3000);

		int size2=messages.ATTACHFILErecordaudio.size();

		Point firstEleTop2 = messages.ATTACHFILErecordaudio.get(size2-1).getLocation();
		int y3 = firstEleTop2.getY();
		int x2 = firstEleTop2.getX();
		Dimension sizeOfScreen2 = driver1.manage().window().getSize();
		int x4 = (int) (sizeOfScreen2.width * 0.2);



		TouchAction ta3 = new TouchAction(driver1);
		ta3.longPress(PointOption.point(x2, y3)).moveTo(PointOption.point(x4, y3)).release().perform();
		Thread.sleep(10000);




		messages.AttachVideobutton.click();
		messages.SelectVideo.get(0).click();
		messages.SelectVideo.get(1).click();
		messages.SelectVideo.get(2).click();
		messages.SelectVideo.get(3).click();
		 Thread.sleep(2000);

		 messages.SendMedia.click();
		Thread.sleep(2000);

		messages.messageSendButton.click();
//		Thread.sleep(100);	

		WebDriverWait wait = new WebDriverWait(driver1, 5);
		wait.until(ExpectedConditions.visibilityOf(driver1.findElement(By.xpath("//android.widget.Toast[1]"))));

		//WebElement toastView = driver1.findElement(By.xpath("//android.widget.Toast[1]"));
		//System.out.println("******toastViews****" +toastViews.size());

		//String text = toastViews.get(0).getAttribute("name");
		String text = driver1.findElement(By.xpath("//android.widget.Toast[1]")).getText();

		log.info("********************************************" +text);

		boolean flagMsgReceived=false;

		if(text.length()!=0)
		{
			flagMsgReceived=true;

		}

		assertEquals(flagMsgReceived,true);


	}



	@Test
	public void Messaging_44947_FileTransferIntruption() throws IOException, InterruptedException
	{

		log.info("Messaging_44947_FileTransferIntruption ");
		cDigits_Home home = new cDigits_Home(driver1);

		String receipient1 = excelFile.readCell("Messaging", "44947", excelFile.INPUT1_COL);
		String Status1 = excelFile.readCell("Messaging", "44947", excelFile.INPUT2_COL);
		String Status2 = excelFile.readCell("Messaging", "44947", excelFile.INPUT3_COL);
		String Status3 = excelFile.readCell("Messaging", "44947", excelFile.INPUT4_COL);

		cDigits_Messages messages = new cDigits_Messages(driver1);
		home.messages.click();
		messages.newMessageIcon.click();
		Thread.sleep(2000);

		messages.enterRecepient.sendKeys(receipient1);
		driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
		messages.nextButton.click();
		Thread.sleep(2000);
		messages.MsgAttachmentfilebutton.click();
		Thread.sleep(3000);

		messages.ImageAttachmentbutton.click();
		messages.SelectVideo.get(0).click();
		messages.SendMedia.click();
		Thread.sleep(2000);

		messages.messageSendButton.click();

		driver1.toggleWifi();

		String ErrorMsg =messages.MsgNotSentError.getText();
		assertEquals(ErrorMsg, "Error, file not sent.");

		driver1.toggleWifi();
		Thread.sleep(8000);


		int SentMsg=messages.SentMsgImageList.size();
		messages.SentMsgImageList.get(SentMsg-1).click();
		Thread.sleep(15000);


		String SentStatus =messages.SentStatus.getText();



		if(SentStatus.equalsIgnoreCase(Status1) || SentStatus.equalsIgnoreCase(Status2) || SentStatus.equalsIgnoreCase(Status3)) 
		{
			cDigits_Home home1 = new cDigits_Home(driver2);		
			cDigits_Messages messages1 = new cDigits_Messages(driver2);
			home1.messages.click();
			messages1.MsgList.get(0).click();

			int recievedMsg=messages1.ReceivedMsgImageList.size();

			String text=messages1.ReceivedMsgImageList.get(recievedMsg-1).getAttribute("content-desc");

			boolean verify=text.equalsIgnoreCase("Image Received");

			assertEquals(verify, true);
		}
	}


		@Test
		public void Messaging_44950_FileTransferToOfflineUser() throws IOException, InterruptedException
		{

			log.info("Messaging_44950_FileTransferToOfflineUser ");
			cDigits_Home home = new cDigits_Home(driver1);

			String receipient1 = excelFile.readCell("Messaging", "44950", excelFile.INPUT1_COL);
			String Status1 = excelFile.readCell("Messaging", "44950", excelFile.INPUT2_COL);
			String Status2 = excelFile.readCell("Messaging", "44950", excelFile.INPUT3_COL);
			String Status3 = excelFile.readCell("Messaging", "44950", excelFile.INPUT4_COL);

			cDigits_Messages messages = new cDigits_Messages(driver1);
			home.messages.click();
			messages.newMessageIcon.click();
			Thread.sleep(2000);

			driver2.toggleWifi();

			messages.enterRecepient.sendKeys(receipient1);
			driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
			messages.nextButton.click();
			Thread.sleep(2000);
			messages.MsgAttachmentfilebutton.click();
			Thread.sleep(3000);

			messages.ImageAttachmentbutton.click();
			messages.SelectVideo.get(0).click();
			messages.SendMedia.click();
			Thread.sleep(2000);

			messages.messageSendButton.click();
			Thread.sleep(15000);


			String SentStatus =messages.SentStatus.getText();



			if(SentStatus.equalsIgnoreCase(Status1) || SentStatus.equalsIgnoreCase(Status2) || SentStatus.equalsIgnoreCase(Status3)) 
			{

				driver2.toggleWifi();
				Thread.sleep(8000);

				cDigits_Home home1 = new cDigits_Home(driver2);		
				cDigits_Messages messages1 = new cDigits_Messages(driver2);
				home1.messages.click();
				Thread.sleep(8000);

				messages1.MsgList.get(0).click();

				int recievedMsg=messages1.ReceivedMsgImageList.size();

				String text=messages1.ReceivedMsgImageList.get(recievedMsg-1).getAttribute("content-desc");

				boolean verify=text.equalsIgnoreCase("Image Received");

				assertEquals(verify, true);
			}			

	}

		@Test
		public void Messaging_48284_TestSendMessageMOReceivedMT() throws IOException, InterruptedException
		{
				log.info("Messaging_48284_TestSendMessageMOReceivedMT");

				cDigits_Home home = new cDigits_Home(driver1);

				String receipient = excelFile.readCell("Messaging", "48284", excelFile.INPUT1_COL);
				String msgToSend = excelFile.readCell("Messaging", "48284", excelFile.INPUT2_COL);


				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				String strDateTime = dtf.format(now);
				msgToSend = msgToSend + " " + strDateTime;


				cDigits_Messages messages = new cDigits_Messages(driver1);
				home.messages.click();
				messages.newMessageIcon.click();
				Thread.sleep(2000);

				messages.enterRecepient.sendKeys(receipient);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.nextButton.click();
				Thread.sleep(2000);
				messages.messageEnter.sendKeys(msgToSend);
				messages.messageSendButton.click();
				Thread.sleep(10000);
				driver1.navigate().back(); 

				cDigits_Home home1 = new cDigits_Home(driver2);
				home1.messages.click();

				cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

				messagesDev2.MsgList.get(0).click();
				boolean flagMsgReceived= false;
				int msgno = messagesDev2.textMessagesReceived.size();
				log.info("msgR cnt : " + msgno);
		         for(int i=0; i<msgno; i++) {

					String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
					log.info("msgReceived 1 : " + msgReceived);

					if(msgToSend.equalsIgnoreCase(msgReceived))
					{
						log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
						flagMsgReceived = true;
						break;
					}
				}
		         System.out.println("****************************"+flagMsgReceived);
				 assertEquals(flagMsgReceived,true);


		}



		@Test
		public void Messaging_48331_TestSendMessageMOReceivedMT1toN() throws IOException, InterruptedException
		{
				log.info("Messaging_48331_TestSendMessageMOReceivedMT");

				cDigits_Home home = new cDigits_Home(driver1);

				String receipient1 = excelFile.readCell("Messaging", "48331", excelFile.INPUT1_COL);
				String receipient2 = excelFile.readCell("Messaging", "48331", excelFile.INPUT2_COL);
				String msgToSend = excelFile.readCell("Messaging", "48331", excelFile.INPUT3_COL);


				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				String strDateTime = dtf.format(now);
				msgToSend = msgToSend + " " + strDateTime;


				cDigits_Messages messages = new cDigits_Messages(driver1);
				home.messages.click();
				messages.newMessageIcon.click();
				Thread.sleep(2000);

				messages.enterRecepient.sendKeys(receipient1);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.enterRecepient.sendKeys(receipient2);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.nextButton.click();
				Thread.sleep(2000);
				messages.messageEnter.sendKeys(msgToSend);
				messages.messageSendButton.click();
				Thread.sleep(10000);
				driver1.navigate().back(); 

				cDigits_Home home1 = new cDigits_Home(driver2);
				home1.messages.click();

				cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

				messagesDev2.MsgList.get(0).click();
				boolean flagMsgReceived= false;
				int msgno = messagesDev2.textMessagesReceived.size();
				log.info("msgR cnt : " + msgno);
		         for(int i=0; i<msgno; i++) {

					String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
					log.info("msgReceived 1 : " + msgReceived);

					if(msgToSend.equalsIgnoreCase(msgReceived))
					{
						log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
						flagMsgReceived = true;
						break;
					}
				}

		         driver2.navigate().back(); 
		         Thread.sleep(2000);

		         cDigits_Home home3 = new cDigits_Home(driver3);
					home3.messages.click();

					cDigits_Messages messagesDev3 = new cDigits_Messages(driver3);

		         messagesDev3.MsgList.get(0).click();
					boolean flagMsgReceived1= false;
					int msgno1 = messagesDev3.textMessagesReceived.size();
					log.info("msgR cnt : " + msgno1);
			         for(int i=0; i<msgno1; i++) {

						String msgReceived1 = messagesDev3.textMessagesReceived.get(i).getText();
						log.info("msgReceived 1 : " + msgReceived1);

						if(msgToSend.equalsIgnoreCase(msgReceived1))
						{
							log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived1);
							flagMsgReceived1 = true;
							break;
						}
					}

			         boolean flagMsgReceived3= false;

			         if(flagMsgReceived==true && flagMsgReceived1==true )
			         {
			        	 flagMsgReceived3=true;
			         }


		         System.out.println("****************************"+flagMsgReceived3);
				 assertEquals(flagMsgReceived3,true);


		}


		@Test
		public void Messaging_49569_TestSendMessageMOReceivedMTemoji() throws IOException, InterruptedException
		{
				log.info("Messaging_49569_TestSendMessageMOReceivedMT");

				cDigits_Home home = new cDigits_Home(driver1);

				String receipient = excelFile.readCell("Messaging", "49569", excelFile.INPUT1_COL);
				String msgToSend = excelFile.readCell("Messaging", "49569", excelFile.INPUT2_COL);


				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				String strDateTime = dtf.format(now);
				msgToSend = msgToSend + " " + strDateTime;


				cDigits_Messages messages = new cDigits_Messages(driver1);
				home.messages.click();
				messages.newMessageIcon.click();
				Thread.sleep(2000);

				messages.enterRecepient.sendKeys(receipient);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.nextButton.click();
				Thread.sleep(2000);
				messages.messageEnter.sendKeys(msgToSend);
				messages.messageSendButton.click();
				Thread.sleep(10000);
				driver1.navigate().back(); 

				cDigits_Home home1 = new cDigits_Home(driver2);
				home1.messages.click();

				cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

				messagesDev2.MsgList.get(0).click();
				boolean flagMsgReceived= false;
				int msgno = messagesDev2.textMessagesReceived.size();
				log.info("msgR cnt : " + msgno);
		         for(int i=0; i<msgno; i++) {

					String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
					log.info("msgReceived 1 : " + msgReceived);

					if(msgToSend.equalsIgnoreCase(msgReceived))
					{
						log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
						flagMsgReceived = true;
						break;
					}
				}
		         System.out.println("****************************"+flagMsgReceived);
				 assertEquals(flagMsgReceived,true);


		}



		@Test
		public void Messaging_49616_TestSendMessageMOReceivedMT1toNemoji() throws IOException, InterruptedException
		{
				log.info("Messaging_49616_TestSendMessageMOReceivedMT");

				cDigits_Home home = new cDigits_Home(driver1);

				String receipient1 = excelFile.readCell("Messaging", "49616", excelFile.INPUT1_COL);
				String receipient2 = excelFile.readCell("Messaging", "49616", excelFile.INPUT2_COL);
				String msgToSend = excelFile.readCell("Messaging", "49616", excelFile.INPUT3_COL);


				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				String strDateTime = dtf.format(now);
				msgToSend = msgToSend + " " + strDateTime;


				cDigits_Messages messages = new cDigits_Messages(driver1);
				home.messages.click();
				messages.newMessageIcon.click();
				Thread.sleep(2000);

				messages.enterRecepient.sendKeys(receipient1);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.enterRecepient.sendKeys(receipient2);
				driver1.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));

				messages.nextButton.click();
				Thread.sleep(2000);
				messages.messageEnter.sendKeys(msgToSend);
				messages.messageSendButton.click();
				Thread.sleep(10000);
				driver1.navigate().back(); 

				cDigits_Home home1 = new cDigits_Home(driver2);
				home1.messages.click();

				cDigits_Messages messagesDev2 = new cDigits_Messages(driver2);

				messagesDev2.MsgList.get(0).click();
				boolean flagMsgReceived= false;
				int msgno = messagesDev2.textMessagesReceived.size();
				log.info("msgR cnt : " + msgno);
		         for(int i=0; i<msgno; i++) {

					String msgReceived = messagesDev2.textMessagesReceived.get(i).getText();
					log.info("msgReceived 1 : " + msgReceived);

					if(msgToSend.equalsIgnoreCase(msgReceived))
					{
						log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived);
						flagMsgReceived = true;
						break;
					}
				}

		         driver2.navigate().back(); 
		         Thread.sleep(2000);

		         cDigits_Home home3 = new cDigits_Home(driver3);
					home3.messages.click();

					cDigits_Messages messagesDev3 = new cDigits_Messages(driver3);

		         messagesDev3.MsgList.get(0).click();
					boolean flagMsgReceived1= false;
					int msgno1 = messagesDev3.textMessagesReceived.size();
					log.info("msgR cnt : " + msgno1);
			         for(int i=0; i<msgno1; i++) {

						String msgReceived1 = messagesDev3.textMessagesReceived.get(i).getText();
						log.info("msgReceived 1 : " + msgReceived1);

						if(msgToSend.equalsIgnoreCase(msgReceived1))
						{
							log.info("msg sent matched 1 : " + msgToSend + "  :   " +  msgReceived1);
							flagMsgReceived1 = true;
							break;
						}
					}

			         boolean flagMsgReceived3= false;

			         if(flagMsgReceived==true && flagMsgReceived1==true )
			         {
			        	 flagMsgReceived3=true;
			         }


		         System.out.println("****************************"+flagMsgReceived3);
				 assertEquals(flagMsgReceived3,true);


		}*/
}
