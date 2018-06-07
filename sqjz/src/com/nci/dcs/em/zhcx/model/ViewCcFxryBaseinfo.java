package com.nci.dcs.em.zhcx.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewCcFxryBaseinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_BASEINFO", schema = "SQJZ")
public class ViewCcFxryBaseinfo implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4323121940300585616L;
	private String responseOrg;
	private String adjustType;
	private String name;
	private String sex;
	private String healthMental;
	private String natureHome;
	private String politicsStatus;
	private String politicsStatusOriginal;
	private String educationDegree;
	private String maritalState;
	private String workState;
	private String crimeType;
	private String oriPunishment;
	private String addpunishment;
	private String sisType;
	private String sansType;
	private String isRecidivism;
	private String isalone;
	private String isforbidden;
	private Date dateReceive;
	private String receiveType;
	private String reportInfo;
	private String isgroupInfo;
	private String groupInfo;
	private String isdeviceCode;
	private String fxryId;

	// Constructors

	/** default constructor */
	public ViewCcFxryBaseinfo() {
	}

	/** full constructor */
	public ViewCcFxryBaseinfo(String responseOrg, String adjustType,
			String name, String sex, String healthMental, String natureHome,
			String politicsStatus, String politicsStatusOriginal,
			String educationDegree, String maritalState, String workState,
			String crimeType, String oriPunishment, String addpunishment,
			String sisType, String sansType, String isRecidivism,
			String isalone, String isforbidden, Date dateReceive,
			String receiveType, String reportInfo, String isgroupInfo,
			String groupInfo, String isdeviceCode, String fxryId) {
		this.responseOrg = responseOrg;
		this.adjustType = adjustType;
		this.name = name;
		this.sex = sex;
		this.healthMental = healthMental;
		this.natureHome = natureHome;
		this.politicsStatus = politicsStatus;
		this.politicsStatusOriginal = politicsStatusOriginal;
		this.educationDegree = educationDegree;
		this.maritalState = maritalState;
		this.workState = workState;
		this.crimeType = crimeType;
		this.oriPunishment = oriPunishment;
		this.addpunishment = addpunishment;
		this.sisType = sisType;
		this.sansType = sansType;
		this.isRecidivism = isRecidivism;
		this.isalone = isalone;
		this.isforbidden = isforbidden;
		this.dateReceive = dateReceive;
		this.receiveType = receiveType;
		this.reportInfo = reportInfo;
		this.isgroupInfo = isgroupInfo;
		this.groupInfo = groupInfo;
		this.isdeviceCode = isdeviceCode;
		this.fxryId = fxryId;
	}

	// Property accessors
	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
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

	@Column(name = "HEALTH_MENTAL", length = 30)
	public String getHealthMental() {
		return this.healthMental;
	}

	public void setHealthMental(String healthMental) {
		this.healthMental = healthMental;
	}

	@Column(name = "NATURE_HOME", length = 30)
	public String getNatureHome() {
		return this.natureHome;
	}

	public void setNatureHome(String natureHome) {
		this.natureHome = natureHome;
	}

	@Column(name = "POLITICS_STATUS", length = 30)
	public String getPoliticsStatus() {
		return this.politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	@Column(name = "POLITICS_STATUS_ORIGINAL", length = 30)
	public String getPoliticsStatusOriginal() {
		return this.politicsStatusOriginal;
	}

	public void setPoliticsStatusOriginal(String politicsStatusOriginal) {
		this.politicsStatusOriginal = politicsStatusOriginal;
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

	@Column(name = "CRIME_TYPE", length = 30)
	public String getCrimeType() {
		return this.crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	@Column(name = "ORI_PUNISHMENT", length = 30)
	public String getOriPunishment() {
		return this.oriPunishment;
	}

	public void setOriPunishment(String oriPunishment) {
		this.oriPunishment = oriPunishment;
	}

	@Column(name = "ADDPUNISHMENT", length = 30)
	public String getAddpunishment() {
		return this.addpunishment;
	}

	public void setAddpunishment(String addpunishment) {
		this.addpunishment = addpunishment;
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

	@Column(name = "ISFORBIDDEN", length = 40)
	public String getIsforbidden() {
		return this.isforbidden;
	}

	public void setIsforbidden(String isforbidden) {
		this.isforbidden = isforbidden;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Column(name = "ISGROUP_INFO", length = 40)
	public String getIsgroupInfo() {
		return this.isgroupInfo;
	}

	public void setIsgroupInfo(String isgroupInfo) {
		this.isgroupInfo = isgroupInfo;
	}

	@Column(name = "GROUP_INFO", length = 150)
	public String getGroupInfo() {
		return this.groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}
	
	@Column(name = "ISDEVICE_CODE", length = 40)
	public String getIsdeviceCode() {
		return this.isdeviceCode;
	}

	public void setIsdeviceCode(String isdeviceCode) {
		this.isdeviceCode = isdeviceCode;
	}
	@Id
	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}
}