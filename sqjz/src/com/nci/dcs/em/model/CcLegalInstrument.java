package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcLegalInstrument entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_LEGAL_INSTRUMENT", schema = "SQJZ")
public class CcLegalInstrument implements java.io.Serializable {

	// Fields

	private String instrumentId;
	private String personId;
	private String investigateUnit;
	private Date dateDetention;
	private Date dateRecord;
	private String permitArrestUnit;
	private Date dateArrest;
	private Date dateEndInvestigate;
	private String publicProsecution;
	private String indictmentNumber;
	private Date dateIndictment;
	private String trialUnit;
	private String judgmentNumber;
	private Date dateJudgment;
	private String informNumber;
	private Date dateInform;
	private String orgdetentionAddress;
	private Date dateExecute;
	private String decideUnit;
	private Date writEffectiveDate;
	private String writType;
	private String writNumber;
	private String majorCrime;
	private String administrativePenalty;
	private String criminalPunshment;

	// Constructors

	/** default constructor */
	public CcLegalInstrument() {
	}

	/** minimal constructor */
	public CcLegalInstrument(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	/** full constructor */
	public CcLegalInstrument(String instrumentId, String personId,
			String investigateUnit, Date dateDetention, Date dateRecord,
			String permitArrestUnit, Date dateArrest, Date dateEndInvestigate,
			String publicProsecution, String indictmentNumber,
			Date dateIndictment, String trialUnit, String judgmentNumber,
			Date dateJudgment, String informNumber, Date dateInform,
			String orgdetentionAddress, Date dateExecute, String decideUnit,
			Date writEffectiveDate, String writType, String writNumber,
			String majorCrime, String administrativePenalty,
			String criminalPunshment) {
		this.instrumentId = instrumentId;
		this.personId = personId;
		this.investigateUnit = investigateUnit;
		this.dateDetention = dateDetention;
		this.dateRecord = dateRecord;
		this.permitArrestUnit = permitArrestUnit;
		this.dateArrest = dateArrest;
		this.dateEndInvestigate = dateEndInvestigate;
		this.publicProsecution = publicProsecution;
		this.indictmentNumber = indictmentNumber;
		this.dateIndictment = dateIndictment;
		this.trialUnit = trialUnit;
		this.judgmentNumber = judgmentNumber;
		this.dateJudgment = dateJudgment;
		this.informNumber = informNumber;
		this.dateInform = dateInform;
		this.orgdetentionAddress = orgdetentionAddress;
		this.dateExecute = dateExecute;
		this.decideUnit = decideUnit;
		this.writEffectiveDate = writEffectiveDate;
		this.writType = writType;
		this.writNumber = writNumber;
		this.majorCrime = majorCrime;
		this.administrativePenalty = administrativePenalty;
		this.criminalPunshment = criminalPunshment;
	}

	// Property accessors
	@Id
	@Column(name = "INSTRUMENT_ID", unique = true, nullable = false, length = 32)
	public String getInstrumentId() {
		return this.instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "INVESTIGATE_UNIT", length = 64)
	public String getInvestigateUnit() {
		return this.investigateUnit;
	}

	public void setInvestigateUnit(String investigateUnit) {
		this.investigateUnit = investigateUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DETENTION", length = 7)
	public Date getDateDetention() {
		return this.dateDetention;
	}

	public void setDateDetention(Date dateDetention) {
		this.dateDetention = dateDetention;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RECORD", length = 7)
	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	@Column(name = "PERMIT_ARREST_UNIT", length = 64)
	public String getPermitArrestUnit() {
		return this.permitArrestUnit;
	}

	public void setPermitArrestUnit(String permitArrestUnit) {
		this.permitArrestUnit = permitArrestUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ARREST", length = 7)
	public Date getDateArrest() {
		return this.dateArrest;
	}

	public void setDateArrest(Date dateArrest) {
		this.dateArrest = dateArrest;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_END_INVESTIGATE", length = 7)
	public Date getDateEndInvestigate() {
		return this.dateEndInvestigate;
	}

	public void setDateEndInvestigate(Date dateEndInvestigate) {
		this.dateEndInvestigate = dateEndInvestigate;
	}

	@Column(name = "PUBLIC_PROSECUTION", length = 64)
	public String getPublicProsecution() {
		return this.publicProsecution;
	}

	public void setPublicProsecution(String publicProsecution) {
		this.publicProsecution = publicProsecution;
	}

	@Column(name = "INDICTMENT_NUMBER", length = 32)
	public String getIndictmentNumber() {
		return this.indictmentNumber;
	}

	public void setIndictmentNumber(String indictmentNumber) {
		this.indictmentNumber = indictmentNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INDICTMENT", length = 7)
	public Date getDateIndictment() {
		return this.dateIndictment;
	}

	public void setDateIndictment(Date dateIndictment) {
		this.dateIndictment = dateIndictment;
	}

	@Column(name = "TRIAL_UNIT", length = 64)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
	}

	@Column(name = "JUDGMENT_NUMBER", length = 32)
	public String getJudgmentNumber() {
		return this.judgmentNumber;
	}

	public void setJudgmentNumber(String judgmentNumber) {
		this.judgmentNumber = judgmentNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "INFORM_NUMBER", length = 32)
	public String getInformNumber() {
		return this.informNumber;
	}

	public void setInformNumber(String informNumber) {
		this.informNumber = informNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INFORM", length = 7)
	public Date getDateInform() {
		return this.dateInform;
	}

	public void setDateInform(Date dateInform) {
		this.dateInform = dateInform;
	}

	@Column(name = "ORGDETENTION_ADDRESS", length = 64)
	public String getOrgdetentionAddress() {
		return this.orgdetentionAddress;
	}

	public void setOrgdetentionAddress(String orgdetentionAddress) {
		this.orgdetentionAddress = orgdetentionAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_EXECUTE", length = 7)
	public Date getDateExecute() {
		return this.dateExecute;
	}

	public void setDateExecute(Date dateExecute) {
		this.dateExecute = dateExecute;
	}

	@Column(name = "DECIDE_UNIT", length = 128)
	public String getDecideUnit() {
		return this.decideUnit;
	}

	public void setDecideUnit(String decideUnit) {
		this.decideUnit = decideUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WRIT_EFFECTIVE_DATE", length = 7)
	public Date getWritEffectiveDate() {
		return this.writEffectiveDate;
	}

	public void setWritEffectiveDate(Date writEffectiveDate) {
		this.writEffectiveDate = writEffectiveDate;
	}

	@Column(name = "WRIT_TYPE", length = 10)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Column(name = "WRIT_NUMBER", length = 32)
	public String getWritNumber() {
		return this.writNumber;
	}

	public void setWritNumber(String writNumber) {
		this.writNumber = writNumber;
	}

	@Column(name = "MAJOR_CRIME", length = 128)
	public String getMajorCrime() {
		return this.majorCrime;
	}

	public void setMajorCrime(String majorCrime) {
		this.majorCrime = majorCrime;
	}

	@Column(name = "ADMINISTRATIVE_PENALTY", length = 128)
	public String getAdministrativePenalty() {
		return this.administrativePenalty;
	}

	public void setAdministrativePenalty(String administrativePenalty) {
		this.administrativePenalty = administrativePenalty;
	}

	@Column(name = "CRIMINAL_PUNSHMENT", length = 128)
	public String getCriminalPunshment() {
		return this.criminalPunshment;
	}

	public void setCriminalPunshment(String criminalPunshment) {
		this.criminalPunshment = criminalPunshment;
	}

}