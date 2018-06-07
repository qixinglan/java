package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;
import com.nci.dcs.jzgl.dagl.service.LegalInstrumentService;
import com.nci.dcs.jzgl.sync.service.ViewCcFxrySyncinfoService;


public class LegalInstrumentAction extends BaseAction<LegalInstrument>{
	
	@Autowired
	LegalInstrumentService service;
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
			this.entity = new LegalInstrument();
		}
		return "success";
	}
	
	public String saveOrUpdate(){
		try{
			String fxryId = entity.getFxryId();
			if (!StringUtils.isBlank(fxryId)){
				LegalInstrument real = service.getByFXRYId(fxryId);
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
				//同步至司法部
				viewCcFxrySyncinfoService.fxrySync(fxryId);
				
				ajaxFormResult = AjaxFormResult.success(entity.getId());
			}
			else{
				ajaxFormResult = AjaxFormResult.error("找不到对应的服刑人员信息");
			}
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return "success";
	}
}
