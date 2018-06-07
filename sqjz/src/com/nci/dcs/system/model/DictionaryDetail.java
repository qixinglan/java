package com.nci.dcs.system.model;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * DictionaryDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DICTIONARY_DETAIL")
public class DictionaryDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;
	private DictionaryInfo dictionaryInfo;
	private Integer using;
	private String parentID;
	private Integer rank;

	public DictionaryDetail() {
	}

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

	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="MAJOR_ID")
	 public DictionaryInfo getDictionaryInfo() {
	 return this.dictionaryInfo;
	 }
	
	 public void setDictionaryInfo(DictionaryInfo dictionaryInfo) {
	 this.dictionaryInfo = dictionaryInfo;
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
	public Integer getUsing() {
		return this.using;
	}

	public void setUsing(Integer using) {
		this.using = using;
	}

	@Column(name = "PARENT_ID", length = 32)
	public String getParentID() {
		return this.parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Column(name = "RANK", precision = 8, scale = 0)
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Transient
	public int getRankValue() {
		return this.rank == null ? 0 : this.rank.intValue();
	}

	public boolean IsUsing() {
		return this.using == null || this.using.intValue() == 0 ? false : true;
	}
}