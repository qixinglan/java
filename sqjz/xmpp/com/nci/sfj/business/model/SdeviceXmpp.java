package com.nci.sfj.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Description:XMPP通信模块中记录的分体设备信息
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SUPERVISE_DEVICE", schema = "SQJZ")
public class SdeviceXmpp implements java.io.Serializable {

	// Fields
	private String deviceId;

	/**
	 * Description:当前使用机构
	 */
	private String orgId;

	/**
	 * Description:设备类型(1:主机;2:腕表)
	 */
	private String deviceType;

	/**
	 * Description:设备编号
	 */
	private String deviceNumber;

	/**
	 * Description:对应一套设备的编号
	 */
	private String pairDeviceNumber;

	/**
	 * Description:设备状态(02:未使用;03:使用中)
	 */
	private String deviceStatus;

	/**
	 * Description:设备版本号
	 */
	private String deviceVersion;

	/**
	 * Description:购买单位
	 */
	private String procureUnit;

	/**
	 * Description:购买时间
	 */
	private Date procureDate;

	/**
	 * Description:SIM卡电话号码
	 */
	private String phone;

	private Set<FxryDevice> fxryDevices = new HashSet<FxryDevice>(0);

	// Constructors

	/** default constructor */
	public SdeviceXmpp() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "DEVICE_TYPE")
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Column(name = "DEVICE_NUMBER")
	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Column(name = "PAIR_DEVICE_NUMBER")
	public String getPairDeviceNumber() {
		return this.pairDeviceNumber;
	}

	public void setPairDeviceNumber(String pairDeviceNumber) {
		this.pairDeviceNumber = pairDeviceNumber;
	}

	@Column(name = "DEVICE_STATUS")
	public String getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	@Column(name = "DEVICE_VERSION")
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
	@Column(name = "PROCURE_DATE")
	public Date getProcureDate() {
		return this.procureDate;
	}

	public void setProcureDate(Date procureDate) {
		this.procureDate = procureDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "superviseDevice")
	public Set<FxryDevice> getFxryDevices() {
		return fxryDevices;
	}

	public void setFxryDevices(Set<FxryDevice> fxryDevices) {
		this.fxryDevices = fxryDevices;
	}

	@Column(name = "CONNECT_PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}