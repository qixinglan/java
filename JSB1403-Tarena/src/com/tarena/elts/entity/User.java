package com.tarena.elts.entity;

import java.io.Serializable;

/**
 * 用户的实体类
 * @author asus
 *
 */

public class User implements Serializable{
	private String name;
	private int Id;
	private String password;
	private String phonenumber;
	private String email;
	
	public User() {
		super();
	}

	public User(String name, int id, String password) {
		super();
		this.name = name;
		Id = id;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return name+",id为" +Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
