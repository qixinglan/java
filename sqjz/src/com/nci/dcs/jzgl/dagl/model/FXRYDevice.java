package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CC_FXRY_DEVICE", schema = "SQJZ")
public class FXRYDevice implements java.io.Serializable {

	// Fields

	private String id;
	private String deviceId;
	private String fxryId;
	private Date startTime;
	private Date endTime;
	private Long status;

	/**
	 * 20150403借助数据库已有字段记录人员佩戴设备时的机构id
	 */
	private String fxryOrgId;
	/**
	 * 20150407借助数据库已有字段记录是否解除设备
	 */
	private String unbindStatus;

	// Constructors

	/** default constructor */
	public FXRYDevice() {
	}

	/** minimal constructor */
	public FXRYDevice(String id) {
		this.id = id;
	}

	public FXRYDevice(String id, String deviceId, String fxryId,
			Date startTime, Date endTime, Long useruserStatus) {
		this.id = id;
		this.deviceId = deviceId;
		this.fxryId = fxryId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

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

	@Temporal(TemporalType.DATE)
	@Column(name = "START_TIME", length = 7)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "DEVICE_ID", length = 32)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "XGRJG", length = 32)
	public String getFxryOrgId() {
		return fxryOrgId;
	}

	public void setFxryOrgId(String fxryOrgId) {
		this.fxryOrgId = fxryOrgId;
	}

	@Column(name = "XGR", length = 32)
	public String getUnbindStatus() {
		return unbindStatus;
	}

	public void setUnbindStatus(String unbindStatus) {
		this.unbindStatus = unbindStatus;
	}

}