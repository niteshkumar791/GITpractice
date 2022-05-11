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

public class cDigits_Menu {

	public cDigits_Menu(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/name_text_view")
	public WebElement nameText;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/email_text_view")
	public WebElement emailText;
	
	@AndroidFindBy(xpath="//*[@text='My lines']")
	public WebElement myLines;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/text_phone_number")
	public List<WebElement> linesOnAccount;
	
	@AndroidFindBy(xpath="//*[@text='App settings']")
	public WebElement appSettings;
	
	@AndroidFindBy(xpath="//*[@text='Help']")
	public WebElement help;
	
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	public WebElement signOut;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/btn_signout")
	public WebElement signoutconfirm;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/toggle_switch")
	public List<WebElement> lineToggle;
	
}
