package com.nci.dcs.em.dwjk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nci.dcs.jzgl.dagl.model.Address;


/**
 * CcFxryCrimeinfoId entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="CC_FXRY_CRIMEINFO"
    ,schema="SQJZ"
)
public class CcFxryCrimeinfo  implements java.io.Serializable {
    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2596372257395391874L;
	private String id;
     private String name;
     private String sex;
     private String idcard;
     private String phoneNumber;
     private Address houseAddress;
     private String adjustType;
     private String adjustPeriod;
     private Date dateSAdjust;
     private Date dateEAdjust;
     private String crimeType;
     private String picture; //照片

    // Constructors

    /** default constructor */
    public CcFxryCrimeinfo() {
    }

	/** minimal constructor */
    public CcFxryCrimeinfo(String id) {
        this.id = id;
    }
    
    /** full constructor */
    public CcFxryCrimeinfo(String id, String name, String sex, String idcard, String phoneNumber, Address houseAddress, String adjustType, String adjustPeriod, Date dateSAdjust, Date dateEAdjust, String crimeType, String picture) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.idcard = idcard;
        this.phoneNumber = phoneNumber;
        this.houseAddress = houseAddress;
        this.adjustType = adjustType;
        this.adjustPeriod = adjustPeriod;
        this.dateSAdjust = dateSAdjust;
        this.dateEAdjust = dateEAdjust;
        this.crimeType = crimeType;
        this.picture = picture;
    }

   
    // Property accessors
    @Id
    @Column(name="ID", nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
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

    @Column(name="IDCARD", length=18)

    public String getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Column(name="PHONE_NUMBER", length=30)

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


	@OneToOne
	//@Column(name = "HOUSE_ADDRESS", length = 32)
	@JoinColumn(name="HOUSE_ADDRESS", insertable=true,updatable=true)
	public Address getHouseAddress() {
		return this.houseAddress;
	}

	public void setHouseAddress(Address houseAddress) {
		this.houseAddress = houseAddress;
	}

    @Column(name="ADJUST_TYPE", length=5)

    public String getAdjustType() {
        return this.adjustType;
    }
    
    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    @Column(name="ADJUST_PERIOD", precision=8, scale=0)

    public String getAdjustPeriod() {
        return this.adjustPeriod;
    }
    
    public void setAdjustPeriod(String adjustPeriod) {
        this.adjustPeriod = adjustPeriod;
    }
@Temporal(TemporalType.DATE)
    @Column(name="DATE_S_ADJUST", length=7)

    public Date getDateSAdjust() {
        return this.dateSAdjust;
    }
    
    public void setDateSAdjust(Date dateSAdjust) {
        this.dateSAdjust = dateSAdjust;
    }
@Temporal(TemporalType.DATE)
    @Column(name="DATE_E_ADJUST", length=7)

    public Date getDateEAdjust() {
        return this.dateEAdjust;
    }
    
    public void setDateEAdjust(Date dateEAdjust) {
        this.dateEAdjust = dateEAdjust;
    }

    @Column(name="CRIME_TYPE", length=5)

    public String getCrimeType() {
        return this.crimeType;
    }
    
    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }
   
    @Column(name = "PICTURE", length=32)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CcFxryCrimeinfo) ) return false;
		 CcFxryCrimeinfo castOther = ( CcFxryCrimeinfo ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getSex()==castOther.getSex()) || ( this.getSex()!=null && castOther.getSex()!=null && this.getSex().equals(castOther.getSex()) ) )
 && ( (this.getIdcard()==castOther.getIdcard()) || ( this.getIdcard()!=null && castOther.getIdcard()!=null && this.getIdcard().equals(castOther.getIdcard()) ) )
 && ( (this.getPhoneNumber()==castOther.getPhoneNumber()) || ( this.getPhoneNumber()!=null && castOther.getPhoneNumber()!=null && this.getPhoneNumber().equals(castOther.getPhoneNumber()) ) )
 && ( (this.getHouseAddress()==castOther.getHouseAddress()) || ( this.getHouseAddress()!=null && castOther.getHouseAddress()!=null && this.getHouseAddress().equals(castOther.getHouseAddress()) ) )
 && ( (this.getAdjustType()==castOther.getAdjustType()) || ( this.getAdjustType()!=null && castOther.getAdjustType()!=null && this.getAdjustType().equals(castOther.getAdjustType()) ) )
 && ( (this.getAdjustPeriod()==castOther.getAdjustPeriod()) || ( this.getAdjustPeriod()!=null && castOther.getAdjustPeriod()!=null && this.getAdjustPeriod().equals(castOther.getAdjustPeriod()) ) )
 && ( (this.getDateSAdjust()==castOther.getDateSAdjust()) || ( this.getDateSAdjust()!=null && castOther.getDateSAdjust()!=null && this.getDateSAdjust().equals(castOther.getDateSAdjust()) ) )
 && ( (this.getDateEAdjust()==castOther.getDateEAdjust()) || ( this.getDateEAdjust()!=null && castOther.getDateEAdjust()!=null && this.getDateEAdjust().equals(castOther.getDateEAdjust()) ) )
 && ( (this.getCrimeType()==castOther.getCrimeType()) || ( this.getCrimeType()!=null && castOther.getCrimeType()!=null && this.getCrimeType().equals(castOther.getCrimeType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getSex() == null ? 0 : this.getSex().hashCode() );
         result = 37 * result + ( getIdcard() == null ? 0 : this.getIdcard().hashCode() );
         result = 37 * result + ( getPhoneNumber() == null ? 0 : this.getPhoneNumber().hashCode() );
         result = 37 * result + ( getHouseAddress() == null ? 0 : this.getHouseAddress().hashCode() );
         result = 37 * result + ( getAdjustType() == null ? 0 : this.getAdjustType().hashCode() );
         result = 37 * result + ( getAdjustPeriod() == null ? 0 : this.getAdjustPeriod().hashCode() );
         result = 37 * result + ( getDateSAdjust() == null ? 0 : this.getDateSAdjust().hashCode() );
         result = 37 * result + ( getDateEAdjust() == null ? 0 : this.getDateEAdjust().hashCode() );
         result = 37 * result + ( getCrimeType() == null ? 0 : this.getCrimeType().hashCode() );
         return result;
   }   
}