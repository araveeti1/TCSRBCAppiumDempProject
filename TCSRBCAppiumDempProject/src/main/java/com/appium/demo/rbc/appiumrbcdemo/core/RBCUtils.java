package com.appium.demo.rbc.appiumrbcdemo.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class RBCUtils {
	
	public  String readProperty(String property) {
		Properties prop;
		String value = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("RBCconfig.properties")));
			
			value = prop.getProperty(property);
			
			if (value == null || value.isEmpty()) {
				throw new Exception("Value not set or empty");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}

}
