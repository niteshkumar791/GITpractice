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

public class cDigits_Contacts {

	public cDigits_Contacts(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.mavenir.ucctest:id/top_contact_tab")
	public WebElement localTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/top_fav_tab")
	public WebElement favouriteTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/top_cloud_tab")
	public WebElement cloudTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/edittext_search_contact")
	public WebElement searchInput;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tabIcon")
	public WebElement contactHigligtning;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/contact_tab_highlight")
	public WebElement localTabHighlight;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/fav_tab_highlight")
	public WebElement favouriteTabHighlight;
		
	@AndroidFindBy(id="com.mavenir.ucctest:id/cloud_tab_highlight")
	public WebElement cloudTabHighlight;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtContactName")
	public  List<WebElement> favouriteContactList;
	
	//@AndroidFindBy(id="com.mavenir.ucctest:id/swipe")
	///public List<WebElement> SwipeList;

	
	@AndroidFindBy(xpath="//*[@text='G5+']")
	public WebElement G5contact;
	
	@AndroidFindBy(xpath="//*[@text='Mexico E5']")
	public WebElement MexicoE5contact;
	
	@AndroidFindBy(xpath="//*[@text='Ameen']")
	public WebElement Ameencontact;
	
	@AndroidFindBy(xpath="//*[@text='AAA-1']")
	public WebElement Vervebacontact;
	
	@AndroidFindBy(xpath="//*[@text='Verveba-4610']")
	public WebElement Verveba4610;
	
	@AndroidFindBy(xpath="//*[@text='ATTTestNumber1']")
	public WebElement ATTContact1;
	
	@AndroidFindBy(xpath="//*[@text='ATT-2']")
	public WebElement ATTContact2;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/action_contact_info")
	public WebElement ContactInfo;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/img_fav")
	public WebElement Favirtestaroncontact;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/favorite_view")
	public WebElement Makefavoritebutton;

	@AndroidFindBy(id="com.mavenir.ucctest:id/fab_contact") 
	public WebElement Addcontact;
	
	@AndroidFindBy(xpath="//*[@text='First name']") 
	public WebElement FirstName;
	
	@AndroidFindBy(xpath="//*[@text='Last name']")
	public WebElement LastName;
	
	@AndroidFindBy(xpath="//*[@text='Phone']")
	public WebElement Phone;
	
	@AndroidFindBy(id="com.android.contacts:id/menu_save")
	public WebElement Savebutton;
	
	@AndroidFindBy(xpath="//*[@text='AAAaaa']")
	public WebElement AAAAcontact;
	
	@AndroidFindBy(xpath="//*[@text='Delete']")
	public WebElement Deletecontact;
	
	@AndroidFindBy(xpath="//*[@text='Name']")
	public WebElement ContactName;
	
	@AndroidFindBy(xpath="//*[@text='Phone']")
	public WebElement PhoneNumber;
	
	@AndroidFindBy(id="com.android.contacts:id/menu_save")
	public WebElement ContactSave; 
	
	@AndroidFindBy(id="com.samsung.android.contacts:id/menu_done")
	public WebElement ContactSaveTab; 
	
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtContactName")
	public List<WebElement> favoriteblank;
	
	//Local contact list
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtContactName")
	public List<WebElement> Localcontactlist;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtContactName")
	public List<WebElement> Cloudcontactlist;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/toolbar_more_option")
	public WebElement toolbaroption;
	
	@AndroidFindBy(xpath="//*[@text='A']")
	public WebElement textA;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/action_message")
	public WebElement MessageAction;

	@AndroidFindBy(id="com.mavenir.ucctest:id/action_audio_call")
	public WebElement CallAction;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/alertTitle")
	public WebElement AlerttoChooseLine;
	
	//to choose selected line once again 
	@AndroidFindBy(id="com.mavenir.ucctest:id/contact_profile_name_txt")
	public WebElement MessageAlertLineName;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvNumber")
	public WebElement MessageAlertLineNumberselect;
	
	
	//to choose line from which do further action
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_phone")
	public List<WebElement> ChooseOneLineforMSGorCall;
	
	//
	
	
	@AndroidFindBy(xpath="//*[@text='(360) 213-4693']")
	public WebElement ChooseLine4693;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/txtContactName")
	public List<WebElement> favouritecontactlist;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/swipe")
	public List<WebElement> SwipeList;

	@AndroidFindBy(id="com.mavenir.ucctest:id/toolbar_more_option")
	public WebElement MenuOptions;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvNumber")
	public List<WebElement> SelectContactfor;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/tvType")
	public List<WebElement> TypeOfContactNumber;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/text_phoneline_type")
	public List<WebElement> TypeOfContactNumberTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/messageButton")
	public List<WebElement> MessageActionTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/callButton")
	public List<WebElement> CallActionTab;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/line_name")
	public List<WebElement> SelectLineforphone;
	
	@AndroidFindBy(id="com.mavenir.ucctest:id/alertTitle")
	public WebElement Alertfoselectline;
	
	@AndroidFindBy(id="	com.mavenir.ucctest:id/tvEndCall")
	public WebElement EndCall;
	
	
	@AndroidFindBy(id="com.samsung.android.contacts:id/kind_title_edit")
	public WebElement PhoneNumberTab;
	

}
