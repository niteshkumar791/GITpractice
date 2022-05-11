package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class cDigits_Login {

	public cDigits_Login(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/phone")
	public WebElement loginEmail;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/password")
	public WebElement password;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/sign_in")
	public WebElement signInButton;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/different_tmoid")
	public WebElement notMe;
	
	//com.mavenir.ucctest:id/check_mark
	
	//com.mavenir.ucctest:id/walkthrough_info_continue_btn
	
}
