package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcWritAffix entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_WRIT_AFFIX", schema = "SQJZ")
public class CcWritAffix implements java.io.Serializable {

	// Fields

	private String writId;
	private String personId;
	private String writName;
	private Long writSize;
	private String orgId;
	private Date uploadTime;
	private String affix;

	// Constructors

	/** default constructor */
	public CcWritAffix() {
	}

	/** minimal constructor */
	public CcWritAffix(String writId) {
		this.writId = writId;
	}

	/** full constructor */
	public CcWritAffix(String writId, String personId, String writName,
			Long writSize, String orgId, Date uploadTime, String affix) {
		this.writId = writId;
		this.personId = personId;
		this.writName = writName;
		this.writSize = writSize;
		this.orgId = orgId;
		this.uploadTime = uploadTime;
		this.affix = affix;
	}

	// Property accessors
	@Id
	@Column(name = "WRIT_ID", unique = true, nullable = false, length = 32)
	public String getWritId() {
		return this.writId;
	}

	public void setWritId(String writId) {
		this.writId = writId;
	}

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "WRIT_NAME", length = 64)
	public String getWritName() {
		return this.writName;
	}

	public void setWritName(String writName) {
		this.writName = writName;
	}

	@Column(name = "WRIT_SIZE", precision = 10, scale = 0)
	public Long getWritSize() {
		return this.writSize;
	}

	public void setWritSize(Long writSize) {
		this.writSize = writSize;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOAD_TIME", length = 7)
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Lob
	@Column(name = "AFFIX")
	public String getAffix() {
		return this.affix;
	}

	public void setAffix(String affix) {
		this.affix = affix;
	}

}