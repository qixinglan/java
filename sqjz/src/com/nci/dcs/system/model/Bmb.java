package com.nci.dcs.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门表
 * 
 * @author zzn
 * 
 */
@Entity
@Table(name = "CC_ORGANIZATION_INFO")
public class Bmb implements Serializable{
	@Id
	@Column(name = "org_id")
	private String bm;
	@Column(name = "CNAME")
	private String mc;
	@Column(name = "POSTAL_CODE")
	private String postalCode;
	@Column(name = "ORG_TYPE")
	private String orgType;
	@Column(name = "SUP_ORG_ID")
	private String supOrg;
	/**
	 * 1为机构，0为部门
	 * */
	@Column(name = "IS_UNIT")
	private String unit;
	/**
	 * 1为司法部同步，本系统留空
	 * */
	@Column(name = "SOURCE")
	private String source;

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * Description:判断是否是司法所
	 */
	public boolean isSfs() {
		return "3".equals(orgType) ? true : false;
	}

	/**
	 * Description:判断是否是区县司法局
	 */
	public boolean isQxsfj() {
		return "2".equals(orgType) ? true : false;
	}
	/**
	 * Description:判断是否是市局
	 *
	 * @author Shuzz
	 * @return
	 * @since 2014年10月10日上午10:17:27
	 */
	public boolean isSj() {
		return "1".equals(orgType) ? true : false;
	}
	public String getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(String supOrg) {
		this.supOrg = supOrg;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
