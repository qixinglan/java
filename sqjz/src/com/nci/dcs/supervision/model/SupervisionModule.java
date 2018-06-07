package com.nci.dcs.supervision.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.nci.dcs.homepage.todo.model.TodoCount;

@Entity(name = "CC_SUPERVISION_MODULE")
public class SupervisionModule extends TodoCount {
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
	 * 图标
	 */
	@Column(name = "ICON", length = 200)
	private String icon;
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
	 * 访问URL
	 */
	@Column(name = "URL", length = 300)
	private String url;
	/**
	 * 获取数量的ITodoService实现类
	 */
	@Column(name = "SERVICE", length = 300)
	private String service;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "REPORT_TYPE")
	private String reportType;
	
	@Column(name = "REPORT_NAME")
	private String reportName;

	@Column(name = "REPORT_URL")
	private String reportUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Override
	public String toString() {
		return "SupervisionModule [id=" + id + ", name=" + name + ", icon="
				+ icon + ", sort=" + sort + ", visible=" + visible + ", url="
				+ url + ", service=" + service + ", type=" + type
				+ ", reportType=" + reportType + ", reportName=" + reportName
				+ ", reportUrl=" + reportUrl + "]";
	}
	
}
