package com.nci.dcs.em.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.base.action.FileUploadAction;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.dcs.em.model.CcSimDevice;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.service.DeviceFxryService;
import com.nci.dcs.em.service.DeviceRecordService;
import com.nci.dcs.em.service.DzjgsbDeviceService;
import com.nci.dcs.em.service.SimDeviceService;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;
import com.nci.dcs.system.service.UserService;
import com.runqian.report4.model.expression.function.GetCell;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DzjgsbDeviceAction extends BaseAction<CcDzjgsbDevice>{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private List rlist;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	private AjaxFormResult ajaxFormResult;
	@Autowired
	protected FXRYBaseInfoService FXRYBaseInfoservice;
	@Autowired
	private UserService useService;
	@Autowired
	private DzjgsbDeviceService dzjgsbDeviceService;
	@Autowired
	private DeviceFxryService deviceFxryService;
	@Autowired 
	private DeviceRecordService deviceRecordService;
	@Autowired 
	private SimDeviceService simDeviceService;
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
	//历史佩戴人员统计,并非某一设备
	public String jgsbfxrytj(){
		String useUnit=request.getParameter("useUnit");
		String startTime=request.getParameter("startTime");
		String overTime=request.getParameter("overTime");
		String useSfsUnit=request.getParameter("useSfsUnit");
		rlist=(deviceFxryService.findTotalNumbers(getCurOrgId(),rootOrgId,useUnit,startTime,overTime,useSfsUnit));
		return SUCCESS;
	}
	// 状态统计
	public String jgsbzt(){
		String useUnit=request.getParameter("useUnit");
		rlist=(dzjgsbDeviceService.findTotalNumbersByStatus(getCurOrgId(),rootOrgId,useUnit));
		return SUCCESS;
	}
	//各司法所在用数量统计
	public String jgsbsfs(){
		String useUnit=request.getParameter("useUnit");
		List<Map> list=null;
		if(useUnit!=null&&useUnit!=""&&getCurOrgId().equals(rootOrgId)){
			list=dzjgsbDeviceService.findTotalNumbersBySfs(useUnit);
		}else if(useUnit==""&&getCurOrgId().equals(rootOrgId)){
			list=dzjgsbDeviceService.findTotalNumbersBySfj();
		}else{
			list=dzjgsbDeviceService.findTotalNumbersBySfs(getCurOrgId());
		}
		for(Map m:list){
			m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
		}
		rlist=list;
		return SUCCESS;
	}
	//各司法局在用数量统计
	public String jgsbsfj(){
		if(getCurOrgId().equals(rootOrgId)){//用户类型判断，防止非对应客户自己写请求地址获得数据
			List<Map> list=dzjgsbDeviceService.findTotalNumbersBySfj();
			for(Map m:list){
				m.put("name", organizationInfoService.getOrgName((String)(m.get("name"))));
			}
			rlist=list;
			return SUCCESS;
		}
		return SUCCESS;
	}
	@Override
	public String list() throws Throwable {
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
				haveStatus=true;//因为点击柱状图搜索若为单位柱状图没有status搜索条件，会过滤掉作废，注销状态的所以。。。。
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
			dzjgsbDeviceService.findDzzjgsbPaged(
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
		// TODO Auto-generated method stub
		entity=dzjgsbDeviceService.findByDeviceNumber(entity.getDeviceNumber());
		String deviceNumber = entity.getDeviceNumber();
		List<CcDeviceRecord> deviceRecords=deviceRecordService.findAllByDeviceNumber(deviceNumber);
		//显示操作人人名
		for(CcDeviceRecord d:deviceRecords){
			d.setOperatePersionName(useService.get(d.getOperatePersion()).getName());
		}
		//显示佩戴人人名
		for(CcDeviceRecord d:deviceRecords){
			if(d.getWearPersion()!=null){
				d.setWearPersionName(FXRYBaseInfoservice.getById(d.getWearPersion()).getName());
			}
		}
		request.setAttribute("totalFxryNum", deviceFxryService.totalFxryByDy(deviceNumber));//佩戴人员总数(去重)
		request.setAttribute("deviceRecords", deviceRecords);
		return SUCCESS;
	}

	@Override
	//显示新增弹出框
	public String input() throws Exception {
		//用于显示使用单位下拉选
		String id=getCurOrgId();
		if(id.equals(rootOrgId)){//市局单位
		}else{//非市局单位只显示司法局
		}
		return "add";
	}
	//sim卡号联动下拉列表,找到未用的Sim卡
	public String findSim(){
		try {
			//找到未用的Sim卡，list<SimDevice>
			String useUnit=request.getParameter("useUnit");
			noUsedSimDevices=simDeviceService.findNoUsedByUseUnitAndUseType(useUnit, Constants.USEDEVICE_DZJGSBTYPE);
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
	//删除
	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		String[] ids=request.getParameter("id").split(",");
		
		//添加操作记录——删除
		
		for(String i:ids){
			entity=dzjgsbDeviceService.findById(i);
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateName(Constants.DEVICELIFE_DELETE);//操作名称删除
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseUnit(entity.getUseUnit());//使用单位司法局
			deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());//使用单位司法所
			HttpSession session=request.getSession();
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(deviceRecord);
			//修改本设备的sim卡状态为未用
			if(entity.getPhoneNumber()!=null){//设备状态为作废时，没有sim卡
				CcSimDevice simd=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
				simd.setStatus(Constants.DEVICE_NOTUSEDSTATUS);
				simd.setUseDeviceNumber(null);
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
		dzjgsbDeviceService.delete(ids);//删除设备
		
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
	//新增电子监管设备同时在设备记录表、/device-sim、添加记录
	public String add() throws Throwable {
		try{
			dzjgsbDeviceService.add(entity);//新增电子监管设备
			/*
			 * 新增设备记录--购买
			 */
			CcDeviceRecord deviceRecord=new CcDeviceRecord();
			deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
			deviceRecord.setOperateName(Constants.DEVICELIFE_BUY);//操作名称
			deviceRecord.setOperateDate(new Date());//操作日期
			deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
			deviceRecord.setUseUnit(entity.getUseUnit());//使用单位司法局
			HttpSession session=request.getSession();
			deviceRecord.setOperatePersion(getUserId());//操作人id
			//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(deviceRecord);
			/*
			 * 修改sim卡状态为已用
			 */
			CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
			simDevice.setStatus(Constants.DEVICE_USEDSTATUS);
			simDevice.setUseDeviceNumber(entity.getDeviceNumber());
			simDeviceService.update(simDevice);
			/*
			 * 新增设备记录--sim卡被使用
			 */
			CcDeviceRecord simdeviceRecord=new CcDeviceRecord();
			simdeviceRecord.setDeviceNumber(simDevice.getDeviceNumber());//设置设备编号
			simdeviceRecord.setSimNumber(entity.getPhoneNumber());//设备卡号
			simdeviceRecord.setOperateName(Constants.DEVICELIFE_USE);//操作名称
			simdeviceRecord.setOperateDate(new Date());//操作日期
			simdeviceRecord.setUseUnit(entity.getUseUnit());
			simdeviceRecord.setJgDeviceNumber(entity.getDeviceNumber());
			//simdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
			simdeviceRecord.setOperatePersion(getUserId());//操作人id
			simdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
			deviceRecordService.add(simdeviceRecord);
			ajaxFormResult = AjaxFormResult.success("添加成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxFormResult = AjaxFormResult.error("添加失败");
		}
		
		return SUCCESS;
	}
	//状态修改同时做记录
	public String changeStatus(){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		entity=dzjgsbDeviceService.findById(id);
		if(status.equals(Constants.DEVICE_BROKENSTATUS)){//作废操作，sim卡要回收，本设备就没有sim卡了
			CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
			entity.setPhoneNumber(null);
			simDevice.setStatus(Constants.DEVICE_NOTUSEDSTATUS);//修改sim卡状态为未用
			simDevice.setUseDeviceNumber(null);
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
		//添加操作记录
		CcDeviceRecord deviceRecord=new CcDeviceRecord();
		deviceRecord.setDeviceNumber(entity.getDeviceNumber());//设备编号
		deviceRecord.setOperateDate(new Date());//操作日期
		deviceRecord.setSimNumber(entity.getPhoneNumber());//sim卡号
		deviceRecord.setUseSfsUnit(entity.getUseSfsUnit());//使用单位司法局
		//deviceRecord.setUseUnit(entity.getUseUnit());//使用单位司法所
		deviceRecord.setOperatePersion(getUserId());//操作人id
		//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
		deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
		if(status.equals(Constants.DEVICE_FIXSTATUS)){
			deviceRecord.setOperateName(Constants.DEVICELIFE_FIX);//操作名称
			//若是解绑状态的维修之后司法所使用单位为空，与之对应的sim卡的司法所使用单位也为空
			if(Constants.DEVICE_RELEASESTATUS.equals(entity.getStatus())){
				entity.setUseSfsUnit(null);
				CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(entity.getPhoneNumber());
				simDevice.setUseSfsUnit(null);
				simDeviceService.update(simDevice);
			}
		}
		if(status.equals(Constants.DEVICE_BROKENSTATUS)){
			deviceRecord.setOperateName(Constants.DEVICELIFE_ABANDON);//操作名称
		}
		if(status.equals(Constants.DEVICE_NOTUSEDSTATUS)){
			deviceRecord.setOperateName(Constants.DEVICELIFE_RETURN);//操作名称
		}
		deviceRecordService.add(deviceRecord);
		entity.setStatus(status);// 状态修改
		dzjgsbDeviceService.update(entity);
		ajaxFormResult=AjaxFormResult.success("null");
		return SUCCESS;
	}
	//修改sim卡号
	public String changeSimNum(){
		String id=request.getParameter("id");
		String phoneNumber=request.getParameter("phoneNumber");
		entity=dzjgsbDeviceService.findById(id);
		String oPhoneNumber=entity.getPhoneNumber();
		entity.setPhoneNumber(phoneNumber);
		dzjgsbDeviceService.update(entity);
		//新号为已用状态,修改旧卡号为未用状态，，同时添加新号的使用记录
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
		simdeviceRecord.setUseSfsUnit(osim.getUseSfsUnit());//使用单位司法所
		simdeviceRecord.setUseUnit(osim.getUseUnit());//使用单位司法局
		simdeviceRecord.setJgDeviceNumber(entity.getDeviceNumber());
		deviceRecordService.add(simdeviceRecord);
		//添加电子监管设备使用记录——更换sim卡
		CcDeviceRecord dzjgdeviceRecord=new CcDeviceRecord();
		dzjgdeviceRecord.setDeviceNumber(entity.getDeviceNumber());//设置设备编号
		dzjgdeviceRecord.setSimNumber(phoneNumber);//设备卡号
		dzjgdeviceRecord.setOperateName(Constants.DEVICELIFE_REPLACESIM);//操作名称
		dzjgdeviceRecord.setOperateDate(new Date());//操作日期
		//dzjgdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
		dzjgdeviceRecord.setOperatePersion(getUserId());//操作人id
		dzjgdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
		dzjgdeviceRecord.setUseSfsUnit(entity.getUseSfsUnit());//使用单位司法所
		dzjgdeviceRecord.setUseUnit(entity.getUseUnit());//使用单位司法局
		dzjgdeviceRecord.setWearPersion(entity.getWearPerson());//佩戴人id
		deviceRecordService.add(dzjgdeviceRecord);
		//添加旧sim卡使用记录——被替换
		CcDeviceRecord osimdeviceRecord=new CcDeviceRecord();
		osimdeviceRecord.setDeviceNumber(osim.getDeviceNumber());//设置设备编号
		osimdeviceRecord.setSimNumber(oPhoneNumber);//设备卡号
		osimdeviceRecord.setOperateName(Constants.DEVICELIFE_REPLACEED);//操作名称
		osimdeviceRecord.setOperateDate(new Date());//操作日期
		//osimdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
		osimdeviceRecord.setOperatePersion(getUserId());//操作人id
		osimdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
		osimdeviceRecord.setUseUnit(osim.getUseUnit());//使用单位司法局
		deviceRecordService.add(osimdeviceRecord);
		ajaxFormResult = AjaxFormResult.success(null);
		return SUCCESS;
	}
	//导出
	private static final String[] headers = new String[] { "设备编号",
		"购买单位", "购买日期", "使用单位（司法局）","使用单位（司法所）", "sim卡号", "状态" };

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
		List res = dzjgsbDeviceService.findPaged(
				fillPageWithJQGridRequest(),
				parseJQGridRequest(searchs), Arrays.asList(head));
		for (int i = 0; i < res.size(); i++) {
			Object[] cc = (Object[]) res.get(i);
			LinkedList content = new LinkedList();
			content.add(i+1);//序号
			for (int j = 0; j < cc.length; j++) {
				if (j == 6) {
					content.add(dictionaryInfoService.findItemsMap("DZJGSBZT").get(cc[j]));
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
//导入

private String importFile = null;
public String getImportFile() {
	return importFile;
}
public void setImportFile(String importFile) {
	this.importFile = importFile;
}
public String importExcel() throws IOException {
	FileInputStream fis = null;
	HSSFWorkbook wb = null;
	List<CcDzjgsbDevice> dzjgsbDevices=new ArrayList<CcDzjgsbDevice>();
	try {
		File file = new File(FileUploadAction.getRealPath("jgsb/import",
				importFile) + File.separator + "CONTENT");
		fis = new FileInputStream(file);
		// 创建excel文件
		wb = new HSSFWorkbook(fis);
		int sheetSize = 1;// wb.getNumberOfSheets();
		for (int i = 0; i < sheetSize; i++) {
			HSSFSheet sheet = wb.getSheetAt(i);
			int lastRow = sheet.getLastRowNum();
			for (int j = 1; j <= lastRow; j++) {
				HSSFRow row = sheet.getRow(j);
				dzjgsbDevices.add(handRow(row));
			}
		}
		wb = null;
	} catch (Exception e) {
		e.printStackTrace();
		ajaxFormResult = new AjaxFormResult(false, "导入文件错误，请导入正确格式的文件！");
		return SUCCESS;
	} finally {
		if (fis != null) {
			try {
				fis.close();
			} catch (Exception e) {
			}
			fis = null;
		}
	}
	int index=0;//行号
	String message="";//错误提示
	List<String> info=new ArrayList();//错误提示集合
	if (dzjgsbDevices.size() > 0) {
		for(CcDzjgsbDevice d:dzjgsbDevices){//数据检验
			Boolean  canSave = true;
			index++;
			message="";
			if(organizationInfoService.checkByCname(d.getUseUnit())){//检查使用单位是否存在
				OrganizationInfo organizationInfo=organizationInfoService.findByProperty("cname",d.getUseUnit()).get(0);
				String useUnit=organizationInfo.getOrgId();
				d.setUseUnit(useUnit);
			}else{
				canSave=false;
				message+="第"+index+"行使用单位书写错误";
			}
			if(dzjgsbDeviceService.checkByDeviceNumber(d.getDeviceNumber())){//检查设备编号唯一性
			}else{
				message+="第"+index+"行设备编号已使用";
				canSave=false;
			}
			if(simDeviceService.checkSim(d.getPhoneNumber(),d.getUseUnit(),Constants.USEDEVICE_DZJGSBTYPE)){	//检查sim卡号是否可用
			}else{
				message+="第"+index+"行sim卡号不可用";
				canSave=false;
			}
			if(canSave){
				//修改SIM卡状态
				CcSimDevice ccSimDevice=simDeviceService.findSimDeviceByPhoneNumber(d.getPhoneNumber());
				ccSimDevice.setStatus(Constants.DEVICE_USEDSTATUS);
				ccSimDevice.setUseDeviceNumber(d.getDeviceNumber());
				simDeviceService.update(ccSimDevice);//修改sim卡状态为已用
				//新增sim卡使用记录
				CcDeviceRecord simdeviceRecord=new CcDeviceRecord();
				simdeviceRecord.setDeviceNumber(ccSimDevice.getDeviceNumber());//设置设备编号
				simdeviceRecord.setSimNumber(d.getPhoneNumber());//设备卡号
				simdeviceRecord.setOperateName(Constants.DEVICELIFE_USE);//操作名称
				simdeviceRecord.setOperateDate(new Date());//操作日期
				simdeviceRecord.setUseUnit(d.getUseUnit());
				simdeviceRecord.setJgDeviceNumber(d.getDeviceNumber());
				//simdeviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
				simdeviceRecord.setOperatePersion(getUserId());//操作人id
				simdeviceRecord.setOperateUnit(getCurOrgId());//操作人单位
				deviceRecordService.add(simdeviceRecord);
				/*
				 * 新增设备记录--电子监管设备购买
				 */
				CcDeviceRecord deviceRecord=new CcDeviceRecord();
				deviceRecord.setDeviceNumber(d.getDeviceNumber());//设备编号
				deviceRecord.setOperateName(Constants.DEVICELIFE_BUY);//操作名称
				deviceRecord.setOperateDate(new Date());//操作日期
				deviceRecord.setSimNumber(d.getPhoneNumber());//sim卡号
				deviceRecord.setUseUnit(d.getUseUnit());//使用单位司法局
				deviceRecord.setOperatePersion(getUserId());//操作人id
				deviceRecord.setOperateUnit(getCurOrgId());//操作人单位
				deviceRecordService.add(deviceRecord);
				
				dzjgsbDeviceService.add(d);
			}else{
				info.add(message);
			}
			
		}
		if (info.size() == 0)
			ajaxFormResult = new AjaxFormResult(true, "");
		else
			ajaxFormResult = new AjaxFormResult(false,
					StringUtils.join(info.toArray()));
	} else
		ajaxFormResult = new AjaxFormResult(false, "文件中没有记录。");
	return SUCCESS;
}
protected final CcDzjgsbDevice handRow(HSSFRow row) throws ParseException {
	int i=0;
	CcDzjgsbDevice dzjgsbDevice=new CcDzjgsbDevice();
	dzjgsbDevice.setBuyUnit(getCurOrgId());//购买单位就是登陆人所属单位
	dzjgsbDevice.setStatus(Constants.DEVICE_NOTUSEDSTATUS);//状态都是未用
	HSSFCell cell = null;
	// 设备编号
	cell = row.getCell(i++);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	dzjgsbDevice.setDeviceNumber(cell.getStringCellValue());
	// 购买日期
	i++;
	cell = row.getCell(i++);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	String strDate=cell.getStringCellValue();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=sdf.parse(strDate);
	dzjgsbDevice.setBuyDate(date);
	// 局使用单位
	cell = row.getCell(i++);
	String useUnitName=cell.getStringCellValue();
	
	dzjgsbDevice.setUseUnit(useUnitName);
	//sim卡号
	i++;
	cell = row.getCell(i++);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	String phoneNumber=cell.getStringCellValue();
	//检查sim卡号状态，格式和使用单位是否正确
	dzjgsbDevice.setPhoneNumber(phoneNumber);
	return dzjgsbDevice;
}

	//矫正管理模块找到可用设备组建可用设备下拉列表
	private List<Map<String, Object>> dto;
	public String queryEquipableMachine() {
		dto = dzjgsbDeviceService.queryEquipableMachine(getCurOrgId());
		return SUCCESS;
	}
	
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
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
	
	public DzjgsbDeviceService getDzjgsbDeviceService() {
		return dzjgsbDeviceService;
	}
	public void setDzjgsbDeviceService(DzjgsbDeviceService dzjgsbDeviceService) {
		this.dzjgsbDeviceService = dzjgsbDeviceService;
	}
	public DeviceFxryService getDeviceFxryService() {
		return deviceFxryService;
	}
	public void setDeviceFxryService(DeviceFxryService deviceFxryService) {
		this.deviceFxryService = deviceFxryService;
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
	public List<Map<String, Object>> getDto() {
		return dto;
	}
	public void setDto(List<Map<String, Object>> dto) {
		this.dto = dto;
	}
	public FXRYBaseInfoService getFXRYBaseInfoservice() {
		return FXRYBaseInfoservice;
	}
	public void setFXRYBaseInfoservice(FXRYBaseInfoService fXRYBaseInfoservice) {
		FXRYBaseInfoservice = fXRYBaseInfoservice;
	}
	public List getRlist() {
		return rlist;
	}
	public void setRlist(List rlist) {
		this.rlist = rlist;
	}
	

}
