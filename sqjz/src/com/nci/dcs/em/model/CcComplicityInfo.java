package com.nci.dcs.em.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcComplicityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_COMPLICITY_INFO", schema = "SQJZ")
public class CcComplicityInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String personId;
	private String name;
	private String sex;
	private Date birthday;
	private String accusation;
	private String punishmentAddress;

	// Constructors

	/** default constructor */
	public CcComplicityInfo() {
	}

	/** minimal constructor */
	public CcComplicityInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcComplicityInfo(String id, String personId, String name,
			String sex, Date birthday, String accusation,
			String punishmentAddress) {
		this.id = id;
		this.personId = personId;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.accusation = accusation;
		this.punishmentAddress = punishmentAddress;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PERSON_ID", length = 32)
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "NAME", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ACCUSATION", length = 128)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "PUNISHMENT_ADDRESS", length = 128)
	public String getPunishmentAddress() {
		return this.punishmentAddress;
	}

	public void setPunishmentAddress(String punishmentAddress) {
		this.punishmentAddress = punishmentAddress;
	}

}