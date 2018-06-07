package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录设备状态的实体模型(MQTT协议中)
 * 
 * @author MyEclipse Persistence Tools
 * 
 */
@Entity
@Table(name = "CC_XMPP_DEVICE_STATUS", schema = "SQJZ")
public class XmppDeviceStatus {
	// Fields
	/**
	 * Description:主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * Description:服刑人员主键
	 */
	@Column(name = "FXRY_ID")
	private String fxryId;

	/**
	 * Description:腕表主键
	 */
	@Column(name = "DEVICE_ID")
	private String deviceId;

	/**
	 * Description:腕表编号
	 */
	@Column(name = "DEVICE_NUMBER")
	private String deviceNum;

	/**
	 * Description:电量
	 */
	@Column(name = "POWER")
	private String power;

	/**
	 * Description:是否静默(0:是;1:否)
	 */
	@Column(name = "SILENT_STATE")
	private String silentState;

	/**
	 * Description:是否破拆(0:是;1:否)
	 */
	@Column(name = "CLOSE_STATE")
	private String closeState;

	/**
	 * Description:上报信息的时间
	 */
	@Column(name = "RECEIVE_TIME")
	private Date time;

	// Property accessors
	public String getId() {
		return this.id;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getSilentState() {
		return silentState;
	}

	public void setSilentState(String silentState) {
		this.silentState = silentState;
	}

	public String getCloseState() {
		return closeState;
	}

	public void setCloseState(String closeState) {
		this.closeState = closeState;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
