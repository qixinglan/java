package com.nci.dcs.jzgl.dagl.docModel;

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

/**
 * ViewCcDoc8Id entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_8", schema = "SQJZ")
public class ViewCcDoc8 implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String name;
	private String cname;
	private String address;
	private Date reportTime;
	private String person;
	private String phone;
	//转成字符类型的报到时间
	private String report_Time;

	// Constructors
	@Transient
	public String getReport_Time() {
		return report_Time;
	}

	public void setReport_Time(String report_Time) {
		this.report_Time = report_Time;
	}

	/** default constructor */
	public ViewCcDoc8() {
	}

	/** minimal constructor */
	public ViewCcDoc8(String id) {
		this.id = id;
	}

	/** full constructor */
	public ViewCcDoc8(String id, String fxryId, String name, String cname,
			String address, Date reportTime, String person, String phone) {
		this.id = id;
		this.fxryId = fxryId;
		this.name = name;
		this.cname = cname;
		this.address = address;
		this.reportTime = reportTime;
		this.person = person;
		this.phone = phone;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", length = 32)
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

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPORT_TIME", length = 7)
	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "PERSON", length = 60)
	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}