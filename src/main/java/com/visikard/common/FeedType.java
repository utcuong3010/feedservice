package com.visikard.common;

public enum FeedType {
	SOCIAL_FEED("social_feed"),NOTIFICATION_FEED("notification_feed"),EAR_FEED("earn_feed");
	private String type;
	
	private FeedType(String type) {
		this.type = type;
	}
}
