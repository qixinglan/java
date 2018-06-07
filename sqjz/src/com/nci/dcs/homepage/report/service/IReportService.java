package com.nci.dcs.homepage.report.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.system.model.User;

public abstract class IReportService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	abstract public ReportData getReportData(User user);

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2015年4月8日下午2:16:31
	 */
	public class ReportListComparator implements Comparator<ReportList> {
		@Override
		public int compare(ReportList left, ReportList right) {
			if (left.getStort() == null || right.getStort() == null) {
				return 0;
			}
			return left.getStort().compareTo(right.getStort());
		}

	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @param map
	 * @param list
	 * @param emptyText
	 * @param keyPosition
	 * @param valuePosition
	 * @return
	 * @since 2015年4月8日下午2:16:26
	 */
	public List<ReportList> organiseReport(Map<String, String> map, List list,
			String emptyText, int keyPosition, int valuePosition) {
		List<ReportList> reportLists = new ArrayList<ReportList>();
		try {
			for (Object obj : list) {
				Object[] object = (Object[]) obj;
				if (map.containsKey(object[keyPosition])) {
					ReportList reportList = new ReportList();
					reportList.setName(map.get(object[keyPosition]));
					reportList.setStort(object[keyPosition].toString());
					reportList.setValue(object[valuePosition].toString());
					reportLists.add(reportList);
					map.remove(object[1]);
				} else {
					ReportList reportList = new ReportList();
					reportList.setName(emptyText);
					reportList.setStort("");
					reportList.setValue(object[valuePosition].toString());
					reportLists.add(reportList);
				}
			}
			for (String key : map.keySet()) {
				ReportList reportList = new ReportList();
				reportList.setName(map.get(key));
				reportList.setStort(key);
				reportList.setValue("0");
				reportLists.add(reportList);
			}
		} catch (Exception e) {
			logger.warn("组织报表数据出错", e);
		}
		return reportLists;
	}
}
