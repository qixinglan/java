package com.nci.dcs.em.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CcSettingArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SETTING_AREA", schema = "SQJZ")
public class CcSettingArea implements java.io.Serializable {

	// Fields

	private CcSettingAreaId id;
	private CcAreaInfo ccAreaInfo;
	private CcSysSetting ccSysSetting;

	// Constructors

	/** default constructor */
	public CcSettingArea() {
	}

	/** full constructor */
	public CcSettingArea(CcSettingAreaId id, CcAreaInfo ccAreaInfo,
			CcSysSetting ccSysSetting) {
		this.id = id;
		this.ccAreaInfo = ccAreaInfo;
		this.ccSysSetting = ccSysSetting;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "areaId", column = @Column(name = "AREA_ID", nullable = false, length = 32)),
			@AttributeOverride(name = "settingId", column = @Column(name = "SETTING_ID", nullable = false, length = 32)) })
	public CcSettingAreaId getId() {
		return this.id;
	}

	public void setId(CcSettingAreaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID", nullable = false, insertable = false, updatable = false)
	public CcAreaInfo getCcAreaInfo() {
		return this.ccAreaInfo;
	}

	public void setCcAreaInfo(CcAreaInfo ccAreaInfo) {
		this.ccAreaInfo = ccAreaInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_ID", nullable = false, insertable = false, updatable = false)
	public CcSysSetting getCcSysSetting() {
		return this.ccSysSetting;
	}

	public void setCcSysSetting(CcSysSetting ccSysSetting) {
		this.ccSysSetting = ccSysSetting;
	}

}