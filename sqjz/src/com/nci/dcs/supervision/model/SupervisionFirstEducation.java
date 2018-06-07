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
@Table(name = "VIEW_CC_DC_FIRST_EDUCATION", schema = "SQJZ")
public class SupervisionFirstEducation extends ViewSupervision {

	private Date receiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RECEIVE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

}