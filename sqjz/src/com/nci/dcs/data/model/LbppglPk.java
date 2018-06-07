package com.nci.dcs.data.model;

import java.io.Serializable;

import javax.persistence.Column;

public class LbppglPk implements Serializable{
	
	private long lbdm;
	private long ppdm;
	
	@Column(name="LBDM")
	public long getLbdm() {
		return lbdm;
	}

	public void setLbdm(long lbdm) {
		this.lbdm = lbdm;
	}
	@Column(name="PPDM")
	public long getPpdm() {
		return ppdm;
	}

	public void setPpdm(long ppdm) {
		this.ppdm = ppdm;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	

	
}
