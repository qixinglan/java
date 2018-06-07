package com.nci.dcs.jzgl.dagl.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name="CC_EXECUTE_INFO",schema="SQJZ")
public class FXRYExecuteInfo  implements java.io.Serializable {
     private String id;
     private String fxryId;
     private String adjustType;
     private String adjustPeriod;
     private Date dateSAdjust;
     private Date dateEAdjust;
     private String crimeType;
     private String accusation;
     private String oriPunishment;
     private Date dateSOri;
     private Date dateEOri;
     private String oriPeriod;
     private String imprisonmentPeriod;
     private String addpunishment;
     private String nonpoliticalPeriod;
     private Date dateSNonpolitical;
     private Date dateENonpolitical;
     private String receiveUnit;
     private String receivePerson;
     private Date dateTransfer;
     private String sisType;
     private String sansType;
     private String slfzType;
     private String isRecidivism;
     private String isalone;
     private String isforbidden;
     private String forbidden;
     private Date dateSForbidden;
     private Date dateEForbidden;
     private Date dateReceive;
     private String receiveType;
     private String reportInfo;
     private String groupInfo;
     private String recordOrgId;
     private Date createTime;
     private String remark;

    // Constructors

    /** default constructor */
    public FXRYExecuteInfo() {
    }

    
    /** full constructor */
    public FXRYExecuteInfo(String fxryId, String adjustType, String adjustPeriod, Date dateSAdjust, 
    		Date dateEAdjust, String crimeType, String accusation, String oriPunishment, 
    		Date dateSOri, Date dateEOri, String oriPeriod, String imprisonmentPeriod, 
    		String addpunishment, String nonpoliticalPeriod, Date dateSNonpolitical, 
    		Date dateENonpolitical, String receiveUnit, String receivePerson, Date dateTransfer, 
    		String sisType, String sansType, String isRecidivism, String isalone, String forbidden, 
    		Date dateSForbidden, Date dateEForbidden, Date dateReceive, String receiveType, 
    		String reportInfo, String groupInfo, String recordOrgId, Date createTime) {
        this.fxryId = fxryId;
        this.adjustType = adjustType;
        this.adjustPeriod = adjustPeriod;
        this.dateSAdjust = dateSAdjust;
        this.dateEAdjust = dateEAdjust;
        this.crimeType = crimeType;
        this.accusation = accusation;
        this.oriPunishment = oriPunishment;
        this.dateSOri = dateSOri;
        this.dateEOri = dateEOri;
        this.oriPeriod = oriPeriod;
        this.imprisonmentPeriod = imprisonmentPeriod;
        this.addpunishment = addpunishment;
        this.nonpoliticalPeriod = nonpoliticalPeriod;
        this.dateSNonpolitical = dateSNonpolitical;
        this.dateENonpolitical = dateENonpolitical;
        this.receiveUnit = receiveUnit;
        this.receivePerson = receivePerson;
        this.dateTransfer = dateTransfer;
        this.sisType = sisType;
        this.sansType = sansType;
        this.isRecidivism = isRecidivism;
        this.isalone = isalone;
        this.forbidden = forbidden;
        this.dateSForbidden = dateSForbidden;
        this.dateEForbidden = dateEForbidden;
        this.dateReceive = dateReceive;
        this.receiveType = receiveType;
        this.reportInfo = reportInfo;
        this.groupInfo = groupInfo;
        this.recordOrgId = recordOrgId;
        this.createTime = createTime;
    }
    
