package com.nci.dcs.em.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.em.model.AreaInfo;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SettingArea;
import com.nci.dcs.em.model.SettingAreaId;
import com.nci.dcs.em.model.SysSetting;
import com.nci.dcs.em.service.AreaInfoService;
import com.nci.dcs.em.service.SysSettingService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

/**
 * Description:系统默认设置action
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
public class SysSettingAction extends BaseAction<SysSetting> {
	@Autowired
	private SysSettingService sysSettingService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private AreaInfoService areaInfoService;
	@Autowired
	private EmMessageSendService emMessageSendService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1882885279371284866L;

	/**
	 * Description:矫正人员设置列表
	 * 
	 * @author Shuzz
	 * @since 2014年5月23日上午9:51:54
	 */
	@Override
	public String list() throws Throwable {
		return null;
	}

	/**
	 * Description:查看设置
	 * 
	 * @author Shuzz
	 */
	@Override
	public String view() throws Throwable {
		String orgId = request.getParameter("orgId");
		entity = sysSettingService.getByOrgID(orgId);
		OrganizationInfo org = organizationInfoService.get(orgId);
		organizehow(org);
		return "success";
	}

	/**
	 * Description:组织页面显示内容
	 * 
	 * @author Shuzz
	 */
	private void organizehow(OrganizationInfo org) {
		String areas = "";
		if (entity != null) {
			for (SettingArea area : entity.getSetAreas()) {
				areas += area.getId().getAreaId() + ",";
			}
			if (areas.length() > 0) {
				areas = areas.substring(0, areas.length() - 1);
			}
		}
		request.setAttribute("areas", areas);
		request.setAttribute("orgName", org.getOrgName());
		request.setAttribute("orgType", org.getOrgType());
		request.setAttribute("areaList", areaInfoService.getCached());
	}

	/**
	 * Description:系统和区县默认设置进入页面
	 * 
	 * @author Shuzz
	 */
	@Override
	public String input() throws Exception {
		User user = getUser();
		OrganizationInfo org = null;
		if (user != null) {
			org = organizationInfoService.get(user.getWunit().getBm());
		} else {
			org = organizationInfoService.findRoot();
		}
		if (org.getOrgType().equals("1") || org.getOrgType().equals("2")) {
			List<OrganizationInfo> orgList;
			if (org.getOrgType().equals("1")) {
				orgList = organizationInfoService
						.findByProperty("orgType", "2");
			} else {
				orgList = organizationInfoService
						.findByProperty("orgType", "1");
			}
			entity = sysSettingService.getByOrgID(org.getOrgId());
			if (entity == null) {
				entity = new SysSetting();
				entity.setOrgId(org.getOrgId());
			}
			organizehow(org);
			request.setAttribute("periods", entity.getLocPeriods().size());
			request.setAttribute("orgList", orgList);
			request.setAttribute("entity", entity);
			return SUCCESS;
		} else {
			return NONE;
		}

	}

	/**
	 * Description:系统和区县默认设置保存
	 * 
	 * @author Shuzz
	 */
	@Override
	public String update() throws Throwable {
		try{
			Bmb bmb=getCurOrg();
			if(bmb.isSj()||bmb.isQxsfj()){
				//市局或区县局才进行设置
				String periods = request.getParameter("periods");
				int number = 0;
				try {
					number = Integer.parseInt(periods);
				} catch (Exception e) {
					logger.error("定位周期频率数量设置出错", e);
				}
				//每个机构一条设置数据
				SysSetting setting = sysSettingService.getByOrgID(bmb.getBm());
				if (setting == null) {
					setting = entity;
					setting.setOrgId(bmb.getBm());
					setting.setSettingId(null);
				}
				setting.setRailType(entity.getRailType());
				sysSettingService.update(setting);
				entity = null;
				//设置采集周期
				Set<LocationPeriod> locPeriods = new HashSet<LocationPeriod>();
				for (int i = 0; i < number; i++) {
					LocationPeriod period = new LocationPeriod();
					period.setStartTime(request.getParameter("startTime" + i));
					period.setEndTiem(request.getParameter("endTime" + i));
					String space = request.getParameter("space" + i);
					period.setSpace(Short.valueOf(space));
					period.setSysSetting(setting);
					locPeriods.add(period);
				}
				//电子围栏为行政区划时设置行政区划
				Set<SettingArea> setAreas = new HashSet<SettingArea>();
				if (setting.getRailType().equals("2")) {
					String[] areas = request.getParameterValues("area");
					for (String area : areas) {
						SettingArea s = new SettingArea();
						s.setId(new SettingAreaId(area, setting.getSettingId()));
						s.setAreaInfo(new AreaInfo(area));
						s.setSysSetting(setting);
						setAreas.add(s);
					}
				}
				sysSettingService.update(setting, locPeriods, setAreas);
				List<String> notOrgId = null;
				if (setting.getOrgId().equals(rootOrgId)) {
					notOrgId = sysSettingService.getOtherOrg();
					if (number == 0) {
						emMessageSendService.setFrequency(null,
								setting.getOrgId(), notOrgId);
					}
				} else {
					if (number == 0) {
						SysSetting root = sysSettingService
								.getByOrgID(rootOrgId);
						if (root != null && root.getLocPeriods().size() > 0) {
							emMessageSendService.setFrequency(
									root.getSettingId(), setting.getOrgId(),
									null);
						} else {
							emMessageSendService.setFrequency(null,
									setting.getOrgId(), null);
						}
					}
				}
				if (number != 0) {
					emMessageSendService.setFrequency(setting.getSettingId(),
							setting.getOrgId(), notOrgId);
				}
			}
		} catch (Exception e) {
			logger.error("市局和区县默认设置保存出错", e);
		}
		return SUCCESS;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		request.setAttribute("name", name);
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("view", request.getParameter("view"));
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
