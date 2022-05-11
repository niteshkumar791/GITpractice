package supportForTC;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import cdigitsTC_UI.Login_Tests;
import commonClasses.base;

public class BeforeAfterSuite extends base{
	
	public static final Logger log = Logger.getLogger(Login_Tests.class);

	//@BeforeSuite
	public void runBeforeSuite() throws IOException, InterruptedException {
		log.info(" ************** START NEW TEST SCRIPTS ****************   ");
		
		//kill all the appium nodes, if any
		System.out.println("Killing the appium nodes");
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
				
		int noParallelServers = excelFile.readNoOfParallelDevices();
		
		/*if(noParallelServers==1) {
			service1=startServer(1);
		}
		
		if(noParallelServers==2) {
			service1=startServer(1);
			service2=startServer(2);
		}*/
		startServers(noParallelServers);
		
		excelFile.createNewFileForOutput();
		Thread.sleep(5000);
	}
	
	//@AfterSuite
	public void runAfterSuite() throws IOException, InterruptedException {
		log.info(" ************** END OF TEST SCRIPTS ****************   ");
		
		int noParallelServers = excelFile.readNoOfParallelDevices();
		
		if(noParallelServers==1) {
			service1.stop();
		}
		
		if(noParallelServers==2) {
			service1.stop();
			service2.stop();
		}
	
				
	}

}
