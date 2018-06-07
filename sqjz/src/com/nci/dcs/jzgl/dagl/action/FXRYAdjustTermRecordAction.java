package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.CcAdjusttermRecord;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYAdjustTermRecordService;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;

public class FXRYAdjustTermRecordAction extends BaseAction<CcAdjusttermRecord>{
	
	@Autowired
	FXRYAdjustTermRecordService service;
	
	@Autowired
	FXRYExecuteInfoService executeInfoservice;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	
	@Override
	public String list() throws Throwable {
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
		try
		{
			FXRYExecuteInfo executeInfo=executeInfoservice.get(entity.getId());
			executeInfo.setDateEAdjust(entity.getNewEndDate());
			executeInfo.setDateSAdjust(entity.getNewStartDate());
			executeInfoservice.update(executeInfo);
			
			this.entity.setId(null);
			this.entity.setRecordOrgId(getCurOrgId());
			this.entity.setRecordTime(new Date());
			service.create(entity);
			ajaxFormResult = AjaxFormResult.success(entity.getFxryId());
		}		
		catch(Exception e)
		{
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		
		return "success";
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

