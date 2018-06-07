package com.nci.dcs.jzgl.bbgl.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.bbgl.model.CcWorkStatisticTable;
import com.nci.dcs.jzgl.bbgl.service.WorkStatTableService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;

public class WorkStatTableAction extends BaseAction<CcWorkStatisticTable>{
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	@Autowired
	private WorkStatTableService service;
	@Autowired OrganizationInfoService orgService;
	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}

	public WorkStatTableService getService() {
		return service;
	}

	public void setService(WorkStatTableService service) {
		this.service = service;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		String orgId = getUser().getWunit().getBm();
		String opt = request.getParameter("opt");
		getRequestPage();
		if("view".equals(opt)){
			String id = request.getParameter("id");
			this.entity = service.get(id); 
			page.getCriterions().add(Restrictions.eq("superOrg", orgId));
			page.getCriterions().add(Restrictions.or(Restrictions.eq("status", "5"),Restrictions.or(Restrictions.eq("status", "3"), Restrictions.eq("status", "4"))));
			page.getCriterions().add(Restrictions.eq("year", entity.getYear()));
			page.getCriterions().add(Restrictions.eq("month", entity.getMonth()));
		}else{
			page.getCriterions().add(Restrictions.eq("orgid", orgId));
		}
		service.findPaged(page, null);
		return SUCCESS;
	}
	/**
	 * 审核不成功，退回
	 * @return
	 */
	public String sendBack(){
		String id = request.getParameter("id");
		String reason = request.getParameter("reason");
		this.entity = service.get(id);
		this.entity.setStatus("4");//被退回
		this.entity.setReason(reason);
		service.update(entity);
		ajaxFormResult = AjaxFormResult.success(entity.getId());
		return SUCCESS;
	}
	/**
	 * 审核成功
	 * @return
	 */
	public String sendSuccess(){
		String id = request.getParameter("id");
		this.entity = service.get(id);
		this.entity.setStatus("5");//审核成功
		service.update(entity);
		ajaxFormResult = AjaxFormResult.success(entity.getId());
		return null;
	}
	/**
	 * 上报
	 * @return
	 */
	public String reportTable(){
		String id = request.getParameter("id");
		this.entity = service.get(id);
		this.entity.setStatus("3");//上报
		this.entity.setSuperOrg(getUser().getWunit().getSupOrg());
		service.update(entity);
		List childTable = service.getChildTable(entity.getOrgid(),entity.getMonth(),entity.getYear());
		for(int i=0;i<childTable.size();i++){
			CcWorkStatisticTable updateCC = (CcWorkStatisticTable)childTable.get(i);
			updateCC.setStatus("5");
			service.update(updateCC);
		}
		
		if(!service.checkTable(entity.getYear(),entity.getMonth(),entity.getSuperOrg())){
			CcWorkStatisticTable superTable = new CcWorkStatisticTable();
			superTable.setId(CommonUtils.uuid());
			superTable.setYear(entity.getYear());
			superTable.setMonth(entity.getMonth());
			superTable.setOrgid(entity.getSuperOrg());
			superTable.setStatus("1");
			service.create(superTable);
		}
		return null;
	}
	public String viewReport(){
		return SUCCESS;
	}
	public String statReport(){
		return SUCCESS;
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
		String id = request.getParameter("id");
		service.delete(id);
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
		try{
			String time = request.getParameter("time");
			String date[] = time.split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			boolean res = service.checkTable(year,month,getUser().getWunit().getBm());
			if(!res){
				this.entity.setId(CommonUtils.uuid());
				this.entity.setYear(year);
				this.entity.setMonth(month);
				this.entity.setOrgid(getUser().getWunit().getBm());
				this.entity.setStatus("1");
				service.create(entity);
				ajaxFormResult = AjaxFormResult.success(entity.getId());
			}else{
				throw new Exception("该时间报表已建立");
			}
		}catch(Exception e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
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
	
	public String reCreateTable(){
		String id = request.getParameter("id");
		this.entity = service.get(id);
		CcWorkStatisticTable newSat = new CcWorkStatisticTable();
		newSat.setId(id);
		newSat.setYear(entity.getYear());
		newSat.setMonth(entity.getMonth());
		newSat.setOrgid(getUser().getWunit().getBm());
		newSat.setStatus("1");
		service.delete(id);
		service.create(newSat);
		return null;
	}
	
	/**
	 * 定时任务，每月定时生成报表
	 * 司法所初始化基本数据
	 * 
	 */
	public void executeSchedule(){
		System.out.println("定时生成报表Start");
		Calendar c = Calendar.getInstance();
		
		List<OrganizationInfo> orglist = orgService.find();
		
		for(int i=0;i<orglist.size();i++){
			OrganizationInfo temOrg = orglist.get(i);
			if("1".equals(temOrg.getOrgType())||"2".equals(temOrg.getOrgType())
					||"3".equals(temOrg.getOrgType())){
				CcWorkStatisticTable entity = new CcWorkStatisticTable();
				entity.setYear(c.get(Calendar.YEAR));
				entity.setMonth(c.get(Calendar.MONTH));
				entity.setOrgid(orglist.get(i).getOrgId());
				entity.setSuperOrg(orglist.get(i).getSupOrg().getOrgId());
				entity.setStatus("1");
				service.update(entity);
			}
		}
		System.out.println("生成报表End");
	}

	/**
	 * 手动调用存储过程
	 */
	public void updateTable(){
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			Connection connection  = DriverManager.getConnection("jdbc:oracle:thin:@10.3.1.115:1521:sfjjk","sqjz","sqjz");
			CallableStatement c = connection.prepareCall("{call cc_statistics_fxry_per_month()}");
			
			c.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
