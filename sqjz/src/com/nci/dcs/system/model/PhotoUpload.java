package com.nci.dcs.system.model;

import java.io.File;

public class PhotoUpload {
	private String id;
	
	private File photo;

	private String photoContentType;

	private String photoFileName;
	
	public PhotoUpload(File photo, String photoContentType, String photoFileName){
		this.photo = photo;
		this.photoContentType = photoContentType;
		this.photoFileName = photoFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
