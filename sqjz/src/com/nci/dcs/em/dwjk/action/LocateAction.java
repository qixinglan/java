package com.nci.dcs.em.dwjk.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.sql.SQLStringUtils;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;
import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;
import com.nci.dcs.em.dwjk.model.CcFxryCrimeinfo;
import com.nci.dcs.em.dwjk.model.CcLocationInfo;
import com.nci.dcs.em.dwjk.model.CcLocationInfoBuffer;
import com.nci.dcs.em.dwjk.model.ViewFxryOnMap;
import com.nci.dcs.em.dwjk.service.AlarmService;
import com.nci.dcs.em.dwjk.service.FxryBasicinfoService;
import com.nci.dcs.em.dwjk.service.FxryCrimeinfoService;
import com.nci.dcs.em.dwjk.service.LocationBufferService;
import com.nci.dcs.em.dwjk.service.LocationService;
import com.nci.dcs.em.service.FxryDeviceService;
import com.nci.dcs.homepage.report.model.ReportList;
import com.nci.dcs.homepage.report.model.ReportModule;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.supervision.model.SupervisionModule;
import com.nci.dcs.supervision.service.ISupervisionService;
import com.nci.dcs.system.model.Bmb;

public class LocateAction extends BaseAction<CcLocationInfo> {
	@Autowired
	private LocationService locateService;
	@Autowired
	private OrganizationInfoService orgService;
	//add by yang on 2016/6/14
	@Autowired
	private LocationBufferService locateBufferService;
	private List<ReportModule> chartDatas;
	@Autowired
	private FxryCrimeinfoService crimeService;
	//end
	@Autowired
	private FxryBasicinfoService fxryService;
	@Autowired
	private FxryDeviceService deviceService;
	@Autowired
	private AlarmService alarmService;

	public Map resmap = new HashMap();
	public List datas = new ArrayList();
	//add by yang on 2016/08/31
	public List baseinfos = new ArrayList();
	//end
	public Page dataPage = new Page();
	private StringBuilder history;

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		String fxryId = request.getParameter("fxryid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		List<CcLocationInfo> list ;
		if(startTime!=null&&endTime!=null){
			//update by yang on 2016/6/14
			int days = locateBufferService.getDiffDays(startTime);
			if(days >= 0 && days < 3){
				List<CcLocationInfoBuffer> listfrombuffer = locateBufferService.findHistory(fxryId, startTime, endTime);
				list = new ArrayList<CcLocationInfo>();
				for(CcLocationInfoBuffer locbuffer : listfrombuffer){
					CcLocationInfo loc = new CcLocationInfo();
					PropertyUtils.copyProperties(loc, locbuffer);
					list.add(loc);
				}
			}else{
				list = locateService.findHistory(fxryId,startTime,endTime);
			}
		}else{
			list = locateService.findHistory(fxryId);
		}
		
		//add by yang on 2016/08/26
		CcFxryCrimeinfo baseinfo = crimeService.get(fxryId);
		//end
		history = new StringBuilder();
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
			datas = new ArrayList();
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
		}
		
		//add by yang on 2016/08/26
		if(baseinfo != null){
			datas.add(baseinfo.getId());
			datas.add(baseinfo.getName());
			datas.add(baseinfo.getSex());
			datas.add(baseinfo.getIdcard());
			datas.add(baseinfo.getPhoneNumber());
			datas.add(baseinfo.getHouseAddress());
			datas.add(baseinfo.getAdjustType());
			datas.add(baseinfo.getPicture());
		}else{
			datas.add("");
			datas.add("");
			datas.add("");
			datas.add("");
			datas.add("");
			datas.add("");
			datas.add("");
			datas.add("");
		}
		//end
		
		return SUCCESS;
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
		return SUCCESS;
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

	public String cityView() {
		datas = locateService.getStaData(getUser());

		return SUCCESS;
	}

	public String countyView() {
		String cCode = request.getParameter("cCode");
		datas = locateService.getCoutyData(cCode,getUser());
		return SUCCESS;
	}
	
	public String realTimeLocation(){
		String startTime =  request.getParameter("personName");
		return null;
	}

