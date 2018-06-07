package com.nci.dcs.em.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CcDictionaryInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DICTIONARY_INFO", schema = "SQJZ")
public class CcDictionaryInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String majorCode;
	private String majorName;
	private Boolean isusing;
	private Integer rank;
	private Set<CcDictionaryDetail> ccDictionaryDetails = new HashSet<CcDictionaryDetail>(
			0);

	// Constructors

	/** default constructor */
	public CcDictionaryInfo() {
	}

	/** minimal constructor */
	public CcDictionaryInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcDictionaryInfo(String id, String majorCode, String majorName,
			Boolean isusing, Integer rank,
			Set<CcDictionaryDetail> ccDictionaryDetails) {
		this.id = id;
		this.majorCode = majorCode;
		this.majorName = majorName;
		this.isusing = isusing;
		this.rank = rank;
		this.ccDictionaryDetails = ccDictionaryDetails;
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

	@Column(name = "MAJOR_CODE", length = 32)
	public String getMajorCode() {
		return this.majorCode;
	}

	public void setMajorCode(String majorCode) {
		this.majorCode = majorCode;
	}

	@Column(name = "MAJOR_NAME", length = 64)
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "ISUSING", precision = 1, scale = 0)
	public Boolean getIsusing() {
		return this.isusing;
	}

	public void setIsusing(Boolean isusing) {
		this.isusing = isusing;
	}

	@Column(name = "RANK", precision = 8, scale = 0)
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ccDictionaryInfo")
	public Set<CcDictionaryDetail> getCcDictionaryDetails() {
		return this.ccDictionaryDetails;
	}

	public void setCcDictionaryDetails(
			Set<CcDictionaryDetail> ccDictionaryDetails) {
		this.ccDictionaryDetails = ccDictionaryDetails;
	}

}