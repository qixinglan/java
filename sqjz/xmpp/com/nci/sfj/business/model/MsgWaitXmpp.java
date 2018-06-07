package com.nci.sfj.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description:记录等待下发给各个腕表的设置的实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_XMPP_MSG_WAIT", schema = "SQJZ")
public class MsgWaitXmpp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6780327550708628885L;

	/**
	 * Description:主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;

	/**
	 * Description:消息类型
	 */
	@Column(name = "TYPE", nullable = false)
	private String type;

	/**
	 * Description:腕表编号
	 */
	@Column(name = "DEVICE_NUMBER", length = 8)
	private String deviceNumber;

	/**
	 * Description:消息内容
	 */
	@Column(name = "CONTENT", length = 8)
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}