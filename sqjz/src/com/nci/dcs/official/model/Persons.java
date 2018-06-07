package com.nci.dcs.official.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.system.model.User;

/**
 * CcPersons entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_PERSONS", schema = "SQJZ")
public class Persons implements java.io.Serializable {

	// Fields

	private String id;
	private String persontype;
	private String name;
	private String sex;
	private String nation;
	private String polityvisage;
	private String degree;
	private Date birthday;
	private String adress;
	private String nativeplace;
	private String phone;
	private OrganizationInfo org;
	private String oldorg;
	private String zjtype;
	private String zjcode;
	private Integer orderid;
	private String description;
	private String duty;
	private String sfzg;
	private User user;
	private String workPhone;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PERSONTYPE", length = 30)
	public String getPersontype() {
		return this.persontype;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "NATION", length = 30)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "POLITYVISAGE", length = 30)
	public String getPolityvisage() {
		return this.polityvisage;
	}

	public void setPolityvisage(String polityvisage) {
		this.polityvisage = polityvisage;
	}

	@Column(name = "DEGREE", length = 30)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ADRESS", length = 300)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "NATIVEPLACE", length = 300)
	public String getNativeplace() {
		return this.nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	@Column(name = "PHONE", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ZJTYPE", length = 30)
	public String getZjtype() {
		return this.zjtype;
	}

	public void setZjtype(String zjtype) {
		this.zjtype = zjtype;
	}

	@Column(name = "ZJCODE", length = 50)
	public String getZjcode() {
		return this.zjcode;
	}

	public void setZjcode(String zjcode) {
		this.zjcode = zjcode;
	}

	@Column(name = "ORDERID", precision = 6, scale = 0)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "DESCRIPTION", length = 900)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DUTY", length = 30)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "SFZG", length = 30)
	public String getSfzg() {
		return sfzg;
	}

	public void setSfzg(String sfzg) {
		this.sfzg = sfzg;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_ID")
	public OrganizationInfo getOrg() {
		return org;
	}

	public void setOrg(OrganizationInfo org) {
		this.org = org;
	}

	@Column(name = "OLDORG")
	public String getOldorg() {
		return oldorg;
	}

	public void setOldorg(String oldorg) {
		this.oldorg = oldorg;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE,mappedBy="person")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@JoinColumn(name = "WORKPHONE")
	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
}