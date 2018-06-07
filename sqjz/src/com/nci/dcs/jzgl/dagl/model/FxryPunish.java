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
 * CcFxryPunish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_PUNISH", schema = "SQJZ")
public class FxryPunish implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryid;
	private Date punishdate;
	private String punishorg;
	private String result;
	private String detail;
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
	@Column(name = "PUNISHDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getPunishdate() {
		return this.punishdate;
	}

	public void setPunishdate(Date punishdate) {
		this.punishdate = punishdate;
	}

	@Column(name = "PUNISHORG", length = 300)
	public String getPunishorg() {
		return this.punishorg;
	}

	public void setPunishorg(String punishorg) {
		this.punishorg = punishorg;
	}

	@Column(name = "RESULT", length = 30)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
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
}