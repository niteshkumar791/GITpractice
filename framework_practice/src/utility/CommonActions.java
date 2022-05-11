package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonActions {

	public Properties readProperty() throws IOException {

		FileInputStream file = new FileInputStream(new File("Data.properties"));
		     
		Properties pro = new Properties();
		pro.load(file);

		return pro;
		//pro.getProperty("UserName");  // admin
		}

}
