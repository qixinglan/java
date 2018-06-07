package com.nci.dcs.jzgl.cxtj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.googlecode.jsonplugin.annotations.JSON;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

@Entity
@Table(name = "VIEW_REPORT_FXRY_DEVICE", schema = "SQJZ")
public class ViewReportFxryDevice implements java.io.Serializable {

	private String id;// 后台字段
	private String status;// 后台字段
	private String fxryId;
	private Date start;
	private Date end;
	private String responseOrg;// 负责矫正单位
	private String supOrg;
	private String unbindStatus;// 后台字段

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "RESPONSE_ORG", length = 32)
	public String getResponseOrg() {
		return responseOrg;
	}

	public void setResponseOrg(String responseOrg) {
		this.responseOrg = responseOrg;
	}

	@Column(name = "SUP_ORG_ID", length = 32)
	public String getSupOrg() {
		return supOrg;
	}

	public void setSupOrg(String supOrg) {
		this.supOrg = supOrg;
	}

	@Column(name = "STATUS", length = 30)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "START_TIME", length = 7)
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@JSON(format = DateTimeFmtSpec.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Column(name = "FXRY_ID", length = 30)
	public String getFxryId() {
		return fxryId;
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}
	
	@Column(name = "UNBIND_STATUS", length = 30)
	public String getUnbindStatus() {
		return unbindStatus;
	}

	public void setUnbindStatus(String unbindStatus) {
		this.unbindStatus = unbindStatus;
	}
	

}