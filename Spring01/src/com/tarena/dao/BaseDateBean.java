package com.tarena.dao;

import java.io.Serializable;

public class BaseDateBean implements Serializable{
	String name;
	double height;
	long time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "BsaeDateBean [name=" + name + ", height=" + height + ", time="
				+ time + "]";
	}
	
}
