package com.nci.dcs.jzgl.dagl.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.dagl.model.AddressSelect;
import com.nci.dcs.jzgl.dagl.service.DeviceCheckService;

@SuppressWarnings("serial")
public class DeviceCheckAction extends BaseAction<AddressSelect> {
	@Autowired
	private DeviceCheckService deviceCheckService;
	private String result = "";
	private AjaxFormResult ajaxFormResult;

	public String deviceCheck() {
		String deviceCode = request.getParameter("deviceCode");
		int type = Integer.parseInt(request.getParameter("type"));
		if (!CommonUtils.isNull(deviceCode)) {
			result = deviceCheckService.deviceCheck(type, deviceCode);
			if (result.split("@").length == 2) {
				ajaxFormResult = "true".equals(result.split("@")[0]) ? AjaxFormResult
						.success(result.split("@")[1]) : AjaxFormResult
						.error(result.split("@")[1]);
			} else {
				ajaxFormResult = AjaxFormResult.error("服务器错误");
			}
		}else{
			ajaxFormResult = AjaxFormResult.error("未选择需要检测的设备");
		}
		return SUCCESS;
	}
	public String deviceClose() {
		String deviceCode = request.getParameter("deviceCode");
		if (!CommonUtils.isNull(deviceCode)) {
			deviceCheckService.deviceClose(deviceCode);
			ajaxFormResult = AjaxFormResult.success("");
		}else{
			ajaxFormResult = AjaxFormResult.error("未选择需要检测的设备");
		}
		return SUCCESS;
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
