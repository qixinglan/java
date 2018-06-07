package com.nci.dcs.homepage.report.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

@Service
public class AlarmInfoReportService extends IReportService {
	
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;
	
	@Autowired
	DictionaryInfoService dictionaryService;
	
	@Autowired
	private OrganizationInfoService organizationInfoService;
	/**
	 * @name 获取报警信息趋势图数据
	 * @return
	 * @throws Throwable
	 * @author caolj
	 * @date 2015年4月7日 上午10:10:36
	 * @message:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReportData getReportData(User user) {
		List<Category> searchTimes = new ArrayList<Category>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		int tempDays = 7;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -tempDays+1);
		for (int i = 0; i < tempDays; i++) {
			Category category = new Category(DateTimeFmtSpec.getDateFormat()
					.format(cal.getTime()).substring(5, 10));
			searchTimes.add(category);
			cal.add(Calendar.DATE, 1);
		}
		String detail="最近一周产生报警总数：";
		List<DictionaryKeyValue> iteams = dictionaryService.findItems("BJLX");
		List<Criterion> criterions = new ArrayList<Criterion>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//判断机构类型
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		String orgId = org.getBm();
		Map<String,List<Data>> result = new HashMap<String,List<Data>>();//封装结果MAP
		//添加报警类型
		for (DictionaryKeyValue temp : iteams) {
			result.put(temp.getCode(), new ArrayList<Data>());
		}
		List<Data> zsDs = new ArrayList<Data>();//总数
		List<Data> datas = null;
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -tempDays+1);
		int zsInt = 0;
		//循环查看每天各类报警次数
		if (org.isSfs()) {
			detail=org.getMc()+detail;
		} else if (org.isQxsfj()) {
			detail=org.getMc()+detail;
		}else{
			detail="北京市"+detail;
		}
		for (int i = 0; i < tempDays; i++) {
			String time = DateTimeFmtSpec.getDateFormat().format(
					cal1.getTime());
			String startTime = time + " 00:00:00";
			String endTime = time + " 24:00:00";
			try {
				criterions.add(Restrictions.and(
						Restrictions.ge("alarmTime",
								sdf.parse(startTime)),
						Restrictions.lt("alarmTime",
								sdf.parse(endTime))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (org.isSfs()) {
				criterions.add(Restrictions.eq("executeUnit", orgId));//机构
			} else if (org.isQxsfj()) {
				criterions.add(Restrictions.in("executeUnit", organizationInfoService.getChildrenIds(orgId)));//机构
			}
			List<Object[]> jlist = viewReportFxryInfoService.findByCriteria(criterions,
					CcAlarmInfo.class, Projections.projectionList().add(Projections.rowCount())
					.add(Projections.groupProperty("alarmType")), null);
			//将报警次数计入结果中对应大类
			for(Object[] res: jlist){
				datas = result.get(String.valueOf(res[1]));
				datas.add(new Data(String.valueOf(res[0])));
				zsInt += (Integer)res[0];
			}
			zsDs.add(new Data(String.valueOf(zsInt)));//添加当天报警总数
			//补全未查出的其他大类报警次数
			for (DictionaryKeyValue temp : iteams) {
				datas = result.get(temp.getCode());
				if(datas.size() < i+1){
					result.get(temp.getCode()).add(new Data("0"));
				}
			}
			zsInt = 0;
			cal1.add(Calendar.DATE, 1);
			criterions.clear();//清空查询条件
		}
		//按大类提取数据并封装返回参数
		for (DictionaryKeyValue temp : iteams) {
			DataSet ds = new DataSet(temp.getName());
			ds.setData(result.get(temp.getCode()));
			dateSets.add(ds);
		}
		DataSet ds = new DataSet("报警总数");
		ds.setData(zsDs);
		dateSets.add(ds);
		long count =0;
		for(Data zs:zsDs){
			count=count+Integer.parseInt(zs.getValue());
		}
		detail=detail+count+"次";
		ReportData data = new ReportData();
		data.setCategory(searchTimes);
		data.setDataset(dateSets);
		data.setDetail(detail);
		return data;
	}
}
