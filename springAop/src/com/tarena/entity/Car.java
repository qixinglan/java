package com.tarena.entity;

/**
 *	小汽车实体类，封装小汽车相关字段
 */
public class Car extends Vehicle {

	private int doorSize;

	public int getDoorSize() {
		return doorSize;
	}

	public void setDoorSize(int doorSize) {
		this.doorSize = doorSize;
	}
}
