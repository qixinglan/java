package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcEscortInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ESCORT_INFO", schema = "SQJZ")
public class CcEscortInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String policeName;
	private String policeOffice;
	private String job;
	private Date escortDate;

	// Constructors

	/** default constructor */
	public CcEscortInfo() {
	}

	/** minimal constructor */
	public CcEscortInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcEscortInfo(String id, String personId, String policeName,
			String policeOffice, String job, Date escortDate) {
		this.id = id;
		this.personId = personId;
		this.policeName = policeName;
		this.policeOffice = policeOffice;
		this.job = job;
		this.escortDate = escortDate;
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

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "POLICE_NAME", length = 128)
	public String getPoliceName() {
		return this.policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	@Column(name = "POLICE_OFFICE", length = 128)
	public String getPoliceOffice() {
		return this.policeOffice;
	}

	public void setPoliceOffice(String policeOffice) {
		this.policeOffice = policeOffice;
	}

	@Column(name = "JOB", length = 128)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ESCORT_DATE", length = 7)
	public Date getEscortDate() {
		return this.escortDate;
	}

	public void setEscortDate(Date escortDate) {
		this.escortDate = escortDate;
	}

}