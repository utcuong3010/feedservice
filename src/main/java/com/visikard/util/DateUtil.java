package com.visikard.util;

import java.util.Date;

public class DateUtil {
	
	
	/***
	 * get current date time in milis econd
	 * @return
	 */
	public static String getDateTimeForNow() {
		Date date = new Date();
		return date.toString();
	}
	
	

}
