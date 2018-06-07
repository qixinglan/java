package com.nci.dcs.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(LbppglPk.class)
@Table(name = "T_LBPPGL")
public class Lbppgl {
	private long lbdm;//类别
	private long ppdm;//品牌
	private Date cjsj;//创建时间
	private String cjr;//创建人
	private Date xgsj;//修改时间
	private String xgr;//修改人
	@Id
	public long getLbdm() {
		return lbdm;
	}
	public void setLbdm(long lbdm) {
		this.lbdm = lbdm;
	}
	@Id
	public long getPpdm() {
		return ppdm;
	}
	public void setPpdm(long ppdm) {
		this.ppdm = ppdm;
	}
	@Column(name = "CJSJ")
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	@Column(name = "CJR")
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	@Column(name = "XGSJ")
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	@Column(name = "XGR")
	public String getXgr() {
		return xgr;
	}
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
	
}
