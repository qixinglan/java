package com.tarena.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.tarena.entity.User;

public class Action {
	String name="π»¡¡";
	String password="1234";
	User user;
	public Action(){
		System.out.println("Action≥ı ºªØ");
	}
		public String helloStruts2(){
			HttpServletRequest r=ServletActionContext.getRequest();
			r.setAttribute("res","request");
			HttpSession session =r.getSession();
			session.setAttribute("s", "session");
			name="1";
			password="2";
			user=new User();
			user.setName("3");
			user.setPassword("4");
			return "ok";
		}
	public String helloStruts(){
		name="bbbbbb";
		user=new User();
		user.setPassword("bbbbbbbbbbbbbbb");
		return "ok";
	}
	public String execute(){
		return "success";
	}
	public void login(){
		System.out.println(name);
		System.out.println(password);
		System.out.println(user.getName());
		System.out.println(user.getPassword());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setName");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		System.out.println("setpassword");
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		System.out.println("setUser");
	}
	
}
