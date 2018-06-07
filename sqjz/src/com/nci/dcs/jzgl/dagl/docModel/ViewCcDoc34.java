package com.nci.dcs.jzgl.dagl.docModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc34 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_34", schema = "SQJZ")
public class ViewCcDoc34 implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ViewCcDoc34() {
	}

	private String id;
	private String fxryId;
	private String name;
	private String sex;
	private Date birthdate;
	private String houseAddress;
	private String homeAddress;
	private String accusation;
	private String oriPeriod;
	private String adjustType;
	private String adjustPeriod;
	private Date dateSAdjust;
	private Date dateEAdjust;
	private String forbidden;
	private Date dateSForbidden;
	private Date dateEForbidden;
	private String opinion;
	private String remark;
	private String dateS_Adjust;
	private String dateE_Adjust;
	private String dateS_Forbidden;
	private String dateE_Forbidden;
	private String birth_Date;

	private String identityCard;
	private String oriPunishment;
	private Date dateSOri;
	private Date dateEOri;
	private Date deathDate;
	private String deathReason;
	private Date dateJudgment;
	private String responseOrg;

	private String dateS_Ori;
	private String dateE_Ori;
	private String death_Date;
	private String date_Judgment;
	private String trialUnit;

	@Transient
	public String getDateS_Ori() {
		return dateS_Ori;
	}

	public void setDateS_Ori(String dateS_Ori) {
		this.dateS_Ori = dateS_Ori;
	}

	@Transient
	public String getDateE_Ori() {
		return dateE_Ori;
	}

	public void setDateE_Ori(String dateE_Ori) {
		this.dateE_Ori = dateE_Ori;
	}

	@Transient
	public String getDeath_Date() {
		return death_Date;
	}

	public void setDeath_Date(String death_Date) {
		this.death_Date = death_Date;
	}

	@Transient
	public String getDate_Judgment() {
		return date_Judgment;
	}

	public void setDate_Judgment(String date_Judgment) {
		this.date_Judgment = date_Judgment;
	}

	@Column(name = "IDENTITY_CARD", length = 100)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "ORI_PUNISHMENT", length = 128)
	public String getOriPunishment() {
		return this.oriPunishment;
	}

	public void setOriPunishment(String oriPunishment) {
		this.oriPunishment = oriPunishment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_ORI", length = 7)
	public Date getDateSOri() {
		return this.dateSOri;
	}

	public void setDateSOri(Date dateSOri) {
		this.dateSOri = dateSOri;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_ORI", length = 7)
	public Date getDateEOri() {
		return this.dateEOri;
	}

	public void setDateEOri(Date dateEOri) {
		this.dateEOri = dateEOri;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEATH_DATE", length = 7)
	public Date getDeathDate() {
		return this.deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Column(name = "DEATH_REASON", length = 30)
	public String getDeathReason() {
		return this.deathReason;
	}

	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Transient
	public String getDateS_Adjust() {
		return dateS_Adjust;
	}

	public void setDateS_Adjust(String dateS_Adjust) {
		this.dateS_Adjust = dateS_Adjust;
	}

	@Transient
	public String getDateE_Adjust() {
		return dateE_Adjust;
	}

	public void setDateE_Adjust(String dateE_Adjust) {
		this.dateE_Adjust = dateE_Adjust;
	}

	@Transient
	public String getDateS_Forbidden() {
		return dateS_Forbidden;
	}

	public void setDateS_Forbidden(String dateS_Forbidden) {
		this.dateS_Forbidden = dateS_Forbidden;
	}

	@Transient
	public String getDateE_Forbidden() {
		return dateE_Forbidden;
	}

	public void setDateE_Forbidden(String dateE_Forbidden) {
		this.dateE_Forbidden = dateE_Forbidden;
	}

	@Transient
	public String getBirth_Date() {
		return birth_Date;
	}

	public void setBirth_Date(String birth_Date) {
		this.birth_Date = birth_Date;
	}

	// Constructors
	/** full constructor */
	public ViewCcDoc34(String id, String fxryId, String name, String sex,
			Date birthdate, String houseAddress, String homeAddress,
			String accusation, String oriPeriod, String adjustType,
			String adjustPeriod, Date dateSAdjust, Date dateEAdjust,
			String forbidden, Date dateSForbidden, Date dateEForbidden,
			String opinion, String remark, String identityCard,
			String oriPunishment, Date dateSOri, Date dateEOri, Date deathDate,
			String deathReason, Date dateJudgment, String responseOrg,
			String trialUnit) {
		this.id = id;
		this.fxryId = fxryId;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.accusation = accusation;
		this.oriPeriod = oriPeriod;
		this.adjustType = adjustType;
		this.adjustPeriod = adjustPeriod;
		this.dateSAdjust = dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.forbidden = forbidden;
		this.dateSForbidden = dateSForbidden;
		this.dateEForbidden = dateEForbidden;
		this.opinion = opinion;
		this.remark = remark;
		this.identityCard = identityCard;
		this.oriPunishment = oriPunishment;
		this.dateSOri = dateSOri;
		this.dateEOri = dateEOri;
		this.deathDate = deathDate;
		this.deathReason = deathReason;
		this.dateJudgment = dateJudgment;
		this.responseOrg = responseOrg;
		this.trialUnit = trialUnit;
	}

	// Property accessors

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
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

	@Column(name = "SEX", length = 128)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "HOUSE_ADDRESS", length = 1500)
	public String getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	@Column(name = "HOME_ADDRESS", length = 1500)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "ORI_PERIOD", length = 150)
	public String getOriPeriod() {
		return this.oriPeriod;
	}

	public void setOriPeriod(String oriPeriod) {
		this.oriPeriod = oriPeriod;
	}

	@Column(name = "ADJUST_TYPE", length = 128)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ADJUST_PERIOD", length = 150)
	public String getAdjustPeriod() {
		return this.adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_ADJUST", length = 7)
	public Date getDateSAdjust() {
		return this.dateSAdjust;
	}

	public void setDateSAdjust(Date dateSAdjust) {
		this.dateSAdjust = dateSAdjust;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_ADJUST", length = 7)
	public Date getDateEAdjust() {
		return this.dateEAdjust;
	}

	public void setDateEAdjust(Date dateEAdjust) {
		this.dateEAdjust = dateEAdjust;
	}

	@Column(name = "FORBIDDEN", length = 300)
	public String getForbidden() {
		return this.forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_FORBIDDEN", length = 7)
	public Date getDateSForbidden() {
		return this.dateSForbidden;
	}

	public void setDateSForbidden(Date dateSForbidden) {
		this.dateSForbidden = dateSForbidden;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_FORBIDDEN", length = 7)
	public Date getDateEForbidden() {
		return this.dateEForbidden;
	}

	public void setDateEForbidden(Date dateEForbidden) {
		this.dateEForbidden = dateEForbidden;
	}

	@Column(name = "OPINION", length = 1500)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "REMARK", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "RESPONSE_ORG", length = 100)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
	}

}