package com.nci.dcs.em.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

/**
 * CcFxryDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_DEVICE", schema = "SQJZ")
public class CcFxryDevice implements java.io.Serializable {

	// Fields

	private String id;
	private CcSuperviseDevice ccSuperviseDevice;
	private CcFxryBaseinfo ccFxryBaseinfo;
	private Date startTime;
	private Date endTime;
	private BigDecimal userStatus;

	// Constructors

	/** default constructor */
	public CcFxryDevice() {
	}

	/** minimal constructor */
	public CcFxryDevice(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcFxryDevice(String id, CcSuperviseDevice ccSuperviseDevice,
			CcFxryBaseinfo ccFxryBaseinfo, Date startTime, Date endTime,
			BigDecimal userStatus) {
		this.id = id;
		this.ccSuperviseDevice = ccSuperviseDevice;
		this.ccFxryBaseinfo = ccFxryBaseinfo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userStatus = userStatus;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID")
	public CcSuperviseDevice getCcSuperviseDevice() {
		return this.ccSuperviseDevice;
	}

	public void setCcSuperviseDevice(CcSuperviseDevice ccSuperviseDevice) {
		this.ccSuperviseDevice = ccSuperviseDevice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FXRY_ID")
	public CcFxryBaseinfo getCcFxryBaseinfo() {
		return this.ccFxryBaseinfo;
	}

	public void setCcFxryBaseinfo(CcFxryBaseinfo ccFxryBaseinfo) {
		this.ccFxryBaseinfo = ccFxryBaseinfo;
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

	@Column(name = "STATUS", precision = 22, scale = 0)
	public BigDecimal getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(BigDecimal userStatus) {
		this.userStatus = userStatus;
	}

}