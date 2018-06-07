package com.nci.dcs.em.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.model.CcSimDevice;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.service.DeviceRecordService;
import com.nci.dcs.em.service.SimDeviceService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.nci.dcs.system.service.UserService;

import edu.emory.mathcs.backport.java.util.Arrays;
public class SimDeviceAction extends BaseAction<CcSimDevice>{
	private List rlist;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private AjaxFormResult ajaxFormResult;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	@Autowired
	private UserService useService;
	@Autowired
	private SimDeviceService simDeviceService;
	@Autowired 
	private DeviceRecordService deviceRecordService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private Excel excel;
	class Excel {
		private InputStream inputStream;
		private String name;

		public Excel(InputStream inputStream, String name) {
			super();
			this.inputStream = inputStream;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
	}
	// 状态统计
	public String simsbzt(){
		String useUnit=request.getParameter("useUnit");
		rlist=(simDeviceService.findTotalNumbersByStatus(getCurOrgId(),rootOrgId,useUnit));
		return SUCCESS;
	}
	//各司法所在用数量统计
	public String simsbsfs(){
		String useUnit=request.getParameter("useUnit");
		List<Map> list=null;
		if(useUnit!=null&&useUnit!=""&&getCurOrgId().equals(rootOrgId)){
			list=simDeviceService.findTotalNumbersBySfs(useUnit);
		}else if(useUnit==""&&getCurOrgId().equals(rootOrgId)){
			list=simDeviceService.findTotalNumbersBySfj();
		}else{
			list=simDeviceService.findTotalNumbersBySfs(getCurOrgId());
		}
		for(Map m:list){
			m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
		}
		rlist=list;
		return SUCCESS;
	}
	//各司法局在用数量统计
	public String simsbsfj(){
		if(getCurOrgId().equals(rootOrgId)){//用户类型判断，防止非对应客户自己写请求地址获得数据
			List<Map> list=simDeviceService.findTotalNumbersBySfj();
			for(Map m:list){
				m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
			}
			rlist=list;
			return SUCCESS;
		}
		return SUCCESS;
	}
	//类型统计
	public String simsbty(){
		String useUnit=request.getParameter("useUnit");
		List<Map> list=null;
		list=simDeviceService.findTotalNumbersByType(getCurOrgId(),rootOrgId, useUnit);
		rlist=list;
		return SUCCESS;
	}
	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String search() {
		boolean haveStatus=false;
		try {
			List<SearchRule> searchs = this
					.parseJQGridRequest(getDefaultRule());
			//统计信息点击柱状图搜索
			String status=request.getParameter("status");
			String useUnit=request.getParameter("useUnit");
			String useDeviceType=request.getParameter("useDeviceType");
			if(status!=null&&!status.equals("null")&&!"undefined".equals(status)&&!status.equals("")){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("eq");
				sr.setData(status);
				searchs.add(sr);
			}
			if(useUnit!=null&&!useUnit.equals("null")&&!"undefined".equals(useUnit)&&!useUnit.equals("")){
				SearchRule sr= new SearchRule();
				sr.setField("useUnit");
				sr.setOp("eq");
				sr.setData(useUnit);
				searchs.add(sr);
				haveStatus=true;//因为点击柱状图搜索若为单位柱状图没有status搜索条件，会过滤掉作废，注销状态的所以。。。
			}
			if(useDeviceType!=null&&!useDeviceType.equals("null")&&!"undefined".equals(useDeviceType)&&!useDeviceType.equals("")){
				SearchRule sr = new SearchRule();
				sr.setField("useDeviceType");
				sr.setOp("eq");
				sr.setData(useDeviceType);
				searchs.add(sr);
				haveStatus=true;//因为点击柱状图没有status搜索条件，会过滤掉作废，注销状态的所以。。。
			}
			for(int i=0;i<searchs.size();i++){
				//搜索条件若为useUnit，判断单位是否司法局或司法所,若为司法所修改条件为useSfsUnit
				if(searchs.get(i).getField().equals("useUnit")){
					if(!"undefined".equals(searchs.get(i).getData())){
						OrganizationInfo organizationInfo=organizationInfoService.get(searchs.get(i).getData());
						if(organizationInfo.getOrgType().equals(Constants.ORGANIZATION_SFSTYPE)){
							searchs.get(i).setField("useSfsUnit");
						}
					}
				}
				if(searchs.get(i).getField().equals("status")){
					haveStatus=true;
				}
			}
			//默认 注销状态不显示但是可以通过搜索显示
			if(!haveStatus){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("nc");//not like 
				sr.setData(Constants.DEVICE_LOGOUTSTATUS);
				searchs.add(sr);
			}
			System.out.println(searchs);
			simDeviceService.findDzzjgsbPaged(
					this.fillPageWithJQGridRequest(), searchs);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return SUCCESS;
	}
	protected List<SearchRule> getDefaultRule() {
		// 可以加入本级单位过滤
		List<SearchRule> rules = new ArrayList<SearchRule>();
		String orgId = getCurOrgId();//机构ID   rootOrgId=1市局
		if (!rootOrgId.equals(orgId)) {//非市局的搜索条件，购买单位修改为使用单位useUnit
			SearchRule sr = new SearchRule();
			sr.setField("useUnit");
			sr.setOp("eq");
			sr.setData(orgId);
			rules.add(sr);
		}
		return rules;
	}
	@Override
	public String view() throws Throwable {
		entity=simDeviceService.findSimDeviceBydeviceNumber(entity.getDeviceNumber());
		String deviceNumber = entity.getDeviceNumber();
		List<CcDeviceRecord> deviceRecords=deviceRecordService.findAllByDeviceNumber(deviceNumber);
		//显示操作人人名
		for(CcDeviceRecord d:deviceRecords){
			d.setOperatePersionName(useService.get(d.getOperatePersion()).getName());
		}
		request.setAttribute("deviceRecords", deviceRecords);
		return SUCCESS;
	}
	//状态修改同时做记录
		public String changeStatus(){
			// 状态修改
			String id=request.getParameter("id");
			String status=request.getParameter("status");
			entity=simDeviceService.findSimDeviceBydeviceNumber(id);
			entity.setStatus(status);
			simDeviceService.update(entity);
			//添加操作记录
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());//使用单位司法局
			deviceRecord.setUseUnit(entity.getUseUnit());//使用单位司法所
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			if(status.equals(Constants.DEVICE_LOGOUTSTATUS)){
				deviceRecord.setOperateName(Constants.DEVICELIFE_LOGOFF);//操作名称
			}
			if(status.equals(Constants.DEVICE_NOTUSEDSTATUS)){
				deviceRecord.setOperateName(Constants.DEVICELIFE_OPEN);//操作名称
			}
			deviceRecordService.add(deviceRecord);
			ajaxFormResult=AjaxFormResult.success("null");
			return SUCCESS;
		}
	@Override
	//显示新增弹出框
	public String input() throws Exception {
		// TODO Auto-generated method stub
		//用于显示使用单位下拉选
		String id=getCurOrgId();
		if(id.equals(rootOrgId)){//市局单位
		}else{//非市局单位只显示司法局
		}
		return "add";
	}
	//导出
		private static final String[] headers = new String[] { "sim卡号",
			"购买单位", "购买日期", "使用单位（司法局）","使用单位（司法所）", "SIM卡类型", "状态" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exportExcel() throws UnsupportedEncodingException {
		CreateFileUtil util = CreateFileUtil.getInstance();
		String[] head = jqgrid.getCols().split(",");
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		headTable.add(0, "序号");

		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		// service.findByfilter(entity1, entity2);
		try {
			//加入非注销状态条件
			List<SearchRule> searchs=getDefaultRule();
			String filters=this.jqgrid.getFilters();
			if(filters==null||(filters!=null&&!filters.contains("status"))){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("nc");//not like 
				sr.setData(Constants.DEVICE_LOGOUTSTATUS);
				searchs.add(sr);
			}
			List res = simDeviceService.findPaged(
					fillPageWithJQGridRequest(),
					parseJQGridRequest(searchs), Arrays.asList(head));
			for (int i = 0; i < res.size(); i++) {
				Object[] cc = (Object[]) res.get(i);
				LinkedList content = new LinkedList();
				content.add(i);
				for (int j = 0; j < cc.length; j++) {
					if (j == 5) {
						content.add(dictionaryInfoService.findItemsMap("SIMTYPE").get(cc[j]));
						continue;
					}
					if (j == 6) {
						content.add(dictionaryInfoService.findItemsMap("SIMSBZT").get(cc[j]));
						continue;
					}
					if (j == 1 || j == 3||j==4) {
						content.add(getOrgName((String) cc[j]));
						continue;
					}
					if (cc[j] != null && cc[j] instanceof Date) {
						cc[j] = sdf.format(cc[j]);
					}
					content.add(cc[j]);
				}
				contentList.add(content);
			}
			String fileName = util.create(headTable, contentList);
			excel = new Excel(util.getFileInputStream(fileName), fileName);
		} catch (Exception e) {
			logger.error(e);
		}
		return SUCCESS;
	}
	@Override
	public String delete() throws Throwable {
		String[] deviceNumbers=request.getParameter("deviceNumber").split(",");
		
		//添加操作记录——删除
		CcDeviceRecord deviceRecord=new CcDeviceRecord();
		for(String i:deviceNumbers){
			entity=simDeviceService.findSimDeviceBydeviceNumber(i);
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateName(Constants.DEVICELIFE_DELETE);//操作名称
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());
			deviceRecord.setUseUnit(entity.getUseUnit());
			HttpSession session=request.getSession();
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(deviceRecord);
		}
		//放在最后
		simDeviceService.delete(deviceNumbers);//删除设备
		
		ajaxFormResult = AjaxFormResult.success(null);
		return SUCCESS;
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
	//新增sim卡同时在设备记录表添加记录
	public String add() throws Throwable {
		try{
		simDeviceService.add(entity);//新增sim卡设备
		/*
		 * 新增设备记录--购买
		 */
		CcDeviceRecord deviceRecord=new CcDeviceRecord();
		deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
		if(entity.getStatus().equals(Constants.DEVICE_NOTUSEDSTATUS)){
			deviceRecord.setOperateName(Constants.DEVICELIFE_BUYANDOPEN);//操作名称
		}else{
			deviceRecord.setOperateName(Constants.DEVICELIFE_BUY);
		}
		deviceRecord.setOperateDate(new Date());//操作日期
		deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
		deviceRecord.setUseUnit(entity.getUseUnit());//使用单位
		deviceRecord.setOperatePersion(getUserId());//操作人id
		//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
		deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
		deviceRecordService.add(deviceRecord);
		ajaxFormResult = AjaxFormResult.success("添加成功");
		}catch(Exception e){
		e.printStackTrace();
		ajaxFormResult = AjaxFormResult.error("添加失败");
	}
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

	public SimDeviceService getSimDeviceService() {
		return simDeviceService;
	}

	public void setSimDeviceService(SimDeviceService simDeviceService) {
		this.simDeviceService = simDeviceService;
	}
	public DeviceRecordService getDeviceRecordService() {
		return deviceRecordService;
	}

	public void setDeviceRecordService(DeviceRecordService deviceRecordService) {
		this.deviceRecordService = deviceRecordService;
	}
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}
	public OrganizationInfoService getOrganizationInfoService() {
		return organizationInfoService;
	}
	public void setOrganizationInfoService(
			OrganizationInfoService organizationInfoService) {
		this.organizationInfoService = organizationInfoService;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public Excel getExcel() {
		return excel;
	}
	public void setExcel(Excel excel) {
		this.excel = excel;
	}
	public static String[] getHeaders() {
		return headers;
	}
	public UserService getUseService() {
		return useService;
	}
	public void setUseService(UserService useService) {
		this.useService = useService;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	

}
