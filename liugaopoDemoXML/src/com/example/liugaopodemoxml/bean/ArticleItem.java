package com.example.liugaopodemoxml.bean;

import java.io.Serializable;

public class ArticleItem implements Serializable{

	/**
	 * –Ú¡–ªØid
	 */
	private static final long serialVersionUID = 1L;


	private String thumbnail;
	private String title;
	private String pubtime;
	private String source;
	public ArticleItem() {
		super();
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ArticleItem [thumbnail=" + thumbnail + ", title=" + title
				+ ", pubtime=" + pubtime + ", source=" + source + "]";
	}

}
