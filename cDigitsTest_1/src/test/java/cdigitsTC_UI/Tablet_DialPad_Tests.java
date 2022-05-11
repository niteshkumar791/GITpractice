package cdigitsTC_UI;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonClasses.base;
import pageObjects.cDigits_Calls;
import pageObjects.cDigits_DialPad;
import pageObjects.cDigits_Home;

/*
 * 	UI_46095_CheckDialPad
 * 
 * 
 */
public class Tablet_DialPad_Tests extends base {
	
	
	public static final Logger log = Logger.getLogger(DialPad_Tests.class);

	@Test
	public void UI_46095_CheckDialPad() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		log.info("UI_46095_CheckDialPad - Started");
		
		cDigits_Home home = new cDigits_Home(driver1);
		cDigits_Calls calls = new cDigits_Calls(driver1);
		//home.dialPad.click();
		home.calls.click();
		calls.dialPad.click();
		 
		cDigits_DialPad dialPad = new cDigits_DialPad(driver1);
		dialPad.one.click();
		Thread.sleep(2000);
		dialPad.two.click();Thread.sleep(1000);
		dialPad.three.click();Thread.sleep(1000);
		dialPad.four.click();Thread.sleep(1000);
		dialPad.five.click();Thread.sleep(1000);
		dialPad.six.click();Thread.sleep(1000);
		dialPad.seven.click();Thread.sleep(1000);
		dialPad.eight.click();Thread.sleep(1000);
		dialPad.nine.click();Thread.sleep(1000);
		dialPad.zero.click();Thread.sleep(1000);
		dialPad.star.click();Thread.sleep(1000);
		dialPad.hash.click();Thread.sleep(1000);

		String verifyInput = dialPad.dialPadEnteredValues.getText();
		Assert.assertEquals(verifyInput, "1234567890*#");

		driver1.navigate().back();   //tapping the back button
		wait1.until(ExpectedConditions.visibilityOf(home.contacts));
		
		log.info("UI_46095_CheckDialPad - Completed");

	}

}
