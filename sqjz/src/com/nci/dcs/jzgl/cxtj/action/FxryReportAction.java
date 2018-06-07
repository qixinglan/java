package com.nci.dcs.jzgl.cxtj.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.jzgl.cxtj.model.StatisticsFxryMonth;
import com.nci.dcs.jzgl.cxtj.model.ViewReportFxryInfo;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.jzgl.education.service.EducationInfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

/**
 * Description:系统默认设置action
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class FxryReportAction extends BaseAction<ViewReportFxryInfo> {
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private List jlist;
	private Map zlmap;

	@SuppressWarnings("unchecked")
	private List<Criterion> orgCriterion() {
		String filters = request.getParameter("filters");
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				String sorgId = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(sorgId)) {
					OrganizationInfo org = organizationInfoService.get(sorgId);
					if (org != null && "2".equals(org.getOrgType())) {
						String newFilters = tempFilters[0]
								+ "\"field\":\"supOrg\"" + tempFilters[1];
						filters = newFilters;
					}
				}
			} catch (Exception e) {

			}
		}
		JQGridSearchRuleParser parser = new JQGridSearchRuleParser(filters);
		List<Criterion> criterions = parser.parse(ViewReportFxryInfo.class);
		if (criterions == null) {
			criterions = new ArrayList();
		}
		Bmb org = getCurOrg();
		String orgId = org.getBm();
		if (org.isSfs()) {
			criterions.add(Restrictions.eq("responseOrg", orgId));
		} else if (org.isQxsfj()) {
			criterions.add(Restrictions.eq("supOrg", orgId));
		}
		return criterions;
	}

	public String fzlxReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("crimeType"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("crimeType")), orders);
		return SUCCESS;
	}

	public String jyjxReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("workState"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("workState")), orders);
		return SUCCESS;
	}

	public String whcdReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("educationDegree"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("educationDegree")),
				orders);
		return SUCCESS;
	}

	public String hjxzReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("natureHome"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("natureHome")), orders);
		return SUCCESS;
	}

	public String nldReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("age"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("age")), orders);
		return SUCCESS;
	}
	public String jzlxReport() throws Throwable {
		List<Criterion> criterions = orgCriterion();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("adjustType"));
		jlist = viewReportFxryInfoService.findByCriteria(
				criterions,
				ViewReportFxryInfo.class,
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("adjustType")), orders);
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String ryslReport() throws Throwable {
		String filters = request.getParameter("filters");
		List<Category> searchTimes = new ArrayList<Category>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		Date startTime = null, endTime = null;
		List<SearchRule> rules = JQGridSearchRuleParser.getSearchRules(filters);
		for (int i = 0; i < rules.size(); i++) {
			String field = rules.get(i).getField();
			if ("time".equals(field)) {
				String op = rules.get(i).getOp();
				if ("ge".equals(op)) {
					startTime = DateTimeFmtSpec.getMonthFormat().parse(
							rules.get(i).getData());
				} else if ("le".equals(op)) {
					endTime = DateTimeFmtSpec.getMonthFormat().parse(
							rules.get(i).getData());
				}
				rules.remove(i);
				--i;// 删除了元素，迭代的下标也跟着改变
			}
		}
		zlmap = new HashMap();
		if (startTime != null && endTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(startTime);
			JQGridSearchRuleParser parser = new JQGridSearchRuleParser(filters);
			List<Criterion> criterions = parser.parse(rules,
					StatisticsFxryMonth.class);
			Bmb org = getCurOrg();
			String orgId = org.getBm();
			Map<String, Map<String, String>> orgMap = new HashMap<String, Map<String, String>>();
			if (!org.isSfs()) {
				List<OrganizationInfo> orgs = organizationInfoService
						.findSfsBySup(orgId);
				for (OrganizationInfo orgInfo : orgs) {
					orgMap.put(orgInfo.getOrgId(),
							new HashMap<String, String>());
				}
				criterions.add(Restrictions.eq("superOrg", orgId));
			} else {
				orgMap.put(orgId, new HashMap<String, String>());
				criterions.add(Restrictions.eq("orgid", orgId));
			}
			List<Order> orders = new ArrayList<Order>();
			orders.add(Order.asc("orgid"));
			while (endTime.compareTo(c.getTime()) >= 0) {
				Category category = new Category(DateTimeFmtSpec
						.getMonthFormat().format(c.getTime()));
				searchTimes.add(category);
				c.add(Calendar.MONTH, 1);
			}
			for (Category searchTime : searchTimes) {
				String sTime = searchTime.getLabel();
				List<Criterion> sCriterions = new ArrayList<Criterion>(
						criterions);
				sCriterions.add(Restrictions.eq("year", sTime.split("-")[0]));
				sCriterions.add(Restrictions.eq("month", sTime.split("-")[1]));
				List<StatisticsFxryMonth> lists = viewReportFxryInfoService
						.findByCriteria(sCriterions, StatisticsFxryMonth.class,
								null, orders);
				for (StatisticsFxryMonth sfm : lists) {
					String oid = sfm.getOrgid();
					if (sfm.getByzcrs() == null) {
						sfm.setByzcrs(0L);
					}
					if (!orgMap.containsKey(oid)) {
						Map<String, String> m = new HashMap<String, String>();
						m.put(sTime, sfm.getByzcrs().toString());
						orgMap.put(oid, m);
					} else {
						Map<String, String> m = orgMap.get(oid);
						if (!m.containsKey(sTime)) {
							m.put(sTime, sfm.getByzcrs().toString());
						}
					}
				}
				for (String key : orgMap.keySet()) {
					Map<String, String> m = orgMap.get(key);
					if (!m.containsKey(sTime)) {
						m.put(sTime, "0");
					}
				}
			}
			for (String key : orgMap.keySet()) {
				Map<String, String> m = orgMap.get(key);
				DataSet ds = new DataSet(
						organizationInfoService.getOrgName(key));
				List<Data> datas = new ArrayList<Data>();
				for (Category searchTime : searchTimes) {
					Data data = new Data(m.get(searchTime.getLabel()));
					datas.add(data);
				}
				ds.setData(datas);
				dateSets.add(ds);
			}
		}
		zlmap.put("category", searchTimes);
		zlmap.put("dataset", dateSets);
		return SUCCESS;
	}
	
	/**
	 * @name 集中初始教育数据统计
	 * @return
	 * @throws Throwable
	 * @author clj
	 * @date 2015年5月29日 下午2:32:30 
	 * @message：
	 */
	@Autowired
	private EducationInfoService service;
	@SuppressWarnings("unchecked")
	public String educationReport() throws Throwable {
		String filters = request.getParameter("filters");
		List<DataSet> dateSets = new ArrayList<DataSet>();
		Date startTime = null, endTime = null;
		List<SearchRule> rules = JQGridSearchRuleParser.getSearchRules(filters);
		for (int i = 0; i < rules.size(); i++) {
			String field = rules.get(i).getField();
			if ("time".equals(field)) {
				String op = rules.get(i).getOp();
				if ("ge".equals(op)) {
					startTime = DateTimeFmtSpec.getDateFormat().parse(
							rules.get(i).getData()+"-01");
				} else if ("le".equals(op)) {
					endTime = DateTimeFmtSpec.getDateFormat().parse(
							rules.get(i).getData()+"-01");
					//结束日期月份加一
					Calendar cal = Calendar.getInstance();
					cal.setTime(endTime);
					cal.add(Calendar.MONTH,1);
					endTime = cal.getTime();
				}
				rules.remove(i);
				--i;// 删除了元素，迭代的下标也跟着改变
			}
		}
		zlmap = new HashMap();
		List<Category> orgNames = new ArrayList<Category>();
		// 获取查询起止时间
		String detail = "北京市最近一年共新增在矫人员 ";
		if (startTime != null && endTime != null) {
			Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager
					.getSession());
			String orgId = org.getBm();
			DataSet addDs = new DataSet("新增在矫人员");
			DataSet educationDs = new DataSet("集中初始教育");
			List<Data> fxryDatas = new ArrayList<Data>();
			List<Data> personEducation = new ArrayList<Data>();
			List<OrganizationInfo> organizationInfos = new ArrayList<OrganizationInfo>();
			List resultEducation = new ArrayList();
			List result = new ArrayList();
			if (org.isQxsfj()) {
				organizationInfos = organizationInfoService
						.findByCriteria(Restrictions.or(Restrictions.eq(
								"orgId", orgId), Restrictions.and(
								Restrictions.eq("supOrg.orgId", orgId),
								Restrictions.eq("orgType", "3"))));
				String sql = "select count(o.org_id), o.org_id, 0"
						+ " from CC_FXRY_BASEINFO fxry"
						+ " left join CC_EXECUTE_INFO e"
						+ "   on fxry.id = e.fxry_id"
						+ " left join CC_ORGANIZATION_INFO o"
						+ " on fxry.response_org = o.org_id"
						+ " where fxry.state ='1'"
						+ "  and e.Date_Receive >= ?"
						+ "  and e.Date_Receive < ?" + " and o.sup_org_id = '"
						+ orgId + "'" + "  group by o.org_id";
				result = service.findBySql(sql, startTime, endTime);
				sql = "select count(o.org_id), o.org_id, 0"
						+ " from cc_education_info t, CC_FXRY_BASEINFO f"
						+ " left join CC_ORGANIZATION_INFO o"
						+ " on f.response_org = o.org_id"
						+ " left join CC_EXECUTE_INFO e"
						+ " on f.id = e.fxry_id" + " where t.fxry_id = f.id"
						+ " and f.state ='1'" + " and e.Date_Receive >= ?"
						+ " and e.Date_Receive < ?" + " and t.type = '1'"
						+ " and t.sfcy = '1'" + " and o.sup_org_id = '" + orgId
						+ "'" + " group by o.org_id";
				resultEducation = service.findBySql(sql, startTime, endTime);
				detail = org.getMc() + "各司法所" + detail;
			} else if (org.isSj()) {
				organizationInfos = organizationInfoService
						.findByCriteria(Restrictions.eq("orgType", "2"));
				String sql = "select count(o.sup_org_id),o.sup_org_id, 0"
						+ " from CC_FXRY_BASEINFO fxry"
						+ " left join CC_EXECUTE_INFO e"
						+ "   on fxry.id = e.fxry_id"
						+ " left join CC_ORGANIZATION_INFO o"
						+ " on fxry.response_org = o.org_id"
						+ " where fxry.state ='1'"
						+ "  and e.Date_Receive >= ?"
						+ "  and e.Date_Receive < ?"
						+ "  group by o.sup_org_id";
				result = service.findBySql(sql, startTime, endTime);
				sql = "select count(o.sup_org_id), o.sup_org_id, 0"
						+ " from cc_education_info t, CC_FXRY_BASEINFO f"
						+ " left join CC_ORGANIZATION_INFO o"
						+ " on f.response_org = o.org_id"
						+ " left join CC_EXECUTE_INFO e"
						+ " on f.id = e.fxry_id" + " where t.fxry_id = f.id"
						+ " and f.state ='1'" + " and e.Date_Receive >= ?"
						+ " and e.Date_Receive < ?" + " and t.type = '1'"
						+ " and t.sfcy = '1'" + " group by o.sup_org_id";
				resultEducation = service.findBySql(sql, startTime, endTime);
				detail = "北京市各区县司法局" + detail;
			}
			for (Object obj : result) {
				Object[] object = (Object[]) obj;
				for (Object obje : resultEducation) {
					Object[] objEducation = (Object[]) obje;
					if (object[1] != null
							&& object[1].toString().equals(
									objEducation[1].toString())) {
						object[2] = objEducation[0];
						break;
					}
				}
			}
			boolean flag = true;
			for (OrganizationInfo orgInfo : organizationInfos) {
				for (Object obj : result) {
					Object[] object = (Object[]) obj;
					if (object[1] != null
							&& object[1].toString().equals(orgInfo.getOrgId())) {
						fxryDatas.add(new Data(String.valueOf(object[0])));
						personEducation
								.add(new Data(String.valueOf(object[2])));
						flag = false;
						break;
					}
				}
				if (flag) {
					fxryDatas.add(new Data("0"));
					personEducation.add(new Data("0"));
				}
				flag = true;
				orgNames.add(new Category(orgInfo.getCname()));
			}
			addDs.setData(fxryDatas);
			educationDs.setData(personEducation);
			dateSets.add(educationDs);
			dateSets.add(addDs);
			long count = 0;
			for (Data stage : fxryDatas) {
				count = count + Integer.parseInt(stage.getValue());
			}
			detail = detail + count + "人";
			count = 0;
			for (Data person : personEducation) {
				count = count + Integer.parseInt(person.getValue());
			}
			detail = detail + "参加集中初始教育" + count + "人";

		}
		zlmap.put("category", orgNames);
		zlmap.put("dataset", dateSets);
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
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

	public List getJlist() {
		return jlist;
	}

	public void setJlist(List jlist) {
		this.jlist = jlist;
	}

	public Map getZlmap() {
		return zlmap;
	}

	public void setZlmap(Map zlmap) {
		this.zlmap = zlmap;
	}
}