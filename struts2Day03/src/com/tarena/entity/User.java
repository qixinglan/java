package com.tarena.entity;

public class User {
	private String Name;
	private String password;
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [name=" + Name + ", password=" + password + "]";
	}
	
}
