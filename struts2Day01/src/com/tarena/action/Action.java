package com.tarena.action;

import com.tarena.entity.User;

public class Action {
	String name;
	String password;
	User user;
	public Action(){
		System.out.println("ʵ����Action");
	}
	public String helloStruts2(){
		System.out.println(name);
		System.out.println(password);
		System.out.println(user);
		return "ok";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("ʵ������Ա����name");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		System.out.println("ʵ������Ա����password");
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		System.out.println("ʵ������Ա����user");
	}
	
}	
