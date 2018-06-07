package com.nci.dcs.em.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.WhereJoinTable;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * CcSuperviseDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SUPERVISE_DEVICE", schema = "SQJZ")
public class CcSuperviseDevice implements java.io.Serializable {

	// Fields

	private String deviceId;
	private String org;
	private String deviceType;
	private String deviceNumber;
	//private String pairDeviceNumber;
	private CcDevicePair devicePair;
	private String deviceStatus;
	private String deviceVersion;
	private String procureUnit;
	private Date procureDate;
	private String connectPhone;
	
	@Column(name = "CONNECT_PHONE")
	public String getConnectPhone() {
		return connectPhone;
	}

	public void setConnectPhone(String connectPhone) {
		this.connectPhone = connectPhone;
	}

	//private Set<CcPersonDevice> ccPersonDevices = new HashSet<CcPersonDevice>(0);
	private Set<CcFxryDevice> ccFxryDevices = new HashSet<CcFxryDevice>(0);

	// Constructors

	/** default constructor */
	public CcSuperviseDevice() {
	}

	/** minimal constructor */
	public CcSuperviseDevice(String deviceId) {
		this.deviceId = deviceId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	
	@Column(name = "ORG_ID")
	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Column(name = "DEVICE_TYPE", length = 2)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "DEVICE_NUMBER", length = 12)
	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

//	@Column(name = "PAIR_DEVICE_NUMBER", length = 12)
//	public String getPairDeviceNumber() {
//		return this.pairDeviceNumber;
//	}
//
//	public void setPairDeviceNumber(String pairDeviceNumber) {
//		this.pairDeviceNumber = pairDeviceNumber;
//	}

	@Column(name = "DEVICE_STATUS", length = 2)
	public String getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	@Column(name = "DEVICE_VERSION", length = 32)
	public String getDeviceVersion() {
		return this.deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	@Column(name = "PROCURE_UNIT")
	public String getProcureUnit() {
		return this.procureUnit;
	}

	public void setProcureUnit(String procureUnit) {
		this.procureUnit = procureUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PROCURE_DATE", length = 7)
	public Date getProcureDate() {
		return this.procureDate;
	}

	public void setProcureDate(Date procureDate) {
		this.procureDate = procureDate;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccSuperviseDevice")
//	public Set<CcPersonDevice> getCcPersonDevices() {
//		return this.ccPersonDevices;
//	}
//
//	public void setCcPersonDevices(Set<CcPersonDevice> ccPersonDevices) {
//		this.ccPersonDevices = ccPersonDevices;
//	}

	@JSON(serialize=false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccSuperviseDevice")
	public Set<CcFxryDevice> getCcFxryDevices() {
		return this.ccFxryDevices;
	}

	public void setCcFxryDevices(Set<CcFxryDevice> ccFxryDevices) {
		this.ccFxryDevices = ccFxryDevices;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "PAIR_DEVICE_NUMBER",referencedColumnName="PAIR_DEVICE_NUMBER")
	public CcDevicePair getDevicePair() {
		return devicePair;
	}

	public void setDevicePair(CcDevicePair devicePair) {
		this.devicePair = devicePair;
	}
}