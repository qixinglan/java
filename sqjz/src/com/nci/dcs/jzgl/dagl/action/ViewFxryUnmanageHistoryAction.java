package com.nci.dcs.jzgl.dagl.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.ViewFxryUnmanageHistory;
import com.nci.dcs.jzgl.dagl.service.ViewFxryUnmanageHistoryService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

public class ViewFxryUnmanageHistoryAction extends
		BaseAction<ViewFxryUnmanageHistory> {

	@Autowired
	ViewFxryUnmanageHistoryService service;

	@Autowired
	private OrganizationInfoService organizationInfoService;

	protected AjaxFormResult ajaxFormResult;

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String list() throws Throwable {
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
			crit = Restrictions.in("responseOrg", ids);
		} else if (org.isSfs()) {// 司法所按负责单位查询
			crit = Restrictions.eq("responseOrg", org.getBm());
		}
		String filters = this.getJqgrid().getFilters();
		Object restriction = null;
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				String orgId = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(orgId)) {
					OrganizationInfo suporg = organizationInfoService
							.get(orgId);
					if (suporg != null && "2".equals(suporg.getOrgType())) {
						restriction = Restrictions.or(
								Restrictions.eq("supOrgId", orgId),
								Restrictions.eq("responseOrg", orgId));
						int end = 0;
						if (tempFilters[1].indexOf("{") > -1) {
							end = tempFilters[1].indexOf("{");
						} else {
							end = tempFilters[1].indexOf("}") + 1;
						}
						String newFilters = tempFilters[0].substring(0,
								tempFilters[0].length() - 1)
								+ tempFilters[1].substring(end);
						this.getJqgrid().setFilters(newFilters);
					}
				}
			} catch (Exception e) {

			}
		}
		Page page = getRequestPage();
		if (null != crit) {
			page.getCriterions().add(crit);
		}
		if (restriction != null) {
			page.getCriterions().add(restriction);
		}
		page.getCriterions().add(Restrictions.eq("state", FXRYState.TG));
		page.getCriterions().add(Restrictions.isNull("endDate"));
		service.findPaged(page);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String supervisonList() {
		this.getRequestPage();
		String sfsId = request.getParameter("sfsId");
		String qxsfjId = request.getParameter("qxsfjId");
		String orgId = request.getParameter("orgId");
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		page.getCriterions().add(Restrictions.eq("state", FXRYState.TG));
		page.getCriterions().add(Restrictions.isNull("endDate"));
		if (!CommonUtils.isNull(sfsId)) {
			page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
		} else if (!CommonUtils.isNull(qxsfjId)) {
			page.getCriterions().add(
					Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
							Restrictions.eq("supOrgId", qxsfjId)));
		} else if (!CommonUtils.isNull(orgId)) {
			page.getCriterions().add(
					Restrictions.or(Restrictions.eq("responseOrg", orgId),
							Restrictions.eq("supOrgId", orgId)));
		}
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
}
