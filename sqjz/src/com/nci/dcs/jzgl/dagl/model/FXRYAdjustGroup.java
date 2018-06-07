package com.nci.dcs.jzgl.dagl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CcAdjustGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ADJUST_GROUP", schema = "SQJZ")
public class FXRYAdjustGroup implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String name;
	private String job;
	private String workUnit;
	private String isduty;
	private String phone;
	private String homeAddress;
	private String recordOrgId;

	// Constructors

	/** default constructor */
	public FXRYAdjustGroup() {
	}

	/** full constructor */
	public FXRYAdjustGroup(String fxryId, String name, String job,
			String workUnit, String isduty, String phone, String homeAddress,
			String recordOrgId) {
		this.fxryId = fxryId;
		this.name = name;
		this.job = job;
		this.workUnit = workUnit;
		this.isduty = isduty;
		this.phone = phone;
		this.homeAddress = homeAddress;
		this.recordOrgId = recordOrgId;
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

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "JOB", length = 60)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "WORK_UNIT", length = 150)
	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "ISDUTY", length = 30)
	public String getIsduty() {
		return this.isduty;
	}

	public void setIsduty(String isduty) {
		this.isduty = isduty;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "HOME_ADDRESS", length = 300)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

}