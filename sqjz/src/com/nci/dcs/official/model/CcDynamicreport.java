package com.nci.dcs.official.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * Dynamicreport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DYNAMICREPORT", schema = "SQJZ")
public class CcDynamicreport implements java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String content;
	private String status;
	private Date createdate;
	private String creater;
	private String creatername;
	private Date reporttime;
	private String receiveOrgId;
	private String recordOrgId;
	private String affixId;
//	private List<File> file;
//	private List<String> filename;
	private CcDynamicreportreply reply;
//	private List<CcUploadfiles> uploadfiles;

//	@OneToMany(mappedBy="parentId",fetch=FetchType.EAGER,cascade = CascadeType.ALL,targetEntity=CcUploadfiles.class)
//	public List getUploadfiles() {
//		return uploadfiles;
//	}
//
//	public void setUploadfiles(List uploadfiles) {
//		this.uploadfiles = uploadfiles;
//	}

	@OneToOne(mappedBy="report",fetch=FetchType.EAGER,cascade=CascadeType.MERGE,targetEntity=CcDynamicreportreply.class)
	public CcDynamicreportreply getReply() {
		return reply;
	}

	public void setReply(CcDynamicreportreply reply) {
		this.reply = reply;
	}

// Constructors
//@Transient
//	public List<File> getFile() {
//		return file;
//	}
//
//	public void setFile(List<File> file) {
//		this.file = file;
//	}
//	@Transient
//	public List<String> getFilename() {
//		return filename;
//	}
//
//	public void setFilename(List<String> filename) {
//		this.filename = filename;
//	}

	/** default constructor */
	public CcDynamicreport() {
	}

	/** minimal constructor */
	public CcDynamicreport(String id) {
		this.id = id;
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

	@Column(name = "CONTENT", length = 3000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATUS", precision = 22, scale = 0)
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

	@Column(name = "CREATER", length = 60)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORTTIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getReporttime() {
		return this.reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	@Column(name = "RECEIVE_ORG_ID", length = 32)
	public String getReceiveOrgId() {
		return this.receiveOrgId;
	}

	public void setReceiveOrgId(String receiveOrgId) {
		this.receiveOrgId = receiveOrgId;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Column(name = "AFFIX_ID", length = 32)
	public String getAffixId() {
		return this.affixId;
	}

	public void setAffixId(String affixId) {
		this.affixId = affixId;
	}
	@Column(name = "CREATERNAME")
	public String getCreatername() {
		return creatername;
	}

	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}

}