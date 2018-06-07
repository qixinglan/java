package com.nci.sfj.business.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Description:机构信息
 * 
 * @author MyEclipse Persistence Tools
 * @since 2014年5月23日下午2:06:26
 */
@Entity
@Table(name = "CC_ORGANIZATION_INFO", schema = "SQJZ")
public class OrgXmpp implements java.io.Serializable {

	// Fields

	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 机构类别
	 */
	private String orgType;

	private String orgName;
	/**
	 * 子机构
	 */
	private Set<OrgXmpp> subOrg = new HashSet<OrgXmpp>(0);
	/**
	 * 上级机构
	 */
	private OrgXmpp supOrg;
	/**
	 * 固定电话
	 */
	private String phone;

	// Property accessors
	@Id
	@Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_TYPE", length = 5)
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUP_ORG_ID")
	public OrgXmpp getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(OrgXmpp supOrg) {
		this.supOrg = supOrg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "supOrg")
	public Set<OrgXmpp> getSubOrg() {
		return subOrg;
	}

	public void setSubOrg(Set<OrgXmpp> subOrg) {
		this.subOrg = subOrg;
	}

	@Column(name = "ORG_NAME")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}