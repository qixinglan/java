package com.nci.dcs.jzgl.dagl.docModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc30 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_32", schema = "SQJZ")
public class ViewCcDoc32 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String sex;
	private String nation;
	private Date birthdate;
	private String educationDegree;
	private String houseAddress;
	private String homeAddress;
	private String accusation;
	private String trialUnit;
	private Date dateJudgment;
	private String addpunishment;
	private String province;
	private String city;
	private String judgmentNumber;
	private String wsbh;
	private String oriPunishment;
	private String county;

	// Constructors

	/** default constructor */
	public ViewCcDoc32() {
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

	@Column(name = "NATION", length = 128)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "EDUCATION_DEGREE", length = 128)
	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
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

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "ADDPUNISHMENT", length = 30)
	public String getAddpunishment() {
		return this.addpunishment;
	}

	public void setAddpunishment(String addpunishment) {
		this.addpunishment = addpunishment;
	}

	@Column(name = "PROVINCE", length = 300)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 300)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "JUDGMENT_NUMBER", length = 100)
	public String getJudgmentNumber() {
		return this.judgmentNumber;
	}

	public void setJudgmentNumber(String judgmentNumber) {
		this.judgmentNumber = judgmentNumber;
	}

	@Column(name = "WSBH", length = 0)
	public String getWsbh() {
		return this.wsbh;
	}

	public void setWsbh(String wsbh) {
		this.wsbh = wsbh;
	}

	@Column(name = "ORI_PUNISHMENT", length = 128)
	public String getOriPunishment() {
		return this.oriPunishment;
	}

	public void setOriPunishment(String oriPunishment) {
		this.oriPunishment = oriPunishment;
	}

	@Column(name = "COUNTY", length = 300)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
}