package com.visikard.common;

public enum FeedStatus {
	
	NEW("new"),READ("read");
	
	private String status;
	
	private FeedStatus(String status) {
		this.status = status;
	}

}
