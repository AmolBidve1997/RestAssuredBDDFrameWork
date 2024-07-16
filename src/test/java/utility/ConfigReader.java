package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public  String readConfigData(String propertyToRead) throws IOException {

		Properties prop = new Properties();

		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

		FileInputStream fis = new FileInputStream(path);

		prop.load(fis);

		String value = prop.getProperty(propertyToRead);

		return value;

	}

}
