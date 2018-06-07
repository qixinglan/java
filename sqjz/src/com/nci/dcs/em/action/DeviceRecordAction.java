package com.nci.dcs.em.action;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.service.DeviceRecordService;
import com.nci.dcs.system.service.UserService;

public class DeviceRecordAction extends BaseAction<CcDeviceRecord>{
	@Autowired
	private UserService useService;
	@Autowired
	private DeviceRecordService deviceRecordService;
	/*//分页查询某设备操作历史记录用于jqGrid
	public String searchHistory() {
		String deviceNumber = request.getParameter("deviceNumber");
		getRequestPage();
		page.getCriterions().add(Restrictions.eq("deviceNumber", deviceNumber));
		deviceRecordService.searchHistory(page);
		//显示操作人人名
		List<DeviceRecord> deviceRecords=page.getResult();
		for(DeviceRecord d:deviceRecords){
			d.setOperatePersionName(useService.get(d.getOperatePersion()).getName());
		}
		page.setResult(deviceRecords);
		return SUCCESS;
	}*/
	
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

}
