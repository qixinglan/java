package com.nci.dcs.jzgl.dagl.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "CC_LEGAL_INSTRUMENT", schema = "SQJZ")
public class LegalInstrument implements java.io.Serializable {
	private String id;
	private String fxryId;//服刑人员ID
	private String investigateUnit;//侦查机关
	private Date dateDetention;//拘留日期
	private Date dateRecord;//立案日期
	private String permitArrestUnit;//决定批准逮捕机关
	private Date dateArrest;//逮捕日期
	private Date dateEndInvestigate;//侦结日期
	private String publicProsecution;//公诉机关(自诉人)
	private String indictmentNumber;//起诉书编号
	private Date dateIndictment;//提起起诉日期
	private String trialUnit;//审判机关名称
	private String judgmentNumber;//判决书编号
	private Date dateJudgment;//判决日期
	private String informNumber;//执行通知书文号
	private Date dateInform;//执行通知书日期
	private String orgdetentionAddress;//原羁押场所
	private Date dateExecute;//交付执行日期
	private String decideUnit;//社区矫正决定机关
	private Date writEffectiveDate;//文书生效日期
	private String writType;//文书类型
	private String writNumber;//文书编号
	private String majorCrime;//主要犯罪事实
	private String administrativePenalty;//行政处罚处分
	private String criminalPunshment;//刑事处罚
	private Date createTime;//创建时间
	private String recordOrgId;//维护机构

	public LegalInstrument() {
	}

	public LegalInstrument(String fxryId, String investigateUnit,
			Date dateDetention, Date dateRecord, String permitArrestUnit,
			Date dateArrest, Date dateEndInvestigate, String publicProsecution,
			String indictmentNumber, Date dateIndictment, String trialUnit,
			String judgmentNumber, Date dateJudgment, String informNumber,
			Date dateInform, String orgdetentionAddress, Date dateExecute,
			String decideUnit, Date writEffectiveDate, String writType,
			String writNumber, String majorCrime, String administrativePenalty,
			String criminalPunshment, Date createTime, String recordOrgId) {
		this.fxryId = fxryId;
		this.investigateUnit = investigateUnit;
		this.dateDetention = dateDetention;
		this.dateRecord = dateRecord;
		this.permitArrestUnit = permitArrestUnit;
		this.dateArrest = dateArrest;
		this.dateEndInvestigate = dateEndInvestigate;
		this.publicProsecution = publicProsecution;
		this.indictmentNumber = indictmentNumber;
		this.dateIndictment = dateIndictment;
		this.trialUnit = trialUnit;
		this.judgmentNumber = judgmentNumber;
		this.dateJudgment = dateJudgment;
		this.informNumber = informNumber;
		this.dateInform = dateInform;
		this.orgdetentionAddress = orgdetentionAddress;
		this.dateExecute = dateExecute;
		this.decideUnit = decideUnit;
		this.writEffectiveDate = writEffectiveDate;
		this.writType = writType;
		this.writNumber = writNumber;
		this.majorCrime = majorCrime;
		this.administrativePenalty = administrativePenalty;
		this.criminalPunshment = criminalPunshment;
		this.createTime = createTime;
		this.recordOrgId = recordOrgId;
	}
	
	
	public void copy(LegalInstrument right){
		this.investigateUnit = right.getInvestigateUnit();
		this.dateDetention = right.getDateDetention();
		this.dateRecord = right.getDateRecord();
		this.permitArrestUnit = right.getPermitArrestUnit();
		this.dateArrest = right.getDateArrest();
		this.dateEndInvestigate = right.getDateEndInvestigate();
		this.publicProsecution = right.getPublicProsecution();
		this.indictmentNumber = right.getIndictmentNumber();
		this.dateIndictment = right.getDateIndictment();
		this.trialUnit = right.getTrialUnit();
		this.judgmentNumber = right.getJudgmentNumber();
		this.dateJudgment = right.getDateJudgment();
		this.informNumber = right.getInformNumber();
		this.dateInform = right.getDateInform();
		this.orgdetentionAddress = right.getOrgdetentionAddress();
		this.dateExecute = right.getDateExecute();
		this.decideUnit = right.getDecideUnit();
		this.writEffectiveDate = right.getWritEffectiveDate();
		this.writType = right.getWritType();
		this.writNumber = right.getWritNumber();
		this.majorCrime = right.getMajorCrime();
		this.administrativePenalty = right.getAdministrativePenalty();
		this.criminalPunshment = right.getCriminalPunshment();
	}

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

	@Column(name = "INVESTIGATE_UNIT", length = 150)
	public String getInvestigateUnit() {
		return this.investigateUnit;
	}

	public void setInvestigateUnit(String investigateUnit) {
		this.investigateUnit = investigateUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DETENTION", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateDetention() {
		return this.dateDetention;
	}

	public void setDateDetention(Date dateDetention) {
		this.dateDetention = dateDetention;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_RECORD", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateRecord() {
		return this.dateRecord;
	}

