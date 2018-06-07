package com.nci.dcs.official.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcWeeknoticesId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_WEEKNOTICES", schema = "SQJZ")
public class CcWeeknotice implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2187807891924745321L;
	private String id;
	private String title;
	private String required;
	private String status;
	private Date createdate;
	private String creater;
	private Date sendtime;
	private String description;
	private String affixId;
	private String recordOrgId;
	private Set<CcNoticereceive> ccNoticereceives = new HashSet<CcNoticereceive>(0);
	private String jgIds;//所有收到通知的机关的ID
	private String receiveType;
	// Constructors

	/** default constructor */
	public CcWeeknotice() {
	}

	// Property accessors

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 150)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "REQUIRED", length = 1500)
	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "CREATER", length = 32)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SENDTIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "DESCRIPTION", length = 1500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "AFFIX_ID", length = 32)
	public String getAffixId() {
		return this.affixId;
	}

	public void setAffixId(String affixId) {
		this.affixId = affixId;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccWeeknotice")
	public Set<CcNoticereceive> getCcNoticereceives() {
		return this.ccNoticereceives;
	}

	public void setCcNoticereceives(Set<CcNoticereceive> ccNoticereceives) {
		this.ccNoticereceives = ccNoticereceives;
	}
	@Transient
	public String getJgIds() {
		return jgIds;
	}

	public void setJgIds(String jgIds) {
		this.jgIds = jgIds;
	}
	@Column(name = "RECEIVETYPE", length = 30)
	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
}