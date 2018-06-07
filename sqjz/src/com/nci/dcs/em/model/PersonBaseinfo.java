package com.nci.dcs.em.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * CcPersonBaseinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_PERSON_BASEINFO", schema = "SQJZ")
public class PersonBaseinfo implements java.io.Serializable {

	// Fields

	private String personId;
	private String personCode;
	private String personState;
	private String origin;
	private String userdName;
	private BigDecimal isadult;
	private String personName;
	private String sex;
	private String nation;
	private String identityCard;
	private Date birthdate;
	private String passport;
	private Integer hrPermit;
	private String hkIdentity;
	private String amIdentity;
	private String tbIdentity;
	private String gatPermit;
	private String health;
	private String healthSpecific;
	private String healthContagion;
	private BigDecimal healthMental;
	private String healthMentalSpecific;
	private String accreditingOrgan;
	private String politicsStatusOriginal;
	private String politicsStatus;
	private String phoneNumber;
	private String natureHome;
	private BigDecimal issameHome;
	private Date reportTime;
	private String houseAddress;
	private String homeAddress;
	private String homeAddressEn;
	private String educationDegree;
	private String maritalState;
	private String workState;
	private String workNuit;
	private String workNuitPhone;

	// private Set<CcAlarmInfo> ccAlarmInfos = new HashSet<CcAlarmInfo>(0);
	// private Set<CcIgnorePerson> ccIgnorePersons = new
	// HashSet<CcIgnorePerson>(0);
	// private Set<CcResumeInfo> ccResumeInfos = new HashSet<CcResumeInfo>(0);
	// private Set<CcPersonDevice> ccPersonDevices = new
	// HashSet<CcPersonDevice>(0);
	// private Set<SysSetting> ccSysSettings = new HashSet<SysSetting>(0);

	// private Set<CcSocialRelations> ccSocialRelationses = new
	// HashSet<CcSocialRelations>(
	// 0);

	// Constructors

	/** default constructor */
	public PersonBaseinfo() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PERSON_ID", unique = true, nullable = false, length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "PERSON_CODE", length = 20)
	public String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	@Column(name = "PERSON_STATE", length = 2)
	public String getPersonState() {
		return this.personState;
	}

	public void setPersonState(String personState) {
		this.personState = personState;
	}

