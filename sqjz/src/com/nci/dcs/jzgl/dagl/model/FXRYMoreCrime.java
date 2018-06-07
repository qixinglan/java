package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcMoreCrime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MORE_CRIME", schema = "SQJZ")
public class FXRYMoreCrime implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String crimes;
	private String investigateUnit;
	private Date time;
	private String judgeUnit;
	private String accusation;
	private String prisonTerm;

	// Constructors

	/** default constructor */
	public FXRYMoreCrime() {
	}

	/** full constructor */
	public FXRYMoreCrime(String fxryId, String crimes, String investigateUnit,
			Date time, String judgeUnit, String accusation, String prisonTerm) {
		this.fxryId = fxryId;
		this.crimes = crimes;
		this.investigateUnit = investigateUnit;
		this.time = time;
		this.judgeUnit = judgeUnit;
		this.accusation = accusation;
		this.prisonTerm = prisonTerm;
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

	@Column(name = "CRIMES", length = 150)
	public String getCrimes() {
		return this.crimes;
	}

	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}

	@Column(name = "INVESTIGATE_UNIT", length = 150)
	public String getInvestigateUnit() {
		return this.investigateUnit;
	}

	public void setInvestigateUnit(String investigateUnit) {
		this.investigateUnit = investigateUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "JUDGE_UNIT", length = 150)
	public String getJudgeUnit() {
		return this.judgeUnit;
	}

	public void setJudgeUnit(String judgeUnit) {
		this.judgeUnit = judgeUnit;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "PRISON_TERM", length = 60)
	public String getPrisonTerm() {
		return this.prisonTerm;
	}

	public void setPrisonTerm(String prisonTerm) {
		this.prisonTerm = prisonTerm;
	}

}