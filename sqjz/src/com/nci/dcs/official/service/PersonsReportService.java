package com.nci.dcs.official.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
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
import com.nci.dcs.homepage.report.service.IReportService;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

@Service
public class PersonsReportService extends IReportService {
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;

	@SuppressWarnings("unchecked")
	@Override
	public ReportData getReportData(User user) {
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("org.source", "1"));
		List<Order> orders = new ArrayList<Order>();
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.rowCount());
		Map<String, String> typeMap = dictionaryInfoService
				.getDictMapByKey("RYLB");
		String orgId = org.getBm();
		String detail="目前矫正工作人员统计数：";
		if (org.isQxsfj()) {
			List<String> ids = organizationInfoService.getChildrenIds(orgId);
			if(ids!=null&&ids.size()>0){
				criterions.add(Restrictions.in("org.orgId", ids));
			}
			detail=org.getMc()+"各司法所"+detail;
		} else if (org.isSfs()) {
			List<String> ids = organizationInfoService.getChildrenIds(orgId);
			if(ids!=null&&ids.size()>0){
				criterions.add(Restrictions.in("org.orgId", ids));
			}
			detail=org.getMc()+detail;
		}else{
			detail="北京市各区县司法局"+detail;
		}
		orders.add(Order.asc("persontype"));
		projectionList.add(Projections.groupProperty("persontype"));
		Criteria criteria= viewReportFxryInfoService.createCriteria(criterions,
				Persons.class, projectionList, orders);
		criteria.createAlias("org", "org",Criteria.LEFT_JOIN);
		List<Object> list=criteria.list();
		List<ReportList> reportLists = organiseReport(typeMap, list, "不详", 1, 0);
		//排序显示
		for(ReportList report:reportLists){
			String code = report.getStort();
			if("1".equals(code)){
				report.setStort("2");
			}else if("2".equals(code)){
				report.setStort("6");
			}else if("3".equals(code)){
				report.setStort("4");
			}else if("4".equals(code)){
				report.setStort("3");
			}else if("5".equals(code)){
				report.setStort("5");
			}else if("6".equals(code)){
				report.setStort("1");
			}else{
				report.setStort("0");	
			}
		}
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
