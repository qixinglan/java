package com.nci.dcs.jzgl.cxtj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewCcFxryAllinfoSearchId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_ALLINFO_SEARCH", schema = "SQJZ")
public class ViewCcFxryAllinfoSearch implements java.io.Serializable {

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
	private Double gisX;
	private Double gisY;
	private String deviceCode;
	private String psychosis;
	private String isdeviceCode;

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
	private String supOrgId;

	private String isreward;
	private String ispunish;
	private String isillegal;
	private String iscrime;
	private String isoutmanage;

	private String iscontagion;
	private String hasoutmanage;

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

	@Column(name = "IDENTITY_CARD", length = 20)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
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

	@Column(name = "PHONE_NUMBER", length = 32)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "REPORT_TIME", length = 7)
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

	@Column(name = "DEVICE_CODE", length = 12)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_S_ADJUST", length = 7)
	public Date getDateSAdjust() {
		return this.dateSAdjust;
	}

	public void setDateSAdjust(Date dateSAdjust) {
		this.dateSAdjust = dateSAdjust;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_E_ADJUST", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_S_ORI", length = 7)
	public Date getDateSOri() {
		return this.dateSOri;
	}

	public void setDateSOri(Date dateSOri) {
		this.dateSOri = dateSOri;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_E_ORI", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_S_NONPOLITICAL", length = 7)
	public Date getDateSNonpolitical() {
		return this.dateSNonpolitical;
	}

	public void setDateSNonpolitical(Date dateSNonpolitical) {
		this.dateSNonpolitical = dateSNonpolitical;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_E_NONPOLITICAL", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_TRANSFER", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_S_FORBIDDEN", length = 7)
	public Date getDateSForbidden() {
		return this.dateSForbidden;
	}

	public void setDateSForbidden(Date dateSForbidden) {
		this.dateSForbidden = dateSForbidden;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_E_FORBIDDEN", length = 7)
	public Date getDateEForbidden() {
		return this.dateEForbidden;
	}

	public void setDateEForbidden(Date dateEForbidden) {
		this.dateEForbidden = dateEForbidden;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_RECEIVE", length = 7)
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

	@Column(name = "ISFORBIDDEN", length = 40)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_DETENTION", length = 7)
	public Date getDateDetention() {
		return this.dateDetention;
	}

	public void setDateDetention(Date dateDetention) {
		this.dateDetention = dateDetention;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_RECORD", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_ARREST", length = 7)
	public Date getDateArrest() {
		return this.dateArrest;
	}

	public void setDateArrest(Date dateArrest) {
		this.dateArrest = dateArrest;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_END_INVESTIGATE", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_INDICTMENT", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "INFORM_NUMBER", length = 100)
	public String getInformNumber() {
		return this.informNumber;
	}

	public void setInformNumber(String informNumber) {
		this.informNumber = informNumber;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_INFORM", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "DATE_EXECUTE", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "WRIT_EFFECTIVE_DATE", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "JAIL_DATE", length = 7)
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

	@JSON(format = DateTimeFmtSpec.DATE)
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

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "ISREWARD")
	public String getIsreward() {
		return isreward;
	}

	public void setIsreward(String isreward) {
		this.isreward = isreward;
	}

	@Column(name = "ISPUNISH")
	public String getIspunish() {
		return ispunish;
	}

	public void setIspunish(String ispunish) {
		this.ispunish = ispunish;
	}

	@Column(name = "ISILLEGAL")
	public String getIsillegal() {
		return isillegal;
	}

	public void setIsillegal(String isillegal) {
		this.isillegal = isillegal;
	}

	@Column(name = "ISCRIME")
	public String getIscrime() {
		return iscrime;
	}

	public void setIscrime(String iscrime) {
		this.iscrime = iscrime;
	}

	@Column(name = "ISOUTMANAGE")
	public String getIsoutmanage() {
		return isoutmanage;
	}

	public void setIsoutmanage(String isoutmanage) {
		this.isoutmanage = isoutmanage;
	}

	@Column(name = "ISCONTAGION")
	public String getIscontagion() {
		return iscontagion;
	}

	public void setIscontagion(String iscontagion) {
		this.iscontagion = iscontagion;
	}

	@Column(name = "HASOUTMANAGE")
	public String getHasoutmanage() {
		return hasoutmanage;
	}

	public void setHasoutmanage(String hasoutmanage) {
		this.hasoutmanage = hasoutmanage;
	}
	@Column(name = "ISCAPITAL", length = 30)
	public String getIscapital() {
		return iscapital;
	}

	public void setIscapital(String iscapital) {
		this.iscapital = iscapital;
	}
}