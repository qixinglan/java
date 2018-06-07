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
 * Description:记录超距报警消息的实体类
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_XMPP_MSG_OUTRANGE", schema = "SQJZ")
public class MsgOutrangeXmpp implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	private String id;
	@Column(name = "FXRY_ID", length = 32)
	private String fxryId;
	@Column(name = "ALARM_ID", length = 32)
	private String alarmId;
	@Column(name = "STATUS", length = 8)
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MSG_TIME")
	private Date msgTime;

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

}