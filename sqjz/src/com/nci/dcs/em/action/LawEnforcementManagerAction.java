package com.nci.dcs.em.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.em.model.CcLawEnforcement;
import com.nci.dcs.em.service.LawEnforcementManagerService;

import edu.emory.mathcs.backport.java.util.Arrays;

public class LawEnforcementManagerAction  extends BaseAction<CcLawEnforcement>{

	public List<CcLawEnforcement> lawList;
	public CcLawEnforcement lawEn ;
	private String fileName;
	private InputStream targetFile;
	private boolean result;
 	
	
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private LawEnforcementManagerService service;
	
	
	
	public LawEnforcementManagerService getService() {
		return service;
	}

	public void setService(LawEnforcementManagerService service) {
		this.service = service;
	}

	@Override
	public String add() throws Throwable {
		if("add".equals(request.getParameter("oper"))){
			service.create(entity);
			return "add";
		}
		if("edit".equals(request.getParameter("oper"))){
			this.view();
		}
		return "success";
	}
	
	public String checkBeforeAdd(){
		String id = request.getParameter("id");
		String equnumber = request.getParameter("equnumber");
		List res ;
		if(id != null){
			entity = service.get(id);
			if(equnumber.equals(entity.getEqunumber())){
				result = true;
				return SUCCESS;
			}
		}
		
		res = service.findBySql("select 1 from CC_LAW_ENFORCEMENT where EQUNUMBER =?", equnumber);
		
		if(res.size()==0){
			result = true;
		}else{
			result = false;
		}
		return SUCCESS;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String[] ids = request.getParameter("id").split(",");
		for(int i=0;i<ids.length;i++)
			service.delete(ids[i]);
		return SUCCESS;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
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
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Throwable {
		lawList = service.find();
		return "success";
		
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		lawEn = service.get(id);
		return "success";
	}

	public List<CcLawEnforcement> getLawList() {
		return lawList;
	}

	public void setLawList(List<CcLawEnforcement> lawList) {
		this.lawList = lawList;
	}

	public String search(){
		service.findPaged(this.getRequestPage());
		return "success";
	}
	
	private final String[] headers = new String[] {"序号", "设备编号", "购进日期", "设备状态","领用单位","领用日期" };
	
	public String createFile() throws UnsupportedEncodingException{
		CreateFileUtil util = CreateFileUtil.getInstance();
		String sortName = request.getParameter("sortName");
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		
		//service.findByfilter(entity1, entity2);
		List res = service.find(sortName,null);
		
		for(int i=0;i<res.size();i++){
			Object[] cc = (Object[])res.get(i);
			LinkedList content = new LinkedList();
			content.add(i);
			for(int j=0;j<cc.length;j++){
				if(cc[j]!=null&&cc[j] instanceof Date){
					cc[j] = sdf.format(cc[j]);
				}
				if (j == 2) {
					content.add("02".equals(cc[j]) ? "未用" : "03"
							.equals(cc[j]) ? "在用" : "停用");
					continue;
				}
				content.add(cc[j]);
			}
			contentList.add(content);
		}
		
		try {
			fileName = util.create(headTable, contentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public InputStream getTargetFile() {
		CreateFileUtil util =CreateFileUtil.getInstance();
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
