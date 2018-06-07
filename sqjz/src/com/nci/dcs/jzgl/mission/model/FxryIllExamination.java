package com.nci.dcs.jzgl.mission.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CcAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_ILL_EXAMINATION", schema = "SQJZ")
public class FxryIllExamination implements java.io.Serializable {

	// Fields

	private String id;
	private Date time;
	private String period;
	private Date createtime;
	private String createuser;

	// Constructors

	/** default constructor */
	public FxryIllExamination() {
	}

	/** full constructor */
	public FxryIllExamination(String id, Date time, String period, Date createtime,
			String createuser) {
		this.id = id;
		this.time = time;
		this.period = period;
		this.createtime = createtime;
		this.createuser = createuser;
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
	@Column(name = "TIME")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name = "PERIOD")
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name = "CREATEUSER")
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
}