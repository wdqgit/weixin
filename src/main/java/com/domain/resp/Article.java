package com.domain.resp;

public class Article {
	private String Title;
	private String Description;
	//ͼƬ����
	private String PicUrl;
	//���ͼ����Ϣ��ת������
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
