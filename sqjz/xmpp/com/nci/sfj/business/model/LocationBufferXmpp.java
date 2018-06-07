package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 项目名称： sqjz
 * 包名称： com.nci.sfj.business.model
 * 类名称: LocationBufferXmpp
 * 类描述：XMPP通信模块中的定位信息实体类(三个月内信息)
 * 创建人： yang
 * 创建时间: 2016-6-14上午10:31:37
 * 修改人： yang
 * 修改时间: 2016-6-14上午10:31:37
 * 修改备注：
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CC_LOCATION_INFO_BUFFER", schema = "SQJZ")
public class LocationBufferXmpp implements java.io.Serializable {

	// Fields

	/**
	 * Description:主键
	 */
	private String locId;
	/**
	 * Description:关联设备的主键
	 */
	private String deviceId;
	/**
	 * Description:服刑人员的主键
	 */
	private String personId;
	/**
	 * Description:定位时间
	 */
	private Date locTime;
	/**
	 * Description:经度
	 */
	private Double longitude;
	/**
	 * Description:纬度
	 */
	private Double latitude;
	/**
	 * Description:报警信息上报时产生的定位的报警类型
	 */
	private String alarmType;
	/**
	 * Description:定位类型(GPS或LBS)
	 */
	private String locType;
	/**
	 * Description:是否与上一个坐标重复
	 */
	private String repeat;

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LOC_ID", unique = true, nullable = false, length = 32)
	public String getLocId() {
		return this.locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	@Column(name = "DEVICE_ID")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "FXRY_ID")
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "LOC_TIME")
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

	@Column(name = "ALARM_TYPE")
	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	@Column(name = "LOC_TYPE")
	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	@Column(name = "IS_REPEAT")
	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
}