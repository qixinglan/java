package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "CC_FXRY_BASEINFO", schema = "SQJZ")
public class FXRYBaseinfo implements java.io.Serializable {
	private String id;//后台字段
	private String code;
	private String state;//后台字段
	private String origin;//后台字段
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
	private String houseAddressId;
	private String homeAddressId;
	private Address houseAddress;//外键
	private Address homeAddress;//外键
	private String educationDegree;
	private String maritalState;
	private String workState;
	private String workNuit;
	private String workNuitPhone;
	private String recordOrgId;//后台字段
	private Date createTime;//创建时间
	private String deviceCode;//监控设备编号
	private String responseOrg;//负责矫正单位
	private String picture; //照片
	private String psychosis; //照片
	private String fingerPrintNo;
	private String isTgry;
	@Column(name = "ISTGRY", length =1)
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}
	@Column(name = "FINGERPRINTNO", length = 32)
	public String getFingerPrintNo() {
		return fingerPrintNo;
	}

	public void setFingerPrintNo(String fingerPrintNo) {
		this.fingerPrintNo = fingerPrintNo;
	}

	private FXRYReportOrg report;
	
	public FXRYBaseinfo() {
		this.createTime = new Date();
	}
	
	public void copyBaseInfo(FXRYBaseinfo right){
		userdName = right.getUserdName();
		isadult = right.getIsadult();
		name = right.getName();
		sex = right.getSex();
		nation = right.getNation();
		identityCard = right.getIdentityCard();
		birthdate = right.getBirthdate();
		picture = right.getPicture();
		reportTime = right.getReportTime();
		report = right.getReport();
		fingerPrintNo = right.getFingerPrintNo();
		isTgry=right.getIsTgry();
	}
	public void copyMoreInfo(FXRYBaseinfo right) {
		userdName = right.getUserdName();
		isadult = right.getIsadult();
		name = right.getName();
		sex = right.getSex();
		nation = right.getNation();
		identityCard = right.getIdentityCard();
		birthdate = right.getBirthdate();
		picture = right.getPicture();
		passport = right.getPassport();
		hrPermit = right.getHrPermit();
		hkIdentity = right.getHkIdentity();
		amIdentity = right.getAmIdentity();
		tbIdentity = right.getTbIdentity();
		gatPermit = right.getGatPermit();
		health = right.getHealth();
		healthSpecific = right.getHealthSpecific();
		healthContagion = right.getHealthContagion();
		healthMental = right.getHealthMental();
		healthMentalSpecific = right.getHealthMentalSpecific();
		accreditingOrgan = right.getAccreditingOrgan();
		politicsStatusOriginal = right.getPoliticsStatusOriginal();
		politicsStatus = right.getPoliticsStatus();
		phoneNumber = right.getPhoneNumber();
		natureHome = right.getNatureHome();
		issameHome = right.getIssameHome();
		//reportTime = right.getReportTime();
		houseAddress = right.getHouseAddress();
		homeAddress = right.getHomeAddress();
		educationDegree = right.getEducationDegree();
		maritalState = right.getMaritalState();
		workState = right.getWorkState();
		workNuit = right.getWorkNuit();
		workNuitPhone = right.getWorkNuitPhone();
		psychosis=right.getPsychosis();
		isTgry=right.getIsTgry();
	}

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
	@Column(name = "REPORT_TIME")
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	//@OneToOne
	//@Column(name = "HOUSE_ADDRESS", length = 32)
	//@JoinColumn(name="HOUSE_ADDRESS", insertable=true,updatable=true)
	@Transient
	public Address getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(Address houseAddress) {
		this.houseAddress = houseAddress;
	}

	//@OneToOne
	//@Column(name = "HOME_ADDRESS", length = 32)
	//@JoinColumn(name="HOME_ADDRESS", insertable=true,updatable=true)
	@Transient
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(serialize=false, deserialize=false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "DEVICE_CODE")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	@Column(name = "RESPONSE_ORG", length=32)
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "PICTURE", length=32)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Transient
	public FXRYReportOrg getReport() {
		return report;
	}

	public void setReport(FXRYReportOrg report) {
		this.report = report;
	}

	@Column(name = "HOUSE_ADDRESS", length=32)
	public String getHouseAddressId() {
		return houseAddressId;
	}

	public void setHouseAddressId(String houseAddressId) {
		this.houseAddressId = houseAddressId;
	}

	@Column(name = "HOME_ADDRESS", length=32)
	public String getHomeAddressId() {
		return homeAddressId;
	}

	public void setHomeAddressId(String homeAddressId) {
		this.homeAddressId = homeAddressId;
	}
	@Column(name = "PSYCHOSIS", length=30)
	public String getPsychosis() {
		return psychosis;
	}

	public void setPsychosis(String psychosis) {
		this.psychosis = psychosis;
	}
}