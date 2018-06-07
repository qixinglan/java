package com.nci.dcs.monitor.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CcMonitorManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CC_MONITOR_MANAGE", schema = "SQJZ")
public class ManageMonitor implements java.io.Serializable {

	// Fields

	/**
	 * @name 
	 * @author caolj
	 * @date 2016年7月19日 下午4:40:25
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String code;
	private String name;
	private String type;

	// Constructors

	/** default constructor */
	public ManageMonitor() {
	}

	/** minimal constructor */
	public ManageMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public ManageMonitor(String id, String code, String name, String type) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE", length = 60)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", length = 60)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}