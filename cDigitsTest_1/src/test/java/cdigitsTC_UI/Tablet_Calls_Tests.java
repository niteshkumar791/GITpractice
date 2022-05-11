package cdigitsTC_UI;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Home;
import pageObjects.util_CallListObject;

/*
 * 	UI_46098_CheckVoicemail
 * 	UI_46097_CheckMissedCallLogOrder
 * 	UI_46096_CheckAllCallLogOrder
 * 	UI_46094_SwitchBetweenCallTabs
 */
public class Tablet_Calls_Tests extends base {
	public static final Logger log = Logger.getLogger(Calls_Tests.class);


	//@Test
	public void UI_46098_CheckVoicemail() throws Exception{

		log.info("Test UI_46098_Checkvoicemail - Started");
		try {
			cDigits_Home home= new cDigits_Home(driver1);
			cDigits_Calls call= new cDigits_Calls(driver1);
	
			home.calls.click();
			Thread.sleep(15000);
			call.voicemail.click();
			wait1.until(ExpectedConditions.visibilityOf(call.PhonesList.get(0)));
			Thread.sleep(10000);
	
			//swipe down to get latest records
			Point firstEleTop = call.PhonesList.get(0).getLocation();
			int y = firstEleTop.getY();
			int x = firstEleTop.getX();
			Dimension sizeOfScreen = driver1.manage().window().getSize();
			int y2 = (int) (sizeOfScreen.height * 0.95);
	
			TouchAction ta = new TouchAction(driver1);
			ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
			Thread.sleep(10000);
	
	
			List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
			List<String> callListString = new ArrayList<String>();
			int recCount = 1;
			boolean scrollDown = true;
	
			while (scrollDown) {
				boolean newRecFound = false;
				cDigits_Calls callScroll= new cDigits_Calls(driver1);
				int noRecsOnScreen = callScroll.PhonesList.size();
	
				Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
				Point seclastSwipe = callScroll.PhonesList.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
				//create the list of CallListObject
				if(callScroll.PhonesList.size() != callScroll.DateList.size()) {
					callScroll.DateList.remove(0);
				}
	
				for(int i=0; i<noRecsOnScreen-1; i++)
				{
					String textVal = callScroll.PhonesList.get(i).getText();
					String dateVal = callScroll.DateList.get(i).getText();
					String durationVal = callScroll.DurationList.get(i).getText();
					if(textVal!=null && ! textVal.equalsIgnoreCase("")) { 
						String concatString = textVal+dateVal+durationVal;
						System.out.println("CallList : " + recCount + " --" + textVal + " " + dateVal + " " + durationVal);
						log.info("CallList : " + recCount + " --" + textVal + " " + dateVal + " " + durationVal);
						if(! callListString.contains(concatString))
						{
	
							log.info("New record");
							callList.add(new util_CallListObject(recCount,textVal,dateVal,durationVal));
							callListString.add(concatString);
							newRecFound = true;
	
						}
					}
	
					recCount = recCount + 1;
				}
	
				if(! newRecFound)   //new record not found, stop the scroll down
				{
					System.out.println("No new call list record found");
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
	
			//check chronological order
			Utilities util = new Utilities();
			boolean chronoCheck = util.PerformChronologicalOrderCheck(callList);
	
			Assert.assertEquals(chronoCheck, true);
			log.debug(" chronoCheck :  " + chronoCheck);
	
			driver1.navigate().back(); // tapping the back button
		}
		catch(Exception e){
			log.debug(" Exception :  " + e.getMessage());
		}
		log.info("Test UI_46098_Checkvoicemail - Completed");
	}


	//@Test
	public void UI_46097_CheckMissedCallLogOrder() throws Exception{
		log.info("Test UI_46097_CheckMissedCallLogOrder - Started");
		try {

			cDigits_Home home= new cDigits_Home(driver1);
			cDigits_Calls call= new cDigits_Calls(driver1);
			home.calls.click();		
			Thread.sleep(15000);
			call.missed.click();
			//wait1.until(ExpectedConditions.visibilityOf(call.PhonesList.get(0)));
			Thread.sleep(10000);		
			boolean chronoCheck = false;
			//swipe down to get latest records
			if(call.PhonesList.size()>0) {
				Point firstEleTop = call.PhonesList.get(0).getLocation();
				int y = firstEleTop.getY();
				int x = firstEleTop.getX();		
				Dimension sizeOfScreen = driver1.manage().window().getSize();
				int y2 = (int) (sizeOfScreen.height * 0.9);	
		
				TouchAction ta = new TouchAction(driver1);		
				ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();		
				Thread.sleep(10000);		
		
				List<util_CallListObject> callList = new ArrayList<util_CallListObject>();		
				List<String> callListString = new ArrayList<String>();		
				int recCount = 1;
				boolean scrollDown = true;
		
				while (scrollDown) {
		
					boolean newRecFound = false;			
					cDigits_Calls callScroll= new cDigits_Calls(driver1);			
					int noRecsOnScreen = callScroll.PhonesList.size();			
		
					Point firstSwipe = callScroll.SwipeList.get(0).getLocation();			
					Point seclastSwipe = callScroll.PhonesList.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
					//create the list of CallListObject
		
					for(int i=0; i<noRecsOnScreen-1; i++)
					{
						System.out.println(" ****************************************************** ");
						String textVal = callScroll.PhonesList.get(i).getText();
						String dateVal = callScroll.DateList.get(i).getText();
						String concatString = textVal+dateVal;
						System.out.println("CallList : " + recCount + " --" + textVal + " " + dateVal);
						if(! callListString.contains(concatString))
						{
							System.out.println("New record");
							callList.add(new util_CallListObject(recCount,textVal,dateVal));
							callListString.add(concatString);
							newRecFound = true;
						}
		
						recCount = recCount + 1;
					}
		
					if(! newRecFound)   //new record not found, stop the scroll down
					{
						System.out.println("No new call list record found");
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
		
				//check if at least 20 records present
				
				if(callList.size()>=20) {
					//check chronological order
					Utilities util = new Utilities();
					chronoCheck = util.PerformChronologicalOrderCheck(callList);
			
					Assert.assertEquals(chronoCheck, true);
					log.debug(" chronoCheck :  " + chronoCheck);
				}
				else
				{
					log.debug(" Less than 20 records present. Test Case Fails  ");
					Assert.assertEquals(chronoCheck, true);
				}
			}
			else
			{
				log.debug(" No Records found in Missed Calls. Test Case Fails  ");
				Assert.assertEquals(chronoCheck, true);
			}
			driver1.navigate().back(); // tapping the back button
		}
		catch(Exception e){
			e.printStackTrace();
			
			log.debug(" Exception :  " + e);
		}		
		log.info("Test UI_46097_CheckMissedCallLogOrder - Completed");
	}



	//@Test
	public void UI_46096_CheckAllCallLogOrder() throws InterruptedException, ParseException{
		log.info("Test UI_46096_CheckAllCallLogOrder - Started");
		try {
			cDigits_Home home= new cDigits_Home(driver1);
			cDigits_Calls call= new cDigits_Calls(driver1);
			home.calls.click();
			call.all.click();
			Thread.sleep(15000);
			boolean chronoCheck = false;
			//swipe down to get latest records
			if(call.PhonesList.size()>0) {
				Point firstEleTop = call.PhonesList.get(0).getLocation();
				int y = firstEleTop.getY();
				int x = firstEleTop.getX();
				Dimension sizeOfScreen = driver1.manage().window().getSize();
				int y2 = (int) (sizeOfScreen.height * 0.95);
		
				TouchAction ta = new TouchAction(driver1);
				ta.longPress(PointOption.point(x,y)).moveTo(PointOption.point(x,y2)).release().perform();
				Thread.sleep(10000);
		
				List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
				List<String> callListString = new ArrayList<String>();
				int recCount = 1;
				boolean scrollDown = true;
		
				while (scrollDown) {
					boolean newRecFound = false;
					cDigits_Calls callScroll= new cDigits_Calls(driver1);
					int noRecsOnScreen = callScroll.PhonesList.size();
		
					Point firstSwipe = callScroll.SwipeList.get(1).getLocation();
					Point seclastSwipe = callScroll.PhonesList.get(noRecsOnScreen-2).getLocation(); //taking -2 to avoid incomplete info in last record
					//create the list of CallListObject
		
					for(int i=0; i<noRecsOnScreen-1; i++)
					{
						String textVal = callScroll.PhonesList.get(i).getText();
						String dateVal = callScroll.DateList.get(i).getText();
						String durationVal = "00:00";
						String concatString = textVal+dateVal+durationVal;
						System.out.println("CallList : " + recCount + " --" + textVal + " " + dateVal + " " + durationVal);
						if(! callListString.contains(concatString))
						{
							System.out.println("New record");
							callList.add(new util_CallListObject(recCount,textVal,dateVal,durationVal));
							callListString.add(concatString);
							newRecFound = true;
						}
		
						recCount = recCount + 1;
					}
		
					if(! newRecFound)   //new record not found, stop the scroll down
					{
						System.out.println("No new call list record found");
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
		
				if(callList.size()>=20) {
					//check chronological order
					Utilities util = new Utilities();
					chronoCheck = util.PerformChronologicalOrderCheck(callList);
			
					Assert.assertEquals(chronoCheck, true);
					log.debug(" chronoCheck :  " + chronoCheck);
				}
				else
				{
					log.debug(" Less than 20 records present. Test Case Fails  ");
					Assert.assertEquals(chronoCheck, true);
				}
			}
			else
			{
				log.debug(" No Records found in Missed Calls. Test Case Fails  ");
				Assert.assertEquals(chronoCheck, true);
			}
	
			driver1.navigate().back(); // tapping the back button
			System.out.println("***********555*************");
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(" Exception :  " + e);
		}	

		log.info("Test UI_46096_CheckAllCallLogOrder - Completed");
	}



	//Select "CALLS" tab 2- Swith between"Key pad/All Recent Calls/All Missed Calls/Voice mail
	@Test
	public void UI_46094_SwitchBetweenCallTabs() throws Exception{
		log.info("Test UI_46094_SwitchBetweenCallTabs - Started");
		try {
			cDigits_Home home= new cDigits_Home(driver1);
			cDigits_Calls call= new cDigits_Calls(driver1);
	
			boolean checkTab= true;
			// Calls Tab Highlight

			home.calls.click();
			if (checkTab)
				checkTab = home.calls.isSelected();
			Thread.sleep(2000);

			// All calls Tab Highlight
			call.all.click();
			if (checkTab)
				checkTab =call.all.isSelected();
			Thread.sleep(2000);

			// Missed call Tab Highlight
			call.missed.click();
			if (checkTab)
				checkTab = call.missed.isSelected();
			Thread.sleep(2000);

			// Voicemail Tab Highlight
			call.voicemail.click();
			if (checkTab)
				checkTab = call.voicemail.isSelected();
			Thread.sleep(2000);


			if (checkTab) {
				log.debug(" All Calls tabs working");
			}
			Assert.assertEquals(checkTab, true);
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug(" Exception :  " + e);
		}	

		driver1.navigate().back(); // tapping the back button
		Thread.sleep(5000);
		log.info("Test UI_46094_SwitchBetweenCallTabs - Completed");
	}



}
