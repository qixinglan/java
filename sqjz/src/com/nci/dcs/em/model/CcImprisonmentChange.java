package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcImprisonmentChange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_IMPRISONMENT_CHANGE", schema = "SQJZ")
public class CcImprisonmentChange implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String changeType;
	private Date changeDate;
	private String range;
	private String reason;

	// Constructors

	/** default constructor */
	public CcImprisonmentChange() {
	}

	/** minimal constructor */
	public CcImprisonmentChange(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcImprisonmentChange(String id, String personId, String changeType,
			Date changeDate, String range, String reason) {
		this.id = id;
		this.personId = personId;
		this.changeType = changeType;
		this.changeDate = changeDate;
		this.range = range;
		this.reason = reason;
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

	@Column(name = "CHANGE_TYPE", length = 5)
	public String getChangeType() {
		return this.changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHANGE_DATE", length = 7)
	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	@Column(name = "RANGE", length = 20)
	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Column(name = "REASON", length = 20)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}