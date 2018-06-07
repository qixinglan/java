package com.nci.dcs.jzgl.dagl.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.jzgl.dagl.fxrydochanler.Encapsulation;
//import com.nci.dcs.jzgl.dagl.docModel.ViewCcDoc8;
import com.nci.dcs.jzgl.dagl.fxrydochanler.FxryDocHandlerFactory;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYDocExportService;
import com.nci.dcs.jzgl.dagl.util.FXRYEditConfig;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

public class FXRYAction extends BaseAction<FXRYBaseinfo> {
	@Autowired
	private FXRYBaseInfoService fxryBaseInfoService;
	
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	
	private String fileName;
	private InputStream targetFile;

	public FXRYAction() {
		super(FXRYBaseinfo.class);
	}

	protected AjaxFormResult ajaxFormResult;

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	@Autowired
	protected FXRYBaseInfoService service;

	@Override
	public String list() throws Throwable {
		service.findPaged(this.getRequestPage());
		return "success";
	}

	public String getByIdOrCode() throws Throwable {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		try {
			if (!StringUtils.isBlank(id)) {
				this.entity = service.get(id);
			} else if (!StringUtils.isBlank(code)) {
				this.entity = service.getByCode(code);
			} else {
				this.entity = new FXRYBaseinfo();
				Calendar cal = Calendar.getInstance();
				this.entity.setReportTime(cal.getTime());
				FXRYReportOrg reportOrg = new FXRYReportOrg();
				cal.add(Calendar.DATE, 3);
				reportOrg.setReportTime(cal.getTime());
				this.entity.setReport(reportOrg);
			}
			return "success";
		} catch (Exception e) {
			return "none";
		}

	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
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
		return "add";
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

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return "success";
	}

	FXRYEditConfig editConfig;

	public String getOrgEditConfig() {
		String target = request.getParameter("target");
		String id = request.getParameter("id");
		try {
			Bmb org = getCurOrg();
			this.entity = service.get(id);
			if (org.isQxsfj()) {
				editConfig = getQXSFJEditConfig(target);
			} else if (org.isSfs()) {
				editConfig = getSFSEditConfig(target);
			}
		} catch (Exception e) {
		}
		if (editConfig == null) {
			editConfig = new FXRYEditConfig();
		}
		return "success";
	}

	private FXRYEditConfig getSFSEditConfig(String target) {
		FXRYEditConfig config = new FXRYEditConfig();
		if ("edit".equals(target)) {
			if (this.entity.getResponseOrg().equals(this.getCurOrgId())) {
				// 当前维护机构拥有全部修改权限
				config = new FXRYEditConfig(true);
			}
		} else if ("accept".equals(target)) {// 司法局登记 司法所接收
			config.setMoreinfo(true);
			config.setLegal(true);
			config.setExecuteinfo(true);
			config.setTeam(true);
		} else if ("moveIn".equals(target)) {// 司法所转入
			config.setTeam(true);
		} else if ("release".equals(target)) {// 司法所转入
			config.setRelease(true);
		}
		return config;
	}

	private FXRYEditConfig getQXSFJEditConfig(String target) {
		return new FXRYEditConfig(true);
	}

	public FXRYEditConfig getEditConfig() {
		return editConfig;
	}
	
	@Autowired
	private FXRYDocExportService fxryDocExportService;

	public String exportFxryDoc() {
		// 获得服刑人员id
		String fxry_Id = request.getParameter("fxry_Id");
		// 获得服刑人员档案编号
		String fxry_dabh = request.getParameter("fxry_dabh");
		//获取当前用户
		User user=getUser();
		//获取当前用户所在的机构
		OrganizationInfo org=getUserOrg();
		CreateFileUtil util = CreateFileUtil.getInstance();
		//第一次加载时，把具体罪名赋值到Encapsulation类中
		if(!Encapsulation.ACCUSATION.containsKey("JTZM")){
			Map<String, List<DictionaryKeyValue>> dictionaryMap=dictionaryInfoService.getDict();
			List<DictionaryKeyValue> jtzm=dictionaryMap.get("JTZM");
			if(jtzm!=null&&jtzm.size()>0){
				Map<String,String> keyValue=new HashMap<String,String>();
				for(DictionaryKeyValue dict:jtzm){
					keyValue.put(dict.getCode(), dict.getName());
				}
				Encapsulation.ACCUSATION.put("JTZM", keyValue);
				Encapsulation.PropertiesToMap();
			}
		}
		try {
			//根据档案编号，选择合适的dochandler
			Map<String,String> root=FxryDocHandlerFactory.getFxryDocHandlerInstance(fxry_dabh).Execute(fxry_Id,fxryDocExportService);
			//当前登录人
			root.put("currentUser", user.getName());
			//当前登录人所在机构
			root.put("currentOrg", org.getCname());
			String damc=Encapsulation.docMapNames.get(fxry_dabh);
			String frxry_name=root.get("name")==null?"":root.get("name");
			fileName = util.createFxryDoc(root, fxry_dabh,frxry_name,damc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public InputStream getTargetFile() {
		CreateFileUtil util = CreateFileUtil.getInstance();
		try {
			return util.getFileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		// 编码转换，否则下载的文件中文乱码
		this.fileName = new String(fileName.getBytes("ISO-8859-1"), "utf-8");
	}
	/**
	 * 获取登录用户
	 * @return
	 */
	protected User getUser(){
		User user = LoginInfoUtils.getUser(request.getSession());
		return user;
	}
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private OrganizationInfo getUserOrg() {
		User user = getUser();
		OrganizationInfo org;
		if (user != null) {
			org = organizationInfoService.get(user.getWunit().getBm());
		} else {
			org = organizationInfoService.findRoot();
		}
		return org;
	}
}
