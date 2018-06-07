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
@Table(name = "CC_MONITOR_DATABASE", schema = "SQJZ")
public class DataBaseMonitor implements java.io.Serializable {

	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月17日 下午3:20:17
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String portStatus;
	private String maxLink;
	private String sumLink;
	private String nowLink;
	private Date createTime;
	private String idleLink;
	private String ip;
	private String db1Sent;//发送次数
	private String db1Received;//接受次数
	private String db1Lost;//失败次数
	private String db1LossRatio;//失败率
	private String db2Sent;//发送次数
	private String db2Received;//接受次数
	private String db2Lost;//失败次数
	private String db2LossRatio;//失败率
	private String db1PortStatus;
	

	private String db2PortStatus;

	// Constructors

	/** default constructor */
	public DataBaseMonitor() {
	}

	/** minimal constructor */
	public DataBaseMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public DataBaseMonitor(String id, String portStatus, String maxLink,
			String sumLink, String idleLink, String nowLink, Date createTime) {
		this.id = id;
		this.portStatus = portStatus;
		this.maxLink = maxLink;
		this.sumLink = sumLink;
		this.idleLink = idleLink;
		this.nowLink = nowLink;
		this.createTime = createTime;
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

	@Column(name = "PORT_STATUS", length = 60)
	public String getPortStatus() {
		return this.portStatus;
	}

	public void setPortStatus(String portStatus) {
		this.portStatus = portStatus;
	}

	@Column(name = "MAX_LINK", length = 60)
	public String getMaxLink() {
		return this.maxLink;
	}

	public void setMaxLink(String maxLink) {
		this.maxLink = maxLink;
	}

	@Column(name = "SUM_LINK", length = 60)
	public String getSumLink() {
		return this.sumLink;
	}

	public void setSumLink(String sumLink) {
		this.sumLink = sumLink;
	}

	@Column(name = "NOW_LINK", length = 60)
	public String getNowLink() {
		return this.nowLink;
	}

	public void setNowLink(String nowLink) {
		this.nowLink = nowLink;
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
	
	@Column(name = "IDLE_LINK", length = 60)
	public String getIdleLink() {
		return idleLink;
	}

	public void setIdleLink(String idleLink) {
		this.idleLink = idleLink;
	}
	
	@Column(name = "IP", length = 60)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "DB1_SENT", length = 60)
	public String getDb1Sent() {
		return db1Sent;
	}

	public void setDb1Sent(String db1Sent) {
		this.db1Sent = db1Sent;
	}

	@Column(name = "DB1_RECEIVED", length = 60)
	public String getDb1Received() {
		return db1Received;
	}

	public void setDb1Received(String db1Received) {
		this.db1Received = db1Received;
	}

	@Column(name = "DB1_LOST", length = 60)
	public String getDb1Lost() {
		return db1Lost;
	}

	public void setDb1Lost(String db1Lost) {
		this.db1Lost = db1Lost;
	}

	@Column(name = "DB1_LOSS_RATIO", length = 60)
	public String getDb1LossRatio() {
		return db1LossRatio;
	}

	public void setDb1LossRatio(String db1LossRatio) {
		this.db1LossRatio = db1LossRatio;
	}

	@Column(name = "DB2_SENT", length = 60)
	public String getDb2Sent() {
		return db2Sent;
	}

	public void setDb2Sent(String db2Sent) {
		this.db2Sent = db2Sent;
	}

	@Column(name = "DB2_RECEIVED", length = 60)
	public String getDb2Received() {
		return db2Received;
	}

	public void setDb2Received(String db2Received) {
		this.db2Received = db2Received;
	}

	@Column(name = "DB2_LOST", length = 60)
	public String getDb2Lost() {
		return db2Lost;
	}

	public void setDb2Lost(String db2Lost) {
		this.db2Lost = db2Lost;
	}

	@Column(name = "DB2_LOSS_RATIO", length = 60)
	public String getDb2LossRatio() {
		return db2LossRatio;
	}

	public void setDb2LossRatio(String db2LossRatio) {
		this.db2LossRatio = db2LossRatio;
	}
	
	@Column(name = "DB1_PORT_STATUS", length = 60)
	public String getDb1PortStatus() {
		return db1PortStatus;
	}

	public void setDb1PortStatus(String db1PortStatus) {
		this.db1PortStatus = db1PortStatus;
	}

	@Column(name = "DB2_PORT_STATUS", length = 60)
	public String getDb2PortStatus() {
		return db2PortStatus;
	}

	public void setDb2PortStatus(String db2PortStatus) {
		this.db2PortStatus = db2PortStatus;
	}
}