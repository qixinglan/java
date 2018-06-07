package com.nci.sfj.transmit.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.dom4j.Element;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.data.utils.DateUtils;
import com.nci.dcs.em.dwjk.dao.FxryLocationOnMapDao;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;
import com.nci.dcs.em.dwjk.model.CcLocationInfoBuffer;
import com.nci.dcs.em.dwjk.model.ViewAlarminfo;
import com.nci.dcs.em.dwjk.model.ViewFxryOnMap;
import com.nci.dcs.em.dwjk.service.AlarmService;
import com.nci.dcs.em.dwjk.service.LocationBufferService;
import com.nci.dcs.em.service.AlarmInfoManagerService;
import com.nci.dcs.em.service.DzjgsbDeviceService;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.homepage.report.model.ReportModule;
import com.nci.dcs.homepage.report.service.ReportService;
import com.nci.dcs.homepage.todo.model.TodoCount;
import com.nci.dcs.homepage.todo.model.TodoModule;
import com.nci.dcs.homepage.todo.service.TodoService;
import com.nci.dcs.jzgl.dagl.model.FXRYAdjustGroup;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYOutManageInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.model.FXRYTransferInfo;
import com.nci.dcs.jzgl.dagl.model.FxryNotice;
import com.nci.dcs.jzgl.dagl.model.FxryReward;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;
import com.nci.dcs.jzgl.dagl.model.ViewFXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.model.ViewFxryEducationJzcsjy;
import com.nci.dcs.jzgl.dagl.model.ViewFxryEducationJzjjqjy;
import com.nci.dcs.jzgl.dagl.model.ViewFxryNotice;
import com.nci.dcs.jzgl.dagl.model.ViewFxryReadyRelease;
import com.nci.dcs.jzgl.dagl.model.ViewFxryTransferInfo;
import com.nci.dcs.jzgl.dagl.model.ViewFxryUnmanageHistory;
import com.nci.dcs.jzgl.dagl.service.FXRYAdjustGroupService;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYOutManageInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYRemoveAdjustService;
import com.nci.dcs.jzgl.dagl.service.FXRYTransferInfoService;
import com.nci.dcs.jzgl.dagl.service.FxryNoticeService;
import com.nci.dcs.jzgl.dagl.service.FxryRewardService;
import com.nci.dcs.jzgl.dagl.service.LegalInstrumentService;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFXRYTransferInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryEducationJzcsjyService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryEducationJzjjqjyService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryNoticeService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryReadyReleaseInfoService;
import com.nci.dcs.jzgl.dagl.service.ViewFxryUnmanageHistoryService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.education.model.EducationInfo;
import com.nci.dcs.jzgl.education.service.EducationInfoService;
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
import com.nci.dcs.jzgl.rcbdgl.model.CcVacateInfo;
import com.nci.dcs.jzgl.rcbdgl.model.ViewCCFxryReportinfo;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryReportinfo;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryVacateinfo;
import com.nci.dcs.jzgl.rcbdgl.service.AddReportInfoService;
import com.nci.dcs.jzgl.rcbdgl.service.ReportListService;
import com.nci.dcs.jzgl.rcbdgl.service.VacateService;
import com.nci.dcs.jzgl.rcbdgl.service.ViewCCFxryReportInfoService;
import com.nci.dcs.jzgl.rcbdgl.service.ViewFxryVacateinfoService;
import com.nci.dcs.official.dao.DynamicreportDao;
import com.nci.dcs.official.dao.WeekNoticeDao;
import com.nci.dcs.official.model.CcDynamicreport;
import com.nci.dcs.official.model.CcDynamicreportreply;
import com.nci.dcs.official.model.CcNoticereceive;
import com.nci.dcs.official.model.CcWeeknotice;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.DynamicreportService;
import com.nci.dcs.official.service.DynamicreportreplyService;
import com.nci.dcs.official.service.NoticeReceiveServie;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.official.service.WeekNoticeService;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionCrime;
import com.nci.dcs.supervision.model.SupervisionFirstEducation;
import com.nci.dcs.supervision.model.SupervisionIllExamination;
import com.nci.dcs.supervision.model.SupervisionModule;
import com.nci.dcs.supervision.model.SupervisionMonthReport;
import com.nci.dcs.supervision.model.SupervisionPhoneReport;
import com.nci.dcs.supervision.model.SupervisionPublicWork;
import com.nci.dcs.supervision.model.SupervisionRelease;
import com.nci.dcs.supervision.model.SupervisionVacate;
import com.nci.dcs.supervision.service.SupervisionCrimeService;
import com.nci.dcs.supervision.service.SupervisionFirstEducationService;
import com.nci.dcs.supervision.service.SupervisionIllExaminationService;
import com.nci.dcs.supervision.service.SupervisionModuleService;
import com.nci.dcs.supervision.service.SupervisionMonthReportService;
import com.nci.dcs.supervision.service.SupervisionPhoneReportService;
import com.nci.dcs.supervision.service.SupervisionPublicWorkService;
import com.nci.dcs.supervision.service.SupervisionReleaseService;
import com.nci.dcs.supervision.service.SupervisionVacateService;
import com.nci.dcs.system.dao.BmbDao;
import com.nci.dcs.system.dao.UserDao;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.BmbService;
import com.nci.dcs.system.service.UserService;
import com.nci.sfj.transmit.model.DataQuery;
import com.nci.sfj.transmit.model.QueryType;
import com.nci.sfj.transmit.service.dbsx.ITodoServiceNew;
import com.nci.sfj.transmit.service.zfdc.ISupervisionServiceNew;
import com.nci.sfj.transmit.service.zfdc.report.IReportServiceNew;
import com.nci.sfj.webservice.dao.ViewQxsfjTjxxDao;
import com.nci.sfj.webservice.dao.ViewSfjTjxxDao;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.exception.PacketMessageException;

/**
 * Description:提供至移动终端的Service
 * 
 * @author chenzz
 * @date 2016-6-29下午12:57:48
 */

@Service
@Transactional
public class MobileServiceNew {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BmbDao bmbDao;
	@Autowired
	FxryLocationOnMapDao mapDao;
	@Autowired
	private ViewQxsfjTjxxDao viewQxsfjTjxxDao;
	@Autowired
	private ViewSfjTjxxDao viewSfjTjxxDao;
	@Autowired
	private WeekNoticeDao weekNoticeDao;
	@Autowired
	private DynamicreportDao dynamicreportDao;

	@Autowired
	private TodoService todoService;
	@Autowired
	protected  ViewFXRYExecuteInfoService viewFXRYExecuteInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	protected FXRYBaseInfoService fxryBaseInfoService;
	@Autowired
	LegalInstrumentService legalInstrumentService;
	@Autowired
	FXRYExecuteInfoService fxryExecuteInfoService;
	@Autowired
	FXRYAdjustGroupService fxryAdjustGroupService;
	@Autowired
	private ReportListService reportListService;
	@Autowired
	private MissionInfoService missionInfoService;
	@Autowired
	private EducationInfoService educationInfoService;
	@Autowired
	private ViewFxryVacateinfoService viewFxryVacateinfoService;
	@Autowired
	FXRYTransferInfoService fxryTransferInfoService;
	@Autowired
	FXRYOutManageInfoService fxryOutManageInfoService;
	@Autowired
	FXRYRemoveAdjustService fxryRemoveAdjustService;
	@Autowired
	private FxryRewardService fxryRewardService;
	@Autowired
	private ViewFxryNoticeService viewFxryNoticeService;
	@Autowired
	private FxryNoticeService fxryNoticeService;
	@Autowired
	ViewFXRYTransferInfoService viewFXRYTransferInfoService;
	@Autowired
	private IllExaminationService illExaminationService;
	@Autowired
	ViewFxryReadyReleaseInfoService viewFxryReadyReleaseInfoService;
	@Autowired
	private PhoneReportService phoneReportService;
	@Autowired
	private ViewCCFxryReportInfoService viewCCFxryReportInfoService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private PublicWorkService publicWorkService;
	@Autowired
	ViewFxryEducationJzcsjyService viewFxryEducationJzcsjyService;
	@Autowired
	ViewFxryEducationJzjjqjyService viewFxryEducationJzjjqjyService;
	@Autowired
	private AlarmInfoManagerService alarmInfoManagerService;
	@Autowired
	private LocationBufferService locationBufferService;
	@Autowired
	private SupervisionModuleService supervisionModuleService;
	@Autowired
	private BmbService bmbService;	
	@Autowired
	private ReportService reportService;
	@Autowired
	private WeekNoticeService weekNoticeService;
	@Autowired
	private NoticeReceiveServie noticeReceiveServie;	
	@Autowired
	private DynamicreportService dynamicreportService;
	@Autowired
	private DynamicreportreplyService dynamicreportreplyService;
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private VacateService vacateService;
	@Autowired
	private AddReportInfoService addReportInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private DzjgsbDeviceService dzjgsbDeviceService;
	@Autowired
	private SupervisionPhoneReportService supervisonPhoneReportService;
	@Autowired
	private SupervisionPublicWorkService supervisonPublicWorkService;
	@Autowired
	private SupervisionMonthReportService supervisonMonthReportService;
	@Autowired
	ViewFxryUnmanageHistoryService viewFxryUnmanageHistoryService;
	@Autowired
	private SupervisionVacateService supervisonVacateService;
	@Autowired
	private SupervisionReleaseService supervisionReleaseService;
	@Autowired
	private SupervisionFirstEducationService supervisionFirstEducationService;
	@Autowired
	private SupervisionCrimeService supervisionCrimeService;
	@Autowired
	private SupervisionIllExaminationService supervisionIllExaminationService;
	

