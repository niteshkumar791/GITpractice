package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_Menu_AppSettings {

	public cDigits_Menu_AppSettings(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//*[@text='Device name']")
	public WebElement deviceName;
	
	//TODO - need to check the line that was activated at login and check for that here.
	@AndroidFindBy(xpath="//*[@text='(360) 213-3986']")
	public WebElement activatedLine;
	
	@AndroidFindBy(xpath="//*[@text='Voicemail PIN']")
	public WebElement voicemailPin;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/old_pin")
	public WebElement oldPin;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/new_pin")
	public WebElement newPin;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/new_pin_confirm")
	public WebElement ConfirmnewPin;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/save")
	public WebElement Save;
	
	@AndroidFindBy(xpath="//*[@text='Device name']")
	public WebElement DeviceName;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ll_line_info")
	public List<WebElement> lineInfo;
	

	
}
