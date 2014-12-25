package com.example.liugaopodemojson.bean;

import java.io.Serializable;

public class Score implements Serializable{

	/**
	 * –Ú¡–ªØid
	 */
	private static final long serialVersionUID = 1L;
	
	private int math;
	private int english;
	private int chinese;
	public Score(int math, int english, int chinese) {
		super();
		this.math = math;
		this.english = english;
		this.chinese = chinese;
	}
	public Score() {
		super();
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Score [math=" + math + ", english=" + english + ", chinese="
				+ chinese + "]";
	}

}
