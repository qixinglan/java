package com.nci.dcs.em.dwjk.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcAlarmInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cc_alarm_info", schema = "SQJZ")
public class CcAlarmInfo implements java.io.Serializable {

	// Fields

	private String alarmId;
	
	//private CcFxryBaseinfo ccFxryBaseinfo;
	private String fxryId;
	private Date alarmTime;
	private String alarmLevel;
	private String alarmType;
	private String executeUnit;
	private String adjustType;
	private BigDecimal isautoAlert;
	private String alert;
	private String alarm;
	private Date handleTime;
	private String handler;
	private String record;
	private String status;
	private Double x;
	private Double y;
	// Constructors

	/** default constructor */
	public CcAlarmInfo() {
	}

	/** minimal constructor */
	public CcAlarmInfo(String alarmId) {
		this.alarmId = alarmId;
	}

	/** full constructor */
	public CcAlarmInfo(String alarmId, String fxryId,
			Date alarmTime, String alarmLevel, String alarmType,
			String executeUnit, String adjustType, BigDecimal isautoAlert,
			String alert, String alarm, Date handleTime, String handler,
			String record, String status) {
		this.alarmId = alarmId;
		this.fxryId = fxryId;
		this.alarmTime = alarmTime;
		this.alarmLevel = alarmLevel;
		this.alarmType = alarmType;
		this.executeUnit = executeUnit;
		this.adjustType = adjustType;
		this.isautoAlert = isautoAlert;
		this.alert = alert;
		this.alarm = alarm;
		this.handleTime = handleTime;
		this.handler = handler;
		this.record = record;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "ALARM_ID", unique = true, nullable = false, length = 32)
	public String getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	@Column(name="fxry_id",nullable = true)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALARM_TIME", length = 7)
	public Date getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	@Column(name = "ALARM_LEVEL", length = 5)
	public String getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	@Column(name = "ALARM_TYPE", length = 10)
	public String getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	@Column(name = "EXECUTE_UNIT", length = 64)
	public String getExecuteUnit() {
		return this.executeUnit;
	}

	public void setExecuteUnit(String executeUnit) {
		this.executeUnit = executeUnit;
	}

	@Column(name = "ADJUST_TYPE", length = 5)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ISAUTO_ALERT", precision = 38, scale = 0)
	public BigDecimal getIsautoAlert() {
		return this.isautoAlert;
	}

	public void setIsautoAlert(BigDecimal isautoAlert) {
		this.isautoAlert = isautoAlert;
	}

	@Column(name = "ALERT", length = 200)
	public String getAlert() {
		return this.alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	@Column(name = "ALARM", length = 200)
	public String getAlarm() {
		return this.alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANDLE_TIME", length = 7)
	public Date getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	@Column(name = "HANDLER", length = 64)
	public String getHandler() {
		return this.handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@Column(name = "RECORD", length = 400)
	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "X")
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}
	@Column(name = "Y")
	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
}