	/**
	 * 
	 * Description:处理终端请求
	 * 
	 * @param root
	 * 			packet's childElement
	 * @param token
	 * 			
	 * @return
	 * 			packet's childElement
	 */
	public Element handleMobile(Element root, AuthToken token)
			throws PacketMessageException {
		String json = root.element("body").getText();	
		int flag = Integer.parseInt(root.attributeValue("flag"));
		int pageNo = Integer.parseInt(root.attributeValue("pageNo"));
		int pageSize = Integer.parseInt(root.attributeValue("pageSize"));
		
		Element result = root.createCopy();
		try {
			String jsonResult = handleMobileNew(json, flag, pageNo, pageSize);
			result.element("body").setText(jsonResult);
		} catch (Exception e) {
			result.element("body").setText(null);
		}
		
		return result;
	}

	/**
	 * 
	 * Description:处理终端请求
	 * 
	 * @param json
	 * 			body元素JSON串
	 * @param flag
	 * 			查询标识说明，以QueryType枚举取值
	 * @return
	 * 			终端查询数据的JSON实体
	 */
	public String handleMobileNew(String json, int flag, int pageNo, int pageSize) {
		String type = "";
		DataQuery dataQuery = new DataQuery();
		FXRYBaseinfo fxryBaseinfo = new FXRYBaseinfo();
		if (flag < QueryType.values().length) {
			type = QueryType.values()[flag].toString();
		}
		if ("JZGLBDDJXX".equals(type)) {//矫正管理报到登记信息提交
			fxryBaseinfo = JSON.parseObject(json, FXRYBaseinfo.class);
			try {
				String code = generateCode(fxryBaseinfo.getCode());
				fxryBaseinfo.setCode(code);
				fxryBaseinfo.setIsTgry("2");//终端默认都为非特管人员
				if (!StringUtils.isBlank(fxryBaseinfo.getDeviceCode())){
					fxryBaseInfoService.equip(fxryBaseinfo.getDeviceCode(), fxryBaseinfo, "", "");//绑定设备，目前终端未设置操作人和操作单位
					fxryBaseInfoService.createWithEquip(fxryBaseinfo);
				} else{
					fxryBaseInfoService.create(fxryBaseinfo);
				}
				return "success";
			} catch (Exception e) {
				return "failed";
			}
		} else {
			dataQuery = JSON.parseObject(json, DataQuery.class);
		}
		if ("JGXX".equals(type)) {//机构信息
			if (dataQuery != null) {
				User user = getUserInfo(dataQuery.getUserName());
				return JSON.toJSONString(user);
			} else {
				throw new PacketMessageException();
			}
		} else if ("DBSX".equals(type)) {//待办事项
			if (dataQuery != null) {
				return getVisibleModules(dataQuery.getUserName());
			} else {
				throw new PacketMessageException();
			}
		}
		
		if (type.startsWith("DZJG")) {
			if ("DZJGTJXX".equals(type)) {//电子监管统计信息
				if (dataQuery != null) {				
					return getCountData(dataQuery.getOrgID(), dataQuery.getOrgType());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DZJGLIST".equals(type)) {//电子监管人员列表
				if (dataQuery != null) {
					Page<ViewFxryOnMap> page = new Page<ViewFxryOnMap>(20);
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					GISCriterions(page, dataQuery.getOrgID(), dataQuery.getCommonValue());
					return JSON.toJSONString(mapDao.findByCriteria(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DZJGLSGJLIST".equals(type)) {// 电子监管历史轨迹信息列表
				if (dataQuery != null) {
					List<CcLocationInfoBuffer> datas = getLocationHistory(dataQuery.getRyID(), dataQuery.getStartTime(), dataQuery.getEndTime());
					return JSON.toJSONString(datas);
				} else {
					throw new PacketMessageException();
				}
			}
		}
		
		if (type.startsWith("ZJRY")) {
			if ("ZJRYLIST".equals(type)) {//在矫人员列表
				if (dataQuery != null) {
					Page<ViewFXRYExecuteInfo> page = new Page<ViewFXRYExecuteInfo>(20);
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);				
					orgCriterions(page, dataQuery.getOrgType(), dataQuery.getOrgID(), dataQuery.getCommonValue());			
					return JSON.toJSONString(viewFXRYExecuteInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJBXX".equals(type)) {//在矫人员基本信息
				if (dataQuery != null) {				
					return getZjryJbxx(dataQuery.getRyID());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYFLWS".equals(type)) {//在矫人员法律文书信息
				if (dataQuery != null) {				
					return getZjryFlws(dataQuery.getRyID());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYXFZX".equals(type)) {//在矫人员刑罚执行信息
				if (dataQuery != null) {				
					return getZjryXfzx(dataQuery.getRyID());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJZCYLIST".equals(type)) {//在矫人员矫正成员信息列表
				if (dataQuery != null) {				
					return getZjryJzcyxx(dataQuery.getRyID());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYDYBDLIST".equals(type)) {//在矫人员当月报到记录
				if (dataQuery != null) {
					Page<ViewFxryReportinfo> page = new Page<ViewFxryReportinfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					dybdCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(reportListService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYDHBDLIST".equals(type)) {//在矫人员电话报到记录
				if (dataQuery != null) {
					Page<MissionInfo> page = new Page<MissionInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					getCriterions(page, dataQuery.getRyID(), "1");
					return JSON.toJSONString(missionInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJWBJLIST".equals(type)) {//在矫人员暂监外病检记录
				if (dataQuery != null) {
					Page<MissionInfo> page = new Page<MissionInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					getCriterions(page, dataQuery.getRyID(), "5");
					return JSON.toJSONString(missionInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYSQFWLIST".equals(type)) {//在矫人员社区服务记录
				if (dataQuery != null) {
					Page<MissionInfo> page = new Page<MissionInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					getCriterions(page, dataQuery.getRyID(), "4");
					return JSON.toJSONString(missionInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYMYZFLIST".equals(type)) {//在矫人员每月走访记录
				if (dataQuery != null) {
					Page<MissionInfo> page = new Page<MissionInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					getCriterions(page, dataQuery.getRyID(), "2");
					return JSON.toJSONString(missionInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYMYJYLIST".equals(type)) {//在矫人员每月教育记录
				if (dataQuery != null) {
					Page<MissionInfo> page = new Page<MissionInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					getCriterions(page, dataQuery.getRyID(), "3");
					return JSON.toJSONString(missionInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJZJYLIST".equals(type)) {//在矫人员集中教育记录
				if (dataQuery != null) {
					Page<EducationInfo> page = new Page<EducationInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					eduCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(educationInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYQJJLLIST".equals(type)) {//在矫人员请假记录
				if (dataQuery != null) {
					Page<ViewFxryVacateinfo> page = new Page<ViewFxryVacateinfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					vacteCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(viewFxryVacateinfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJZZYLIST".equals(type)) {//矫正转移
				if (dataQuery != null) {
					Page<FXRYTransferInfo> page = new Page<FXRYTransferInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					transferCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(fxryTransferInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJZTGLIST".equals(type)) {//矫正脱管
				if (dataQuery != null) {
					Page<FXRYOutManageInfo> page = new Page<FXRYOutManageInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					outManageCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(fxryOutManageInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJCJZ".equals(type)) {//解除矫正信息
				if (dataQuery != null) {				
					return getZjryJcjz(dataQuery.getRyID());
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZJRYJCXXLIST".equals(type)) {//奖惩信息
				if (dataQuery != null) {
					Page<FxryReward> page = new Page<FxryReward>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					rewardCriterions(page, dataQuery.getRyID());
					return JSON.toJSONString(fxryRewardService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			}
		}
		
		if (type.startsWith("DBSX") && type.length() > 4) {
			 if ("DBSXTZXXLIST".equals(type)) {//通知信息
				if (dataQuery != null) {
					Page<CcWeeknotice> page = new Page<CcWeeknotice>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					String oper = dataQuery.getCommonValue();
					List<CcWeeknotice> list = new ArrayList<CcWeeknotice>();
					if (oper != null && ("tzView").equals(oper)) {
						list = findPaged(page, dataQuery.getOrgID(), dataQuery.getCommonValue(), "").getResult();
					} else {//办公管理中返回查看和拟制的通知信息
						List<CcWeeknotice> list1 = findPaged(page, dataQuery.getOrgID(), "tzView", "").getResult();
						List<CcWeeknotice> list2 = findPaged(page, dataQuery.getOrgID(), dataQuery.getCommonValue(), "").getResult();
						list.addAll(list1);
						list.addAll(list2);
					}
					
					List<CcWeeknotice> endList = new ArrayList<CcWeeknotice>();
					String result = "";
					try {
						result = JSON.toJSONString(list);
					} catch (Exception e) {//一对多关联在JSON转换时脏数据出现org.hibernate.ObjectNotFoundException: No row with the given identifier exists错误，终端暂时不用直接设置空
						for (int i = 0; i < list.size(); i++) {
							CcWeeknotice ccWeeknotice = list.get(i);
							Set<CcNoticereceive> ccNoticereceive = ccWeeknotice.getCcNoticereceives();
							Set<CcNoticereceive> ccNoticereceives = new HashSet<CcNoticereceive>(0);
							for (Iterator<CcNoticereceive> iterator = ccNoticereceive.iterator(); iterator.hasNext();) {
								CcNoticereceive ccNoticereceive2 = (CcNoticereceive) iterator.next();
								ccNoticereceive2.setReceiver(null);
								ccNoticereceives.add(ccNoticereceive2);
							}
							ccWeeknotice.setCcNoticereceives(ccNoticereceives);
							endList.add(ccWeeknotice);
						}
						result = JSON.toJSONString(endList);
					}
					return result;
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXDTXXLIST".equals(type)) {//动态信息
				if (dataQuery != null) {
					Page<CcDynamicreport> page = new Page<CcDynamicreport>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					dynamicCriterions(page, dataQuery.getOrgID(), dataQuery.getCommonValue(), "");
					return JSON.toJSONString(dynamicreportDao.findByCriteria(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXWJSLIST".equals(type)) {//人员未接收列表
				if (dataQuery != null) {
					Page<ViewFxryNotice> page = new Page<ViewFxryNotice>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					List<SearchRule> result = getWjsSearchRuleResult(dataQuery.getOrgID());
					return JSON.toJSONString(viewFxryNoticeService.findPaged(page, result).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXYJSLIST".equals(type)) {//人员已接收列表
				if (dataQuery != null) {
					Page<FxryNotice> page = new Page<FxryNotice>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					List<SearchRule> result = getYjsSearchRuleResult(dataQuery.getOrgID());
					return JSON.toJSONString(fxryNoticeService.findPaged(page, result).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXDZRLIST".equals(type)) {//待转入人员列表
				if (dataQuery != null) {
					Page<ViewFxryTransferInfo> page = new Page<ViewFxryTransferInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					dzrCriterions(page, dataQuery.getOrgID(), "untreat");
					return JSON.toJSONString(viewFXRYTransferInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXJWBJLIST".equals(type)) {//暂监外病检列表
				if (dataQuery != null) {
					Page<ViewIllExamination> page = new Page<ViewIllExamination>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					zjwbjCriterions(page, dataQuery.getOrgID(), dataQuery.getOrgType());
					return JSON.toJSONString(illExaminationService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXYJJTXLIST".equals(type)) {//预解矫提醒列表
				if (dataQuery != null) {
					Page<ViewFxryReadyRelease> page = new Page<ViewFxryReadyRelease>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					yjjtxCriterions(page, dataQuery.getOrgID(), dataQuery.getOrgType());
					return JSON.toJSONString(viewFxryReadyReleaseInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXDHBDLIST".equals(type)) {//电话报告列表
				if (dataQuery != null) {
					Page<ViewPhoneReport> page = new Page<ViewPhoneReport>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					dhbdCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(phoneReportService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXDYBDLIST".equals(type)) {//当月报告列表
				if (dataQuery != null) {
					Page<ViewCCFxryReportinfo> page = new Page<ViewCCFxryReportinfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					reportCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(viewCCFxryReportInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXJYTXLIST".equals(type)) {//教育提醒列表
				if (dataQuery != null) {
					Page<ViewEducation> page = new Page<ViewEducation>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					jytxCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(educationService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXZFTXLIST".equals(type)) {//走访提醒列表
				if (dataQuery != null) {
					Page<ViewInterview> page = new Page<ViewInterview>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					zftxCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(interviewService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXSQFWLIST".equals(type)) {//社区服务列表
				if (dataQuery != null) {
					Page<ViewPublicWork> page = new Page<ViewPublicWork>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					sqfwCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(publicWorkService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXRYJSLIST".equals(type)) {//人员接收列表
				if (dataQuery != null) {
					Page<ViewFXRYExecuteInfo> page = new Page<ViewFXRYExecuteInfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					orgCriterions(page, "3", dataQuery.getOrgID(), "");
					ryjsCriterions(page);
					return JSON.toJSONString(viewFXRYExecuteInfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXXJTXLIST".equals(type)) {// 销假提醒列表
				if (dataQuery != null) {
					Page<ViewFxryVacateinfo> page = new Page<ViewFxryVacateinfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					xjtxCriterions(page, dataQuery.getOrgID(), dataQuery.getOrgType());
					return JSON.toJSONString(viewFxryVacateinfoService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXJZCSJYLIST".equals(type)) {// 集中初始教育列表
				if (dataQuery != null) {
					Page<ViewFxryEducationJzcsjy> page = new Page<ViewFxryEducationJzcsjy>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					jzcsjyCriterions(page, dataQuery.getOrgID(), dataQuery.getOrgType());
					return JSON.toJSONString(viewFxryEducationJzcsjyService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXJZJJQJYLIST".equals(type)) {// 集中解矫前教育列表
				if (dataQuery != null) {
					Page<ViewFxryEducationJzjjqjy> page = new Page<ViewFxryEducationJzjjqjy>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					jzjjqjyCriterions(page, dataQuery.getOrgID(), dataQuery.getOrgType());
					return JSON.toJSONString(viewFxryEducationJzjjqjyService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXBJXXLIST".equals(type)) {// 历史报警信息列表
				if (dataQuery != null) {
					Page<ViewAlarminfo> page = new Page<ViewAlarminfo>(20);    		  
					page.setPageNo(pageNo);
					page.setPageSize(pageSize);
					bjxxCriterions(page, dataQuery.getOrgID());
					return JSON.toJSONString(alarmInfoManagerService.findPaged(page).getResult());
				} else {
					throw new PacketMessageException();
				}
			}
		}
		
		if (type.startsWith("ZFDC")) {
		   if ("ZFDCXX".equals(type)) {// 执法督察信息
				if (dataQuery != null) {
					Map<String, List<SupervisionModule>> supervisionModules = getZfdcVisibleModules(dataQuery.getUserName());
					return JSON.toJSONString(supervisionModules);
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZFDCLXTJXX".equals(type)) {// 执法督察按类型统计的下级信息，返回该类型下的各单位统计数据
				if (dataQuery != null) {
					List<ReportModule> reportModules = getZfdcLXReportData(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(reportModules);
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZFDCBMTJXX".equals(type)) {// 执法督察按机构统计的下级信息，返回该单位下的各类型统计数据
				if (dataQuery != null) {
					List<ReportModule> reportModules = getZfdcBMReportData(dataQuery.getOrgID());
					return JSON.toJSONString(reportModules);
				} else {
					throw new PacketMessageException();
				}
			} else if ("ZFDCXXJZRYLIST".equals(type)) {// 执法督察信息列表，按类型、单位显示人员列表
				if (dataQuery != null) {
					String result = getZfdcxxJzryList(pageNo, pageSize, dataQuery.getOrgType(), dataQuery.getOrgID(), dataQuery.getCommonValue());
					return result;
				} else {
					throw new PacketMessageException();
				}
			}
		}

		if (type.startsWith("DBSXCZ")) {//待办事项操作
			if ("DBSXCZTZXX".equals(type)) {//通知信息
				if (dataQuery != null) {
					String status = saveNoticeStatus(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if("DBSXCZDTXX".equals(type)){//动态信息
				if (dataQuery != null) {
					String status = saveDynamicStatus(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if("DBSXCZDZRRY".equals(type)){//待转入人员
				if (dataQuery != null) {
					String status = qxjMoveIn(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if("DBSXCZZJWBJ".equals(type)){//暂监外病检
				if (dataQuery != null) {
					String status = saveIllMissionInfo(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if("DBSXCZBJXXCL".equals(type)){//报警信息处理
				if (dataQuery != null) {
					String status = dealAlarms(dataQuery.getCommonValue(), dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if("DBSXCZXJTXCL".equals(type)){//销假提醒处理
				if (dataQuery != null) {
					String status = saveVacate(dataQuery.getCommonValue());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZDHBG".equals(type)) {// houxj 电话报告
				if (dataQuery != null) {
					String status = dhbg(dataQuery.getCommonValue(),
							dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZDYBG".equals(type)) {// houxj 当月报告
				if (dataQuery != null) {
					String status = dybg(dataQuery.getCommonValue());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZJYTX".equals(type)) {// houxj 教育提醒
				if (dataQuery != null) {
					String status = jytx(dataQuery.getCommonValue(),
							dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZZFTX".equals(type)) {// houxj 走访提醒
				if (dataQuery != null) {
					String status = zftx(dataQuery.getCommonValue(),
							dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZSQFW".equals(type)) {// houxj 社区服务
				if (dataQuery != null) {
					String status = sqfw(dataQuery.getCommonValue(),
							dataQuery.getUserName());
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZCSJY".equals(type)) {// 初始教育：类型为1
				if (dataQuery != null) {
					String status = saveEducationInfo(dataQuery.getRyID(), dataQuery.getStartTime(), dataQuery.getEndTime(), dataQuery.getCommonValue(), dataQuery.getUserName(), "1");
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			} else if ("DBSXCZJJQJY".equals(type)) {// 解矫前教育：类型为3
				if (dataQuery != null) {
					String status = saveEducationInfo(dataQuery.getRyID(), dataQuery.getStartTime(), dataQuery.getEndTime(), dataQuery.getCommonValue(), dataQuery.getUserName(), "3");
					return JSON.toJSONString(status);
				} else {
					throw new PacketMessageException();
				}
			}
		}
		
		if ("ZFZDXGMM".equals(type)) {//修改账号密码
			if (dataQuery != null) {
				String status = updateUserPassWord(dataQuery.getCommonValue(), dataQuery.getUserName());
				return JSON.toJSONString(status);
			} else {
				throw new PacketMessageException();
			}
		}
		
		if ("ZFZDDWSBBH".equals(type)) {//获取设备编号 deviceNumber，报到登记时使用(旧版本在superviseDeviceService，新版本从dzjgsbDeviceService获取)
			if (dataQuery != null) {
				List<Map<String, Object>> deviceList = dzjgsbDeviceService.queryEquipableMachine(dataQuery.getOrgID());
				return JSON.toJSONString(deviceList);
			} else {
				throw new PacketMessageException();
			}
		}
		
		return null;
	}
	
	/**
	 * Description:获取执法督察信息的矫正人员列表
	 * @param pageSize 
	 * @param pageNo 
	 * 
	 * @param orgType 当前单位类型：1市局；2区县局；3所
	 * @param orgID	要查询的单位id
	 * @param commonValue 
	 * 		要查询的类型：
	 * 			WJS:人员报到
	 * 			PR:电话报告
	 * 			PW:社区服务
	 * 			MR:当月报告
	 * 			UM:脱管人员
	 *			XJ:销假逾期
	 * 			JJ:解除矫正
	 * 			FE:集中初始教育
	 * 			CRIME:涉嫌再犯罪
	 * 			ILL:暂监外病检
	 * @return
	 * 		人员信息JSON
	 * 
	 */
	@SuppressWarnings("unchecked")
	private String getZfdcxxJzryList(int pageNo, int pageSize, String orgType, String orgID, String commonValue) {
		String sfsId = "";
		String qxsfjId = "";
		if (CommonUtils.isNull(orgType) || CommonUtils.isNull(orgID)) {
			return null;
		}
		if (!CommonUtils.isNull(orgType)) {
			if ("1".equals(orgType)) {
				qxsfjId = orgID;
			} else if ("2".equals(orgType)) {
				sfsId = orgID;
			}
		}
		if (!CommonUtils.isNull(commonValue)) {
			if ("WJS".equals(commonValue)) {
				Page<ViewFxryNotice> page = new Page<ViewFxryNotice>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				page.getCriterions().add(Restrictions.lt("reportTime", new Date()));
				page.getCriterions().add(Restrictions.eq("state", FXRYState.ZJ));
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<ViewFxryNotice> list = viewFxryNoticeService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("PR".equals(commonValue)) {
				Page<SupervisionPhoneReport> page = new Page<SupervisionPhoneReport>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				page.getCriterions().add(
						Restrictions.lt("createTime", DateUtils.getMonday(new Date())));
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionPhoneReport> list = supervisonPhoneReportService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("PW".equals(commonValue)) {
				Page<SupervisionPublicWork> page = new Page<SupervisionPublicWork>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				page.getCriterions().add(
						Restrictions.lt("createTime",
								DateUtils.getFirstDayForMonth(new Date())));
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionPublicWork> list = supervisonPublicWorkService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("MR".equals(commonValue)) {
				Page<SupervisionMonthReport> page = new Page<SupervisionMonthReport>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				page.getCriterions().add(
						Restrictions.lt("createTime",
								DateUtils.getFirstDayForMonth(new Date())));
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionMonthReport> list = supervisonMonthReportService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("UM".equals(commonValue)) {
				Page<ViewFxryUnmanageHistory> page = new Page<ViewFxryUnmanageHistory>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				page.getCriterions().add(Restrictions.eq("state", FXRYState.TG));
				page.getCriterions().add(Restrictions.isNull("endDate"));
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrgId", qxsfjId)));
				}
				List<ViewFxryUnmanageHistory> list = viewFxryUnmanageHistoryService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("XJ".equals(commonValue)) {
				Page<SupervisionVacate> page = new Page<SupervisionVacate>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionVacate> list = supervisonVacateService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("JJ".equals(commonValue)) {
				Page<SupervisionRelease> page = new Page<SupervisionRelease>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionRelease> list = supervisionReleaseService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("FE".equals(commonValue)) {
				Page<SupervisionFirstEducation> page = new Page<SupervisionFirstEducation>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionFirstEducation> list = supervisionFirstEducationService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("CRIME".equals(commonValue)) {
				Page<SupervisionCrime> page = new Page<SupervisionCrime>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionCrime> list = supervisionCrimeService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			} else if ("ILL".equals(commonValue)) {
				Page<SupervisionIllExamination> page = new Page<SupervisionIllExamination>(20);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				if(page.getCriterions() == null){
					List<Object> c = new ArrayList<Object>();
					page.setCriterions(c);
				}
				if (!CommonUtils.isNull(sfsId)) {
					page.getCriterions().add(Restrictions.eq("responseOrg", sfsId));
				} else if (!CommonUtils.isNull(qxsfjId)) {
					page.getCriterions().add(
							Restrictions.or(Restrictions.eq("responseOrg", qxsfjId),
									Restrictions.eq("supOrg", qxsfjId)));
				}
				List<SupervisionIllExamination> list = supervisionIllExaminationService.findPaged(page).getResult();
				return JSON.toJSONString(list);
			}
		}
		return null;
	}

	/**
	 * 
	 * Description:集中教育保存
	 * 
	 * @param ids 集中教育人员id
	 * @param time 集中教育时间
	 * @param sfcy 是否参与
	 * @param bz 备注
	 * @param userName 登录用户名
	 * @param type 集中教育类型：1为初始教育；3为解矫前教育
	 * @return
	 * 		success or fail
	 */
	private String saveEducationInfo(String ids, String time, String sfcy,
			String bz, String userName, String type) {
		try {
			User user = getUserInfo(userName);
			if (!CommonUtils.isNull(ids)) {
				String[] fxryIds = ids.split(",");
				Date now = new Date();
				for (String fxryId : fxryIds) {
					EducationInfo temp= new EducationInfo();
					temp.setTime(CommonUtils.StringToShortDate(time));
					temp.setType(type);
					temp.setSfcy(sfcy);
					temp.setBz(bz);
					temp.setCreater(user.getId());
					temp.setCreatedate(now);
					temp.setOrgId(user.getWunit().getBm());
					temp.setFxryId(fxryId);
					educationInfoService.create(temp);
				}
				return "success";
			} else {
				return "fail:id is null";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Description:修改密码
	 * 
	 * @param passWord 新密码
	 * @param userName 用户名
	 * @return
	 *  	success or fail
	 */
	private String updateUserPassWord(String passWord, String userName) {
		try {
			User user = getUserInfo(userName);
			if (!CommonUtils.isNull(passWord)) {
				if (!passWord.equals(user.getPassWord())) {
					user.setPassWord(passWord);
					userService.update(user);
					return "success";
				} else {
					return "fail:the passWord is same as old...";
				}
			} else {
				return "fail:passWord is null!";
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	// houxj 社区服务
	private String sqfw(String ids, String userName) {
		String str = "success";
		try {
			String fxryIds = ids;
			if (!CommonUtils.isNull(fxryIds) && !CommonUtils.isNull(userName)) {
				User user = getUserInfo(userName);
				missionInfoService.createPublicWork(fxryIds.split(","), user);
				str = "success";
			} else {
				System.out.println("终端--走访提醒--id,或者username为null");
				str = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "fail";
		}
		return str;
	}
	
	// houxj 电话报告
	private String dhbg(String ids, String userName) {
		String str = "success";
		try {
			String fxryIds = ids;
			if (!CommonUtils.isNull(fxryIds) && !CommonUtils.isNull(userName)) {
				User user = getUserInfo(userName);
				missionInfoService.createPhoneReport(fxryIds.split(","),user);
				str = "success";
			} else {
				System.out.println("终端--电话报告--id,或者username为null");
				str = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "fail";
		}
		return str;
	}

	// houxj 当月报告
	private String dybg(String ids) {
		String str = "success";
		try {
			String fxryIds = ids;
			SimpleDateFormat sdfTime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String reportDate = sdfTime.format(new Date());
			if (!CommonUtils.isNull(fxryIds)) {
				for (String id : fxryIds.split(",")) {
					addReportInfoService.addReportInfo(id, reportDate, reportDate);
				}
				str = "success";
			} else {
				System.out.println("终端--当月报告--id为null");
				str = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "fail";
		}
		return str;
	}

	// houxj 教育提醒
	private String jytx(String ids, String userName) {
		String str = "success";
		try {
			String fxryIds = ids;
			if (!CommonUtils.isNull(fxryIds) && !CommonUtils.isNull(userName)) {
				User user = getUserInfo(userName);
				missionInfoService.createEducation(fxryIds.split(","),user);
				str = "success";
			} else {
				System.out.println("终端--教育提醒--id,或者username为null");
				str = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "fail";
		}
		return str;
	}

	// houxj 走访提醒
	private String zftx(String ids, String userName) {
		String str = "success";
		try {
			String fxryIds = ids;
			if (!CommonUtils.isNull(fxryIds) && !CommonUtils.isNull(userName)) {
				User user = getUserInfo(userName);
				missionInfoService.createInterview(fxryIds.split(","),user);
				str = "success";
			} else {
				System.out.println("终端--走访提醒--id,或者username为null");
				str = "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "fail";
		}
		return str;
	}
	
	/**
	 * Description:销假处理
	 * 
	 * @param ids 销假信息id
	 * @return
	 * 		success or fail
	 */
	private String saveVacate(String ids) {
		try {
			String[] idArr = ids.split(",");
			for(String id : idArr){
				CcVacateInfo vacate = vacateService.get(id);
				vacate.setReportDate(new Date());
				//vacate.setBz(bz);
				//vacate.setCreaterId(userId);
				vacate.setCreateDate(new Date());
				vacateService.create(vacate);
			}
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Description:报警信息处理
	 * 
	 * @param ids 报警信息id
	 * @param userName 登录用户名
	 * @return
	 * 		success or fail
	 */
	private String dealAlarms(String ids, String userName) {
		try {
			User user = getUserInfo(userName);
			if (ids.contains(",")) {//批量处理，进行了循环操作，原始service中的in查询出错
				String[] idsArr = ids.split(",");
				for (String id:idsArr) {
					CcAlarmInfo ccAlarmInfo = alarmService.get(id);
					//ccAlarmInfo.setRecord(jlnr);
					ccAlarmInfo.setStatus("1");
					ccAlarmInfo.setHandler(user.getName());
					ccAlarmInfo.setHandleTime(new Date());
					alarmService.update(ccAlarmInfo);
				}
			}
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Description:区县司法局暂监外病检
	 * 
	 * @param fxryId 服刑人员id
	 * @param userName 登录用户名
	 * @return
	 * 		success or fail
	 */
	private String saveIllMissionInfo(String fxryId, String userName) {
		try {
			User user = getUserInfo(userName);
			missionInfoService.createIllExamination(fxryId.split(","), user);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * Description:区县司法局待转入人员签收
	 * @param fxryId 服刑人员id
	 * @param userName 登录用户名
	 * @return
	 * 		success or fail
	 */
	private String qxjMoveIn(String fxryId, String userName) {
		Bmb bmb = getCurOrganizationInfoModel(userName);
		try {
			if (bmb.isQxsfj()) {
				fxryTransferInfoService.sfjMoveIn(fxryId, bmb.getBm());
			} else if (bmb.isSfs()) {//司法所间转移人员时转移其设备记录
				fxryTransferInfoService.sfsMoveIn(fxryId, bmb.getBm());
				fxryBaseInfoService.changeEquip(fxryId, bmb.getBm());
			}			
			return "success";
		} catch (FXRYStateChangeException e) {
			return "fail";
		}
	}

	/**
	 * 
	 * Description:动态信息签收保存操作，状态为3为未签收
	 * 
	 * @param id 动态信息的id
	 * @param userName 登录用户名
	 * @return
	 * 		success or fail
	 */
	private String saveDynamicStatus(String id, String userName) {
		CcDynamicreport ccDynamicreport = dynamicreportService.get(id);
		User user = getUserInfo(userName);
		try {
			if("3".equals(ccDynamicreport.getStatus())){
				CcDynamicreportreply reply=null;
				if(null==ccDynamicreport.getReply()){
					reply=new CcDynamicreportreply();
					reply.setId(CommonUtils.uuid());
					ccDynamicreport.setReply(reply);
				}else{
					reply=ccDynamicreport.getReply();
				}
				Date date = new Date();
				reply.setReplydate(date);
				reply.setReplyPersonId(user.getId());
				reply.setReplyPersonName(user.getName());
				reply.setReport(ccDynamicreport);
				ccDynamicreport.setStatus("1");
				dynamicreportreplyService.create(reply);
				dynamicreportService.create(ccDynamicreport);
			}
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * 
	 * Description:通知信息查看保存操作，状态为2为未查看
	 * 
	 * @param id 通知信息的id
	 * @param userName 登录用户名称
	 * @return
	 * 		success or fail
	 */
	private String saveNoticeStatus(String id, String userName) {
		CcWeeknotice ccWeeknotice = weekNoticeService.get(id);
		User user = getUserInfo(userName);
		String orgID = user.getWunit().getBm();
		Set<CcNoticereceive> notice = ccWeeknotice.getCcNoticereceives();
		String tempId = "";
		String status = "";
		try {
			for (CcNoticereceive temp : notice) {
				tempId = temp.getOrgId();
				status = temp.getStatus();

				// 通知查看进入，将机构通知状态改为签收
				if (orgID.equals(tempId)
						&& (status != null && ("2").equals(status))) {
					temp.setReceivetime(new Date());
					temp.setStatus("1");// 1：已签收；2：待签收
					temp.setReceiver(user);
					temp.setReceivername(user.getName());
					noticeReceiveServie.create(temp);
				}
			}
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	/**
	 * 
	 * Description:查询执法督察按类型统计的下级信息，返回该类型下的各单位统计数据
	 * 
	 * @param id ReportModule对象标识，执法督察一级查询left的url属性中包含该信息
	 * @param userName 终端用户
	 * @return
	 * 		ReportModule list
	 */
	private List<ReportModule> getZfdcLXReportData(String id, String userName) {
		Bmb bmb = getCurOrganizationInfoModel(userName);
		List<ReportModule> reportModules = new ArrayList<ReportModule>();
		try {
			if (!CommonUtils.isNull(id)) {
				ReportModule all = reportService.get(id);
				if(null!=all){
					orgReportData(all, bmb);
					reportModules.add(all);
				}
			}
		} catch (Exception e) {
			//logger.error("获取报表数据出错", e);
		}
		return reportModules;
	}
	
	/**
	 * 
	 * Description:按原业务逻辑组织需要展示的数据
	 * 
	 * @param module ReportModule对象
	 * @param bmb 部门
	 * 
	 */
	private void orgReportData(ReportModule module, Bmb bmb) {
		if (!CommonUtils.isNull(module.getService())) {
			try {
				IReportServiceNew service = (IReportServiceNew) SpringContextUtil
						.getBean(module.getService() + "ZFDC");
				ReportData data = service.getReportData(bmb);
				module.setListData(data.getListData());
				module.setCategory(data.getCategory());
				module.setDataset(data.getDataset());
				module.setDetail(data.getDetail());
			} catch (Exception e) {
				//e.getMessage();
			}
		}
	}

	/**
	 * 
	 * Description:查询执法督察按机构统计的下级信息，返回该单位下的各类型统计数据
	 * 
	 * @param orgId 单位id
	 * @return
	 * 		ReportModule list
	 */
	private List<ReportModule> getZfdcBMReportData(String orgId) {
		List<ReportModule> reportModules = new ArrayList<ReportModule>();
		if (!CommonUtils.isNull(orgId)) {
			Bmb bmb = bmbService.get(orgId);
			if (bmb != null && !bmb.isSj()) {
				ReportModule module = new ReportModule();
				module.setName(bmb.getMc() + "督察项");
				module.setType("1");
				String detail = bmb.getMc() + "督察项分类统计" + ":";
				long count = 0;
				List<ReportList> reportLists = new ArrayList<ReportList>();
				List<SupervisionModule> todoModules = getModules();
				for (SupervisionModule todoModule : todoModules) {
					if (!CommonUtils.isNull(todoModule.getService())) {
						try {
							ISupervisionServiceNew service = (ISupervisionServiceNew) SpringContextUtil
									.getBean(todoModule.getService() + "ZFDC");
							SupervisionCount supervisionCount = service
									.getSupervisionCount(bmb);
							count = count + supervisionCount.getTotal();
							ReportList reportList = new ReportList();
							reportList.setName(todoModule.getReportName());
							if (!CommonUtils.isNull(todoModule.getReportUrl())) {
								String url = todoModule.getReportUrl();
								if (url.contains("?")) {
									url = url + "&orgId=" + bmb.getBm();
								} else {
									url = url + "?orgId=" + bmb.getBm();
								}
								reportList.setLink(url);
							}
							reportList.setValue(Long.toString(supervisionCount
									.getTotal()));
							reportLists.add(reportList);
						} catch (Exception e) {
							//logger.warn("获取待办数量错误:", e);
						}
					}
				}
				detail = detail + count + "项";
				module.setDetail(detail);
				module.setListData(reportLists);
				reportModules.add(module);
			}
		}
		return reportModules;
	}

	/**
	 * 
	 * Description:查询执法督察统计信息
	 * 
	 * @param userName 用户名
	 * @return 
	 * 		map: left督察事项，以类型纬度统计；right督察项，以单位纬度统计
	 */
	private Map<String, List<SupervisionModule>> getZfdcVisibleModules(String userName) {
		Bmb bmb = getCurOrganizationInfoModel(userName);
		List<SupervisionModule> mine = new ArrayList<SupervisionModule>();
		List<SupervisionModule> orgModules = getOrganizationModule(bmb);
		if (!bmb.isSfs()) {
			mine = getModules();
			Iterator<SupervisionModule> iter = mine.iterator();
			while (iter.hasNext()) {
				SupervisionModule module = iter.next();
				if (!CommonUtils.isNull(module.getService())) {
					try {
						ISupervisionServiceNew service = (ISupervisionServiceNew) SpringContextUtil
								.getBean(module.getService() + "ZFDC");
						SupervisionCount count = service.getSupervisionCountNew(bmb);//对应系统中session中获取部门的方法
						Map<String, Integer> supervisons = count.getSupervisions();
						module.setTotal(count.getTotal());
						for (SupervisionModule orgModule : orgModules) {
							if (supervisons.containsKey(orgModule.getId())) {
								orgModule.setTotal(orgModule.getTotal() + supervisons.get(orgModule.getId()));
							}
						}
					} catch (Exception e) {
						//logger.warn("获取待办数量错误:", e);
					}
				}
			}
		}
		Map<String, List<SupervisionModule>> map = new HashMap<String, List<SupervisionModule>>();
		map.put("left", mine);
		map.put("right", orgModules);
		return map;
	}
	
	/**
	 * 
	 * Description:查询执法督察项
	 * @return
	 */
	private List<SupervisionModule> getModules() {
		List<SupervisionModule> all = supervisionModuleService.findByCriteria(
				Restrictions.eq("type", "supervision"),
				Restrictions.eq("visible", "1"));
		List<SupervisionModule> visibleModules = new ArrayList<SupervisionModule>();
		for (SupervisionModule module : all) {
			visibleModules.add(module);
		}
		Collections.sort(visibleModules, getModuleComparator());
		return visibleModules;
	}
	
	/**
	 * 
	 * Description:排序
	 * 
	 * @return
	 */
	private Comparator<SupervisionModule> getModuleComparator() {
		return new Comparator<SupervisionModule>() {
			public int compare(SupervisionModule left, SupervisionModule right) {
				if (left.getSort() == null || right.getSort() == null) {
					return 0;
				}
				return left.getSort().compareTo(right.getSort());
			}
		};
	}
	
	/**
	 * 
	 * Description:按部门获取执法督察统计信息
	 * 
	 * @param bmb 部门
	 * @return SupervisionModule list
	 * 		
	 * 
	 */
	private List<SupervisionModule> getOrganizationModule(Bmb bmb) {
		List<OrganizationInfo> orgs = new ArrayList<OrganizationInfo>();
		if (bmb.isSj()) {
			orgs = organizationInfoService.findByCriteria(Restrictions.eq(
					"orgType", "2"));
		}
		if (bmb.isQxsfj()) {
			orgs = organizationInfoService.findByCriteria(
					Restrictions.eq("supOrg.orgId", bmb.getBm()),
					Restrictions.eq("orgType", "3"));
		}
		Collections.sort(orgs, new Comparator<OrganizationInfo>() {
			public int compare(OrganizationInfo left, OrganizationInfo right) {
				if (left.getOrgId() == null || right.getOrgId() == null) {
					return 0;
				}
				return left.getOrgId().compareTo(right.getOrgId());
			}
		});
		SupervisionModule baseModule = getBaseModule();
		List<SupervisionModule> orgModules = new ArrayList<SupervisionModule>();
		for (int i = 0; i < orgs.size(); i++) {
			SupervisionModule orgModule = new SupervisionModule();
			orgModule.setId(orgs.get(i).getOrgId());
			orgModule.setName(orgs.get(i).getCname());
			orgModule.setIcon(baseModule.getIcon());
			orgModule.setUrl(baseModule.getUrl() + "?orgId="
					+ orgs.get(i).getOrgId());
			orgModule.setSort(i);
			orgModules.add(orgModule);
		}
		return orgModules;
	}
	
	/**
	 * Description：按业务系统逻辑设置执法督察数据项的ICON及URL，目前终端用户未使用
	 * @return
	 */
	private SupervisionModule getBaseModule() {
		List<SupervisionModule> baseModules = supervisionModuleService
				.findByCriteria(Restrictions.eq("type", "supervisionOrg"));
		SupervisionModule baseModule = null;
		if (baseModules.size() > 0) {
			baseModule = baseModules.get(0);
		} else {
			baseModule = new SupervisionModule();
			baseModule.setIcon("/images/index/zanjw_xb.jpg");
			baseModule.setUrl("/data/jzgl/dagl/mission/dhbd.jsp");
		}
		return baseModule;
	}

	/**
	 * 
	 * Description:按业务系统逻辑查询电子监管历史轨迹信息
	 * 如果传入时间为空，后台默认显示7天的数据
	 * 
	 * warn:2016-9-20调整为查询CcLocationInfoBuffer表，该表只保留最近三天的有效数据
	 *  
	 * @param ryID 人员标识
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return  轨迹list
	 */
	private List<CcLocationInfoBuffer> getLocationHistory(String ryID, String startTime, String endTime) {
		//List<Object> datas = new ArrayList<Object>();
		List<CcLocationInfoBuffer> list = new ArrayList<CcLocationInfoBuffer>();
		try {
			if(startTime!=null&&endTime!=null){
				list = locationBufferService.findHistory(ryID,startTime,endTime);
			}else{
				list = locationBufferService.findHistory(ryID);
			}
			/*StringBuilder history = new StringBuilder();
			StringBuilder time= new StringBuilder();
			StringBuilder locType= new StringBuilder();
			
			for (CcLocationInfo c : list) {
				history.append(c.getLongitude()).append(" ").append(c.getLatitude()).append(",");
				time.append(c.getLocTime()).append(";").append(c.getAlarmType()).append(",");
				locType.append(c.getLocType()).append(",");
			}
			if (history.length() > 0) {
				String his = history.toString();
				his = his.substring(0, his.length() - 1);
				String t = time.toString();
				t = t.substring(0, t.length() - 1);
				datas.add(his);
				datas.add(list.get(0).getLongitude());
				datas.add(list.get(0).getLatitude());
				datas.add(list.get(list.size()-1).getLongitude());
				datas.add(list.get(list.size()-1).getLatitude());
				datas.add(t);
				datas.add(list.get(0).getLocTime());
				datas.add(list.get(0).getAlarmType());
				datas.add(list.get(list.size()-1).getLocTime());
				datas.add(list.get(list.size()-1).getAlarmType());
				datas.add(locType);
			}*/
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	/**
	 * 
	 * Description:设置报警信息条件
	 * 
	 * @param page 分页对象
	 * @param orgID	机构标识
	 */
	@SuppressWarnings("unchecked")
	private void bjxxCriterions(Page<ViewAlarminfo> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("executeUnit", orgID));
		page.getCriterions().add(Restrictions.eq("status", "2"));
	}

	/**
	 * 
	 * Description:设置集中解矫前教育条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 * @param orgType 单位级别
	 */
	@SuppressWarnings("unchecked")
	private void jzjjqjyCriterions(Page<ViewFxryEducationJzjjqjy> page, String orgID, String orgType) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("2".equals(orgType)){
			page.getCriterions().add(Restrictions.eq("supOrgId", orgID));
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			page.getCriterions().add(Restrictions.eq("responseOrg", orgID));
		}
	}

	/**
	 * 
	 * Description:设置集中初始教育条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 * @param orgType 单位级别
	 */
	@SuppressWarnings("unchecked")
	private void jzcsjyCriterions(Page<ViewFxryEducationJzcsjy> page, String orgID, String orgType) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("2".equals(orgType)){
			page.getCriterions().add(Restrictions.eq("supOrgId", orgID));
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			page.getCriterions().add(Restrictions.eq("responseOrg", orgID));
		}
	}

	/**
	 * 
	 * Description:设置销假提醒条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 * @param orgType 单位级别，所级单位使用，区县局暂时用不到
	 */
	@SuppressWarnings("unchecked")
	private void xjtxCriterions(Page<ViewFxryVacateinfo> page, String orgID, String orgType) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("2".equals(orgType)){
			OrganizationInfo curOrg = organizationInfoService.get(orgID);
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			page.getCriterions().add(Restrictions.in("recordOrgId", ids));
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			page.getCriterions().add(
					Restrictions.eq("recordOrgId", orgID));
		}
		page.getCriterions().add(Restrictions.isNull("reportDate"));
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		page.getCriterions().add(Restrictions.le("endDate", c.getTime()));
	}

	/**
	 * 
	 * Description:设置人员接收条件
	 * 
	 * @param page 分页对象
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void ryjsCriterions(Page<ViewFXRYExecuteInfo> page) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.isNull("excuteId"));
	}

	/**
	 * 
	 * Description:设置社区服务条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 */
	@SuppressWarnings("unchecked")
	private void sqfwCriterions(Page<ViewPublicWork> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", orgID));
	}

	/**
	 * 
	 * Description:设置走访提醒条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 */
	@SuppressWarnings("unchecked")
	private void zftxCriterions(Page<ViewInterview> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", orgID));
	}

	/**
	 * 
	 * Description:设置教育提醒条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 */
	@SuppressWarnings("unchecked")
	private void jytxCriterions(Page<ViewEducation> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		// 司法所按负责单位查询
		page.getCriterions().add(Restrictions.eq("orgId", orgID));
	}

	/**
	 * 
	 * Description:设置当月报告条件,待办事项中的列表，只获取当月需要报到的人
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 */
	@SuppressWarnings("unchecked")
	private void reportCriterions(Page<ViewCCFxryReportinfo> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		List<Criterion> list = new ArrayList<Criterion>();
		if (!CommonUtils.isNull(orgID)) {
			list.add(Restrictions.eq("orgid", orgID));
		}
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		list.add(Restrictions.or(Restrictions.isNull("reportdate"),
				Restrictions.le("reportdate", c.getTime())));
		page.getCriterions().addAll(list);
	}

	/**
	 * 
	 * Description:设置电话报告条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 */
	@SuppressWarnings("unchecked")
	private void dhbdCriterions(Page<ViewPhoneReport> page, String orgID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("orgId", orgID));
	}

	/**
	 * 
	 * Description:设置预解矫提醒条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 * @param orgType 单位级别
	 */
	@SuppressWarnings("unchecked")
	private void yjjtxCriterions(Page<ViewFxryReadyRelease> page, String orgID, String orgType) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("2".equals(orgType)){
			page.getCriterions().add(Restrictions.eq("supOrgId", orgID));
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			page.getCriterions().add(Restrictions.eq("responseOrg", orgID));
		}
	}

	/**
	 * 
	 * Description:设置暂监外病检条件
	 * 
	 * @param page 分页对象
	 * @param orgID 单位标识
	 * @param orgType 单位级别
	 */
	@SuppressWarnings("unchecked")
	private void zjwbjCriterions(Page<ViewIllExamination> page, String orgID, String orgType) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("2".equals(orgType)){
			page.getCriterions().add(Restrictions.eq("supOrgId", orgID));
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			page.getCriterions().add(Restrictions.eq("orgId", orgID));
		}
	}

	/**
	 * 
	 * Description:设置待转入人员条件
	 * 
	 * @param page 分页信息
	 * @param ryID 人员标识
	 * @param oper 查询类型
	 */
	@SuppressWarnings("unchecked")
	private void dzrCriterions(Page<ViewFxryTransferInfo> page, String orgID, String oper) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if("untreat".equals(oper)){
			//待转入人员必须是未接收，人员状态为ZC,目标机构为当前机构
			page.getCriterions().add(Restrictions.eq("transtatus", 0));
			page.getCriterions().add(Restrictions.eq("receiveOrgId", orgID));
			page.getCriterions().add(Restrictions.eq("state", FXRYState.ZC));
		}
	}

	/**
	 * 
	 * Description:按业务模块原方式设置已接收SearchRule
	 * 
	 * @param orgID 单位标识
	 * @return SearchRule list
	 */
	@SuppressWarnings("unchecked")
	private List<SearchRule> getYjsSearchRuleResult(String orgID) {
		List<SearchRule> result = new ArrayList<SearchRule>();
		//机构信息
		if (orgID != null && !orgID.equals("1")) {
			List<String> orgIds = organizationInfoService.getChildrenIds(orgID);
			String childs = orgIds.toString();
			childs = childs.replace(" ", "");
			childs = childs.replace("[", "");
			childs = childs.replace("]", "");
			SearchRule searchRule = new SearchRule();
			searchRule.setField("orgId");
			searchRule.setData(childs);
			searchRule.setOp("in");
			result.add(searchRule);
		}
		//通知状态
		SearchRule searchRule = new SearchRule();
		searchRule.setField("status");
		searchRule.setData("2");
		searchRule.setOp("eq");
		result.add(searchRule);
		return result;
	}

	/**
	 * 
	 * Description:按业务模块原方式设置未接收SearchRule
	 * 
	 * @param orgID 机构标识
	 * @return SearchRule list
	 */
	@SuppressWarnings("unchecked")
	private List<SearchRule> getWjsSearchRuleResult(String orgID) {
		List<SearchRule> result = new ArrayList<SearchRule>();
		// 添加机构条件
		if (orgID != null && !orgID.equals("1")) {
			List<String> orgIds = organizationInfoService.getChildrenIds(orgID);
			String childs = orgIds.toString();
			childs = childs.replace(" ", "");
			childs = childs.replace("[", "");
			childs = childs.replace("]", "");
			SearchRule searchRule = new SearchRule();
			searchRule.setField("responseOrg");
			searchRule.setData(childs);
			searchRule.setOp("in");
			result.add(searchRule);
		}
		// 添加条件：接收过期三天后
		Calendar cal = Calendar.getInstance();
		Date dealDate = cal.getTime();
		Timestamp tt = new Timestamp(dealDate.getTime());
		SearchRule searchRule = new SearchRule();
		searchRule.setField("reportTime");
		searchRule.setData(tt.toString().substring(0, 10));
		searchRule.setOp("lt");
		result.add(searchRule);
		// 矫正状态
		SearchRule searchRule1 = new SearchRule();
		searchRule1.setField("state");
		searchRule1.setData(FXRYState.ZJ);
		searchRule1.setOp("eq");
		result.add(searchRule1);
		
		return result;
	}

	/**
	 * 
	 * Description:动态信息条件
	 * 
	 * @param page 分页信息
	 * @param orgId 单位标识
	 * @param oper 查看标识
	 * @param status 动态信息状态：1已签收，3已上报，2拟制中
	 * @return 
	 * 		动态信息PAGE条件
	 */
	@SuppressWarnings("unchecked")
	private void dynamicCriterions(Page<CcDynamicreport> page, String orgId, String oper, String status ) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		if(oper!=null && ("dtglView").equals(oper)){//动态信息查看进入
			page.getCriterions().add(Restrictions.eq("receiveOrgId", orgId));
			page.getCriterions().add(Restrictions.in("status", new String[]{"1","3"}));
			if(!CommonUtils.isNull(status)){
				page.getCriterions().add(Restrictions.eq("status", status));
			}
		} else{//办公管理动态信息包括查看和拟制
			page.getCriterions().add(
					Restrictions.or(Restrictions.and(Restrictions.eq("receiveOrgId", orgId), Restrictions.in("status",new String[] { "1", "3" })), 
							Restrictions.eq("recordOrgId", orgId)));
		}
	}

	/**
	 * 
	 * Description:获取通知信息
	 * 
	 * @param page 分页信息
	 * @param orgId 单位标识
	 * @param oper 查看标识
	 * @param status 通知状态：1未读，2已读
	 * @return 通知信息PAGE
	 */
	@SuppressWarnings("unchecked")
	private Page<CcWeeknotice> findPaged(Page<CcWeeknotice> page, String orgId, String oper, String status) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		Criteria criteria = weekNoticeDao.createCriteria(page.getCriterions());
		if (oper != null && ("tzView").equals(oper)) {
			// 通知查看进入
			criteria.createAlias("ccNoticereceives", "ccNoticereceives", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("ccNoticereceives.orgId", orgId));
			if (!CommonUtils.isNull(status)) {
				criteria.add(Restrictions.eq("ccNoticereceives.status", status));
			}
			criteria.add(Restrictions.eq("status", "1"));
		} else {
			// 通知拟制进入
			criteria.add(Restrictions.eq("recordOrgId", orgId));
		}
		return weekNoticeDao.findPageByCriteria(criteria, page);
	}

	/**
	 * Description:奖惩信息条件
	 * @param page
	 * @param ryID
	 */
	@SuppressWarnings("unchecked")
	private void rewardCriterions(Page<FxryReward> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryid", ryID));
	}

	/**
	 * Description:获取解矫信息
	 * @param ryID 人员表示
	 * @return 解矫信息列表
	 */
	private String getZjryJcjz(String ryID) {
		FXRYRemoveAdjust fXRYRemoveAdjust = fxryRemoveAdjustService.getByFXRYId(ryID);
		return JSON.toJSONString(fXRYRemoveAdjust);
	}

	/**
	 * Description：矫正托管条件
	 * @param page 分页对象
	 * @param ryID 人员标识
	 */
	@SuppressWarnings("unchecked")
	private void outManageCriterions(Page<FXRYOutManageInfo> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryId", ryID));
	}

	/**
	 * Description：矫正转移条件
	 * @param page 分页对象
	 * @param ryID 人员标识
	 */
	@SuppressWarnings("unchecked")
	private void transferCriterions(Page<FXRYTransferInfo> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryId", ryID));
	}

	/**
	 * Description：请假记录条件
	 * @param page 分页对象
	 * @param ryID 人员标识
	 */
	@SuppressWarnings("unchecked")
	private void vacteCriterions(Page<ViewFxryVacateinfo> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryid", ryID));
	}

	/**
	 * Description：集中教育条件
	 * @param page 分页对象
	 * @param ryID 人员标识
	 */
	@SuppressWarnings("unchecked")
	private void eduCriterions(Page<EducationInfo> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryId", ryID));
	}

	/**
	 * 
	 * Description：查询条件设置
	 * 
	 * @param page 分页对象
	 * @param ryID 人员标识
	 * @param type 类型：1电话报到；5暂监外病检；4社区服务；2每月走访；3每月教育
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getCriterions(Page<MissionInfo> page, String ryID, String type) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryId", ryID));
		page.getCriterions().add(Restrictions.eq("missionType", type));
	}

	/**
	 * 
	 * Description：当月报到数据条件
	 * 
	 * @param page 分页对象
	 * @param ryID 人员标识
	 */
	@SuppressWarnings("unchecked")
	private void dybdCriterions(Page<ViewFxryReportinfo> page, String ryID) {
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		page.getCriterions().add(Restrictions.eq("fxryid", ryID));
	}

	/**
	 * 
	 * Description:获取矫正小组成员信息列表
	 * 
	 * @param ryID
	 * @return
	 */
	private String getZjryJzcyxx(String ryID) {
		List<FXRYAdjustGroup> list = fxryAdjustGroupService.getByFXRYId(ryID);
		return JSON.toJSONString(list);
	}

	/**
	 * 
	 * Description:获取人员刑罚执行信息
	 * 
	 * @param ryID
	 * @return
	 */
	private String getZjryXfzx(String ryID) {
		FXRYExecuteInfo fXRYExecuteInfo = fxryExecuteInfoService.getByFXRYId(ryID);
		return JSON.toJSONString(fXRYExecuteInfo);
	}

	/**
	 * 
	 * Description:获取人员法律文书信息
	 * 
	 * @param ryId
	 * @return
	 */
	private String getZjryFlws(String ryID) {
		LegalInstrument legalInstrument = legalInstrumentService.getByFXRYId(ryID);
		return JSON.toJSONString(legalInstrument);
	}

	/**
	 * 
	 * Description:获取人员基本信息
	 * 
	 * @param ryId
	 * @return
	 */
	private String getZjryJbxx(String ryID) {
		FXRYBaseinfo fxryBaseinfo = fxryBaseInfoService.get(ryID);
		return JSON.toJSONString(fxryBaseinfo);
	}

	/**
	 * 
	 * Description:设置监管人员数据权限
	 * 
	 * @param page 分页对象
	 * @param orgID 机构标识
	 * @param name 姓名
	 */
	@SuppressWarnings("unchecked")
	private void GISCriterions(Page<ViewFxryOnMap> page, String orgID, String name) {
		List<String> orgIds = organizationInfoService.getChildrenIds(orgID);

		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		
		page.getCriterions().add(Restrictions.in("orgId", orgIds));
		page.getCriterions().add(Restrictions.eq("status", "1"));//1绑定设备；0解绑设备
		if (name != null) {
			page.getCriterions().add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
	}

	/**
	 * Description:获取电子监管统计信息
	 * @param orgID 单位标识
	 * @param orgType 单位类型
	 * @return （含下级单位）
	 * 		在矫人员数据量、电子监控人员数据量、未处理报警数据量
	 */
	private String getCountData(String orgID, String orgType) {
		if ("1".equals(orgType)) {
			return JSON.toJSONString(viewSfjTjxxDao.getAll());
		} else if ("2".equals(orgType)) {
			return JSON.toJSONString(viewQxsfjTjxxDao.findByCriteria(Restrictions.eq("supOrg", orgID)));
		}
		return null;
	}

	
	/**
	 * Description:设置在矫人员数据权限
	 * @param page 实体page
	 * @param orgType 单位类型
	 * @param orgID 单位标识
	 * @param name 姓名
	 */
	@SuppressWarnings("unchecked")
	private void orgCriterions(Page<ViewFXRYExecuteInfo> page, String orgType, String orgID, String name) {
		Criterion crit = null;
		if ("2".equals(orgType)) {
			OrganizationInfo curOrg = organizationInfoService.get(orgID);
			Set<OrganizationInfo> suborgs = curOrg.getSubOrg();
			List<String> ids = new ArrayList<String>();
			for (OrganizationInfo item : suborgs) {
				ids.add(item.getOrgId());
			}
			ids.add(curOrg.getOrgId());
			crit = Restrictions.in("responseOrg", ids);
		} else if ("3".equals(orgType)) {
			// 司法所按负责单位查询
			crit = Restrictions.eq("responseOrg", orgID);
		}
		
		if(page.getCriterions() == null){
			List<Object> c = new ArrayList<Object>();
			page.setCriterions(c);
		}
		
		if (crit != null) {
			page.getCriterions().add(crit);
		}
		if (name != null) {
			page.getCriterions().add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		}
		page.getCriterions().add(Restrictions.ne("state", FXRYState.JJ));
		page.getCriterions().add(Restrictions.ne("state", FXRYState.LJ));
		page.getCriterions().add(Restrictions.eq("isTgry",Constants.FS));//终端不显示特管人员
	}
	
	/**
	 * 
	 * Description:find organization by userName
	 * 
	 * @param userName	终端登录用户账户
	 * @return	单位JSON实体
	 */
	@SuppressWarnings("unused")
	private String getOrganizationInfo(String userName) {
		String json = "";
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				if (null != users.get(0).getWunit()) {
					Bmb bmb = users.get(0).getWunit();
					if (bmb.getUnit().equals("0")) {
						bmb = bmbDao.get(bmb.getSupOrg());
					}
					json = JSON.toJSONString(bmb);			
				}
			}
		}
		return json;
	}
	
	/**
	 * 
	 * Description:find current organization by userName
	 * 
	 * @param userName	终端登录用户账户
	 * @return	单位实体
	 */
	private Bmb getCurOrganizationInfoModel(String userName) {
		Bmb bmb = new Bmb();
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				if (null != users.get(0).getWunit()) {
					bmb = users.get(0).getWunit();		
				}
			}
		}
		return bmb;
	}
	
	/**
	 * 
	 * Description:find organization by userName
	 * 
	 * @param userName	终端登录用户账户
	 * @return	单位实体
	 */
	private Bmb getOrganizationInfoModel(String userName) {
		Bmb bmb = new Bmb();
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (CommonUtils.isNotNull(users)) {
				if (null != users.get(0).getWunit()) {
					bmb = users.get(0).getWunit();
					if (bmb.getUnit().equals("0")) {
						bmb = bmbDao.get(bmb.getSupOrg());
					}		
				}
			}
		}
		return bmb;
	}
	
	/**
	 * 获取用户首页可见的待办快捷：区县局和所
	 * 
	 * @param userName 登录用户名称 
	 * 
	 * @return	TodoModule的JSON数据
	 * 
	 */
	private String getVisibleModules(String userName) {
		Bmb bm = getOrganizationInfoModel(userName);
		List<TodoModule> mine = getModules(bm.getOrgType());
		Iterator<TodoModule> iter = mine.iterator();
		while (iter.hasNext()) {
			TodoModule module = iter.next();
			if (!"1".equals(module.getVisible())) {
				iter.remove();
				continue;
			}
			if (!CommonUtils.isNull(module.getService())) {
				ITodoServiceNew service = (ITodoServiceNew) SpringContextUtil
						.getBean(module.getService() + "DBSX");				
				TodoCount count = service.getTodoCount(bm);
				module.setTotal(count.getTotal());
				module.setDeadline(count.getDeadline());
			}
		}
		return JSON.toJSONString(mine);
	}

	/**
	 * Description:按部门获取相应的代办列表并排序
	 * @param orgType
	 * @return List<TodoModule>
	 */
	private List<TodoModule> getModules(String orgType) {
		List<TodoModule> all = todoService.getAllModules();
		List<TodoModule> visibleModules = new ArrayList<TodoModule>();
		for (TodoModule module : all) {
			if (orgType.equals(module.getPermission())) {
				visibleModules.add(module);
			}
		}
		Collections.sort(visibleModules, new Comparator<TodoModule>() {
			public int compare(TodoModule left, TodoModule right) {
				if (left.getSort() == null || right.getSort() == null) {
					return 0;
				}
				return left.getSort().compareTo(right.getSort());
			}
		});
		return visibleModules;
	}
	
	/**
	 * Description:生成人员编号
	 * @param postCode 单位编号
	 * @return	人员编号:单位编码+日期+排序号
	 */
	private String generateCode(String postCode){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date today = new Date();
		String date = sdf.format(today);
		String prefix = postCode + date;
		return fxryBaseInfoService.getCode(prefix);
	}
	
	/**
	 * 
	 * Description:获取用户信息
	 * 
	 * @param userName 用户名称
	 * @return USER单实体串
	 */
	private User getUserInfo(String userName) {
		if (!CommonUtils.isNull(userName)) {
			List<User> users = userDao.findByCriteria(Restrictions.eq(
					"userName", userName));
			if (users.get(0).getIsvalid().equals("9")) {
				return users.get(0);
			}
		}
		return null;
	}
	
}
