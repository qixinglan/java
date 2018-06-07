package com.nci.dcs.em.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CC_DEVICE_RECORD",schema = "SQJZ")
public class CcDeviceRecord {
	private String id;
	private String deviceNumber;
	private String operateName;
	private String operatePersion;
	private String operateUnit;
	private Date operateDate;
	private String useUnit;
	private String wearPersion;
	private String simNumber;
	private String ydDeviceNumber;
	private String type;
	private String operatePersionName;
	
	private String wearPersionName;
	private String useSfsUnit;
	private String jgDeviceNumber;
	
	
	@Column(name = "USE_SFSUNIT")
	public String getUseSfsUnit() {
		return useSfsUnit;
	}
	@Column(name = "JGDEVICE_NUMBER")
	public String getJgDeviceNumber() {
		return jgDeviceNumber;
	}
	public void setJgDeviceNumber(String jgDeviceNumber) {
		this.jgDeviceNumber = jgDeviceNumber;
	}
	public void setUseSfsUnit(String useSfsUnit) {
		this.useSfsUnit = useSfsUnit;
	}
	@Column(name = "WEAR_PERSION")
	public String getWearPersion() {
		return wearPersion;
	}
	public void setWearPersion(String wearPersion) {
		this.wearPersion = wearPersion;
	}
	@Transient
	public String getWearPersionName() {
		return wearPersionName;
	}
	public void setWearPersionName(String wearPersionName) {
		this.wearPersionName = wearPersionName;
	}
	//非该表真实字段用于显示操作人名
	@Transient
	public String getOperatePersionName() {
		return operatePersionName;
	}
	public void setOperatePersionName(String operatePersionName) {
		this.operatePersionName = operatePersionName;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	@Column(name = "OPERATE_NAME")
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	@Column(name = "OPERATE_PERSION")
	public String getOperatePersion() {
		return operatePersion;
	}
	public void setOperatePersion(String operatePersion) {
		this.operatePersion = operatePersion;
	}
	@Column(name = "OPERATE_UNIT")
	public String getOperateUnit() {
		return operateUnit;
	}
	public void setOperateUnit(String operateUnit) {
		this.operateUnit = operateUnit;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE")
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	@Column(name = "USE_UNIT")
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	
	@Column(name = "SIM_NUMBER")
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	@Column(name = "YDDEVICE_NUMBER")
	public String getYdDeviceNumber() {
		return ydDeviceNumber;
	}
	public void setYdDeviceNumber(String ydDeviceNumber) {
		this.ydDeviceNumber = ydDeviceNumber;
	}
	
}
