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
 * CcImprisonmentChange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_IMPRISONMENT_CHANGE", schema = "SQJZ")
public class FXRYImprisonmentChange implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String changeType;
	private Date changeDate;
	private String range;
	private String reason;

	// Constructors

	/** default constructor */
	public FXRYImprisonmentChange() {
	}

	/** full constructor */
	public FXRYImprisonmentChange(String fxryId, String changeType,
			Date changeDate, String range, String reason) {
		this.fxryId = fxryId;
		this.changeType = changeType;
		this.changeDate = changeDate;
		this.range = range;
		this.reason = reason;
	}

	// Property accessors
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

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "CHANGE_TYPE", length = 30)
	public String getChangeType() {
		return this.changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHANGE_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	@Column(name = "RANGE", length = 60)
	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Column(name = "REASON", length = 300)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}