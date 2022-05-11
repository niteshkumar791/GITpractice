package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_Activate {

	public cDigits_Activate(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_name_view")
	public List<WebElement> namesList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_number_view")
	public List<WebElement> phonesList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/navigate_button")
	public WebElement activateButton;
	
	
	
}
