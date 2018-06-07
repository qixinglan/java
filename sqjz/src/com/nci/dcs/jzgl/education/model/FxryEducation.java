package com.nci.dcs.jzgl.education.model;

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

@Entity
@Table(name = "CC_FXRY_EDUCATION", schema = "SQJZ")
public class FxryEducation implements java.io.Serializable {

	/**
	 * @name 
	 * @author caolj
	 * @date 2015年4月1日 下午3:46:01
	 * @message:
	 */
	private static final long serialVersionUID = -8362744574439222566L;
	
	private String id;
	private String stage;//期
	private Long persons;//人员
	private Date month;//月份
	private Date createdate;
	private String creater;
	private String orgId;
	// Constructors

	public FxryEducation() {
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
	
	@Column(name = "STAGE", length = 30)
	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Column(name = "PERSONS", length = 30)
	public Long getPersons() {
		return this.persons;
	}

	public void setPersons(Long persons) {
		this.persons = persons;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MONTH", length = 7)
	@JSON(format=DateTimeFmtSpec.MONTH)
	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
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
}