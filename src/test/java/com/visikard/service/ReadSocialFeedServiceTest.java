package com.visikard.service;

import java.util.List;

import com.visikard.dto.SocialFeedInfo;


public class ReadSocialFeedServiceTest {
	
	
	public static void main(String[] args) {
		
		SocialFeedService feedService = new SocialFeedServiceImpl();
//		
		List<SocialFeedInfo> list = feedService.getFeeds(1,90,20);
//		
		System.out.println(list.size());
		System.out.println(list);
		
	
	}

}
