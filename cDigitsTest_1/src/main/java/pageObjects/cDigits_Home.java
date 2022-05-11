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

public class cDigits_Home {

	public cDigits_Home(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	
	@AndroidFindBy(id="com.mavenir.ucctest:id/dialpadTabIcon")
	public WebElement dialPad;
	
	
	
	@AndroidFindBy(xpath="//*[@text='Contacts']")
	public WebElement contacts;
	
	@AndroidFindBy(xpath="//*[@text='Contacts']")
	public WebElement contactsTab;
	
	@AndroidFindBy(xpath="//*[@text='Calls']")
	public WebElement calls;
	
	@AndroidFindBy(xpath="//*[@text='Messages']")
	public WebElement messages;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/edittext_search_message")
	public WebElement searchInput;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/drawer_handle")
	public WebElement drawerHandle;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/fab_new_message")
	public WebElement newMessageIcon;
	
	@AndroidFindBy(xpath="//*[@text='1(360) 213-3986']")
	public List<WebElement> messagesFrom3986List;
	
	@AndroidFindBy(xpath="//*[@text='All Lines']")
	public  WebElement SelectLine;
	
	@AndroidFindBy(xpath="//*[@text='Mohit']")
	public  WebElement Line1;
	
	@AndroidFindBy(xpath="//*[@text='All Lines']")
	public  WebElement AllLine;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tabIcon")
	public WebElement contactHigligtning;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_name")
	public List<WebElement> Linenumber;
	
	
	
	

}
