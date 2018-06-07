package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "VIEW_FXRY_EXECUTE_INFO", schema = "SQJZ")
public class ViewFXRYExecuteInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String identityCard;
	private String sex;
	private String state;
	private String isadult;
	private String phoneNumber;
	private String responseOrg;
	private String recordOrgId;
	private String responseOrgName;
	private String excuteId;
	private String adjustType;
	private String adjustPeriod;
	private Date dateSAdjust;
	private Date dateEAdjust;
	private String crimeType;
	private Integer reported;
	private Integer remainDays;
	private String deviceCode;
	private Integer vacated;
	private String isTgry;
	// Constructors
	@Column(name = "ISTGRY", length =1)
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}
	public ViewFXRYExecuteInfo() {
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

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "IDENTITY_CARD", length = 18)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "STATE", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ISADULT", length = 30)
	public String getIsadult() {
		return this.isadult;
	}

	public void setIsadult(String isadult) {
		this.isadult = isadult;
	}

	@Column(name = "PHONE_NUMBER", length = 30)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "EXCUTE_ID", length = 32)
	public String getExcuteId() {
		return this.excuteId;
	}

	public void setExcuteId(String excuteId) {
		this.excuteId = excuteId;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ADJUST_PERIOD")
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

	@Column(name = "REPORTED")
	public Integer getReported() {
		return this.reported;
	}

	public void setReported(Integer reported) {
		this.reported = reported;
	}
	
	@Column(name = "RESPONSE_ORG")
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "RECORD_ORG_ID")
	public String getRecordOrgId() {
		return recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Column(name = "REMAIN_DAYS")
	public Integer getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	@Column(name = "RESPONSE_ORG_NAME")
	public String getResponseOrgName() {
		return responseOrgName;
	}

	public void setResponseOrgName(String responseOrgName) {
		this.responseOrgName = responseOrgName;
	}

	@Column(name = "DEVICE_CODE")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	@Column(name = "VACATE")
	public Integer getVacated() {
		return vacated;
	}

	public void setVacated(Integer vacated) {
		this.vacated = vacated;
	}

}