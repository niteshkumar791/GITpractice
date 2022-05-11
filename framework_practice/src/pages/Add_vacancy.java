package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Add_vacancy {

	WebDriver driver;
	@FindBy(xpath="//*[@id=\"txtUsername\"]")
	WebElement user_name;
	
	@FindBy(xpath="//*[@id=\"txtPassword\"]")
	WebElement login_password;

	@FindBy(xpath="//*[@id=\"btnLogin\"]")
	WebElement submit_btn_click;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/h1")
	WebElement dashboard;
	
	@FindBy(linkText="Recruitment")
	WebElement recruit;
	
	@FindBy(linkText="Vacancies")
	WebElement vacancy;
	
	@FindBy(xpath="//*[@id=\"btnAdd\"]")
	WebElement add_btn;
	
	@FindBy(xpath="//*[@id=\"addJobVacancy\"]/div[1]/h1")
	WebElement add_job_visible;
	
	@FindBy(id="addJobVacancy_jobTitle")
	WebElement addjobvacancy_title;
	
	@FindBy(id="addJobVacancy_name")
	WebElement vacancy_name;
	
	@FindBy(id="addJobVacancy_hiringManager")
	WebElement hiring_manager;
	
	@FindBy(id="addJobVacancy_noOfPositions")
	WebElement No_of_position;
	
	@FindBy(xpath="//*[@id=\"btnSave\"]")
	WebElement save_btn;
	
	
    //constructor
	public Add_vacancy(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//page methods
	public void dologin(String username,String password) {
		
		//Sends username,password and click submit
		user_name.sendKeys(username);
		login_password.sendKeys(password);
		submit_btn_click.submit();
		
		//wait for visiblity of dashboard
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.visibilityOf(dashboard));
		
		//mouse hover to recruitment & click on vacancy
		Actions act = new Actions(driver);
		act.moveToElement(recruit).build().perform();
		vacancy.click();
		
		//click on add vacancy
		wait.until(ExpectedConditions.elementToBeClickable(add_btn)).click();
		
		//wait to visiblity of required fields
		wait.until(ExpectedConditions.visibilityOf(add_job_visible));
		
		//selecting job title from drop down
		Select job_title = new Select(addjobvacancy_title);
		job_title.selectByVisibleText("IT Manager");
		
		vacancy_name.sendKeys("Java developer");
		hiring_manager.sendKeys("Linda Anderson");
		No_of_position.sendKeys("2");
		save_btn.click();
		
		
		
	}
	
}
