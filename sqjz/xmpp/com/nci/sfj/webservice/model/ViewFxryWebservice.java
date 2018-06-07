package com.nci.sfj.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VIEW_CC_FXRY_WEBSERVICE", schema = "SQJZ")
public class ViewFxryWebservice implements java.io.Serializable {

	// Fields
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "USERD_NAME")
	private String userdName;
	@Column(name = "SEX")
	private String sex;
	@Column(name = "IDENTITY_CARD")
	private String identityCard;
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	@Column(name = "DEVICE_CODE")
	private String deviceCode;
	@Column(name = "RESPONSE_ORG")
	private String responseOrg;
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "cname")
	private String cname;
	@Column(name = "state")
	private String state;

	@Column(name = "ALARM_TYPE")
	public String status;
	@Column(name = "GIS_X")
	public String GIS_X;
	@Column(name = "GIS_Y")
	public String GIS_Y;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	public String getSex() {
		return this.sex.equals("1") ? "男" : "女";
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDeviceCode() {
		return this.deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGIS_X() {
		return GIS_X;
	}

	public void setGIS_X(String gIS_X) {
		GIS_X = gIS_X;
	}

	public String getGIS_Y() {
		return GIS_Y;
	}

	public void setGIS_Y(String gIS_Y) {
		GIS_Y = gIS_Y;
	}

}