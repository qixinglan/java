package com.nci.sfj.webservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * CcAlarmInfoViewId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ALARM_INFO_VIEW", schema = "SQJZ")
public class ViewAlarmInfo implements java.io.Serializable {

	// Fields

	private String alarmId;
	private String fxryId;
	private Date alarmTime;
	private String alarmLevel;
	private String alarmType;
	private String executeUnit;
	private String executeUnitName;
	private String adjustType;
	private Integer isautoAlert;
	private String alert;
	private String alarm;
	private Date handleTime;
	private String handler;
	private String record;
	private String status;
	private String name;
	private String orgId;

	// Property accessors
	@Id
	@Column(name = "ALARM_ID", nullable = false, length = 32)
	public String getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	@Column(name = "FXRY_ID", length = 32)
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

	@Column(name = "EXECUTE_UNIT", length = 32)
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

	@Column(name = "ISAUTO_ALERT")
	public Integer getIsautoAlert() {
		return this.isautoAlert;
	}

	public void setIsautoAlert(Integer isautoAlert) {
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

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RESPONSE_ORG")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "CNAME")
	public String getExecuteUnitName() {
		return executeUnitName;
	}

	public void setExecuteUnitName(String executeUnitName) {
		this.executeUnitName = executeUnitName;
	}

	@Transient
	public String isautoAlert() {
		return this.isautoAlert == 1 ? "是" : "否";
	}

	@Transient
	public String status() {
		return "1".equals(this.status) ? "已处理" : "未处理";
	}
}