package com.nci.dcs.data.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CcIgnoreOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_IGNORE_ORG", schema = "SQJZ")
public class CcIgnoreOrg implements java.io.Serializable {

	// Fields

	private String id;
	private String ignoreId;
	private String orgId;
	private String orgName;
	private CcGatherAlarmIgnore ccGatherAlarmIgnore;
	
	@Column(name = "IGNORE_ID", nullable = true, length = 32)
	public String getIgnoreId() {
		return ignoreId;
	}

	public void setIgnoreId(String ignoreId) {
		this.ignoreId = ignoreId;
	}
	@Column(name = "ORG_ID", nullable = true, length = 32)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "ORG_Name", nullable = true, length = 90)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	// Constructors

	/** default constructor */
	public CcIgnoreOrg() {
	}

	// Property accessors
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	//@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IGNORE_ID", nullable = false, insertable = false, updatable = false)
	public CcGatherAlarmIgnore getCcGatherAlarmIgnore() {
		return this.ccGatherAlarmIgnore;
	}

	public void setCcGatherAlarmIgnore(CcGatherAlarmIgnore ccGatherAlarmIgnore) {
		this.ccGatherAlarmIgnore = ccGatherAlarmIgnore;
	}

}