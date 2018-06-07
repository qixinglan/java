package com.nci.dcs.jzgl.rcbdgl.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryVacateinfo;
import com.nci.dcs.jzgl.rcbdgl.service.ViewFxryVacateinfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

/**
 * Description:服刑人员请假信息查询action
 *
 * @author Shuzz
 * @since 2014年10月22日上午9:44:05
 */
public class ViewFxryVacateinfoAction extends BaseAction<ViewFxryVacateinfo> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6748944635117385789L;

	/**
	 * Description:日常提醒中获取假期即将到期的服刑人员列表
	 *
	 * @author Shuzz
	 * @since 2014年10月22日上午9:44:05
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Throwable {
		this.getRequestPage();
		Bmb org = getCurOrg();
		if (org.isQxsfj()) {
			OrganizationInfo curOrg = organizationInfoService.get(org.getBm());
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			page.getCriterions().add(Restrictions.in("recordOrgId", ids));
		} else if (org.isSfs()) {
			//检查用户是否有查看特管人员权限特管
			if("2".equals(getUser().getIsws())){
				page.getCriterions().add(Restrictions.eq("isTgry","2"));
			}
			// 司法所按负责单位查询
			page.getCriterions().add(
					Restrictions.eq("recordOrgId", org.getBm()));
		}
		page.getCriterions().add(Restrictions.isNull("reportDate"));
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		page.getCriterions().add(Restrictions.le("endDate", c.getTime()));
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

	@Autowired
	private ViewFxryVacateinfoService service;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	public ViewFxryVacateinfoService getService() {
		return service;
	}

	public void setService(ViewFxryVacateinfoService service) {
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	public String vacateSearch() throws Throwable {
		String id = request.getParameter("id");
		this.getRequestPage();
		if (id != null && !("").equals(id)) {
			page.getCriterions().add(Restrictions.eq("fxryid", id));
		} else {
			Bmb org = getCurOrg();
			if (org.isQxsfj()) {
				OrganizationInfo curOrg = organizationInfoService.get(org
						.getBm());
				Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
				List<String> ids = new ArrayList<String>();
				for (OrganizationInfo item : suborgs) {
					ids.add(item.getOrgId());
				}
				ids.add(curOrg.getOrgId());
				page.getCriterions().add(Restrictions.in("recordOrgId", ids));
			} else if (org.isSfs()) {
				// 司法所按负责单位查询
				page.getCriterions().add(
						Restrictions.eq("recordOrgId", org.getBm()));
			}

			page.getCriterions().add(Restrictions.isNull("reportDate"));
		}
		service.findPaged(page);
		return SUCCESS;
	}
}
