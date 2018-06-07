package com.nci.dcs.system.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.Log;
import com.nci.dcs.system.service.LogService;
import com.opensymphony.xwork2.Action;

public class LogAction extends BaseAction<Log>{
	
	@Autowired
	private LogService logService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	/**
	 * Description:列表条件
	 * 
	 * @author Shuzz
	 * @return
	 */
	private List<SearchRule> defaultRule() {
		List<SearchRule> results = new ArrayList<SearchRule>();
		Bmb bmb=this.getCurOrg();
		if(bmb.isSfs()){
			SearchRule rule=new SearchRule();
			rule.setField("orgId");
			rule.setOp("eq");
			rule.setData(bmb.getBm());
			results.add(rule);
		}else if(bmb.isQxsfj()){
			String orgIds=organizationInfoService.getChildrenIdsString(bmb.getBm());
			SearchRule rule=new SearchRule();
			rule.setField("orgId");
			rule.setOp("in");
			rule.setData(orgIds);
			results.add(rule);
		}
		return results;
	}

	public String search() throws Throwable {
		try {
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			logService.findPersonPaged(this.fillPageWithJQGridRequest(), searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
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

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Throwable {
		try {
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
