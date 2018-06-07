package com.nci.dcs.em.zhcx.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * VIEW_CC_FXRY_ALLINFO_UNALARM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_ALLINFO_UNALARM", schema = "SQJZ")
public class ViewCcFxryAllinfoUnAlarm implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String state;
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
	private String iscapital;
	private Date reportTime;
	private String houseAddress;
	private String homeAddress;
	private String educationDegree;
	private String maritalState;
	private String workState;
	private String workNuit;
	private String workNuitPhone;
	private String responseOrg;
	private String supOrgId;
	private Double gisX;
	private Double gisY;
	private String deviceCode;
	private String psychosis;
	private String isdeviceCode;
	private String iscontagion;
	private String adjustType;
	private String adjustPeriod;
	private Date dateSAdjust;
	private Date dateEAdjust;
	private String crimeType;
	private String accusation;
	private String oriPunishment;
	private Date dateSOri;
	private Date dateEOri;
	private String oriPeriod;
	private String imprisonmentPeriod;
	private String addpunishment;
	private String nonpoliticalPeriod;
	private Date dateSNonpolitical;
	private Date dateENonpolitical;
	private String receiveUnit;
	private String receivePerson;
	private Date dateTransfer;
	private String sisType;
	private String sansType;
	private String isRecidivism;
	private String isalone;
	private String forbidden;
	private Date dateSForbidden;
	private Date dateEForbidden;
	private Date dateReceive;
	private String receiveType;
	private String reportInfo;
	private String groupInfo;
	private String eremark;
	private String slfzType;
	private String isforbidden;
	private String isgroupInfo;
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
	private String removeReason;
	private Date removeDate;
	private String jailType;
	private Date jailDate;
	private String jailReason;
	private Date deathDate;
	private String deathReason;
	private String deathReasonDetail;
	private String performance;
	private String manner;
	private String istrained;
	private String hascertificate;
	private String speciality;
	private String issanwu;
	private String risk;
	private String familyContact;
	private String remark;
	private String isreward;
	private String ispunish;
	private String isillegal;
	private String iscrime;
	private String hasoutmanage;
	private String isoutmanage;
	
	private String istgry;
	//private String ydDeviceNumber;
	private Date wearTime;
	private Date releaseTime;
