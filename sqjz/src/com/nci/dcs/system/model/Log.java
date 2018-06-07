package com.nci.dcs.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.googlecode.jsonplugin.annotations.JSON;

@Entity
@Table(name = "CC_LOGS")
public class Log {
	private long id;
	private String yhm;
	private String mkm;
	private int rzlx;
	private Date czsj;
	private String orgId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g")
	@SequenceGenerator(name = "g", sequenceName = "SEQ_T_LOG")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "YHM")
	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	@Column(name = "MKM")
	public String getMkm() {
		return mkm;
	}

	public void setMkm(String mkm) {
		this.mkm = mkm;
	}

	@Column(name = "RZLX")
	public int getRzlx() {
		return rzlx;
	}

	public void setRzlx(int rzlx) {
		this.rzlx = rzlx;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CZSJ")
	@JSON(format = "yyyy年MM月dd日 HH时mm分ss秒")
	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	
	@Column(name = "ORGID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
