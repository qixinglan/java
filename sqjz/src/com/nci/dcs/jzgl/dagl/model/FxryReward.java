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

/**
 * CcFxryReward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_FXRY_REWARD", schema = "SQJZ")
public class FxryReward implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryid;
	private String reward;
	private Date rewarddate;
	private String rewardorg;
	private String reason;
	private String info;
	private String recordOrgId;

	// Property accessors
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRYID", nullable = false, length = 32)
	public String getFxryid() {
		return this.fxryid;
	}

	public void setFxryid(String fxryid) {
		this.fxryid = fxryid;
	}

	@Column(name = "REWARD", length = 150)
	public String getReward() {
		return this.reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REWARDDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getRewarddate() {
		return this.rewarddate;
	}

	public void setRewarddate(Date rewarddate) {
		this.rewarddate = rewarddate;
	}

	@Column(name = "REWARDORG", length = 300)
	public String getRewardorg() {
		return this.rewardorg;
	}

	public void setRewardorg(String rewardorg) {
		this.rewardorg = rewardorg;
	}

	@Column(name = "REASON", length = 600)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "INFO", length = 600)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

}