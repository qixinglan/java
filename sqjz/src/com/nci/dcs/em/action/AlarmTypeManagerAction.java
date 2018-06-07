package com.nci.dcs.em.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.em.model.CcAlarmType;
import com.nci.dcs.em.model.CcSuperviseDevice;
import com.nci.dcs.em.service.AlarmTypeManagerService;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

public class AlarmTypeManagerAction extends BaseAction<CcAlarmType> {

	public List<CcAlarmType> list;
	public CcAlarmType itm;

	@Autowired
	private AlarmTypeManagerService service;
	@Autowired
	private EmMessageSendService emMessageSendService;

	public AlarmTypeManagerService getService() {
		return service;
	}

	public void setService(AlarmTypeManagerService service) {
		this.service = service;
	}

	public String search() {
		service.findPaged(this.getRequestPage());

		return "success";
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		list = service.find();
		return "success";
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		this.entity = service.get(id);
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
		return "success";
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		service.update(entity);
		// 设置静默时间并发送至各个定位主机
		if (entity.getSilentTime() != null) {
			//设置中为小时，接口中为分钟
			emMessageSendService.setSilentListByOrg(
					Integer.toString(entity.getSilentTime() * 60), rootOrgId);
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
