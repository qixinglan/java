package com.nci.dcs.official.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * Description:机构信息
 * 
 * @author MyEclipse Persistence Tools
 * @since 2014年5月23日下午2:06:26
 */
@Entity
@Table(name = "CC_ORGANIZATION_INFO", schema = "SQJZ")
public class OrganizationInfo implements java.io.Serializable {

	// Fields

	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 机构缩写
	 */
	private String orgName;
	/**
	 * 机构名称
	 */
	private String cname;
	/**
	 * 机构地址
	 */
	private String address;
	/**
	 * 机构设置情况
	 */
	private String description;
	/**
	 * 机构代码
	 */
	private String orgCode;
	/**
	 * 机构类别
	 */
	private String orgType;
	/**
	 * 机构状态
	 */
	private String orgStatus;
	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 固定电话
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private Integer postalCode;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 子机构
	 */
	private Set<OrganizationInfo> subOrg = new HashSet<OrganizationInfo>(0);
	/**
	 * 上级机构
	 */
	private OrganizationInfo supOrg;
	/**
	 * 人员
	 */
	private Set<Persons> persons = new HashSet<Persons>(0);

	// 定位信息
	private Double locX;
	private Double locY;
	/**
	 * 1为机构，0为部门
	 * */
	private String unit;
	/**
	 * 1为司法部同步，本系统留空
	 * */
	private String source;

	private String rank;

	// Constructors

	/** default constructor */
	public OrganizationInfo() {
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "CNAME", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ORG_CODE", length = 32)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_TYPE", length = 5)
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "ORG_STATUS", length = 30)
	public String getOrgStatus() {
		return this.orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	@Column(name = "FAX", length = 32)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUP_ORG_ID")
	@JSON(serialize = false)
	public OrganizationInfo getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(OrganizationInfo supOrg) {
		this.supOrg = supOrg;
	}

	@Column(name = "PHONE", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "POSTAL_CODE", precision = 8, scale = 0)
	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "supOrg")
	@JSON(serialize = false)
	public Set<OrganizationInfo> getSubOrg() {
		return subOrg;
	}

	public void setSubOrg(Set<OrganizationInfo> subOrg) {
		this.subOrg = subOrg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "org")
	@JSON(serialize = false)
	public Set<Persons> getPersons() {
		return persons;
	}

	public void setPersons(Set<Persons> persons) {
		this.persons = persons;
	}

	@Column(name = "LOC_X", precision = 15, scale = 4)
	public Double getLocX() {
		return this.locX;
	}

	public void setLocX(Double locX) {
		this.locX = locX;
	}

	@Column(name = "LOC_Y", precision = 15, scale = 4)
	public Double getLocY() {
		return this.locY;
	}

	public void setLocY(Double locY) {
		this.locY = locY;
	}

	@Column(name = "IS_UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "RANK")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	//上级机构名称
	private String supOrgCname;
	@Transient
	public String getSupOrgCname() {
		return supOrgCname;
	}

	public void setSupOrgCname(String supOrgCname) {
		this.supOrgCname = supOrgCname;
	}
}