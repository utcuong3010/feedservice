package com.visikard.util;

import org.apache.log4j.Logger;

public class CommonUtil {
	
	
	static final Logger logger = Logger.getLogger(CommonUtil.class);

	/***
	 * keep last feed id
	 */
	private static long lastFeedId = 0L;
	public static synchronized long getFeedId() {
		logger.info("Generate new feed id is: "+ (lastFeedId+1));
		return ++lastFeedId;
	}
	
	
}
