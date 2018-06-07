package com.tarena.entity;

public class Worker {
	private String name;
	private String pwd;
	private String phone;
	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Worker(String name, String pwd, String phone) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.phone = phone;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Worker [name=" + name + ", pwd=" + pwd + ", phone=" + phone
				+ "]";
	}
	
	
}
