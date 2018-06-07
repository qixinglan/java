package com.nci.dcs.jzgl.dagl.model;
// default package

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
 * FXRYTransferInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CC_TRANSFER_INFO",schema="SQJZ")
public class FXRYTransferInfo  implements java.io.Serializable {


    // Fields    

     private String id;
     private String fxryId;
     private String outOrgId;
     private Date outTime;
     private Date receiveTime;
     private String receiveOrgId;
     private String reason;
     private String receiveOrgName;
     private String name;
     private String phone;
     private String affixId;
     private Date reportTime;

    // Constructors

    /** default constructor */
    public FXRYTransferInfo() {
    }

    
    /** full constructor */
    public FXRYTransferInfo(String fxryId, String outOrgId, Date outTime, Date receiveTime, String receiveOrgId, String reason, String receiveOrgName, String name, String phone, String affixId) {
        this.fxryId = fxryId;
        this.outOrgId = outOrgId;
        this.outTime = outTime;
        this.receiveTime = receiveTime;
        this.receiveOrgId = receiveOrgId;
        this.reason = reason;
        this.receiveOrgName = receiveOrgName;
        this.name = name;
        this.phone = phone;
        this.affixId = affixId;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")
    @Id 
    @GeneratedValue(generator="generator")
    @Column(name="ID", unique=true, nullable=false, length=32)
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
    
    @Column(name="OUT_ORG_ID", length=32)

    public String getOutOrgId() {
        return this.outOrgId;
    }
    
    public void setOutOrgId(String outOrgId) {
        this.outOrgId = outOrgId;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="OUT_TIME", length=7)
    @JSON(format=DateTimeFmtSpec.MINUTES)
    public Date getOutTime() {
        return this.outTime;
    }
    
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RECEIVE_TIME", length=7)
    @JSON(format=DateTimeFmtSpec.MINUTES)
    public Date getReceiveTime() {
        return this.receiveTime;
    }
    
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
    
    @Column(name="RECEIVE_ORG_ID", length=32)

    public String getReceiveOrgId() {
        return this.receiveOrgId;
    }
    
    public void setReceiveOrgId(String receiveOrgId) {
        this.receiveOrgId = receiveOrgId;
    }
    
    @Column(name="REASON", length=300)

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    @Column(name="RECEIVE_ORG_NAME", length=150)

    public String getReceiveOrgName() {
        return this.receiveOrgName;
    }
    
    public void setReceiveOrgName(String receiveOrgName) {
        this.receiveOrgName = receiveOrgName;
    }
    
    @Column(name="NAME", length=60)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="PHONE", length=30)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="AFFIX_ID", length=32)
    public String getAffixId() {
        return this.affixId;
    }
    
    public void setAffixId(String affixId) {
        this.affixId = affixId;
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