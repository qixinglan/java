package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcFxryCrime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_CRIME", schema = "SQJZ")
public class FxryCrime implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryid;
	private Date dealdate;
	/**
	 * 强制措施结束时间:
	 */
	private Date enddate;
	private String dealorg;
	private String crimeType;
	private String accusation;
	private String detail;
	/**
	 * 情况说明
	 */
	private String explain;
	private String recordOrgId;

	// Property accessors
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRYID", nullable = false, length = 32)
	public String getFxryid() {
		return this.fxryid;
	}

	public void setFxryid(String fxryid) {
		this.fxryid = fxryid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEALDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getDealdate() {
		return this.dealdate;
	}

	public void setDealdate(Date dealdate) {
		this.dealdate = dealdate;
	}

	@Column(name = "DEALORG", length = 300)
	public String getDealorg() {
		return this.dealorg;
	}

	public void setDealorg(String dealorg) {
		this.dealorg = dealorg;
	}

	@Column(name = "CRIME_TYPE", length = 30)
	public String getCrimeType() {
		return this.crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "DETAIL", length = 600)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	@Column(name = "EXPLAIN", length = 600)
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}