	public String searchGIS() {
		String personName = request.getParameter("personName");
		String orgId = request.getParameter("orgId");
		String alarmSta = request.getParameter("alarmSta");
		String corrType = request.getParameter("corrType");
		String pageNo = request.getParameter("pageNo");
		String hasDevice = request.getParameter("hasDevice");
		String fxryid = request.getParameter("fxryid");
		List<Criterion> params = new ArrayList<Criterion>();
		//检查用户是否有查看特管人员权限特管
				if("2".equals(getUser().getIsws())){
					params.add(Restrictions.eq("isTgry","2"));
				}
		if (StringUtils.isNotBlank(personName)) {
			params.add(Restrictions.like("name", SQLStringUtils.escape(personName), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(orgId)) {
			List<String> orgIds = orgService.getChildrenIds(orgId);
			params.add(Restrictions.in("orgId", orgIds));
		}else{
			List<String> orgIds = orgService.getChildrenIds(getUser().getWunit().getBm());
			params.add(Restrictions.in("orgId", orgIds));
		}
		if (StringUtils.isNotBlank(alarmSta)) {
			if(alarmSta.equals("1")){
				params.add(Restrictions.gt("ac", 0));
			}else{
				params.add(Restrictions.or(Restrictions.isNull("ac"), Restrictions.eq("ac",0)));
			}
		}
		if (StringUtils.isNotBlank(corrType)) {
			
		}
		if (StringUtils.isNotBlank(hasDevice)) {
			hasDevice = (Integer.parseInt(hasDevice) -1)+"";
			params.add(Restrictions.eq("status", hasDevice));
		}
		if (StringUtils.isNotBlank(fxryid)) {
			params.add(Restrictions.eq("id", fxryid));
		}
		datas = locateService.getAllGisData(params);

		dataPage.setResult(datas);
		dataPage.setPageNo(pageNo);
		dataPage.setPageSize(4);
		dataPage.setTotalPages(datas.size() / 4);
		dataPage.setTotalCount(datas.size());
		
		return SUCCESS;
	}
	
	/***
	 * 
	 * 方法名称: searchFxryList
	 * 方法描述：
	 * 创建人： yang
	 * 创建时间: 2016-8-31下午05:30:45
	 * 修改人： yang
	 * 修改时间: 2016-8-31下午05:30:45
	 * 修改备注：
	 * 参数： @return
	 *
	 */
	public String searchFxryList() {
		String personName = request.getParameter("personName");
		String orgId = request.getParameter("orgId");
		String alarmSta = request.getParameter("alarmSta");
		String corrType = request.getParameter("corrType");
		String pageNo = request.getParameter("pageNo");
		String hasDevice = request.getParameter("hasDevice");

		List<Criterion> params = new ArrayList<Criterion>();
		//过滤特管
		if("2".equals(getUser().getIsws())){
			params.add(Restrictions.eq("isTgry","2"));
		}
		if (StringUtils.isNotBlank(personName)) {
			params.add(Restrictions.like("name", SQLStringUtils.escape(personName)));
		}
		if (StringUtils.isNotBlank(orgId)) {
			List<String> orgIds = orgService.getChildrenIds(orgId);
			params.add(Restrictions.in("orgId", orgIds));
		}else{
			List<String> orgIds = orgService.getChildrenIds(getUser().getWunit().getBm());
			params.add(Restrictions.in("orgId", orgIds));
		}
		if (StringUtils.isNotBlank(alarmSta)) {
			if(alarmSta.equals("1")){
				params.add(Restrictions.gt("ac", 0));
			}else{
				params.add(Restrictions.or(Restrictions.isNull("ac"), Restrictions.eq("ac",0)));
			}
		}
		if (StringUtils.isNotBlank(corrType)) {
			params.add(Restrictions.eq("adjustType", corrType));
		}
		if (StringUtils.isNotBlank(hasDevice)) {
			hasDevice = (Integer.parseInt(hasDevice) -1)+"";
			params.add(Restrictions.eq("status", hasDevice));
		}
		
		dataPage.setPageNo(pageNo);
		dataPage.setPageSize(20);
		dataPage = locateService.getPagedGisData(params, dataPage);
		
		baseinfos = new ArrayList<CcFxryCrimeinfo>();
		if(dataPage != null && dataPage.getResult() != null){
			for(ViewFxryOnMap viewFxryOnMap : (List<ViewFxryOnMap>)dataPage.getResult()){
				baseinfos.add(crimeService.get(viewFxryOnMap.getId()));
			}
		}
		
		if(dataPage == null){
			dataPage = new Page();
			dataPage.setPageNo(1);
		}
		dataPage.setResult(baseinfos);
		
		return SUCCESS;
	}

	public String getAlarmDetailOnGis() {
		String id = request.getParameter("id");
		datas = locateService.getAlarmDetailOnGis(id);
		return SUCCESS;
	}

	public String getAllXYById() {
		String id = request.getParameter("id");
		page = new Page<CcLocationInfo>();
		this.getRequestPage();
		page.getCriterions().add(Restrictions.eq("fxryId", id));
		locateService.findPaged(page);
		return SUCCESS;
	}

	public String countyViewControl() {
		return SUCCESS;
	}

	public List<ReportModule> getChartDatas() {
		return chartDatas;
	}

	public void setChartDatas(List<ReportModule> chartDatas) {
		this.chartDatas = chartDatas;
	}

	public String locationDetail() {
		String id = request.getParameter("orgId") ;
		if(id==null)
			request.setAttribute("orgId",getUser().getWunit().getBm());
		else
			request.setAttribute("orgId", id );
		return SUCCESS;
	}
	
	/**
	 * 
	 * 方法名称: toCountryChart
	 * 方法描述：
	 * 创建人： yang
	 * 创建时间: 2016-8-12下午05:21:14
	 * 修改人： yang
	 * 修改时间: 2016-8-12下午05:21:14
	 * 修改备注：
	 * 参数： @return
	 *
	 */
	public String toCountryChart(){
		return "success";
	}
	
	/**
	 * 
	 * 方法名称: getChartData
	 * 方法描述：
	 * 创建人： yang
	 * 创建时间: 2016-8-16下午03:00:16
	 * 修改人： yang
	 * 修改时间: 2016-8-16下午03:00:16
	 * 修改备注：
	 * 参数： @return
	 *
	 */
	public String getChartData(){
		chartDatas = new ArrayList<ReportModule>();
		List<Object[]> chartList = new ArrayList<Object[]>();
		Bmb bmb = getCurOrg();
		if (null != bmb && (bmb.isSj() || bmb.isQxsfj())){
			ReportModule module = new ReportModule();
			module.setType("1");
			String cCode = request.getParameter("cCode") ;
			if(StringUtils.isNotEmpty(cCode)){
				if(!"1".equals(cCode)){
					String orgName = orgService.getOrgName(cCode);
					module.setName(orgName + "各司法所电子监管人员统计");
					chartList = locateService.getCoutyData(cCode,getUser());
				}else{
					module.setName("北京市司法局各区县电子监管人员统计");
					chartList = locateService.getCityData(getUser());
				}
			}else{
				module.setName(bmb.getMc() + "各司法所电子监管人员统计");
				chartList = locateService.getCoutyData(bmb.getBm(),getUser());
			}
			long count = 0;
			List<ReportList> dataList = new ArrayList<ReportList>();
			for (Object[] obj : chartList) {
				ReportList data = new ReportList();
				String orgid = obj[0]==null ? "" : (String)obj[0];
			    String orgName = obj[1]==null ? "" : (String)obj[1];
			    //Integer zjryCount = obj[2]==null ? 0 : (Integer)obj[2];
			    Integer jkryCount = obj[3]==null ? 0 : (Integer)obj[3];
			    data.setName(orgName);
			    data.setLink("data/dwjk/locationDetail.action?orgId=" + orgid);
				data.setValue(String.valueOf(jkryCount.intValue()));
				count += jkryCount;
				dataList.add(data);
			}
			String detail = "电子监管人数:" + count + "个";
			module.setDetail(detail);
			module.setListData(dataList);
			chartDatas.add(module);
		}
		
		return "success";
	}

	public List getBaseinfos() {
		return baseinfos;
	}

	public void setBaseinfos(List baseinfos) {
		this.baseinfos = baseinfos;
	}

	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}

	public LocationService getLocateService() {
		return locateService;
	}

	public void setLocateService(LocationService locateService) {
		this.locateService = locateService;
	}

	public AlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public FxryDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(FxryDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public Page getDataPage() {
		return dataPage;
	}

	public void setDataPage(Page dataPage) {
		this.dataPage = dataPage;
	}
	
	public FxryBasicinfoService getFxryService() {
		return fxryService;
	}

	public void setFxryService(FxryBasicinfoService fxryService) {
		this.fxryService = fxryService;
	}

	public Map getResmap() {
		return resmap;
	}

	public void setResmap(Map resmap) {
		this.resmap = resmap;
	}
}
