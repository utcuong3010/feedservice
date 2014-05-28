package com.visikard.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.visikard.common.SocialFeedType;
import com.visikard.dto.SocialFeedInfo;

public class InsertSocialFeedServiceTest {
	

	
	public static void main(String[] args) {
		
	
		
		InsertSocialFeedServiceTest test = new InsertSocialFeedServiceTest();
		test.insertFeed();
		
	
		
	}
	
	public void insertFeed() {
		
		int num = 100;
		for(int i =0 ;i<num;i++) {
			final int t = 1;
//		
//			 Thread thread = new Thread() {
//				@Override
//				public void run() {
					//for(int j =0 ;j<100 ;j++) {
					SocialFeedService feedService = new SocialFeedServiceImpl();
					
					System.out.println("Thread[" + t + "] is starting");
					Date start = new Date();
					//test function below
					SocialFeedInfo feedInfo = new SocialFeedInfo();
					feedInfo.setContent("wrong this is mad" + i);
					feedInfo.setType(SocialFeedType.TEXT);
					
					//friend kards
					Set<Long> friends = new HashSet<Long>();
					
					feedService.addFeed(t, feedInfo, friends);
					
					//end test
					Date end = new Date();
					long second = end.getTime() - start.getTime();
					second = (second / 1000) % 60;
					
					
					System.out.println("Thread[" + t+ "] has done with the time:"+second);
					//}
					
					
				};
//			};
//			
//			thread.start();
//		}
//		
		
		
		
	}

}
