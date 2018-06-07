package com.nci.dcs.jzgl.dagl.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * ViewFxryUnmanageHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_FXRY_UNMANAGE_HISTORY", schema = "SQJZ")
public class ViewFxryUnmanageHistory implements java.io.Serializable {

	// Fields

		private String id;
		private String name;
		private String code;
		private String identityCard;
		private String sex;
		private String state;
		private String isadult;
		private String phoneNumber;
		private String responseOrg;
		private String fxryId;
		private String adjustType;
		private String adjustPeriod;
		private Date dateSAdjust;
		private Date dateEAdjust;
		private String crimeType;
		private Date startDate;
		private Date endDate;
		private Integer adjustDays;
		private Integer remainDays;
		private String orgId;
		private String orgPath;
		private String orgName;
		private Integer reported;
        private String supOrgId;
        private String isTgry;
        
		// Constructors
    	@Column(name ="ISTGRY")
		public String getIsTgry() {
			return isTgry;
		}
		public void setIsTgry(String isTgry) {
			this.isTgry = isTgry;
		}
		/** default constructor */
		public ViewFxryUnmanageHistory() {
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

		@Column(name = "STATE", length = 30)
		public String getState() {
			return this.state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Column(name = "ISADULT", length = 30)
		public String getIsadult() {
			return this.isadult;
		}

		public void setIsadult(String isadult) {
			this.isadult = isadult;
		}

		@Column(name = "PHONE_NUMBER", length = 30)
		public String getPhoneNumber() {
			return this.phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		@Column(name = "RESPONSE_ORG", length = 32)
		public String getResponseOrg() {
			return this.responseOrg;
		}

		public void setResponseOrg(String responseOrg) {
			this.responseOrg = responseOrg;
		}

		@Column(name = "FXRY_ID", length = 32)
		public String getFxryId() {
			return this.fxryId;
		}

		public void setFxryId(String fxryId) {
			this.fxryId = fxryId;
		}

		@Column(name = "ADJUST_TYPE", length = 30)
		public String getAdjustType() {
			return this.adjustType;
		}

		public void setAdjustType(String adjustType) {
			this.adjustType = adjustType;
		}

		@Column(name = "ADJUST_PERIOD", precision = 8, scale = 0)
		public String getAdjustPeriod() {
			return this.adjustPeriod;
		}

		public void setAdjustPeriod(String adjustPeriod) {
			this.adjustPeriod = adjustPeriod;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "DATE_S_ADJUST", length = 7)
		@JSON(format=DateTimeFmtSpec.DATE)
		public Date getDateSAdjust() {
			return this.dateSAdjust;
		}

		public void setDateSAdjust(Date dateSAdjust) {
			this.dateSAdjust = dateSAdjust;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@JSON(format=DateTimeFmtSpec.DATE)
		@Column(name = "DATE_E_ADJUST", length = 7)
		public Date getDateEAdjust() {
			return this.dateEAdjust;
		}

		public void setDateEAdjust(Date dateEAdjust) {
			this.dateEAdjust = dateEAdjust;
		}

		@Column(name = "CRIME_TYPE", length = 30)
		public String getCrimeType() {
			return this.crimeType;
		}

		public void setCrimeType(String crimeType) {
			this.crimeType = crimeType;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "START_DATE", length = 7)
		@JSON(format=DateTimeFmtSpec.DATE)
		public Date getStartDate() {
			return this.startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "END_DATE", length = 7)
		@JSON(format=DateTimeFmtSpec.DATE)
		public Date getEndDate() {
			return this.endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		@Column(name = "REMAIN_DAYS")
		public Integer getRemainDays() {
			return this.remainDays;
		}

		public void setRemainDays(Integer remainDays) {
			this.remainDays = remainDays;
		}

		@Column(name = "ORG_ID", length = 5)
		public String getOrgId() {
			return this.orgId;
		}

		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}

		@Column(name = "ORG_PATH", length = 8)
		public String getOrgPath() {
			return this.orgPath;
		}

		public void setOrgPath(String orgPath) {
			this.orgPath = orgPath;
		}

		@Column(name = "ORG_NAME", length = 14)
		public String getOrgName() {
			return this.orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

		@Column(name = "REPORTED")
		public Integer getReported() {
			return this.reported;
		}

		public void setReported(Integer reported) {
			this.reported = reported;
		}

		@Column(name = "sup_org_id", length = 32)
		public String getSupOrgId() {
			return supOrgId;
		}

		public void setSupOrgId(String supOrgId) {
			this.supOrgId = supOrgId;
		}
		
		@Column(name = "ADJUST_DAYS")
		public Integer getAdjustDays() {
			return adjustDays;
		}
		public void setAdjustDays(Integer adjustDays) {
			this.adjustDays = adjustDays;
		}


}