//	private String alarmType;
//	private Date alarmTime;
	private String opinion;
	
	// Constructors

	/** default constructor */
	public ViewCcFxryAllinfoUnAlarm() {
	}


	/** full constructor */
	public ViewCcFxryAllinfoUnAlarm(String id, String code, String state,
			String userdName, String isadult, String name, String sex,
			String nation, String identityCard, Date birthdate,
			String passport, String hrPermit, String hkIdentity,
			String amIdentity, String tbIdentity, String gatPermit,
			String health, String healthSpecific, String healthContagion,
			String healthMental, String healthMentalSpecific,
			String accreditingOrgan, String politicsStatusOriginal,
			String politicsStatus, String phoneNumber, String natureHome,
			String issameHome, String iscapital, Date reportTime,
			String houseAddress, String homeAddress, String educationDegree,
			String maritalState, String workState, String workNuit,
			String workNuitPhone, String responseOrg, String supOrgId,
			Double gisX, Double gisY, String deviceCode, String psychosis,
			String isdeviceCode, String iscontagion, String adjustType,
			String adjustPeriod, Date dateSAdjust, Date dateEAdjust,
			String crimeType, String accusation, String oriPunishment,
			Date dateSOri, Date dateEOri, String oriPeriod,
			String imprisonmentPeriod, String addpunishment,
			String nonpoliticalPeriod, Date dateSNonpolitical,
			Date dateENonpolitical, String receiveUnit, String receivePerson,
			Date dateTransfer, String sisType, String sansType,
			String isRecidivism, String isalone, String forbidden,
			Date dateSForbidden, Date dateEForbidden, Date dateReceive,
			String receiveType, String reportInfo, String groupInfo,
			String eremark, String slfzType, String isforbidden,
			String isgroupInfo, String investigateUnit, Date dateDetention,
			Date dateRecord, String permitArrestUnit, Date dateArrest,
			Date dateEndInvestigate, String publicProsecution,
			String indictmentNumber, Date dateIndictment, String trialUnit,
			String judgmentNumber, Date dateJudgment, String informNumber,
			Date dateInform, String orgdetentionAddress, Date dateExecute,
			String decideUnit, Date writEffectiveDate, String writType,
			String writNumber, String majorCrime, String administrativePenalty,
			String criminalPunshment, String removeReason, Date removeDate,
			String jailType, Date jailDate, String jailReason, Date deathDate,
			String deathReason, String deathReasonDetail, String performance,
			String manner, String istrained, String hascertificate,
			String speciality, String issanwu, String risk,
			String familyContact, String remark, String isreward,
			String ispunish, String isillegal, String iscrime,
			String hasoutmanage, String isoutmanage,String istgry,
			Date wearTime,Date releaseTime,String opinion) {
		this.id = id;
		this.code = code;
		this.state = state;
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
		this.iscapital = iscapital;
		this.reportTime = reportTime;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.educationDegree = educationDegree;
		this.maritalState = maritalState;
		this.workState = workState;
		this.workNuit = workNuit;
		this.workNuitPhone = workNuitPhone;
		this.responseOrg = responseOrg;
		this.supOrgId = supOrgId;
		this.gisX = gisX;
		this.gisY = gisY;
		this.deviceCode = deviceCode;
		this.psychosis = psychosis;
		this.isdeviceCode = isdeviceCode;
		this.iscontagion = iscontagion;
		this.adjustType = adjustType;
		this.adjustPeriod = adjustPeriod;
		this.dateSAdjust = dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.crimeType = crimeType;
		this.accusation = accusation;
		this.oriPunishment = oriPunishment;
		this.dateSOri = dateSOri;
		this.dateEOri = dateEOri;
		this.oriPeriod = oriPeriod;
		this.imprisonmentPeriod = imprisonmentPeriod;
		this.addpunishment = addpunishment;
		this.nonpoliticalPeriod = nonpoliticalPeriod;
		this.dateSNonpolitical = dateSNonpolitical;
		this.dateENonpolitical = dateENonpolitical;
		this.receiveUnit = receiveUnit;
		this.receivePerson = receivePerson;
		this.dateTransfer = dateTransfer;
		this.sisType = sisType;
		this.sansType = sansType;
		this.isRecidivism = isRecidivism;
		this.isalone = isalone;
		this.forbidden = forbidden;
		this.dateSForbidden = dateSForbidden;
		this.dateEForbidden = dateEForbidden;
		this.dateReceive = dateReceive;
		this.receiveType = receiveType;
		this.reportInfo = reportInfo;
		this.groupInfo = groupInfo;
		this.eremark = eremark;
		this.slfzType = slfzType;
		this.isforbidden = isforbidden;
		this.isgroupInfo = isgroupInfo;
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
		this.isreward = isreward;
		this.ispunish = ispunish;
		this.isillegal = isillegal;
		this.iscrime = iscrime;
		this.hasoutmanage = hasoutmanage;
		this.isoutmanage = isoutmanage;
		this.istgry = istgry;
		this.wearTime = wearTime;
		this.releaseTime = releaseTime;
//		this.alarmTime = alarmTime;
//		this.alarmType = alarmType;
		this.opinion = opinion;
	}

	// Property accessors
	@Id
	@Column(name = "ID", nullable = false, length = 32)
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

	@Column(name = "IDENTITY_CARD", length = 100)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
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

	@Column(name = "PHONE_NUMBER", length = 100)
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

	@Column(name = "ISCAPITAL", length = 1)
	public String getIscapital() {
		return this.iscapital;
	}

	public void setIscapital(String iscapital) {
		this.iscapital = iscapital;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
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

	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return this.supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "GIS_X", precision = 15, scale = 4)
	public Double getGisX() {
		return this.gisX;
	}

	public void setGisX(Double gisX) {
		this.gisX = gisX;
	}

	@Column(name = "GIS_Y", precision = 15, scale = 4)
	public Double getGisY() {
		return this.gisY;
	}

	public void setGisY(Double gisY) {
		this.gisY = gisY;
	}

	@Column(name = "DEVICE_CODE", length = 32)
	public String getDeviceCode() {
		return this.deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Column(name = "PSYCHOSIS", length = 30)
	public String getPsychosis() {
		return this.psychosis;
	}

	public void setPsychosis(String psychosis) {
		this.psychosis = psychosis;
	}

	@Column(name = "ISDEVICE_CODE", length = 40)
	public String getIsdeviceCode() {
		return this.isdeviceCode;
	}

	public void setIsdeviceCode(String isdeviceCode) {
		this.isdeviceCode = isdeviceCode;
	}

	@Column(name = "ISCONTAGION", length = 40)
	public String getIscontagion() {
		return this.iscontagion;
	}

	public void setIscontagion(String iscontagion) {
		this.iscontagion = iscontagion;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
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
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateSAdjust() {
		return this.dateSAdjust;
	}

	public void setDateSAdjust(Date dateSAdjust) {
		this.dateSAdjust = dateSAdjust;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_ADJUST", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateEAdjust() {
		return this.dateEAdjust;
	}

	public void setDateEAdjust(Date dateEAdjust) {
		this.dateEAdjust = dateEAdjust;
	}

	@Column(name = "CRIME_TYPE", length = 30)
	public String getCrimeType() {
		return this.crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "ORI_PUNISHMENT", length = 30)
	public String getOriPunishment() {
		return this.oriPunishment;
	}

	public void setOriPunishment(String oriPunishment) {
		this.oriPunishment = oriPunishment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_ORI", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateSOri() {
		return this.dateSOri;
	}

	public void setDateSOri(Date dateSOri) {
		this.dateSOri = dateSOri;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_ORI", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateEOri() {
		return this.dateEOri;
	}

	public void setDateEOri(Date dateEOri) {
		this.dateEOri = dateEOri;
	}

	@Column(name = "ORI_PERIOD", length = 150)
	public String getOriPeriod() {
		return this.oriPeriod;
	}

	public void setOriPeriod(String oriPeriod) {
		this.oriPeriod = oriPeriod;
	}

	@Column(name = "IMPRISONMENT_PERIOD", length = 30)
	public String getImprisonmentPeriod() {
		return this.imprisonmentPeriod;
	}

	public void setImprisonmentPeriod(String imprisonmentPeriod) {
		this.imprisonmentPeriod = imprisonmentPeriod;
	}

	@Column(name = "ADDPUNISHMENT", length = 30)
	public String getAddpunishment() {
		return this.addpunishment;
	}

	public void setAddpunishment(String addpunishment) {
		this.addpunishment = addpunishment;
	}

	@Column(name = "NONPOLITICAL_PERIOD", length = 150)
	public String getNonpoliticalPeriod() {
		return this.nonpoliticalPeriod;
	}

	public void setNonpoliticalPeriod(String nonpoliticalPeriod) {
		this.nonpoliticalPeriod = nonpoliticalPeriod;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_NONPOLITICAL", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateSNonpolitical() {
		return this.dateSNonpolitical;
	}

	public void setDateSNonpolitical(Date dateSNonpolitical) {
		this.dateSNonpolitical = dateSNonpolitical;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_NONPOLITICAL", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateENonpolitical() {
		return this.dateENonpolitical;
	}

	public void setDateENonpolitical(Date dateENonpolitical) {
		this.dateENonpolitical = dateENonpolitical;
	}

	@Column(name = "RECEIVE_UNIT", length = 150)
	public String getReceiveUnit() {
		return this.receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	@Column(name = "RECEIVE_PERSON", length = 60)
	public String getReceivePerson() {
		return this.receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_TRANSFER", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateTransfer() {
		return this.dateTransfer;
	}

	public void setDateTransfer(Date dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	@Column(name = "SIS_TYPE", length = 150)
	public String getSisType() {
		return this.sisType;
	}

	public void setSisType(String sisType) {
		this.sisType = sisType;
	}

	@Column(name = "SANS_TYPE", length = 150)
	public String getSansType() {
		return this.sansType;
	}

	public void setSansType(String sansType) {
		this.sansType = sansType;
	}

	@Column(name = "IS_RECIDIVISM", length = 30)
	public String getIsRecidivism() {
		return this.isRecidivism;
	}

	public void setIsRecidivism(String isRecidivism) {
		this.isRecidivism = isRecidivism;
	}

	@Column(name = "ISALONE", length = 30)
	public String getIsalone() {
		return this.isalone;
	}

	public void setIsalone(String isalone) {
		this.isalone = isalone;
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
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateSForbidden() {
		return this.dateSForbidden;
	}

	public void setDateSForbidden(Date dateSForbidden) {
		this.dateSForbidden = dateSForbidden;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_FORBIDDEN", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateEForbidden() {
		return this.dateEForbidden;
	}

	public void setDateEForbidden(Date dateEForbidden) {
		this.dateEForbidden = dateEForbidden;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RECEIVE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateReceive() {
		return this.dateReceive;
	}

	public void setDateReceive(Date dateReceive) {
		this.dateReceive = dateReceive;
	}

	@Column(name = "RECEIVE_TYPE", length = 30)
	public String getReceiveType() {
		return this.receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	@Column(name = "REPORT_INFO", length = 30)
	public String getReportInfo() {
		return this.reportInfo;
	}

	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}

	@Column(name = "GROUP_INFO", length = 150)
	public String getGroupInfo() {
		return this.groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	@Column(name = "EREMARK", length = 1500)
	public String getEremark() {
		return this.eremark;
	}

	public void setEremark(String eremark) {
		this.eremark = eremark;
	}

	@Column(name = "SLFZ_TYPE", length = 150)
	public String getSlfzType() {
		return this.slfzType;
	}

	public void setSlfzType(String slfzType) {
		this.slfzType = slfzType;
	}

	@Column(name = "ISFORBIDDEN", length = 30)
	public String getIsforbidden() {
		return this.isforbidden;
	}

	public void setIsforbidden(String isforbidden) {
		this.isforbidden = isforbidden;
	}

	@Column(name = "ISGROUP_INFO", length = 40)
	public String getIsgroupInfo() {
		return this.isgroupInfo;
	}

	public void setIsgroupInfo(String isgroupInfo) {
		this.isgroupInfo = isgroupInfo;
	}

	@Column(name = "INVESTIGATE_UNIT", length = 150)
	public String getInvestigateUnit() {
		return this.investigateUnit;
	}

	public void setInvestigateUnit(String investigateUnit) {
		this.investigateUnit = investigateUnit;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DETENTION", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateDetention() {
		return this.dateDetention;
	}

	public void setDateDetention(Date dateDetention) {
		this.dateDetention = dateDetention;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RECORD", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	@Column(name = "PERMIT_ARREST_UNIT", length = 150)
	public String getPermitArrestUnit() {
		return this.permitArrestUnit;
	}

	public void setPermitArrestUnit(String permitArrestUnit) {
		this.permitArrestUnit = permitArrestUnit;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ARREST", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateArrest() {
		return this.dateArrest;
	}

	public void setDateArrest(Date dateArrest) {
		this.dateArrest = dateArrest;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_END_INVESTIGATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateEndInvestigate() {
		return this.dateEndInvestigate;
	}

	public void setDateEndInvestigate(Date dateEndInvestigate) {
		this.dateEndInvestigate = dateEndInvestigate;
	}

	@Column(name = "PUBLIC_PROSECUTION", length = 150)
	public String getPublicProsecution() {
		return this.publicProsecution;
	}

	public void setPublicProsecution(String publicProsecution) {
		this.publicProsecution = publicProsecution;
	}

	@Column(name = "INDICTMENT_NUMBER", length = 100)
	public String getIndictmentNumber() {
		return this.indictmentNumber;
	}

	public void setIndictmentNumber(String indictmentNumber) {
		this.indictmentNumber = indictmentNumber;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INDICTMENT", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateIndictment() {
		return this.dateIndictment;
	}

	public void setDateIndictment(Date dateIndictment) {
		this.dateIndictment = dateIndictment;
	}

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
	}

	@Column(name = "JUDGMENT_NUMBER", length = 100)
	public String getJudgmentNumber() {
		return this.judgmentNumber;
	}

	public void setJudgmentNumber(String judgmentNumber) {
		this.judgmentNumber = judgmentNumber;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "INFORM_NUMBER", length = 200)
	public String getInformNumber() {
		return this.informNumber;
	}

	public void setInformNumber(String informNumber) {
		this.informNumber = informNumber;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INFORM", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateInform() {
		return this.dateInform;
	}

	public void setDateInform(Date dateInform) {
		this.dateInform = dateInform;
	}

	@Column(name = "ORGDETENTION_ADDRESS", length = 150)
	public String getOrgdetentionAddress() {
		return this.orgdetentionAddress;
	}

	public void setOrgdetentionAddress(String orgdetentionAddress) {
		this.orgdetentionAddress = orgdetentionAddress;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_EXECUTE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateExecute() {
		return this.dateExecute;
	}

	public void setDateExecute(Date dateExecute) {
		this.dateExecute = dateExecute;
	}

	@Column(name = "DECIDE_UNIT", length = 150)
	public String getDecideUnit() {
		return this.decideUnit;
	}

	public void setDecideUnit(String decideUnit) {
		this.decideUnit = decideUnit;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "WRIT_EFFECTIVE_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getWritEffectiveDate() {
		return this.writEffectiveDate;
	}

	public void setWritEffectiveDate(Date writEffectiveDate) {
		this.writEffectiveDate = writEffectiveDate;
	}

	@Column(name = "WRIT_TYPE", length = 30)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Column(name = "WRIT_NUMBER", length = 100)
	public String getWritNumber() {
		return this.writNumber;
	}

	public void setWritNumber(String writNumber) {
		this.writNumber = writNumber;
	}

	@Column(name = "MAJOR_CRIME", length = 600)
	public String getMajorCrime() {
		return this.majorCrime;
	}

	public void setMajorCrime(String majorCrime) {
		this.majorCrime = majorCrime;
	}

	@Column(name = "ADMINISTRATIVE_PENALTY", length = 600)
	public String getAdministrativePenalty() {
		return this.administrativePenalty;
	}

	public void setAdministrativePenalty(String administrativePenalty) {
		this.administrativePenalty = administrativePenalty;
	}

	@Column(name = "CRIMINAL_PUNSHMENT", length = 600)
	public String getCriminalPunshment() {
		return this.criminalPunshment;
	}

	public void setCriminalPunshment(String criminalPunshment) {
		this.criminalPunshment = criminalPunshment;
	}

	@Column(name = "REMOVE_REASON", length = 30)
	public String getRemoveReason() {
		return this.removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	@Column(name = "JAIL_TYPE", length = 30)
	public String getJailType() {
		return this.jailType;
	}

	public void setJailType(String jailType) {
		this.jailType = jailType;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "JAIL_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getJailDate() {
		return this.jailDate;
	}

	public void setJailDate(Date jailDate) {
		this.jailDate = jailDate;
	}

	@Column(name = "JAIL_REASON", length = 300)
	public String getJailReason() {
		return this.jailReason;
	}

	public void setJailReason(String jailReason) {
		this.jailReason = jailReason;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "DEATH_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
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

	@Column(name = "DEATH_REASON_DETAIL", length = 150)
	public String getDeathReasonDetail() {
		return this.deathReasonDetail;
	}

	public void setDeathReasonDetail(String deathReasonDetail) {
		this.deathReasonDetail = deathReasonDetail;
	}

	@Column(name = "PERFORMANCE", length = 30)
	public String getPerformance() {
		return this.performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Column(name = "MANNER", length = 30)
	public String getManner() {
		return this.manner;
	}

	public void setManner(String manner) {
		this.manner = manner;
	}

	@Column(name = "ISTRAINED", length = 30)
	public String getIstrained() {
		return this.istrained;
	}

	public void setIstrained(String istrained) {
		this.istrained = istrained;
	}

	@Column(name = "HASCERTIFICATE", length = 30)
	public String getHascertificate() {
		return this.hascertificate;
	}

	public void setHascertificate(String hascertificate) {
		this.hascertificate = hascertificate;
	}

	@Column(name = "SPECIALITY", length = 300)
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "ISSANWU", length = 30)
	public String getIssanwu() {
		return this.issanwu;
	}

	public void setIssanwu(String issanwu) {
		this.issanwu = issanwu;
	}

	@Column(name = "RISK", length = 30)
	public String getRisk() {
		return this.risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	@Column(name = "FAMILY_CONTACT", length = 30)
	public String getFamilyContact() {
		return this.familyContact;
	}

	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}

	@Column(name = "REMARK", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ISREWARD", length = 40)
	public String getIsreward() {
		return this.isreward;
	}

	public void setIsreward(String isreward) {
		this.isreward = isreward;
	}

	@Column(name = "ISPUNISH", length = 40)
	public String getIspunish() {
		return this.ispunish;
	}

	public void setIspunish(String ispunish) {
		this.ispunish = ispunish;
	}

	@Column(name = "ISILLEGAL", length = 40)
	public String getIsillegal() {
		return this.isillegal;
	}

	public void setIsillegal(String isillegal) {
		this.isillegal = isillegal;
	}

	@Column(name = "ISCRIME", length = 40)
	public String getIscrime() {
		return this.iscrime;
	}

	public void setIscrime(String iscrime) {
		this.iscrime = iscrime;
	}
	
	@Column(name = "ISTGRY", length = 1)
	public String getIstgry() {
		return this.istgry;
	}

	public void setIstgry(String istgry) {
		this.istgry = istgry;
	}

	@Column(name = "HASOUTMANAGE", length = 40)
	public String getHasoutmanage() {
		return this.hasoutmanage;
	}

	public void setHasoutmanage(String hasoutmanage) {
		this.hasoutmanage = hasoutmanage;
	}

	@Column(name = "ISOUTMANAGE", length = 1)
	public String getIsoutmanage() {
		return this.isoutmanage;
	}

	public void setIsoutmanage(String isoutmanage) {
		this.isoutmanage = isoutmanage;
	}
	
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "WEAR_TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getWearTime() {
		return wearTime;
	}

	public void setWearTime(Date wearTime) {
		this.wearTime = wearTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
//	@Column(name = "ALARM_TYPE", length = 1)
//	public String getAlarmType() {
//		return alarmType;
//	}
//
//	public void setAlarmType(String alarmType) {
//		this.alarmType = alarmType;
//	}
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "ALARM_TIME", length = 7)
//	@JSON(format=DateTimeFmtSpec.DATE)
//	public Date getAlarmTime() {
//		return alarmTime;
//	}
//
//	public void setAlarmTime(Date alarmTime) {
//		this.alarmTime = alarmTime;
//	}
	
	
	@Column(name = "OPINION", length = 1500)
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewCcFxryAllinfoUnAlarm))
			return false;
		ViewCcFxryAllinfoUnAlarm castOther = (ViewCcFxryAllinfoUnAlarm) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getCode() == castOther.getCode()) || (this.getCode() != null
						&& castOther.getCode() != null && this.getCode()
						.equals(castOther.getCode())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getUserdName() == castOther.getUserdName()) || (this
						.getUserdName() != null
						&& castOther.getUserdName() != null && this
						.getUserdName().equals(castOther.getUserdName())))
				&& ((this.getIsadult() == castOther.getIsadult()) || (this
						.getIsadult() != null && castOther.getIsadult() != null && this
						.getIsadult().equals(castOther.getIsadult())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getSex() == castOther.getSex()) || (this.getSex() != null
						&& castOther.getSex() != null && this.getSex().equals(
						castOther.getSex())))
				&& ((this.getNation() == castOther.getNation()) || (this
						.getNation() != null && castOther.getNation() != null && this
						.getNation().equals(castOther.getNation())))
				&& ((this.getIdentityCard() == castOther.getIdentityCard()) || (this
						.getIdentityCard() != null
						&& castOther.getIdentityCard() != null && this
						.getIdentityCard().equals(castOther.getIdentityCard())))
				&& ((this.getBirthdate() == castOther.getBirthdate()) || (this
						.getBirthdate() != null
						&& castOther.getBirthdate() != null && this
						.getBirthdate().equals(castOther.getBirthdate())))
				&& ((this.getPassport() == castOther.getPassport()) || (this
						.getPassport() != null
						&& castOther.getPassport() != null && this
						.getPassport().equals(castOther.getPassport())))
				&& ((this.getHrPermit() == castOther.getHrPermit()) || (this
						.getHrPermit() != null
						&& castOther.getHrPermit() != null && this
						.getHrPermit().equals(castOther.getHrPermit())))
				&& ((this.getHkIdentity() == castOther.getHkIdentity()) || (this
						.getHkIdentity() != null
						&& castOther.getHkIdentity() != null && this
						.getHkIdentity().equals(castOther.getHkIdentity())))
				&& ((this.getAmIdentity() == castOther.getAmIdentity()) || (this
						.getAmIdentity() != null
						&& castOther.getAmIdentity() != null && this
						.getAmIdentity().equals(castOther.getAmIdentity())))
				&& ((this.getTbIdentity() == castOther.getTbIdentity()) || (this
						.getTbIdentity() != null
						&& castOther.getTbIdentity() != null && this
						.getTbIdentity().equals(castOther.getTbIdentity())))
				&& ((this.getGatPermit() == castOther.getGatPermit()) || (this
						.getGatPermit() != null
						&& castOther.getGatPermit() != null && this
						.getGatPermit().equals(castOther.getGatPermit())))
				&& ((this.getHealth() == castOther.getHealth()) || (this
						.getHealth() != null && castOther.getHealth() != null && this
						.getHealth().equals(castOther.getHealth())))
				&& ((this.getHealthSpecific() == castOther.getHealthSpecific()) || (this
						.getHealthSpecific() != null
						&& castOther.getHealthSpecific() != null && this
						.getHealthSpecific().equals(
								castOther.getHealthSpecific())))
				&& ((this.getHealthContagion() == castOther
						.getHealthContagion()) || (this.getHealthContagion() != null
						&& castOther.getHealthContagion() != null && this
						.getHealthContagion().equals(
								castOther.getHealthContagion())))
				&& ((this.getHealthMental() == castOther.getHealthMental()) || (this
						.getHealthMental() != null
						&& castOther.getHealthMental() != null && this
						.getHealthMental().equals(castOther.getHealthMental())))
				&& ((this.getHealthMentalSpecific() == castOther
						.getHealthMentalSpecific()) || (this
						.getHealthMentalSpecific() != null
						&& castOther.getHealthMentalSpecific() != null && this
						.getHealthMentalSpecific().equals(
								castOther.getHealthMentalSpecific())))
				&& ((this.getAccreditingOrgan() == castOther
						.getAccreditingOrgan()) || (this.getAccreditingOrgan() != null
						&& castOther.getAccreditingOrgan() != null && this
						.getAccreditingOrgan().equals(
								castOther.getAccreditingOrgan())))
				&& ((this.getPoliticsStatusOriginal() == castOther
						.getPoliticsStatusOriginal()) || (this
						.getPoliticsStatusOriginal() != null
						&& castOther.getPoliticsStatusOriginal() != null && this
						.getPoliticsStatusOriginal().equals(
								castOther.getPoliticsStatusOriginal())))
				&& ((this.getPoliticsStatus() == castOther.getPoliticsStatus()) || (this
						.getPoliticsStatus() != null
						&& castOther.getPoliticsStatus() != null && this
						.getPoliticsStatus().equals(
								castOther.getPoliticsStatus())))
				&& ((this.getPhoneNumber() == castOther.getPhoneNumber()) || (this
						.getPhoneNumber() != null
						&& castOther.getPhoneNumber() != null && this
						.getPhoneNumber().equals(castOther.getPhoneNumber())))
				&& ((this.getNatureHome() == castOther.getNatureHome()) || (this
						.getNatureHome() != null
						&& castOther.getNatureHome() != null && this
						.getNatureHome().equals(castOther.getNatureHome())))
				&& ((this.getIssameHome() == castOther.getIssameHome()) || (this
						.getIssameHome() != null
						&& castOther.getIssameHome() != null && this
						.getIssameHome().equals(castOther.getIssameHome())))
				&& ((this.getIscapital() == castOther.getIscapital()) || (this
						.getIscapital() != null
						&& castOther.getIscapital() != null && this
						.getIscapital().equals(castOther.getIscapital())))
				&& ((this.getReportTime() == castOther.getReportTime()) || (this
						.getReportTime() != null
						&& castOther.getReportTime() != null && this
						.getReportTime().equals(castOther.getReportTime())))
				&& ((this.getHouseAddress() == castOther.getHouseAddress()) || (this
						.getHouseAddress() != null
						&& castOther.getHouseAddress() != null && this
						.getHouseAddress().equals(castOther.getHouseAddress())))
				&& ((this.getHomeAddress() == castOther.getHomeAddress()) || (this
						.getHomeAddress() != null
						&& castOther.getHomeAddress() != null && this
						.getHomeAddress().equals(castOther.getHomeAddress())))
				&& ((this.getEducationDegree() == castOther
						.getEducationDegree()) || (this.getEducationDegree() != null
						&& castOther.getEducationDegree() != null && this
						.getEducationDegree().equals(
								castOther.getEducationDegree())))
				&& ((this.getMaritalState() == castOther.getMaritalState()) || (this
						.getMaritalState() != null
						&& castOther.getMaritalState() != null && this
						.getMaritalState().equals(castOther.getMaritalState())))
				&& ((this.getWorkState() == castOther.getWorkState()) || (this
						.getWorkState() != null
						&& castOther.getWorkState() != null && this
						.getWorkState().equals(castOther.getWorkState())))
				&& ((this.getWorkNuit() == castOther.getWorkNuit()) || (this
						.getWorkNuit() != null
						&& castOther.getWorkNuit() != null && this
						.getWorkNuit().equals(castOther.getWorkNuit())))
				&& ((this.getWorkNuitPhone() == castOther.getWorkNuitPhone()) || (this
						.getWorkNuitPhone() != null
						&& castOther.getWorkNuitPhone() != null && this
						.getWorkNuitPhone()
						.equals(castOther.getWorkNuitPhone())))
				&& ((this.getResponseOrg() == castOther.getResponseOrg()) || (this
						.getResponseOrg() != null
						&& castOther.getResponseOrg() != null && this
						.getResponseOrg().equals(castOther.getResponseOrg())))
				&& ((this.getSupOrgId() == castOther.getSupOrgId()) || (this
						.getSupOrgId() != null
						&& castOther.getSupOrgId() != null && this
						.getSupOrgId().equals(castOther.getSupOrgId())))
				&& ((this.getGisX() == castOther.getGisX()) || (this.getGisX() != null
						&& castOther.getGisX() != null && this.getGisX()
						.equals(castOther.getGisX())))
				&& ((this.getGisY() == castOther.getGisY()) || (this.getGisY() != null
						&& castOther.getGisY() != null && this.getGisY()
						.equals(castOther.getGisY())))
				&& ((this.getDeviceCode() == castOther.getDeviceCode()) || (this
						.getDeviceCode() != null
						&& castOther.getDeviceCode() != null && this
						.getDeviceCode().equals(castOther.getDeviceCode())))
				&& ((this.getPsychosis() == castOther.getPsychosis()) || (this
						.getPsychosis() != null
						&& castOther.getPsychosis() != null && this
						.getPsychosis().equals(castOther.getPsychosis())))
				&& ((this.getIsdeviceCode() == castOther.getIsdeviceCode()) || (this
						.getIsdeviceCode() != null
						&& castOther.getIsdeviceCode() != null && this
						.getIsdeviceCode().equals(castOther.getIsdeviceCode())))
				&& ((this.getIscontagion() == castOther.getIscontagion()) || (this
						.getIscontagion() != null
						&& castOther.getIscontagion() != null && this
						.getIscontagion().equals(castOther.getIscontagion())))
				&& ((this.getAdjustType() == castOther.getAdjustType()) || (this
						.getAdjustType() != null
						&& castOther.getAdjustType() != null && this
						.getAdjustType().equals(castOther.getAdjustType())))
				&& ((this.getAdjustPeriod() == castOther.getAdjustPeriod()) || (this
						.getAdjustPeriod() != null
						&& castOther.getAdjustPeriod() != null && this
						.getAdjustPeriod().equals(castOther.getAdjustPeriod())))
				&& ((this.getDateSAdjust() == castOther.getDateSAdjust()) || (this
						.getDateSAdjust() != null
						&& castOther.getDateSAdjust() != null && this
						.getDateSAdjust().equals(castOther.getDateSAdjust())))
				&& ((this.getDateEAdjust() == castOther.getDateEAdjust()) || (this
						.getDateEAdjust() != null
						&& castOther.getDateEAdjust() != null && this
						.getDateEAdjust().equals(castOther.getDateEAdjust())))
				&& ((this.getCrimeType() == castOther.getCrimeType()) || (this
						.getCrimeType() != null
						&& castOther.getCrimeType() != null && this
						.getCrimeType().equals(castOther.getCrimeType())))
				&& ((this.getAccusation() == castOther.getAccusation()) || (this
						.getAccusation() != null
						&& castOther.getAccusation() != null && this
						.getAccusation().equals(castOther.getAccusation())))
				&& ((this.getOriPunishment() == castOther.getOriPunishment()) || (this
						.getOriPunishment() != null
						&& castOther.getOriPunishment() != null && this
						.getOriPunishment()
						.equals(castOther.getOriPunishment())))
				&& ((this.getDateSOri() == castOther.getDateSOri()) || (this
						.getDateSOri() != null
						&& castOther.getDateSOri() != null && this
						.getDateSOri().equals(castOther.getDateSOri())))
				&& ((this.getDateEOri() == castOther.getDateEOri()) || (this
						.getDateEOri() != null
						&& castOther.getDateEOri() != null && this
						.getDateEOri().equals(castOther.getDateEOri())))
				&& ((this.getOriPeriod() == castOther.getOriPeriod()) || (this
						.getOriPeriod() != null
						&& castOther.getOriPeriod() != null && this
						.getOriPeriod().equals(castOther.getOriPeriod())))
				&& ((this.getImprisonmentPeriod() == castOther
						.getImprisonmentPeriod()) || (this
						.getImprisonmentPeriod() != null
						&& castOther.getImprisonmentPeriod() != null && this
						.getImprisonmentPeriod().equals(
								castOther.getImprisonmentPeriod())))
				&& ((this.getAddpunishment() == castOther.getAddpunishment()) || (this
						.getAddpunishment() != null
						&& castOther.getAddpunishment() != null && this
						.getAddpunishment()
						.equals(castOther.getAddpunishment())))
				&& ((this.getNonpoliticalPeriod() == castOther
						.getNonpoliticalPeriod()) || (this
						.getNonpoliticalPeriod() != null
						&& castOther.getNonpoliticalPeriod() != null && this
						.getNonpoliticalPeriod().equals(
								castOther.getNonpoliticalPeriod())))
				&& ((this.getDateSNonpolitical() == castOther
						.getDateSNonpolitical()) || (this
						.getDateSNonpolitical() != null
						&& castOther.getDateSNonpolitical() != null && this
						.getDateSNonpolitical().equals(
								castOther.getDateSNonpolitical())))
				&& ((this.getDateENonpolitical() == castOther
						.getDateENonpolitical()) || (this
						.getDateENonpolitical() != null
						&& castOther.getDateENonpolitical() != null && this
						.getDateENonpolitical().equals(
								castOther.getDateENonpolitical())))
				&& ((this.getReceiveUnit() == castOther.getReceiveUnit()) || (this
						.getReceiveUnit() != null
						&& castOther.getReceiveUnit() != null && this
						.getReceiveUnit().equals(castOther.getReceiveUnit())))
				&& ((this.getReceivePerson() == castOther.getReceivePerson()) || (this
						.getReceivePerson() != null
						&& castOther.getReceivePerson() != null && this
						.getReceivePerson()
						.equals(castOther.getReceivePerson())))
				&& ((this.getDateTransfer() == castOther.getDateTransfer()) || (this
						.getDateTransfer() != null
						&& castOther.getDateTransfer() != null && this
						.getDateTransfer().equals(castOther.getDateTransfer())))
				&& ((this.getSisType() == castOther.getSisType()) || (this
						.getSisType() != null && castOther.getSisType() != null && this
						.getSisType().equals(castOther.getSisType())))
				&& ((this.getSansType() == castOther.getSansType()) || (this
						.getSansType() != null
						&& castOther.getSansType() != null && this
						.getSansType().equals(castOther.getSansType())))
				&& ((this.getIsRecidivism() == castOther.getIsRecidivism()) || (this
						.getIsRecidivism() != null
						&& castOther.getIsRecidivism() != null && this
						.getIsRecidivism().equals(castOther.getIsRecidivism())))
				&& ((this.getIsalone() == castOther.getIsalone()) || (this
						.getIsalone() != null && castOther.getIsalone() != null && this
						.getIsalone().equals(castOther.getIsalone())))
				&& ((this.getForbidden() == castOther.getForbidden()) || (this
						.getForbidden() != null
						&& castOther.getForbidden() != null && this
						.getForbidden().equals(castOther.getForbidden())))
				&& ((this.getDateSForbidden() == castOther.getDateSForbidden()) || (this
						.getDateSForbidden() != null
						&& castOther.getDateSForbidden() != null && this
						.getDateSForbidden().equals(
								castOther.getDateSForbidden())))
				&& ((this.getDateEForbidden() == castOther.getDateEForbidden()) || (this
						.getDateEForbidden() != null
						&& castOther.getDateEForbidden() != null && this
						.getDateEForbidden().equals(
								castOther.getDateEForbidden())))
				&& ((this.getDateReceive() == castOther.getDateReceive()) || (this
						.getDateReceive() != null
						&& castOther.getDateReceive() != null && this
						.getDateReceive().equals(castOther.getDateReceive())))
				&& ((this.getReceiveType() == castOther.getReceiveType()) || (this
						.getReceiveType() != null
						&& castOther.getReceiveType() != null && this
						.getReceiveType().equals(castOther.getReceiveType())))
				&& ((this.getReportInfo() == castOther.getReportInfo()) || (this
						.getReportInfo() != null
						&& castOther.getReportInfo() != null && this
						.getReportInfo().equals(castOther.getReportInfo())))
				&& ((this.getGroupInfo() == castOther.getGroupInfo()) || (this
						.getGroupInfo() != null
						&& castOther.getGroupInfo() != null && this
						.getGroupInfo().equals(castOther.getGroupInfo())))
				&& ((this.getEremark() == castOther.getEremark()) || (this
						.getEremark() != null && castOther.getEremark() != null && this
						.getEremark().equals(castOther.getEremark())))
				&& ((this.getSlfzType() == castOther.getSlfzType()) || (this
						.getSlfzType() != null
						&& castOther.getSlfzType() != null && this
						.getSlfzType().equals(castOther.getSlfzType())))
				&& ((this.getIsforbidden() == castOther.getIsforbidden()) || (this
						.getIsforbidden() != null
						&& castOther.getIsforbidden() != null && this
						.getIsforbidden().equals(castOther.getIsforbidden())))
				&& ((this.getIsgroupInfo() == castOther.getIsgroupInfo()) || (this
						.getIsgroupInfo() != null
						&& castOther.getIsgroupInfo() != null && this
						.getIsgroupInfo().equals(castOther.getIsgroupInfo())))
				&& ((this.getInvestigateUnit() == castOther
						.getInvestigateUnit()) || (this.getInvestigateUnit() != null
						&& castOther.getInvestigateUnit() != null && this
						.getInvestigateUnit().equals(
								castOther.getInvestigateUnit())))
				&& ((this.getDateDetention() == castOther.getDateDetention()) || (this
						.getDateDetention() != null
						&& castOther.getDateDetention() != null && this
						.getDateDetention()
						.equals(castOther.getDateDetention())))
				&& ((this.getDateRecord() == castOther.getDateRecord()) || (this
						.getDateRecord() != null
						&& castOther.getDateRecord() != null && this
						.getDateRecord().equals(castOther.getDateRecord())))
				&& ((this.getPermitArrestUnit() == castOther
						.getPermitArrestUnit()) || (this.getPermitArrestUnit() != null
						&& castOther.getPermitArrestUnit() != null && this
						.getPermitArrestUnit().equals(
								castOther.getPermitArrestUnit())))
				&& ((this.getDateArrest() == castOther.getDateArrest()) || (this
						.getDateArrest() != null
						&& castOther.getDateArrest() != null && this
						.getDateArrest().equals(castOther.getDateArrest())))
				&& ((this.getDateEndInvestigate() == castOther
						.getDateEndInvestigate()) || (this
						.getDateEndInvestigate() != null
						&& castOther.getDateEndInvestigate() != null && this
						.getDateEndInvestigate().equals(
								castOther.getDateEndInvestigate())))
				&& ((this.getPublicProsecution() == castOther
						.getPublicProsecution()) || (this
						.getPublicProsecution() != null
						&& castOther.getPublicProsecution() != null && this
						.getPublicProsecution().equals(
								castOther.getPublicProsecution())))
				&& ((this.getIndictmentNumber() == castOther
						.getIndictmentNumber()) || (this.getIndictmentNumber() != null
						&& castOther.getIndictmentNumber() != null && this
						.getIndictmentNumber().equals(
								castOther.getIndictmentNumber())))
				&& ((this.getDateIndictment() == castOther.getDateIndictment()) || (this
						.getDateIndictment() != null
						&& castOther.getDateIndictment() != null && this
						.getDateIndictment().equals(
								castOther.getDateIndictment())))
				&& ((this.getTrialUnit() == castOther.getTrialUnit()) || (this
						.getTrialUnit() != null
						&& castOther.getTrialUnit() != null && this
						.getTrialUnit().equals(castOther.getTrialUnit())))
				&& ((this.getJudgmentNumber() == castOther.getJudgmentNumber()) || (this
						.getJudgmentNumber() != null
						&& castOther.getJudgmentNumber() != null && this
						.getJudgmentNumber().equals(
								castOther.getJudgmentNumber())))
				&& ((this.getDateJudgment() == castOther.getDateJudgment()) || (this
						.getDateJudgment() != null
						&& castOther.getDateJudgment() != null && this
						.getDateJudgment().equals(castOther.getDateJudgment())))
				&& ((this.getInformNumber() == castOther.getInformNumber()) || (this
						.getInformNumber() != null
						&& castOther.getInformNumber() != null && this
						.getInformNumber().equals(castOther.getInformNumber())))
				&& ((this.getDateInform() == castOther.getDateInform()) || (this
						.getDateInform() != null
						&& castOther.getDateInform() != null && this
						.getDateInform().equals(castOther.getDateInform())))
				&& ((this.getOrgdetentionAddress() == castOther
						.getOrgdetentionAddress()) || (this
						.getOrgdetentionAddress() != null
						&& castOther.getOrgdetentionAddress() != null && this
						.getOrgdetentionAddress().equals(
								castOther.getOrgdetentionAddress())))
				&& ((this.getDateExecute() == castOther.getDateExecute()) || (this
						.getDateExecute() != null
						&& castOther.getDateExecute() != null && this
						.getDateExecute().equals(castOther.getDateExecute())))
				&& ((this.getDecideUnit() == castOther.getDecideUnit()) || (this
						.getDecideUnit() != null
						&& castOther.getDecideUnit() != null && this
						.getDecideUnit().equals(castOther.getDecideUnit())))
				&& ((this.getWritEffectiveDate() == castOther
						.getWritEffectiveDate()) || (this
						.getWritEffectiveDate() != null
						&& castOther.getWritEffectiveDate() != null && this
						.getWritEffectiveDate().equals(
								castOther.getWritEffectiveDate())))
				&& ((this.getWritType() == castOther.getWritType()) || (this
						.getWritType() != null
						&& castOther.getWritType() != null && this
						.getWritType().equals(castOther.getWritType())))
				&& ((this.getWritNumber() == castOther.getWritNumber()) || (this
						.getWritNumber() != null
						&& castOther.getWritNumber() != null && this
						.getWritNumber().equals(castOther.getWritNumber())))
				&& ((this.getMajorCrime() == castOther.getMajorCrime()) || (this
						.getMajorCrime() != null
						&& castOther.getMajorCrime() != null && this
						.getMajorCrime().equals(castOther.getMajorCrime())))
				&& ((this.getAdministrativePenalty() == castOther
						.getAdministrativePenalty()) || (this
						.getAdministrativePenalty() != null
						&& castOther.getAdministrativePenalty() != null && this
						.getAdministrativePenalty().equals(
								castOther.getAdministrativePenalty())))
				&& ((this.getCriminalPunshment() == castOther
						.getCriminalPunshment()) || (this
						.getCriminalPunshment() != null
						&& castOther.getCriminalPunshment() != null && this
						.getCriminalPunshment().equals(
								castOther.getCriminalPunshment())))
				&& ((this.getRemoveReason() == castOther.getRemoveReason()) || (this
						.getRemoveReason() != null
						&& castOther.getRemoveReason() != null && this
						.getRemoveReason().equals(castOther.getRemoveReason())))
				&& ((this.getRemoveDate() == castOther.getRemoveDate()) || (this
						.getRemoveDate() != null
						&& castOther.getRemoveDate() != null && this
						.getRemoveDate().equals(castOther.getRemoveDate())))
				&& ((this.getJailType() == castOther.getJailType()) || (this
						.getJailType() != null
						&& castOther.getJailType() != null && this
						.getJailType().equals(castOther.getJailType())))
				&& ((this.getJailDate() == castOther.getJailDate()) || (this
						.getJailDate() != null
						&& castOther.getJailDate() != null && this
						.getJailDate().equals(castOther.getJailDate())))
				&& ((this.getJailReason() == castOther.getJailReason()) || (this
						.getJailReason() != null
						&& castOther.getJailReason() != null && this
						.getJailReason().equals(castOther.getJailReason())))
				&& ((this.getDeathDate() == castOther.getDeathDate()) || (this
						.getDeathDate() != null
						&& castOther.getDeathDate() != null && this
						.getDeathDate().equals(castOther.getDeathDate())))
				&& ((this.getDeathReason() == castOther.getDeathReason()) || (this
						.getDeathReason() != null
						&& castOther.getDeathReason() != null && this
						.getDeathReason().equals(castOther.getDeathReason())))
				&& ((this.getDeathReasonDetail() == castOther
						.getDeathReasonDetail()) || (this
						.getDeathReasonDetail() != null
						&& castOther.getDeathReasonDetail() != null && this
						.getDeathReasonDetail().equals(
								castOther.getDeathReasonDetail())))
				&& ((this.getPerformance() == castOther.getPerformance()) || (this
						.getPerformance() != null
						&& castOther.getPerformance() != null && this
						.getPerformance().equals(castOther.getPerformance())))
				&& ((this.getManner() == castOther.getManner()) || (this
						.getManner() != null && castOther.getManner() != null && this
						.getManner().equals(castOther.getManner())))
				&& ((this.getIstrained() == castOther.getIstrained()) || (this
						.getIstrained() != null
						&& castOther.getIstrained() != null && this
						.getIstrained().equals(castOther.getIstrained())))
				&& ((this.getHascertificate() == castOther.getHascertificate()) || (this
						.getHascertificate() != null
						&& castOther.getHascertificate() != null && this
						.getHascertificate().equals(
								castOther.getHascertificate())))
				&& ((this.getSpeciality() == castOther.getSpeciality()) || (this
						.getSpeciality() != null
						&& castOther.getSpeciality() != null && this
						.getSpeciality().equals(castOther.getSpeciality())))
				&& ((this.getIssanwu() == castOther.getIssanwu()) || (this
						.getIssanwu() != null && castOther.getIssanwu() != null && this
						.getIssanwu().equals(castOther.getIssanwu())))
				&& ((this.getRisk() == castOther.getRisk()) || (this.getRisk() != null
						&& castOther.getRisk() != null && this.getRisk()
						.equals(castOther.getRisk())))
				&& ((this.getFamilyContact() == castOther.getFamilyContact()) || (this
						.getFamilyContact() != null
						&& castOther.getFamilyContact() != null && this
						.getFamilyContact()
						.equals(castOther.getFamilyContact())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getIsreward() == castOther.getIsreward()) || (this
						.getIsreward() != null
						&& castOther.getIsreward() != null && this
						.getIsreward().equals(castOther.getIsreward())))
				&& ((this.getIspunish() == castOther.getIspunish()) || (this
						.getIspunish() != null
						&& castOther.getIspunish() != null && this
						.getIspunish().equals(castOther.getIspunish())))
				&& ((this.getIsillegal() == castOther.getIsillegal()) || (this
						.getIsillegal() != null
						&& castOther.getIsillegal() != null && this
						.getIsillegal().equals(castOther.getIsillegal())))
				&& ((this.getIscrime() == castOther.getIscrime()) || (this
						.getIscrime() != null && castOther.getIscrime() != null && this
						.getIscrime().equals(castOther.getIscrime())))
				&& ((this.getHasoutmanage() == castOther.getHasoutmanage()) || (this
						.getHasoutmanage() != null
						&& castOther.getHasoutmanage() != null && this
						.getHasoutmanage().equals(castOther.getHasoutmanage())))
				&& ((this.getIstgry() == castOther.getIstgry()) || (this
						.getIstgry() != null && castOther.getIstgry() != null && this
						.getIstgry().equals(castOther.getIstgry())))
//				&& ((this.getAlarmType() == castOther.getAlarmType()) || (this
//						.getAlarmType() != null && castOther.getAlarmType() != null && this
//						.getAlarmType().equals(castOther.getAlarmType())))
				&& ((this.getWearTime() == castOther.getWearTime()) || (this
						.getWearTime() != null
						&& castOther.getWearTime() != null && this
						.getWearTime().equals(castOther.getWearTime())))
				&& ((this.getReleaseTime() == castOther.getReleaseTime()) || (this
						.getReleaseTime() != null
						&& castOther.getReleaseTime() != null && this
						.getReleaseTime().equals(castOther.getReleaseTime())))
//				&& ((this.getAlarmTime() == castOther.getAlarmTime()) || (this
//						.getAlarmTime() != null
//						&& castOther.getAlarmTime() != null && this
//						.getAlarmTime().equals(castOther.getAlarmTime())))
				&& ((this.getOpinion() == castOther.getOpinion()) || (this
						.getOpinion() != null && castOther.getOpinion() != null && this
						.getOpinion().equals(castOther.getOpinion())))
						
				&& ((this.getIsoutmanage() == castOther.getIsoutmanage()) || (this
						.getIsoutmanage() != null
						&& castOther.getIsoutmanage() != null && this
						.getIsoutmanage().equals(castOther.getIsoutmanage())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getCode() == null ? 0 : this.getCode().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getUserdName() == null ? 0 : this.getUserdName().hashCode());
		result = 37 * result
				+ (getIsadult() == null ? 0 : this.getIsadult().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getSex() == null ? 0 : this.getSex().hashCode());
		result = 37 * result
				+ (getNation() == null ? 0 : this.getNation().hashCode());
		result = 37
				* result
				+ (getIdentityCard() == null ? 0 : this.getIdentityCard()
						.hashCode());
		result = 37 * result
				+ (getBirthdate() == null ? 0 : this.getBirthdate().hashCode());
		result = 37 * result
				+ (getPassport() == null ? 0 : this.getPassport().hashCode());
		result = 37 * result
				+ (getHrPermit() == null ? 0 : this.getHrPermit().hashCode());
		result = 37
				* result
				+ (getHkIdentity() == null ? 0 : this.getHkIdentity()
						.hashCode());
		result = 37
				* result
				+ (getAmIdentity() == null ? 0 : this.getAmIdentity()
						.hashCode());
		result = 37
				* result
				+ (getTbIdentity() == null ? 0 : this.getTbIdentity()
						.hashCode());
		result = 37 * result
				+ (getGatPermit() == null ? 0 : this.getGatPermit().hashCode());
		result = 37 * result
				+ (getHealth() == null ? 0 : this.getHealth().hashCode());
		result = 37
				* result
				+ (getHealthSpecific() == null ? 0 : this.getHealthSpecific()
						.hashCode());
		result = 37
				* result
				+ (getHealthContagion() == null ? 0 : this.getHealthContagion()
						.hashCode());
		result = 37
				* result
				+ (getHealthMental() == null ? 0 : this.getHealthMental()
						.hashCode());
		result = 37
				* result
				+ (getHealthMentalSpecific() == null ? 0 : this
						.getHealthMentalSpecific().hashCode());
		result = 37
				* result
				+ (getAccreditingOrgan() == null ? 0 : this
						.getAccreditingOrgan().hashCode());
		result = 37
				* result
				+ (getPoliticsStatusOriginal() == null ? 0 : this
						.getPoliticsStatusOriginal().hashCode());
		result = 37
				* result
				+ (getPoliticsStatus() == null ? 0 : this.getPoliticsStatus()
						.hashCode());
		result = 37
				* result
				+ (getPhoneNumber() == null ? 0 : this.getPhoneNumber()
						.hashCode());
		result = 37
				* result
				+ (getNatureHome() == null ? 0 : this.getNatureHome()
						.hashCode());
		result = 37
				* result
				+ (getIssameHome() == null ? 0 : this.getIssameHome()
						.hashCode());
		result = 37 * result
				+ (getIscapital() == null ? 0 : this.getIscapital().hashCode());
		result = 37
				* result
				+ (getReportTime() == null ? 0 : this.getReportTime()
						.hashCode());
		result = 37
				* result
				+ (getHouseAddress() == null ? 0 : this.getHouseAddress()
						.hashCode());
		result = 37
				* result
				+ (getHomeAddress() == null ? 0 : this.getHomeAddress()
						.hashCode());
		result = 37
				* result
				+ (getEducationDegree() == null ? 0 : this.getEducationDegree()
						.hashCode());
		result = 37
				* result
				+ (getMaritalState() == null ? 0 : this.getMaritalState()
						.hashCode());
		result = 37 * result
				+ (getWorkState() == null ? 0 : this.getWorkState().hashCode());
		result = 37 * result
				+ (getWorkNuit() == null ? 0 : this.getWorkNuit().hashCode());
		result = 37
				* result
				+ (getWorkNuitPhone() == null ? 0 : this.getWorkNuitPhone()
						.hashCode());
		result = 37
				* result
				+ (getResponseOrg() == null ? 0 : this.getResponseOrg()
						.hashCode());
		result = 37 * result
				+ (getSupOrgId() == null ? 0 : this.getSupOrgId().hashCode());
		result = 37 * result
				+ (getGisX() == null ? 0 : this.getGisX().hashCode());
		result = 37 * result
				+ (getGisY() == null ? 0 : this.getGisY().hashCode());
		result = 37
				* result
				+ (getDeviceCode() == null ? 0 : this.getDeviceCode()
						.hashCode());
		result = 37 * result
				+ (getPsychosis() == null ? 0 : this.getPsychosis().hashCode());
		result = 37
				* result
				+ (getIsdeviceCode() == null ? 0 : this.getIsdeviceCode()
						.hashCode());
		result = 37
				* result
				+ (getIscontagion() == null ? 0 : this.getIscontagion()
						.hashCode());
		result = 37
				* result
				+ (getAdjustType() == null ? 0 : this.getAdjustType()
						.hashCode());
		result = 37
				* result
				+ (getAdjustPeriod() == null ? 0 : this.getAdjustPeriod()
						.hashCode());
		result = 37
				* result
				+ (getDateSAdjust() == null ? 0 : this.getDateSAdjust()
						.hashCode());
		result = 37
				* result
				+ (getDateEAdjust() == null ? 0 : this.getDateEAdjust()
						.hashCode());
		result = 37 * result
				+ (getCrimeType() == null ? 0 : this.getCrimeType().hashCode());
		result = 37
				* result
				+ (getAccusation() == null ? 0 : this.getAccusation()
						.hashCode());
		result = 37
				* result
				+ (getOriPunishment() == null ? 0 : this.getOriPunishment()
						.hashCode());
		result = 37 * result
				+ (getDateSOri() == null ? 0 : this.getDateSOri().hashCode());
		result = 37 * result
				+ (getDateEOri() == null ? 0 : this.getDateEOri().hashCode());
		result = 37 * result
				+ (getOriPeriod() == null ? 0 : this.getOriPeriod().hashCode());
		result = 37
				* result
				+ (getImprisonmentPeriod() == null ? 0 : this
						.getImprisonmentPeriod().hashCode());
		result = 37
				* result
				+ (getAddpunishment() == null ? 0 : this.getAddpunishment()
						.hashCode());
		result = 37
				* result
				+ (getNonpoliticalPeriod() == null ? 0 : this
						.getNonpoliticalPeriod().hashCode());
		result = 37
				* result
				+ (getDateSNonpolitical() == null ? 0 : this
						.getDateSNonpolitical().hashCode());
		result = 37
				* result
				+ (getDateENonpolitical() == null ? 0 : this
						.getDateENonpolitical().hashCode());
		result = 37
				* result
				+ (getReceiveUnit() == null ? 0 : this.getReceiveUnit()
						.hashCode());
		result = 37
				* result
				+ (getReceivePerson() == null ? 0 : this.getReceivePerson()
						.hashCode());
		result = 37
				* result
				+ (getDateTransfer() == null ? 0 : this.getDateTransfer()
						.hashCode());
		result = 37 * result
				+ (getSisType() == null ? 0 : this.getSisType().hashCode());
		result = 37 * result
				+ (getSansType() == null ? 0 : this.getSansType().hashCode());
		result = 37
				* result
				+ (getIsRecidivism() == null ? 0 : this.getIsRecidivism()
						.hashCode());
		result = 37 * result
				+ (getIsalone() == null ? 0 : this.getIsalone().hashCode());
		result = 37 * result
				+ (getForbidden() == null ? 0 : this.getForbidden().hashCode());
		result = 37
				* result
				+ (getDateSForbidden() == null ? 0 : this.getDateSForbidden()
						.hashCode());
		result = 37
				* result
				+ (getDateEForbidden() == null ? 0 : this.getDateEForbidden()
						.hashCode());
		result = 37
				* result
				+ (getDateReceive() == null ? 0 : this.getDateReceive()
						.hashCode());
		result = 37
				* result
				+ (getReceiveType() == null ? 0 : this.getReceiveType()
						.hashCode());
		result = 37
				* result
				+ (getReportInfo() == null ? 0 : this.getReportInfo()
						.hashCode());
		result = 37 * result
				+ (getGroupInfo() == null ? 0 : this.getGroupInfo().hashCode());
		result = 37 * result
				+ (getEremark() == null ? 0 : this.getEremark().hashCode());
		result = 37 * result
				+ (getSlfzType() == null ? 0 : this.getSlfzType().hashCode());
		result = 37
				* result
				+ (getIsforbidden() == null ? 0 : this.getIsforbidden()
						.hashCode());
		result = 37
				* result
				+ (getIsgroupInfo() == null ? 0 : this.getIsgroupInfo()
						.hashCode());
		result = 37
				* result
				+ (getInvestigateUnit() == null ? 0 : this.getInvestigateUnit()
						.hashCode());
		result = 37
				* result
				+ (getDateDetention() == null ? 0 : this.getDateDetention()
						.hashCode());
		result = 37
				* result
				+ (getDateRecord() == null ? 0 : this.getDateRecord()
						.hashCode());
		result = 37
				* result
				+ (getPermitArrestUnit() == null ? 0 : this
						.getPermitArrestUnit().hashCode());
		result = 37
				* result
				+ (getDateArrest() == null ? 0 : this.getDateArrest()
						.hashCode());
		result = 37
				* result
				+ (getDateEndInvestigate() == null ? 0 : this
						.getDateEndInvestigate().hashCode());
		result = 37
				* result
				+ (getPublicProsecution() == null ? 0 : this
						.getPublicProsecution().hashCode());
		result = 37
				* result
				+ (getIndictmentNumber() == null ? 0 : this
						.getIndictmentNumber().hashCode());
		result = 37
				* result
				+ (getDateIndictment() == null ? 0 : this.getDateIndictment()
						.hashCode());
		result = 37 * result
				+ (getTrialUnit() == null ? 0 : this.getTrialUnit().hashCode());
		result = 37
				* result
				+ (getJudgmentNumber() == null ? 0 : this.getJudgmentNumber()
						.hashCode());
		result = 37
				* result
				+ (getDateJudgment() == null ? 0 : this.getDateJudgment()
						.hashCode());
		result = 37
				* result
				+ (getInformNumber() == null ? 0 : this.getInformNumber()
						.hashCode());
		result = 37
				* result
				+ (getDateInform() == null ? 0 : this.getDateInform()
						.hashCode());
		result = 37
				* result
				+ (getOrgdetentionAddress() == null ? 0 : this
						.getOrgdetentionAddress().hashCode());
		result = 37
				* result
				+ (getDateExecute() == null ? 0 : this.getDateExecute()
						.hashCode());
		result = 37
				* result
				+ (getDecideUnit() == null ? 0 : this.getDecideUnit()
						.hashCode());
		result = 37
				* result
				+ (getWritEffectiveDate() == null ? 0 : this
						.getWritEffectiveDate().hashCode());
		result = 37 * result
				+ (getWritType() == null ? 0 : this.getWritType().hashCode());
		result = 37
				* result
				+ (getWritNumber() == null ? 0 : this.getWritNumber()
						.hashCode());
		result = 37
				* result
				+ (getMajorCrime() == null ? 0 : this.getMajorCrime()
						.hashCode());
		result = 37
				* result
				+ (getAdministrativePenalty() == null ? 0 : this
						.getAdministrativePenalty().hashCode());
		result = 37
				* result
				+ (getCriminalPunshment() == null ? 0 : this
						.getCriminalPunshment().hashCode());
		result = 37
				* result
				+ (getRemoveReason() == null ? 0 : this.getRemoveReason()
						.hashCode());
		result = 37
				* result
				+ (getRemoveDate() == null ? 0 : this.getRemoveDate()
						.hashCode());
		result = 37 * result
				+ (getJailType() == null ? 0 : this.getJailType().hashCode());
		result = 37 * result
				+ (getJailDate() == null ? 0 : this.getJailDate().hashCode());
		result = 37
				* result
				+ (getJailReason() == null ? 0 : this.getJailReason()
						.hashCode());
		result = 37 * result
				+ (getDeathDate() == null ? 0 : this.getDeathDate().hashCode());
		result = 37
				* result
				+ (getDeathReason() == null ? 0 : this.getDeathReason()
						.hashCode());
		result = 37
				* result
				+ (getDeathReasonDetail() == null ? 0 : this
						.getDeathReasonDetail().hashCode());
		result = 37
				* result
				+ (getPerformance() == null ? 0 : this.getPerformance()
						.hashCode());
		result = 37 * result
				+ (getManner() == null ? 0 : this.getManner().hashCode());
		result = 37 * result
				+ (getIstrained() == null ? 0 : this.getIstrained().hashCode());
		result = 37
				* result
				+ (getHascertificate() == null ? 0 : this.getHascertificate()
						.hashCode());
		result = 37
				* result
				+ (getSpeciality() == null ? 0 : this.getSpeciality()
						.hashCode());
		result = 37 * result
				+ (getIssanwu() == null ? 0 : this.getIssanwu().hashCode());
		result = 37 * result
				+ (getRisk() == null ? 0 : this.getRisk().hashCode());
		result = 37
				* result
				+ (getFamilyContact() == null ? 0 : this.getFamilyContact()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getIsreward() == null ? 0 : this.getIsreward().hashCode());
		result = 37 * result
				+ (getIspunish() == null ? 0 : this.getIspunish().hashCode());
		result = 37 * result
				+ (getIsillegal() == null ? 0 : this.getIsillegal().hashCode());
		result = 37 * result
				+ (getIscrime() == null ? 0 : this.getIscrime().hashCode());
		result = 37 * result
				+ (getIstgry() == null ? 0 : this.getIstgry().hashCode());
//		result = 37 * result
//				+ (getAlarmType() == null ? 0 : this.getAlarmType().hashCode());
		result = 37 * result
				+ (getWearTime() == null ? 0 : this.getWearTime().hashCode());
		result = 37 * result
				+ (getReleaseTime() == null ? 0 : this.getReleaseTime().hashCode());
//		result = 37 * result
//				+ (getAlarmTime() == null ? 0 : this.getAlarmTime().hashCode());
		result = 37 * result
				+ (getHasoutmanage() == null ? 0 : this.getHasoutmanage()
						.hashCode());
		result = 37 * result
				+ (getOpinion() == null ? 0 : this.getOpinion().hashCode());
		result = 37 * result
				+ (getIsoutmanage() == null ? 0 : this.getIsoutmanage()
						.hashCode());
		return result;
	}


}