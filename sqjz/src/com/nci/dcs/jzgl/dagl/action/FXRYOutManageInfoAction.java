package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.FXRYOutManageInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYOutManageInfoService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;


public class FXRYOutManageInfoAction extends BaseAction<FXRYOutManageInfo>{
	
	@Autowired
	FXRYOutManageInfoService service;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	@Override
	public String list() throws Throwable {
		String fxryId = request.getParameter("fxryId");
		Page page = getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", fxryId));
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
	
	public String getByIdOrFxryId(){
		String id = this.entity.getId();
		String fxryId = this.entity.getFxryId();
		if (!StringUtils.isBlank(fxryId)){
			this.entity = service.getByFXRYId(fxryId);
		}
		else{
			this.entity = service.get(id);
		}
		if (this.entity == null){
			this.entity = new FXRYOutManageInfo();
			this.entity.setStartDate(new Date());
		}
		return "success";
	}
	
	public String saveOrUpdate(){
		try{
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
				entity.setRecordTime(new Date());
				entity.setRecordOrgId(getCurOrgId());
				entity.setRegistrantId(getUserId());
				service.create(entity);
			}
			else{
				FXRYOutManageInfo real = service.get(entity.getId());
				real.copy(entity);
				service.update(real);
			}
			ajaxFormResult = AjaxFormResult.success(entity.getId());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("保存人员脱管信息出错");
		}
		return "success";
	}
	
	public String outManage(){
		try {
			FXRYOutManageInfo real = service.getByFXRYId(entity.getFxryId());
			if (real == null){
				entity.setId(null);
				entity.setRecordTime(new Date());
				entity.setRecordOrgId(getCurOrgId());
				entity.setRegistrantId(getUserId());
				real = entity;
			}
			else{
				real.copy(entity);
			}
			if (real.getStartDate() == null) { 
				real.setStartDate(new Date());
			}
			service.outManage(real, getCurOrgId());
			ajaxFormResult = AjaxFormResult.success("人员已脱管！");
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员脱管失败:" + e.getMessage());
		}
		return "success";
	}
	
	public String inManage(){
		/*
		 * 因为【执法督察】功能只有市局和区县局使用
		 * 因此，增加zfdc这个标识，确保市局和区县局可以通过【执法督察】来 使用“恢复矫正”功能，
		 * 而司法所也能通过正常途径下使用 “恢复矫正”功能，防止权限问题
		 * zfdc=null 司法所使用 恢复矫正 功能
		 * zfdc！=null 市局和区县局通过【执法督察】使用“恢复矫正”功能
		 */
		String zfdc = request.getParameter("zfdc");
		try {
			FXRYOutManageInfo real = service.getByFXRYId(entity.getFxryId());
			if (real == null){
				ajaxFormResult = AjaxFormResult.success("找不到脱管信息");
			}
			else{
				if (real.getEndDate() == null) { 
					real.setEndDate(new Date());
				}
				if("zfdc".equalsIgnoreCase(zfdc)){
					service.inManage(real, getCurOrgId(),zfdc);
				}else{
					service.inManage(real, getCurOrgId());
				}
				ajaxFormResult = AjaxFormResult.success("人员已恢复矫正！");
			}
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员恢复矫正失败:" + e.getMessage());
		}
		return "success";
	}
}
