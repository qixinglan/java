package com.nci.dcs.jzgl.education.model;

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

@Entity
@Table(name = "CC_EDUCATION_INFO", schema = "SQJZ")
public class EducationInfo implements java.io.Serializable {

	/**
	 * caolj
	 */
	private static final long serialVersionUID = -2107102472269517156L;
	
	private String id;
	private Date time;
	private String type;//期
	private String sfcy;//人员
	private String bz;//月份
	private Date createdate;
	private String creater;
	private String orgId;
	private String fxryId;
	private String personName;
	private String createTime;
	// Constructors

	public EducationInfo() {
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "TYPE", length = 60)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "CREATER", length = 32)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}
	
	@Transient
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@Transient
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}