package com.nci.dcs.jzgl.dagl.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageResponse;
import com.nci.dcs.jzgl.dagl.model.FXRYEscortInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYEscortInfoService;


public class FXRYEscortInfoAction extends BaseAction<FXRYEscortInfo>{
	
	@Autowired
	FXRYEscortInfoService service;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	@Override
	public String list() throws Throwable {
		String fxryId = this.request.getParameter("fxryId");
		List<FXRYEscortInfo> list = service.getByFXRYId(fxryId);//从数据库加载
		this.setJqgridPage(new JQGridPageResponse(list));
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
		int deleted = 0;
		String lastId = "";
		try{
			String idsParam = this.request.getParameter("ids");
			if (idsParam != null && !idsParam.isEmpty()){
				String ids[] = idsParam.split(",");
				for (String id : ids){
					lastId = id;
					service.delete(id.trim());
					++deleted;
				}
				ajaxFormResult = AjaxFormResult.success(String.valueOf(deleted));
			}
			else{
				ajaxFormResult = AjaxFormResult.error("无删除对象！");
			}
		}
		catch(Exception e){
			ajaxFormResult = AjaxFormResult.error(lastId);
		}
		return "success";
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
	
	public String save(){
		try{
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
				service.create(entity);
			}
			else{
				service.update(entity);
			}
			ajaxFormResult = AjaxFormResult.success(entity.getId());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return "success";
	}
}
