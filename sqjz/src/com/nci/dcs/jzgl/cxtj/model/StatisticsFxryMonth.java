package com.nci.dcs.jzgl.cxtj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_STATISTICS_FXRY_MONTH", schema = "SQJZ")
public class StatisticsFxryMonth implements java.io.Serializable {

	private String id;
	private String orgid;
	private String year;
	private String month;
	private String superOrg;
	private Long byzcrs;

	@Id
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "ORGID", length = 32)
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	@Column(name = "YEAR", precision = 4, scale = 0)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "MONTH", precision = 2, scale = 0)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name = "SUPER_ORG", length = 32)
	public String getSuperOrg() {
		return superOrg;
	}

	public void setSuperOrg(String superOrg) {
		this.superOrg = superOrg;
	}
	@Column(name = "BYZCRS", precision = 22, scale = 0)
	public Long getByzcrs() {
		return byzcrs;
	}

	public void setByzcrs(Long byzcrs) {
		this.byzcrs = byzcrs;
	}

}