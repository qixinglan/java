package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.service.FXRYRemoveAdjustService;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.sync.service.ViewCcFxrySyncinfoService;


public class FXRYRemoveAdjustAction extends BaseAction<FXRYRemoveAdjust>{
	
	@Autowired
	FXRYRemoveAdjustService service;
	@Autowired
	private ViewCcFxrySyncinfoService viewCcFxrySyncinfoService;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
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
			this.entity = new FXRYRemoveAdjust();
			this.entity.setRemoveDate(new Date());
		}
		return "success";
	}
	
	public String saveOrUpdate(){
		try{
			String fxryId = entity.getFxryId();
			FXRYRemoveAdjust real = service.getByFXRYId(fxryId);
			if (real == null){
				entity.setId(null);
				entity.setCreateTime(new Date());
				entity.setRecordOrgId(getCurOrgId());
				service.create(entity);
			}
			else{
				real.copy(entity);
				service.update(real);
			}
			ajaxFormResult = AjaxFormResult.success(entity.getId());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return "success";
	}

	public String removeAdjust(){
		try {
			service.removeAdjust(entity.getFxryId(), getCurOrgId());
			//同步至司法部
			viewCcFxrySyncinfoService.fxrySync(entity.getFxryId());
			
			ajaxFormResult = AjaxFormResult.success("人员解矫成功！");
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员解矫失败:" + e.getMessage());
		}
		return "success";
	}
}
