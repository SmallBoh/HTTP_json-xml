package com.example.liugaopodemojson.bean;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Serializable{

	/**
	 * –Ú¡–ªØid
	 */
	private static final long serialVersionUID = 2L;
	
	private int id;
	private String name;
	private int age;
	private Score score;
	private String[] telephone;
	private String imgUrl;
	public Student(int id, String name, int age, Score score,
			String[] telephone, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
		this.telephone = telephone;
		this.imgUrl = imgUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public String[] getTelephone() {
		return telephone;
	}
	public void setTelephone(String[] telephone) {
		this.telephone = telephone;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", score=" + score + ", telephone="
				+ Arrays.toString(telephone) + ", imgUrl=" + imgUrl + "]";
	}
}
