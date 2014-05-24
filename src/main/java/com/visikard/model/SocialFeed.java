package com.visikard.model;

import java.util.Date;

import com.visikard.dto.SocialFeedInfo;

public class SocialFeed {
	
	private long id;
	private long kardId;//author
	private int type;//text,video,image 
	private String content;
	private int comments;//total comments
	private int likes;//total likes
	private Date date = new Date();
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	public int getType() {
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
	
	@Override
	public String toString() {
		return String.format("Feed[id=%d,kardId=%d,content=%s,comments=%d,likes=%d]",
				this.id,
				this.kardId,
				this.content,
				this.comments,
				this.likes);
	}
		
	
	/***
	 * copy all properties for save feed
	 * @param feedInfo
	 */
	public void copyProperties(SocialFeedInfo feedInfo) {
		this.id = feedInfo.getFeedId();
		this.kardId = feedInfo.getKardId();
		this.content = feedInfo.getContent();
		this.type = feedInfo.getType();
		this.date = feedInfo.getDate();
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
	
	
}
