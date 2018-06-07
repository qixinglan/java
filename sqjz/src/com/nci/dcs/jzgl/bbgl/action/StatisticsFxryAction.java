package com.nci.dcs.jzgl.bbgl.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.jzgl.bbgl.model.CcStatisticsFxry;
import com.nci.dcs.jzgl.bbgl.model.CcStatisticsFxryPerMonth;
import com.nci.dcs.jzgl.bbgl.service.StatisticsFxryPerMonthService;
import com.nci.dcs.jzgl.bbgl.service.StatisticsFxryService;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.jzgl.bbgl.dto.*;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageResponse;

public class StatisticsFxryAction extends BaseAction<CcStatisticsFxry>{
	protected AjaxFormResult ajaxFormResult;
	private String fileName;
	private InputStream targetFile;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	@Autowired
	private StatisticsFxryService service;
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
    
	/*
	 * 获取每周各区县司法局累计接收、解除多少社区服刑人员等
	 * */
	public String weekList(){
	   String str=request.getParameter("myDate");
	   if(str==null || str=="undefined" || str.isEmpty()){
		   str=CommonUtils.DateToString(new Date());
	   }
	   List<WeekStaticData> list=service.getWeekStaticData(str);
	   JQGridPageResponse jqgridPage=new JQGridPageResponse(list);
	   this.setJqgridPage(jqgridPage);
	   return SUCCESS; 
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String excel() throws Throwable {
		CreateFileUtil util = CreateFileUtil.getInstance();
		// String[] head = new String(request.getParameter("colNames").getBytes(
		// "ISO-8859-1"), "gb2312").split(",");
		String[] head = new String[] { "区县名称","统计时限", "累计接收人员","累计解除人员", "在册管制人员", "在册假释人员", "在册缓刑人员",
				"在册暂予监外人员", "在册小计", "增加人员数量", "减少人员数量" };
		
		LinkedList<String> headTable = new LinkedList<String>(
				Arrays.asList(head));
		   String str=request.getParameter("myDate");
		   if(str==null || str=="undefined" || str.isEmpty()){
			   str=CommonUtils.DateToString(new Date());
		   }
		List<WeekStaticData> res=service.getWeekStaticData(str);
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		for (int i = 0; i < res.size(); i++) {
			LinkedList content = new LinkedList();
			
			content.add(res.get(i).getOrgName());
			content.add(res.get(i).getCurrDate());
			content.add(res.get(i).getAddData());
			content.add(res.get(i).getSubData());
			content.add(res.get(i).getGuanZhi());
			content.add(res.get(i).getJiaShi());
			content.add(res.get(i).getHuanXing());
			content.add(res.get(i).getZanJianWai());
			content.add(res.get(i).getXiaoJi());
			content.add(res.get(i).getWeekAddNum());
			content.add(res.get(i).getWeekSubNum());
			contentList.add(content);
		}
		try {
			fileName = util.create(headTable, contentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}

	public StatisticsFxryService getService() {
		return service;
	}

	public void setService(StatisticsFxryService service) {
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
		List childTable = service.getChildTable(entity.getOrgid(),entity.getMonth(),entity.getYear());
		for(int i=0;i<childTable.size();i++){
			CcStatisticsFxry updateCC = (CcStatisticsFxry)childTable.get(i);
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
			this.entity = service.get(id);
			CcStatisticsFxry sybb = service.getLastMonthTable(entity.getYear(),entity.getMonth(),getUser().getWunit().getBm());
			if(sybb==null){
				throw new Exception("该条为初始报表不能删除！");
			}else{
				this.entity = null;
			}
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
				this.entity.setOrgid(getUser().getWunit().getBm());
				this.entity.setStatus("1");
				boolean hasBeforeData = service.dbHasDataByOrgId(getUser().getWunit().getBm());
				if(hasBeforeData&&!"3".equals(getUser().getWunit().getBm())){
					CcStatisticsFxry sybb = service.getLastMonthTable(entity.getYear(),entity.getMonth(),entity.getOrgid());
					if(sybb!=null){
						initTable(entity);
					}else{
						throw new Exception("新建时间不能小于已有报表的最小时间!");
					}
				}
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
	/**
	 * 初始化当月报表中的累计值及上月值
	 * @param entity
	 * @throws Exception
	 */
	private void initTable(CcStatisticsFxry entity) throws Exception{
		CcStatisticsFxry sybb = service.getLastMonthTable(entity.getYear(),entity.getMonth(),entity.getOrgid());
		if(sybb!=null){
			entity.setSyzcrs(sybb.getSyzcrs()+sybb.getBdqkByzj()
				-sybb.getBdqkByjsCxhx()-sybb.getBdqkByjsCxjs()
				-sybb.getBdqkByjsJzdbg()-sybb.getBdqkByjsQmjc()
				-sybb.getBdqkByjsQt()-sybb.getBdqkByjsSjQt()
				-sybb.getBdqkByjsSjzx()-sybb.getBdqkByjsSwFzc()
				-sybb.getBdqkByjsSwZc());
		}else{
			throw new Exception("请先生成上月报表!");
		}
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
		CcStatisticsFxry newSat = new CcStatisticsFxry();
		this.entity = service.get(id);
		if("1".equals(getUser().getWunit().getOrgType())||"2".equals(getUser().getWunit().getOrgType())){
			newSat.setId(id);
			newSat.setYear(entity.getYear());
			newSat.setMonth(entity.getMonth());
			newSat.setOrgid(getUser().getWunit().getBm());
			newSat.setStatus("1");
			service.delete(id);
		}else if("3".equals(getUser().getWunit().getOrgType())){
			CcStatisticsFxryPerMonth perMonth = perMonthService.get(entity.getOrgid(),entity.getYear(),entity.getMonth());
			newSat.setId(id);
			newSat.setYear(perMonth.getYear());
			newSat.setMonth(perMonth.getMonth());
			newSat.setOrgid(perMonth.getOrgid());
			newSat.setStatus("1");
			newSat.setSyzcrs(perMonth.getSyzcrs());
			newSat.setBdqkByzj(perMonth.getBdqkByzj());
			newSat.setBdqkByjsQmjc(perMonth.getBdqkByjsQmjc());
			newSat.setBdqkByjsCxhx(perMonth.getBdqkByjsCxhx());
			newSat.setBdqkByjsCxjs(perMonth.getBdqkByjsCxjs());
			newSat.setBdqkByjsSjzx(perMonth.getBdqkByjsSjzx());
			newSat.setBdqkByjsQt(perMonth.getBdqkByjsQt());
			newSat.setXflbGzZs(perMonth.getXflbGzZs());
			newSat.setXflbGzXgjzl(perMonth.getXflbGzXgjzl());
			newSat.setXflbHxZs(perMonth.getXflbHxZs());
			newSat.setXflbHxXgjzl(perMonth.getXflbHxXgjzl());
			newSat.setXflbJs(perMonth.getXflbJs());
			newSat.setXflbZyjwzx(perMonth.getXflbZyjwzx());
			newSat.setXflbBdzzql(perMonth.getXflbBdzzql());
			newSat.setFzlxWhgjaq(perMonth.getFzlxWhgjaq());
			newSat.setFzlxWhggaq(perMonth.getFzlxWhggaq());
			newSat.setFzlxPhjjzx(perMonth.getFzlxPhjjzx());
			newSat.setFzlxQfgm(perMonth.getFzlxQfgm());
			newSat.setFzlxQfcc(perMonth.getFzlxQfcc());
			newSat.setFzlxFhsh(perMonth.getFzlxFhsh());
			newSat.setFzlxWhgfly(perMonth.getFzlxWhgfly());
			newSat.setFzlxTwsh(perMonth.getFzlxTwsh());
			newSat.setFzlxDz(perMonth.getFzlxDz());
			newSat.setFzlxQt(perMonth.getFzlxQt());
			newSat.setHjCz(perMonth.getHjCz());
			newSat.setHjNc(perMonth.getHjNc());
			newSat.setHjGat(perMonth.getHjGat());
			newSat.setHjWg(perMonth.getHjWg());
			newSat.setHjQt(perMonth.getHjQt());
			newSat.setXbN(perMonth.getXbN());
			newSat.setXbV(perMonth.getXbV());
			newSat.setMzH(perMonth.getMzH());
			newSat.setMzSs(perMonth.getMzSs());
			newSat.setNl18(perMonth.getNl18());
			newSat.setNl1845(perMonth.getNl1845());
			newSat.setNl4660(perMonth.getNl4660());
			newSat.setNl61(perMonth.getNl61());
			newSat.setWhcdDzys(perMonth.getWhcdDzys());
			newSat.setWhcdGz(perMonth.getWhcdGz());
			newSat.setWhcdCzyx(perMonth.getWhcdCzyx());
			newSat.setJyjxJy(perMonth.getJyjxJy());
			newSat.setJyjxJx(perMonth.getJyjxJx());
			newSat.setJyjxWn(perMonth.getJyjxWn());
			newSat.setJyjxWy(perMonth.getJyjxWy());
			newSat.setByJg(perMonth.getByJg());
			newSat.setByZacf(perMonth.getByZacf());
			newSat.setByCdjx(perMonth.getByCdjx());
			newSat.setByJzdbg(perMonth.getByJzdbg());
			newSat.setByJwzx(perMonth.getByJwzx());
			newSat.setLjxmCxhx(perMonth.getLjxmCxhx());
			newSat.setLjxmCxjs(perMonth.getLjxmCxjs());
			newSat.setLjxmCxjw(perMonth.getLjxmCxjw());
			newSat.setLjxmJg(perMonth.getLjxmJg());
			newSat.setLjxmZacf(perMonth.getLjxmZacf());
			newSat.setLjxmJzdbg(perMonth.getLjxmJzdbg());
			newSat.setLjxmCdjx(perMonth.getLjxmCdjx());
			newSat.setLjxmXgjzl(perMonth.getLjxmXgjzl());
			newSat.setLjxmJcjzl(perMonth.getLjxmJcjzl());
			newSat.setLjxmJsjzry(perMonth.getLjxmJsjzry());
			newSat.setLjxmJcjzry(perMonth.getLjxmJcjzry());
			newSat.setSuperOrg(perMonth.getSuperOrg());
			newSat.setBdqkByjsSjQt(perMonth.getBdqkByjsSjQt());
			newSat.setBdqkByjsSwZc(perMonth.getBdqkByjsSwZc());
			newSat.setBdqkByjsSwFzc(perMonth.getBdqkByjsSwFzc());
			newSat.setBdqkByjsJzdbg(perMonth.getBdqkByjsJzdbg());
			newSat.setLjxmZfz(perMonth.getLjxmZfz());
			newSat.setByLtg(perMonth.getByLtg());
			newSat.setByZfz(perMonth.getByZfz());
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
	
	public InputStream getTargetFile() {
		CreateFileUtil util = CreateFileUtil.getInstance();
		try {
			return util.getFileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
