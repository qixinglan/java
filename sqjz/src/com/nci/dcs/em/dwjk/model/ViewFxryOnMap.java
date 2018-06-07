package com.nci.dcs.em.dwjk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ViewFxryOnMapId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_ON_MAP", schema = "SQJZ")
public class ViewFxryOnMap implements java.io.Serializable {

	// Fields

	private String id;
	private String phoneNumber;
	private String name;
	private String orgId;
	private String adjustType;
	private int ac;
	private Double gisX;
	private Double gisY;
	private String personName;
	private String personPhone;
	private String address;
	private String status;
	private String locType;
	private Timestamp locTime;
	private String isTgry;

	// Constructors
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	/** default constructor */
	public ViewFxryOnMap() {
	}

	/** minimal constructor */
	public ViewFxryOnMap(String id) {
		this.id = id;
	}

	/** full constructor */
	public ViewFxryOnMap(String id, String phoneNumber, String name,
			String orgId, String adjustType, int ac, Double gisX,
			Double gisY, String personName, String personPhone, String address,
			String status, String locType, Timestamp locTime) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.orgId = orgId;
		this.adjustType = adjustType;
		this.ac = ac;
		this.gisX = gisX;
		this.gisY = gisY;
		this.personName = personName;
		this.personPhone = personPhone;
		this.address = address;
		this.status = status;
		this.locType = locType;
		this.locTime = locTime;
	}

	// Property accessors

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PHONE_NUMBER", length = 100)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "AC", precision = 22, scale = 0)
	public int getAc() {
		return this.ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	@Column(name = "GIS_X", precision = 15, scale = 4)
	public Double getGisX() {
		return this.gisX;
	}

	public void setGisX(Double gisX) {
		this.gisX = gisX;
	}

	@Column(name = "GIS_Y", precision = 15, scale = 4)
	public Double getGisY() {
		return this.gisY;
	}

	public void setGisY(Double gisY) {
		this.gisY = gisY;
	}

	@Column(name = "PERSON_NAME", length = 60)
	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name = "PERSON_PHONE", length = 30)
	public String getPersonPhone() {
		return this.personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	@Column(name = "ADDRESS", length = 1500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LOC_TYPE", length = 30)
	public String getLocType() {
		return this.locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	@Column(name = "LOC_TIME", length = 11)
	public Timestamp getLocTime() {
		return this.locTime;
	}

	public void setLocTime(Timestamp locTime) {
		this.locTime = locTime;
	}
}