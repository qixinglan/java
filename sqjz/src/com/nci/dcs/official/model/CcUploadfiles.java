package com.nci.dcs.official.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CcUploadfiles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_UPLOADFILES", schema = "SQJZ")
public class CcUploadfiles implements java.io.Serializable {

	// Fields

	private String id;
	private String parentId;
	private String filename;
	private String filepath;
	private String comments;
	private Date uploaddate;

	// Constructors

	/** default constructor */
	public CcUploadfiles() {
	}

	/** minimal constructor */
	public CcUploadfiles(String id) {
		this.id = id;
	}

	/** full constructor */
	public CcUploadfiles(String id, String parentId, String filename,
			String filepath, String comments, Date uploaddate) {
		this.id = id;
		this.parentId = parentId;
		this.filename = filename;
		this.filepath = filepath;
		this.comments = comments;
		this.uploaddate = uploaddate;
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

	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "FILENAME", length = 50)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILEPATH", length = 50)
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "COMMENTS", length = 100)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOADDATE", length = 7)
	public Date getUploaddate() {
		return this.uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

}