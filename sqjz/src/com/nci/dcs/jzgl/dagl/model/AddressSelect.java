package com.nci.dcs.jzgl.dagl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * CcAreaInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_AREA_INFO", schema = "SQJZ")
public class AddressSelect implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private Long areaLevel;
	private Long zxs;
	private Long zgx;
	private String parentid;

	// Constructors

	/** default constructor */
	public AddressSelect() {
	}

	/** full constructor */
	public AddressSelect(String name, String code, Long areaLevel, Long zxs,
			Long zgx, String parentid) {
		this.name = name;
		this.code = code;
		this.areaLevel = areaLevel;
		this.zxs = zxs;
		this.zgx = zgx;
		this.parentid = parentid;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	@JSON(name="code")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 300)
	@JSON(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 6)
	@JSON(serialize=false, deserialize=false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "AREA_LEVEL", precision = 10, scale = 0)
	@JSON(serialize=false, deserialize=false)
	public Long getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(Long areaLevel) {
		this.areaLevel = areaLevel;
	}

	@Column(name = "ZXS", precision = 10, scale = 0)
	@JSON(serialize=false, deserialize=false)
	public Long getZxs() {
		return this.zxs;
	}

	public void setZxs(Long zxs) {
		this.zxs = zxs;
	}

	@Column(name = "ZGX", precision = 10, scale = 0)
	@JSON(serialize=false, deserialize=false)
	public Long getZgx() {
		return this.zgx;
	}

	public void setZgx(Long zgx) {
		this.zgx = zgx;
	}

	@Column(name = "PARENTID", length = 32)
	@JSON(serialize=false, deserialize=false)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}