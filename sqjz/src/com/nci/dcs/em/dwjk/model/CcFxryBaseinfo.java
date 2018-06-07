package com.nci.dcs.em.dwjk.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nci.dcs.jzgl.dagl.model.Address;

/**
 * CcFxryBaseinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_BASEINFO", schema = "SQJZ")
public class CcFxryBaseinfo implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String state;
	private String origin;
	private String userdName;
	private String isadult;
	private String name;
	private String sex;
	private String nation;
	private String identityCard;
	private Date birthdate;
	private String passport;
	private String hrPermit;
	private String hkIdentity;
	private String amIdentity;
	private String tbIdentity;
	private String gatPermit;
	private String health;
	private String healthSpecific;
	private String healthContagion;
	private String healthMental;
	private String healthMentalSpecific;
	private String accreditingOrgan;
	private String politicsStatusOriginal;
	private String politicsStatus;
	private String phoneNumber;
	private String natureHome;
	private String issameHome;
	private Date reportTime;
	private Address houseAddress;
	private Address homeAddress;
	private String educationDegree;
	private String maritalState;
	private String workState;
	private String workNuit;
	private String workNuitPhone;
	private String recordOrgId;
	private Date createTime;
	private String fingerprintno;
	private String isTgry;
	@Column(name = "ISTGRY", length =1)
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	@Column(name = "FINGERPRINTNO", length = 32)
	public String getFingerprintno() {
		return fingerprintno;
	}

	public void setFingerprintno(String fingerprintno) {
		this.fingerprintno = fingerprintno;
	}

	/** default constructor */
	public CcFxryBaseinfo() {
	}

	/** minimal constructor */
	public CcFxryBaseinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcFxryBaseinfo(String id, String code, String state, String origin,
			String userdName, String isadult, String name, String sex,
			String nation, String identityCard, Date birthdate,
			String passport, String hrPermit, String hkIdentity,
			String amIdentity, String tbIdentity, String gatPermit,
			String health, String healthSpecific, String healthContagion,
			String healthMental, String healthMentalSpecific,
			String accreditingOrgan, String politicsStatusOriginal,
			String politicsStatus, String phoneNumber, String natureHome,
			String issameHome, Date reportTime, Address houseAddress,
			Address homeAddress, String educationDegree, String maritalState,
			String workState, String workNuit, String workNuitPhone,
			String recordOrgId, Date createTime) {
		this.id = id;
		this.code = code;
		this.state = state;
		this.origin = origin;
		this.userdName = userdName;
		this.isadult = isadult;
		this.name = name;
		this.sex = sex;
		this.nation = nation;
		this.identityCard = identityCard;
		this.birthdate = birthdate;
		this.passport = passport;
		this.hrPermit = hrPermit;
		this.hkIdentity = hkIdentity;
		this.amIdentity = amIdentity;
		this.tbIdentity = tbIdentity;
		this.gatPermit = gatPermit;
		this.health = health;
		this.healthSpecific = healthSpecific;
		this.healthContagion = healthContagion;
		this.healthMental = healthMental;
		this.healthMentalSpecific = healthMentalSpecific;
		this.accreditingOrgan = accreditingOrgan;
		this.politicsStatusOriginal = politicsStatusOriginal;
		this.politicsStatus = politicsStatus;
		this.phoneNumber = phoneNumber;
		this.natureHome = natureHome;
		this.issameHome = issameHome;
		this.reportTime = reportTime;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.educationDegree = educationDegree;
		this.maritalState = maritalState;
		this.workState = workState;
		this.workNuit = workNuit;
		this.workNuitPhone = workNuitPhone;
		this.recordOrgId = recordOrgId;
		this.createTime = createTime;
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

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "STATE", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ORIGIN", length = 1)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "USERD_NAME", length = 60)
	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	@Column(name = "ISADULT", length = 30)
	public String getIsadult() {
		return this.isadult;
	}

	public void setIsadult(String isadult) {
		this.isadult = isadult;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "IDENTITY_CARD", length = 18)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "PASSPORT", length = 60)
	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Column(name = "HR_PERMIT", length = 60)
	public String getHrPermit() {
		return this.hrPermit;
	}

	public void setHrPermit(String hrPermit) {
		this.hrPermit = hrPermit;
	}

	@Column(name = "HK_IDENTITY", length = 60)
	public String getHkIdentity() {
		return this.hkIdentity;
	}

	public void setHkIdentity(String hkIdentity) {
		this.hkIdentity = hkIdentity;
	}

	@Column(name = "AM_IDENTITY", length = 60)
	public String getAmIdentity() {
		return this.amIdentity;
	}

	public void setAmIdentity(String amIdentity) {
		this.amIdentity = amIdentity;
	}

	@Column(name = "TB_IDENTITY", length = 60)
	public String getTbIdentity() {
		return this.tbIdentity;
	}

	public void setTbIdentity(String tbIdentity) {
		this.tbIdentity = tbIdentity;
	}

	@Column(name = "GAT_PERMIT", length = 60)
	public String getGatPermit() {
		return this.gatPermit;
	}

	public void setGatPermit(String gatPermit) {
		this.gatPermit = gatPermit;
	}

	@Column(name = "HEALTH", length = 30)
	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Column(name = "HEALTH_SPECIFIC", length = 300)
	public String getHealthSpecific() {
		return this.healthSpecific;
	}

	public void setHealthSpecific(String healthSpecific) {
		this.healthSpecific = healthSpecific;
	}

	@Column(name = "HEALTH_CONTAGION", length = 30)
	public String getHealthContagion() {
		return this.healthContagion;
	}

	public void setHealthContagion(String healthContagion) {
		this.healthContagion = healthContagion;
	}

	@Column(name = "HEALTH_MENTAL", length = 30)
	public String getHealthMental() {
		return this.healthMental;
	}

	public void setHealthMental(String healthMental) {
		this.healthMental = healthMental;
	}

	@Column(name = "HEALTH_MENTAL_SPECIFIC", length = 300)
	public String getHealthMentalSpecific() {
		return this.healthMentalSpecific;
	}

	public void setHealthMentalSpecific(String healthMentalSpecific) {
		this.healthMentalSpecific = healthMentalSpecific;
	}

	@Column(name = "ACCREDITING_ORGAN", length = 300)
	public String getAccreditingOrgan() {
		return this.accreditingOrgan;
	}

	public void setAccreditingOrgan(String accreditingOrgan) {
		this.accreditingOrgan = accreditingOrgan;
	}

	@Column(name = "POLITICS_STATUS_ORIGINAL", length = 30)
	public String getPoliticsStatusOriginal() {
		return this.politicsStatusOriginal;
	}

	public void setPoliticsStatusOriginal(String politicsStatusOriginal) {
		this.politicsStatusOriginal = politicsStatusOriginal;
	}

	@Column(name = "POLITICS_STATUS", length = 30)
	public String getPoliticsStatus() {
		return this.politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	@Column(name = "PHONE_NUMBER", length = 30)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "NATURE_HOME", length = 30)
	public String getNatureHome() {
		return this.natureHome;
	}

	public void setNatureHome(String natureHome) {
		this.natureHome = natureHome;
	}

	@Column(name = "ISSAME_HOME", length = 30)
	public String getIssameHome() {
		return this.issameHome;
	}

	public void setIssameHome(String issameHome) {
		this.issameHome = issameHome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME", length = 7)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}


	@OneToOne
	//@Column(name = "HOUSE_ADDRESS", length = 32)
	@JoinColumn(name="HOUSE_ADDRESS", insertable=true,updatable=true)
	public Address getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(Address houseAddress) {
		this.houseAddress = houseAddress;
	}

	@OneToOne
	//@Column(name = "HOME_ADDRESS", length = 32)
	@JoinColumn(name="HOME_ADDRESS", insertable=true,updatable=true)
	public Address getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "EDUCATION_DEGREE", length = 30)
	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	@Column(name = "MARITAL_STATE", length = 30)
	public String getMaritalState() {
		return this.maritalState;
	}

	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
	}

	@Column(name = "WORK_STATE", length = 30)
	public String getWorkState() {
		return this.workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Column(name = "WORK_NUIT", length = 300)
	public String getWorkNuit() {
		return this.workNuit;
	}

	public void setWorkNuit(String workNuit) {
		this.workNuit = workNuit;
	}

	@Column(name = "WORK_NUIT_PHONE", length = 30)
	public String getWorkNuitPhone() {
		return this.workNuitPhone;
	}

	public void setWorkNuitPhone(String workNuitPhone) {
		this.workNuitPhone = workNuitPhone;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}