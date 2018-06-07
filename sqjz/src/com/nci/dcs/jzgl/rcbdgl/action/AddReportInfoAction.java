package com.nci.dcs.jzgl.rcbdgl.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.data.service.JjszSevice;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryAddreportinfo;
import com.nci.dcs.jzgl.rcbdgl.service.AddReportInfoService;
import com.nci.dcs.jzgl.rcbdgl.service.ReportInfoService;
import com.nci.dcs.system.model.User;

public class AddReportInfoAction extends BaseAction<ViewFxryAddreportinfo> {

	protected AjaxFormResult ajaxFormResult;

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	@Autowired
	private AddReportInfoService service;

	@Autowired
	public JjszSevice jjszSevice;

	public AddReportInfoService getService() {
		return service;
	}

	public void setService(AddReportInfoService service) {
		this.service = service;
	}

	@Autowired
	private ReportInfoService reportinfoService;

	// public ReportInfoService getReportInfoService() {
	// return reportinfoService;
	// }

	// public void setReportInfoService(ReportInfoService service) {
	// this.reportinfoService = service;
	// }

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date reportDate = sdf.parse(this.entity.getReportdate());
		this.entity.setNextreportdate(sdfTime.format(reportDate));
		try {
			service.addReportInfo(this.entity.getFxryid(),
					sdfTime.format(reportDate), this.entity.getNextreportdate(), "1", "",getUser());
			ajaxFormResult = AjaxFormResult.success(entity.getFxryid());
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return "success";
	}

	/**
	 * Description:待办事项中报到
	 *
	 * @author Shuzz
	 * @return
	 * @throws Throwable
	 * @since 2015年4月1日上午10:33:12
	 */
	public String todoAdd() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			String reportType = request.getParameter("reportType");
			String description = request.getParameter("description");
			String reportDate = request.getParameter("reportDateTime");
			if(CommonUtils.isNull(reportDate)){
				SimpleDateFormat sdfTime = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				reportDate = sdfTime.format(new Date());
			}
			User user = getUser();
			if (!CommonUtils.isNull(fxryId)) {
				service.addReportInfo(fxryId, reportDate, reportDate,
						reportType, description,user);
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				for (String id : fxryIds.split(",")) {
					service.addReportInfo(id, reportDate, reportDate,
							reportType, description,user);
				}
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (CommonUtils.isNull(fxryId) && CommonUtils.isNull(fxryIds)) {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行报到！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public String search() {
		String id = request.getParameter("id");
		this.entity = service.get(id);
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONDAY, 1);
		this.entity.setReportdate(DateTimeFmtSpec.getDateFormat().format(dt));
		this.entity.setNextreportdate(DateTimeFmtSpec.getMonthFormat().format(
				c.getTime()));

		String cCode = this.getCurOrgId();
		List lst = new ArrayList();
		lst = jjszSevice.getfxryData(cCode);
		this.entity.setFxrylist(lst);
		return "success";
	}

	public String Toadd() throws Throwable {
		// TODO Auto-generated method stub

		return "add";
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

}