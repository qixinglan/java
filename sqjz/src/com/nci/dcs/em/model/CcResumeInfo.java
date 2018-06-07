package com.nci.dcs.em.model;

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

import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

/**
 * CcResumeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_RESUME_INFO", schema = "SQJZ")
public class CcResumeInfo implements java.io.Serializable {

	// Fields

	private String resumeId;
	private CcFxryBaseinfo ccFxryBaseinfo;
	private Date startTime;
	private Date endTime;
	private String workUnit;
	private String job;

	// Constructors

	/** default constructor */
	public CcResumeInfo() {
	}

	/** minimal constructor */
	public CcResumeInfo(String resumeId) {
		this.resumeId = resumeId;
	}

	/** full constructor */
	public CcResumeInfo(String resumeId, CcFxryBaseinfo ccFxryBaseinfo,
			Date startTime, Date endTime, String workUnit, String job) {
		this.resumeId = resumeId;
		this.ccFxryBaseinfo = ccFxryBaseinfo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workUnit = workUnit;
		this.job = job;
	}

	// Property accessors
	@Id
	@Column(name = "RESUME_ID", unique = true, nullable = false, length = 32)
	public String getResumeId() {
		return this.resumeId;
	}

	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	public CcFxryBaseinfo getCcFxryBaseinfo() {
		return this.ccFxryBaseinfo;
	}

	public void setCcFxryBaseinfo(CcFxryBaseinfo ccFxryBaseinfo) {
		this.ccFxryBaseinfo = ccFxryBaseinfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_TIME", length = 7)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "WORK_UNIT")
	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "JOB", length = 100)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}