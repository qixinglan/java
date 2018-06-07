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
@Table(name = "CC_DZJGSB_DEVICE", schema = "SQJZ")
public class CcDzjgsbDevice implements java.io.Serializable{
	
	private String id;
	private String deviceNumber;
	
	private String buyUnit;
	
	private Date buyDate;
	
	private String useUnit;
	
	private String phoneNumber;

	private String wearPerson;
	
	private String status;
	private Date createTime;
	private String createPersion;
	private String useSfsUnit;
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
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "DEVICE_NUMBER")
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
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
	@Column(name = "PHONE_NUMBER", length = 11)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name = "WEAR_PERSON")
	public String getWearPerson() {
		return wearPerson;
	}
	public void setWearPerson(String wearPerson) {
		this.wearPerson = wearPerson;
	}
	@Column(name = "STATUS",length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "USE_SFSUNIT")
	public String getUseSfsUnit() {
		return useSfsUnit;
	}
	public void setUseSfsUnit(String useSfsUnit) {
		this.useSfsUnit = useSfsUnit;
	}
	@Override
	public String toString() {
		return "CcDzjgsbDevice [id=" + id + ", deviceNumber=" + deviceNumber
				+ ", buyUnit=" + buyUnit + ", buyDate=" + buyDate
				+ ", useUnit=" + useUnit + ", phoneNumber=" + phoneNumber
				+ ", wearPersonName=" + wearPerson + ", status=" + status
				+ ", createTime=" + createTime + ", createPersion="
				+ createPersion + "]";
	}
	
}
