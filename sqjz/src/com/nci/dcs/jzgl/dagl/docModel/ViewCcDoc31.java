package com.nci.dcs.jzgl.dagl.docModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ViewCcDoc30 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIEW_CC_DOC_31", schema = "SQJZ")
public class ViewCcDoc31 implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String jdsnr;
	private String wsbh;
	
	/** default constructor */
	public ViewCcDoc31() {
	}
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "JDSNR", length = 60)
	public String getJdsnr() {
		return this.jdsnr;
	}

	public void setJdsnr(String jdsnr) {
		this.jdsnr = jdsnr;
	}

	@Column(name = "WSBH", length = 60)
	public String getWsbh() {
		return this.wsbh;
	}

	public void setWsbh(String wsbh) {
		this.wsbh = wsbh;
	}
}