	@Column(name = "ORIGIN", length = 1)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "USERD_NAME", length = 128)
	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	@Column(name = "ISADULT", precision = 38, scale = 0)
	public BigDecimal getIsadult() {
		return this.isadult;
	}

	public void setIsadult(BigDecimal isadult) {
		this.isadult = isadult;
	}

	@Column(name = "PERSON_NAME", length = 64)
	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "NATION", length = 8)
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

	@Column(name = "PASSPORT", length = 32)
	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Column(name = "HR_PERMIT", precision = 7, scale = 0)
	public Integer getHrPermit() {
		return this.hrPermit;
	}

	public void setHrPermit(Integer hrPermit) {
		this.hrPermit = hrPermit;
	}

	@Column(name = "HK_IDENTITY", length = 32)
	public String getHkIdentity() {
		return this.hkIdentity;
	}

	public void setHkIdentity(String hkIdentity) {
		this.hkIdentity = hkIdentity;
	}

	@Column(name = "AM_IDENTITY", length = 32)
	public String getAmIdentity() {
		return this.amIdentity;
	}

	public void setAmIdentity(String amIdentity) {
		this.amIdentity = amIdentity;
	}

	@Column(name = "TB_IDENTITY", length = 32)
	public String getTbIdentity() {
		return this.tbIdentity;
	}

	public void setTbIdentity(String tbIdentity) {
		this.tbIdentity = tbIdentity;
	}

	@Column(name = "GAT_PERMIT", length = 11)
	public String getGatPermit() {
		return this.gatPermit;
	}

	public void setGatPermit(String gatPermit) {
		this.gatPermit = gatPermit;
	}

	@Column(name = "HEALTH", length = 5)
	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Column(name = "HEALTH_SPECIFIC", length = 32)
	public String getHealthSpecific() {
		return this.healthSpecific;
	}

	public void setHealthSpecific(String healthSpecific) {
		this.healthSpecific = healthSpecific;
	}

	@Column(name = "HEALTH_CONTAGION", length = 5)
	public String getHealthContagion() {
		return this.healthContagion;
	}

	public void setHealthContagion(String healthContagion) {
		this.healthContagion = healthContagion;
	}

	@Column(name = "HEALTH_MENTAL", precision = 38, scale = 0)
	public BigDecimal getHealthMental() {
		return this.healthMental;
	}

	public void setHealthMental(BigDecimal healthMental) {
		this.healthMental = healthMental;
	}

	@Column(name = "HEALTH_MENTAL_SPECIFIC", length = 32)
	public String getHealthMentalSpecific() {
		return this.healthMentalSpecific;
	}

	public void setHealthMentalSpecific(String healthMentalSpecific) {
		this.healthMentalSpecific = healthMentalSpecific;
	}

	@Column(name = "ACCREDITING_ORGAN", length = 64)
	public String getAccreditingOrgan() {
		return this.accreditingOrgan;
	}

	public void setAccreditingOrgan(String accreditingOrgan) {
		this.accreditingOrgan = accreditingOrgan;
	}

	@Column(name = "POLITICS_STATUS_ORIGINAL", length = 5)
	public String getPoliticsStatusOriginal() {
		return this.politicsStatusOriginal;
	}

	public void setPoliticsStatusOriginal(String politicsStatusOriginal) {
		this.politicsStatusOriginal = politicsStatusOriginal;
	}

	@Column(name = "POLITICS_STATUS", length = 5)
	public String getPoliticsStatus() {
		return this.politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	@Column(name = "PHONE_NUMBER", length = 32)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "NATURE_HOME", length = 5)
	public String getNatureHome() {
		return this.natureHome;
	}

	public void setNatureHome(String natureHome) {
		this.natureHome = natureHome;
	}

	@Column(name = "ISSAME_HOME", precision = 38, scale = 0)
	public BigDecimal getIssameHome() {
		return this.issameHome;
	}

	public void setIssameHome(BigDecimal issameHome) {
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

	@Column(name = "HOUSE_ADDRESS", length = 32)
	public String getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	@Column(name = "HOME_ADDRESS", length = 32)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "HOME_ADDRESS_EN", length = 256)
	public String getHomeAddressEn() {
		return this.homeAddressEn;
	}

	public void setHomeAddressEn(String homeAddressEn) {
		this.homeAddressEn = homeAddressEn;
	}

	@Column(name = "EDUCATION_DEGREE", length = 5)
	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	@Column(name = "MARITAL_STATE", length = 5)
	public String getMaritalState() {
		return this.maritalState;
	}

	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
	}

	@Column(name = "WORK_STATE", length = 5)
	public String getWorkState() {
		return this.workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Column(name = "WORK_NUIT", length = 128)
	public String getWorkNuit() {
		return this.workNuit;
	}

	public void setWorkNuit(String workNuit) {
		this.workNuit = workNuit;
	}

	@Column(name = "WORK_NUIT_PHONE", length = 32)
	public String getWorkNuitPhone() {
		return this.workNuitPhone;
	}

	public void setWorkNuitPhone(String workNuitPhone) {
		this.workNuitPhone = workNuitPhone;
	}

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<CcAlarmInfo> getCcAlarmInfos() {
	// return this.ccAlarmInfos;
	// }
	//
	// public void setCcAlarmInfos(Set<CcAlarmInfo> ccAlarmInfos) {
	// this.ccAlarmInfos = ccAlarmInfos;
	// }
	//
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<CcIgnorePerson> getCcIgnorePersons() {
	// return this.ccIgnorePersons;
	// }
	//
	// public void setCcIgnorePersons(Set<CcIgnorePerson> ccIgnorePersons) {
	// this.ccIgnorePersons = ccIgnorePersons;
	// }
	//
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<CcResumeInfo> getCcResumeInfos() {
	// return this.ccResumeInfos;
	// }
	//
	// public void setCcResumeInfos(Set<CcResumeInfo> ccResumeInfos) {
	// this.ccResumeInfos = ccResumeInfos;
	// }
	//
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<CcPersonDevice> getCcPersonDevices() {
	// return this.ccPersonDevices;
	// }
	//
	// public void setCcPersonDevices(Set<CcPersonDevice> ccPersonDevices) {
	// this.ccPersonDevices = ccPersonDevices;
	// }
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<CcSocialRelations> getCcSocialRelationses() {
	// return this.ccSocialRelationses;
	// }
	//
	// public void setCcSocialRelationses(
	// Set<CcSocialRelations> ccSocialRelationses) {
	// this.ccSocialRelationses = ccSocialRelationses;
	// }
	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	// "ccPersonBaseinfo")
	// public Set<SysSetting> getCcSysSettings() {
	// return this.ccSysSettings;
	// }
	//
	// public void setCcSysSettings(Set<SysSetting> ccSysSettings) {
	// this.ccSysSettings = ccSysSettings;
	// }

}