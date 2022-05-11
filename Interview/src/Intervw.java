import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.sun.jna.platform.FileUtils;

public class Intervw {

	public static void main(String[] args) {
		

	}
	
	public static void Screeshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		
		FilenameUtils.cop
}
	
	UPDATE employee set sal=80000 where first_name='Nit';
