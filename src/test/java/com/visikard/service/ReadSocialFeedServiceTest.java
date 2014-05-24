package com.visikard.service;

import java.util.List;

import com.visikard.dto.SocialFeedInfo;

public class ReadSocialFeedServiceTest {
	
	
	public static void main(String[] args) {
		
		SocialFeedService feedService = new SocialFeedServiceImpl();
//		
		List<SocialFeedInfo> list = feedService.getAllFeeds(12);
//		
		System.out.println(list);
		
	
	}

}