	public void setDateRecord(Date dateRecord) {
		this.dateRecord = dateRecord;
	}

	@Column(name = "PERMIT_ARREST_UNIT", length = 150)
	public String getPermitArrestUnit() {
		return this.permitArrestUnit;
	}

	public void setPermitArrestUnit(String permitArrestUnit) {
		this.permitArrestUnit = permitArrestUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ARREST", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateArrest() {
		return this.dateArrest;
	}

	public void setDateArrest(Date dateArrest) {
		this.dateArrest = dateArrest;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_END_INVESTIGATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateEndInvestigate() {
		return this.dateEndInvestigate;
	}

	public void setDateEndInvestigate(Date dateEndInvestigate) {
		this.dateEndInvestigate = dateEndInvestigate;
	}

	@Column(name = "PUBLIC_PROSECUTION", length = 150)
	public String getPublicProsecution() {
		return this.publicProsecution;
	}

	public void setPublicProsecution(String publicProsecution) {
		this.publicProsecution = publicProsecution;
	}

	@Column(name = "INDICTMENT_NUMBER", length = 100)
	public String getIndictmentNumber() {
		return this.indictmentNumber;
	}

	public void setIndictmentNumber(String indictmentNumber) {
		this.indictmentNumber = indictmentNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INDICTMENT", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateIndictment() {
		return this.dateIndictment;
	}

	public void setDateIndictment(Date dateIndictment) {
		this.dateIndictment = dateIndictment;
	}

	@Column(name = "TRIAL_UNIT", length = 150)
	public String getTrialUnit() {
		return this.trialUnit;
	}

	public void setTrialUnit(String trialUnit) {
		this.trialUnit = trialUnit;
	}

	@Column(name = "JUDGMENT_NUMBER", length = 100)
	public String getJudgmentNumber() {
		return this.judgmentNumber;
	}

	public void setJudgmentNumber(String judgmentNumber) {
		this.judgmentNumber = judgmentNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_JUDGMENT", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateJudgment() {
		return this.dateJudgment;
	}

	public void setDateJudgment(Date dateJudgment) {
		this.dateJudgment = dateJudgment;
	}

	@Column(name = "INFORM_NUMBER", length = 100)
	public String getInformNumber() {
		return this.informNumber;
	}

	public void setInformNumber(String informNumber) {
		this.informNumber = informNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_INFORM", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateInform() {
		return this.dateInform;
	}

	public void setDateInform(Date dateInform) {
		this.dateInform = dateInform;
	}

	@Column(name = "ORGDETENTION_ADDRESS", length = 150)
	public String getOrgdetentionAddress() {
		return this.orgdetentionAddress;
	}

	public void setOrgdetentionAddress(String orgdetentionAddress) {
		this.orgdetentionAddress = orgdetentionAddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_EXECUTE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getDateExecute() {
		return this.dateExecute;
	}

	public void setDateExecute(Date dateExecute) {
		this.dateExecute = dateExecute;
	}

	@Column(name = "DECIDE_UNIT", length = 150)
	public String getDecideUnit() {
		return this.decideUnit;
	}

	public void setDecideUnit(String decideUnit) {
		this.decideUnit = decideUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WRIT_EFFECTIVE_DATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATE)
	public Date getWritEffectiveDate() {
		return this.writEffectiveDate;
	}

	public void setWritEffectiveDate(Date writEffectiveDate) {
		this.writEffectiveDate = writEffectiveDate;
	}

	@Column(name = "WRIT_TYPE", length = 30)
	public String getWritType() {
		return this.writType;
	}

	public void setWritType(String writType) {
		this.writType = writType;
	}

	@Column(name = "WRIT_NUMBER", length = 100)
	public String getWritNumber() {
		return this.writNumber;
	}

	public void setWritNumber(String writNumber) {
		this.writNumber = writNumber;
	}

	@Column(name = "MAJOR_CRIME", length = 600)
	public String getMajorCrime() {
		return this.majorCrime;
	}

	public void setMajorCrime(String majorCrime) {
		this.majorCrime = majorCrime;
	}

	@Column(name = "ADMINISTRATIVE_PENALTY", length = 600)
	public String getAdministrativePenalty() {
		return this.administrativePenalty;
	}

	public void setAdministrativePenalty(String administrativePenalty) {
		this.administrativePenalty = administrativePenalty;
	}

	@Column(name = "CRIMINAL_PUNSHMENT", length = 600)
	public String getCriminalPunshment() {
		return this.criminalPunshment;
	}

	public void setCriminalPunshment(String criminalPunshment) {
		this.criminalPunshment = criminalPunshment;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	@JSON(serialize=false, deserialize=false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "RECORD_ORG_ID", length = 32)
	public String getRecordOrgId() {
		return recordOrgId;
	}

	public void setRecordOrgId(String recordOrgId) {
		this.recordOrgId = recordOrgId;
	}
}