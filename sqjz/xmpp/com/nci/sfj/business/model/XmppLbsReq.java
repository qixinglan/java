package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:LBS定位请求记录
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_LBS_REQ")
public class XmppLbsReq {
	
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
	 * Description:定位时间
	 */
	@Column(name = "LOC_TIME")
	private Date locTime;

	/**
	 * Description:请求时间
	 */
	@Column(name = "REQ_TIME")
	private Date reqTime;

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

	public Date getLocTime() {
		return locTime;
	}

	public void setLocTime(Date locTime) {
		this.locTime = locTime;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
}