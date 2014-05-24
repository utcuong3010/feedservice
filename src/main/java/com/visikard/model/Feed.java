package com.visikard.model;

import java.util.Date;

import com.visikard.common.FeedStatus;
import com.visikard.common.FeedType;

public abstract class Feed {
	private int id;
	private long sender;
	private Message message;
	private FeedStatus status;
	private FeedType type;
	private long receiver;
	private Date date;
	
	
	
	public abstract String getKey();
	public abstract String getValue();
	
	

}
