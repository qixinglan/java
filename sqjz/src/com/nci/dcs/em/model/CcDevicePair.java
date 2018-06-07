package com.nci.dcs.em.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

@Entity
@Table(name = "CC_DEVICE_PAIR", schema = "SQJZ")
@Where(clause = "END_DATE IS NULL")
public class CcDevicePair implements java.io.Serializable {
	private String id;
	private String pairDeviceNumber;
	private CcSuperviseDevice machine;
	private CcSuperviseDevice watch;
	private String status;
	private Date beginDate;
	private Date endDate;
	private String org;
	private Integer currentPair;
	private Integer no;
	private String crtperson;
	private String mdfperson;
	private String crtorg;
	private String mdforg;
	private String fxryid;
	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="PAIR_DEVICE_NUMBER")
	public String getPairDeviceNumber() {
		return pairDeviceNumber;
	}
	public void setPairDeviceNumber(String pairDeviceNumber) {
		this.pairDeviceNumber = pairDeviceNumber;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="MACHINE_ID",referencedColumnName="DEVICE_NUMBER")
	@WhereJoinTable(clause="deviceType=com.nci.dcs.common.Constants.TYPE_MACHINE")
	public CcSuperviseDevice getMachine() {
		return machine;
	}
	public void setMachine(CcSuperviseDevice machine) {
		this.machine = machine;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="WATCH_ID",referencedColumnName="DEVICE_NUMBER")
	@WhereJoinTable(clause="deviceType=com.nci.dcs.common.Constants.TYPE_WATCH")
	public CcSuperviseDevice getWatch() {
		return watch;
	}
	public void setWatch(CcSuperviseDevice watch) {
		this.watch = watch;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@Column(name="END_DATE")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name = "ORG_ID")
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	@Formula("case when END_DATE=null then 1 else 0 end")
	public Integer getCurrentPair() {
		return currentPair;
	}
	public void setCurrentPair(Integer currentPair) {
		this.currentPair = currentPair;
	}
	@Transient
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "CRTPERSON", length = 32)
	public String getCrtperson() {
		return this.crtperson;
	}

	public void setCrtperson(String crtperson) {
		this.crtperson = crtperson;
	}

	@Column(name = "MDFPERSON", length = 32)
	public String getMdfperson() {
		return this.mdfperson;
	}

	public void setMdfperson(String mdfperson) {
		this.mdfperson = mdfperson;
	}

	@Column(name = "CRTORG", length = 32)
	public String getCrtorg() {
		return this.crtorg;
	}

	public void setCrtorg(String crtorg) {
		this.crtorg = crtorg;
	}

	@Column(name = "MDFORG", length = 32)
	public String getMdforg() {
		return this.mdforg;
	}

	public void setMdforg(String mdforg) {
		this.mdforg = mdforg;
	}
	@Column(name = "FXRYID", length = 32)
	public String getFxryid() {
		return this.fxryid;
	}

	public void setFxryid(String fxryid) {
		this.fxryid = fxryid;
	}

}
