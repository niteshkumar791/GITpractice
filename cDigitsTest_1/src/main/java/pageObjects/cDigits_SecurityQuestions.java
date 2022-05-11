package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_SecurityQuestions {

	public cDigits_SecurityQuestions(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="que_0_field")
	public WebElement answer1;
	
	@AndroidFindBy(id="que_1_field")
	public WebElement answer2;
	
	@AndroidFindBy(xpath="//*[@text='Continue']")
	public WebElement continueButton;
	
	
	
}
