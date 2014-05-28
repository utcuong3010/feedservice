package com.visikard.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CommonUtil {
	
	
	static final Logger _logger = Logger.getLogger(CommonUtil.class);

	/***
	 * keep last feed id
	 */
	private static long lastFeedId = 0L;
	public static synchronized long getFeedId() {
		_logger.info("Generate new feed id is: "+ (lastFeedId+1));
		return ++lastFeedId;
	}
	
	/***
	 * set config from properties file
	 * @param fileName
	 * @return
	 */
	public static Properties loadConfigFile(String fileName) {
		Properties props = new Properties();
		
		try {
			
			props.load(new FileInputStream(fileName));
			
		} catch(IOException ex) {
			_logger.warn(ex.getMessage());
		} catch (Exception ex) {
			_logger.warn(ex.getMessage());
		}
		
		Enumeration<Object> data = props.keys();
		while(data.hasMoreElements()) {
			String key = (String)data.nextElement();
			String value = (String)props.get(key);
			
			System.setProperty(key, value);
		}
		
		return props;
	}
	
	
}
