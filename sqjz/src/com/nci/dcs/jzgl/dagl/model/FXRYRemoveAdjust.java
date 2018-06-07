package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

/**
 * CcRemoveAdjust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_REMOVE_ADJUST", schema = "SQJZ")
public class FXRYRemoveAdjust implements java.io.Serializable {

	// Fields

	private String id;
	private String fxryId;
	private String removeReason;
	private Date removeDate;
	private String jailType;
	private Date jailDate;
	private String jailReason;
	private Date deathDate;
	private String deathReason;
	private String deathReasonDetail;
	private String performance;
	private String manner;
	private String istrained;
	private String hascertificate;
	private String speciality;
	private String issanwu;
	private String risk;
	private String familyContact;
	private String remark;
	private String recordOrgId;
	private Date createTime;
	/**
	 * 鉴定意见
	 */
	private String expertOpinion;

	// Constructors

	/** default constructor */
	public FXRYRemoveAdjust() {
	}

	/** full constructor */
	public FXRYRemoveAdjust(String fxryId, String removeReason,
			Date removeDate, String jailType, Date jailDate, String jailReason,
			Date deathDate, String deathReason, String deathReasonDetail,
			String performance, String manner, String istrained,
			String hascertificate, String speciality, String issanwu,
			String risk, String familyContact, String remark,
			String recordOrgId, Date createTime) {
		this.fxryId = fxryId;
		this.removeReason = removeReason;
		this.removeDate = removeDate;
		this.jailType = jailType;
		this.jailDate = jailDate;
		this.jailReason = jailReason;
		this.deathDate = deathDate;
		this.deathReason = deathReason;
		this.deathReasonDetail = deathReasonDetail;
		this.performance = performance;
		this.manner = manner;
		this.istrained = istrained;
		this.hascertificate = hascertificate;
		this.speciality = speciality;
		this.issanwu = issanwu;
		this.risk = risk;
		this.familyContact = familyContact;
		this.remark = remark;
		this.recordOrgId = recordOrgId;
		this.createTime = createTime;
	}

	public void copy(FXRYRemoveAdjust right) {
		this.removeReason = right.getRemoveReason();
		this.removeDate = right.getRemoveDate();
		this.jailType = right.getJailType();
		this.jailDate = right.getJailDate();
		this.jailReason = right.getJailReason();
		this.deathDate = right.getDeathDate();
		this.deathReason = right.getDeathReason();
		this.deathReasonDetail = right.getDeathReasonDetail();
		this.performance = right.getPerformance();
		this.manner = right.getManner();
		this.istrained = right.getIstrained();
		this.hascertificate = right.getHascertificate();
		this.speciality = right.getSpeciality();
		this.issanwu = right.getIssanwu();
		this.risk = right.getRisk();
		this.familyContact = right.getFamilyContact();
		this.remark = right.getRemark();
		this.expertOpinion = right.getExpertOpinion();
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FXRY_ID", length = 32)
	public String getFxryId() {
		return this.fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	@Column(name = "REMOVE_REASON", length = 30)
	public String getRemoveReason() {
		return this.removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REMOVE_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	@Column(name = "JAIL_TYPE", length = 30)
	public String getJailType() {
		return this.jailType;
	}

	public void setJailType(String jailType) {
		this.jailType = jailType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JAIL_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getJailDate() {
		return this.jailDate;
	}

	public void setJailDate(Date jailDate) {
		this.jailDate = jailDate;
	}

	@Column(name = "JAIL_REASON", length = 300)
	public String getJailReason() {
		return this.jailReason;
	}

	public void setJailReason(String jailReason) {
		this.jailReason = jailReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEATH_DATE", length = 7)
	@JSON(format = DateTimeFmtSpec.DATE)
	public Date getDeathDate() {
		return this.deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@Column(name = "DEATH_REASON", length = 30)
	public String getDeathReason() {
		return this.deathReason;
	}

	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}

	@Column(name = "DEATH_REASON_DETAIL", length = 150)
	public String getDeathReasonDetail() {
		return this.deathReasonDetail;
	}

	public void setDeathReasonDetail(String deathReasonDetail) {
		this.deathReasonDetail = deathReasonDetail;
	}

	@Column(name = "PERFORMANCE", length = 30)
	public String getPerformance() {
		return this.performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Column(name = "MANNER", length = 30)
	public String getManner() {
		return this.manner;
	}

	public void setManner(String manner) {
		this.manner = manner;
	}

	@Column(name = "ISTRAINED", precision = 1, scale = 0)
	public String getIstrained() {
		return this.istrained;
	}

	public void setIstrained(String istrained) {
		this.istrained = istrained;
	}

	@Column(name = "HASCERTIFICATE", precision = 1, scale = 0)
	public String getHascertificate() {
		return this.hascertificate;
	}

	public void setHascertificate(String hascertificate) {
		this.hascertificate = hascertificate;
	}

	@Column(name = "SPECIALITY", length = 300)
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "ISSANWU", precision = 1, scale = 0)
	public String getIssanwu() {
		return this.issanwu;
	}

	public void setIssanwu(String issanwu) {
		this.issanwu = issanwu;
	}

	@Column(name = "RISK", length = 30)
	public String getRisk() {
		return this.risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	@Column(name = "FAMILY_CONTACT", length = 30)
	public String getFamilyContact() {
		return this.familyContact;
	}

	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}

	@Column(name = "REMARK", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return this.recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(format = DateTimeFmtSpec.TIMESTAMP, serialize = false, deserialize = false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "OPINION", length = 1500)
	public String getExpertOpinion() {
		return expertOpinion;
	}

	public void setExpertOpinion(String expertOpinion) {
		this.expertOpinion = expertOpinion;
	}

}