package cdigitsTC_UI;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonClasses.Utilities;
import commonClasses.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_Home;
import pageObjects.util_CallListObject;
import supportForTC.BeforeAfterTest;

/*
 * UI_43806_Checkvoicemail()
 * UI_43804_CheckAllCallLogOrder
 * UI_43805_CheckMissedCallLogOrder
 */
public class Calls_Tests extends base {

	public static final Logger log = Logger.getLogger(Calls_Tests.class);

	// 43806 Checkvoicemail
	@Test
	public void UI_43806_CheckVoicemail() throws Exception {

		log.info("Test UI_43806_Checkvoicemail - Started");
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call = new cDigits_Calls(driver1);

		home.calls.click();
		Thread.sleep(15000);
		call.voicemail.click();
		wait1.until(ExpectedConditions.visibilityOf(call.PhonesList.get(0)));
		Thread.sleep(10000);

		// swipe down to get latest records
		Point firstEleTop = call.PhonesList.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.9);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
		Thread.sleep(10000);

		List<util_CallListObject> callList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		int recCount = 1;
		boolean scrollDown = true;

		
		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Calls callScroll = new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.PhonesList.size();
			
			Point firstSwipe = callScroll.PhonesList.get(0).getLocation();
			Point seclastSwipe = callScroll.SwipeList.get(noRecsOnScreen - 2).getLocation(); // taking -2 to avoid incomplete info in last record create the list of CallListObject.
			
			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = callScroll.PhonesList.get(i).getText();
				String dateVal = callScroll.DateList.get(i).getText();
				String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal + dateVal + durationVal;
				log.debug("CallList : " + recCount + " --" + textVal + " " + dateVal + " " + durationVal);
				if (!callListString.contains(concatString)) {
					log.debug("New record");
					callList.add(new util_CallListObject(recCount, textVal, dateVal, durationVal));
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
					//TouchAction secAns = new TouchAction(driver1);
					
					ta.longPress(PointOption.point(x,seclastSwipe.y)).moveTo(PointOption.point(x,firstSwipe.y)).release()
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
		boolean chronoCheck = util.PerformChronologicalOrderCheck(callList);

		Assert.assertEquals(chronoCheck, true);
		log.debug(" chronoCheck :  " + chronoCheck);

		driver1.navigate().back(); // tapping the back button
		log.info("Test UI_43806_Checkvoicemail - Completed");

	}

	// 43804 Check ALL recent call log order//@Test
	public void UI_43804_CheckAllCallLogOrder() throws Exception {
		log.info("Test UI_43804_CheckAllCallLogOrder - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call = new cDigits_Calls(driver1);
		home.calls.click();
		call.all.click();

		// TODO SIMILAR as Vocemail


		// swipe down to get latest records
		Point firstEleTop = call.PhonesList.get(0).getLocation();
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
			cDigits_Calls callScroll = new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.PhonesList.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.PhonesList.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoidincomplete info in last record  create the list of CallListObject.

			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = callScroll.PhonesList.get(i).getText();
				String dateVal = callScroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal + dateVal;
				log.debug("CallList : " + recCount + " --" + textVal + " " + dateVal);

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

		// check chronological order
		Utilities util = new Utilities();
		boolean chronoCheckALLCALL = util.PerformChronologicalOrderCheck(callList);

		Assert.assertEquals(chronoCheckALLCALL, true);
		log.debug(" chronoCheck :  " + chronoCheckALLCALL);


		log.info("Test UI_43804_CheckAllCallLogOrder - Completed");
	}

	// 43805 Check missed call log order by time stamp and scrolling
	@Test
	public void UI_43805_CheckMissedCallLogOrder() throws Exception {
		log.info("Test UI_43805_CheckMissedCallLogOrder - Started");

		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls call = new cDigits_Calls(driver1);
		home.calls.click();
		call.missed.click();

		// TODO SIMILAR as Vocemail

		// swipe down to get latest records
		Point firstEleTop = call.PhonesList.get(0).getLocation();
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
			cDigits_Calls callScroll = new cDigits_Calls(driver1);
			int noRecsOnScreen = callScroll.PhonesList.size();

			Point firstSwipe = callScroll.SwipeList.get(0).getLocation();
			Point seclastSwipe = callScroll.PhonesList.get(noRecsOnScreen - 2).getLocation(); // taking -2  to avoid incomplete info in last record create the list of CallListObject.

			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = callScroll.PhonesList.get(i).getText();
				String dateVal = callScroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal + dateVal;
				log.debug("CallList : " + recCount + " --" + textVal + " " + dateVal);

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

				if (noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver1);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release().perform();
					Thread.sleep(2000);
				} else // no need to scroll down
				{
					scrollDown = false;
				}
			}
		}

		// check chronological order
		Utilities util = new Utilities();
		boolean chronoCheckALLCALL = util.PerformChronologicalOrderCheck(callList);

		Assert.assertEquals(chronoCheckALLCALL, true);
		log.debug(" chronoCheck :  " + chronoCheckALLCALL);

		log.info("Test UI_43805_CheckMissedCallLogOrder - Completed");
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
