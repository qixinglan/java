package com.nci.dcs.system.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
//zjc adds
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
// 注解Entity表示该类能被Hibernate持久化
@Table(name = "CC_FUNCTIONS")
// 指定该Entity对应的数据表名
public class Authorization implements Serializable{
	private Integer id;
	private String name;// 功能名称
	private String address;// 功能Url
	private String type; // 功能类型
	private Integer order; // 排序

	private Authorization parent;
	private Set<Authorization> children = new HashSet<Authorization>();
	private Set<Role> roles;

	@Id
	// 指定该列为主键
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g")
	// 设定主键增长类型
	@SequenceGenerator(name = "g", sequenceName = "SEQ_T_AUTHORIZATION")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FUNCTIONNAME")
	// 指定属性对应的数据库表的列为name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FUNCTIONURL")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToMany
	@JoinTable(name = "CC_ROLESFUNCTIONS", joinColumns = @JoinColumn(name = "FUNCTIONID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne
	@JoinColumn(name = "PARENTID")
	public Authorization getParent() {
		return parent;
	}

	public void setParent(Authorization parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	public Set<Authorization> getChildren() {
		return children;
	}

	public void setChildren(Set<Authorization> children) {
		this.children = children;
	}

	@Column(name = "FUNCTIONTYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ORDERID")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
