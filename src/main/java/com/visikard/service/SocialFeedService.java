package com.visikard.service;

import java.util.List;
import java.util.Set;

import com.visikard.dto.SocialFeedInfo;


/***
 * 
 * @author cuongtv
 * You can post status,comment,..all of operations related as social network
 *
 */

public interface SocialFeedService {
	
	public boolean addFeed(long kardId,SocialFeedInfo feedInfo,Set<Long> friendIds);
	public List<SocialFeedInfo>getFeeds(long kardId, int from, int num);

}
