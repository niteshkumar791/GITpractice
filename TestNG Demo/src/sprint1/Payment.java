package sprint1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Payment extends Base {

	
	@Test(dataProvider="getdata")
	public void tc04(String un, String pwd) {
		
		System.out.println("Username is = " + un);
		System.out.println("Password is = " + pwd);
		
	}
	
	
	@DataProvider
	public Object[][] getdata(){
		
		Object[][] arr = new Object[2][2];
		
		arr[0][0] ="un1";
		arr[0][1] ="pwd1";
		
		arr[1][0] ="un2";
		arr[1][1] ="pwd2";
		
		return arr;
	}
	

}
