package com.nci.dcs.jzgl.dagl.docModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc30 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_30", schema = "SQJZ")
public class ViewCcDoc30 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String userdName;
	private String sex;
	private Date birthdate;
	private String educationDegree;
	private String nation;
	private String homeAddress;
	private String houseAddress;
	private String accusation;
	private String oriPeriod;
	private String addpunishment;
	private String forbidden;
	private Date dateSForbidden;
	private Date dateEForbidden;
	private String adjustType;
	private String adjustPeriod;
	private Date dateSAdjust;
	private Date dateEAdjust;

	// Constructors

	/** default constructor */
	public ViewCcDoc30() {
	}
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
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

	@Column(name = "USERD_NAME", length = 60)
	public String getUserdName() {
		return this.userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	@Column(name = "SEX", length = 128)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDATE", length = 7)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "EDUCATION_DEGREE", length = 128)
	public String getEducationDegree() {
		return this.educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	@Column(name = "NATION", length = 128)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "HOME_ADDRESS", length = 1500)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "HOUSE_ADDRESS", length = 1500)
	public String getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	@Column(name = "ACCUSATION", length = 150)
	public String getAccusation() {
		return this.accusation;
	}

	public void setAccusation(String accusation) {
		this.accusation = accusation;
	}

	@Column(name = "ORI_PERIOD", length = 150)
	public String getOriPeriod() {
		return this.oriPeriod;
	}

	public void setOriPeriod(String oriPeriod) {
		this.oriPeriod = oriPeriod;
	}

	@Column(name = "ADDPUNISHMENT", length = 30)
	public String getAddpunishment() {
		return this.addpunishment;
	}

	public void setAddpunishment(String addpunishment) {
		this.addpunishment = addpunishment;
	}

	@Column(name = "FORBIDDEN", length = 300)
	public String getForbidden() {
		return this.forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_FORBIDDEN", length = 7)
	public Date getDateSForbidden() {
		return this.dateSForbidden;
	}

	public void setDateSForbidden(Date dateSForbidden) {
		this.dateSForbidden = dateSForbidden;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_FORBIDDEN", length = 7)
	public Date getDateEForbidden() {
		return this.dateEForbidden;
	}

	public void setDateEForbidden(Date dateEForbidden) {
		this.dateEForbidden = dateEForbidden;
	}

	@Column(name = "ADJUST_TYPE", length = 128)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ADJUST_PERIOD", length = 150)
	public String getAdjustPeriod() {
		return this.adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_S_ADJUST", length = 7)
	public Date getDateSAdjust() {
		return this.dateSAdjust;
	}

	public void setDateSAdjust(Date dateSAdjust) {
		this.dateSAdjust = dateSAdjust;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_E_ADJUST", length = 7)
	public Date getDateEAdjust() {
		return this.dateEAdjust;
	}

	public void setDateEAdjust(Date dateEAdjust) {
		this.dateEAdjust = dateEAdjust;
	}
}