package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_DialPad {

	public cDigits_DialPad(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//*[@text='1']")
	public WebElement one;
	
	@AndroidFindBy(xpath="//*[@text='ABC']")
	public WebElement two;
	
	@AndroidFindBy(xpath="//*[@text='DEF']")
	public WebElement three;
	
	@AndroidFindBy(xpath="//*[@text='GHI']")
	public WebElement four;
	
	@AndroidFindBy(xpath="//*[@text='JKL']")
	public WebElement five;
	
	@AndroidFindBy(xpath="//*[@text='MNO']")
	public WebElement six;
	
	@AndroidFindBy(xpath="//*[@text='PQRS']")
	public WebElement seven;
	
	@AndroidFindBy(xpath="//*[@text='TUV']")
	public WebElement eight;
	
	@AndroidFindBy(xpath="//*[@text='WXYZ']")
	public WebElement nine;
	
	@AndroidFindBy(xpath="//*[@text='0']")
	public WebElement zero;
	
	@AndroidFindBy(xpath="//*[@text='*']")
	public WebElement star;
	
	@AndroidFindBy(xpath="//*[@text='#']")
	public WebElement hash;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/etInputText")
	public WebElement searchContactsInput;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibVoiceMail")
	public WebElement voicemailSelect;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibCallAudio")
	public WebElement callAudioSelect;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibCallVideo")
	public WebElement callVideoSelect;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/etInput")
	public WebElement dialPadEnteredValues;
	
	
	
}
