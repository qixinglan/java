package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "VIEW_FXRY_UNCONTROL_HISTORY", schema = "SQJZ")
public class ViewFxryUncontrolHistory implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String state;
	private String identityCard;
	private String sex;
	private String fxryId;
	private Date removeDate;
	private String responseOrg;
    private String supOrgId;
    private String orgName;  
	// Constructors

	/** default constructor */
	public ViewFxryUncontrolHistory() {
	}

	/** minimal constructor */
	public ViewFxryUncontrolHistory(String id) {
		this.id = id;
	}

	/** full constructor */
	public ViewFxryUncontrolHistory(String id, String name, String code,
			String identityCard, String sex, String fxryid, Date removeDate,String supOrgId,String orgName) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.identityCard = identityCard;
		this.sex = sex;
		this.fxryId = fxryid;
		this.removeDate = removeDate;
		this.supOrgId=supOrgId;
		this.orgName=orgName;
	}

	// Property accessors
	@Id
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "IDENTITY_CARD", length = 18)
	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	@Column(name = "SEX", length = 30)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "FXRYID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryid) {
		this.fxryId = fxryid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	
	@Column(name = "state", length = 30)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "RESPONSE_ORG", length=32)
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "org_name", length = 100)
	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@Column(name = "sup_org_id", length = 32)
	public String getSupOrgId() {
		return supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}
}