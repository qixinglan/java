package com.nci.dcs.jzgl.mission.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcVacateInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MISSION_INFO", schema = "SQJZ")
public class MissionInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8794375700500421199L;
	private String id;
	private String fxryId;
	private Date missionStart;
	private Date missionEnd;
	private Date accomplishDate;
	private String missionType;
	private String personId;
	private String personName;
	private String sfcy;//人员
	private String bz;//月份
    private String createTime;
    private String reportDateTime;
	private Date createDate;
	
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

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MISSION_PERIOD_STA", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getMissionStart() {
		return missionStart;
	}

	public void setMissionStart(Date missionStart) {
		this.missionStart = missionStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MISSION_PERIOD_END", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getMissionEnd() {
		return missionEnd;
	}

	public void setMissionEnd(Date missionEnd) {
		this.missionEnd = missionEnd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCOMPLISH_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getAccomplishDate() {
		return accomplishDate;
	}

	public void setAccomplishDate(Date accomplishDate) {
		this.accomplishDate = accomplishDate;
	}

	@Column(name = "PERSON_NAME", length = 32)
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "MISSION_TYPE", length = 32)
	public String getMissionType() {
		return missionType;
	}

	public void setMissionType(String missionType) {
		this.missionType = missionType;
	}
	
	@Column(name = "SFCY", length = 60)
	public String getSfcy() {
		return this.sfcy;
	}

	public void setSfcy(String sfcy) {
		this.sfcy = sfcy;
	}
	
	@Column(name = "BZ", length = 600)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Transient
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(String reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATETIME)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}