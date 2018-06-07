package com.nci.dcs.jzgl.dagl.action;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.dagl.model.ViewFxryRewardPunish;
import com.nci.dcs.jzgl.dagl.service.ViewFxryRewardPunishService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

@SuppressWarnings("serial")
public class ViewFxryRewardPunishAction extends
		BaseAction<ViewFxryRewardPunish> {
	@Autowired
	private ViewFxryRewardPunishService viewFxryRewardPunishService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	private AjaxFormResult ajaxFormResult;

	@Override
	@SuppressWarnings("unchecked")
	public String list() throws Throwable {
		String filters = this.getJqgrid().getFilters();
		Object restriction = null;
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				String orgId = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(orgId)) {
					OrganizationInfo org = organizationInfoService.get(orgId);
					if (org != null && "2".equals(org.getOrgType())) {
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
		Page<ViewFxryRewardPunish> page = getRequestPage();
		Bmb org = getCurOrg();
		String orgId = org.getBm();
		if (org.isSfs()) {
			page.getCriterions().add(Restrictions.eq("responseOrg", orgId));
		} else if (org.isQxsfj()) {
			page.getCriterions().add(
					Restrictions.or(Restrictions.eq("supOrgId", orgId),
							Restrictions.eq("responseOrg", orgId)));
		}
		if (restriction != null) {
			page.getCriterions().add(restriction);
		}
		String type = request.getParameter("type");
		if (!CommonUtils.isNull(type)) {
			if ("jc".equals(type)) {
				page.getCriterions().add(
						Restrictions.or(Restrictions.gt("rnum", 0L),
								Restrictions.gt("pnum", 0L)));
			}
			if ("wf".equals(type)) {
				page.getCriterions().add(
						Restrictions.or(Restrictions.gt("inum", 0L),
								Restrictions.gt("cnum", 0L)));
			}
			viewFxryRewardPunishService.findPaged(page);
		}
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
