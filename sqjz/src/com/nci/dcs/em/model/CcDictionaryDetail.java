package com.nci.dcs.em.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CcDictionaryDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DICTIONARY_DETAIL", schema = "SQJZ")
public class CcDictionaryDetail implements java.io.Serializable {

	// Fields

	private String id;
	private CcDictionaryInfo ccDictionaryInfo;
	private String code;
	private String name;
	private Boolean isusing;
	private String parentId;
	private Integer rank;

	// Constructors

	/** default constructor */
	public CcDictionaryDetail() {
	}

	/** minimal constructor */
	public CcDictionaryDetail(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcDictionaryDetail(String id, CcDictionaryInfo ccDictionaryInfo,
			String code, String name, Boolean isusing, String parentId,
			Integer rank) {
		this.id = id;
		this.ccDictionaryInfo = ccDictionaryInfo;
		this.code = code;
		this.name = name;
		this.isusing = isusing;
		this.parentId = parentId;
		this.rank = rank;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAJOR_ID")
	public CcDictionaryInfo getCcDictionaryInfo() {
		return this.ccDictionaryInfo;
	}

	public void setCcDictionaryInfo(CcDictionaryInfo ccDictionaryInfo) {
		this.ccDictionaryInfo = ccDictionaryInfo;
	}

	@Column(name = "CODE", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISUSING", precision = 1, scale = 0)
	public Boolean getIsusing() {
		return this.isusing;
	}

	public void setIsusing(Boolean isusing) {
		this.isusing = isusing;
	}

	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "RANK", precision = 8, scale = 0)
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}