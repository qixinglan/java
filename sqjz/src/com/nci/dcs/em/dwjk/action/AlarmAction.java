package com.nci.dcs.em.dwjk.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;
import com.nci.dcs.em.dwjk.service.AlarmService;

public class AlarmAction extends BaseAction<CcAlarmInfo> {

	@Autowired
	private AlarmService service;

	public AjaxFormResult ajaxFormResult;

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	public AlarmService getService() {
		return service;
	}

	public CcAlarmInfo datas = new CcAlarmInfo();
	public List<Object> pageData = new ArrayList<Object>();
	public Object data;

	public void setService(AlarmService service) {
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

	public String getUntratedInfos() {
		String id = request.getParameter("id");
		this.getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", id));
		page.getCriterions().add(Restrictions.eq("status", "2"));
		// service.findPaged(page, "from CcAlarmInfo where id=? and zt=0",id);
		service.findPaged(page);
		return SUCCESS;
	}
	
	public String getUntreatedInfoStatictis() {
		String id = request.getParameter("id");
		
		data = (Map)service.wclbjStatic(id);
		ajaxFormResult = AjaxFormResult.success("");
		return SUCCESS;
	}
	

	public String getSfsSta() {
		String cCode = request.getParameter("cCode");
		pageData = service.getSfsSta(cCode);
		return SUCCESS;
	}

	public String dealAlarm() {
		String username = "";
		if (getUser() != null)
			username = getUser().getName();
		String[] idsArr = request.getParameter("ids").split(",");
		List<String> idsList = new ArrayList<String>();
		for (int i = 0; i < idsArr.length; i++) {
			idsList.add(idsArr[i]);
		}
		service.dealAlarm(idsList, username);
		return SUCCESS;
	}

	public String todoDealAlarm() {
		try {
			String username = "";
			if (getUser() != null)
				username = getUser().getName();
			String[] idsArr = request.getParameter("ids").split(",");
			List<String> idsList = new ArrayList<String>();
			for (int i = 0; i < idsArr.length; i++) {
				idsList.add(idsArr[i]);
			}
			service.dealAlarm(idsList, username);
			ajaxFormResult = AjaxFormResult.success("");
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String writeContent() {
		try {
			String username = "";
			if (getUser() != null)
				username = getUser().getName();
			String id = request.getParameter("id");
			String jlnr = request.getParameter("jlnr");
			CcAlarmInfo ccAlarmInfo = service.get(id);
			ccAlarmInfo.setRecord(jlnr);
			ccAlarmInfo.setStatus("1");
			ccAlarmInfo.setHandler(username);
			ccAlarmInfo.setHandleTime(new Date());
			service.update(ccAlarmInfo);
			ajaxFormResult = AjaxFormResult.success("");
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String getAlarmInfo() {
		String id = request.getParameter("id");
		page = new Page<CcAlarmInfo>();
		this.getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", id));
		/*
		 * String alarmLevel = request.getParameter("alarmLevel"); String
		 * handler = request.getParameter("handler"); String alarmType =
		 * request.getParameter("alarmType"); String status =
		 * request.getParameter("status");
		 * 
		 * String handleSTime = request.getParameter("handleSTime"); String
		 * handleETime = request.getParameter("handleETime"); String alarmSTime
		 * = request.getParameter("alarmSTime"); String alarmETime =
		 * request.getParameter("alarmSTime");
		 * 
		 * entity.setFxryId(id); if(StringUtils.isNotEmpty(alarmLevel))
		 * entity.setAlarmLevel(alarmLevel);
		 * if(StringUtils.isNotEmpty(alarmLevel)) entity.setHandler(handler);
		 * if(StringUtils.isNotEmpty(alarmLevel))
		 * entity.setAlarmType(alarmType);
		 * if(StringUtils.isNotEmpty(alarmLevel)) entity.setStatus(status);
		 * if(StringUtils.isNotEmpty(alarmLevel))
		 * values[0]=alarmSTime+"|"+alarmETime;
		 * if(StringUtils.isNotEmpty(alarmLevel)){ if(values[0]!=null)
		 * values[0]=handleSTime+"|"+handleETime; else
		 * values[1]=handleSTime+"|"+handleETime; }
		 */
		// service.findPaged(this.getRequestPage(),entity,values);
		service.findPaged(page);
		return SUCCESS;
	}

	public CcAlarmInfo getDatas() {
		return datas;
	}

	public void setDatas(CcAlarmInfo datas) {
		this.datas = datas;
	}

}
