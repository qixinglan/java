package com.nci.dcs.em.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

/**
 * CcSysSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SYS_SETTING", schema = "SQJZ")
public class CcSysSetting implements java.io.Serializable {

	// Fields

	private String settingId;
	private CcFxryBaseinfo ccFxryBaseinfo;
	private String orgId;
	private String railType;
	private String gisId;
	private Set<CcLocationPeriod> ccLocationPeriods = new HashSet<CcLocationPeriod>(
			0);
	private Set<CcSettingArea> ccSettingAreas = new HashSet<CcSettingArea>(0);

	// Constructors

	/** default constructor */
	public CcSysSetting() {
	}

	/** minimal constructor */
	public CcSysSetting(String settingId) {
		this.settingId = settingId;
	}

	/** full constructor */
	public CcSysSetting(String settingId, CcFxryBaseinfo ccFxryBaseinfo,
			String orgId, String railType, String gisId,
			Set<CcLocationPeriod> ccLocationPeriods,
			Set<CcSettingArea> ccSettingAreas) {
		this.settingId = settingId;
		this.ccFxryBaseinfo = ccFxryBaseinfo;
		this.orgId = orgId;
		this.railType = railType;
		this.gisId = gisId;
		this.ccLocationPeriods = ccLocationPeriods;
		this.ccSettingAreas = ccSettingAreas;
	}

	// Property accessors
	@Id
	@Column(name = "SETTING_ID", unique = true, nullable = false, length = 32)
	public String getSettingId() {
		return this.settingId;
	}

	public void setSettingId(String settingId) {
		this.settingId = settingId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	public CcFxryBaseinfo getCcFxryBaseinfo() {
		return this.ccFxryBaseinfo;
	}

	public void setCcFxryBaseinfo(CcFxryBaseinfo ccFxryBaseinfo) {
		this.ccFxryBaseinfo = ccFxryBaseinfo;
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

	@Column(name = "GIS_ID", length = 32)
	public String getGisId() {
		return this.gisId;
	}

	public void setGisId(String gisId) {
		this.gisId = gisId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccSysSetting")
	public Set<CcLocationPeriod> getCcLocationPeriods() {
		return this.ccLocationPeriods;
	}

	public void setCcLocationPeriods(Set<CcLocationPeriod> ccLocationPeriods) {
		this.ccLocationPeriods = ccLocationPeriods;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccSysSetting")
	public Set<CcSettingArea> getCcSettingAreas() {
		return this.ccSettingAreas;
	}

	public void setCcSettingAreas(Set<CcSettingArea> ccSettingAreas) {
		this.ccSettingAreas = ccSettingAreas;
	}

}