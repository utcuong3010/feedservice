package com.visikard.service;

import kt4j.KyotoTycoonClient;
import kt4j.binary.KyotoTycoonBinaryClient;

import com.visikard.common.SocialFeedType;
import com.visikard.dto.SocialFeedInfo;
import com.visikard.util.JsonUtil;

public class SocailFeedServiceTest {
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("testing...");
		String hostname ="localhost";
		int port = 1978;
		
		KyotoTycoonClient client = new KyotoTycoonBinaryClient(hostname, port);
		client.start();
		
		
		SocialFeedInfo feedInfo = new SocialFeedInfo();
		feedInfo.setContent("wrong this is mad dvds hfksjdbv");
		feedInfo.setType(SocialFeedType.TEXT);
		//set data
		client.set("feed_1003efwe",JsonUtil.toJson(feedInfo));
		System.out.println(client.get("feed_1003efwe"));
		
		
		
		//client.stop();
		
	}
	
	

}
