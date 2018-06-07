package com.nci.sfj.business.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录人机分离消息的实体类
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_XMPP_MSG_DISAPPEAR", schema = "SQJZ")
public class MsgDisappearXmpp implements Serializable {

	/**
	 * Description:主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * Description:对应服刑人员主键
	 */
	@Column(name = "FXRY_ID", length = 32)
	private String fxryId;

	/**
	 * Description:对应报警信息主键
	 */
	@Column(name = "ALARM_ID", length = 32)
	private String alarmId;

	/**
	 * Description:信息状态(1:报警;2:解除报警)
	 */
	@Column(name = "STATUS")
	private String status;

	/**
	 * Description:消息时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MSG_TIME")
	private Date msgTime;

	/**
	 * Description:设备编号
	 */
	@Column(name = "DEVICE_NUMBER")
	private String deviceNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

}