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

@Entity
@Table(name = "VIEW_FXRY_TRANSFER_INFO", schema = "SQJZ")
public class ViewFxryTransferInfo implements java.io.Serializable {

	// Fields

		private String id;
		private String name;
		private String code;
		private String state;
		private String identityCard;
		private String sex;
		private String fxryId;
		private String outOrgId;
		private Date outTime;
		private Date receiveTime;
		private String receiveOrgId;
		private String reason;
		private String receiveOrgName;
		private String connectName;
		private String connectPhone;
		private String affixId;
		private Integer transtatus;
		private String responseOrg;
		private Date reportTime;
		private String isTgry;
		@Column(name = "ISTGRY")
		public String getIsTgry() {
			return isTgry;
		}

		public void setIsTgry(String isTgry) {
			this.isTgry = isTgry;
		}
		// Constructors

		/** default constructor */
		public ViewFxryTransferInfo() {
		}

		/** minimal constructor */
		public ViewFxryTransferInfo(String id) {
			this.id = id;
		}

		/** full constructor */
		public ViewFxryTransferInfo(String id, String name, String code,
				String identityCard, String sex, String fxryid, String outOrgId,
				Date outTime, Date receiveTime, String receiveOrgId, String reason,
				String receiveOrgName, String connectName, String connectPhone,
				String affixId, Integer transtatus) {
			this.id = id;
			this.name = name;
			this.code = code;
			this.identityCard = identityCard;
			this.sex = sex;
			this.fxryId = fxryid;
			this.outOrgId = outOrgId;
			this.outTime = outTime;
			this.receiveTime = receiveTime;
			this.receiveOrgId = receiveOrgId;
			this.reason = reason;
			this.receiveOrgName = receiveOrgName;
			this.connectName = connectName;
			this.connectPhone = connectPhone;
			this.affixId = affixId;
			this.transtatus = transtatus;
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

		@Column(name = "OUT_ORG_ID", length = 32)
		public String getOutOrgId() {
			return this.outOrgId;
		}

		public void setOutOrgId(String outOrgId) {
			this.outOrgId = outOrgId;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "OUT_TIME", length = 7)
		@JSON(format=DateTimeFmtSpec.MINUTES)
		public Date getOutTime() {
			return this.outTime;
		}

		public void setOutTime(Date outTime) {
			this.outTime = outTime;
		}

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "RECEIVE_TIME", length = 7)
		@JSON(format=DateTimeFmtSpec.MINUTES)
		public Date getReceiveTime() {
			return this.receiveTime;
		}

		public void setReceiveTime(Date receiveTime) {
			this.receiveTime = receiveTime;
		}

		@Column(name = "RECEIVE_ORG_ID", length = 32)
		public String getReceiveOrgId() {
			return this.receiveOrgId;
		}

		public void setReceiveOrgId(String receiveOrgId) {
			this.receiveOrgId = receiveOrgId;
		}

		@Column(name = "REASON", length = 300)
		public String getReason() {
			return this.reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		@Column(name = "RECEIVE_ORG_NAME", length = 150)
		public String getReceiveOrgName() {
			return this.receiveOrgName;
		}

		public void setReceiveOrgName(String receiveOrgName) {
			this.receiveOrgName = receiveOrgName;
		}

		@Column(name = "CONNECT_NAME", length = 60)
		public String getConnectName() {
			return this.connectName;
		}

		public void setConnectName(String connectName) {
			this.connectName = connectName;
		}

		@Column(name = "CONNECT_PHONE", length = 30)
		public String getConnectPhone() {
			return this.connectPhone;
		}

		public void setConnectPhone(String connectPhone) {
			this.connectPhone = connectPhone;
		}

		@Column(name = "AFFIX_ID", length = 32)
		public String getAffixId() {
			return this.affixId;
		}

		public void setAffixId(String affixId) {
			this.affixId = affixId;
		}

		@Column(name = "TRANSTATUS", precision = 22, scale = 0)
		public Integer getTranstatus() {
			return this.transtatus;
		}

		public void setTranstatus(Integer transtatus) {
			this.transtatus = transtatus;
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
		
	    @Temporal(TemporalType.DATE)
	    @Column(name="REPORT_TIME", length=7)
	    @JSON(format=DateTimeFmtSpec.DATE)
		public Date getReportTime() {
			return reportTime;
		}


		public void setReportTime(Date reportTime) {
			this.reportTime = reportTime;
		}
}