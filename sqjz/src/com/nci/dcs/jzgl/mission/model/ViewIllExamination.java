package com.nci.dcs.jzgl.mission.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VIEW_CC_TD_ILLEXAMINATION", schema = "SQJZ")
public class ViewIllExamination extends ViewMission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2182175859060540418L;
	private String supOrgId;

	@Column(name = "SUP_ORG_ID")
	public String getSupOrgId() {
		return supOrgId;
	}

	public void setSupOrgId(String supOrgId) {
		this.supOrgId = supOrgId;
	}

}