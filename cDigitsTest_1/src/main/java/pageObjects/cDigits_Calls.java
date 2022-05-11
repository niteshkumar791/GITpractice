package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cDigits_Calls {

	public cDigits_Calls(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.mavenir.ucctest:id/fab_calllog")
	public WebElement dialPad;
	
	@AndroidFindBy(xpath="//*[@text='All']")
	public WebElement all;
	
	@AndroidFindBy(xpath="//*[@text='Missed']")
	public WebElement missed;
	
	@AndroidFindBy(xpath="//*[@text='Voicemail']")
	public WebElement voicemail;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/title")
	public List<WebElement> PhonesList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/time")
	public List<WebElement> DateList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/duration")
	public List<WebElement> DurationList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/type")
	public WebElement MissedcallLogo;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/swipe")
	public List<WebElement> SwipeList;
	
	
	@AndroidFindBy(xpath="//*[@text='Verveba56-1']")
	public WebElement contactVerveba56_1;
	
	@AndroidFindBy(xpath="//*[@text='1(360) 213-5057']")
	public WebElement Verveba56_1_Number;

	@AndroidFindBy(xpath="//*[@text='11:16 PM']")
	public WebElement Verveba56_1_Time;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibSpeaker")
	public WebElement speakerOn;
	
	@AndroidFindBy(xpath="//*[@text='Calling (360) 213-4197']")
	public WebElement callingstatus4197;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvRemoteLine")
	public WebElement callingstatus;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvEndCall")
	public WebElement EndCall;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvNumber")
	public WebElement CallDetailNumber;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/timeText")
	public List<WebElement> CallDetailNumberTime;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/type")
	public List<WebElement> calllogolist;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/title")
	public List<WebElement> allcalllist;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/type")
	public WebElement calllogo;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/internal_recycler_view_id")
	public WebElement ListView;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_name")
	public WebElement LinesSelection;
	
	@AndroidFindBy(xpath="//*[@text='(425) 246-4303']")
	public WebElement Line4303;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvNumber")
	public WebElement CallNo;
	
}
