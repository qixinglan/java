package com.nci.dcs.em.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.model.AreaInfo;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SettingArea;
import com.nci.dcs.em.model.SettingAreaId;
import com.nci.dcs.em.model.SysSetting;
import com.nci.dcs.em.model.ViewFxrySetting;
import com.nci.dcs.em.service.AreaInfoService;
import com.nci.dcs.em.service.FxrySettingService;
import com.nci.dcs.em.service.SysSettingService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.User;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

/**
 * Description:社区服刑人员设置actio
 * 
 * @author Shuzz
 * @since 2014年6月23日上午11:02:33
 */
public class FxrySysSettingAction extends BaseAction<ViewFxrySetting> {

	@Autowired
	private FxrySettingService fxrySettingService;
	@Autowired
	private SysSettingService sysSettingService;
	@Autowired
	private AreaInfoService areaInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private EmMessageSendService emMessageSendService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1882885279371284866L;

	/**
	 * Description:进入矫正人员设置列表
	 * 
	 * @author Shuzz
	 * @since 2014年6月23日上午11:02:02
	 * @return
	 * @throws Throwable
	 */
	@Override
	public String list() throws Throwable {
		return SUCCESS;
	}

	private List<SearchRule> defaultRule() {
		List<SearchRule> result = new ArrayList<SearchRule>();
		User user = getUser();
		OrganizationInfo org = null;
		if (user != null) {
			org = organizationInfoService.get(user.getWunit().getBm());
		} else {
			org = organizationInfoService.findRoot();
		}
		SearchRule sr = new SearchRule();
		sr.setField("org.orgId");
		sr.setOp("eq");
		sr.setData(org.getOrgId());
		result.add(sr);
		return result;
	}

	/**
	 * Description:JQGrid列表查询
	 * 
	 * @author Shuzz
	 * @since 2014年6月23日上午11:02:02
	 * @return
	 * @throws Throwable
	 */
	public String search() throws Throwable {
		try {
			// 目前暂未实现登录功能呢，暂无User，
			// 增加后台过滤字段
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			//检查用户是否有查看特管人员权限特管
			if("2".equals(getUser().getIsws())){
				SearchRule searchRule=new SearchRule();
				searchRule.setField("isTgry");
				searchRule.setOp("eq");
				searchRule.setData("2");
				searchs.add(searchRule);
			}
			fxrySettingService.findPersonPaged(
					this.fillPageWithJQGridRequest(), searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Description:组织页面显示内容
	 * 
	 * @author Shuzz
	 */
	private void organizehow() {
		String areas = "";
		if (entity != null) {
			SysSetting sys = sysSettingService.getByFxry(entity.getId());
			if (sys != null) {
				for (SettingArea area : sys.getSetAreas()) {
					areas += area.getId().getAreaId() + ",";
				}
				if (areas.length() > 0) {
					areas = areas.substring(0, areas.length() - 1);
				}
				request.setAttribute("locP", sys.getLocPeriods());
				request.setAttribute("locSize", sys.getLocPeriods().size());
			} else {
				request.setAttribute("locP", new HashSet(0));
				request.setAttribute("locSize", 0);
			}
		} else {
			request.setAttribute("locP", new HashSet(0));
			request.setAttribute("locSize", 0);
		}
		request.setAttribute("areas", areas);
		request.setAttribute("areaList", areaInfoService.getCached());
	}

	/**
	 * Description:查看设置
	 * 
	 * @author Shuzz
	 */
	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		entity = fxrySettingService.get(id);
		organizehow();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SysSetting setting = sysSettingService.getByFxry(entity.getId());
		if (setting == null) {
			setting = new SysSetting();
			setting.setSettingId(null);
			setting.setFxryId(entity.getId());
			setting.setOrgId(entity.getOrg().getOrgId());
		}
		setting.setStatus(entity.getStatus());
		setting.setRailType(entity.getRailType());
		sysSettingService.update(setting);
		Set<LocationPeriod> locPeriods = new HashSet<LocationPeriod>();
		Set<SettingArea> setAreas = new HashSet<SettingArea>();
		if (entity.getRailType().equals("2")) {
			String[] areas = request.getParameterValues("area");
			for (String area : areas) {
				SettingArea s = new SettingArea();
				s.setId(new SettingAreaId(area, setting.getSettingId()));
				s.setAreaInfo(new AreaInfo(area));
				s.setSysSetting(setting);
				setAreas.add(s);
			}
		}
		String lp = request.getParameter("lp");
		if ("2".equals(lp)) {
			// 自定义设置采集周期必有周期
			String periods = request.getParameter("periods");
			int number = Integer.parseInt(periods);
			for (int i = 0; i < number; i++) {
				LocationPeriod period = new LocationPeriod();
				period.setStartTime(request.getParameter("startTime" + i));
				period.setEndTiem(request.getParameter("endTime" + i));
				String space = request.getParameter("space" + i);
				period.setSpace(Short.valueOf(space));
				period.setSysSetting(setting);
				locPeriods.add(period);
			}
			emMessageSendService.setFrequency(setting.getSettingId(),
					setting.getFxryId());
		} else {
			// 按区县默认设置的先取区县的设置，再取市局的设置
			OrganizationInfo org = organizationInfoService.get(setting
					.getOrgId());
			SysSetting sys = sysSettingService.getByOrgID(org.getSupOrg()
					.getOrgId());
			if (sys == null || sys.getLocPeriods().size() <= 0) {
				// 区县设置定位采集频率可能为空这时取市局的
				sys = sysSettingService.getByOrgID(rootOrgId);
			}
			if (sys == null || sys.getLocPeriods().size() <=0) {
				emMessageSendService.setFrequency(null, setting.getFxryId());
			} else {
				emMessageSendService.setFrequency(sys.getSettingId(),
						setting.getFxryId());
			}
		}
		sysSettingService.update(setting, locPeriods, setAreas);
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
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
		return SUCCESS;
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
