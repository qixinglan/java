package com.nci.dcs.jzgl.mission.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageResponse;
import com.nci.dcs.jzgl.mission.model.MissionInfo;
import com.nci.dcs.jzgl.mission.model.ViewEducation;
import com.nci.dcs.jzgl.mission.model.ViewIllExamination;
import com.nci.dcs.jzgl.mission.model.ViewInterview;
import com.nci.dcs.jzgl.mission.model.ViewPhoneReport;
import com.nci.dcs.jzgl.mission.model.ViewPublicWork;
import com.nci.dcs.jzgl.mission.service.EducationService;
import com.nci.dcs.jzgl.mission.service.IllExaminationService;
import com.nci.dcs.jzgl.mission.service.InterviewService;
import com.nci.dcs.jzgl.mission.service.MissionInfoService;
import com.nci.dcs.jzgl.mission.service.PhoneReportService;
import com.nci.dcs.jzgl.mission.service.PublicWorkService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MissionInfoAction extends BaseAction<MissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8426055226358418580L;

	protected AjaxFormResult ajaxFormResult;
	@Autowired
	private MissionInfoService missionInfoService;
	@Autowired
	private PhoneReportService phoneReportService;
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private PublicWorkService publicWorkService;
	@Autowired
	private IllExaminationService illExaminationService;

	public Page getRequestPage(Page page, Class type) {
		List c = new ArrayList();
		page.setCriterions(c);
		if (jqgrid != null) {
			page.setPageNo(jqgrid.getPageStart());
			page.setPageSize(jqgrid.getLimit());
			page.setOrderBy(jqgrid.getSortCol());
			page.setOrder(jqgrid.getSortOrder());
			page.setAutoCount(true);
			List criter = jqgrid.getCriterions(type);
			if (criter == null) {
				criter = new ArrayList();
			}
			page.setCriterions(criter);
		}
		return page;
	}

	@Override
	public String list() throws Throwable {
		return null;
	}

	public String getMissionInitData() {
		User user = getUser();
		this.entity.setReportDateTime(DateTimeFmtSpec.getDateTimeFormat().format(new Date()));
		this.entity.setPersonName(user.getName());
		this.entity.setCreateTime(DateTimeFmtSpec.getDateFormat().format(new Date()));
		return SUCCESS;
	}
	
	private void findMission(String fxryId, String type) {
		this.getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", fxryId));
		page.getCriterions().add(Restrictions.eq("missionType", type));
		missionInfoService.findPaged(page);
	}

	public String phoneReportList() throws Throwable {
		Page<ViewPhoneReport> page = new Page();
		page = getRequestPage(page, ViewPhoneReport.class);
		Bmb org = getCurOrg();
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", org.getBm()));
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		phoneReportService.findPaged(page);
		setJqgridPage(new JQGridPageResponse(page));
		return SUCCESS;
	}

	public String phoneReport() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			String reportDateTime = request.getParameter("reportDateTime");
			String bz = request.getParameter("bz");
			Date now = new Date();
			if (!CommonUtils.isNull(reportDateTime)) {
				now = DateTimeFmtSpec.getDateTimeFormat().parse(reportDateTime);
			}
			if (!CommonUtils.isNull(fxryId)) {
				missionInfoService.createPhoneReport(fxryId, getUser(),now, bz);
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				missionInfoService.createPhoneReport(fxryIds.split(","),
						getUser(),now, bz);
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

	public String fxryPhoneReport() throws Throwable {
		String fxryId = request.getParameter("id");
		findMission(fxryId, "1");
		return SUCCESS;
	}
	
	public String illExaminationList() throws Throwable {
		Page<ViewIllExamination> page = new Page();
		page = getRequestPage(page, ViewIllExamination.class);
		Bmb org = getCurOrg();
		if(org.isQxsfj()){
			//检查用户是否有查看特管人员权限特管
			if("2".equals(getUser().getIsws())){
				page.getCriterions().add(Restrictions.eq("isTgry","2"));
			}
			page.getCriterions().add(Restrictions.eq("supOrgId", org.getBm()));
		}
		if(org.isSfs()){
			//检查用户是否有查看特管人员权限特管
			if("2".equals(getUser().getIsws())){
				page.getCriterions().add(Restrictions.eq("isTgry","2"));
			}
			// 司法所按负责单位查询
			page.getCriterions().add(Restrictions.eq("orgId", org.getBm()));
		}
		illExaminationService.findPaged(page);
		setJqgridPage(new JQGridPageResponse(page));
		return SUCCESS;
	}

	public String illExamination() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			String reportDateTime = request.getParameter("reportDateTime");
			String bz = request.getParameter("bz");
			Date now = new Date();
			if (!CommonUtils.isNull(reportDateTime)) {
				now = DateTimeFmtSpec.getDateTimeFormat().parse(reportDateTime);
			}
			if (!CommonUtils.isNull(fxryId)) {
				missionInfoService.createIllExamination(fxryId, getUser(),now,bz);
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				missionInfoService.createIllExamination(fxryIds.split(","),
						getUser(),now,bz);
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (CommonUtils.isNull(fxryId) && CommonUtils.isNull(fxryIds)) {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行病检！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String fxryIllExamination() throws Throwable {
		String fxryId = request.getParameter("id");
		findMission(fxryId, "5");
		return SUCCESS;
	}

	public String interviewList() throws Throwable {
		Page<ViewInterview> page = new Page(); // 分页对象
		page = getRequestPage(page, ViewInterview.class);
		Bmb org = getCurOrg();
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", org.getBm()));
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		interviewService.findPaged(page);
		setJqgridPage(new JQGridPageResponse(page));
		return SUCCESS;
	}

	public String interview() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			if (!CommonUtils.isNull(fxryId)) {
				missionInfoService.createInterview(fxryId, getUser());
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				missionInfoService.createInterview(fxryIds.split(","),
						getUser());
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (CommonUtils.isNull(fxryId) && CommonUtils.isNull(fxryIds)) {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行走访！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String fxryInterview() throws Throwable {
		String fxryId = request.getParameter("id");
		findMission(fxryId, "2");
		return SUCCESS;
	}

	public String educationList() throws Throwable {
		Page<ViewEducation> page = new Page(15); // 分页对象
		page = getRequestPage(page, ViewEducation.class);
		Bmb org = getCurOrg();
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", org.getBm()));
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		educationService.findPaged(page);
		setJqgridPage(new JQGridPageResponse(page));
		return SUCCESS;
	}

	public String education() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			if (!CommonUtils.isNull(fxryId)) {
				missionInfoService.createEducation(fxryId, getUser());
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				missionInfoService.createEducation(fxryIds.split(","),
						getUser());
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (CommonUtils.isNull(fxryId) && CommonUtils.isNull(fxryIds)) {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行教育！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String fxryEducation() throws Throwable {
		String fxryId = request.getParameter("id");
		findMission(fxryId, "3");
		return SUCCESS;
	}

	public String publicWorkList() throws Throwable {
		Page<ViewPublicWork> page = new Page(15); // 分页对象
		page = getRequestPage(page, ViewPublicWork.class);
		Bmb org = getCurOrg();
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", org.getBm()));
		//检查用户是否有查看特管人员权限特管
				if("2".equals(getUser().getIsws())){
					page.getCriterions().add(Restrictions.eq("isTgry","2"));
				}
		publicWorkService.findPaged(page);
		setJqgridPage(new JQGridPageResponse(page));
		return SUCCESS;
	}

	public String publicWork() throws Throwable {
		try {
			String fxryId = request.getParameter("id");
			String fxryIds = request.getParameter("ids");
			String reportDateTime = request.getParameter("reportDateTime");
			String bz = request.getParameter("bz");
			String sfcy = request.getParameter("sfcy");
			Date now = new Date();
			if (!CommonUtils.isNull(reportDateTime)) {
				now = DateTimeFmtSpec.getDateTimeFormat().parse(reportDateTime);
			}
			if (!CommonUtils.isNull(fxryId)) {
				
				MissionInfo missionInfo = new MissionInfo();
				missionInfo.setMissionType("4");
				missionInfo.setFxryId(fxryId);
			    missionInfo.setPersonId(getUser().getId());
			    missionInfo.setPersonName(getUser().getName());
				
//				Date now = new Date();
//				missionInfo.setAccomplishDate(entity.getAccomplishDate());
				missionInfo.setAccomplishDate(now);
				missionInfo.setMissionStart(missionInfoService.getFirstDayForMonth(now));
				missionInfo.setMissionEnd(missionInfoService.getLastDayForMonth(now));
				missionInfo.setBz(entity.getBz());
				missionInfo.setSfcy(entity.getSfcy());
				missionInfo.setCreateDate(new Date());
				missionInfoService.create(missionInfo);
				
				
				//missionInfoService.createPublicWork(fxryId, getUser());
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (!CommonUtils.isNull(fxryIds)) {
				missionInfoService.createPublicWork(fxryIds.split(","),
						getUser(),now,bz,sfcy);
				ajaxFormResult = AjaxFormResult.success("");
			}
			if (CommonUtils.isNull(fxryId) && CommonUtils.isNull(fxryIds)) {
				ajaxFormResult = AjaxFormResult.error("未选中任何人员进行社区服务！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String fxryPublicWork() throws Throwable {
		String fxryId = request.getParameter("id");
		findMission(fxryId, "4");
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

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
}
