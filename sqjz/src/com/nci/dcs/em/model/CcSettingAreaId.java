package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CcSettingAreaId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class CcSettingAreaId implements java.io.Serializable {

	// Fields

	private String areaId;
	private String settingId;

	// Constructors

	/** default constructor */
	public CcSettingAreaId() {
	}

	/** full constructor */
	public CcSettingAreaId(String areaId, String settingId) {
		this.areaId = areaId;
		this.settingId = settingId;
	}

	// Property accessors

	@Column(name = "AREA_ID", nullable = false, length = 32)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "SETTING_ID", nullable = false, length = 32)
	public String getSettingId() {
		return this.settingId;
	}

	public void setSettingId(String settingId) {
		this.settingId = settingId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CcSettingAreaId))
			return false;
		CcSettingAreaId castOther = (CcSettingAreaId) other;

		return ((this.getAreaId() == castOther.getAreaId()) || (this
				.getAreaId() != null && castOther.getAreaId() != null && this
				.getAreaId().equals(castOther.getAreaId())))
				&& ((this.getSettingId() == castOther.getSettingId()) || (this
						.getSettingId() != null
						&& castOther.getSettingId() != null && this
						.getSettingId().equals(castOther.getSettingId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAreaId() == null ? 0 : this.getAreaId().hashCode());
		result = 37 * result
				+ (getSettingId() == null ? 0 : this.getSettingId().hashCode());
		return result;
	}

}