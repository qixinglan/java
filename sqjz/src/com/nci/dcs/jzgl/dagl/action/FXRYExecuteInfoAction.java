package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.model.FxryNotice;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.service.FxryNoticeService;
import com.nci.dcs.jzgl.sync.service.ViewCcFxrySyncinfoService;


public class FXRYExecuteInfoAction extends BaseAction<FXRYExecuteInfo>{
	
	@Autowired
	FXRYExecuteInfoService service;
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
			this.entity = new FXRYExecuteInfo();
			this.entity.setDateSAdjust(new Date());
		}
		return "success";
	}
	@Autowired
	FxryNoticeService noticeService;
	
	@Autowired
	FXRYBaseInfoService fxryService;
	
	public String saveOrUpdate(){
		try{
			String fxryId = entity.getFxryId();
			if (!StringUtils.isBlank(fxryId)){
				FXRYExecuteInfo real = service.getByFXRYId(fxryId);
				if (real == null){
					entity.setId(null);
					entity.setCreateTime(new Date());
					entity.setRecordOrgId(getCurOrgId());
					service.create(entity);
					//保存通知信息
					saveNotice(entity);
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
	
	public void saveNotice(FXRYExecuteInfo entity){
		String fxryId = entity.getFxryId();
		FXRYBaseinfo fxryInfo = fxryService.get(fxryId);
		FxryNotice notice = new FxryNotice();
		notice.setFxryId(fxryId);
		notice.setName(fxryInfo.getName());//姓名
		notice.setCode(fxryInfo.getCode());//人员编号
		notice.setIdentityCard(fxryInfo.getIdentityCard());//身份证号
		notice.setSex(fxryInfo.getSex());//性别
		notice.setResponseOrg(fxryInfo.getResponseOrg());//矫正负责单位
		notice.setDateSAdjust(entity.getDateSAdjust());//矫正期限起
		notice.setDateEAdjust(entity.getDateEAdjust());//矫正期限止
		notice.setState(fxryInfo.getState());//当前状态
		notice.setDeviceCode(fxryInfo.getDeviceCode());//是否电子监管
		notice.setStatus("2");//通知状态1：已签收；2：待签收
		notice.setCreatedate(new Date());
		notice.setCreater(getUserId());
		notice.setOrgId(getCurOrgId());
		noticeService.create(notice);
	}
}
