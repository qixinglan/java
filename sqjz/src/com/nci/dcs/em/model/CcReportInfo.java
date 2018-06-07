package com.nci.dcs.em.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcReportInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_REPORT_INFO", schema = "SQJZ")
public class CcReportInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private Date reportDate;
	private Date realReportDate;
	private String reportType;
	private String reportOrgId;
	private String affirmUserId;
	private Timestamp affirmTime;
	private String remark;

	// Constructors

	/** default constructor */
	public CcReportInfo() {
	}

	/** minimal constructor */
	public CcReportInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcReportInfo(String id, String personId, Date reportDate,
			Date realReportDate, String reportType, String reportOrgId,
			String affirmUserId, Timestamp affirmTime, String remark) {
		this.id = id;
		this.personId = personId;
		this.reportDate = reportDate;
		this.realReportDate = realReportDate;
		this.reportType = reportType;
		this.reportOrgId = reportOrgId;
		this.affirmUserId = affirmUserId;
		this.affirmTime = affirmTime;
		this.remark = remark;
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

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE", length = 7)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REAL_REPORT_DATE", length = 7)
	public Date getRealReportDate() {
		return this.realReportDate;
	}

	public void setRealReportDate(Date realReportDate) {
		this.realReportDate = realReportDate;
	}

	@Column(name = "REPORT_TYPE", length = 5)
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "REPORT_ORG_ID", length = 32)
	public String getReportOrgId() {
		return this.reportOrgId;
	}

	public void setReportOrgId(String reportOrgId) {
		this.reportOrgId = reportOrgId;
	}

	@Column(name = "AFFIRM_USER_ID", length = 32)
	public String getAffirmUserId() {
		return this.affirmUserId;
	}

	public void setAffirmUserId(String affirmUserId) {
		this.affirmUserId = affirmUserId;
	}

	@Column(name = "AFFIRM_TIME", length = 11)
	public Timestamp getAffirmTime() {
		return this.affirmTime;
	}

	public void setAffirmTime(Timestamp affirmTime) {
		this.affirmTime = affirmTime;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}