package com.nci.dcs.supervision.action;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.data.utils.DateUtils;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.supervision.model.SupervisionPublicWork;
import com.nci.dcs.supervision.service.SupervisionPublicWorkService;

public class SupervisionPublicWorkAction extends
		BaseAction<SupervisionPublicWork> {
	@Autowired
	private SupervisionPublicWorkService supervisonPublicWorkService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Throwable {
		this.getRequestPage();
		String sfsId = request.getParameter("sfsId");
		String qxsfjId = request.getParameter("qxsfjId");
		String orgId = request.getParameter("orgId");
		page.getCriterions().add(
				Restrictions.lt("createTime",
						DateUtils.getFirstDayForMonth(new Date())));
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
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
		supervisonPublicWorkService.findPaged(page);
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
