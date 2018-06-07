package com.nci.dcs.system.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CC_DICTIONARY_INFO",schema="SQJZ")
public class DictionaryInfo implements java.io.Serializable {
	private String id;
	private String code;
	private String name;
	private Integer using;
	private Integer rank;

	private Set<DictionaryDetail> dictionaryDetails = new HashSet<DictionaryDetail>(0);

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

	@Column(name = "MAJOR_CODE", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "MAJOR_NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISUSING")
	public Integer getUsing() {
		return this.using;
	}

	public void setUsing(Integer using) {
		this.using = using;
	}

	@Column(name = "RANK")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	 mappedBy="dictionaryInfo")
	 @OrderBy("rank,code")
	 public Set<DictionaryDetail> getDictionaryDetails() {
	 return this.dictionaryDetails;
	 }
	
	 public void setDictionaryDetails(Set<DictionaryDetail> dictionaryDetails)
	 {
	 this.dictionaryDetails = dictionaryDetails;
	 }
	 
	 @Transient
	 public int getRankValue(){
		 return this.rank == null ? 0 : this.rank.intValue();
	 }
	 
	 public boolean IsUsing(){
		 return this.using == null || this.using.intValue() == 0 ? false : true;
	 }
}