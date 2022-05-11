package functionalTestcase;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.Login;
import utility.CommonActions;
import utility.base;

public class sprint3_datadriven extends base {
	
	@Test(dataProvider="getdata")
	public void negative01(String user, String pwd) {
		
		Login obj = new Login(driver);
		obj.dologin(user,pwd);
		
		logger = extent.createTest("tc01","Login tc,negative scenario");
		logger.log(Status.INFO,"Entered username");
		logger.log(Status.INFO, "Entered password");
		logger.log(Status.INFO, "Clicked Login button");
	
		Assert.assertFalse(obj.IsSuccessfullLogin());
	}
	
	@Test
	public void positive02() throws IOException {
		
		Login obj1 = new Login(driver);
		CommonActions c= new CommonActions();
		
		Properties pro = c.readProperty();
		obj1.dologin(pro.getProperty("username"), pro.getProperty("pwd"));
		
		logger = extent.createTest("tc02","Login tc,positive scenario");
		logger.log(Status.INFO,"Entered username");
		logger.log(Status.INFO, "Entered password");
		logger.log(Status.INFO, "Clicked Login button");
		
		Assert.assertTrue(obj1.IsSuccessfullLogin());
	}
	
	
	@DataProvider
	public String[][] getdata() throws IOException{
		
		String[][] arr = new String[2][2];
		
		CommonActions c = new CommonActions();
		Properties pro=c.readProperty();
		
		arr[0][0] =pro.getProperty("incorrect_username");
		arr[0][1] =pro.getProperty("incorrect_pwd");
		
		arr[1][0] =pro.getProperty(" ");
		arr[1][1] =pro.getProperty(" ");
		
		return arr;
	}


	@DataProvider
	public Object[][] getpositivedata() throws IOException{
		
		Object[][] arr1 = new Object[2][2];
		
		CommonActions c = new CommonActions();
		Properties pro=c.readProperty();
		
		arr1[0][0] =pro.getProperty("username");
		arr1[0][1] =pro.getProperty("pwd");
		
		return arr1;
	}
	
	
}
