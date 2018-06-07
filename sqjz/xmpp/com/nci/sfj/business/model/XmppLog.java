package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:XMPP通信消息日志记录实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_LOG")
public class XmppLog {

	/**
	 * Description:主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * Description:设备编号
	 */
	@Column(name = "DEVICE_NUMBER")
	private String deviceNumber;

	/**
	 * Description:协议类型(登录、定位、报警、设置、自检等)
	 */
	@Column(name = "TYPE")
	private String type;

	/**
	 * Description:消息接收时间
	 */
	@Column(name = "MSG_TIME")
	private Date messageTime;

	/**
	 * Description:XMPP中消息id
	 */
	@Column(name = "MSG_Id")
	private String messageId;

	/**
	 * Description:消息类型(set/get/error)
	 */
	@Column(name = "MSG_TYPE")
	private String messageType;

	/**
	 * Description:消息内容
	 */
	@Column(name = "MESSAGE")
	private String message;

	@Transient
	private Date loginTime;
	@Transient
	private Date alertTime;
	@Transient
	private Date locationTime;
	@Transient
	private String zjnum;
	@Transient
	private String wbnum;
	@Transient
	private String telPhones;
	@Transient
	private String silent;
	@Transient
	private String voltage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

	public Date getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Date locationTime) {
		this.locationTime = locationTime;
	}

	public String getZjnum() {
		return zjnum;
	}

	public void setZjnum(String zjnum) {
		this.zjnum = zjnum;
	}

	public String getWbnum() {
		return wbnum;
	}

	public void setWbnum(String wbnum) {
		this.wbnum = wbnum;
	}

	public String getTelPhones() {
		return telPhones;
	}

	public void setTelPhones(String telPhones) {
		this.telPhones = telPhones;
	}

	public String getSilent() {
		return silent;
	}

	public void setSilent(String silent) {
		this.silent = silent;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
}