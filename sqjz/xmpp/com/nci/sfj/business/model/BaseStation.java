package com.nci.sfj.business.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Description:基站信息实体类
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_STATION", schema = "SQJZ")
public class BaseStation implements java.io.Serializable {

	// Fields
	/**
	 *  Description:联合主键
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "mcc", column = @Column(name = "MCC", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "mnc", column = @Column(name = "MNC", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "lac", column = @Column(name = "LAC", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "cell", column = @Column(name = "CELL", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "lng", column = @Column(name = "LNG", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "lat", column = @Column(name = "LAT", nullable = false, precision = 22, scale = 0)) })
	private BaseStationId id;
	@Column(name = "O_LNG")
	private Double OLng;
	@Column(name = "O_LAT")
	private Double OLat;
	@Column(name = "PRECISION")
	private Double precision;
	@Column(name = "DAY")
	private Date day;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "REGION")
	private String region;
	@Column(name = "CITY")
	private String city;
	@Column(name = "COUNTRY")
	private String country;

	// Property accessors
	public BaseStationId getId() {
		return this.id;
	}

	public void setId(BaseStationId id) {
		this.id = id;
	}

	public Double getOLng() {
		return OLng;
	}

	public void setOLng(Double oLng) {
		OLng = oLng;
	}

	public Double getOLat() {
		return OLat;
	}

	public void setOLat(Double oLat) {
		OLat = oLat;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}