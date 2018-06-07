package com.nci.dcs.jzgl.sync.model;

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
 * ViewCcFxrySyncinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_FXRY_SYNCINFO", schema = "SQJZ")
public class ViewCcFxrySyncInfo implements java.io.Serializable {

	private String id;
	private String name;
	private String code;
	private String state;
	private String sex;
	private String identityCard;
	private String responseOrg;
	private String supOrgId;
	private String isok;
	private String saveTime;
	private Date syncTime;
	private String syncState;
	private String realState;

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

	@Column(name = "IDENTITY_CARD", length = 20)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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

	@Column(name = "ISOK", length = 5)
	public String getIsok() {
		return this.isok;
	}

	public void setIsok(String isok) {
		this.isok = isok;
	}

	@Column(name = "SAVE_TIME")
	public String getSaveTime() {
		return this.saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNC_TIME", length = 7)
	@JSON(format = DateTimeFmtSpec.DATETIME)
	public Date getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	@Column(name = "SYNC_STATE", length = 7)
	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}
	@Column(name = "REAL_STATE", length = 32)
	public String getRealState() {
		return realState;
	}

	public void setRealState(String realState) {
		this.realState = realState;
	}
}
