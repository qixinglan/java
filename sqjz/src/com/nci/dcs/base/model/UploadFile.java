package com.nci.dcs.base.model;

import java.io.File;

public class UploadFile {
	private String[] fileContentType;
	private File[] file;
	private String[] fileFileName;
	
	public int size(){
		return file.length;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public UploadFile(){}
	public UploadFile(File[] file,String[] fileContentType, 
			String[] fileFileName) {
		super();
		this.fileContentType = fileContentType;
		this.file = file;
		this.fileFileName = fileFileName;
	}
}
