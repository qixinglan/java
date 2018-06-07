package com.nci.dcs.jzgl.rcbdgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
/**
 * CcReportInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_REPORT_INFO", schema = "SQJZ")
public class ReportInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String affirmUserId;
	private Date reportDate;
	private String reportOrgId;
	private Date affirmTime;
	private String fxryId;
	private String description;
	private Date realReportDate;
	private String reportType;
    private String affirmUserName;
    private Integer dataStatus; 
    private FXRYBaseinfo baseinfo;
    private String creater; 
    private String createTime;
    private String reportDateTime;
    private String createrId;//审批人
	// Constructors

	/** default constructor */
	public ReportInfo() {
	}

	/** full constructor */
	public ReportInfo(String affirmUserId, Date reportDate,
			String reportOrgId, Date affirmTime, String fxryId,
			String description, Date realReportDate, String reportType,String affirmUserName,Integer dataStatus,FXRYBaseinfo baseinfo) {
		this.affirmUserId = affirmUserId;
		this.reportDate = reportDate;
		this.reportOrgId = reportOrgId;
		this.affirmTime = affirmTime;
		this.fxryId = fxryId;
		this.description = description;
		this.realReportDate = realReportDate;
		this.reportType = reportType;
		this.affirmUserName=affirmUserName;
		this.dataStatus=dataStatus;
		this.baseinfo=baseinfo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "AFFIRM_USER_ID", length = 32)
	public String getAffirmUserId() {
		return this.affirmUserId;
	}

	public void setAffirmUserId(String affirmUserId) {
		this.affirmUserId = affirmUserId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_DATE", length = 7)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Column(name = "REPORT_ORG_ID", length = 32)
	public String getReportOrgId() {
		return this.reportOrgId;
	}

	public void setReportOrgId(String reportOrgId) {
		this.reportOrgId = reportOrgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AFFIRM_TIME", length = 7)
	public Date getAffirmTime() {
		return this.affirmTime;
	}

	public void setAffirmTime(Date affirmTime) {
		this.affirmTime = affirmTime;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "DESCRIPTION", length = 600)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REAL_REPORT_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getRealReportDate() {
		return this.realReportDate;
	}

	public void setRealReportDate(Date realReportDate) {
		this.realReportDate = realReportDate;
	}

	@Column(name = "REPORT_TYPE", length = 30)
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Column(name = "AFFIRM_USER_NAME", length = 60)
	public String getaffirmUserName() {
		return this.affirmUserName;
	}

	public void setaffirmUserName(String affirmUserName) {
		this.affirmUserName = affirmUserName;
	}
	
	@Column(name = "DATA_STATUS")
	public Integer getdataStatus() {
		return this.dataStatus;
	}

	public void setdataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	@Transient
	public FXRYBaseinfo getBaseinfo() {
		return baseinfo;
	}

	public void setBaseinfo(FXRYBaseinfo baseinfo) {
		this.baseinfo = baseinfo;
	}
	
	@Transient
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Transient
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(String reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	
	@Column(name = "CREATER_ID", length = 32)
	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
}