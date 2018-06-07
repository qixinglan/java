package com.nci.sfj.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcAlarmInfoViewId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_SFJ_TJDATA_NOTGRY", schema = "SQJZ")
public class ViewSfjTjxxNoTgry implements java.io.Serializable {

	// Fields
	@Id
	@Column(name = "ORG_ID")
	private String orgId;
	@Column(name = "ORG_NAME")
	private String cname;
	@Column(name = "ZJRY")
	private Integer zjry = new Integer(0);
	@Column(name = "JKRY")
	private Integer jkry = new Integer(0);
	@Column(name = "WCLBJ")
	private Integer wclbj = new Integer(0);
	@Column(name = "LOC_X")
	private String gisx;
	@Column(name = "LOC_Y")
	private String gisy;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getZjry() {
		return zjry;
	}

	public void setZjry(Integer zjry) {
		this.zjry = zjry;
	}

	public Integer getJkry() {
		return jkry;
	}

	public void setJkry(Integer jkry) {
		this.jkry = jkry;
	}

	public Integer getWclbj() {
		return wclbj;
	}

	public void setWclbj(Integer wclbj) {
		this.wclbj = wclbj;
	}

	public String getGisx() {
		return gisx;
	}

	public void setGisx(String gisx) {
		this.gisx = gisx;
	}

	public String getGisy() {
		return gisy;
	}

	public void setGisy(String gisy) {
		this.gisy = gisy;
	}

}