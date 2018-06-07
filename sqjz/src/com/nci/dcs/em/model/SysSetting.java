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
 * CcSysSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SYS_SETTING", schema = "SQJZ")
public class SysSetting implements java.io.Serializable {

	// Fields

	private String settingId;
	private String fxryId;
	private String orgId;
	private String railType;
	private String status;
	private Set<LocationPeriod> locPeriods = new HashSet<LocationPeriod>(0);
	private Set<SettingArea> setAreas = new HashSet<SettingArea>(0);

	// Constructors

	/** default constructor */
	public SysSetting() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SETTING_ID", unique = true, nullable = false, length = 32)
	public String getSettingId() {
		return this.settingId;
	}

	public void setSettingId(String settingId) {
		this.settingId = settingId;
	}

	@Column(name = "FXRY_ID")
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "RAIL_TYPE", length = 2)
	public String getRailType() {
		return this.railType;
	}

	public void setRailType(String railType) {
		this.railType = railType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysSetting")
	public Set<LocationPeriod> getLocPeriods() {
		return locPeriods;
	}

	public void setLocPeriods(Set<LocationPeriod> locPeriods) {
		this.locPeriods = locPeriods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysSetting")
	public Set<SettingArea> getSetAreas() {
		return setAreas;
	}

	public void setSetAreas(Set<SettingArea> setAreas) {
		this.setAreas = setAreas;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}