package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录所有通信设备登录状态的实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_INFO")
public class XmppDeviceInfo {
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
	 * Description:设备类型 (1:腕表端;2:移动执法终端端)
	 */
	@Column(name = "DEVICE_TYPE")
	private String type;

	/**
	 * Description:是否登录(1:是;2:否)
	 */
	@Column(name = "IS_LOGIN")
	private String isLogin;

	/**
	 * Description:登录时间
	 */
	@Column(name = "LOGIN_TIME")
	private Date loginTime;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
