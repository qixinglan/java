package com.nci.dcs.jzgl.rcbdgl.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewFxryAddreportinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_ADDREPORTINFO", schema = "SQJZ")
public class ViewFxryAddreportinfo implements java.io.Serializable {

	// Fields
	private String fxryid;
	private String fxrycode;
	private String fxrydevicecode;
	private String fxrystate;
	private String fxryname;
	private String fxryisadult;
	private String sex;
	private String nation;
	private String fxryidentity;
	private Date fxrybirthday;
	private String orgname;
	private String reportdate;
	private String nextreportdate;
	private List fxrylist;
	// Constructors

	/** default constructor */
	public ViewFxryAddreportinfo() {
	}

	/** full constructor */
	/** full constructor */
	public ViewFxryAddreportinfo(String fxryid, String fxrycode,
			String fxrydevicecode, String fxrystate, String fxryname,
			String fxryisadult, String sex, String nation, String fxryidentity,
			Date fxrybirthday, String orgname, String reportdate,
			String nextreportdate) {
		this.fxryid = fxryid;
		this.fxrycode = fxrycode;
		this.fxrydevicecode = fxrydevicecode;
		this.fxrystate = fxrystate;
		this.fxryname = fxryname;
		this.fxryisadult = fxryisadult;
		this.sex = sex;
		this.nation = nation;
		this.fxryidentity = fxryidentity;
		this.fxrybirthday = fxrybirthday;
		this.orgname = orgname;
		this.reportdate = reportdate;
		this.nextreportdate = nextreportdate;
	}
	
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "FXRYID", unique = true, nullable = false, length = 32)
	public String getFxryid() {
		return this.fxryid;
	}

	public void setFxryid(String fxryid) {
		this.fxryid = fxryid;
	}

	@Column(name = "FXRYCODE", length = 16)
	public String getFxrycode() {
		return this.fxrycode;
	}

	public void setFxrycode(String fxrycode) {
		this.fxrycode = fxrycode;
	}

	@Column(name = "FXRYDEVICECODE", length = 12)
	public String getFxrydevicecode() {
		return this.fxrydevicecode;
	}

	public void setFxrydevicecode(String fxrydevicecode) {
		this.fxrydevicecode = fxrydevicecode;
	}

	@Column(name = "FXRYSTATE", length = 30)
	public String getFxrystate() {
		return this.fxrystate;
	}

	public void setFxrystate(String fxrystate) {
		this.fxrystate = fxrystate;
	}

	@Column(name = "FXRYNAME", length = 60)
	public String getFxryname() {
		return this.fxryname;
	}

	public void setFxryname(String fxryname) {
		this.fxryname = fxryname;
	}

	@Column(name = "FXRYISADULT", length = 30)
	public String getFxryisadult() {
		return this.fxryisadult;
	}

	public void setFxryisadult(String fxryisadult) {
		this.fxryisadult = fxryisadult;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "FXRYIDENTITY", length = 18)
	public String getFxryidentity() {
		return this.fxryidentity;
	}

	public void setFxryidentity(String fxryidentity) {
		this.fxryidentity = fxryidentity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FXRYBIRTHDAY", length = 7)
	public Date getFxrybirthday() {
		return this.fxrybirthday;
	}

	public void setFxrybirthday(Date fxrybirthday) {
		this.fxrybirthday = fxrybirthday;
	}

	@Column(name = "ORGNAME", length = 100)
	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "REPORTDATE", length = 0)
	public String getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	@Column(name = "NEXTREPORTDATE", length = 0)
	public String getNextreportdate() {
		return this.nextreportdate;
	}

	public void setNextreportdate(String nextreportdate) {
		this.nextreportdate = nextreportdate;
	}
@Transient
	public List getFxrylist() {
		return fxrylist;
	}

	public void setFxrylist(List fxrylist) {
		this.fxrylist = fxrylist;
	}
	
}