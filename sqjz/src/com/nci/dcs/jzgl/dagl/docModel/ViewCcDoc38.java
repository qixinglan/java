package com.nci.dcs.jzgl.dagl.docModel;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc38 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_38", schema = "SQJZ")
public class ViewCcDoc38 implements java.io.Serializable {

	/** default constructor */
	public ViewCcDoc38() {
	}

	// Fields

	private String id;
	private String name;
	private String sex;
	private Date birthdate;
	private String identityCard;
	private String accusation;
	private String oriPunishment;
	private Date dateSOri;
	private Date dateEOri;
	private String trialUnit;
	private String houseAddress;
	private String homeAddress;
	private String adjustType;
	private Date dateSAdjust;
	private Date dateEAdjust;
	private Date dateJudgment;
	private String writNumber;
	private String writType;
	private Date removeDate;
	
	private String birth_Date;
	private String dateS_Ori;
	private String dateE_Ori;
	private String dateS_Adjust;
	private String dateE_Adjust;
	private String date_Judgment;
	private String remove_Date;

	@Transient
	public String getBirth_Date() {
		return birth_Date;
	}

	public void setBirth_Date(String birth_Date) {
		this.birth_Date = birth_Date;
	}

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
	public String getDate_Judgment() {
		return date_Judgment;
	}

	public void setDate_Judgment(String date_Judgment) {
		this.date_Judgment = date_Judgment;
	}
	@Transient
	public String getRemove_Date() {
		return remove_Date;
	}

	public void setRemove_Date(String remove_Date) {
		this.remove_Date = remove_Date;
	}

	/** full constructor */
	public ViewCcDoc38(String id, String name, String sex, Date birthdate,
			String identityCard, String accusation, String oriPunishment,
			Date dateSOri, Date dateEOri, String trialUnit,
			String houseAddress, String homeAddress, String adjustType,
			Date dateSAdjust, Date dateEAdjust, Date dateJudgment,
			String writNumber, String writType, Date removeDate) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthdate = birthdate;
		this.identityCard = identityCard;
		this.accusation = accusation;
		this.oriPunishment = oriPunishment;
		this.dateSOri = dateSOri;
		this.dateEOri = dateEOri;
		this.trialUnit = trialUnit;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.adjustType = adjustType;
		this.dateSAdjust = dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.dateJudgment = dateJudgment;
		this.writNumber = writNumber;
		this.writType = writType;
		this.removeDate = removeDate;
	}

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

	@Column(name = "IDENTITY_CARD", length = 100)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
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

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
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

	@Column(name = "ADJUST_TYPE", length = 128)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "WRIT_NUMBER", length = 100)
	public String getWritNumber() {
		return this.writNumber;
	}

	public void setWritNumber(String writNumber) {
		this.writNumber = writNumber;
	}

	@Column(name = "WRIT_TYPE", length = 128)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

}