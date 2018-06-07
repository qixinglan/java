package com.nci.dcs.homepage.report.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.homepage.report.model.ReportModule;
import com.nci.dcs.homepage.report.service.IReportService;
import com.nci.dcs.homepage.report.service.ReportService;
import com.nci.dcs.system.model.Bmb;

@SuppressWarnings("serial")
public class ReportAction extends BaseAction<ReportModule> {
	@Autowired
	private ReportService reportService;

	private List<ReportModule> reportModules;

	/**
	 * Description:获取当前用户可见的报表，用于工作桌面。<br>
	 * 原计划展示多张报表，由于老旧浏览器性能问题，目前只展示一张。
	 * 
	 * @return
	 */
	public String getMineData() {
		reportModules = getVisibleModules();
		return SUCCESS;
	}

	/**
	 * Description:用以按当前用户所有的报表,但不获取数据
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年4月17日上午9:17:10
	 */
	public String getAllData() {
		reportModules = getModules();
		return SUCCESS;
	}

	/**
	 * Description:用以按id获取每张报表的数据
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年4月17日上午9:17:12
	 */
	public String getReportData() {
		reportModules = new ArrayList<ReportModule>();
		try {
			String id = request.getParameter("id");
			if (!CommonUtils.isNull(id)) {
				ReportModule all = reportService.get(id);
				if(null!=all){
					orgReportData(all);
					reportModules.add(all);
				}
			}
		} catch (Exception e) {
			logger.error("获取报表数据出错", e);
		}
		return SUCCESS;
	}

	/**
	 * Description:组织报表中需要展示的数据
	 * 
	 * @author Shuzz
	 * @param module
	 * @since 2015年4月17日上午9:19:44
	 */
	private void orgReportData(ReportModule module) {
		if (!CommonUtils.isNull(module.getService())) {
			try {
				IReportService service = (IReportService) SpringContextUtil
						.getBean(module.getService());
				ReportData data = service.getReportData(getUser());
				module.setListData(data.getListData());
				module.setCategory(data.getCategory());
				module.setDataset(data.getDataset());
				module.setDetail(data.getDetail());
			} catch (BeansException e) {
				logger.warn("获取报表数据错误，Bean不存在：" + module.getService());
			} catch (Exception e) {
				logger.warn("获取报表数据错误，Bean必须实现接口IReportService："
						+ module.getService());
			}
		}
	}

	/**
	 * 获取用户首页可见的统计图
	 * 
	 * @return
	 */
	private List<ReportModule> getVisibleModules() {
		List<ReportModule> mine = getModules();
		Iterator<ReportModule> iter = mine.iterator();
		while (iter.hasNext()) {
			ReportModule module = iter.next();
			if (!"1".equals(module.getVisible())) {
				iter.remove();
				continue;
			}
			orgReportData(module);
		}
		return mine;
	}

	/**
	 * Description:根据机构获取报表信息
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年4月8日上午9:29:37
	 */
	private List<ReportModule> getModules() {
		List<ReportModule> all = reportService.getAllModules();
		Bmb org = this.getCurOrg();
		List<ReportModule> visibleModules = new ArrayList<ReportModule>();
		for (ReportModule module : all) {
			if (org.getOrgType().equals(module.getPermission())) {
				visibleModules.add(module);
			}
		}
		Collections.sort(visibleModules, new Comparator<ReportModule>() {
			public int compare(ReportModule left, ReportModule right) {
				if (left.getSort() == null || right.getSort() == null) {
					return 0;
				}
				return left.getSort().compareTo(right.getSort());
			}
		});
		return visibleModules;
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

}
