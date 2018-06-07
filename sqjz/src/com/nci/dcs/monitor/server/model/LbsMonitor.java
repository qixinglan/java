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
 * CcServiceMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MONITOR_LBS", schema = "SQJZ")
public class LbsMonitor implements java.io.Serializable {

	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月17日 下午3:20:17
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String status;
	private String ratio;
	private Date createTime;
	private String success;
	private String fail;
	private String amount;
	private String sent;//发送次数
	private String received;//接受次数
	private String lost;//失败次数
	private String lossRatio;//失败率
	private String networkStatus;
	private String ip;

	// Constructors
	/** default constructor */
	public LbsMonitor() {
	}

	/** minimal constructor */
	public LbsMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public LbsMonitor(String id, String status, String ratio,
			Date createTime, String success, String fail, String amount) {
		this.id = id;
		this.status = status;
		this.ratio = ratio;
		this.createTime = createTime;
		this.success = success;
		this.fail = fail;
		this.amount = amount;
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

	@Column(name = "STATUS", length = 60)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RATIO", length = 60)
	public String getRatio() {
		return this.ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
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
	
	@Column(name = "SUCCESS", length = 60)
	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Column(name = "FAIL", length = 60)
	public String getFail() {
		return this.fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	@Column(name = "AMOUNT", length = 60)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Column(name = "SENT", length = 60)
	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	@Column(name = "RECEIVED", length = 60)
	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	@Column(name = "LOST", length = 60)
	public String getLost() {
		return lost;
	}

	public void setLost(String lost) {
		this.lost = lost;
	}

	@Column(name = "LOSS_RATIO", length = 60)
	public String getLossRatio() {
		return lossRatio;
	}

	public void setLossRatio(String lossRatio) {
		this.lossRatio = lossRatio;
	}

	@Column(name = "NETWORK_STATUS", length = 60)
	public String getNetworkStatus() {
		return networkStatus;
	}

	public void setNetworkStatus(String networkStatus) {
		this.networkStatus = networkStatus;
	}
	
	@Column(name = "IP", length = 60)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}