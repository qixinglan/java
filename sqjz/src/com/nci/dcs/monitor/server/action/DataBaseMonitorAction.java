package com.nci.dcs.monitor.server.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.monitor.server.model.DataBaseMonitor;
import com.nci.dcs.monitor.server.service.DataBaseMonitorService;
import com.opensymphony.xwork2.Action;

public class DataBaseMonitorAction  extends BaseAction<DataBaseMonitor>  {
	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月17日 下午3:02:41
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	public String search(){
		Page<DataBaseMonitor> page = this.getRequestPage();
		try {
			service.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@Autowired
	private DataBaseMonitorService service;

	@Override
	public String list() throws Throwable {
		return Action.SUCCESS;
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
