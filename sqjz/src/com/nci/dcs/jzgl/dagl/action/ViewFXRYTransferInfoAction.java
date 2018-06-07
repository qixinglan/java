package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTransferInfo;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYTransferInfoService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;


public class ViewFXRYTransferInfoAction extends BaseAction<ViewFxryTransferInfo>{
	
	@Autowired
	ViewFXRYTransferInfoService service;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	@Override
	public String list() throws Throwable {
		String oper = request.getParameter("oper");
		this.getRequestPage();
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		if("untreat".equals(oper)){
			//待转入人员必须是未接收，人员状态为ZC,目标机构为当前机构
			page.getCriterions().add(Restrictions.eq("transtatus", 0));
			page.getCriterions().add(Restrictions.eq("receiveOrgId", getCurOrgId()));
			page.getCriterions().add(Restrictions.eq("state", FXRYState.ZC));
		}else{
			//已转出人员 必须是曾经从本机构转出,且当前机构不属于本机构,如果为北京内转出检查接收时间，如果为北京外转出，检查receiveOrgId
			page.getCriterions().add(Restrictions.or(Restrictions.eq("transtatus", 1)
					, Restrictions.isNull("receiveOrgId")));
			page.getCriterions().add(Restrictions.or(Restrictions.isNull("responseOrg")
					, Restrictions.ne("responseOrg", getCurOrgId())));
			page.getCriterions().add(Restrictions.eq("outOrgId", getCurOrgId()));
		}
		service.findPaged(page);
		return "success";
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
}
