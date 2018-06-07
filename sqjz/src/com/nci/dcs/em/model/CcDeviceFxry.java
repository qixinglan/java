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
@Table(name = "CC_DEVICE_FXRY",schema = "SQJZ")
public class CcDeviceFxry {
	private String id;
	private String deviceNumber;
	private String fxryId;
	private String status;//关联状态
	private Date wearTime;//开始佩戴日期
	private Date releaseTime;//解除日期
	private String createPersion;
	private String fxryName;
	private String useUnit;
	private String useSfsUnit;
	@Column(name = "USE_UNIT")
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	@Column(name = "USE_SFSUNIT")
	public String getUseSfsUnit() {
		return useSfsUnit;
	}
	public void setUseSfsUnit(String useSfsUnit) {
		this.useSfsUnit = useSfsUnit;
	}
	@Column(name = "FXRY_NAME")
	public String getFxryName() {
		return fxryName;
	}
	public void setFxryName(String fxryName) {
		this.fxryName = fxryName;
	}
	@Column(name = "CREATE_PERSION")
	public String getCreatePersion() {
		return createPersion;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "WEAR_TIME")
	public Date getWearTime() {
		return wearTime;
	}
	public void setWearTime(Date wearTime) {
		this.wearTime = wearTime;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_TIME")
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
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
	@Column(name = "FXRY_ID")
	public String getFxryId() {
		return fxryId;
	}
	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
