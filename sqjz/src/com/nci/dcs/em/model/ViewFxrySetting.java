package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.model.Persons;

/**
 * ViewCcFxrySettingId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_SETTING", schema = "SQJZ")
public class ViewFxrySetting implements java.io.Serializable {

	// Fields

	private String id;
	private OrganizationInfo org;
	private String pname;
	private String name;
	private String adjustType;
	private String railType;
	private String status;
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
	@Column(name = "ID", nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_ID")
	public OrganizationInfo getOrg() {
		return org;
	}

	public void setOrg(OrganizationInfo org) {
		this.org = org;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADJUST_TYPE")
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "RAIL_TYPE")
	public String getRailType() {
		return this.railType;
	}

	public void setRailType(String railType) {
		this.railType = railType;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "PNAME")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}