package com.nci.dcs.supervision.report.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.homepage.report.service.IReportService;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.jzgl.dagl.model.ViewFxryUnmanageHistory;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.supervision.model.SupervisionModule;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
public class SupervisionReportUnmanageService extends IReportService {
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	@SuppressWarnings("unchecked")
	private String getReportUrl() {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("reportType", "UM"));
		List<SupervisionModule> list = viewReportFxryInfoService
				.findByCriteria(criterions, SupervisionModule.class, null, null);
		if (CommonUtils.isNotNull(list)) {
			return list.get(0).getReportUrl();
		}
		return "";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ReportData getReportData(User user) {
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		List<Criterion> criterions = new ArrayList<Criterion>();
		//特管人员过滤
				if("2".equals(user.getIsws())){
					criterions.add(Restrictions.eq("isTgry", "2"));
				}
		criterions.add(Restrictions.eq("state", FXRYState.TG));
		criterions.add(Restrictions.isNull("endDate"));
		List<Order> orders = new ArrayList<Order>();
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.rowCount());
		Map<String, String> orgMap = new HashMap<String, String>();
		String orgId = org.getBm();
		String detail = "脱管人员统计数：";
		String parameter = "";
		if (org.isQxsfj()) {
			parameter = "sfsId";
			criterions.add(Restrictions.or(
					Restrictions.eq("responseOrg", orgId),
					Restrictions.eq("supOrgId", orgId)));
			orders.add(Order.asc("responseOrg"));
			projectionList.add(Projections.groupProperty("responseOrg"));
			List<OrganizationInfo> organizationInfos = organizationInfoService
					.findByCriteria(Restrictions.or(Restrictions.eq("orgId",
							orgId), Restrictions.and(
							Restrictions.eq("supOrg.orgId", orgId),
							Restrictions.eq("orgType", "3"))));
			for (OrganizationInfo orgInfo : organizationInfos) {
				orgMap.put(orgInfo.getOrgId(), orgInfo.getCname());
			}
			detail = org.getMc() + "各司法所" + detail;
		} else if (org.isSj()) {
			parameter = "qxsfjId";
			orders.add(Order.asc("supOrgId"));
			projectionList.add(Projections.groupProperty("supOrgId"));
			List<OrganizationInfo> organizationInfos = organizationInfoService
					.findByCriteria(Restrictions.eq("orgType", "2"));
			for (OrganizationInfo orgInfo : organizationInfos) {
				orgMap.put(orgInfo.getOrgId(), orgInfo.getCname());
			}
			detail = "北京市各区县司法局" + detail;
		}
		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewFxryUnmanageHistory.class, projectionList, orders);
		List<ReportList> reportLists = organiseReport(orgMap, list, "不详", 1, 0);
		Collections.sort(reportLists, new ReportListComparator());
		long count = 0;
		String url = getReportUrl();
		for (ReportList report : reportLists) {
			count = count + Integer.parseInt(report.getValue());
			String tempUrl = url;
			if (tempUrl.contains("?")) {
				tempUrl += "&" + parameter + "=" + report.getStort();
			} else {
				tempUrl += "?" + parameter + "=" + report.getStort();
			}
			report.setLink(tempUrl);
		}
		detail = detail + count + "人";
		ReportData data = new ReportData();
		data.setListData(reportLists);
		data.setDetail(detail);
		return data;
	}

}
