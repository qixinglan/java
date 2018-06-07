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
 * CcReportInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_REPORT_ORG", schema = "SQJZ")
public class FXRYReportOrg implements java.io.Serializable {
	// Fields

	private String id;
	private String fxryId;
	private String orgId;
	private Date reportTime;
	private String personId;
	private String phone;
	private String recordOrgId;

	// Constructors

	/** default constructor */
	public FXRYReportOrg() {
	}

	/** full constructor */
	public FXRYReportOrg(String fxryId, String orgId, Date reportTime,
			String personId, String phone, String recordOrgId) {
		this.fxryId = fxryId;
		this.orgId = orgId;
		this.reportTime = reportTime;
		this.personId = personId;
		this.phone = phone;
		this.recordOrgId = recordOrgId;
	}

	
	public void copy(FXRYReportOrg right){
		this.orgId = right.getOrgId();
		this.reportTime = right.getReportTime();
		this.personId = right.getPersonId();
		this.phone = right.getPhone();
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

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}
}