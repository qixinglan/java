package com.nci.dcs.jzgl.bbgl.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.bbgl.model.CcZtzjStatisticMonth;
import com.nci.dcs.jzgl.bbgl.service.StatisticsFxryPerMonthService;
import com.nci.dcs.jzgl.bbgl.service.YgztzjStatService;
import com.nci.dcs.official.service.OrganizationInfoService;

public class YgztzjStatAction extends BaseAction<CcZtzjStatisticMonth>{
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	@Autowired
	private YgztzjStatService service;
	@Autowired
	private StatisticsFxryPerMonthService perMonthService;
	
	public StatisticsFxryPerMonthService getPerMonthService() {
		return perMonthService;
	}

	public void setPerMonthService(StatisticsFxryPerMonthService perMonthService) {
		this.perMonthService = perMonthService;
	}
	@Autowired OrganizationInfoService orgService;
	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
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
			page.getCriterions().add(Restrictions.eq("supOrgId", orgId));
			page.getCriterions().add(Restrictions.or(Restrictions.eq("status", "5"),Restrictions.or(Restrictions.eq("status", "3"), Restrictions.eq("status", "4"))));
			page.getCriterions().add(Restrictions.eq("year", entity.getYear()));
			page.getCriterions().add(Restrictions.eq("month", entity.getMonth()));
		}else{
			page.getCriterions().add(Restrictions.eq("orgId", orgId));
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
		return SUCCESS;
	}
	/**
	 * 上报
	 * @return
	 */
	public String reportTable(){
		String id = request.getParameter("id");
		this.entity = service.get(id);
		this.entity.setStatus("3");//上报
		this.entity.setReporttime(new Date());
		service.update(entity);
		List childTable = service.getChildTable(entity.getOrgId(),entity.getMonth(),entity.getYear());
		for(int i=0;i<childTable.size();i++){
			CcZtzjStatisticMonth updateCC = (CcZtzjStatisticMonth)childTable.get(i);
			updateCC.setStatus("5");
			if(updateCC.getReporttime()==null)
			updateCC.setReporttime(new Date());
			service.update(updateCC);
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
		try{
			String id = request.getParameter("id");
			service.delete(id);
		}catch(Exception e){
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
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
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			Date tableTime = sf.parse(year+"-"+month);
			Date currentTime = new Date();
			if(tableTime.getTime()>currentTime.getTime()){
				throw new Exception("创建报表时间不能大于当前月份!");
			}
			String res = service.checkTable(year,month,getUser().getWunit().getBm());
			if(res==null){
				this.entity.setId(CommonUtils.uuid());
				this.entity.setYear(year);
				this.entity.setMonth(month);
				this.entity.setOrgId(getUser().getWunit().getBm());
				this.entity.setStatus("1");
				service.create(entity);
				ajaxFormResult = AjaxFormResult.success(entity.getId());
			}else{
				throw new Exception(res);
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
		CcZtzjStatisticMonth newSat = new CcZtzjStatisticMonth();
		this.entity = service.get(id);
		if("1".equals(getUser().getWunit().getOrgType())||"2".equals(getUser().getWunit().getOrgType())){
			newSat.setId(id);
			newSat.setYear(entity.getYear());
			newSat.setMonth(entity.getMonth());
			newSat.setOrgId(getUser().getWunit().getBm());
			newSat.setStatus("1");
			service.delete(id);
		}else if("3".equals(getUser().getWunit().getOrgType())){
			CcZtzjStatisticMonth perMonth = service.get(entity.getOrgId(),entity.getYear(),entity.getMonth());
			if(perMonth==null)
				return null;
			newSat.setId(id);
			newSat.setYear(perMonth.getYear());
			newSat.setMonth(perMonth.getMonth());
			newSat.setOrgId(perMonth.getOrgId());
			newSat.setStatus("1");
			newSat.setYjjsJzmj(perMonth.getYjjsJzmj());
			newSat.setYjjsSyb(perMonth.getYjjsSyb()); 
			newSat.setYjjsJftr(perMonth.getYjjsJftr());
			newSat.setYjjsClqk(perMonth.getYjjsClqk()); 
			newSat.setYjjsGljd(perMonth.getYjjsGljd()); 
			newSat.setGzdwGzry(perMonth.getGzdwGzry());
			newSat.setGzdwZzZs(perMonth.getGzdwZzZs()); 
			newSat.setGzdwZzSyb(perMonth.getGzdwZzSyb()); 
			newSat.setGzdwZzHtz(perMonth.getGzdwZzHtz());
			newSat.setGzdwZzDz(perMonth.getGzdwZzDz()); 
			newSat.setGzdwZzPx(perMonth.getGzdwZzPx());
			newSat.setGzdwCdgjZs(perMonth.getGzdwCdgjZs()); 
			newSat.setGzdwCdgjPx(perMonth.getGzdwCdgjPx()); 
			newSat.setGzdwZyzZs(perMonth.getGzdwZyzZs()); 
			newSat.setGzdwZyzPx(perMonth.getGzdwZyzPx());
			newSat.setGnkzJzRs(perMonth.getGnkzJzRs()); 
			newSat.setGnkzJzCs(perMonth.getGnkzJzCs());
			newSat.setGnkzShzdRs(perMonth.getGnkzShzdRs()); 
			newSat.setGnkzShzdTtcs(perMonth.getGnkzShzdTtcs());
			newSat.setGnkzShzdGrcs(perMonth.getGnkzShzdGrcs()); 
			newSat.setGnkzXlzxRs(perMonth.getGnkzXlzxRs());
			newSat.setGnkzXlzxTtcs(perMonth.getGnkzXlzxTtcs()); 
			newSat.setGnkzXlzxGrcs(perMonth.getGnkzXlzxGrcs()); 
			newSat.setGnkzJybzJnpx(perMonth.getGnkzJybzJnpx());
			newSat.setGnkzJybzJyzd(perMonth.getGnkzJybzJyzd());
			newSat.setGnkzJybzJyxx(perMonth.getGnkzJybzJyxx());
			newSat.setGnkzGyldCs(perMonth.getGnkzGyldCs()); 
			newSat.setGnkzGyldRs(perMonth.getGnkzGyldRs());
			newSat.setGnkzSwry(perMonth.getGnkzSwry()); 
			newSat.setGnkzQtkz(perMonth.getGnkzQtkz()); 
			newSat.setRcglZklh(perMonth.getRcglZklh()); 
			newSat.setRcglMtbd(perMonth.getRcglMtbd()); 
			newSat.setRcglRyjl(perMonth.getRcglRyjl()); 
			newSat.setRcglRycl(perMonth.getRcglRycl()); 
			newSat.setRcglDwjl(perMonth.getRcglDwjl());
			newSat.setGzdwFzgzry(perMonth.getGzdwFzgzry());
			newSat.setSupOrgId(perMonth.getSupOrgId()); 
			newSat.setReason(perMonth.getReason());
			newSat.setStatus(perMonth.getStatus()); 
			newSat.setReporttime(perMonth.getReporttime()); 
			newSat.setReportperson(perMonth.getReportperson()); 
		}
		service.create(newSat);
		
		return null;
	}
	
	/**
	 * 定时任务，每月定时生成司法所报表
	 * 
	 */
	public void executeSchedule(){
		System.out.println("定时生成报表");
	}
}
