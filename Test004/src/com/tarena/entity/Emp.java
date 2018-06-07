package com.tarena.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Emp implements Serializable {

	private Integer id;
	private String name;
	private Integer age;
	private Double salary;
	private Boolean marry;
	private Date birthday;
	private Timestamp lastLoginTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Boolean getMarry() {
		return marry;
	}
	public void setMarry(Boolean marry) {
		this.marry = marry;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + ", marry=" + marry + ", birthday="
				+ birthday + ", lastLoginTime=" + lastLoginTime + "]";
	}
	
	
	
}
