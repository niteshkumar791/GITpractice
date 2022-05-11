package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_Initial {

	public cDigits_Initial(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/walkthrough_info_continue_btn")
	public WebElement walkthroughContinueButton;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/walkthrough_skip_text")
	public WebElement walkthroughSkipButton;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/accept_btn")
	public WebElement acceptButton;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/walkthrough_permissions_next_btn")
	public WebElement walkthroughNextButton;
	
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	public WebElement walkthroughAllowButton;
}
