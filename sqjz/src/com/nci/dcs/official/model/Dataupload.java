package com.nci.dcs.official.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Dataupload entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_DATAUPLOAD", schema = "SQJZ")
public class Dataupload implements java.io.Serializable {

	// Fields

	private String id;
	private String filename;
	private String filetype;
	private String filestatus;
	private String uploadperson;
	private Date uploaddate;
	private Integer downloadtime;
	private String uploadorg;
	private File file;
	private String filepath;
	private String realname;

	

// Constructors
	
	@Column(name="REALNAME")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
@Column(name="FILEPATH")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Transient
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Column(name="UPLOADORG")
	public String getUploadorg() {
		return uploadorg;
	}

	public void setUploadorg(String uploadorg) {
		this.uploadorg = uploadorg;
	}

	/** default constructor */
	public Dataupload() {
	}

	/** minimal constructor */
	public Dataupload(String id) {
		this.id = id;
	}

	/** full constructor */
	public Dataupload(String id, String filename, String filetype,
			String filestatus, String uploadperson, Date uploaddate,
			Integer downloadtime,String uploadorg,String filepath) {
		this.id = id;
		this.filename = filename;
		this.filetype = filetype;
		this.filestatus = filestatus;
		this.uploadperson = uploadperson;
		this.uploaddate = uploaddate;
		this.downloadtime = downloadtime;
		this.uploadorg = uploadorg;
		this.filepath = filepath;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "FILENAME", length = 150)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILETYPE", length = 32)
	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	@Column(name = "FILESTATUS", length = 32)
	public String getFilestatus() {
		return this.filestatus;
	}

	public void setFilestatus(String filestatus) {
		this.filestatus = filestatus;
	}

	@Column(name = "UPLOADPERSON", length = 60)
	public String getUploadperson() {
		return this.uploadperson;
	}

	public void setUploadperson(String uploadperson) {
		this.uploadperson = uploadperson;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPLOADDATE", length = 7)
	public Date getUploaddate() {
		return this.uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	@Column(name = "DOWNLOADTIME", precision = 22, scale = 0)
	public Integer getDownloadtime() {
		return this.downloadtime;
	}

	public void setDownloadtime(Integer downloadtime) {
		this.downloadtime = downloadtime;
	}

}