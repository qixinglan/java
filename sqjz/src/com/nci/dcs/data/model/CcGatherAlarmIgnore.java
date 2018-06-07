package com.nci.dcs.data.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcGatherAlarmIgnore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_GATHER_ALARM_IGNORE", schema = "SQJZ")
public class CcGatherAlarmIgnore implements java.io.Serializable {

	// Fields

	private String ignoreId;
	private String orgId;
	private String orgName;
	private Date startTime;
	private Date endTime;
	private Set<CcIgnorePerson> ccIgnorePersons = new HashSet<CcIgnorePerson>(0);
	private Set<CcIgnoreOrg> ccIgnoreOrgs = new HashSet<CcIgnoreOrg>(0);

	// Constructors

	/** default constructor */
	public CcGatherAlarmIgnore() {
	}

	/** minimal constructor */
	public CcGatherAlarmIgnore(String ignoreId) {
		this.ignoreId = ignoreId;
	}
    
	/** full constructor */
	public CcGatherAlarmIgnore(String ignoreId, String orgId,String orgName,
			Date startTime, Date endTime,
			Set<CcIgnorePerson> ccIgnorePersons, Set<CcIgnoreOrg> ccIgnoreOrgs) {
		this.ignoreId = ignoreId;
		this.orgId = orgId;
		this.orgName=orgName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ccIgnorePersons = ccIgnorePersons;
		this.ccIgnoreOrgs = ccIgnoreOrgs;
	}
	@Column(name = "Org_Name", nullable = true, length = 150)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	// Property accessors
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	//@GeneratedValue(generator = "generator")
	@Column(name = "IGNORE_ID", unique = true, nullable = false, length = 32)
	public String getIgnoreId() {
		return this.ignoreId;
	}

	public void setIgnoreId(String ignoreId) {
		this.ignoreId = ignoreId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "START_TIME", length = 16)
	@Temporal(TemporalType.TIMESTAMP)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", length = 16)
	@Temporal(TemporalType.TIMESTAMP)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccGatherAlarmIgnore")
	public Set<CcIgnorePerson> getCcIgnorePersons() {
		return this.ccIgnorePersons;
	}

	public void setCcIgnorePersons(Set<CcIgnorePerson> ccIgnorePersons) {
		this.ccIgnorePersons = ccIgnorePersons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccGatherAlarmIgnore")
	public Set<CcIgnoreOrg> getCcIgnoreOrgs() {
		return this.ccIgnoreOrgs;
	}

	public void setCcIgnoreOrgs(Set<CcIgnoreOrg> ccIgnoreOrgs) {
		this.ccIgnoreOrgs = ccIgnoreOrgs;
	}

}