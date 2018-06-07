package com.nci.dcs.jzgl.dagl.docModel;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc35 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_35", schema = "SQJZ")
public class ViewCcDoc35 implements java.io.Serializable {

	// Fields



	// Constructors

	/** default constructor */
	public ViewCcDoc35() {
	}



	private String id;
	private String name;
	private String writNumber;
	private String writType;
	private String adjustType;
	private String adjustCode;
	private Date dateSAdjust;
	private Date dateEAdjust;
	private String opinion;
	private String dateS_Adjust;
	private String dateE_Adjust;

	/** full constructor */
	public ViewCcDoc35(String id, String name, String writNumber,
			String writType, String adjustType, String adjustCode,
			Date dateSAdjust, Date dateEAdjust, String opinion) {
		this.id = id;
		this.name = name;
		this.writNumber = writNumber;
		this.writType = writType;
		this.adjustType = adjustType;
		this.adjustCode = adjustCode;
		this.dateSAdjust = dateSAdjust;
		this.dateEAdjust = dateEAdjust;
		this.opinion = opinion;
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

	@Column(name = "WRIT_NUMBER", length = 100)
	public String getWritNumber() {
		return this.writNumber;
	}

	public void setWritNumber(String writNumber) {
		this.writNumber = writNumber;
	}

	@Column(name = "WRIT_TYPE", length = 128)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Column(name = "ADJUST_TYPE", length = 128)
	public String getAdjustType() {
		return this.adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	@Column(name = "ADJUST_CODE", length = 30)
	public String getAdjustCode() {
		return this.adjustCode;
	}

	public void setAdjustCode(String adjustCode) {
		this.adjustCode = adjustCode;
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

	@Column(name = "OPINION", length = 1500)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	@Transient
	public String getDateS_Adjust() {
		return dateS_Adjust;
	}

	public void setDateS_Adjust(String dateS_Adjust) {
		this.dateS_Adjust = dateS_Adjust;
	}
	@Transient
	public String getDateE_Adjust() {
		return dateE_Adjust;
	}

	public void setDateE_Adjust(String dateE_Adjust) {
		this.dateE_Adjust = dateE_Adjust;
	}
}