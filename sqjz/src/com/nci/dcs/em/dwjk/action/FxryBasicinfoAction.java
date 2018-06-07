package com.nci.dcs.em.dwjk.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;
import com.nci.dcs.em.dwjk.service.FxryBasicinfoService;
import com.nci.dcs.em.dwjk.service.FxryCrimeinfoService;
import com.nci.dcs.official.service.OrganizationInfoService;

public class FxryBasicinfoAction extends BaseAction<CcFxryBaseinfo> {

	@Autowired
	private FxryBasicinfoService service;
	@Autowired
	private FxryCrimeinfoService crimeService;
	@Autowired
	private OrganizationInfoService orgService;

	public Object data = new Object();

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public FxryBasicinfoService getService() {
		return service;
	}

	public void setService(FxryBasicinfoService service) {
		this.service = service;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFxryInfo() {
		String id = request.getParameter("id");
		data = service.get(id);
		return SUCCESS;
	}

	public String getFxryCrimeInfo() {
		String id = request.getParameter("id");
		data = crimeService.get(id);
		return SUCCESS;
	}

	public String drawPersonOnMap() {
		String orgId = request.getParameter("orgId");
		List orgIds = orgService.getChildrenIds(orgId);
		data = service.getPersons(orgId, orgIds);
		return SUCCESS;
	}
}
