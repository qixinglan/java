package com.nci.dcs.jzgl.rcbdgl.action;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.rcbdgl.model.ReportInfo;
import com.nci.dcs.jzgl.rcbdgl.service.ReportInfoService;
import com.nci.dcs.system.model.User;

public class ReportInfoAction extends BaseAction<ReportInfo>
{
	
	@Autowired
	private ReportInfoService service;
	
	@Autowired
	private FXRYBaseInfoService baseInfoService;
	
	public ReportInfoService getService() {
		return service;
	}

	public void setService(ReportInfoService service) {
		this.service = service;
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
		return null;
	}
	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		
		return null;
	}

	public String getReportInitData() {
		User user = getUser();
		this.entity.setReportType("1");
		this.entity.setReportDateTime(DateTimeFmtSpec.getDateTimeFormat().format(new Date()));
		this.entity.setCreater(user.getName());
		this.entity.setCreateTime(DateTimeFmtSpec.getDateFormat().format(new Date()));
		return SUCCESS;
	}
	
	public String search(){
		//目前暂未实现登录功能呢，暂无User，
		//String orgID=getUser().getWunit().getBm();
		//String orgID="1";
		String fxryId=this.request.getParameter("id");
		//增加后台过滤字段
	    Page<ReportInfo> page = this.getRequestPage();	    
	    page.getCriterions().add(Restrictions.eq("fxryId", fxryId));
	    service.findPaged(page);
	    //FXRYBaseinfo baseInfo= baseInfoService.getFXRYBaseInfoById(fxryId, false, false);
	    //this.entity.setBaseinfo(baseInfo);			
		return "success";
	}
    
	public String Toadd() throws Throwable {
		// TODO Auto-generated method stub
		
		return "add";
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
