package com.nci.dcs.supervision.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewFxryReadyRelease entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DC_RELEASE", schema = "SQJZ")
public class SupervisionRelease extends ViewSupervision {

	private Date startdate;
	private Date enddate;
	private String adjustType;
	private BigDecimal days;

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "DAYS", precision = 22, scale = 0)
	public BigDecimal getDays() {
		return this.days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}
}