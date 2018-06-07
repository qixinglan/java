package com.nci.dcs.monitor.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * NetworkMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MONITOR_NETWORK", schema = "SQJZ")
public class NetworkMonitor implements java.io.Serializable {

	// Fields

	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月20日 下午4:31:16
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String ip;//请求服务器
	private Date createTime;//录入日期
	private String sent;//发送次数
	private String received;//接受次数
	private String lost;//失败次数
	private String lossRatio;//失败率

	// Constructors

	/** default constructor */
	public NetworkMonitor() {
	}

	/** minimal constructor */
	public NetworkMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public NetworkMonitor(String id, String ip, Date createTime, String sent,
			String received, String lost, String lossRatio) {
		this.id = id;
		this.ip = ip;
		this.createTime = createTime;
		this.sent = sent;
		this.received = received;
		this.lost = lost;
		this.lossRatio = lossRatio;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "IP", length = 60)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(format = DateTimeFmtSpec.DATETIME)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "SENT", length = 60)
	public String getSent() {
		return this.sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	@Column(name = "RECEIVED", length = 60)
	public String getReceived() {
		return this.received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	@Column(name = "LOST", length = 60)
	public String getLost() {
		return this.lost;
	}

	public void setLost(String lost) {
		this.lost = lost;
	}

	@Column(name = "LOSS_RATIO", length = 60)
	public String getLossRatio() {
		return this.lossRatio;
	}

	public void setLossRatio(String lossRatio) {
		this.lossRatio = lossRatio;
	}

}