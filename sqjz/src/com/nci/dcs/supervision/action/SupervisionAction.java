package com.nci.dcs.supervision.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.homepage.report.model.ReportModule;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionModule;
import com.nci.dcs.supervision.service.ISupervisionService;
import com.nci.dcs.supervision.service.SupervisionModuleService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.service.BmbService;

@SuppressWarnings("rawtypes")
public class SupervisionAction extends BaseAction<SupervisionModule> {
	@Autowired
	private SupervisionModuleService supervisionModuleService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private BmbService bmbService;

	private Map<String, List> supervisionModules;

	private List<ReportModule> reportModules;

	public String getMineData() {
		supervisionModules = getVisibleModules();
		return SUCCESS;
	}

	private SupervisionModule getBaseModule() {
		List<SupervisionModule> baseModules = supervisionModuleService
				.findByCriteria(Restrictions.eq("type", "supervisionOrg"));
		SupervisionModule baseModule = null;
		if (baseModules.size() > 0) {
			baseModule = baseModules.get(0);
		} else {
			baseModule = new SupervisionModule();
			baseModule.setIcon("/images/index/zanjw_xb.jpg");
			baseModule.setUrl("/data/jzgl/dagl/mission/dhbd.jsp");
		}
		return baseModule;
	}

	private List<SupervisionModule> getOrganizationModule(Bmb bmb) {
		List<OrganizationInfo> orgs = new ArrayList<OrganizationInfo>();
		if (bmb.isSj()) {
			orgs = organizationInfoService.findByCriteria(Restrictions.eq(
					"orgType", "2"));
		}
		if (bmb.isQxsfj()) {
			orgs = organizationInfoService.findByCriteria(
					Restrictions.eq("supOrg.orgId", bmb.getBm()),
					Restrictions.eq("orgType", "3"));
		}
		Collections.sort(orgs, new Comparator<OrganizationInfo>() {
			public int compare(OrganizationInfo left, OrganizationInfo right) {
				if (left.getOrgId() == null || right.getOrgId() == null) {
					return 0;
				}
				return left.getOrgId().compareTo(right.getOrgId());
			}
		});
		SupervisionModule baseModule = getBaseModule();
		List<SupervisionModule> orgModules = new ArrayList<SupervisionModule>();
		for (int i = 0; i < orgs.size(); i++) {
			SupervisionModule orgModule = new SupervisionModule();
			orgModule.setId(orgs.get(i).getOrgId());
			orgModule.setName(orgs.get(i).getCname());
			orgModule.setIcon(baseModule.getIcon());
			orgModule.setUrl(baseModule.getUrl() + "?orgId="
					+ orgs.get(i).getOrgId());
			orgModule.setSort(i);
			orgModules.add(orgModule);
		}
		return orgModules;
	}

	/**
	 * 获取用户首页可见的待办快捷
	 * 
	 * @return
	 */
	private Map<String, List> getVisibleModules() {
		Bmb bmb = getCurOrg();
		List<SupervisionModule> mine = new ArrayList<SupervisionModule>();
		List<SupervisionModule> orgModules = getOrganizationModule(bmb);
		if (!bmb.isSfs()) {
			mine = getModules();
			Iterator<SupervisionModule> iter = mine.iterator();
			while (iter.hasNext()) {
				SupervisionModule module = iter.next();
				if (!CommonUtils.isNull(module.getService())) {
					try {
						ISupervisionService service = (ISupervisionService) SpringContextUtil
								.getBean(module.getService());
						SupervisionCount count = service.getSupervisionCount(getUser());
						Map<String, Integer> supervisons = count
								.getSupervisions();
						module.setTotal(count.getTotal());
						for (SupervisionModule orgModule : orgModules) {
							if (supervisons.containsKey(orgModule.getId())) {
								orgModule.setTotal(orgModule.getTotal()
										+ supervisons.get(orgModule.getId()));
							}
						}
					} catch (BeansException e) {
						logger.warn("获取待办数量错误，Bean不存在：" + module.getService());
					} catch (ClassCastException e) {
						logger.warn("获取待办数量错误，Bean必须实现接口ISupervisonService："
								+ module.getService());
					} catch (Exception e) {
						logger.warn("获取待办数量错误:", e);
					}
				}
			}
		}
		Map<String, List> map = new HashMap<String, List>();
		map.put("left", mine);
		map.put("right", orgModules);
		createReportDate(orgModules, bmb);
		map.put("report", reportModules);
		return map;
	}

