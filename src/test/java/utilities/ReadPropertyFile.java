package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ReadPropertyFile {
	private Properties properties;

	public ReadPropertyFile(String propertyFilePath) {
		properties = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties not found at " + propertyFilePath);
		}		

	}
	
	public String getProp(String key) {
		String value= properties.getProperty(key);
		if(value!=null)
			return value;
		else
			throw new RuntimeException(key+" not found in properties file");
	}
}
