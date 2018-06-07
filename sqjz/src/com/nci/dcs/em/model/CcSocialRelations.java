package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;

/**
 * CcSocialRelations entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_SOCIAL_RELATIONS", schema = "SQJZ")
public class CcSocialRelations implements java.io.Serializable {

	// Fields

	private String relationId;
	private CcFxryBaseinfo ccFxryBaseinfo;
	private String name;
	private String relation;
	private String address;
	private String phoneNumber;

	// Constructors

	/** default constructor */
	public CcSocialRelations() {
	}

	/** minimal constructor */
	public CcSocialRelations(String relationId) {
		this.relationId = relationId;
	}

	/** full constructor */
	public CcSocialRelations(String relationId, CcFxryBaseinfo ccFxryBaseinfo,
			String name, String relation, String address, String phoneNumber) {
		this.relationId = relationId;
		this.ccFxryBaseinfo = ccFxryBaseinfo;
		this.name = name;
		this.relation = relation;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	// Property accessors
	@Id
	@Column(name = "RELATION_ID", unique = true, nullable = false, length = 32)
	public String getRelationId() {
		return this.relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	public CcFxryBaseinfo getCcFxryBaseinfo() {
		return this.ccFxryBaseinfo;
	}

	public void setCcFxryBaseinfo(CcFxryBaseinfo ccFxryBaseinfo) {
		this.ccFxryBaseinfo = ccFxryBaseinfo;
	}

	@Column(name = "NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RELATION", length = 64)
	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE_NUMBER", length = 32)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}