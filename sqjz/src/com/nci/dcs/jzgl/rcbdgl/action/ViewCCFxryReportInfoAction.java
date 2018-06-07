package com.nci.dcs.jzgl.rcbdgl.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.rcbdgl.model.ViewCCFxryReportinfo;
import com.nci.dcs.jzgl.rcbdgl.service.ViewCCFxryReportInfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

/**
 * Description:日常工作提醒中获取待报到人员的action
 * 
 * @author Shuzz
 * @since 2014年10月22日上午9:40:34
 */
@SuppressWarnings("unchecked")
public class ViewCCFxryReportInfoAction extends
		BaseAction<ViewCCFxryReportinfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4056742816949968432L;
	@Autowired
	private ViewCCFxryReportInfoService viewCCFxryReportInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	@Override
	public String list() throws Throwable {
		try {
			Page<ViewCCFxryReportinfo> page = this.getRequestPage();
			Bmb org = getCurOrg();
			Criterion crit = null;
			if (org.isQxsfj()) {
				OrganizationInfo curOrg = organizationInfoService.get(org
						.getBm());
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
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DAY_OF_YEAR, 3);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);

			page.getCriterions().add(
					Restrictions.or(Restrictions.isNull("reportdate"),
							Restrictions.le("reportdate", c.getTime())));
			viewCCFxryReportInfoService.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Description:待办事项中的列表，只获取当月需要报到的人
	 * 
	 * @author Shuzz
	 * @return
	 * @throws Throwable
	 * @since 2015年4月1日上午8:52:09
	 */
	public String todoList() throws Throwable {
		try {
			Page<ViewCCFxryReportinfo> page = this.getRequestPage();
			Bmb org = getCurOrg();
			//检查用户是否有查看特管人员权限特管
			if("2".equals(getUser().getIsws())){
				page.getCriterions().add(Restrictions.eq("isTgry","2"));
			}
			page.getCriterions().addAll(
					viewCCFxryReportInfoService.getTodoCriterion(org.getBm()));
			viewCCFxryReportInfoService.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
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
