package cdigitsTC_UI;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import commonClasses.Utilities;
import commonClasses.base;
import pageObjects.cDigits_Contacts;
import pageObjects.cDigits_Home;
import pageObjects.cDigits_Menu;
import pageObjects.cDigits_Menu_AppSettings;
import pageObjects.cDigits_Messages;
import pageObjects.util_CallListObject;
import supportForTC.BeforeAfterTest;

/*
 * 	UI_46099_ChecktTabMessagesTabScroll
 * 
 */
public class Tablet_Messages_Tests extends base{

	public static final Logger log = Logger.getLogger(Contacts_Tests.class);

	
	@Test
	public void UI_46099_ChecktTabMessagesTabScroll() throws Exception {

		log.info("Test UI_46099_ChecktTabMessagesTabScroll - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Messages msg= new cDigits_Messages(driver1);
		home.messages.click();

	/*	home.SelectLine.click();
		Thread.sleep(2000);
		home.Linenumber.get(0).click(); // clicks on first line*/
		Thread.sleep(2000);

		// Scroll for messages of selected line
		//TO DO:
		
		
		// swipe down to get latest records
				Point firstEleTop = msg.MsgList.get(0).getLocation();
				int y = firstEleTop.getY();
				int x = firstEleTop.getX();
				Dimension sizeOfScreen = driver1.manage().window().getSize();
				int y2 = (int) (sizeOfScreen.height * 0.8);

				TouchAction ta = new TouchAction(driver1);
				ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
				Thread.sleep(10000);

				List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
				List<String> callListString = new ArrayList<String>();
				int recCount = 1;
				boolean scrollDown = true;

				while (scrollDown) {
					boolean newRecFound = false;
					cDigits_Messages msgScroll= new cDigits_Messages(driver1);
					int noRecsOnScreen = msgScroll.MsgList.size();

					Point firstSwipe = msgScroll.SwipeList.get(1).getLocation();
					Point seclastSwipe = msgScroll.MsgList.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoidincomplete info in last record  create the list of CallListObject.

					for (int i = 0; i < noRecsOnScreen - 1; i++) {
						String textVal = msgScroll.MsgList.get(i).getText();
						String dateVal = msgScroll.DateList.get(i).getText();
					  //String durationVal = callScroll.DurationList.get(i).getText();
						String concatString = textVal + dateVal ;
						log.debug("CallList : " + recCount + " --" + textVal+" --"+dateVal);
						
						if (!callListString.contains(concatString)) {
							log.debug("New record");
							callList.add(new util_CallListObject(recCount, textVal, dateVal));
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

						if (noRecsOnScreen >= 11) {
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
				
				// check chronological order
				Utilities util = new Utilities();
				boolean chronoCheckMSG = util.PerformChronologicalOrderCheck(callList);

				Assert.assertEquals(chronoCheckMSG, true);
				
		
		
		
		
		log.info("Test UI_46099_ChecktTabMessagesTabScroll - Completed");
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
