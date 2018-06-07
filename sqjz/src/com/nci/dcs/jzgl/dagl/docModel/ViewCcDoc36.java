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
 * ViewCcDoc36 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_36", schema = "SQJZ")
public class ViewCcDoc36 implements java.io.Serializable {



	// Constructors

	/** default constructor */
	public ViewCcDoc36() {
	}

	private String id;
	private String name;
	private String houseAddress;
	private String homeAddress;
	private String identityCard;
	private String accusation;
	private Date dateJudgment;
	private String trialUnit;
	private String writNumber;
	private String writType;
	private String adjustType;
	private Date removeDate;
	private String date_Judgment;
	private String remove_Date;

	// Constructors

	/** full constructor */
	public ViewCcDoc36(String id, String name, String houseAddress,
			String homeAddress, String identityCard, String accusation,
			Date dateJudgment, String trialUnit, String writNumber,
			String writType, String adjustType, Date removeDate) {
		this.id = id;
		this.name = name;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.identityCard = identityCard;
		this.accusation = accusation;
		this.dateJudgment = dateJudgment;
		this.trialUnit = trialUnit;
		this.writNumber = writNumber;
		this.writType = writType;
		this.adjustType = adjustType;
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

	public void setName(String name) {
		this.name = name;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
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

	@Column(name = "ADJUST_TYPE", length = 128)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
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