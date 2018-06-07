package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CcLocationPeriod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_LOCATION_PERIOD", schema = "SQJZ")
public class LocationPeriod implements java.io.Serializable {

	// Fields

	private String id;
	private SysSetting sysSetting;
	private String startTime;
	private String endTiem;
	private Short space;

	// Constructors

	/** default constructor */
	public LocationPeriod() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_ID")
	public SysSetting getSysSetting() {
		return sysSetting;
	}

	public void setSysSetting(SysSetting sysSetting) {
		this.sysSetting = sysSetting;
	}

	@Column(name = "START_TIME", length = 5)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIEM", length = 5)
	public String getEndTiem() {
		return this.endTiem;
	}

	public void setEndTiem(String endTiem) {
		this.endTiem = endTiem;
	}

	@Column(name = "SPACE", precision = 4, scale = 0)
	public Short getSpace() {
		return this.space;
	}

	public void setSpace(Short space) {
		this.space = space;
	}

}