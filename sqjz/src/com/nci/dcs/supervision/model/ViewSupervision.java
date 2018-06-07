package com.nci.dcs.supervision.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class ViewSupervision implements java.io.Serializable {

	private String id;
	private String name;
	private String sex;
	private String identityCard;
	private String code;
	private String policename;
	private Date createTime;
	private String responseOrg;
	private String supOrg;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	// Property accessors
	@Id
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "IDENTITY_CARD", length = 100)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "POLICENAME", length = 60)
	public String getPolicename() {
		return this.policename;
	}

	public void setPolicename(String policename) {
		this.policename = policename;
	}

	@Column(name = "SUP_ORG_ID", length = 1)
	public String getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(String supOrg) {
		this.supOrg = supOrg;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}