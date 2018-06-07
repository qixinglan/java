package com.nci.dcs.em.dwjk.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 项目名称： sqjz
 * 包名称： com.nci.dcs.em.dwjk.model
 * 类名称: CcLocationInfoBuffer
 * 类描述：
 * 创建人： yang
 * 创建时间: 2016-6-14上午10:30:26
 * 修改人： yang
 * 修改时间: 2016-6-14上午10:30:26
 * 修改备注：
 *
 */
@Entity
@Table(name = "CC_LOCATION_INFO_BUFFER", schema = "SQJZ")
public class CcLocationInfoBuffer implements java.io.Serializable {

	// Fields

	private String locId;
	private String fxryId;
	private Date locTime;
	private Double longitude;
	private Double latitude;
	private String address;
	private String alarmType;
	private String locType;

	// Constructors
	/** default constructor */
	public CcLocationInfoBuffer() {
	}

	/** minimal constructor */
	public CcLocationInfoBuffer(String locId) {
		this.locId = locId;
	}

	/** full constructor */
	public CcLocationInfoBuffer(String locId, String fxryId,
			Timestamp locTime, Double longitude, Double latitude, String address,String alarmType,String locType) {
		this.locId = locId;
		this.fxryId = fxryId;
		this.locTime = locTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.alarmType = alarmType;
		this.locType = locType;
	}
	@Column(name = "ALARM_TYPE")
	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	// Property accessors
	@Id
	@Column(name = "LOC_ID", unique = true, nullable = false, length = 32)
	public String getLocId() {
		return this.locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	
	@Column(name = "fxry_id")
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOC_TIME", length = 11)
	public Date getLocTime() {
		return this.locTime;
	}

	public void setLocTime(Date locTime) {
		this.locTime = locTime;
	}

	@Column(name = "LONGITUDE", precision = 14, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE", precision = 14, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "ADDRESS", length = 32)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "LOC_TYPE", length = 32)
	public String getLocType() {
		return this.locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}


}