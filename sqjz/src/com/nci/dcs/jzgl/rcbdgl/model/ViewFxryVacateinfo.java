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
 * ViewFxryVacateinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_VACATEINFO", schema = "SQJZ")
public class ViewFxryVacateinfo implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7354549936521913995L;
	// Fields

	private String fxryid;
	private String fxryname;
	private String fxrystate;
	private String sex;
	private String fxryidentirycard;
	private String fxrycode;
	private String id;
	private Date startDate;
	private Date endDate;
	private String reason;
	private Integer period;
	private Date reportDate;
	private String termini;
	private String recordOrgId;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}
	// Constructors

	/** default constructor */
	public ViewFxryVacateinfo() {
	}


	/** full constructor */
	public ViewFxryVacateinfo(String fxryid, String fxryname,
			String fxrystate, String sex, String fxryidentirycard,
			String fxrycode, String id, Date startDate, Date endDate,
			String reason, Integer period, Date reportDate, String termini,
			String recordOrgId) {
		this.fxryid = fxryid;
		this.fxryname = fxryname;
		this.fxrystate = fxrystate;
		this.sex = sex;
		this.fxryidentirycard = fxryidentirycard;
		this.fxrycode = fxrycode;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.period = period;
		this.reportDate = reportDate;
		this.termini = termini;
		this.recordOrgId = recordOrgId;
	}

	// Property accessors

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

	@Id
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "REASON", length = 300)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "PERIOD", precision = 5, scale = 0)
	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORT_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Column(name = "TERMINI", length = 300)
	public String getTermini() {
		return this.termini;
	}

	public void setTermini(String termini) {
		this.termini = termini;
	}
	
	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

}