package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "VIEW_FXRY_NOTICE", schema = "SQJZ")
public class ViewFxryNotice implements java.io.Serializable {

	// Fields

	/**
	 * @name 
	 * @author caolj
	 * @date 2015年3月30日 下午4:47:44
	 * @message:
	 */
	private static final long serialVersionUID = 2470495060983274372L;
	
	private String id;
	private String name;//姓名
	private String code;//人员编号
	private String identityCard;//身份证号
	private String sex;//性别
	private String responseOrg;//矫正负责单位
	private String supOrg;//矫正负责单位
	private String state;//当前状态
	private String deviceCode;//是否电子监管
	private Date reportTime;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	public ViewFxryNotice() {
	}

	// Property accessors
	@Id
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "IDENTITY_CARD", length = 18)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "STATE", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "RESPONSE_ORG")
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "DEVICE_CODE")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME")
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name = "SUP_ORG_ID")
	public String getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(String supOrg) {
		this.supOrg = supOrg;
	}
}