    public void copy(FXRYExecuteInfo right){
    	this.adjustType = right.getAdjustType();
        this.adjustPeriod = right.getAdjustPeriod();
        this.dateSAdjust = right.getDateSAdjust();
        this.dateEAdjust = right.getDateEAdjust();
        this.crimeType = right.getCrimeType();
        this.accusation = right.getAccusation();
        this.oriPunishment = right.getOriPunishment();
        this.dateSOri = right.getDateSOri();
        this.dateEOri = right.getDateEOri();
        this.oriPeriod = right.getOriPeriod();
        this.imprisonmentPeriod = right.getImprisonmentPeriod();
        this.addpunishment = right.getAddpunishment();
        this.nonpoliticalPeriod = right.getNonpoliticalPeriod();
        this.dateSNonpolitical = right.getDateSNonpolitical();
        this.dateENonpolitical = right.getDateENonpolitical();
        this.receiveUnit = right.getReceiveUnit();
        this.receivePerson = right.getReceivePerson();
        this.dateTransfer = right.getDateTransfer();
        this.sisType = right.getSisType();
        this.sansType = right.getSansType();
        this.isRecidivism = right.getIsRecidivism();
        this.isalone = right.getIsalone();
        this.forbidden = right.getForbidden();
        this.dateSForbidden = right.getDateSForbidden();
        this.dateEForbidden = right.getDateEForbidden();
        this.dateReceive = right.getDateReceive();
        this.receiveType = right.getReceiveType();
        this.reportInfo = right.getReportInfo();
        this.groupInfo = right.getGroupInfo();
        this.remark = right.getRemark();
        this.slfzType=right.getSlfzType();
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
    
    @Column(name="FXRY_ID", length=32)
    public String getFxryId() {
        return this.fxryId;
    }
    
    public void setFxryId(String fxryId) {
        this.fxryId = fxryId;
    }
    
    @Column(name="ADJUST_TYPE", length=30)

    public String getAdjustType() {
        return this.adjustType;
    }
    
    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }
    
    @Column(name="ADJUST_PERIOD", length=150)

    public String getAdjustPeriod() {
        return this.adjustPeriod;
    }
    
    public void setAdjustPeriod(String adjustPeriod) {
        this.adjustPeriod = adjustPeriod;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_S_ADJUST", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateSAdjust() {
        return this.dateSAdjust;
    }
    
    public void setDateSAdjust(Date dateSAdjust) {
        this.dateSAdjust = dateSAdjust;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_E_ADJUST", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateEAdjust() {
        return this.dateEAdjust;
    }
    
    public void setDateEAdjust(Date dateEAdjust) {
        this.dateEAdjust = dateEAdjust;
    }
    
    @Column(name="CRIME_TYPE", length=30)

