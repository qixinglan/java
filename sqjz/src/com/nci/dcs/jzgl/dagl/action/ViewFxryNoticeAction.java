package com.nci.dcs.jzgl.dagl.action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.dagl.model.ViewFxryNotice;
import com.nci.dcs.jzgl.dagl.service.ViewFxryNoticeService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.service.OrganizationInfoService;

public class ViewFxryNoticeAction extends BaseAction<ViewFxryNotice> {

	/**
	 * @name
	 * @author caolj
	 * @date 2015年4月1日 上午8:40:38
	 * @message:
	 */
	private static final long serialVersionUID = -8703389794470714965L;

	@Autowired
	private ViewFxryNoticeService service;

	@Autowired
	private OrganizationInfoService orgService;

	@Override
	public String list() throws Throwable {
		return SUCCESS;
	}

	/**
	 * @name 执法督察获取列表信息
	 * @return
	 * @author shuzz
	 * @message:
	 */

	@SuppressWarnings("unchecked")
	public String getSupervisonData() {
		this.getRequestPage();
		String sfsId = request.getParameter("sfsId");
		String qxsfjId = request.getParameter("qxsfjId");
		String orgId = request.getParameter("orgId");
		//检查用户是否有查看特管人员权限特管
				if("2".equals(getUser().getIsws())){
					page.getCriterions().add(Restrictions.eq("isTgry","2"));
				}
		page.getCriterions().add(Restrictions.lt("reportTime", new Date()));
		page.getCriterions().add(Restrictions.eq("state", FXRYState.ZJ));
		if (!CommonUtils.isNull(sfsId)) {
			page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
		} else if (!CommonUtils.isNull(qxsfjId)) {
			page.getCriterions().add(
					Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
							Restrictions.eq("supOrg", qxsfjId)));
		} else if (!CommonUtils.isNull(orgId)) {
			page.getCriterions().add(
					Restrictions.or(Restrictions.eq("responseOrg", orgId),
							Restrictions.eq("supOrg", orgId)));
		}
		service.findPaged(page);
		return SUCCESS;
	}

	/**
	 * @name 获取列表信息
	 * @return
	 * @author caolj
	 * @date 2015年4月1日 上午8:40:38
	 * @message:
	 */
	@SuppressWarnings("unchecked")
	public String getData() {
		this.getRequestPage();
		List<SearchRule> result = JQGridSearchRuleParser
				.getSearchRules(this.jqgrid.getFilters());// 获取所有searchRule
		List<Criterion> criterions = page.getCriterions();
		page.getCriterions().removeAll(criterions);
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
			SearchRule searchRule = new SearchRule();
			searchRule.setField("isTgry");
			searchRule.setData("2");
			searchRule.setOp("eq");
			result.add(searchRule);
		}
		String deviceCode = "";
		String field = "";
		for (SearchRule temp : result) {
			field = temp.getField();
			if (field != null && ("deviceCode").equals(field)) {
				deviceCode = temp.getData();
				if ("2".equals(deviceCode)) {
					temp.setOp("nu");
				} else {
					temp.setOp("nn");
				}
			}
		}
		// 添加机构条件
		String orgId = getCurOrgId();
		if (orgId != null && !orgId.equals("1")) {
			List<String> orgIds = orgService.getChildrenIds(orgId);
			String childs = orgIds.toString();
			childs = childs.replace(" ", "");
			childs = childs.replace("[", "");
			childs = childs.replace("]", "");
			SearchRule searchRule = new SearchRule();
			searchRule.setField("responseOrg");
			searchRule.setData(childs);
			searchRule.setOp("in");
			result.add(searchRule);
		}
		// 添加条件：接收过期三天后
		Calendar cal = Calendar.getInstance();
		Date dealDate = cal.getTime();
		Timestamp tt = new Timestamp(dealDate.getTime());
		SearchRule searchRule = new SearchRule();
		searchRule.setField("reportTime");
		searchRule.setData(tt.toString().substring(0, 10));
		searchRule.setOp("lt");
		result.add(searchRule);
		// 矫正状态
		SearchRule searchRule1 = new SearchRule();
		searchRule1.setField("state");
		searchRule1.setData(FXRYState.ZJ);
		searchRule1.setOp("eq");
		result.add(searchRule1);
		service.findPaged(page, result);
		return SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		return null;
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
