package com.nci.dcs.em.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CcAreaInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_AREA_INFO", schema = "SQJZ")
public class AreaInfo implements java.io.Serializable {

	// Fields

	private String areaId;
	private String areaName;
	private String parentID;
	private Set<SettingArea> settingAreas = new HashSet<SettingArea>(0);

	// Constructors

	/** default constructor */
	public AreaInfo() {
	}

	public AreaInfo(String areaId) {
		this.areaId = areaId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "NAME", length = 32)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "PARENTID", length = 32)
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "areaInfo")
	public Set<SettingArea> getSettingAreas() {
		return settingAreas;
	}

	public void setSettingAreas(Set<SettingArea> settingAreas) {
		this.settingAreas = settingAreas;
	}

}