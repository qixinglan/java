package com.nci.dcs.em.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcLawEnforcement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_LAW_ENFORCEMENT", schema = "SQJZ")
public class CcLawEnforcement implements java.io.Serializable {

	// Fields

	private String id;
	private CcUserInfo ccUserInfo;
	private String orgId;
	private String equtype;
	private String equnumber;
	private String equstatus;
	private Date procureDate;
	private Date useTime;
	private String MOrgId;

	// Constructors

	/** default constructor */
	public CcLawEnforcement() {
	}

	/** minimal constructor */
	public CcLawEnforcement(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcLawEnforcement(String id, CcUserInfo ccUserInfo, String orgId,
			String equtype, String equnumber, String equstatus,
			Date procureDate, Timestamp useTime, String MOrgId) {
		this.id = id;
		this.ccUserInfo = ccUserInfo;
		this.orgId = orgId;
		this.equtype = equtype;
		this.equnumber = equnumber;
		this.equstatus = equstatus;
		this.procureDate = procureDate;
		this.useTime = useTime;
		this.MOrgId = MOrgId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public CcUserInfo getCcUserInfo() {
		return this.ccUserInfo;
	}

	public void setCcUserInfo(CcUserInfo ccUserInfo) {
		this.ccUserInfo = ccUserInfo;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "EQUTYPE", length = 32)
	public String getEqutype() {
		return this.equtype;
	}

	public void setEqutype(String equtype) {
		this.equtype = equtype;
	}

	@Column(name = "EQUNUMBER", length = 12)
	public String getEqunumber() {
		return this.equnumber;
	}

	public void setEqunumber(String equnumber) {
		this.equnumber = equnumber;
	}

	@Column(name = "EQUSTATUS", length = 3)
	public String getEqustatus() {
		return this.equstatus;
	}

	public void setEqustatus(String equstatus) {
		this.equstatus = equstatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PROCURE_DATE", length = 7)
	public Date getProcureDate() {
		return this.procureDate;
	}

	public void setProcureDate(Date procureDate) {
		this.procureDate = procureDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USE_TIME", length = 7)
	public  Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Column(name = "M_ORG_ID", length = 32)
	public String getMOrgId() {
		return this.MOrgId;
	}

	public void setMOrgId(String MOrgId) {
		this.MOrgId = MOrgId;
	}

}