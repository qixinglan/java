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
public class SettingArea implements java.io.Serializable {

	// Fields

	private SettingAreaId id;
	private AreaInfo areaInfo;
	private SysSetting sysSetting;

	// Constructors

	/** default constructor */
	public SettingArea() {
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "areaId", column = @Column(name = "AREA_ID", nullable = false, length = 32)),
			@AttributeOverride(name = "settingId", column = @Column(name = "SETTING_ID", nullable = false, length = 32)) })
	public SettingAreaId getId() {
		return this.id;
	}

	public void setId(SettingAreaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID", nullable = false, insertable = false, updatable = false)
	public AreaInfo getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(AreaInfo areaInfo) {
		this.areaInfo = areaInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_ID", nullable = false, insertable = false, updatable = false)
	public SysSetting getSysSetting() {
		return sysSetting;
	}

	public void setSysSetting(SysSetting sysSetting) {
		this.sysSetting = sysSetting;
	}

}