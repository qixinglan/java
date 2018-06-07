package com.nci.dcs.jzgl.dagl.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewFxryReadyRelease entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_EDUCATION_JZJJQJY", schema = "SQJZ")
public class ViewFxryEducationJzjjqjy implements java.io.Serializable {

	// Fields
	private String id;
	private String name;
	private String code;
	private String identityCard;
	private String sex;
	private String state;
	private String responseOrg;
	private Date startdate;
	private Date enddate;
	private String adjustType;
	private String orgName;
	private String supOrgId;
	private BigDecimal days;
	private String isTgry;
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}
	/** default constructor */
	public ViewFxryEducationJzjjqjy() {
	}

	/** full constructor */
	public ViewFxryEducationJzjjqjy(String id, String name, String code,
			String identityCard, String sex, String state, String responseOrg,
			Date startdate, Date enddate, String adjustType, String orgName,
			String supOrgId, BigDecimal days) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.identityCard = identityCard;
		this.sex = sex;
		this.state = state;
		this.responseOrg = responseOrg;
		this.startdate = startdate;
		this.enddate = enddate;
		this.adjustType = adjustType;
		this.orgName = orgName;
		this.supOrgId = supOrgId;
		this.days = days;
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

	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return this.responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "ADJUST_TYPE", length = 30)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ORG_NAME", length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return this.supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "DAYS", precision = 22, scale = 0)
	public BigDecimal getDays() {
		return this.days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}
}