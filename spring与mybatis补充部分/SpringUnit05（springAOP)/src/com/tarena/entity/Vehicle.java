package com.tarena.entity;

/**
 *	交通工具实体类，封装汽车表中公用的字段
 */
public class Vehicle {

	private int id;
	private String type;
	private String color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
