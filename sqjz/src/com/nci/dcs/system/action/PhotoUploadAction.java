package com.nci.dcs.system.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import oracle.net.ano.Service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.fingerprint.model.CcFxryTupian;
import com.nci.dcs.jzgl.fingerprint.service.FxryTuPianService;
import com.nci.dcs.system.model.PhotoUpload;
import com.nci.dcs.system.service.PhotoUploadServcie;
import com.opensymphony.xwork2.ActionSupport;

public class PhotoUploadAction extends ActionSupport {

	@Autowired
	private PhotoUploadServcie service;

	private AjaxFormResult ajaxFormResult;

	private String id;

	private File photo;

	private String photoContentType;

	private String photoFileName;

	private InputStream inputStream;
	@Autowired
	private FxryTuPianService tuPianService;
	@Autowired
	private FXRYBaseInfoService baseInfoService;
	// 人员指纹编号
	private String upFPN;
	// 人员照片ID
	private String upPIC;
	// 人员ID
	private String upFID;

	public String upload() throws Exception {
		try {
			// 修改以符合集群方式，文件不再放在本地，放在数据库
			CcFxryTupian tuPian = null;
			if (CommonUtils.isNull(upFPN)) {
				tuPian = new CcFxryTupian();
				tuPian.setId(UUID.randomUUID().toString().replace("-", ""));
			} else {
				tuPian = tuPianService.get(upFPN);
			}
			tuPian.setFacePic(service.getBytes(photo));
			tuPianService.create(tuPian);
			String personId = tuPian.getId();
			String photoFileName = personId + ".jpeg";
			// 删除以前的照片
			if (!CommonUtils.isNull(upPIC)) {
				service.deleteFile(upPIC);
			}
			String newId = service.saveFile(new PhotoUpload(photo,
					photoContentType, photoFileName));
			if (upFID != null && !"".equals(upFID)) {
				FXRYBaseinfo baseinfo = baseInfoService.getById(upFID);
				if (baseinfo != null) {
					baseinfo.setPicture(newId);
					baseinfo.setFingerPrintNo(personId);
					baseInfoService.updateFxryPic(baseinfo);
					String reslut = personId + ";" + newId;
					ajaxFormResult = AjaxFormResult.success(reslut);
				}
			} else {
				ajaxFormResult = AjaxFormResult.error("系统中无该人员！");
			}
		} catch (Exception e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return SUCCESS;
	}

	public String view() {
		try {
			boolean flag = service.checkFile(id);
			if (!flag) {
				FXRYBaseinfo baseinfo = baseInfoService.getById(upFID);
				CcFxryTupian tuPian = tuPianService.get(baseinfo
						.getFingerPrintNo());
				String photoFileName = id + ".jpeg";
				File photo = service.createTempFile(tuPian.getFacePic(),
						photoFileName);
				PhotoUpload photoUpload = new PhotoUpload(photo, "image/jpeg",
						photoFileName);
				service.saveFile(photoUpload, id);
			}
			PhotoUpload photo = service.get(id);
			this.photoContentType = photo.getPhotoContentType();
			this.photoFileName = photo.getPhotoFileName();
			this.inputStream = FileUtils.openInputStream(photo.getPhoto());
			return "success";
		} catch (Exception e) {
			return "none";
		}

	}

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getUpFPN() {
		return upFPN;
	}

	public void setUpFPN(String upFPN) {
		this.upFPN = upFPN;
	}

	public String getUpPIC() {
		return upPIC;
	}

	public void setUpPIC(String upPIC) {
		this.upPIC = upPIC;
	}

	public String getUpFID() {
		return upFID;
	}

	public void setUpFID(String upFID) {
		this.upFID = upFID;
	}
}