	private void createReportDate(List<SupervisionModule> modules, Bmb bmb) {
		reportModules = new ArrayList<ReportModule>();
		if (null != bmb && !bmb.isSfs()) {
			ReportModule module = new ReportModule();
			module.setName(bmb.getMc() + "督察项");
			module.setType("1");
			String detail =  "督察项分类统计数:";
			if (bmb.isQxsfj()) {
				detail = bmb.getMc() + "各司法所" + detail;
			} else if (bmb.isSj()) {
				detail = "北京市各区县司法局" + detail;
			}
			long count = 0;
			List<ReportList> reportLists = new ArrayList<ReportList>();
			for (SupervisionModule supervision : modules) {
				ReportList reportList = new ReportList();
				reportList.setName(supervision.getName());
				reportList.setLink(supervision.getUrl());
				reportList.setValue(Long.toString(supervision.getTotal()));
				count+=supervision.getTotal();
				reportLists.add(reportList);
			}
			detail = detail + count + "项";
			module.setDetail(detail);
			module.setListData(reportLists);
			reportModules.add(module);
		}
	}

	private Comparator<SupervisionModule> getModuleComparator() {
		return new Comparator<SupervisionModule>() {
			public int compare(SupervisionModule left, SupervisionModule right) {
				if (left.getSort() == null || right.getSort() == null) {
					return 0;
				}
				return left.getSort().compareTo(right.getSort());
			}
		};
	}

	private List<SupervisionModule> getModules() {
		List<SupervisionModule> all = supervisionModuleService.findByCriteria(
				Restrictions.eq("type", "supervision"),
				Restrictions.eq("visible", "1"));
		List<SupervisionModule> visibleModules = new ArrayList<SupervisionModule>();
		for (SupervisionModule module : all) {
			visibleModules.add(module);
		}
		Collections.sort(visibleModules, getModuleComparator());
		return visibleModules;
	}

	public String getReportData() {
		reportModules = new ArrayList<ReportModule>();
		String orgId = request.getParameter("orgId");
		if (!CommonUtils.isNull(orgId)) {
			Bmb bmb = bmbService.get(orgId);
			if (bmb != null && !bmb.isSj()) {
				ReportModule module = new ReportModule();
				module.setName(bmb.getMc() + "督察项");
				module.setType("1");
				String detail = bmb.getMc() + "督察项分类统计" + ":";
				long count = 0;
				List<ReportList> reportLists = new ArrayList<ReportList>();
				List<SupervisionModule> todoModules = getModules();
				for (SupervisionModule todoModule : todoModules) {
					if (!CommonUtils.isNull(todoModule.getService())) {
						try {
							ISupervisionService service = (ISupervisionService) SpringContextUtil
									.getBean(todoModule.getService());
							SupervisionCount supervisionCount = service
									.getSupervisionCount(bmb,getUser());
							count = count + supervisionCount.getTotal();
							ReportList reportList = new ReportList();
							reportList.setName(todoModule.getReportName());
							if (!CommonUtils.isNull(todoModule.getReportUrl())) {
								String url = todoModule.getReportUrl();
								if (url.contains("?")) {
									url = url + "&orgId=" + bmb.getBm();
								} else {
									url = url + "?orgId=" + bmb.getBm();
								}
								reportList.setLink(url);
							}
							reportList.setValue(Long.toString(supervisionCount
									.getTotal()));
							reportLists.add(reportList);
						} catch (BeansException e) {
							logger.warn("获取待办数量错误，Bean不存在："
									+ todoModule.getService());
						} catch (ClassCastException e) {
							logger.warn("获取待办数量错误，Bean必须实现接口ISupervisonService："
									+ todoModule.getService());
						} catch (Exception e) {
							logger.warn("获取待办数量错误:", e);
						}
					}
				}
				detail = detail + count + "项";
				module.setDetail(detail);
				module.setListData(reportLists);
				reportModules.add(module);
			}
		}
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
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

	public List<ReportModule> getReportModules() {
		return reportModules;
	}

	public void setReportModules(List<ReportModule> reportModules) {
		this.reportModules = reportModules;
	}

	public Map<String, List> getSupervisionModules() {
		return supervisionModules;
	}

	public void setSupervisionModules(Map<String, List> supervisionModules) {
		this.supervisionModules = supervisionModules;
	}

}
