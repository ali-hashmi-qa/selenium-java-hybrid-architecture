package utils;

import java.io.FileInputStream;
import java.util.Properties;

import constants.FrameworkConstants;

public class ConfigReader {
	
	private static Properties properties;
	
	static {
	try {
		FileInputStream file = new FileInputStream(FrameworkConstants.Config_File_Path);
		properties = new Properties();
		properties.load(file);
	} catch (Exception e) {
		throw new RuntimeException("Unable to load file...", e);		
	}
}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
