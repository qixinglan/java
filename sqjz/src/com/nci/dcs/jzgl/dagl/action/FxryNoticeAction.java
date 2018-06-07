package com.nci.dcs.jzgl.dagl.action;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.dagl.model.FxryNotice;
import com.nci.dcs.jzgl.dagl.service.FxryNoticeService;
import com.nci.dcs.official.service.OrganizationInfoService;

public class FxryNoticeAction  extends BaseAction<FxryNotice>{

	/**
	 * @name 
	 * @author caolj
	 * @date 2015年3月30日 上午11:44:08
	 * @message:
	 */
	private static final long serialVersionUID = -3019850165361719455L;

	@Autowired
	private FxryNoticeService service;
	
	@Autowired
	private OrganizationInfoService orgService;
	
	@Override
	public String list() throws Throwable {
		return SUCCESS;
	}
	
	/**
	 * @name 获取列表信息
	 * @return
	 * @author caolj
	 * @date 2015年3月30日 上午11:47:59
	 * @message:
	 */
	@SuppressWarnings("unchecked")
	public String getData() {
		String orgId = getCurOrgId();
		this.getRequestPage();
		List<SearchRule> result = JQGridSearchRuleParser.getSearchRules(this.jqgrid.getFilters());//获取所有searchRule
		//机构信息
		if (orgId != null && !orgId.equals("1")) {
			List<String> orgIds = orgService.getChildrenIds(orgId);
			String childs = orgIds.toString();
			childs = childs.replace(" ", "");
			childs = childs.replace("[", "");
			childs = childs.replace("]", "");
			SearchRule searchRule = new SearchRule();
			searchRule.setField("orgId");
			searchRule.setData(childs);
			searchRule.setOp("in");
			result.add(searchRule);
		}
		//鉴定设备信息
		List<Criterion> criterions = page.getCriterions();
		page.getCriterions().removeAll(criterions);
		String deviceCode="";
		String field="";
		for(SearchRule temp:result){
			field = temp.getField();
			if(field!=null && ("deviceCode").equals(field)){
				deviceCode = temp.getData();
				if("2".equals(deviceCode)){
					temp.setOp("nu");
				}else{
					temp.setOp("nn");
				}
			}
		}
		//通知状态
		SearchRule searchRule = new SearchRule();
		searchRule.setField("status");
		searchRule.setData("2");
		searchRule.setOp("eq");
		result.add(searchRule);
		
		service.findPaged(page,result);
		List<FxryNotice> resultList = page.getResult();
		long adjustPeriod = 0, remainDays = 0;
		Date now = new Date();
		for (FxryNotice temp : resultList) {
			adjustPeriod = (temp.getDateEAdjust().getTime() - temp
					.getDateSAdjust().getTime()) / (24 * 60 * 60 * 1000);
			remainDays = (temp.getDateEAdjust().getTime() - now.getTime())
					/ (24 * 60 * 60 * 1000);
			temp.setAdjustPeriod(adjustPeriod);
			temp.setRemainDays(remainDays);
			adjustPeriod = 0;
			remainDays = 0;
		}
		return SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		String id = (String) request.getAttribute("id");
		FxryNotice info = service.get(id);
		info.setStatus("1");//1：已签收；2：待签收
		service.update(info);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Throwable {
		return null;
	}

	@Override
	public String enable() throws Throwable {
		return null;
	}

	@Override
	public String disable() throws Throwable {
		return null;
	}

	@Override
	public String audit() throws Throwable {
		return null;
	}

	@Override
	public String add() throws Throwable {
		return null;
	}

	@Override
	public String update() throws Throwable {
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		return null;
	}

	@Override
	public Object getId() {
		return null;
	}

	@Override
	public Object[] getIds() {
		return null;
	}
	
}
