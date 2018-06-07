package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:报警信息模型
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ALARM_INFO", schema = "SQJZ")
public class AlarmXmpp implements java.io.Serializable {

	// Fields

	/**
	 * Description:
	 */
	private static final long serialVersionUID = -944688501580491617L;
	private String alarmId;
	private String fxryId;
	private Date alarmTime;
	private String alarmLevel;
	private String alarmType;
	private String executeUnit;
	private String adjustType;
	private int isautoAlert;
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
	public AlarmXmpp() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ALARM_ID", unique = true, nullable = false, length = 32)
	public String getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "ALARM_TIME", length = 11)
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

	@Column(name = "ISAUTO_ALERT")
	public int getIsautoAlert() {
		return this.isautoAlert;
	}

	public void setIsautoAlert(int isautoAlert) {
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

	@Column(name = "HANDLE_TIME")
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

	@Column(name = "STATUS", length = 400)
	public String getStatus() {
		return status;
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