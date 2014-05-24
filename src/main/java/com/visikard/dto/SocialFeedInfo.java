package com.visikard.dto;

import java.util.Date;

import com.visikard.model.SocialFeed;

public class SocialFeedInfo {
	
	private long feedId;
	private long kardId;
	private int type;//text,imag or video
	private String content;
	private int comments;
	private int likes;
	
	private Date date = new Date();
	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}
	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	/**
	 * @return the kardId
	 */
	public long getKardId() {
		return kardId;
	}
	/**
	 * @param kardId the kardId to set
	 */
	public void setKardId(long kardId) {
		this.kardId = kardId;
	}
	/**
	 * @return the type
	 */
	public int isType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(int comments) {
		this.comments = comments;
	}
	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}
	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return String.format("Feed[id=%d,kardId=%d,content=%s,comments=%d,likes=%d]",
				this.feedId,
				this.kardId,
				this.content,
				this.comments,
				this.likes);
	}
		
	
	
	public void copyProperties(SocialFeed feed) {
		this.feedId = feed.getId();
		this.kardId = feed.getKardId();
		this.content = feed.getContent();
		this.type = feed.getType();
		this.comments = feed.getComments();
		this.likes = feed.getLikes();
		this.date = feed.getDate();
	}
	
	
}
