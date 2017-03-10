package com.domain.resp;

public class Article {
	private String Title;
	private String Description;
	//图片链接
	private String PicUrl;
	//点击图文消息跳转的链接
	private String Url;
	public String getTitle() {
		return null == Title? "" : Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return null == Description? "" : Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return null == PicUrl? "" : PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return null == Url? "" : Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	

}
