package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class cDigits_Menu_Help {

	public cDigits_Menu_Help(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/textViewAboutDigits")
	public WebElement aboutDigits;
	
	@AndroidFindBy(xpath="//*[@text='FAQ']")
	public WebElement faq;
	
	@AndroidFindBy(xpath="//*[@text='Support']")
	public WebElement support;
	
	@AndroidFindBy(xpath="//*[@text='Privacy Policy']")
	public WebElement privacyPolicy;
	
	@AndroidFindBy(xpath="//*[@text='Terms Of Use']")
	public WebElement termsOfUse;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/About_TextView_Name")
	public WebElement aboutVersionDisplayed;
	
	
}
