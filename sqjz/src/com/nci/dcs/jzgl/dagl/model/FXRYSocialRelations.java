package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;

@Entity
@Table(name = "CC_SOCIAL_RELATIONS", schema = "SQJZ")
public class FXRYSocialRelations implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	//private FXRYBaseinfo fxryBaseinfo;
	private String name;
	private String relation;
	private String address;
	private String phoneNumber;
	private Date createTime;//创建时间

	public FXRYSocialRelations() {
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	@Column(name = "ID", length = 32)
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return fxryId;
	}
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "FXRY_ID")
//	public FXRYBaseinfo getFxryBaseinfo() {
//		return this.fxryBaseinfo;
//	}
//
//	public void setFxryBaseinfo(FXRYBaseinfo fxryBaseinfo) {
//		this.fxryBaseinfo = fxryBaseinfo;
//	}

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

	@Column(name = "RELATION", length = 60)
	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Column(name = "ADDRESS", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE_NUMBER", length = 30)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(serialize=false, deserialize=false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}