package com.tarena.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.tarena.entity.User;

public class Action1 implements SessionAware{
	List<User> users;
	public String ognl(){
		User user=new User();
		user.setName("¹È");
		user.setPassword("1234");
		User user1=new User();
		user1.setName("¹È1");
		user1.setPassword("1234");
		User user2=new User();
		user2.setName("¹È2");
		user2.setPassword("1234");
		users=new ArrayList<User>();
		users.add(user);
		users.add(user1);
		users.add(user2);
		System.out.println(users);
		return "ok";
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
