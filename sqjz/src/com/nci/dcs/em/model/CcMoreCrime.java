package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcMoreCrime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MORE_CRIME", schema = "SQJZ")
public class CcMoreCrime implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String crimes;
	private String investigateUnit;
	private Date time;
	private String judgeUnit;
	private String accusation;
	private String prisonTerm;

	// Constructors

	/** default constructor */
	public CcMoreCrime() {
	}

	/** minimal constructor */
	public CcMoreCrime(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcMoreCrime(String id, String personId, String crimes,
			String investigateUnit, Date time, String judgeUnit,
			String accusation, String prisonTerm) {
		this.id = id;
		this.personId = personId;
		this.crimes = crimes;
		this.investigateUnit = investigateUnit;
		this.time = time;
		this.judgeUnit = judgeUnit;
		this.accusation = accusation;
		this.prisonTerm = prisonTerm;
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

	@Column(name = "CRIMES", length = 128)
	public String getCrimes() {
		return this.crimes;
	}

	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}

	@Column(name = "INVESTIGATE_UNIT", length = 128)
	public String getInvestigateUnit() {
		return this.investigateUnit;
	}

	public void setInvestigateUnit(String investigateUnit) {
		this.investigateUnit = investigateUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME", length = 7)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "JUDGE_UNIT", length = 128)
	public String getJudgeUnit() {
		return this.judgeUnit;
	}

	public void setJudgeUnit(String judgeUnit) {
		this.judgeUnit = judgeUnit;
	}

	@Column(name = "ACCUSATION", length = 128)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "PRISON_TERM", length = 10)
	public String getPrisonTerm() {
		return this.prisonTerm;
	}

	public void setPrisonTerm(String prisonTerm) {
		this.prisonTerm = prisonTerm;
	}

}