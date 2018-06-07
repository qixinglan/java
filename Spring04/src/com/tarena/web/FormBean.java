package com.tarena.web;

import java.io.Serializable;

/**
 * 值对象：用来传递值的对象
 * Value Object
 * @author Administrator
 *
 */
public class FormBean implements Serializable{
	String name;
	String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
