package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "CC_ESCORT_INFO", schema = "SQJZ")
public class FXRYEscortInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String policeName;
	private String policeOffice;
	private String job;
	private Date escortDate;

	// Constructors

	/** default constructor */
	public FXRYEscortInfo() {
	}

	/** full constructor */
	public FXRYEscortInfo(String fxryId, String policeName,
			String policeOffice, String job, Date escortDate) {
		this.fxryId = fxryId;
		this.policeName = policeName;
		this.policeOffice = policeOffice;
		this.job = job;
		this.escortDate = escortDate;
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

	@Column(name = "POLICE_NAME", length = 60)
	public String getPoliceName() {
		return this.policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	@Column(name = "POLICE_OFFICE", length = 150)
	public String getPoliceOffice() {
		return this.policeOffice;
	}

	public void setPoliceOffice(String policeOffice) {
		this.policeOffice = policeOffice;
	}

	@Column(name = "JOB", length = 60)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ESCORT_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getEscortDate() {
		return this.escortDate;
	}

	public void setEscortDate(Date escortDate) {
		this.escortDate = escortDate;
	}

}