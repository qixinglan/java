package com.nci.sfj.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:XMPP通信模块中的服刑人员实体类
 * 
 * @author Shuzz
 * 
 */
@Entity
@Table(name = "CC_FXRY_BASEINFO", schema = "SQJZ")
public class FxryXmpp {

	/**
	 * Description:主键
	 */
	@Id
	@Column(name = "ID")
	private String id;
	/**
	 * Description:经度
	 */
	@Column(name = "GIS_X")
	private Double gis_x;
	/**
	 * Description:纬度
	 */
	@Column(name = "GIS_Y")
	private Double gis_y;
	/**
	 * Description:设备编号
	 */
	@Column(name = "DEVICE_CODE")
	private String deviceNumber;
	/**
	 * Description:所属机构
	 */
	@Column(name = "RESPONSE_ORG")
	private String org;
	/**
	 * Description:联系电话
	 */
	@Column(name = "PHONE_NUMBER")
	private String phone;
	/**
	 * Description:姓名
	 */
	@Column(name = "NAME")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Double getGis_x() {
		return gis_x;
	}

	public void setGis_x(Double gis_x) {
		this.gis_x = gis_x;
	}

	public Double getGis_y() {
		return gis_y;
	}

	public void setGis_y(Double gis_y) {
		this.gis_y = gis_y;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
