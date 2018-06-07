package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CcLocationPeriod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_LOCATION_PERIOD", schema = "SQJZ")
public class CcLocationPeriod implements java.io.Serializable {

	// Fields

	private String id;
	private CcSysSetting ccSysSetting;
	private String startTime;
	private String endTiem;
	private Short space;

	// Constructors

	/** default constructor */
	public CcLocationPeriod() {
	}

	/** minimal constructor */
	public CcLocationPeriod(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcLocationPeriod(String id, CcSysSetting ccSysSetting,
			String startTime, String endTiem, Short space) {
		this.id = id;
		this.ccSysSetting = ccSysSetting;
		this.startTime = startTime;
		this.endTiem = endTiem;
		this.space = space;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_ID")
	public CcSysSetting getCcSysSetting() {
		return this.ccSysSetting;
	}

	public void setCcSysSetting(CcSysSetting ccSysSetting) {
		this.ccSysSetting = ccSysSetting;
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