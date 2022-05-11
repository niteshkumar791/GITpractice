package commonClasses;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.cDigits_Contacts;
import pageObjects.util_CallListObject;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Utilities extends base  {

	public static final Logger log = Logger.getLogger(Utilities.class);

	public void typeSecAnswerKeyboard(String PhoneType, String SecAnswer, TouchAction ta) {
		if(PhoneType.equalsIgnoreCase("G5"))
		{
			if(SecAnswer.equalsIgnoreCase("verveba"))
			{
				typeVervebaKeyboard(ta);
			}
			if(SecAnswer.equalsIgnoreCase("verveba1"))
			{
				typeVerveba1Keyboard(ta);
			}

		}

		if(PhoneType.equalsIgnoreCase("E5")) {
			if(SecAnswer.equalsIgnoreCase("verveba"))
			{
				typeVervebaKeyboardMotoE5(ta);
			}
			if(SecAnswer.equalsIgnoreCase("verveba1"))
			{
				typeVerveba1KeyboardMotoE5(ta);
			}
		}

		if(PhoneType.equalsIgnoreCase("Tablet")) {
			if(SecAnswer.equalsIgnoreCase("verveba"))
			{
				typeVervebaKeyboardSamsungTablet(ta);
			}
			if(SecAnswer.equalsIgnoreCase("verveba1"))
			{
				typeVerveba1KeyboardSamsungTablet(ta);
			}
		}
	}


	public void typeVervebaKeyboard(TouchAction ta) {

		ta.tap(PointOption.point(538,1536)).perform();  //v
		ta.tap(PointOption.point(277,1255)).perform();	//e
		ta.tap(PointOption.point(379,1239)).perform();	//r
		ta.tap(PointOption.point(538,1536)).perform();	//v
		ta.tap(PointOption.point(277,1255)).perform();	//e
		ta.tap(PointOption.point(648,1546)).perform();	//b
		ta.tap(PointOption.point(111,1401)).perform();	//a

	}

	public void typeVerveba1Keyboard(TouchAction ta) {

		ta.tap(PointOption.point(538,1536)).perform();  //v
		ta.tap(PointOption.point(277,1255)).perform();	//e
		ta.tap(PointOption.point(379,1239)).perform();	//r
		ta.tap(PointOption.point(538,1536)).perform();	//v
		ta.tap(PointOption.point(277,1255)).perform();	//e
		ta.tap(PointOption.point(648,1546)).perform();	//b
		ta.tap(PointOption.point(111,1401)).perform();	//a
		ta.tap(PointOption.point(55,1100)).perform();	//1

	}

	public void typeVervebaKeyboardMotoE5(TouchAction ta) {

		ta.tap(PointOption.point(360,1190)).perform();  //v
		ta.tap(PointOption.point(180,990)).perform();	//e
		ta.tap(PointOption.point(250,990)).perform();	//r
		ta.tap(PointOption.point(360,1190)).perform();	//v
		ta.tap(PointOption.point(180,990)).perform();	//e
		ta.tap(PointOption.point(430,1190)).perform();	//b
		ta.tap(PointOption.point(75,1090)).perform();	//a

	}


	public void typeVerveba1KeyboardMotoE5(TouchAction ta) {

		ta.tap(PointOption.point(360,1190)).perform();  //v
		ta.tap(PointOption.point(180,990)).perform();	//e
		ta.tap(PointOption.point(250,990)).perform();	//r
		ta.tap(PointOption.point(360,1190)).perform();	//v
		ta.tap(PointOption.point(180,990)).perform();	//e
		ta.tap(PointOption.point(430,1190)).perform();	//b
		ta.tap(PointOption.point(75,1090)).perform();	//a
		ta.tap(PointOption.point(35,890)).perform();	//1

	}

	public void typeVervebaKeyboardSamsungTablet(TouchAction ta) {

		ta.tap(PointOption.point(315,910)).perform();  //v
		ta.tap(PointOption.point(180,780)).perform();	//e
		ta.tap(PointOption.point(245,780)).perform();	//r
		ta.tap(PointOption.point(315,910)).perform();	//v
		ta.tap(PointOption.point(180,780)).perform();	//e
		ta.tap(PointOption.point(380,910)).perform();	//b
		ta.tap(PointOption.point(80,850)).perform();	//a

	}


	public void typeVerveba1KeyboardSamsungTablet(TouchAction ta) {

		ta.tap(PointOption.point(315,910)).perform();  //v
		ta.tap(PointOption.point(180,780)).perform();	//e
		ta.tap(PointOption.point(245,780)).perform();	//r
		ta.tap(PointOption.point(315,910)).perform();	//v
		ta.tap(PointOption.point(180,780)).perform();	//e
		ta.tap(PointOption.point(380,910)).perform();	//b
		ta.tap(PointOption.point(80,850)).perform();	//a
		ta.tap(PointOption.point(45,730)).perform();	//1

	}

	public boolean PerformChronologicalOrderCheck(List<util_CallListObject> callLists) throws ParseException {
		log.debug("PerformChronologicalOrderCheck");
		boolean result = true;

		Date prevDatetoCheck = new Date();  //set current date time
		Date yesterdayDate = DateUtils.addDays(new Date(), -1);

		DateFormat dtfDate = new SimpleDateFormat("MMM dd yyyy");
		DateFormat dtfDateTime = new SimpleDateFormat("MMM dd yyyy hh:mm aa");
		DateFormat dtfYear = new SimpleDateFormat("yyyy");

		String todaysDateStr = dtfDate.format(prevDatetoCheck);
		String yesterdayDateStr = dtfDate.format(yesterdayDate);
		String currYear = dtfYear.format(prevDatetoCheck);
		for(int i=0; i<callLists.size(); i++) {

			String dateVal = callLists.get(i).getDate();
			log.debug(" dateVal " + dateVal);
			Date dateInRec = null;
			if(dateVal.contains(":"))
			{
				String dateToChk = todaysDateStr + " " + dateVal;
				log.debug("  dateToChk 1 " + dateToChk);
				dateInRec = dtfDateTime.parse(dateToChk);
			}

			if(dateVal.contains("Jan") || dateVal.contains("Feb") || dateVal.contains("Mar") || dateVal.contains("Apr") || dateVal.contains("May")
					|| dateVal.contains("Jun") || dateVal.contains("Jul") || dateVal.contains("Aug") || dateVal.contains("Sep") 
					|| dateVal.contains("Oct") || dateVal.contains("Nov") || dateVal.contains("Dec"))
			{
				String dateToChk = dateVal + " " + currYear + " 00:01 AM";
				log.debug("  dateToChk 2 " + dateToChk);
				dateInRec = dtfDateTime.parse(dateToChk);
			}

			if(dateVal.contains("Yesterday"))
			{
				String dateToChk = yesterdayDateStr + " 00:01 AM";;
				log.debug("  dateToChk 3 " + dateToChk);
				dateInRec = dtfDateTime.parse(dateToChk);
			}

			if(dateInRec.compareTo(prevDatetoCheck) <=0 )
			{
				//do nothing. check next record
				log.debug("  ddate less" );
				prevDatetoCheck = dateInRec;
			}
			else
			{
				log.debug("  ddate more" );
				result = false;
				break;
			}

		}

		return result;
	}

	public void  SwipeLeftforDeleteOption(int x2, int y2, int x, int y, TouchAction ta) {
		ta.longPress(PointOption.point(x2, y2)).moveTo(PointOption.point(x, y)).release().perform(); // swipe left for visibility of delete option
	}

	public void SwipeLeftforDeleteOption_MotoG5(TouchAction ta){

		ta.longPress(PointOption.point(871, 968)).moveTo(PointOption.point(218, 989)).release().perform(); // swipe left for visibility of delete option

	}

	public void SwipeLeftforDeleteOption_Tab(TouchAction ta){

		ta.longPress(PointOption.point(242, 358)).moveTo(PointOption.point(38, 359)).release().perform(); // swipe left for visibility of delete option

	}

	public void SwipeBottomtoUP_Tab(TouchAction ta){

		ta.longPress(PointOption.point(131, 936)).moveTo(PointOption.point(133, 318)).release().perform(); // swipe left for visibility of delete option

	}

	public void ScrollBottomToTop(TouchAction ta, int height, int width){

		int x = (int) (width/2);
		int y = (int) (height * 0.8);
		int y1 = (int) (height * 0.2);
		ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y1)).release().perform(); 
	}

	//Swipe code

	//Code to Swipe UP 
	public boolean swipeFromUpToBottom() 
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver1;
			HashMap scrollObject = new HashMap();
			scrollObject.put("direction", "up");
			js.executeScript("mobile: scroll", scrollObject);
			System.out.println("Swipe up was Successfully done.");
		}
		catch (Exception e) 
		{
			System.out.println("swipe up was not successfull");
		} 
		return false; 
	}

	//Code to Swipe DOWN 
	public boolean swipeFromBottomToUp() 
	{ 
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver1;
			HashMap scrollObject = new HashMap();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			System.out.println("Swipe down was Successfully done");
		}
		catch (Exception e) 
		{
			System.out.println("swipe down was not successfull");
		} 
		return false; 
	}



	public void ScrollandGetInList1() throws Exception{

		// swipe down to get latest records
		cDigits_Contacts contact = new cDigits_Contacts(driver1);

		Point firstEleTop = contact.Localcontactlist.get(0).getLocation();
		int y = firstEleTop.getY();
		int x = firstEleTop.getX();
		Dimension sizeOfScreen = driver1.manage().window().getSize();
		int y2 = (int) (sizeOfScreen.height * 0.8);

		TouchAction ta = new TouchAction(driver1);
		ta.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y2)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> LocalCallList = new ArrayList<util_CallListObject>();
		List<String> callListString = new ArrayList<String>();
		int recCount = 1;
		boolean scrollDown = true;

		while (scrollDown) {
			boolean newRecFound = false;
			cDigits_Contacts contactscroll = new cDigits_Contacts(driver1);
			int noRecsOnScreen = contactscroll.Localcontactlist.size();

			Point firstSwipe = contactscroll.SwipeList.get(1).getLocation();
			Point seclastSwipe = contactscroll.Localcontactlist.get(noRecsOnScreen - 2).getLocation(); // taking -2 to avoidincomplete info in last record create the list of CallListObject.

			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal = contactscroll.Localcontactlist.get(i).getText();
				//String dateVal = contactscroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString = textVal;
				log.debug("CallList : " + recCount + " --" + textVal );

				if (!callListString.contains(concatString)) {
					log.debug("New record");
					//LocalCallList.add(new util_CallListObject(recCount, textVal));
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

		log.info("Test UI_43809_CheckFavorite - Completed");



		// swipe down to get latest records
		cDigits_Contacts contact2 = new cDigits_Contacts(driver2);

		Point firstEleTop2 = contact2.Cloudcontactlist.get(0).getLocation();
		int y1 = firstEleTop2.getY();
		int x1 = firstEleTop2.getX();
		Dimension sizeOfScreen2 = driver2.manage().window().getSize();
		int y3 = (int) (sizeOfScreen2.height * 0.8);

		TouchAction ta2 = new TouchAction(driver2);
		ta2.longPress(PointOption.point(x1, y1)).moveTo(PointOption.point(x1, y3)).release().perform();
		Thread.sleep(10000);

		//List<util_CallListObject> CloudCallList = new ArrayList<util_CallListObject>();
		List<String> callListString2 = new ArrayList<String>();
		int recCount2 = 1;
		boolean scrollDown2 = true;

		while (scrollDown2) {
			boolean newRecFound2 = false;
			cDigits_Contacts contactscroll2 = new cDigits_Contacts(driver2);
			int noRecsOnScreen = contactscroll2.Cloudcontactlist.size();

			Point firstSwipe = contactscroll2.SwipeList.get(1).getLocation();
			Point seclastSwipe = contactscroll2.Cloudcontactlist.get(noRecsOnScreen - 2).getLocation(); // taking -2 to avoidincomplete info in last record create the list of CallListObject.

			for (int i = 0; i < noRecsOnScreen - 1; i++) {
				String textVal1 = contactscroll2.Cloudcontactlist.get(i).getText();
				//String dateVal = contactscroll.DateList.get(i).getText();
				//String durationVal = callScroll.DurationList.get(i).getText();
				String concatString2 = textVal1;
				log.debug("CloudCallList : " + recCount2 + " --" + textVal1 );

				if (!callListString2.contains(concatString2)) {
					log.debug("New record");
					//CloudCallList.add(new util_CallListObject(recCount2, textVal1));
					callListString2.add(concatString2);
					newRecFound2 = true;
				}

				recCount2 = recCount2 + 1;
			}

			if (!newRecFound2) // new record not found, stop the scroll down
			{
				log.debug("No new call list record found");
				scrollDown2 = false;
			} else {

				if (noRecsOnScreen >= 4) {
					TouchAction secAns = new TouchAction(driver2);
					secAns.longPress(PointOption.point(seclastSwipe)).moveTo(PointOption.point(firstSwipe)).release()
					.perform();
					Thread.sleep(2000);
				} else // no need to scroll down
				{
					scrollDown2 = false;
				}
			}
		}

		boolean c = callListString.containsAll(callListString2) && callListString.size() == callListString2.size() ;

		//Assert.assertEquals(c, true);
		log.info("Test UI_43809_CheckFavorite - Completed");
	}

	public void ClickOnSyncContacts(String PhoneType, TouchAction ta) {
		if(PhoneType.equalsIgnoreCase("G5"))
		{
			ta.tap((PointOption.point(758,385))).perform();
		}

		if(PhoneType.equalsIgnoreCase("E5")) {

		}

		if(PhoneType.equalsIgnoreCase("Nexus")) {

		}

		if(PhoneType.equalsIgnoreCase("Tablet")) {

		}	
	}


	public boolean  ImageComparision(WebElement i1, WebElement i2) throws InterruptedException, Exception
	{
		log.info("In ImageComparision");
		boolean result = false;
		WebElement logoimgelement=i1;
		Screenshot logoimgscreenshot=new AShot().takeScreenshot(driver1, logoimgelement);
		BufferedImage image1=logoimgscreenshot.getImage();


		//for device 2
		WebElement logoimgelement2=i2;
		Screenshot logoimgscreenshot2=new AShot().takeScreenshot(driver2, logoimgelement2);
		BufferedImage image2=logoimgscreenshot2.getImage();



		ImageDiffer imgdif= new ImageDiffer();
		ImageDiff diff= imgdif.makeDiff(image1,image2);

		result=diff.hasDiff();

		return result;


		//			byte[] screenshot1 = Base64.encodeBase64(driver1.getScreenshotAs(OutputType.BYTES));
		//			byte[] screenshot2 = Base64.encodeBase64(driver2.getScreenshotAs(OutputType.BYTES));
		//			SimilarityMatchingResult result = driver1
		//			        .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions()
		//			                .withEnabledVisualization());
		//			
		//			boolean r1=(result.getVisualization().length > 0);
		//			Assert.assertEquals(r1, true);
		//			//assertThat(result.getVisualization().length, is(greaterThan(0)));
		//			boolean r2=(result.getVisualization().length > 0.0);
		//			Assert.assertEquals(r2, true);
		//assertThat(result.getScore(), is(greaterThan(0.0)));


		//			Image I1=Toolkit.getDefaultToolkit().getImage(i12);
		//			Image I2=Toolkit.getDefaultToolkit().getImage(i22);
		//			PixelGrabber grab1=new PixelGrabber(I1, 0, 0, -1, -1, false);
		//			PixelGrabber grab2=new PixelGrabber(I2, 0, 0, -1, -1, false);
		//			int[] data1=null;
		//			int[] data2=null;
		//	
		//			if(grab1.grabPixels())
		//			{
		//				int h=grab1.getHeight();
		//				int w=grab1.getWidth();
		//				data1=new int[h*w];
		//				data1=(int[]) grab1.getPixels();
		//	
		//			}
		//			if(grab2.grabPixels())
		//			{
		//				int h=grab2.getHeight();
		//				int w=grab2.getWidth();
		//				data2=new int[h*w];
		//				data2=(int[]) grab2.getPixels();
		//			}
		//			boolean x=Arrays.equals(data1, data2);
		//			if(x==true)
		//			{
		//				System.out.println("Images are same");
		//			}
		//			else
		//			{
		//				System.out.println("Images are different");
		//				
		//			}
		//			return x;

	}

}


