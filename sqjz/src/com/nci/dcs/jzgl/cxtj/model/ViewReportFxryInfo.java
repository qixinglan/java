package com.nci.dcs.jzgl.cxtj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "VIEW_REPORT_FXRY_INFO", schema = "SQJZ")
public class ViewReportFxryInfo implements java.io.Serializable {
	private String id;// 后台字段
	private String state;// 后台字段
	private String sex;
	private String natureHome;
	private String educationDegree;
	private String workState;
	private String responseOrg;// 负责矫正单位
	private String supOrg; 
	private String crimeType;
	private String adjustType;
	private String age;
	private String device;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "STATE", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "NATURE_HOME", length = 30)
	public String getNatureHome() {
		return this.natureHome;
	}

	public void setNatureHome(String natureHome) {
		this.natureHome = natureHome;
	}

	@Column(name = "EDUCATION_DEGREE", length = 30)
	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	@Column(name = "WORK_STATE", length = 30)
	public String getWorkState() {
		return this.workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(String supOrg) {
		this.supOrg = supOrg;
	}

	@Column(name = "CRIME_TYPE")
	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	@Column(name = "ADJUST_TYPE")
	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "AGE")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	@Column(name = "DEVICE_CODE")
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}