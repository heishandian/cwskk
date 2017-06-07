package com.yaoli.vo;

public class VideoUrlTokenVO {
	private String postUrl;
	private String uid;
	private String password;
	private String epid;
	private String puid;
	private String dateString;// =String.valueOf(System.currentTimeMillis()/1000L);
	
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEpid() {
		return epid;
	}
	public void setEpid(String epid) {
		this.epid = epid;
	}
	public String getPuid() {
		return puid;
	}
	public void setPuid(String puid) {
		this.puid = puid;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	 
}
