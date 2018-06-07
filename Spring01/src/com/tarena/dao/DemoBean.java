package com.tarena.dao;

import java.io.Serializable;

public class DemoBean implements Serializable{
	String name;
	int age;
	public DemoBean(){
	}
	public DemoBean(String name,int age){
		this.name=name;
		this.age=age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DemoBean [name=" + name + ", age=" + age + "]";
	}
	
	
}
