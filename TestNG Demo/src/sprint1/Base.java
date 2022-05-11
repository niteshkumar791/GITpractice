package sprint1;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {
	
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    
    @BeforeSuite
    public void startReport(){

     
           htmlReporter = new ExtentHtmlReporter("G:\\Reportlatest.html");
           extent = new ExtentReports();
           extent.attachReporter(htmlReporter);

           extent.setSystemInfo("OS", "Mac ");
           extent.setSystemInfo("Host Name", "test");
           extent.setSystemInfo("Environment", "QA");
           extent.setSystemInfo("User Name", "Nitesh");
    }
    
    @Parameters({"browser","url"})
	@BeforeMethod
	public void LaunchBrowser(@Optional("chrome")String browser,@Optional("www.google.com")String url) {
		if(browser.equals("chrome"))
			System.out.println("Chrome browser launched");
		else if(browser.equals("ie"))
			System.out.println("IE browser launched");
		
		
	}
    
    @AfterMethod
    public void getResult(ITestResult result){

     if(result.getStatus() == ITestResult.SUCCESS) {

      logger.log(Status.PASS, "Test Case Passed " + result.getName());
     }
    if(result.getStatus() == ITestResult.FAILURE){

      logger.log(Status.FAIL, "Test Case Failed is	"+result.getName() + result.getThrowable());
     }
    else if(result.getStatus() == ITestResult.SKIP){

      logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
     }
}
	
	@AfterMethod
	public void Closebrowser() {
		System.out.println("broser closed");
	}
    
    @AfterSuite
    public void endReport(){

                   extent.flush();
                   //extent.close();
      }
    

}
