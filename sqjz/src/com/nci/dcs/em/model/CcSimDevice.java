package com.nci.dcs.em.model;

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
@Table(name = "CC_SIM_DEVICE", schema = "SQJZ")
public class CcSimDevice {
	private String deviceNumber;
	private String wearPersion;
	private String buyUnit;
	private Date buyDate;
	private String useUnit;
	private String phoneNumber;
	private String useDeviceNumber;
	private String status;
	private String simType;
	private Date createTime;
	private String createPersion;
	private String useSfsUnit;
	private String useDeviceType;
	@Column(name = "USE_DEVICE_TYPE")
	public String getUseDeviceType() {
		return useDeviceType;
	}
	public void setUseDeviceType(String useDeviceType) {
		this.useDeviceType = useDeviceType;
	}
	@Column(name = "USE_SFSUNIT")
	public String getUseSfsUnit() {
		return useSfsUnit;
	}
	public void setUseSfsUnit(String useSfsUnit) {
		this.useSfsUnit = useSfsUnit;
	}
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "DEVICE_NUMBER", unique = true, nullable = false, length = 32)
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	@Column(name = "WEAR_PERSION")
	public String getWearPersion() {
		return wearPersion;
	}
	public void setWearPersion(String wearPersion) {
		this.wearPersion = wearPersion;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "CREATE_PERSION")
	public String getCreatePersion() {
		return createPersion;
	}
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	@Column(name = "SIM_TYPE")
	public String getSimType() {
		return simType;
	}
	public void setSimType(String simType) {
		this.simType = simType;
	}
	
	@Column(name = "BUY_UNIT")
	public String getBuyUnit() {
		return buyUnit;
	}
	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "BUY_DATE")
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	@Column(name = "USE_UNIT")
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "USE_DEVICE_NUMBER")
	public String getUseDeviceNumber() {
		return useDeviceNumber;
	}
	public void setUseDeviceNumber(String useDeviceNumber) {
		this.useDeviceNumber = useDeviceNumber;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
