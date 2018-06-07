package com.nci.dcs.em.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcRemoveAdjust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_REMOVE_ADJUST", schema = "SQJZ")
public class CcRemoveAdjust implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String removeReason;
	private Date removeDate;
	private String jailType;
	private Date jailDate;
	private String jailReason;
	private Date deathDate;
	private BigDecimal deathReason;
	private String deathReasonDetail;
	private String performance;
	private String manner;
	private BigDecimal istrained;
	private BigDecimal hascertificate;
	private String speciality;
	private BigDecimal issanwu;
	private String risk;
	private String familyContact;
	private String remark;

	// Constructors

	/** default constructor */
	public CcRemoveAdjust() {
	}

	/** minimal constructor */
	public CcRemoveAdjust(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcRemoveAdjust(String id, String personId, String removeReason,
			Date removeDate, String jailType, Date jailDate, String jailReason,
			Date deathDate, BigDecimal deathReason, String deathReasonDetail,
			String performance, String manner, BigDecimal istrained,
			BigDecimal hascertificate, String speciality, BigDecimal issanwu,
			String risk, String familyContact, String remark) {
		this.id = id;
		this.personId = personId;
		this.removeReason = removeReason;
		this.removeDate = removeDate;
		this.jailType = jailType;
		this.jailDate = jailDate;
		this.jailReason = jailReason;
		this.deathDate = deathDate;
		this.deathReason = deathReason;
		this.deathReasonDetail = deathReasonDetail;
		this.performance = performance;
		this.manner = manner;
		this.istrained = istrained;
		this.hascertificate = hascertificate;
		this.speciality = speciality;
		this.issanwu = issanwu;
		this.risk = risk;
		this.familyContact = familyContact;
		this.remark = remark;
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

	@Column(name = "REMOVE_REASON", length = 5)
	public String getRemoveReason() {
		return this.removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	@Column(name = "JAIL_TYPE", length = 5)
	public String getJailType() {
		return this.jailType;
	}

	public void setJailType(String jailType) {
		this.jailType = jailType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JAIL_DATE", length = 7)
	public Date getJailDate() {
		return this.jailDate;
	}

	public void setJailDate(Date jailDate) {
		this.jailDate = jailDate;
	}

	@Column(name = "JAIL_REASON", length = 32)
	public String getJailReason() {
		return this.jailReason;
	}

	public void setJailReason(String jailReason) {
		this.jailReason = jailReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEATH_DATE", length = 7)
	public Date getDeathDate() {
		return this.deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Column(name = "DEATH_REASON", precision = 38, scale = 0)
	public BigDecimal getDeathReason() {
		return this.deathReason;
	}

	public void setDeathReason(BigDecimal deathReason) {
		this.deathReason = deathReason;
	}

	@Column(name = "DEATH_REASON_DETAIL", length = 64)
	public String getDeathReasonDetail() {
		return this.deathReasonDetail;
	}

	public void setDeathReasonDetail(String deathReasonDetail) {
		this.deathReasonDetail = deathReasonDetail;
	}

	@Column(name = "PERFORMANCE", length = 5)
	public String getPerformance() {
		return this.performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Column(name = "MANNER", length = 5)
	public String getManner() {
		return this.manner;
	}

	public void setManner(String manner) {
		this.manner = manner;
	}

	@Column(name = "ISTRAINED", precision = 38, scale = 0)
	public BigDecimal getIstrained() {
		return this.istrained;
	}

	public void setIstrained(BigDecimal istrained) {
		this.istrained = istrained;
	}

	@Column(name = "HASCERTIFICATE", precision = 38, scale = 0)
	public BigDecimal getHascertificate() {
		return this.hascertificate;
	}

	public void setHascertificate(BigDecimal hascertificate) {
		this.hascertificate = hascertificate;
	}

	@Column(name = "SPECIALITY", length = 64)
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "ISSANWU", precision = 38, scale = 0)
	public BigDecimal getIssanwu() {
		return this.issanwu;
	}

	public void setIssanwu(BigDecimal issanwu) {
		this.issanwu = issanwu;
	}

	@Column(name = "RISK", length = 5)
	public String getRisk() {
		return this.risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	@Column(name = "FAMILY_CONTACT", length = 5)
	public String getFamilyContact() {
		return this.familyContact;
	}

	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}