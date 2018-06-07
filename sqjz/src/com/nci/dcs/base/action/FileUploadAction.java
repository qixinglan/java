package com.nci.dcs.base.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import com.nci.dcs.base.model.UploadFile;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.PathUtils;

public class FileUploadAction extends BaseAction<UploadFile> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4829790931177533234L;

	private AjaxFormResult ajaxFormResult;
	private String limitSize;
	
	private static String CONTENT = "CONTENT";
	private static String INFO = "INFO";

	private String spath;

	public String upload() throws Exception {
		saveFile(getSpath(), entity);
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String id = request.getParameter("id");
		deleteFile(getSpath(), id);
		ajaxFormResult = AjaxFormResult.success(id);
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

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	public void deleteFile(String path, String id) {
		try {
			String realPath = getRealPath(path, id);
			FileUtils.deleteDirectory(new File(realPath));
		} catch (IOException e) {

		}
	}

	public String info() throws Throwable {
		try {
			String id = request.getParameter("id");
			String realPath = getRealPath(getSpath(), id);
			String info = realPath + File.separator + INFO;
			File infoFile = new File(info);
			List<String> lines = FileUtils.readLines(infoFile);
			ajaxFormResult = AjaxFormResult.success(lines.get(1));
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.success("");
		}
		return SUCCESS;
	}

	private String contentType;

	private String fileName;

	private InputStream inputStream;

	public String download() {
		try {
			String id = request.getParameter("id");
			UploadFile file = get(getSpath(), id);
			this.contentType = file.getFileContentType()[0];
			this.fileName = file.getFileFileName()[0];
			this.inputStream = FileUtils.openInputStream(file.getFile()[0]);
			return SUCCESS;
		} catch (Exception e) {
			return NONE;
		}

	}

	protected String saveFile(String path, UploadFile entity)
			throws Exception {
		String realPath;
		String newFileName;
		String info;
		File saveFile;
		File infoFile;
		ArrayList<String> ids = new ArrayList<String>();
		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < entity.size(); i++) {
			ids.add(CommonUtils.uuid());
			realPath = getRealPath(path, ids.get(i));
			newFileName = realPath + File.separator + CONTENT;
			saveFile = new File(newFileName);
			//大小验证
			double fileSize = entity.getFile()[i].length()/1024.0/1024.0;
			if(null!=getLimitSize()&&!"".equals(getLimitSize())){
				if(fileSize > Double.parseDouble(getLimitSize())){
					ajaxFormResult = new AjaxFormResult(false,"文件超过允许的大小" + getLimitSize() + "M" );
				}
			}
			FileUtils.copyFile(entity.getFile()[i], saveFile);
			info = realPath + File.separator + INFO;
			infoFile = new File(info);

			lines = new ArrayList<String>();
			lines.add(entity.getFileContentType()[i]);
			lines.add(entity.getFileFileName()[i]);
			FileUtils.writeLines(infoFile, lines);
			lines.clear();
		}
		ajaxFormResult = new AjaxFormResult(true, JSONArray.fromObject(
				ids.toArray()).toString());
		return SUCCESS;
	}

	public static String getRealPath(String path, String id) {
		String realPath = PathUtils.getRealPath() + File.separator + "upload"
				+ File.separator + FilenameUtils.normalize(path);
		realPath = realPath + File.separator + id.substring(0, 2)
				+ File.separator + id.substring(2, 4) + File.separator + id;
		return realPath;
	}

	public UploadFile get(String path, String id) throws IOException {
		String realPath = getRealPath(path, id);
		String file = realPath + File.separator + CONTENT;
		String info = realPath + File.separator + INFO;
		File infoFile = new File(info);
		List<String> lines = FileUtils.readLines(infoFile);
		UploadFile photo = new UploadFile(new File[] { new File(file) },
				new String[] { lines.get(0) }, new String[] { lines.get(1) });
		return photo;
	}

	public String getSpath() {
		return spath;
	}

	public void setSpath(String spath) {
		this.spath = spath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String fileContentType) {
		this.contentType = fileContentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() throws UnsupportedEncodingException {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(String limitSize) {
		this.limitSize = limitSize;
	}
}
