package com.nci.dcs.official.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.official.model.CcDynamicreport;
import com.nci.dcs.official.model.CcDynamicreportreply;
import com.nci.dcs.official.service.DynamicreportService;
import com.nci.dcs.official.service.DynamicreportreplyService;

public class DynamicreportAction extends BaseAction<CcDynamicreport>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1387969095004097350L;
	@Autowired
	private DynamicreportService service;
	@Autowired
	private DynamicreportreplyService dynamicreportreplyService;
	
	public CcDynamicreport data;
	private AjaxFormResult ajaxFormResult;
	private String downloadPath="dtgl/attach";
	
	private String importFile;
	
	public String getImportFile() {
		return importFile;
	}

	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	public DynamicreportService getService() {
		return service;
	}

	public void setService(DynamicreportService service) {
		this.service = service;
	}

	private String filename;
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String list() throws Throwable {
		getRequestPage();
		String oper = request.getParameter("oper");
		String orgId = getUser().getWunit().getBm();
		String status=request.getParameter("status");
		if(oper!=null && ("dtglView").equals(oper)){//动态信息查看进入
			page.getCriterions().add(Restrictions.eq("receiveOrgId", orgId));
			page.getCriterions().add(Restrictions.in("status", new String[]{"1","3"}));
			if(!CommonUtils.isNull(status)){
				page.getCriterions().add(Restrictions.eq("status", status));
			}
		}else{
			page.getCriterions().add(Restrictions.eq("recordOrgId", orgId));
		}
		service.findPaged(page,entity);
		return SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		entity = service.get(id);
		if("3".equals(entity.getStatus())){
			CcDynamicreportreply reply=null;
			if(null==entity.getReply()){
				reply=new CcDynamicreportreply();
				reply.setId(CommonUtils.uuid());
				entity.setReply(reply);
			}else{
				reply=entity.getReply();
			}
			Date date = new Date();
			reply.setReplydate(date);
			reply.setReplyPersonId(getUserId());
			reply.setReplyPersonName(getUser().getName());
			reply.setReport(entity);
			entity.setStatus("1");
			dynamicreportreplyService.create(reply);
			service.create(entity);
		}
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
		String ids = request.getParameter("id");
//		String[] idArr = ids.split(",");
//		for(String id:idArr){
//			entity = service.get(id);
//			String path = PathUtils.getRealPath() + "upload"+File.separator+ FilenameUtils.normalize(downloadPath);
//			List<CcUploadfiles> files =  entity.getUploadfiles();
//			for(CcUploadfiles file : files){
//				path = getPath(path,file.getFilename());
//				FileUtils.deleteDirectory(new File(path));
//			}
//		}
		service.delete(ids);
		//删除附件信息
		String affixId = request.getParameter("affixId");
		if(affixId!=null && !("").equals(affixId)){
			delFile(affixId);
		}
		return SUCCESS;
	}
	
	/**
	 * @name 删除附件信息
	 * @param affixId
	 * @throws Throwable
	 * @author caolj
	 * @date 2014年9月23日 上午9:46:11
	 * @message:
	 */
	public void delFile(String affixId) throws Throwable{
    	String realPath = getRealPath(downloadPath, affixId);
    	FileUtils.deleteDirectory(new File(realPath));
	}
	
	public static String getRealPath(String path, String id) {
		String realPath = PathUtils.getRealPath() + File.separator + "upload"
				+ File.separator + FilenameUtils.normalize(path);
		realPath = realPath + File.separator + id.substring(0, 2)
				+ File.separator + id.substring(2, 4) + File.separator + id;
		return realPath;
	}
	
	/**
	 * @name 上报信息 
	 * @return
	 * @author caolj
	 * @date 2014年9月19日 下午2:38:24
	 * @message:
	 */
	public String commitSbxx(){
		String id = request.getParameter("id");
		CcDynamicreport entity = service.get(id);
		entity.setStatus("3");//已上报
		entity.setReporttime(new Date());
		service.create(entity);
		return SUCCESS;
	}
	
	/**
	 * @name 查看动态信息上报信息 
	 * @return
	 * @author caolj
	 * @date 2014年9月19日 下午2:38:24
	 * @message:
	 */
	public String search(){
		String id = request.getParameter("id");
		this.entity = service.get(id);
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
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Throwable {
		String id = request.getParameter("id");
		data = service.get(id);
		return SUCCESS;
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
	//动态上报
	public String report() throws IOException{
		try{
			String id = request.getParameter("id");
			if(id!=null && !("").equals(id)){
				service.delete(id);
			}
			Date date = new Date();
			entity.setId(CommonUtils.uuid());
			//上传文件
//			if(!"".equals(importFile)){
//				String filePath = FileUploadAction.getRealPath(downloadPath,
//						importFile) + File.separator + "CONTENT";
//				File infofile = new File(FileUploadAction.getRealPath(downloadPath,
//						importFile) + File.separator + "INFO");
//				List<String> infos = FileUtils.readLines(infofile);
//				List<CcUploadfiles> uploadfiles = new ArrayList<CcUploadfiles>();
//				
//				CcUploadfiles uploadfile = new CcUploadfiles();
//				uploadfile.setFilename(importFile);
//				uploadfile.setId(CommonUtils.uuid());
//				uploadfile.setUploaddate(date);
//				uploadfile.setParentId(entity.getId());
//				//uploadfile.setFilepath(filepath);设置文件位置
//				uploadfiles.add(uploadfile);
//				
//				entity.setUploadfiles(uploadfiles);
//			}
			entity.setStatus("2");
			entity.setCreatedate(date);
			entity.setCreater(getUser().getId());
			entity.setCreatername(getUser().getName());
//			entity.setReporttime(date);
			entity.setRecordOrgId(getUser().getWunit().getBm());
			entity.setReceiveOrgId(getUser().getWunit().getSupOrg());
			service.create(entity);
			
			ajaxFormResult = new AjaxFormResult(true, "保存成功");
		}catch(Exception e){
			ajaxFormResult = AjaxFormResult.error("请稍后重试");
			logger.error("保存动态信息出错", e);
		}
		return SUCCESS;
	}
//	public String edit(){
//		
//		Date date = new Date();
//		CcDynamicreport report = service.get(entity.getId());
//		List<CcUploadfiles> uploadfiles = report.getUploadfiles();
//		//判断附件是否重复
//		if(uploadfiles!=null){
//			for(int i=0;i<uploadfiles.size();i++){
//				boolean ishas = false;
//				CcUploadfiles file = uploadfiles.get(i);
//				for(int j=0;j<entity.getFilename().size();j++){
//					if(file.getFilename().equals(entity.getFilename().get(j))){
//						ishas = true;
//					}
//					if(!ishas){
//						
//					}
//				}
//			}
//		}
//		if(entity.getFilename()!=null){
//			for(int i=0;i<entity.getFilename().size();i++){
//				CcUploadfiles uploadfile = new CcUploadfiles();
//				uploadfile.setFilename(entity.getFilename().get(i));
//				uploadfile.setId(CommonUtils.uuid());
//				uploadfile.setUploaddate(date);
//				uploadfile.setParentId(entity.getId());
//				//uploadfile.setFilepath(filepath);设置文件位置
//				uploadfiles.add(uploadfile);
//			}
//		}
//		entity.setUploadfiles(uploadfiles);
//		entity.setCreatedate(date);
//		entity.setCreater(getUser().getId());
//		entity.setReporttime(date);
//		entity.setRecordOrgId(getUser().getWunit().getBm());
//		entity.setReceiveOrgId(getUser().getWunit().getBm());
//		service.create(entity);
//		
//		return SUCCESS;
//	}
	//动态回复
//	public String reply(){
//		Date date = new Date();
//		CcDynamicreportreply reply = new CcDynamicreportreply();
//		reply.setId(CommonUtils.uuid());
//		
//		List<CcUploadfiles> uploadfiles = new ArrayList<CcUploadfiles>();
//		if(entity.getFilename()!=null){
//			for(int i=0;i<entity.getFilename().size();i++){
//				CcUploadfiles uploadfile = new CcUploadfiles();
//				uploadfile.setFilename(entity.getFilename().get(i));
//				uploadfile.setId(CommonUtils.uuid());
//				uploadfile.setUploaddate(date);
//				uploadfile.setParentId(reply.getId());
//				//uploadfile.setFilepath(filepath);设置文件位置
//				uploadfiles.add(uploadfile);
//			}
//		}
//	
//		reply.setReport(entity);
//		reply.setReplydate(date);
//		reply.setReplyPersonId(getUserId());
//		reply.setUploadfiles(uploadfiles);
//		reply.setRelyContent(entity.getReply().getRelyContent());
//		
//		entity = service.get(entity.getId());
//		entity.setStatus("3");
//		entity.setReply(reply);
//		service.create(entity);
//		
//		return SUCCESS;
//	}
	//附件下载
	public String downloadAttach(){
		setFilename(request.getParameter("filename"));
		String path = ServletActionContext.getServletContext().getRealPath("/upload/file");
		File file = new File(path+"\\"+filename);
		return SUCCESS;
	}
	public InputStream getInputStream() throws FileNotFoundException{
		String dir = ServletActionContext.getServletContext().getRealPath("/upload/file");
		return new FileInputStream(new File(dir+"\\"+filename));
	}
	//附件上传
	public String upAttach(){
		return null;
	}
	//删除附件
	public String delattachAttach(){
		return null;
	}
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}
	private String getPath(String path,String filename){
		return path + File.separator + filename.substring(0, 2) + File.separator + filename.substring(2, 4) + File.separator + filename;
	}

}
