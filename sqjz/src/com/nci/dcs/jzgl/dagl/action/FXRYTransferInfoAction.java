package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYTransferInfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYTransferInfoService;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;
import com.nci.dcs.jzgl.fingerprint.service.FingerPrintManagerService;


public class FXRYTransferInfoAction extends BaseAction<FXRYTransferInfo>{
	
	@Autowired
	FXRYTransferInfoService service;
	@Autowired
	private FingerPrintManagerService fingerService;
	@Autowired
	private FXRYBaseInfoService basicService;
	
	
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
			this.entity = new FXRYTransferInfo();
		}
		return "success";
	}
	
	public String saveOrUpdate(){
		try{
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
			}
			entity.setOutOrgId(getCurOrgId());
			entity.setOutTime(new Date());
			service.update(entity);
			ajaxFormResult = AjaxFormResult.success(entity.getId());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("保存出错");
		}
		return "success";
	}
	
	public String sfsMoveOut(){
		try {
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
			}
			entity.setOutOrgId(getCurOrgId());
			entity.setOutTime(new Date());
			service.sfsMoveOut(entity, getCurOrgId());
			ajaxFormResult = AjaxFormResult.success("人员已转出！");
			
			
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员转出失败:" + e.getMessage());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("人员转出失败");
		}
		return "success";
	}
	
	public String sfjMoveOut(){
		try {
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
			}
			entity.setOutOrgId(getCurOrgId());
			entity.setOutTime(new Date());
			FXRYBaseinfo fXRYBaseinfo=basicService.get(entity.getFxryId());
			service.sfjMoveOut(entity,getCurOrgId(), fXRYBaseinfo.getDeviceCode(),getUserId());
			ajaxFormResult = AjaxFormResult.success("人员已转出！");
			
			FXRYBaseinfo basicinfo = basicService.get(entity.getFxryId());
			/*List<CcFingerprintMachine> recList = fingerService.queryMachine(entity.getReceiveOrgId());
			for(CcFingerprintMachine machine : recList){
				fingerService.addTask(5, basicinfo.getFingerPrintNo(), machine.getMachineId(), null);//上传
			}
			List<CcFingerprintMachine> outList = fingerService.queryMachine(entity.getOutOrgId());
			for(CcFingerprintMachine machine :outList){
				fingerService.addTask(4, basicinfo.getFingerPrintNo(), machine.getMachineId(), null);//删除
			}*/
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("解除设备出错");
		}
		return "success";
	}
	
	public String sfjMoveOutBJ(){
		try {
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
			}
			entity.setId(null);
			entity.setOutOrgId(getCurOrgId());
			entity.setOutTime(new Date());
			FXRYBaseinfo fXRYBaseinfo=basicService.get(entity.getFxryId());
			service.sfjMoveOutBJ(entity,getCurOrgId(), fXRYBaseinfo.getDeviceCode(),getUserId());
			ajaxFormResult = AjaxFormResult.success("人员已转出！");
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员转出失败:" + e.getMessage());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("人员转出失败");
		}
		return "success";
	}
	
	public String cancelSfsMoveOut(){
		try {
			service.cancelSfsMoveOut(entity.getFxryId(), getCurOrgId());
			ajaxFormResult = AjaxFormResult.success("人员转出已取消！");
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("人员转出取消失败:" + e.getMessage());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("人员转出取消失败");
		}
		return "success";
	}
	
	public String sfsMoveIn(){
		try {
			service.sfsMoveIn(entity.getFxryId(), getCurOrgId());
			ajaxFormResult = AjaxFormResult.success("人员已接收！");
			//TODO 清除之前的矫正小组信息
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("待转入人员接收失败:" + e.getMessage());
		}
		catch (Exception e){
			ajaxFormResult = AjaxFormResult.error("待转入人员接收失败");
		}
		return "success";
	}
	
	public String sfjMoveIn(){
		try {
			service.sfjMoveIn(entity.getFxryId(), getCurOrgId());
			ajaxFormResult = AjaxFormResult.success("人员已接收！");
			//TODO 清除之前的矫正小组信息
		}
		catch (FXRYStateChangeException e){
			ajaxFormResult = AjaxFormResult.error("待转入人员接收失败:" + e.getMessage());
		}
		return "success";
	}
	
	
	public FingerPrintManagerService getFingerService() {
		return fingerService;
	}

	public void setFingerService(FingerPrintManagerService fingerService) {
		this.fingerService = fingerService;
	}
	
	public FXRYBaseInfoService getBasicService() {
		return basicService;
	}

	public void setBasicService(FXRYBaseInfoService basicService) {
		this.basicService = basicService;
	}
}
