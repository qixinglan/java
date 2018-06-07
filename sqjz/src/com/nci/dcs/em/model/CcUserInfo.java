package com.nci.dcs.em.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CcUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_USER_INFO", schema = "SQJZ")
public class CcUserInfo implements java.io.Serializable {

	// Fields

	private String userId;
	private String orgId;
	private String userMane;
	private String sex;
	private String password;
	private String idcard;
	private Set<CcLawEnforcement> ccLawEnforcements = new HashSet<CcLawEnforcement>(
			0);
	// Constructors
	
	/** default constructor */
	public CcUserInfo() {
	}

	/** minimal constructor */
	public CcUserInfo(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public CcUserInfo(String userId, String orgId, String userMane, String sex,
			String password, String idcard,
			Set<CcLawEnforcement> ccLawEnforcements) {
		this.userId = userId;
		this.orgId = orgId;
		this.userMane = userMane;
		this.sex = sex;
		this.password = password;
		this.idcard = idcard;
		this.ccLawEnforcements = ccLawEnforcements;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "USER_MANE", length = 64)
	public String getUserMane() {
		return this.userMane;
	}

	public void setUserMane(String userMane) {
		this.userMane = userMane;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "PASSWORD", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "IDCARD", length = 30)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccUserInfo")
	public Set<CcLawEnforcement> getCcLawEnforcements() {
		return this.ccLawEnforcements;
	}

	public void setCcLawEnforcements(Set<CcLawEnforcement> ccLawEnforcements) {
		this.ccLawEnforcements = ccLawEnforcements;
	}
}