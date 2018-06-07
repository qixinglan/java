package com.nci.dcs.homepage.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "CC_HP_TODO_MODULE")
public class TodoModule extends TodoCount {
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
	 * 访问URL
	 */
	@Column(name = "URL", length = 300)
	private String url;
	/**
	 * 获取数量的ITodoService实现类
	 */
	@Column(name = "SERVICE", length = 300)
	private String service;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TodoModule [id=" + id + ", name=" + name + ", icon=" + icon
				+ ", permission=" + permission + ", sort=" + sort
				+ ", visible=" + visible + ", url=" + url + ", service="
				+ service + "]";
	}
	
}
