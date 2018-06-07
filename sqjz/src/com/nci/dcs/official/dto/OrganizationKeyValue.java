package com.nci.dcs.official.dto;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.system.model.Bmb;

public class OrganizationKeyValue {
	private String id;
	private String code;
	private String codeDesc;
	private String type;
	private boolean using;
	private List<OrganizationKeyValue> children;

	public OrganizationKeyValue(OrganizationInfo org) {
		this.id = org.getOrgId();
		this.code = org.getOrgCode();
		this.codeDesc = org.getCname();
		this.type = org.getOrgType();
		this.using = org.getOrgStatus().equals("3") ? false : true;
	}

	public OrganizationKeyValue(Bmb bmb) {
		if (bmb != null) {
			this.id = bmb.getBm();
			this.code = bmb.getBm();
			this.codeDesc = bmb.getMc();
			this.type = bmb.getOrgType();
			this.using = true;
			this.children = new ArrayList<OrganizationKeyValue>();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}

	public List<OrganizationKeyValue> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationKeyValue> children) {
		this.children = children;
	}
}