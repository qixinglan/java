package com.nci.dcs.official.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.system.model.User;

/**
 * CcNoticereceiveId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_NOTICERECEIVE", schema = "SQJZ")
public class CcNoticereceive implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6867412659349374185L;
	private String id;
	private String noticeid;
	private String name;
	private String orgId;
	private String status;
	private Date receivetime;
	private User receiver;
	private String  receivername;
    private CcWeeknotice ccWeeknotice;
	// Constructors

	/** default constructor */
	public CcNoticereceive() {
	}

	/** full constructor */
	public CcNoticereceive(String id, String noticeid, String name,
			String orgId, String status, Date receivetime) {
		this.id = id;
		this.noticeid = noticeid;
		this.name = name;
		this.orgId = orgId;
		this.status = status;
		this.receivetime = receivetime;
	}

	// Property accessors

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NOTICEID", length = 32)
	public String getNoticeid() {
		return this.noticeid;
	}

	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECEIVETIME", length = 7)
	@JSON(format=DateTimeFmtSpec.DATETIME)
	public Date getReceivetime() {
		return this.receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NOTICEID", nullable = false, insertable = false, updatable = false)
	public CcWeeknotice getCcWeeknotice() {
		return this.ccWeeknotice;
	}

	public void setCcWeeknotice(CcWeeknotice ccWeeknotice) {
		this.ccWeeknotice = ccWeeknotice;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECEIVERID")
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	@Column(name = "RECEIVERNAME")
	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	
}