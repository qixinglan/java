package com.nci.dcs.jzgl.dagl.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.jzgl.dagl.model.Address;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.service.AddressService;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;
import com.nci.dcs.jzgl.sync.service.ViewCcFxrySyncinfoService;

public class ZJRYAction extends FXRYAction{
	@Autowired
	FXRYExecuteInfoService executeServcie;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	private ViewCcFxrySyncinfoService viewCcFxrySyncinfoService;

	//绑定设备或报道登记
	public String saveJBSFXX(){
		try{
			FXRYBaseinfo real = null;
			String deviceCode = entity.getDeviceCode();
			entity.setDeviceCode(null);
			//报到登记人员无id
			if (StringUtils.isBlank(entity.getId())){
				entity.setId(null);
				entity.setCreateTime(new Date());
				String orgId = getCurOrgId();
				entity.setRecordOrgId(orgId);
				entity.setResponseOrg(orgId);
				//TODO 同机构的人员同时保存时，重码
				entity.setCode(generateCode());
				real = entity;
				
			}
			//绑定设备操作，人员有id
			else{
				real = service.get(entity.getId());
				real.copyBaseInfo(entity);
			}
			//绑定设备
			if (!StringUtils.isBlank(deviceCode) && !deviceCode.equals(real.getDeviceCode())){
				real.setDeviceCode(deviceCode);
				service.equip(deviceCode,real,getUserId(),getCurOrgId());//绑定设备
				service.createWithEquip(real);
			}
			//报到登记未选择绑定设备
			else{
				service.create(real);
			}
			ajaxFormResult = AjaxFormResult.success(real.getId());
		}
		catch (Exception e){
			e.printStackTrace();
			logger.debug(e);
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return "success";
	}
	public String updateJBSFXX(){
		try{
			FXRYBaseinfo real = service.get(entity.getId());
			if ("1".equals(entity.getIssameHome())){
				//如果户籍地址与居住地相同
				entity.setHomeAddress(entity.getHouseAddress());
				Address home = real.getHomeAddress();
				if (home != null && !home.getId().equals(entity.getHouseAddress().getId())){
					//如果旧的户籍地址与新的居住地地址不相同，则删除
					addressService.delete(home);
				}
			}
			real.copyMoreInfo(entity);
			service.update(real);
			//同步至司法部
			viewCcFxrySyncinfoService.fxrySync(real.getId());
			
			ajaxFormResult = AjaxFormResult.success(real.getId());
		}
		catch (Exception e){
			logger.debug(e);
			ajaxFormResult = AjaxFormResult.error("未知,请联系管理员");
		}
		return "success";
	}
	
	private String getOrgPostCode(){
		try{
			return getCurOrg().getPostalCode();
		}
		catch (Exception e){
			return "110XXX";
		}
	}
	
	public String generateCode(){
		String postcode = getOrgPostCode();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date today = new Date();
		String date = sdf.format(today);
		String prefix = postcode + date;
		return service.getCode(prefix);
	}
	
	public String generate(){
		//表单后台初始化
		this.entity = new FXRYBaseinfo();
		return "success";
	}
	
	public String delete(){
		int deleted = 0;
		String lastId = "";
		try{
			String idsParam = this.request.getParameter("ids");
			if (idsParam != null && !idsParam.isEmpty()){
				String ids[] = idsParam.split(",");
				for (String id : ids){
					lastId = id;
					id = id.trim();
					FXRYExecuteInfo executeInfo = executeServcie.getByFXRYId(id);
					if (executeInfo != null){
						throw new Exception("已经报到的在矫人员的档案不能被删除");
					}
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
	//解除绑定
		public String removeEquip(){
			try{
				String fxryId = request.getParameter("fxryId");
				String deviceCode = request.getParameter("deviceCode");
				FXRYBaseinfo entity = service.get(fxryId);
				if (!StringUtils.isBlank(deviceCode) && deviceCode.endsWith(entity.getDeviceCode())){
					//service.unequip(fxryId);原先的方法service.unequip不用了
					service.unequip(deviceCode,getUserId(),getCurOrgId(),entity);//调用解除绑定方法
					//entity.setDeviceCode(null);//绑定设备为空不用了unequip方法中已有
					//service.save(entity);
					ajaxFormResult = AjaxFormResult.success(deviceCode);
				}
				else{
					ajaxFormResult = AjaxFormResult.error("设备编号不匹配！");
				}
			} catch(Exception e){
				e.printStackTrace();
				ajaxFormResult = AjaxFormResult.error("解除设备绑定失败-" + e.getMessage());
			}
			return "success";
		}
	
}