    public String getCrimeType() {
        return this.crimeType;
    }
    
    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }
    
    @Column(name="ACCUSATION", length=150)

    public String getAccusation() {
        return this.accusation;
    }
    
    public void setAccusation(String accusation) {
        this.accusation = accusation;
    }
    
    @Column(name="ORI_PUNISHMENT", length=30)

    public String getOriPunishment() {
        return this.oriPunishment;
    }
    
    public void setOriPunishment(String oriPunishment) {
        this.oriPunishment = oriPunishment;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_S_ORI", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateSOri() {
        return this.dateSOri;
    }
    
    public void setDateSOri(Date dateSOri) {
        this.dateSOri = dateSOri;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_E_ORI", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateEOri() {
        return this.dateEOri;
    }
    
    public void setDateEOri(Date dateEOri) {
        this.dateEOri = dateEOri;
    }
    
    @Column(name="ORI_PERIOD", length=150)

    public String getOriPeriod() {
        return this.oriPeriod;
    }
    
    public void setOriPeriod(String oriPeriod) {
        this.oriPeriod = oriPeriod;
    }
    
    @Column(name="IMPRISONMENT_PERIOD", length=30)

    public String getImprisonmentPeriod() {
        return this.imprisonmentPeriod;
    }
    
    public void setImprisonmentPeriod(String imprisonmentPeriod) {
        this.imprisonmentPeriod = imprisonmentPeriod;
    }
    
    @Column(name="ADDPUNISHMENT", length=30)

    public String getAddpunishment() {
        return this.addpunishment;
    }
    
    public void setAddpunishment(String addpunishment) {
        this.addpunishment = addpunishment;
    }
    
    @Column(name="NONPOLITICAL_PERIOD", length=150)

    public String getNonpoliticalPeriod() {
        return this.nonpoliticalPeriod;
    }
    
    public void setNonpoliticalPeriod(String nonpoliticalPeriod) {
        this.nonpoliticalPeriod = nonpoliticalPeriod;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_S_NONPOLITICAL", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateSNonpolitical() {
        return this.dateSNonpolitical;
    }
    
    public void setDateSNonpolitical(Date dateSNonpolitical) {
        this.dateSNonpolitical = dateSNonpolitical;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_E_NONPOLITICAL", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateENonpolitical() {
        return this.dateENonpolitical;
    }
    
    public void setDateENonpolitical(Date dateENonpolitical) {
        this.dateENonpolitical = dateENonpolitical;
    }
    
    @Column(name="RECEIVE_UNIT", length=150)
    public String getReceiveUnit() {
        return this.receiveUnit;
    }
    
    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }
    
    @Column(name="RECEIVE_PERSON", length=60)

    public String getReceivePerson() {
        return this.receivePerson;
    }
    
    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_TRANSFER", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateTransfer() {
        return this.dateTransfer;
    }
    
    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
    
    @Column(name="SIS_TYPE", length=150)

    public String getSisType() {
        return this.sisType;
    }
    
    public void setSisType(String sisType) {
        this.sisType = sisType;
    }
    
    @Column(name="SANS_TYPE", length=150)

    public String getSansType() {
        return this.sansType;
    }
    
    public void setSansType(String sansType) {
        this.sansType = sansType;
    }
    
    @Column(name="IS_RECIDIVISM", length=30)

    public String getIsRecidivism() {
        return this.isRecidivism;
    }
    
    public void setIsRecidivism(String isRecidivism) {
        this.isRecidivism = isRecidivism;
    }
    
    @Column(name="ISALONE", length=30)

    public String getIsalone() {
        return this.isalone;
    }
    
    public void setIsalone(String isalone) {
        this.isalone = isalone;
    }
    @Column(name="ISFORBIDDEN", length=30)
    public String getIsforbidden() {
		return isforbidden;
	}


	public void setIsforbidden(String isforbidden) {
		this.isforbidden = isforbidden;
	}
	
    @Column(name="FORBIDDEN", length=300)

    public String getForbidden() {
        return this.forbidden;
    }
    
    public void setForbidden(String forbidden) {
        this.forbidden = forbidden;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_S_FORBIDDEN", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateSForbidden() {
        return this.dateSForbidden;
    }
    
    public void setDateSForbidden(Date dateSForbidden) {
        this.dateSForbidden = dateSForbidden;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_E_FORBIDDEN", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateEForbidden() {
        return this.dateEForbidden;
    }
    
    public void setDateEForbidden(Date dateEForbidden) {
        this.dateEForbidden = dateEForbidden;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_RECEIVE", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getDateReceive() {
        return this.dateReceive;
    }
    
    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }
    
    @Column(name="RECEIVE_TYPE", length=30)

    public String getReceiveType() {
        return this.receiveType;
    }
    
    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }
    
    @Column(name="REPORT_INFO", length=30)

    public String getReportInfo() {
        return this.reportInfo;
    }
    
    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }
    
    @Column(name="GROUP_INFO", length=150)

    public String getGroupInfo() {
        return this.groupInfo;
    }
    
    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }
    
    @Column(name="RECORD_ORG_ID", length=32)

    public String getRecordOrgId() {
        return this.recordOrgId;
    }
    
    public void setRecordOrgId(String recordOrgId) {
        this.recordOrgId = recordOrgId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    @JSON(serialize=false, deserialize=false)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="REMARK")
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="SLFZ_TYPE", length=150)
	public String getSlfzType() {
		return slfzType;
	}


	public void setSlfzType(String slfzType) {
		this.slfzType = slfzType;
	}
	
}