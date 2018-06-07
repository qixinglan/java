package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcOutmanageInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_OUTMANAGE_INFO", schema = "SQJZ")
public class CcOutmanageInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private Date startDate;
	private Date endDate;
	private String reason;
	private Date recordTime;
	private String orgId;
	private String remark;

	// Constructors

	/** default constructor */
	public CcOutmanageInfo() {
	}

	/** minimal constructor */
	public CcOutmanageInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcOutmanageInfo(String id, String personId, Date startDate,
			Date endDate, String reason, Date recordTime, String orgId,
			String remark) {
		this.id = id;
		this.personId = personId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.recordTime = recordTime;
		this.orgId = orgId;
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
	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "REASON", length = 5)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECORD_TIME", length = 7)
	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}