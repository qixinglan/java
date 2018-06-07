package com.nci.dcs.em.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.action.DzjgsbDeviceAction.Excel;
import com.nci.dcs.em.model.CcSimDevice;
import com.nci.dcs.em.model.CcYwLawDevice;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.service.DeviceRecordService;
import com.nci.dcs.em.service.SimDeviceService;
import com.nci.dcs.em.service.YwLawDeviceService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.nci.dcs.system.service.UserService;

import edu.emory.mathcs.backport.java.util.Arrays;

public class YwLawDeviceAction extends BaseAction<CcYwLawDevice>{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private List rlist;
	private AjaxFormResult ajaxFormResult;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	@Autowired
	private UserService useService;
	@Autowired 
	private SimDeviceService simDeviceService;
	@Autowired
	private YwLawDeviceService ywLawDeviceService;
	@Autowired 
	private DeviceRecordService deviceRecordService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private List<CcSimDevice> noUsedSimDevices;
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
	public String zfsbzt(){
		String useUnit=request.getParameter("useUnit");
		rlist=(ywLawDeviceService.findTotalNumbersByStatus(getCurOrgId(),rootOrgId,useUnit));
		return SUCCESS;
	}
	//各司法所在用数量统计
	public String zfsbsfs(){
		String useUnit=request.getParameter("useUnit");
		List<Map> list=null;
		if(useUnit!=null&&useUnit!=""&&getCurOrgId().equals(rootOrgId)){
			list=ywLawDeviceService.findTotalNumbersBySfs(useUnit);
		}else if(useUnit==""&&getCurOrgId().equals(rootOrgId)){
			list=ywLawDeviceService.findTotalNumbersBySfj();
		}
		else{
			list=ywLawDeviceService.findTotalNumbersBySfs(getCurOrgId());
		}
		for(Map m:list){
			m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
		}
		rlist=list;
		return SUCCESS;
		}
		//各司法局在用数量统计
	public String zfsbsfj(){
		List<Map> list=ywLawDeviceService.findTotalNumbersBySfj();
		for(Map m:list){
			m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
		}
		rlist=list;
		return SUCCESS;
			}
	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		
		return SUCCESS;
	}
	public String search(){
		boolean haveStatus=false;
		try{
			List<SearchRule> searchs = this
					.parseJQGridRequest(getDefaultRule());
			//统计信息点击柱状图搜索
			String status=request.getParameter("status");
			String useUnit=request.getParameter("useUnit");
			if(status!=null&&!status.equals("")&&!status.equals("null")){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("eq");
				sr.setData(status);
				searchs.add(sr);
			}
			if(useUnit!=null&&!useUnit.equals("")&&!useUnit.equals("null")){
				SearchRule sr= new SearchRule();
				sr.setField("useUnit");
				sr.setOp("eq");
				sr.setData(useUnit);
				searchs.add(sr);
				haveStatus=true;//因为点击柱状图搜索若为单位柱状图没有status搜索条件，会过滤掉作废，注销状态的所以。。。
			}
			for(int i=0;i<searchs.size();i++){
				//搜索条件若为useUnit，判断单位是否司法局或司法所,若为司法所修改条件为useSfsUnit
				if(searchs.get(i).getField().equals("useUnit")){
					OrganizationInfo organizationInfo=organizationInfoService.get(searchs.get(i).getData());
					if(organizationInfo.getOrgType().equals(Constants.ORGANIZATION_SFSTYPE)){
						searchs.get(i).setField("useSfsUnit");
					}
				}
				if(searchs.get(i).getField().equals("status")){
					haveStatus=true;
				}
			}
			//默认作废损坏状态不显示但是可以通过搜索显示
			if(!haveStatus){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("nc");//not like 
				sr.setData(Constants.DEVICE_BROKENSTATUS);
				searchs.add(sr);
			}
			System.out.println(searchs);
			ywLawDeviceService.findDzzjgsbPaged(
					this.fillPageWithJQGridRequest(), searchs);
		}catch(Exception e){
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		entity= ywLawDeviceService.findById(entity.getId());
		String deviceNumber = entity.getDeviceNumber();
		List<CcDeviceRecord> deviceRecords=deviceRecordService.findAllByDeviceNumber(deviceNumber);
		//显示操作人人名
		for(CcDeviceRecord d:deviceRecords){
			d.setOperatePersionName(useService.get(d.getOperatePersion()).getName());
		}
		request.setAttribute("deviceRecords", deviceRecords);
		return "success";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String[] ids=request.getParameter("id").split(",");
		
		//添加操作记录——删除
		
		for(String i:ids){
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			entity=ywLawDeviceService.findById(i);
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
			if(entity.getPhoneNumber()!=null){	//设备状态为作废时，没有sim卡
				//修改本设备的sim卡状态为未用
				CcSimDevice simd=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
				simd.setStatus(Constants.DEVICE_NOTUSEDSTATUS);
				simd.setUseDeviceNumber(null);
				simd.setUseSfsUnit(null);
				simDeviceService.update(simd);
				//添加sim卡操作记录——停用
				CcDeviceRecord deviceSimRecord=new CcDeviceRecord();
				deviceSimRecord.setDeviceNumber(simd.getDeviceNumber());//设备编号
				deviceSimRecord.setOperateName(Constants.DEVICELIFE_STOPUSE);//操作名称
				deviceSimRecord.setOperateDate(new Date());//操作日期
				deviceSimRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
				deviceSimRecord.setUseUnit(simd.getUseUnit());
				deviceSimRecord.setOperatePersion(getUserId());//操作人id
				//deviceSimRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
				deviceSimRecord.setOperateUnit(getCurOrgId());//操作人单位
				deviceRecordService.add(deviceSimRecord);
			}
		}
		
		
		//放在最后
		ywLawDeviceService.delete(ids);//删除设备
		
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
	//显示添加页面
	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		//用于显示局使用单位/所使用单位下拉选
		List<OrganizationInfo>  organizationInfos=null;
		String id=getCurOrgId();
		if(id.equals(rootOrgId)){//市局单位
		}else{//非市局单位只显示司法局
		}
		return "success";
	}
	//sim卡号 联动下拉列表,找到未用的Sim卡
	public String findSim(){
		try {
			//找到未用的Sim卡，list<SimDevice>
			String useUnit=request.getParameter("useUnit");
			noUsedSimDevices=simDeviceService.findNoUsedByUseUnitAndUseType(useUnit,Constants.USEDEVICE_YDZFZDTYPE);
			//封装参数dicts
			Map<String, List<DictionaryKeyValue>> dicts = new HashMap<String,List<DictionaryKeyValue>>();
			List<DictionaryKeyValue> dictionaryKeyValueList = new ArrayList<DictionaryKeyValue>();
			for (CcSimDevice item : noUsedSimDevices){
				DictionaryKeyValue keyvalue = new DictionaryKeyValue();
				keyvalue.setName(item.getPhoneNumber());
				keyvalue.setCode(item.getPhoneNumber());
				keyvalue.setUsing(true);
				dictionaryKeyValueList.add(keyvalue);
			}
			dicts.put("NOUSEDSIM", dictionaryKeyValueList);
			this.response.setHeader("Last-Modified", HttpUtil.date2LastModified(new Date()));
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(dicts);
			this.response.getWriter().write(result.toString());
		} catch (IOException e) {
		}
		return "none";
	}
	//新增执法终端设备
	public String addLawDevice() throws Throwable {
		// TODO Auto-generated method stub
		try{
			ywLawDeviceService.create(entity);
			/*
			 * 修改sim卡状态为已用
			 */
			CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
			simDevice.setStatus(Constants.DEVICE_USEDSTATUS);
			simDevice.setUseDeviceNumber(entity.getDeviceNumber());
			simDevice.setUseSfsUnit(entity.getUseSfsUnit());
			simDeviceService.update(simDevice);
			/*
			 * 新增设备记录--购买
			 */
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateName(Constants.DEVICELIFE_BUY);//操作名称
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseUnit(entity.getUseUnit());//局使用单位
			deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());//所使用单位
			HttpSession session=request.getSession();
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(deviceRecord);
			/*
			 * 新增设备记录--sim卡被使用
			 */
			CcDeviceRecord simdeviceRecord=new CcDeviceRecord();
			simdeviceRecord.setDeviceNumber(simDevice.getDeviceNumber());//设置设备编号
			simdeviceRecord.setSimNumber(entity.getPhoneNumber());//设备卡号
			simdeviceRecord.setOperateName(Constants.DEVICELIFE_USE);//操作名称
			simdeviceRecord.setOperateDate(new Date());//操作日期
			simdeviceRecord.setUseUnit(entity.getUseUnit());
			simdeviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			simdeviceRecord.setYdDeviceNumber(entity.getDeviceNumber());
			//simdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			simdeviceRecord.setOperatePersion(getUserId());//操作人id
			simdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(simdeviceRecord);
			ajaxFormResult=new AjaxFormResult(true,"添加成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxFormResult=new AjaxFormResult(false,"添加失败");
		}
		return "success";
	}
	//状态修改同时做记录
		public String changeStatus(){
			// 状态修改
			String id=request.getParameter("id");
			String status=request.getParameter("status");
			entity=ywLawDeviceService.findById(id);
			if(status.equals(Constants.DEVICE_BROKENSTATUS)){//作废操作，sim卡要回收，本设备就没有sim卡了
				CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
				entity.setPhoneNumber(null);
				simDevice.setUseDeviceNumber(null);
				simDevice.setStatus(Constants.DEVICE_NOTUSEDSTATUS);//修改sim卡状态为未用
				simDevice.setUseSfsUnit(null);
				simDeviceService.update(simDevice);
				//添加sim卡使用记录——停用
				CcDeviceRecord simdeviceRecord=new CcDeviceRecord();
				simdeviceRecord.setDeviceNumber(simDevice.getDeviceNumber());//设置设备编号
				simdeviceRecord.setSimNumber(simDevice.getPhoneNumber());//设备卡号
				simdeviceRecord.setOperateName(Constants.DEVICELIFE_STOPUSE);//操作名称
				simdeviceRecord.setOperateDate(new Date());//操作日期
				simdeviceRecord.setOperatePersion(getUserId());//操作人id
				simdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
				//simdeviceRecord.setUseSfsUnit(simDevice.getUseSfsUnit());//使用单位司法所
				simdeviceRecord.setUseUnit(simDevice.getUseUnit());//使用单位司法局
				deviceRecordService.add(simdeviceRecord);
			}
			entity.setStatus(status);
			ywLawDeviceService.update(entity);
			//添加操作记录
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());
			deviceRecord.setUseUnit(entity.getUseUnit());
			HttpSession session=request.getSession();
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			if(status.equals(Constants.DEVICE_FIXSTATUS)){
				deviceRecord.setOperateName(Constants.DEVICELIFE_FIX);//操作名称
			}
			if(status.equals(Constants.DEVICE_BROKENSTATUS)){
				deviceRecord.setOperateName(Constants.DEVICELIFE_ABANDON);//操作名称
			}
			if(status.equals(Constants.DEVICE_NOTUSEDSTATUS)){
				deviceRecord.setOperateName(Constants.DEVICELIFE_RETURN);//操作名称
			}
			deviceRecordService.add(deviceRecord);
			ajaxFormResult=AjaxFormResult.success("null");
			return SUCCESS;
		}
		//修改sim卡号
		public String changeSimNum(){
			String id=request.getParameter("id");
			String phoneNumber=request.getParameter("phoneNumber");
			entity=ywLawDeviceService.findById(id);
			String oPhoneNumber=entity.getPhoneNumber();
			entity.setPhoneNumber(phoneNumber);
			ywLawDeviceService.update(entity);
			//新号为已用状态,修改旧卡号为未用状态，同时添加新号的使用记录
			CcSimDevice osim=simDeviceService.findSimDeviceByPhoneNumber(oPhoneNumber);
			CcSimDevice sim=simDeviceService.findSimDeviceByPhoneNumber(phoneNumber);
			sim.setUseUnit(osim.getUseUnit());
			sim.setUseSfsUnit(osim.getUseSfsUnit());
			sim.setUseDeviceNumber(osim.getUseDeviceNumber());
			sim.setStatus(Constants.DEVICE_USEDSTATUS);
			simDeviceService.update(sim);
			osim.setStatus(Constants.DEVICE_NOTUSEDSTATUS);
			osim.setUseDeviceNumber(null);
			osim.setUseSfsUnit(null);
			simDeviceService.update(osim);
			
		
			CcDeviceRecord simdeviceRecord=new CcDeviceRecord();
			simdeviceRecord.setDeviceNumber(sim.getDeviceNumber());//设置设备编号
			simdeviceRecord.setSimNumber(phoneNumber);//设备卡号
			simdeviceRecord.setOperateName(Constants.DEVICELIFE_USE);//操作名称
			simdeviceRecord.setOperateDate(new Date());//操作日期
			//simdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			simdeviceRecord.setOperatePersion(getUserId());//操作人id
			simdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			simdeviceRecord.setYdDeviceNumber(entity.getDeviceNumber());
			simdeviceRecord.setUseSfsUnit(sim.getUseSfsUnit());
			simdeviceRecord.setUseUnit(sim.getUseUnit());
			deviceRecordService.add(simdeviceRecord);
			//添加移动执法设备使用记录——更换sim卡
			CcDeviceRecord ydzfDeviceRecord=new CcDeviceRecord();
			ydzfDeviceRecord.setDeviceNumber(entity.getDeviceNumber());//设置设备编号
			ydzfDeviceRecord.setSimNumber(phoneNumber);//设备卡号
			ydzfDeviceRecord.setOperateName(Constants.DEVICELIFE_REPLACESIM);//操作名称
			ydzfDeviceRecord.setOperateDate(new Date());//操作日期
			//ydzfDeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			ydzfDeviceRecord.setOperatePersion(getUserId());//操作人id
			ydzfDeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			ydzfDeviceRecord.setUseSfsUnit(entity.getUseSfsUnit());
			ydzfDeviceRecord.setUseUnit(entity.getUseUnit());
			deviceRecordService.add(ydzfDeviceRecord);
			//添加旧sim卡使用记录——被替换
			CcDeviceRecord osimdeviceRecord=new CcDeviceRecord();
			osimdeviceRecord.setDeviceNumber(osim.getDeviceNumber());//设置设备编号
			osimdeviceRecord.setSimNumber(oPhoneNumber);//设备卡号
			osimdeviceRecord.setOperateName(Constants.DEVICELIFE_REPLACEED);//操作名称
			osimdeviceRecord.setOperateDate(new Date());//操作日期
			//osimdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			osimdeviceRecord.setOperatePersion(getUserId());//操作人id
			osimdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			osimdeviceRecord.setUseUnit(osim.getUseUnit());
			deviceRecordService.add(osimdeviceRecord);
			ajaxFormResult = AjaxFormResult.success(null);
			return SUCCESS;
		}
		//导出
		private static final String[] headers = new String[] { "设备编号",
			"购买单位", "购买日期", "使用单位（司法局）", "使用单位（司法所）","sim卡号", "状态" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exportExcel() throws UnsupportedEncodingException {
		CreateFileUtil util = CreateFileUtil.getInstance();
		String[] head = jqgrid.getCols().split(",");
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		headTable.add(0, "序号");

		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		// service.findByfilter(entity1, entity2);
		try {
			//加入非作废状态条件
			List<SearchRule> searchs=getDefaultRule();
			String filters=this.jqgrid.getFilters();
			if(filters==null||(filters!=null&&!filters.contains("status"))){
				SearchRule sr = new SearchRule();
				sr.setField("status");
				sr.setOp("nc");//not like 
				sr.setData(Constants.DEVICE_BROKENSTATUS);
				searchs.add(sr);
			}
			List res = ywLawDeviceService.findPaged(
					fillPageWithJQGridRequest(),
					parseJQGridRequest(searchs), Arrays.asList(head));
			for (int i = 0; i < res.size(); i++) {
				Object[] cc = (Object[]) res.get(i);
				LinkedList content = new LinkedList();
				content.add(i);
				for (int j = 0; j < cc.length; j++) {
					if (j == 6) {
						content.add(dictionaryInfoService.findItemsMap("ZFSBZT").get(cc[j]));
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

	public YwLawDeviceService getYwLawDeviceService() {
		return ywLawDeviceService;
	}

	public void setYwLawDeviceService(YwLawDeviceService ywLawDeviceService) {
		this.ywLawDeviceService = ywLawDeviceService;
	}

	public DeviceRecordService getDeviceRecordService() {
		return deviceRecordService;
	}

	public void setDeviceRecordService(DeviceRecordService deviceRecordService) {
		this.deviceRecordService = deviceRecordService;
	}
	public SimDeviceService getSimDeviceService() {
		return simDeviceService;
	}
	public void setSimDeviceService(SimDeviceService simDeviceService) {
		this.simDeviceService = simDeviceService;
	}
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
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
	public OrganizationInfoService getOrganizationInfoService() {
		return organizationInfoService;
	}
	public void setOrganizationInfoService(
			OrganizationInfoService organizationInfoService) {
		this.organizationInfoService = organizationInfoService;
	}
	public List<CcSimDevice> getNoUsedSimDevices() {
		return noUsedSimDevices;
	}
	public void setNoUsedSimDevices(List<CcSimDevice> noUsedSimDevices) {
		this.noUsedSimDevices = noUsedSimDevices;
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
