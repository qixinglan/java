package com.nci.dcs.em.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcAdjustGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ADJUST_GROUP", schema = "SQJZ")
public class CcAdjustGroup implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String name;
	private String job;
	private String workUnit;
	private BigDecimal isduty;
	private String phone;
	private String homeAddress;

	// Constructors

	/** default constructor */
	public CcAdjustGroup() {
	}

	/** minimal constructor */
	public CcAdjustGroup(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcAdjustGroup(String id, String personId, String name, String job,
			String workUnit, BigDecimal isduty, String phone, String homeAddress) {
		this.id = id;
		this.personId = personId;
		this.name = name;
		this.job = job;
		this.workUnit = workUnit;
		this.isduty = isduty;
		this.phone = phone;
		this.homeAddress = homeAddress;
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

	@Column(name = "NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "JOB", length = 32)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "WORK_UNIT", length = 128)
	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "ISDUTY", precision = 38, scale = 0)
	public BigDecimal getIsduty() {
		return this.isduty;
	}

	public void setIsduty(BigDecimal isduty) {
		this.isduty = isduty;
	}

	@Column(name = "PHONE", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "HOME_ADDRESS", length = 128)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

}