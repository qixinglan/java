package com.nci.dcs.em.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcAlarmType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ALARM_TYPE", schema = "SQJZ")
public class CcAlarmType implements java.io.Serializable {

	// Fields

	private String id;
	private String alarmLevel;
	private String alarmType;
	private String status;
	private BigDecimal isautoAlert;
	private String alertContent;
	private BigDecimal isautoAlarm;
	private String alarmContent;
	private Integer crossBorderRange;
	private Integer crossBorderTime;
	private Integer gatherTime;
	private Integer sparateRange;
	private Integer sparateTime;
	private Integer silentTime;
	private Integer lostContact;
	private Integer locationError;

	// Constructors

	/** default constructor */
	public CcAlarmType() {
	}

	/** minimal constructor */
	public CcAlarmType(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcAlarmType(String id, String alarmLevel, String alarmType,
			String status, BigDecimal isautoAlert, String alertContent,
			BigDecimal isautoAlarm, String alarmContent,
			Integer crossBorderRange, Integer crossBorderTime,
			Integer gatherTime, Integer sparateRange, Integer sparateTime,
			Integer silentTime, Integer lostContact, Integer locationError) {
		this.id = id;
		this.alarmLevel = alarmLevel;
		this.alarmType = alarmType;
		this.status = status;
		this.isautoAlert = isautoAlert;
		this.alertContent = alertContent;
		this.isautoAlarm = isautoAlarm;
		this.alarmContent = alarmContent;
		this.crossBorderRange = crossBorderRange;
		this.crossBorderTime = crossBorderTime;
		this.gatherTime = gatherTime;
		this.sparateRange = sparateRange;
		this.sparateTime = sparateTime;
		this.silentTime = silentTime;
		this.lostContact = lostContact;
		this.locationError = locationError;
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

	@Column(name = "ALARM_LEVEL", length = 8)
	public String getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	@Column(name = "ALARM_TYPE", length = 8)
	public String getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	@Column(name = "STATUS", length = 8)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ISAUTO_ALERT", precision = 38, scale = 0)
	public BigDecimal getIsautoAlert() {
		return this.isautoAlert;
	}

	public void setIsautoAlert(BigDecimal isautoAlert) {
		this.isautoAlert = isautoAlert;
	}

	@Column(name = "ALERT_CONTENT", length = 400)
	public String getAlertContent() {
		return this.alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	@Column(name = "ISAUTO_ALARM", precision = 38, scale = 0)
	public BigDecimal getIsautoAlarm() {
		return this.isautoAlarm;
	}

	public void setIsautoAlarm(BigDecimal isautoAlarm) {
		this.isautoAlarm = isautoAlarm;
	}

	@Column(name = "ALARM_CONTENT", length = 400)
	public String getAlarmContent() {
		return this.alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	@Column(name = "CROSS_BORDER_RANGE", precision = 8, scale = 0)
	public Integer getCrossBorderRange() {
		return this.crossBorderRange;
	}

	public void setCrossBorderRange(Integer crossBorderRange) {
		this.crossBorderRange = crossBorderRange;
	}

	@Column(name = "CROSS_BORDER_TIME", precision = 8, scale = 0)
	public Integer getCrossBorderTime() {
		return this.crossBorderTime;
	}

	public void setCrossBorderTime(Integer crossBorderTime) {
		this.crossBorderTime = crossBorderTime;
	}

	@Column(name = "GATHER_TIME", precision = 8, scale = 0)
	public Integer getGatherTime() {
		return this.gatherTime;
	}

	public void setGatherTime(Integer gatherTime) {
		this.gatherTime = gatherTime;
	}

	@Column(name = "SPARATE_RANGE", precision = 8, scale = 0)
	public Integer getSparateRange() {
		return this.sparateRange;
	}

	public void setSparateRange(Integer sparateRange) {
		this.sparateRange = sparateRange;
	}

	@Column(name = "SPARATE_TIME", precision = 8, scale = 0)
	public Integer getSparateTime() {
		return this.sparateTime;
	}

	public void setSparateTime(Integer sparateTime) {
		this.sparateTime = sparateTime;
	}

	@Column(name = "SILENT_TIME", precision = 8, scale = 0)
	public Integer getSilentTime() {
		return this.silentTime;
	}

	public void setSilentTime(Integer silentTime) {
		this.silentTime = silentTime;
	}

	@Column(name = "LOST_CONTACT", precision = 8, scale = 0)
	public Integer getLostContact() {
		return this.lostContact;
	}

	public void setLostContact(Integer lostContact) {
		this.lostContact = lostContact;
	}

	@Column(name = "LOCATION_ERROR", precision = 8, scale = 0)
	public Integer getLocationError() {
		return this.locationError;
	}

	public void setLocationError(Integer locationError) {
		this.locationError = locationError;
	}

}