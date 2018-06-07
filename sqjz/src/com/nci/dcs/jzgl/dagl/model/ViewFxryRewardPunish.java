package com.nci.dcs.jzgl.dagl.model;

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
 * ViewFxryRewardpunish entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_REWARDPUNISH", schema = "SQJZ")
public class ViewFxryRewardPunish implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String sex;
	private String nation;
	private String identityCard;
	private Date birthdate;
	private String state;
	private String responseOrg;
	private String supOrgId;
	private Long rnum = new Long(0);
	private Long pnum = new Long(0);
	private Long inum = new Long(0);
	private Long cnum = new Long(0);

	// Property accessors
	@Id
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

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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
	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrgId() {
		return supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

	@Column(name = "RNUM", precision = 22, scale = 0)
	public Long getRnum() {
		return rnum;
	}

	public void setRnum(Long rnum) {
		this.rnum = rnum;
	}

	@Column(name = "PNUM", precision = 22, scale = 0)
	public Long getPnum() {
		return pnum;
	}

	public void setPnum(Long pnum) {
		this.pnum = pnum;
	}

	@Column(name = "INUM", precision = 22, scale = 0)
	public Long getInum() {
		return inum;
	}

	public void setInum(Long inum) {
		this.inum = inum;
	}

	@Column(name = "CNUM", precision = 22, scale = 0)
	public Long getCnum() {
		return cnum;
	}

	public void setCnum(Long cnum) {
		this.cnum = cnum;
	}

}