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
 * ViewCcDoc7 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_7", schema = "SQJZ")
public class ViewCcDoc7 implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ViewCcDoc7() {
	}

	// Fields
	// 服刑人员id
	private String id;
	// 服刑人员编号
	private String code;
	// 姓名
	private String name;
	// 曾用名
	private String userdName;
	// 身份证号
	private String identityCard;
	// 性别
	private String sex;
	// 民族
	private String nation;
	// 生日
	private Date birthdate;
	// 学历
	private String educationDegree;
	// 健康状况
	private String health;
	// 原政治面貌
	private String politicsStatusOriginal;
	// 婚姻状况
	private String maritalState;
	// 居住地详细地址
	private String houseAddress;
	// 户籍详细地址
	private String homeAddress;
	// 现工作单位
	private String workNuit;
	// 现工作单位电话
	private String workNuitPhone;
	// 联系电话
	private String phoneNumber;
	// 具体罪名
	private String accusation;
	// 原判刑罚
	private String oriPunishment;
	// 原判刑期
	private String oriPeriod;
	// 附加刑
	private String addpunishment;
	// 社区矫正决定机关
	private String decideUnit;
	// 原羁押场所
	private String orgdetentionAddress;
	// 宣告禁止令内容
	private String forbidden;
	// 禁令开始时间
	private Date dateSForbidden;
	// 禁令结束时间
	private Date dateEForbidden;
	// 矫正类型
	private String adjustType;
	// 矫正期限
	private String adjustPeriod;
	// 矫正开始时间
	private Date dateSAdjust;
	// 矫正结束时间
	private Date dateEAdjust;
	// 文书类型
	private String writType;
	private String receiveType;
	// 报道时间
	private Date reportTime;
	// 报道类型
	private String reportCode;
	// 主要犯罪事实
	private String majorCrime;
	// 刑事处罚(本次犯罪前的刑事处罚)
	private String criminalPunshment;

	private String birth_Date;
	private String report_Time;
	// 服刑人员照片
	private byte[] picture;
	// 矫正小组成员
	private String groupInfo;
	// 服刑人员所在司法所名称
	private String responseOrg;
	// 服刑人员所在司法所机构id
	private String responseOrgCode;
	// 服刑人员所在司法所上级机构id
	private String supOrgId;
	// 服刑人员所在司法所的机构类型
	private String orgType;
	// 服刑人员所在司法所的上级机构名称
	private String supResponseOrg;

	@Transient
	public String getBirth_Date() {
		return birth_Date;
	}

	public void setBirth_Date(String birth_Date) {
		this.birth_Date = birth_Date;
	}

	@Transient
	public String getReport_Time() {
		return report_Time;
	}

	public void setReport_Time(String report_Time) {
		this.report_Time = report_Time;
	}

	@Column(name = "CRIMINAL_PUNSHMENT", length = 600)
	public String getCriminalPunshment() {
		return criminalPunshment;
	}

	public void setCriminalPunshment(String criminalPunshment) {
		this.criminalPunshment = criminalPunshment;
	}

	// 填表日期
	private String tbrq;
	private String reportCode_1; // 在规定时限报道
	private String reportCode_2; // 超出规定时限报道
	private String reportCode_3; // 未报道且下落不明

	@Transient
	public String getReportCode_1() {
		return reportCode_1;
	}

	public void setReportCode_1(String reportCode_1) {
		this.reportCode_1 = reportCode_1;
	}
	@Transient
	public String getReportCode_2() {
		return reportCode_2;
	}

	public void setReportCode_2(String reportCode_2) {
		this.reportCode_2 = reportCode_2;
	}
	@Transient
	public String getReportCode_3() {
		return reportCode_3;
	}

	public void setReportCode_3(String reportCode_3) {
		this.reportCode_3 = reportCode_3;
	}
	private String beforeCrime; // 之前违法记录


	private String wssdsj; // 法律文书收到时间

	@Transient
	public String getWssdsj() {
		return wssdsj;
	}

	public void setWssdsj(String wssdsj) {
		this.wssdsj = wssdsj;
	}

	@Transient
	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	@Transient
	public String getBeforeCrime() {
		return beforeCrime;
	}

	public void setBeforeCrime(String beforeCrime) {
		this.beforeCrime = beforeCrime;
	}

	/** full constructor */
	public ViewCcDoc7(String id, String code, String name, String userdName,
			String identityCard, String sex, String nation, Date birthdate,
			String educationDegree, String health,
			String politicsStatusOriginal, String maritalState,
			String houseAddress, String homeAddress, String workNuit,
			String workNuitPhone, String phoneNumber, String accusation,
			String oriPunishment, String oriPeriod, String addpunishment,String decideUnit,
			String orgdetentionAddress, String forbidden, Date dateSForbidden,
			Date dateEForbidden, String adjustType, String adjustPeriod,
			Date dateSAdjust, Date dateEAdjust, String writType,
			String receiveType, Date reportTime, String reportCode,
			String majorCrime, String criminalPunshment, byte[] picture,
			String groupInfo, String responseOrg, String responseOrgCode,
			String supOrgId, String orgType, String supResponseOrg) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.userdName = userdName;
		this.identityCard = identityCard;
		this.sex = sex;
		this.nation = nation;
		this.birthdate = birthdate;
		this.educationDegree = educationDegree;
		this.health = health;
		this.politicsStatusOriginal = politicsStatusOriginal;
		this.maritalState = maritalState;
		this.houseAddress = houseAddress;
		this.homeAddress = homeAddress;
		this.workNuit = workNuit;
		this.workNuitPhone = workNuitPhone;
		this.phoneNumber = phoneNumber;
		this.accusation = accusation;
		this.oriPunishment = oriPunishment;
		this.oriPeriod = oriPeriod;
		this.addpunishment=addpunishment;
		this.decideUnit = decideUnit;
		this.orgdetentionAddress = orgdetentionAddress;
		this.forbidden = forbidden;
		this.dateSForbidden = dateSForbidden;
		this.dateEForbidden = dateEForbidden;
		this.adjustType = adjustType;
		this.adjustPeriod = adjustPeriod;
		this.dateSAdjust = dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.writType = writType;
		this.receiveType = receiveType;
		this.reportTime = reportTime;
		this.reportCode = reportCode;
		this.majorCrime = majorCrime;
		this.criminalPunshment = criminalPunshment;
		this.picture = picture;
		this.groupInfo = groupInfo;
		this.responseOrg = responseOrg;
		this.responseOrgCode = responseOrgCode;
		this.supOrgId = supOrgId;
		this.orgType = orgType;
		this.supResponseOrg = supResponseOrg;
	}

	// Property accessors
	@Column(name = "REPORT_CODE", length = 30)
	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", length = 32)
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

	@Column(name = "USERD_NAME", length = 60)
	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	@Column(name = "IDENTITY_CARD", length = 100)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	@Column(name = "HEALTH", length = 128)
	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Column(name = "POLITICS_STATUS_ORIGINAL", length = 128)
	public String getPoliticsStatusOriginal() {
		return this.politicsStatusOriginal;
	}

	public void setPoliticsStatusOriginal(String politicsStatusOriginal) {
		this.politicsStatusOriginal = politicsStatusOriginal;
	}

	@Column(name = "MARITAL_STATE", length = 128)
	public String getMaritalState() {
		return this.maritalState;
	}

	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
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

	@Column(name = "PHONE_NUMBER", length = 100)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	@Column(name = "ADDPUNISHMENT", length = 30)
	public String getAddpunishment() {
		return this.addpunishment;
	}

	public void setAddpunishment(String addpunishment) {
		this.addpunishment = addpunishment;
	}

	@Column(name = "DECIDE_UNIT", length = 150)
	public String getDecideUnit() {
		return this.decideUnit;
	}

	public void setDecideUnit(String decideUnit) {
		this.decideUnit = decideUnit;
	}

	@Column(name = "ORGDETENTION_ADDRESS", length = 150)
	public String getOrgdetentionAddress() {
		return this.orgdetentionAddress;
	}

	public void setOrgdetentionAddress(String orgdetentionAddress) {
		this.orgdetentionAddress = orgdetentionAddress;
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

	@Column(name = "WRIT_TYPE", length = 128)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Column(name = "RECEIVE_TYPE", length = 128)
	public String getReceiveType() {
		return this.receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME", length = 7)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "MAJOR_CRIME", length = 600)
	public String getMajorCrime() {
		return this.majorCrime;
	}

	public void setMajorCrime(String majorCrime) {
		this.majorCrime = majorCrime;
	}

	@Column(name = "PICTURE")
	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ORI_PUNISHMENT", length = 128)
	public String getOriPunishment() {
		return this.oriPunishment;
	}

	public void setOriPunishment(String oriPunishment) {
		this.oriPunishment = oriPunishment;
	}

	@Column(name = "GROUP_INFO", length = 150)
	public String getGroupInfo() {
		return this.groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	@Column(name = "RESPONSE_ORG", length = 100)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "RESPONSE_ORG_CODE", length = 32)
	public String getResponseOrgCode() {
		return this.responseOrgCode;
	}

	public void setResponseOrgCode(String responseOrgCode) {
		this.responseOrgCode = responseOrgCode;
	}
	
	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return this.supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "ORG_TYPE", length = 5)
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "SUP_RESPONSE_ORG", length = 100)
	public String getSupResponseOrg() {
		return this.supResponseOrg;
	}

	public void setSupResponseOrg(String supResponseOrg) {
		this.supResponseOrg = supResponseOrg;
	}

}