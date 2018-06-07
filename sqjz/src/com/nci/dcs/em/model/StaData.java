package com.nci.dcs.em.model;

public class StaData{
	public String orgId;
	public String orgName;
	public Integer personCount;
	public Integer controlCount;
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}
	public int getControlCount() {
		return controlCount;
	}
	public void setControlCount(Integer controlCount) {
		this.controlCount = controlCount;
	}
}
