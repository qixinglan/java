package com.nci.dcs.homepage.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "CC_HP_REPORT_MODULE")
public class ReportModule extends ReportData {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	private String id;
	/**
	 * 名称
	 */
	@Column(name = "NAME", length = 90)
	private String name;
	/**
	 * 权限
	 */
	@Column(name = "PERMISSION", length = 90)
	private String permission;
	/**
	 * 排序号
	 */
	@Column(name = "SORT")
	private Integer sort;
	/**
	 * 是否显示
	 */
	@Column(name = "VISIBLE", length = 30)
	private String visible;
	/**
	 * 获取报表数据的IReportService实现类
	 */
	@Column(name = "SERVICE", length = 300)
	private String service;
	/**
	 * 报表类型
	 */
	@Column(name = "TYPE", length = 30)
	private String type;

	@Column(name = "SHORTNAME", length = 30)
	private String shortName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "ReportModule [id=" + id + ", name=" + name + ", permission="
				+ permission + ", sort=" + sort + ", visible=" + visible
				+ ", service=" + service + ", type=" + type + ", shortName="
				+ shortName + "]";
	}

}
