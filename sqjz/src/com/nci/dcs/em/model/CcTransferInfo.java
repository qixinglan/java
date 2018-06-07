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
 * CcTransferInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_TRANSFER_INFO", schema = "SQJZ")
public class CcTransferInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String transferTypr;
	private String outOrgId;
	private Date outTime;
	private Date receiveTime;
	private String receiveOrgId;
	private String reason;
	private String affixName;
	private String affix;

	// Constructors

	/** default constructor */
	public CcTransferInfo() {
	}

	/** minimal constructor */
	public CcTransferInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcTransferInfo(String id, String personId, String transferTypr,
			String outOrgId, Date outTime, Date receiveTime,
			String receiveOrgId, String reason, String affixName, String affix) {
		this.id = id;
		this.personId = personId;
		this.transferTypr = transferTypr;
		this.outOrgId = outOrgId;
		this.outTime = outTime;
		this.receiveTime = receiveTime;
		this.receiveOrgId = receiveOrgId;
		this.reason = reason;
		this.affixName = affixName;
		this.affix = affix;
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

	@Column(name = "TRANSFER_TYPR", length = 5)
	public String getTransferTypr() {
		return this.transferTypr;
	}

	public void setTransferTypr(String transferTypr) {
		this.transferTypr = transferTypr;
	}

	@Column(name = "OUT_ORG_ID", length = 32)
	public String getOutOrgId() {
		return this.outOrgId;
	}

	public void setOutOrgId(String outOrgId) {
		this.outOrgId = outOrgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OUT_TIME", length = 7)
	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEIVE_TIME", length = 7)
	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	@Column(name = "RECEIVE_ORG_ID", length = 32)
	public String getReceiveOrgId() {
		return this.receiveOrgId;
	}

	public void setReceiveOrgId(String receiveOrgId) {
		this.receiveOrgId = receiveOrgId;
	}

	@Column(name = "REASON", length = 128)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "AFFIX_NAME", length = 32)
	public String getAffixName() {
		return this.affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
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