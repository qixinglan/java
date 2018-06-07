package com.nci.dcs.jzgl.rcbdgl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.jzgl.dagl.service.FXRYReportOrgService;
import com.nci.dcs.jzgl.rcbdgl.dao.AddReportInfoDao;
import com.nci.dcs.jzgl.rcbdgl.model.ReportInfo;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryAddreportinfo;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.official.service.PersonsService;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class AddReportInfoService extends
BaseService<ViewFxryAddreportinfo, String> {

@Autowired
AddReportInfoDao dao;

public AddReportInfoDao getDao() {
return dao;
}

public void setDao(AddReportInfoDao dao) {
this.dao = dao;
}

@Autowired
private FXRYReportOrgService fxryReportOrgService;

@Autowired
private ReportInfoService reportinfoService;

@Autowired
private PersonsService personsService;


@Override
public void audit(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public void create(ViewFxryAddreportinfo entity) {
dao.save(entity);
}

@Override
public void delete(String id) {
dao.delete(id);

}

@Override
public void disable(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public void enable(String id) throws Exception {
// TODO Auto-generated method stub

}

@Override
public List<ViewFxryAddreportinfo> find() {
// TODO Auto-generated method stub
return dao.find("from ViewFxryReportinfo", null);
}

public List<ViewFxryAddreportinfo> find(String col, String where) {
	String sql = "select " + col + " from ViewFxryReportinfo ";
	if(where !=null && !"".equals(where))
		sql += where;
	return dao.find(sql);
}

@Override
public Page<ViewFxryAddreportinfo> findPaged(Page<ViewFxryAddreportinfo> page,
		ViewFxryAddreportinfo entity) {
return dao.findByFilters(page, this.buildPropertyFilter(entity));
}

public Page<ViewFxryAddreportinfo> findPaged(Page<ViewFxryAddreportinfo> page) {
	return dao.findByCriteria(page);
}

@Override
public Page<ViewFxryAddreportinfo> findPaged(Page<ViewFxryAddreportinfo> page,
	String hql, Object... values) {
// TODO Auto-generated method stub
return null;
}

@Override
public ViewFxryAddreportinfo get(String id) {
// TODO Auto-generated method stub
return dao.get(id);
}

@Override
public void update(ViewFxryAddreportinfo entity) {
// TODO Auto-generated method stub

}

	/**
	 * Description:当月报告，维持以前的对外接口，默认为手工报告
	 * 
	 * @author Shuzz
	 * @param fxryid
	 *            服刑人员ID
	 * @param reportdate
	 *            报告时间
	 * @param nextreportdate
	 *            下次报告时间(实际同报告时间)
	 * @throws Exception
	 * @since 2015年10月10日上午10:03:05
	 */
    //different from online
	public void addReportInfo(String fxryid, String reportdate,
			String nextreportdate) throws Exception {
		addReportInfo(fxryid, nextreportdate, nextreportdate, "1", "", new User());
	}

	/**
	 * Description:增加当月报告信息
	 * 
	 * @param fxryid
	 *            服刑人员ID
	 * @param reportdate
	 *            报告时间
	 * @param nextreportdate
	 *            下次报告时间(实际同报告时间)
	 * @param reportType
	 *            报告类型
	 * @param description
	 *            备注
	 * @throws Exception
	 */
	//different from online
	public void addReportInfo(String fxryid, String reportdate,
			String nextreportdate, String reportType, String description, User user)
			throws Exception {
		// 获取当前数据库状态为1的报到数据，更新为0，然后再增加最新的报到数据
		ReportInfo reportInfoOld = reportinfoService
				.getByFxryIdAndDataStatus(fxryid);
		if (reportInfoOld != null) {
			reportInfoOld.setdataStatus(0);
			reportinfoService.update(reportInfoOld);
		}
		ReportInfo reportInfo = new ReportInfo();
		// 获取服刑人员负责矫正机构和责任干警
		FXRYReportOrg fxryReportOrgModel = fxryReportOrgService
				.getByFXRYId(fxryid);
		if (fxryReportOrgModel != null) {
			String personId = fxryReportOrgModel.getPersonId();
			if (personId != null && !("").equals(personId)) {
				Persons person = personsService.get(personId);
				if (person != null) {
					reportInfo.setAffirmUserId(personId);
					reportInfo.setaffirmUserName(person.getName());
				}
			}
			reportInfo.setReportOrgId(fxryReportOrgModel.getOrgId());
		}
		reportInfo.setAffirmTime(new Date());
		// 目前没机构接口，以后增加
		// reportInfoModel.setaffirmUserName(affirmUserName);
		reportInfo.setdataStatus(1);
		reportInfo.setFxryId(fxryid);
		reportInfo.setRealReportDate(DateTimeFmtSpec.getDateTimeFormat().parse(
				reportdate));
		// 取该月份最后一天存到数据库中
		Calendar c = Calendar.getInstance();
		c.setTime(DateTimeFmtSpec.getMonthFormat().parse(nextreportdate));
		c.add(Calendar.MONTH, 2);
		c.add(Calendar.DAY_OF_MONTH, -1);
		reportInfo.setReportDate(c.getTime());

		// 20151010新增区分手工报告和指纹报告
		reportInfo.setReportType(reportType);
		reportInfo.setDescription(description);
		reportInfo.setCreaterId(user.getId());

		reportinfoService.create(reportInfo);
	}

@Override
public List<PropertyFilter> buildPropertyFilter(ViewFxryAddreportinfo entity) {
List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
return filters;
}

public List findBySql(String sql, Object... value) {
Query q = dao.createSQLQuery(sql, value);
return q.list();
}

}

