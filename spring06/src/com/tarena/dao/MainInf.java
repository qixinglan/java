package com.tarena.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MainInf {
	String name;
	String phone;
	@Override
	public String toString() {
		return "MainInf [name=" + name + ", phone=" + phone + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
