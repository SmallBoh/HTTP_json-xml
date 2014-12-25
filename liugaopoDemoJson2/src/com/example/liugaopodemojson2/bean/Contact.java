package com.example.liugaopodemojson2.bean;

import java.io.Serializable;

public class Contact implements Serializable{

	/**
	 * –Ú¡–ªØid
	 */
	private static final long serialVersionUID = 1L;
	
	private String icon;
	private String name;
	private String content;
	private String gender;
	public Contact() {
		super();
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Contact [icon=" + icon + ", name=" + name + ", content="
				+ content + ", gender=" + gender + "]";
	}
}
