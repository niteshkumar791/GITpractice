package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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

public class base {
	
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public WebDriver driver;
    
    @BeforeSuite
    public void startReport(){

     
           htmlReporter = new ExtentHtmlReporter("G:\\Reportframework.html");
           extent = new ExtentReports();
           extent.attachReporter(htmlReporter);

           extent.setSystemInfo("OS", "Mac ");
           extent.setSystemInfo("Host Name", "test");
           extent.setSystemInfo("Environment", "QA");
           extent.setSystemInfo("User Name", "Nitesh");
    }
    
    @Parameters({"browser","url"})
	@BeforeMethod
	public WebDriver LaunchBrowser(@Optional("chrome")String browser,@Optional("www.google.com")String url) {
    	if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","G:\\JAVA videos\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
		else if (browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver","G:\\JAVA videos\\batch\\soft\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(url);
		}
		return driver;
		
		
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
		driver.close();
	}
    
    @AfterSuite
    public void endReport(){

                   extent.flush();
                   //extent.close();
      }
    

}