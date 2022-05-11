package functionalTestcase;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.Add_vacancy;
import utility.base;

public class sprint2 extends base {
	
@Test
public void tc01() {
		
		Add_vacancy obj = new Add_vacancy(driver);
		obj.dologin("Admin","admin123");
		
		logger = extent.createTest("tc01","Login tc,positive scenario");
		logger.log(Status.INFO,"Entered username");
		logger.log(Status.INFO, "Entered password");
		logger.log(Status.INFO, "Clicked Login button");
		logger.log(Status.INFO, "mouse hover to recruitment & click on vacancy");
		logger.log(Status.INFO, "Filled all required information");
		
		//Assert.assertTrue(obj.IsSuccessfullLogin());
		
		
	}

	

}
