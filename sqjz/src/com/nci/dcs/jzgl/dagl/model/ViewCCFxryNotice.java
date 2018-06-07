package com.nci.dcs.jzgl.dagl.model;

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

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "VIEW_CC_FXRY_NOTICE", schema = "SQJZ")
public class ViewCCFxryNotice implements java.io.Serializable {

	// Fields

	/**
	 * @name 
	 * @author caolj
	 * @date 2015年3月30日 下午4:47:44
	 * @message:
	 */
	private static final long serialVersionUID = 2470495060983274372L;
	
	private String id;
	private String fxryId;
	private String name;//姓名
	private String code;//人员编号
	private String identityCard;//身份证号
	private String sex;//性别
	private String responseOrg;//矫正负责单位
	private Date dateSAdjust;//矫正期限起
    private Date dateEAdjust;//矫正期限止
	private String state;//当前状态
	private String deviceCode;//是否电子监管
	private String status;//通知状态
	private Long adjustPeriod;
	private Long remainDays;
	private Date createdate;
	private String creater;
	private String orgId;
	private String isTgry;
	
	// Constructors
	@Column(name = "ISTGRY")
	public String getIsTgry() {
		return isTgry;
	}

	public void setIsTgry(String isTgry) {
		this.isTgry = isTgry;
	}

	public ViewCCFxryNotice() {
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
	
	@Column(name = "FXRY_ID")
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
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

	@Column(name = "RESPONSE_ORG")
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "DEVICE_CODE")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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

    @Column(name = "STATUS", length = 30)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Transient
	public Long getAdjustPeriod() {
		return this.adjustPeriod;
	}

	public void setAdjustPeriod(Long adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}
	
	@Transient
	public Long getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(Long remainDays) {
		this.remainDays = remainDays;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATE", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "CREATER", length = 32)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}