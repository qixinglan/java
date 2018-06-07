package com.nci.dcs.homepage.report.service;

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

import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.jzgl.cxtj.model.ViewReportFxryInfo;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@Service
public class AdjustPersonReportService extends IReportService {
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	@Override
	public ReportData getReportData(User user) {
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.ne("state", "4"));
		criterions.add(Restrictions.ne("state", "7"));
		List<Order> orders = new ArrayList<Order>();
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.rowCount());
		Map<String, String> orgMap = new HashMap<String, String>();
		String orgId = org.getBm();
		String detail="目前在矫人员统计数：";
		if (org.isQxsfj()) {
			criterions.add(Restrictions.eq("supOrg", orgId));
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
			detail=org.getMc()+"各司法所"+detail;
		} else if (org.isSj()) {
			orders.add(Order.asc("supOrg"));
			projectionList.add(Projections.groupProperty("supOrg"));
			List<OrganizationInfo> organizationInfos = organizationInfoService
					.findByCriteria(Restrictions.eq("orgType", "2"));
			for (OrganizationInfo orgInfo : organizationInfos) {
				orgMap.put(orgInfo.getOrgId(), orgInfo.getCname());
			}
			detail="北京市各区县司法局"+detail;
		} else {
			orgMap.put(org.getBm(), org.getMc());
			criterions.add(Restrictions.eq("responseOrg", orgId));
			detail=org.getMc()+detail;
		}
		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewReportFxryInfo.class, projectionList, orders);
		List<ReportList> reportLists = organiseReport(orgMap, list, "不详", 1, 0);
		Collections.sort(reportLists, new ReportListComparator());
		long count =0;
		for(ReportList report:reportLists){
			count=count+Integer.parseInt(report.getValue());
		}
		detail=detail+count+"人";
		ReportData data = new ReportData();
		data.setListData(reportLists);
		data.setDetail(detail);
		return data;
	}
}
