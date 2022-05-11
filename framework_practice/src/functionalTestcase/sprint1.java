package functionalTestcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.Login;
import utility.base;

public class sprint1 extends base {

	@Test
	public void tc01() {				
		
		logger = extent.createTest("tc01","Login tc,positive scenario");
		logger.log(Status.INFO,"Entered username");
		logger.log(Status.INFO, "Entered password");
		logger.log(Status.INFO, "Clicked Login button");
		
		Login obj = new Login(driver);
		obj.dologin("Admin","admin123");
		
		
		Assert.assertTrue(obj.IsSuccessfullLogin());
		
		
	}
	
}
