package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTempoutprionExtend;
import com.nci.dcs.jzgl.dagl.service.ViewFxryTempoutprionExtendService;
import com.nci.dcs.system.model.Bmb;

public class ViewFxryTempoutprionExtendAction extends BaseAction<ViewFxryTempoutprionExtend>{
	
	@Autowired
	ViewFxryTempoutprionExtendService service;
		
	@Override
	public String list() throws Throwable {
		Bmb org = getCurOrg();
		String orgId=org.getBm();		
		Page page = getRequestPage();
		if(org.isSfs())
		{
			page.getCriterions().add(Restrictions.eq("responseOrg", orgId));
		}
		else if(org.isQxsfj())
		{
			page.getCriterions().add(Restrictions.eq("supOrgId", orgId));
		}
		service.findPaged(page);
		return "success";
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		//return null;
		return "success";
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
}

