package sprint1;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

public class Login extends Base {

	SoftAssert softassrt = new SoftAssert();
	
	@Test(priority=2)
	public void tc01() {
		logger = extent.createTest("tc01","Login tc,negative scenario");
		logger.log(Status.INFO,"Entered username");
		//code for entering username
		logger.log(Status.INFO, "Entered password");
		//code for password entering
		logger.log(Status.INFO, "Clicked Login button");
		//code for login button
		Assert.assertEquals(false, true);
	}
	
	
	@Test(priority=1)
	public void tc02() {
		
		logger = extent.createTest("tc02","Login tc,positive scenario");
		logger.log(Status.INFO,"Nitesh");
		logger.log(Status.INFO, "kumar");
		logger.log(Status.INFO, "mandal");
		
		softassrt.assertEquals(true, true);
		softassrt.assertAll();
		
	}

	
	
}
