package com.visikard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kt4j.KyotoTycoonClient;
import kt4j.binary.KyotoTycoonBinaryClient;

import org.apache.log4j.Logger;

import com.visikard.common.Constant;
import com.visikard.dto.SocialFeedInfo;
import com.visikard.model.SocialFeed;
import com.visikard.util.CommonUtil;
import com.visikard.util.JsonUtil;

public class SocialFeedServiceImpl implements SocialFeedService {

	final String hostname = "localhost";
	final int port = 1978;

	KyotoTycoonClient client;
	
	public SocialFeedServiceImpl() {
		client = new KyotoTycoonBinaryClient(hostname, port);
		client.start();
		
	}

	/** log4j **/
	static final Logger logger = Logger.getLogger(SocialFeedServiceImpl.class);

	/**
	 * get next id for feed
	 * 
	 * @return
	 */
	
	

	/***
	 * get feed list with format kard_id, {feed_id}
	 */
	public List<SocialFeedInfo> getAllFeeds(long kardId) {

		List<SocialFeedInfo> feedInfos = new ArrayList<SocialFeedInfo>();
		String kardFeedKey = Constant.KARD_FEEDS_PREFIX + Constant.UNDER_SCORE + kardId;
		String kardFeedsValue = client.get(kardFeedKey);
		if(kardFeedsValue != null) {
			//part separate id by semicolon
			String[] feedsId = kardFeedsValue.split(Constant.SEMICOLON);
			List<String>feedKeys = new ArrayList<String>();
			for (String feedId : feedsId) {
				String feedKey = Constant.FEED_PREFIX + Constant.UNDER_SCORE +
						feedId;
				feedKeys.add(feedKey);
				
			}
			if(!feedKeys.isEmpty()) {
				Map<String,String>data = client.getBulkString(feedKeys);
				for (String key : data.keySet()) {
					SocialFeed feed = JsonUtil.toObject(data.get(key),SocialFeed.class);
					//set feed info
					SocialFeedInfo feedInfo = new SocialFeedInfo();
					//copy properties
					feedInfo.copyProperties(feed);
					
					//add list
					feedInfos.add(feedInfo);
				}
				//sort desc feed id collections
				Collections.sort(feedInfos, new Comparator<SocialFeedInfo>() {

					public int compare(SocialFeedInfo feedInfo1, SocialFeedInfo feedInfo2) {
						long feedId1 = feedInfo1.getFeedId() ;
						long feedId2 = feedInfo2.getFeedId();
						
						return feedId2 < feedId1 ?-1: feedId2 == feedId1? 0: 1;
						
					}
				});
			}
		}
		return feedInfos;
	}

	/***
	 * all put into queue then process sequencely key=kard_id value=array
	 * feed_id FEEDS_{kard_id},feed_id1,feed_id2...
	 * store first in ,first out
	 * FEED_id,{content obj}
	 */
	public boolean addFeed(long kardId, SocialFeedInfo feedInfo,
			Set<Long> friendIds) {
		try {
		//1 create new feed id (FEED_{feedId}
		long nextFeedId = CommonUtil.getFeedId();
		String feedKey = Constant.FEED_PREFIX + Constant.UNDER_SCORE 
				+ nextFeedId;
		
		//save feed
		SocialFeed feed = new SocialFeed();
		feed.copyProperties(feedInfo);
		feed.setId(nextFeedId);
		feed.setKardId(kardId);
		
		//save the feed		
		client.set(feedKey, JsonUtil.toJsonUTF8(feed));
		logger.info("Added New feed: "+feed);
			
		//put the feed to owner and friends
		friendIds.add(kardId);//add for owner
		Map<String, String> map = new HashMap<String, String>();
		for (long id : friendIds) {
			String kardFeedsKey = Constant.KARD_FEEDS_PREFIX+Constant.UNDER_SCORE+ id;
			String feedIds = client.get(kardFeedsKey);
			if(feedIds != null) {
				feedIds = nextFeedId + Constant.SEMICOLON + feedIds;
			}else {
				feedIds = String.valueOf(nextFeedId);
			}
			//put in map data
			map.put(kardFeedsKey, feedIds);
		}
		//insert to kyoto
		client.setBulkString(map);
		//update last feed key
		String lastFeedId = client.get(Constant.LAST_FEED_ID);
		if(lastFeedId == null || nextFeedId > Long.valueOf(lastFeedId)) 
			client.set(Constant.LAST_FEED_ID, String.valueOf(nextFeedId));
		
		return true;
		
		} catch (Exception e) {
			logger.error("Add new feed has the error:" + e);
		}
		return false;
	}
}
