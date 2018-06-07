package com.nci.dcs.em.model;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VIEW_DEVICE_PAIR", schema = "SQJZ")
public class ViewDevicePair implements java.io.Serializable {

	private String id;
	private String pairDeviceNumber;
	private String status;
	private String machineId;
	private Date beginDate;
	private Date endDate;
	private String crtperson;
	private String crtorg;
	private String mdfperson;
	private String mdforg;
	private String orgId;
	private String userdName;

	// Constructors

	/** default constructor */
	public ViewDevicePair() {
	}

	/** minimal constructor */
	public ViewDevicePair(String id) {
		this.id = id;
	}

	/** full constructor */
	public ViewDevicePair(String id, String pairDeviceNumber, String status,
			String machineId, Date beginDate, Date endDate, String crtperson,
			String crtorg, String mdfperson, String mdforg, String orgId,
			String userdName) {
		this.id = id;
		this.pairDeviceNumber = pairDeviceNumber;
		this.status = status;
		this.machineId = machineId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.crtperson = crtperson;
		this.crtorg = crtorg;
		this.mdfperson = mdfperson;
		this.mdforg = mdforg;
		this.orgId = orgId;
		this.userdName = userdName;
	}

	// Property accessors
	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PAIR_DEVICE_NUMBER", length = 12)
	public String getPairDeviceNumber() {
		return this.pairDeviceNumber;
	}

	public void setPairDeviceNumber(String pairDeviceNumber) {
		this.pairDeviceNumber = pairDeviceNumber;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MACHINE_ID", length = 32)
	public String getMachineId() {
		return this.machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE", length = 7)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "CRTPERSON", length = 32)
	public String getCrtperson() {
		return this.crtperson;
	}

	public void setCrtperson(String crtperson) {
		this.crtperson = crtperson;
	}

	@Column(name = "CRTORG", length = 32)
	public String getCrtorg() {
		return this.crtorg;
	}

	public void setCrtorg(String crtorg) {
		this.crtorg = crtorg;
	}

	@Column(name = "MDFPERSON", length = 32)
	public String getMdfperson() {
		return this.mdfperson;
	}

	public void setMdfperson(String mdfperson) {
		this.mdfperson = mdfperson;
	}

	@Column(name = "MDFORG", length = 32)
	public String getMdforg() {
		return this.mdforg;
	}

	public void setMdforg(String mdforg) {
		this.mdforg = mdforg;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "USERD_NAME", length = 60)
	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

}