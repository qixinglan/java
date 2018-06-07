package com.nci.dcs.em.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CcAreaInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_AREA_INFO", schema = "SQJZ")
public class CcAreaInfo implements java.io.Serializable {

	// Fields

	private String areaId;
	private String areaName;
	private String gisId;
	private Set<CcSettingArea> ccSettingAreas = new HashSet<CcSettingArea>(0);

	// Constructors

	/** default constructor */
	public CcAreaInfo() {
	}

	/** minimal constructor */
	public CcAreaInfo(String areaId) {
		this.areaId = areaId;
	}

	/** full constructor */
	public CcAreaInfo(String areaId, String areaName, String gisId,
			Set<CcSettingArea> ccSettingAreas) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.gisId = gisId;
		this.ccSettingAreas = ccSettingAreas;
	}

	// Property accessors
	@Id
	@Column(name = "AREA_ID", unique = true, nullable = false, length = 32)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "AREA_NAME", length = 32)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "GIS_ID", length = 32)
	public String getGisId() {
		return this.gisId;
	}

	public void setGisId(String gisId) {
		this.gisId = gisId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccAreaInfo")
	public Set<CcSettingArea> getCcSettingAreas() {
		return this.ccSettingAreas;
	}

	public void setCcSettingAreas(Set<CcSettingArea> ccSettingAreas) {
		this.ccSettingAreas = ccSettingAreas;
	}

}