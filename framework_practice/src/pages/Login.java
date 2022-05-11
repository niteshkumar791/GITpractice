package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {

	
	WebDriver driver;
	@FindBy(xpath="//*[@id=\"txtUsername\"]")
	WebElement user_name;
	
	@FindBy(xpath="//*[@id=\"txtPassword\"]")
	WebElement login_password;

	@FindBy(xpath="//*[@id=\"btnLogin\"]")
	WebElement submit_btn_click;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/h1")
	WebElement dashboard;
	
//constructor
public Login(WebDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

//page methods
public void dologin(String username,String password) {
	
	user_name.sendKeys(username);
	login_password.sendKeys(password);
	submit_btn_click.submit();
}


public Boolean IsSuccessfullLogin() {
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOf(dashboard));
	if(dashboard.getText().equalsIgnoreCase("Dashboard"))
		return true;
	else
		return false;
		
}

}
