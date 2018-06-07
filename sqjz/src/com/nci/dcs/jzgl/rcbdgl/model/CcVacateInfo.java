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

/**
 * CcVacateInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_VACATE_INFO", schema = "SQJZ")
public class CcVacateInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private Date startDate;
	private Date endDate;
	private String reason;
	private Integer period;
	private String recordOrgId;
	private String recordUserId;
	private Date recordTime;
	private Date reportDate;
	private String termini;
	private String creater;
	private String createTime;
    private String reportDateTime;
    private Date createDate;
    private String createrId;//审批人
    private String bz;

//	private FXRYBaseinfo fxry;
	// Constructors

	/** default constructor */
	public CcVacateInfo() {
	}

	/** minimal constructor */
	public CcVacateInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcVacateInfo(String id, String personId, Date startDate,
			Date endDate, String reason, Integer period, String recordOrgId,
			String recordUserId, Date recordTime, Date reportDate,
			String termini) {
		this.id = id;
		this.personId = personId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.period = period;
		this.recordOrgId = recordOrgId;
		this.recordUserId = recordUserId;
		this.recordTime = recordTime;
		this.reportDate = reportDate;
		this.termini = termini;
	}

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

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
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

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Column(name = "RECORD_USER_ID", length = 32)
	public String getRecordUserId() {
		return this.recordUserId;
	}

	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECORD_TIME", length = 11)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
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
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "PERSON_ID")
//	public FXRYBaseinfo getFxry() {
//		return fxry;
//	}
//
//	public void setFxry(FXRYBaseinfo fxry) {
//		this.fxry = fxry;
//	}
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATETIME)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATER_ID", length = 32)
	public String getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	
	@Column(name = "BZ", length = 600)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}