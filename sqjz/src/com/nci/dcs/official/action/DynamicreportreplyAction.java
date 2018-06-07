package com.nci.dcs.official.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.official.model.CcDynamicreport;
import com.nci.dcs.official.model.CcDynamicreportreply;
import com.nci.dcs.official.service.DynamicreportService;
import com.nci.dcs.official.service.DynamicreportreplyService;

public class DynamicreportreplyAction extends BaseAction<CcDynamicreportreply>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6969076683544068074L;

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
	
	private AjaxFormResult ajaxFormResult;
	
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}
	
	@Autowired
	private DynamicreportreplyService service;
	
	@Autowired
	private DynamicreportService reportService;
	
	/**
	 * @name 保存批复信息
	 * @return
	 * @author caolj
	 * @date 2014年9月19日 下午2:17:21
	 * @message:
	 */
	public String savePfxx(){
		String id = request.getParameter("id");
		if(id!=null && !("").equals(id)){
			String relyContent = request.getParameter("relyContent");
			entity = service.get(id);
			entity.setRelyContent(relyContent);
		}else{
			entity.setId(CommonUtils.uuid());
		}
		Date date = new Date();
		entity.setReplydate(date);
		entity.setReplyPersonId(getUserId());
		entity.setReplyPersonName(getUser().getName());
		service.create(entity);
		//更新上报信息状态为已签收
		String reportId = request.getParameter("report.id");
		CcDynamicreport dynamicreport = reportService.get(reportId);
		dynamicreport.setStatus("1");//已签收
		reportService.create(dynamicreport);
		ajaxFormResult = new AjaxFormResult(true, "");
		return SUCCESS;
	}
	
	/**
	 * @name 获取批复信息
	 * @return
	 * @author caolj
	 * @date 2014年9月19日 下午2:28:53
	 * @message:
	 */
	public String searchPfxx(){
		getRequestPage();
		String id = request.getParameter("id");
		this.entity = service.get(id);
		return SUCCESS;
	}

}
