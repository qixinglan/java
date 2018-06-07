package com.nci.dcs.official.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.base.action.FileUploadAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.official.model.Dataupload;
import com.nci.dcs.official.service.FileOperService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.em.model.CcWritAffix;
import com.nci.dcs.em.service.WritAfixxService;

public class FileOperAction extends BaseAction<Dataupload>{
	@Autowired
	private FileOperService service;
	
	@Autowired
	private WritAfixxService afixxService;
	
	private String filename ;
	
	private String msg ;
	
	private AjaxFormResult ajaxFormResult;
	
	private String importFile;
	
	private String downloadPath="xzyd/upload";

	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Throwable {
		String oper = request.getParameter("oper");
		getRequestPage();
//		List<SearchRule> result = JQGridSearchRuleParser.getSearchRules(this.jqgrid.getFilters());//获取所有searchRule
		if("download".equals(oper)){
			page.getCriterions().add(Restrictions.eq("filestatus", "1"));
			Bmb bmb=getUser().getWunit();
			if (bmb.isSj()) {
				page.getCriterions().add(
						Restrictions.eq("uploadorg", bmb.getBm()));
			} else if (bmb.isQxsfj()) {
				page.getCriterions().add(
						Restrictions.or(
								Restrictions.eq("uploadorg", bmb.getBm()),
								Restrictions.eq("uploadorg", bmb.getSupOrg())));
			} else if (bmb.isSfs()) {
				page.getCriterions().add(
						Restrictions.or(
								Restrictions.eq("uploadorg", bmb.getBm()),
								Restrictions.or(
										Restrictions.eq("uploadorg",
												bmb.getSupOrg()),
										Restrictions.eq("uploadorg",
												this.rootOrgId))));
			}
/*			SearchRule searchRule = new SearchRule();
			searchRule.setField("filestatus");
			searchRule.setData("1");
			searchRule.setOp("eq");
			result.add(searchRule);*/
		}else{
			page.getCriterions().add(Restrictions.eq("uploadorg", getUser().getWunit().getBm()));
			/*SearchRule searchRule = new SearchRule();
			searchRule.setField("uploadorg");
			searchRule.setData(getUser().getWunit().getBm());
			searchRule.setOp("eq");
			result.add(searchRule);*/
		}
		//去掉page内的查询条件
		/*List<Criterion> criterions = page.getCriterions();
		page.getCriterions().removeAll(criterions);
		//日期查询条件补充时分秒
		String scsj="";
		String field="";
		String op="";
		for(SearchRule temp:result){
			field = temp.getField();
			if(field!=null && ("uploaddate").equals(field)){
				op = temp.getOp();
				if(op!=null && ("gt").equals(op)){//上传日期大于一天开始时间零点
					scsj = temp.getData();
					temp.setData(scsj + " 00:00:00");
				}else if(op!=null && ("lt").equals(op)){//上传日期小于一天的最后时间24点
					scsj = temp.getData();
					temp.setData(scsj + " 24:00:00");
				}
			}
		}*/
		service.findPaged(page);
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
		String ids = request.getParameter("ids");
		entity = service.get(ids);
		String path = PathUtils.getRealPath() + "upload"+File.separator+ FilenameUtils.normalize(downloadPath);
		path = getPath(path);
		
		FileUtils.deleteDirectory(new File(path));
		service.delete(ids);
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

	public String upload() throws IOException{
		String filePath = FileUploadAction.getRealPath(downloadPath,
				importFile) + File.separator + "CONTENT";
		File infofile = new File(FileUploadAction.getRealPath(downloadPath,
				importFile) + File.separator + "INFO");
		List<String> infos = FileUtils.readLines(infofile);
		
		//BASE64Decoder decoder = new BASE64Decoder();

		entity.setId(CommonUtils.uuid());
		entity.setUploaddate(new Date());
		entity.setUploadperson(getUser().getName());
		entity.setUploadorg(getUser().getWunit().getBm());
		entity.setFilestatus("0");
		entity.setDownloadtime(0);
		entity.setFilepath("/"+downloadPath+"/");
		entity.setFilename(infos.get(1));
		entity.setRealname(importFile);
		
		service.create(entity);
		
		File file=new File(filePath);
		String fileData=FileUtils.readFileToString(file);
		CcWritAffix affix=new CcWritAffix();
		affix.setWritId(entity.getRealname());
		affix.setOrgId(getUser().getWunit().getBm());
		affix.setPersonId(getUser().getName());
		affix.setUploadTime(new Date());
		affix.setWritName(infos.get(1));
		affix.setAffix(fileData);
		
		afixxService.create(affix);
		
		ajaxFormResult = new AjaxFormResult(true, "");
		msg = "文件上传成功!";
		return SUCCESS;
	}
	
	public String fileoper(){
		String id = request.getParameter("id");
		String oper = request.getParameter("oper");
		
		entity = service.get(id);
		if(entity!=null){
			if("stop".equals(oper)){
				entity.setFilestatus("2");
				entity.setUploadperson(getUser().getName());
			}else{
				entity.setFilestatus("1");
				entity.setUploadperson(getUser().getName());
			}
		}
		service.create(entity);
		return SUCCESS;
	}
	
	public String filedDownload() throws FileNotFoundException{
		String id = request.getParameter("id");
		entity = service.get(id);
		setFilename(entity.getFilename());
		String path = PathUtils.getRealPath() + "upload"+File.separator+ FilenameUtils.normalize(downloadPath);
		path = getPath(path);
		File file = new File(path);
		if(file.exists()){
			entity.setDownloadtime(entity.getDownloadtime()+1);
			service.create(entity);
			return SUCCESS;
		}else {
			throw new FileNotFoundException("下载失败,文件不存在，请联系管理员重新上传！");
		}
	}
	
	public String checkFile(){
		String id = request.getParameter("id");
		entity = service.get(id);
		setFilename(entity.getFilename());
		String path = PathUtils.getRealPath() + "upload"+File.separator+ FilenameUtils.normalize(downloadPath);
		path = getPath(path);
		
		if(!new File(path).exists()){
			try{
			saveFile(entity,path);
			} catch(IOException e){
				msg = "下载失败,文件不存在，请联系管理员重新上传！";
			}			
		}
		return SUCCESS;
	}
	
	public void saveFile(Dataupload entity,String path) throws IOException{
		
		CcWritAffix affix=afixxService.get(entity.getRealname());
		String strContent=affix.getAffix();
		path=path+ File.separator + "CONTENT";
		File file=new File(path);
		FileUtils.writeStringToFile(file, strContent);		
	}
	
	public InputStream getInputStream() throws FileNotFoundException{
		String path =  "/upload"+File.separator+ FilenameUtils.normalize(downloadPath);
		path = getPath(path);
		return ServletActionContext.getServletContext().getResourceAsStream(path+File.separator+"CONTENT");
	}
	
	private String getPath(String path){
		return path + File.separator + entity.getRealname().substring(0, 2) + File.separator + entity.getRealname().substring(2, 4) + File.separator + entity.getRealname();
	}
	public String getImportFile() {
		return importFile;
	}

	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	
	public String getDownFileName() throws UnsupportedEncodingException {
		ServletActionContext.getResponse().setHeader("charset", "ISO8859-1");
		return new String(filename.getBytes(),"ISO8859-1");
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return filename;
	}

	public FileOperService getService() {
		return service;
	}

	public void setService(FileOperService service) {
		this.service = service;
	}
	
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
