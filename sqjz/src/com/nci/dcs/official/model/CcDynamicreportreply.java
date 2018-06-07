package com.nci.dcs.official.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * Dynamicreportreply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DYNAMICREPORTREPLY", schema = "SQJZ")
public class CcDynamicreportreply implements java.io.Serializable {

	// Fields

	private String id;
	private String relyContent;
	private String replyPersonId;
	private String replyPersonName;
	private Date replydate;
//	private List<CcUploadfiles> uploadfiles;
	
	private CcDynamicreport report;

	@OneToOne()
	@JoinColumn(name = "REPORT_ID", insertable = true, unique = true)
	public CcDynamicreport getReport() {
		return report;
	}

	public void setReport(CcDynamicreport report) {
		this.report = report;
	}

	// Constructors
//	@OneToMany(mappedBy="parentId",fetch=FetchType.EAGER,cascade = CascadeType.ALL,targetEntity=CcUploadfiles.class)
//	public List<CcUploadfiles> getUploadfiles() {
//		return uploadfiles;
//	}
//
//	public void setUploadfiles(List<CcUploadfiles> uploadfiles) {
//		this.uploadfiles = uploadfiles;
//	}

	/** default constructor */
	public CcDynamicreportreply() {
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
	

	@Column(name = "RELY_CONTENT", length = 3000)
	public String getRelyContent() {
		return this.relyContent;
	}

	public void setRelyContent(String relyContent) {
		this.relyContent = relyContent;
	}

	@Column(name = "REPLY_PERSON_ID", length = 32)
	public String getReplyPersonId() {
		return this.replyPersonId;
	}

	public void setReplyPersonId(String replyPersonId) {
		this.replyPersonId = replyPersonId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPLYDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getReplydate() {
		return this.replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	@Column(name = "REPLY_PERSON_NAME", length = 60)
	public String getReplyPersonName() {
		return replyPersonName;
	}

	public void setReplyPersonName(String replyPersonName) {
		this.replyPersonName = replyPersonName;
	}

}