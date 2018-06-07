package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcOrganizationInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ORGANIZATION_INFO", schema = "SQJZ")
public class CcOrganizationInfo implements java.io.Serializable {

	// Fields

	private String orgId;
	private String orgName;
	private String cname;
	private String address;
	private String description;
	private String orgCode;
	private String orgType;
	private Long orgStatus;
	private String fax;
	private String supOrgId;
	private String phone;
	private Integer postalCode;
	private String contact;

	// Constructors

	/** default constructor */
	public CcOrganizationInfo() {
	}

	/** minimal constructor */
	public CcOrganizationInfo(String orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public CcOrganizationInfo(String orgId, String orgName, String cname,
			String address, String description, String orgCode, String orgType,
			Long orgStatus, String fax, String supOrgId, String phone,
			Integer postalCode, String contact) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.cname = cname;
		this.address = address;
		this.description = description;
		this.orgCode = orgCode;
		this.orgType = orgType;
		this.orgStatus = orgStatus;
		this.fax = fax;
		this.supOrgId = supOrgId;
		this.phone = phone;
		this.postalCode = postalCode;
		this.contact = contact;
	}

	// Property accessors
	@Id
	@Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ORG_CODE", length = 32)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_TYPE", length = 5)
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "ORG_STATUS", precision = 10, scale = 0)
	public Long getOrgStatus() {
		return this.orgStatus;
	}

	public void setOrgStatus(Long orgStatus) {
		this.orgStatus = orgStatus;
	}

	@Column(name = "FAX", length = 32)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return this.supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "PHONE", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "POSTAL_CODE", precision = 8, scale = 0)
	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "CONTACT", length = 64)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}