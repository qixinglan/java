package com.nci.dcs.supervision.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ViewCcDcPhoneReportId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DC_PUBLIC_WORK", schema = "SQJZ")
public class SupervisionPublicWork extends ViewSupervision {

	// Fields

	private Date missionSta;
	private Date missionEnd;
	private String isvacate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MISSION_STA", length = 7)
	public Date getMissionSta() {
		return this.missionSta;
	}

	public void setMissionSta(Date missionSta) {
		this.missionSta = missionSta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MISSION_END", length = 7)
	public Date getMissionEnd() {
		return this.missionEnd;
	}

	public void setMissionEnd(Date missionEnd) {
		this.missionEnd = missionEnd;
	}

	@Column(name = "ISVACATE", length = 1)
	public String getIsvacate() {
		return this.isvacate;
	}

	public void setIsvacate(String isvacate) {
		this.isvacate = isvacate;
	}

}