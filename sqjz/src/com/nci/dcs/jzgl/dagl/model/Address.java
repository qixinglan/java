package com.nci.dcs.jzgl.dagl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CcAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ADDRESS", schema = "SQJZ")
public class Address implements java.io.Serializable {

	// Fields

	private String id;
	private String province;
	private String city;
	private String county;
	private String town;
	private String fulltext;
	private String detail;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(String province, String city, String county, String town,
			String fulltext, String detail) {
		this.province = province;
		this.city = city;
		this.county = county;
		this.town = town;
		this.fulltext = fulltext;
		this.detail = detail;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PROVINCE", length = 30)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 30)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTY", length = 30)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "TOWN", length = 30)
	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Column(name = "FULLTEXT", length = 1500)
	public String getFulltext() {
		return this.fulltext;
	}

	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}

	@Column(name = "DETAIL", length = 300)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}