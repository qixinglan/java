package com.nci.dcs.system.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.googlecode.jsonplugin.annotations.JSON;

@Entity
@Table(name = "CC_Roles")
public class Role implements Serializable{
	private Integer id;
	private String name;
	private String info;
	private Integer role_level;
	private String orgId;
	private Set<User> users;
	private Set<Authorization> authorization;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g")
	@SequenceGenerator(name = "g", sequenceName = "SEQ_T_ROLE")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLENAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@ManyToMany
	@JSON(serialize = false)
	@JoinTable(name = "CC_ROLESFUNCTIONS", joinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "FUNCTIONID", referencedColumnName = "ID"))
	public Set<Authorization> getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Set<Authorization> authorization) {
		this.authorization = authorization;
	}

	@ManyToMany
	@JSON(serialize = false)
	@JoinTable(name = "CC_PERSONROLES", joinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "USERID", referencedColumnName = "ID"))
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Column(name = "ROLE_LEVEL")
	public Integer getRole_level() {
		return role_level;
	}

	public void setRole_level(Integer role_level) {
		this.role_level = role_level;
	}

	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}