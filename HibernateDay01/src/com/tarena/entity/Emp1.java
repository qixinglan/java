package com.tarena.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * 注解式实体类，就不用写映射配置文件了
 * 但是注意在主配置文件中写入
 * <mapping class="com.tarena.entity.Emp1"/>
 * 注解可以写在变量声明前也可写在set方法前面
 */
@Entity(name="Emp")
public class Emp1 {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_seq")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private Integer age;
	@Column(name="salary")
	private Double salary;
	@Column(name="marry")
	private Boolean marry;
	@Column(name="birthday")
	private Date birthday;
	@Column(name="lastLoginTime")
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
	
}
