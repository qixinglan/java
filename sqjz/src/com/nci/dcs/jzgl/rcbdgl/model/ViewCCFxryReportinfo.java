package com.nci.dcs.jzgl.rcbdgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewFxryReportinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_REPORTINFO", schema = "SQJZ")
public class ViewCCFxryReportinfo implements java.io.Serializable {

	// Fields

	private String fxryid;
	private String fxryname;
	private String fxrystate;
	private String sex;
	private String fxryidentirycard;
	private String fxrycode;
	private Date reportdate;
	private Date realreportdate;
	private String orgid;
	private String policename;
	private Integer datastatus;
	private String vacate;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	@Id
	@Column(name = "FXRYID", length = 32)
	public String getFxryid() {
		return this.fxryid;
	}

	public void setFxryid(String fxryid) {
		this.fxryid = fxryid;
	}

	@Column(name = "FXRYNAME", length = 60)
	public String getFxryname() {
		return this.fxryname;
	}

	public void setFxryname(String fxryname) {
		this.fxryname = fxryname;
	}

	@Column(name = "FXRYSTATE", length = 30)
	public String getFxrystate() {
		return this.fxrystate;
	}

	public void setFxrystate(String fxrystate) {
		this.fxrystate = fxrystate;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "FXRYIDENTIRYCARD", length = 20)
	public String getFxryidentirycard() {
		return this.fxryidentirycard;
	}

	public void setFxryidentirycard(String fxryidentirycard) {
		this.fxryidentirycard = fxryidentirycard;
	}

	@Column(name = "FXRYCODE", length = 16)
	public String getFxrycode() {
		return this.fxrycode;
	}

	public void setFxrycode(String fxrycode) {
		this.fxrycode = fxrycode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORTDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.MONTH)
	public Date getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REALREPORTDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getRealreportdate() {
		return this.realreportdate;
	}

	public void setRealreportdate(Date realreportdate) {
		this.realreportdate = realreportdate;
	}

	@Column(name = "ORGID", length = 32)
	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Column(name = "POLICENAME", length = 60)
	public String getPolicename() {
		return this.policename;
	}

	public void setPolicename(String policename) {
		this.policename = policename;
	}

	@Column(name = "DATASTATUS", precision = 1, scale = 0)
	public Integer getDatastatus() {
		return this.datastatus;
	}

	public void setDatastatus(Integer datastatus) {
		this.datastatus = datastatus;
	}

	@Column(name = "ISVACATE")
	public String getVacate() {
		return vacate;
	}

	public void setVacate(String vacate) {
		this.vacate = vacate;
	}
}