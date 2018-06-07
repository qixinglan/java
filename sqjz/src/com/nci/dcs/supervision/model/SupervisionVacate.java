package com.nci.dcs.supervision.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewCcDcPhoneReportId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DC_VACATE", schema = "SQJZ")
public class SupervisionVacate extends ViewSupervision {

	// Fields

	private String fxryId;
	private Date startDate;
	private Date endDate;
	private Date reportDate;

	@Column(name = "FXRY_ID")
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}