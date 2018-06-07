package com.nci.dcs.homepage.report.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.jzgl.cxtj.model.ViewReportFxryDevice;
import com.nci.dcs.jzgl.cxtj.service.ViewReportFxryInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

/**
 * Description:电子监管人员历时趋势图
 * 
 * @author Shuzz
 * @since 2015年4月8日下午3:25:26
 */
@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DzjgPersonHistoryReportService extends IReportService {
	@Autowired
	private ViewReportFxryInfoService viewReportFxryInfoService;

	/**
	 * Description:组织最基本的机构查询条件
	 * 
	 * @author Shuzz
	 * @param org
	 * @return
	 * @since 2015年4月9日下午2:38:13
	 */
	private List<Criterion> organizeBaseCriterion(Bmb org) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		if (org.isQxsfj()) {
			criterions.add(Restrictions.eq("supOrg", org.getBm()));
		} else if (org.isSfs()) {
			criterions.add(Restrictions.eq("responseOrg", org.getBm()));
		}
		return criterions;
	}

	/**
	 * Description:为时间周期中无数据的月份填0数据
	 * 
	 * @author Shuzz
	 * @param searchTimes
	 *            时间段list
	 * @param list
	 *            包含count和时间两个字段的list
	 * @return datas 报表数据中每一条趋势线的具体数据
	 * @since 2015年4月9日下午2:33:59
	 */
	private List<Data> organizeZeroData(List<Date> searchTimes, List list) {
		List<Data> datas = new ArrayList<Data>();
		SimpleDateFormat sf = DateTimeFmtSpec.getMonthFormat();
		Map map = new HashMap();
		for (Object obj : list) {
			Object[] object = (Object[]) obj;
			Timestamp timestamp = (Timestamp) object[1];
			map.put(sf.format(timestamp), object[0]);
		}
		for (Date date : searchTimes) {
			Data data = new Data("0");
			String time = sf.format(date);
			if (map.containsKey(time)) {
				data.setValue(map.get(time).toString());
			}
			datas.add(data);
		}
		return datas;
	}

	/**
	 * Description:每月新增佩戴人员
	 * 
	 * @author Shuzz
	 * @param org
	 *            机构
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param searchTimes
	 *            按月划分的时间list
	 * @return
	 * @since 2015年4月8日下午3:22:16
	 */
	private DataSet increasedData(Bmb org, Date start, Date end,
			List<Date> searchTimes) {
		/**
		 * 新增人员的开始佩戴时间需包含在起始时间内<br>
		 * 且unbindStatus需为以下几个状态：<br>
		 * 1、未经历司法所间转移，为null；<br>
		 * 2、该司法所为第一个转出所，为10。
		 */
		List<Criterion> criterions = organizeBaseCriterion(org);
		criterions.add(Restrictions.ge("start", start));
		criterions.add(Restrictions.lt("start", end));
		criterions.add(Restrictions.or(Restrictions.isNull("unbindStatus"),
				Restrictions.eq("unbindStatus", "10")));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.countDistinct("fxryId"));
		projectionList.add(Projections.groupProperty("start"));

		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("start"));

		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewReportFxryDevice.class, projectionList, orders);

		DataSet dataSet = new DataSet("新佩戴人员");
		dataSet.setData(organizeZeroData(searchTimes, list));
		return dataSet;
	}

	/**
	 * Description:每月解除佩戴人员
	 * 
	 * @author Shuzz
	 * @param org
	 * @param start
	 * @param end
	 * @param searchTimes
	 * @return
	 * @since 2015年4月8日下午3:23:00
	 */
	private DataSet removeData(Bmb org, Date start, Date end,
			List<Date> searchTimes) {
		/**
		 * 解除佩戴人员的结束佩戴时间需包含在起始时间内<br>
		 * 且unbindStatus需为以下几个状态：<br>
		 * 1、未经历司法所间转移，为null；<br>
		 * 2、经司法所转入后，在该司法所解除绑定的，为2。
		 */
		List<Criterion> criterions = organizeBaseCriterion(org);
		criterions.add(Restrictions.ge("end", start));
		criterions.add(Restrictions.lt("end", end));
		criterions.add(Restrictions.or(Restrictions.isNull("unbindStatus"),
				Restrictions.eq("unbindStatus", "2")));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.countDistinct("fxryId"));
		projectionList.add(Projections.groupProperty("end"));

		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewReportFxryDevice.class, projectionList, null);

		DataSet dataSet = new DataSet("解除佩戴人员");
		dataSet.setData(organizeZeroData(searchTimes, list));
		return dataSet;
	}

	/**
	 * Description:每月在佩戴人员
	 * 
	 * @author Shuzz
	 * @param org
	 * @param start
	 * @param end
	 * @param searchTimes
	 * @return
	 * @since 2015年4月8日下午3:26:28
	 */
	private DataSet wearsData(Bmb org, Date start, Date end,
			List<Date> searchTimes) {
		ProjectionList projectionList = Projections.projectionList();
		projectionList = Projections.projectionList();
		projectionList.add(Projections.countDistinct("fxryId"));
		List<Order> orders = new ArrayList<Order>();
		List<Data> datas = new ArrayList<Data>();

		List list = null;
		for (Date date : searchTimes) {
			Data data = new Data("0");
			List<Criterion> criterions = organizeBaseCriterion(org);
			criterions.add(Restrictions.le("start", date));
			criterions.add(Restrictions.or(Restrictions.ge("end", date),
					Restrictions.isNull("end")));
			list = viewReportFxryInfoService.findByCriteria(criterions,
					ViewReportFxryDevice.class, projectionList, orders);
			if (CommonUtils.isNotNull(list)) {
				data.setValue(list.get(0).toString());
			}
			datas.add(data);
		}
		DataSet dataSet = new DataSet("在佩戴人员");
		dataSet.setData(datas);
		return dataSet;
	}

	/**
	 * Description:司法所的转入人员
	 * 
	 * @author Shuzz
	 * @param org
	 * @param start
	 * @param end
	 * @param searchTimes
	 * @return
	 * @since 2015年4月8日下午4:31:29
	 */
	private DataSet transferIn(Bmb org, Date start, Date end,
			List<Date> searchTimes) {
		/**
		 * 司法所转入人员的开始佩戴时间需包含在起始时间内<br>
		 * 且unbindStatus需为以下几个状态：<br>
		 * 1、经司法所转入后，未转出且还在佩戴的，为01；<br>
		 * 2、经司法所转入后，又从该司法所转出，为11；<br>
		 * 3、经司法所转入后，在该司法所解除绑定的，为2。
		 */
		List<Criterion> criterions = organizeBaseCriterion(org);
		criterions.add(Restrictions.ge("start", start));
		criterions.add(Restrictions.lt("start", end));
		criterions.add(Restrictions.or(Restrictions.or(
				Restrictions.eq("unbindStatus", "11"),
				Restrictions.eq("unbindStatus", "01")), Restrictions.eq(
				"unbindStatus", "2")));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.countDistinct("fxryId"));
		projectionList.add(Projections.groupProperty("start"));

		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("start"));

		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewReportFxryDevice.class, projectionList, orders);

		DataSet dataSet = new DataSet("转入人员");
		dataSet.setData(organizeZeroData(searchTimes, list));
		return dataSet;
	}

	/**
	 * Description:司法所的转出人员
	 * 
	 * @author Shuzz
	 * @param org
	 * @param start
	 * @param end
	 * @param searchTimes
	 * @return
	 * @since 2015年4月9日上午10:05:16
	 */
	private DataSet transferOut(Bmb org, Date start, Date end,
			List<Date> searchTimes) {
		/**
		 * 司法所转出人员的结束佩戴时间需包含在起始时间内<br>
		 * 且unbindStatus需为以下几个状态：<br>
		 * 1、该司法所为第一个转出所，为10；<br>
		 * 2、经司法所转入后，又从该司法所转出，为11。<br>
		 */
		List<Criterion> criterions = organizeBaseCriterion(org);
		criterions.add(Restrictions.ge("end", start));
		criterions.add(Restrictions.lt("end", end));
		criterions.add(Restrictions.or(Restrictions.eq("unbindStatus", "11"),
				Restrictions.eq("unbindStatus", "10")));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.countDistinct("fxryId"));
		projectionList.add(Projections.groupProperty("end"));
		List<Order> orders = new ArrayList<Order>();
		List list = viewReportFxryInfoService.findByCriteria(criterions,
				ViewReportFxryDevice.class, projectionList, orders);

		DataSet dataSet = new DataSet("转出人员");
		dataSet.setData(organizeZeroData(searchTimes, list));
		return dataSet;
	}

	@Override
	public ReportData getReportData(User user) {
		Bmb org = LoginInfoUtils.getCurOrg(StrutsSessionManager.getSession());
		List<Category> categories = new ArrayList<Category>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		List<Date> searchTimes = new ArrayList<Date>();
		// 组织查询时间条件
		Date start = null, end = null, temp = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// 数据库时间基本为timestamp故需清零以下域
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.MONTH, 1);
		end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		start = temp = calendar.getTime();
		SimpleDateFormat sf = DateTimeFmtSpec.getMonthFormat();
		while (temp.before(end)) {
			// 组织趋势图的x轴数据
			searchTimes.add(temp);
			Category category = new Category(sf.format(temp));
			categories.add(category);
			calendar.add(Calendar.MONTH, 1);
			temp = calendar.getTime();
		}
		DataSet xz = increasedData(org, start, end, searchTimes);
		dateSets.add(xz);
		DataSet sc = removeData(org, start, end, searchTimes);
		dateSets.add(sc);
		DataSet now = wearsData(org, start, end, searchTimes);
		dateSets.add(now);
		/**
		 * 司法所之间的人员转移是不会解除电子监管设备。 而且这种转移对区县局来说并不发生报表数据的变化。
		 * 但对司法所来说必须体现其人员转移行为，故司法所显示转入转出的数据趋势。
		 **/
		if (org.isSfs()) {
			DataSet in = transferIn(org, start, end, searchTimes);
			dateSets.add(in);
			DataSet out = transferOut(org, start, end, searchTimes);
			dateSets.add(out);
		}
		ReportData data = new ReportData();
		data.setCategory(categories);
		data.setDataset(dateSets);
		return data;
	}
}
