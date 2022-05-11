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

public class cDigits_Messages {

	public cDigits_Messages(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.mavenir.ucctest:id/etInput")
	public WebElement enterRecepient;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvName")
	public List<WebElement> receipentNamesList;
	
	
	@AndroidFindBy(xpath="//*[@text='Verveba56-2']")
	public WebElement verveba56_2Receipient;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/btn_next_compose_msg")
	public WebElement nextButton;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/etMessage")
	public WebElement messageEnter;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/btnSendMessage")
	public WebElement messageSendButton;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tv_message_sent_status")
	public WebElement SentStatus;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/toolbar_title")
	public WebElement AddContact;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/add_button")
	public WebElement AddContactButton;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtMessage")
	public List<WebElement> textMessagesReceived;
	
	@AndroidFindBy(xpath="Jul 20")
	public List<WebElement> Msg1time;
	
	@AndroidFindBy(xpath="Jul 19")
	public List<WebElement> Msg2time;
	
	@AndroidFindBy(xpath="Jul 18")
	public List<WebElement> Msg3time;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtConversationParticipantName")
	public List<WebElement> MsgList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/swipe")
	public List<WebElement> SwipeList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtConversationTime")
	public List<WebElement> DateList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtMessage")
	public List<WebElement> TxtMsgList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/fab_new_message")
	public WebElement newMessageIcon;
	//
	
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txt_delete")
	public WebElement deleteIcon;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/viewMore")
	public WebElement viewMore_PMmessage;
	
	@AndroidFindBy(className="android.view.View")
	public WebElement viewPMmessage;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibAttachment")
	public WebElement ATTACHFILE;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/bSelectPicture")
	public WebElement ATTACHFILEpicture;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/thumbnail")
	public List<WebElement> ImageList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/thumbnail_image")
	public List<WebElement> MsgImageList;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/send_holder")
	public WebElement FileSelectedSend;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/ibAttachment")
	public WebElement AttachFileButton;
	
	
	@AndroidFindBy(xpath="//*[@text='Photos']")
	public WebElement SelectPhotoButton;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/thumbnail")
	public List <WebElement> SelectImage;
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/send_media")
	public WebElement SendMedia;
	
	@AndroidFindBy(xpath="//*[@text='Delivered']")
	public WebElement DeliveredStatus;
	
	@AndroidFindBy(xpath="//*[@text='All Lines']")
	public WebElement LineSelection;
	
	@AndroidFindBy(xpath="//*[@text='(425) 246-4303']")
	public WebElement Line4303;
	
	@AndroidFindBy(xpath="//*[@text='(425) 246-9832']")
	public WebElement Line9832;
	
	@AndroidFindBy(xpath="//*[@text='(360) 213-4693']")
	public WebElement Line4693;
	
	@AndroidFindBy(xpath="//*[@text='(425) 240-3212']")
	public WebElement Line3212;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_phone")
	public List <WebElement> Selectlinefromdropdown;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_name")
	public WebElement LinesSelection;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/title")
	public WebElement ConfirmDeletePopUp;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/btn_delete")
	public WebElement ConfirmDeleteButton;
	
	
	
}
