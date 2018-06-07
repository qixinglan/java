package com.tarena.action;

import java.util.ArrayList;
import java.util.List;

import com.tarena.entity.User;

public class Action {
	private String name="Tom";
	private User user;
	private List<User> users;
	public String helloStruts2(){
		user=new User();
		user.setName("Tarena");
		user.setPassword("123456");
		User user1=new User();
		user1.setName("Tarena1");
		user1.setPassword("123456");
		User user2=new User();
		user2.setName("Tarena2");
		user2.setPassword("123456");
		users=new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		return "ok";
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}	
