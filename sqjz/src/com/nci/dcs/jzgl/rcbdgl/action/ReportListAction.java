package com.nci.dcs.jzgl.rcbdgl.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryReportinfo;
import com.nci.dcs.jzgl.rcbdgl.service.ReportInfoService;
import com.nci.dcs.jzgl.rcbdgl.service.ReportListService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.DictionaryKeyValue;

public class ReportListAction extends BaseAction<ViewFxryReportinfo>
{
	
	@Autowired
	private ReportListService service;
	
	@Autowired
	private ReportInfoService reportInfoService;

	public ReportListService getService() {
		return service;
	}

	public void setService(ReportListService service) {
		this.service = service;
	}
	
	
	@Override
	public String list() throws Throwable {
		//增加后台过滤字段
		Page<ViewFxryReportinfo> page = this.getRequestPage();
		Bmb org = getCurOrg();
		Criterion crit = null;
		if (org.isQxsfj()) {
			OrganizationInfo curOrg = organizationInfoService.get(org.getBm());
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			crit = Restrictions.in("orgid", ids);
		} else if (org.isSfs()) {
			// 司法所按负责单位查询
			crit = Restrictions.eq("orgid", org.getBm());
		}
		if (crit != null) {
			page.getCriterions().add(crit);
		}
		page.getCriterions().add(Restrictions.eq("datastatus", 1));
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, 3);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		page.getCriterions().add(Restrictions.le("reportdate", c.getTime()));
		service.findPaged(page);
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

	
	public String search(){
		String orgID=this.getCurOrgId();
		//增加后台过滤字段
	    Page<ViewFxryReportinfo> page = this.fillPageWithJQGridRequest();	    
//	    page.getCriterions().add(Restrictions.eq("orgid", orgID));
//	    page.getCriterions().add(Restrictions.eq("datastatus", 1));
//		service.findPaged(page);
		List<SearchRule> result = JQGridSearchRuleParser.getSearchRules(this.jqgrid.getFilters());//获取所有searchRule
		SearchRule searchRule = new SearchRule();
		searchRule.setField("orgid");
		searchRule.setData(orgID);
		searchRule.setOp("eq");
		result.add(searchRule);
		SearchRule searchRule1 = new SearchRule();
		searchRule1.setField("datastatus");
		searchRule1.setData("1");
		searchRule1.setOp("eq");
		result.add(searchRule1);
		//去掉page内的查询条件
		// 日期查询条件补充时分秒
		String field = "";
		for (SearchRule temp : result) {
			field = temp.getField();
			if (field != null && ("reportdate").equals(field)) {
				temp.setData(temp.getData() + "-01 00:00:00");
				break;
			}
		}
		service.findPaged(page,result);
		return "success";
	}
    
	public String getReportInfoByRYID()
	{
		 String fxryId=this.request.getParameter("id");
		 Page<ViewFxryReportinfo> page = this.getRequestPage();	    		  
		 page.getCriterions().add(Restrictions.eq("fxryid", fxryId));
		 service.findPaged(page);
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
	
	@Autowired
	protected  FXRYBaseInfoService fxryService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	/**
	 * 加载服刑人员下拉列表
	 * @return
	 * @throws Throwable 
	 */
	public String fxrysJson() throws Throwable{
		try {
			//获取信息
			List<FXRYBaseinfo> fxryList= getFxryxx();
			//封装参数dicts
			Map<String, List<DictionaryKeyValue>> dicts = new HashMap<String,List<DictionaryKeyValue>>();
			List<DictionaryKeyValue> dictionaryKeyValueList = new ArrayList<DictionaryKeyValue>();
			for (FXRYBaseinfo item : fxryList){
				DictionaryKeyValue keyvalue = new DictionaryKeyValue();
				keyvalue.setName(item.getName());
				keyvalue.setCode(item.getId().toString());
				keyvalue.setUsing(true);
				dictionaryKeyValueList.add(keyvalue);
			}
			dicts.put("FXRYS", dictionaryKeyValueList);
			this.response.setHeader("Last-Modified", HttpUtil.date2LastModified(new Date()));
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(dicts);
			this.response.getWriter().write(result.toString());
		} catch (IOException e) {
		}
		return "none";
	}
	public List<FXRYBaseinfo> getFxryxx() throws Throwable {
		return fxryService.find(getCurOrg().getBm(),FXRYState.ZJ);
	}
}
