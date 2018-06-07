package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ADDRESS", schema = "SQJZ")
public class CcAddress implements java.io.Serializable {

	// Fields

	private String addressId;
	private String province;
	private String city;
	private String county;
	private String town;
	private String village;
	private String detail;

	// Constructors

	/** default constructor */
	public CcAddress() {
	}

	/** minimal constructor */
	public CcAddress(String addressId) {
		this.addressId = addressId;
	}

	/** full constructor */
	public CcAddress(String addressId, String province, String city,
			String county, String town, String village, String detail) {
		this.addressId = addressId;
		this.province = province;
		this.city = city;
		this.county = county;
		this.town = town;
		this.village = village;
		this.detail = detail;
	}

	// Property accessors
	@Id
	@Column(name = "ADDRESS_ID", unique = true, nullable = false, length = 32)
	public String getAddressId() {
		return this.addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	@Column(name = "PROVINCE", length = 5)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 5)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTY", length = 5)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "TOWN", length = 64)
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "VILLAGE", length = 64)
	public String getVillage() {
		return this.village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	@Column(name = "DETAIL", length = 128)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}