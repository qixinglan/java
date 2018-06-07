package com.nci.dcs.jzgl.dagl.model;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.ConversionType;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * FXRYOutManageInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_OUTMANAGE_INFO", schema = "SQJZ")
public class FXRYOutManageInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private Date startDate;
	private Date endDate;
	private String reason;
	private Date recordTime;
	private String recordOrgId;
	private String registrantId;
	private String description;

	private Date noticeTime;
	private String notifier;
	private String noticePolice;

	// Constructors

	/** default constructor */
	public FXRYOutManageInfo() {
	}

	public void copy(FXRYOutManageInfo right) {
		this.startDate = right.getStartDate();
		this.endDate = right.getEndDate();
		this.reason = right.getReason();
		this.registrantId = right.getRegistrantId();
		this.description = right.getDescription();
		this.noticeTime = right.getNoticeTime();
		this.notifier = right.getNotifier();
		this.noticePolice = right.getNoticePolice();
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "REASON", length = 30)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECORD_TIME", length = 7)
	@JSON(format = DateTimeFmtSpec.TIMESTAMP)
	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Column(name = "REGISTRANT_ID", length = 32)
	public String getRegistrantId() {
		return this.registrantId;
	}

	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}

	@Column(name = "DESCRIPTION", length = 1500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_TIME")
	@JSON(format = DateTimeFmtSpec.MINUTES)
	public Date getNoticeTime() {
		return noticeTime;
	}

	@TypeConversion(converter = "com.nci.dcs.common.utils.DateTypeConerter", rule = ConversionRule.PROPERTY, type = ConversionType.CLASS)
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	@Column(name = "NOTIFIER", length = 60)
	public String getNotifier() {
		return notifier;
	}

	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

	@Column(name = "NOTICE_POLICE", length = 150)
	public String getNoticePolice() {
		return noticePolice;
	}

	public void setNoticePolice(String noticePolice) {
		this.noticePolice = noticePolice;
	}

}