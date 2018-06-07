package com.nci.dcs.jzgl.rcbdgl.action;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.jzgl.rcbdgl.model.CcVacateInfo;
import com.nci.dcs.jzgl.rcbdgl.service.VacateService;
import com.nci.dcs.system.model.User;

public class VacateAction extends BaseAction<CcVacateInfo> {
	protected AjaxFormResult ajaxFormResult;

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	@Autowired
	private VacateService service;

	public VacateService getService() {
		return service;
	}

	public void setService(VacateService service) {
		this.service = service;
	}

	@Override
	public String list() throws Throwable {

		return SUCCESS;
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
		Date startDate = entity.getStartDate();
		Date endDate = entity.getEndDate();
		Integer period = (int) ((endDate.getTime() - startDate.getTime())
				/ 1000 / 60 / 60 / 24 + 1);
		entity.setPeriod(period);
		entity.setRecordOrgId(getUser().getWunit().getBm());
		entity.setRecordUserId(getUserId());
		entity.setRecordTime(new Date());
		service.create(entity);
		ajaxFormResult = AjaxFormResult.success(entity.getId());
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub

		return null;
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

	public String vacateSearch() throws Throwable {
		this.getRequestPage();
		page.getCriterions().add(
				Restrictions.eq("recordOrgId", getUser().getWunit().getBm()));
		service.findPaged(page);
		return SUCCESS;
	}
	
	public String getReportInitData() {
		User user = getUser();
		this.entity.setReportDateTime(DateTimeFmtSpec.getDateTimeFormat().format(new Date()));
		this.entity.setCreater(user.getName());
		this.entity.setCreateTime(DateTimeFmtSpec.getDateFormat().format(new Date()));
		return SUCCESS;
	}

	public String report() throws Throwable {
		try {
			String id = request.getParameter("id");
			String fxryid = request.getParameter("fxryid");
			String ids = request.getParameter("ids");
			String userId = getUser().getId();
			Date date= new Date();
			if(!CommonUtils.isNull(ids)){
				String bz = request.getParameter("bz");
				String[] idArr = ids.split(",");
				String reportDateTime = request.getParameter("reportDateTime");
				Date now = DateTimeFmtSpec.getDateTimeFormat().parse(reportDateTime);
				for(String temp : idArr){
					CcVacateInfo vacate = service.get(temp);
					vacate.setReportDate(now);
					vacate.setBz(bz);
					vacate.setCreaterId(userId);
					vacate.setCreateDate(date);
					service.create(vacate);
				}
			}else if (!CommonUtils.isNull(id)) {
				CcVacateInfo vacate = service.get(id);
				vacate.setReportDate(new Date());
				vacate.setCreaterId(userId);
				vacate.setCreateDate(date);
				service.create(vacate);
			} else if (!CommonUtils.isNull(fxryid)) {
				List<CcVacateInfo> vacates = service.find(
						"from CcVacateInfo where personId=? and reportDate is null", fxryid);
				if (CommonUtils.isNotNull(vacates)) {
					for (CcVacateInfo vacate : vacates) {
						vacate.setReportDate(new Date());
						vacate.setCreaterId(userId);
						vacate.setCreateDate(date);
						service.create(entity);
					}
				}
			}
			ajaxFormResult = AjaxFormResult.success("");
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}
}
