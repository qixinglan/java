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

@Entity
@Table(name="CC_COMPLICITY_INFO",schema="SQJZ")
public class FXRYComplicityInfo  implements java.io.Serializable {
     private String id;
     private String fxryId;
     private String name;
     private String sex;
     private Date birthday;
     private String accusation;
     private String punishmentAddress;
    /** default constructor */
    public FXRYComplicityInfo() {
    }

    
    /** full constructor */
    public FXRYComplicityInfo(String fxryId, String name, String sex, Date birthday, String accusation, String punishmentAddress) {
        this.fxryId = fxryId;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.accusation = accusation;
        this.punishmentAddress = punishmentAddress;
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
    
    @Column(name="NAME", length=60)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="SEX", length=30)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY", length=7)
    @JSON(format=DateTimeFmtSpec.DATE)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="ACCUSATION", length=150)

    public String getAccusation() {
        return this.accusation;
    }
    
    public void setAccusation(String accusation) {
        this.accusation = accusation;
    }
    
    @Column(name="PUNISHMENT_ADDRESS", length=300)

    public String getPunishmentAddress() {
        return this.punishmentAddress;
    }
    
    public void setPunishmentAddress(String punishmentAddress) {
        this.punishmentAddress = punishmentAddress;
    }
   








}