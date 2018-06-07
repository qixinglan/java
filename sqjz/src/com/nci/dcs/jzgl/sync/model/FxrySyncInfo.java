package com.nci.dcs.jzgl.sync.model;

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

/**
 * CcFxrySyncInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_SYNC_INFO", schema = "SQJZ")
public class FxrySyncInfo implements java.io.Serializable {
	// Fields

	private String id;
	private String fxryId;
	private String saveTime;
	private Date syncTime;
	private Date syncDataTime;
	private String state;
	private String description;
	private String syncOrg;
	private String syncState;
	private String realState;

	// Property accessors
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
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

	@Column(name = "STATE", length = 150)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "DESCRIPTION", length = 1500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNC_DATA_TIME", length = 7)
	public Date getSyncDataTime() {
		return syncDataTime;
	}

	public void setSyncDataTime(Date syncDataTime) {
		this.syncDataTime = syncDataTime;
	}

	@Column(name = "SYNC_ORG", length = 32)
	public String getSyncOrg() {
		return syncOrg;
	}

	public void setSyncOrg(String syncOrg) {
		this.syncOrg = syncOrg;
	}

	@Column(name = "SYNC_STATE", length = 32)
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
