package com.nci.dcs.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.TrueFalseType;

import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

/**
 * CcIgnorePerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_IGNORE_PERSON", schema = "SQJZ")
public class CcIgnorePerson implements java.io.Serializable {

	// Fields

	
	private String id;
	private String personId;
	private String personName;
	private String ignoreId;
	private CcGatherAlarmIgnore ccGatherAlarmIgnore;
	
	// Constructors

	/** default constructor */
	public CcIgnorePerson() {
	}


	@Column(name = "PERSON_ID", nullable = true, length = 32)
	public String getPersonId() {
		return personId;
	}



	public void setPersonId(String personId) {
		this.personId = personId;
	}


	@Column(name = "Person_Name", nullable = true, length = 60)
	public String getPersonName() {
		return personName;
	}


	
	public void setPersonName(String personName) {
		this.personName = personName;
	}


	@Column(name = "IGNORE_ID", nullable = true, length = 32)
	public String getIgnoreId() {
		return ignoreId;
	}


   
	public void setIgnoreId(String ignoreId) {
		this.ignoreId = ignoreId;
	}



	public void setId(String id) {
		this.id = id;
	}
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IGNORE_ID", nullable = false, insertable = false, updatable = false)
	public CcGatherAlarmIgnore getCcGatherAlarmIgnore() {
		return this.ccGatherAlarmIgnore;
	}

	public void setCcGatherAlarmIgnore(CcGatherAlarmIgnore ccGatherAlarmIgnore) {
		this.ccGatherAlarmIgnore = ccGatherAlarmIgnore;
	}

	

}