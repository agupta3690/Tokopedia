package utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
==========================================
Title:  Tokopedia | Config Reader
Author: Arun Gupta 
Date:   29 Aug 2022
==========================================
 */

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= "resources/configuration.properties";


	//Method to read the properties file
	public ConfigFileReader(){
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
		}		
	}

	//Method to set the order process path params
	public String getOrderProcessPathParams(){
		String reportConfigPath = properties.getProperty("orderProcessPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Order Process Path not specified in the Configuration.properties file for the orderProcessPath");		
	}



	//Method to set the app URL. Relevant in case of UI autmation cases
	public String getApplicationUrl() {
		String url = properties.getProperty("testAppUrl");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}


}
