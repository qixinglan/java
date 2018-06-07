package com.nci.dcs.common.page;

public class JsonTree {
	private JsonTreeAttr attr;
	private String data;
	private String state;
	private String icon;

	public JsonTree(String id, String name, String dm) {
		attr = new JsonTreeAttr();
		attr.setId(id);
		attr.setDm(dm);
		attr.setName(name);
	}

	public JsonTreeAttr getAttr() {
		return attr;
	}

	public void setAttr(JsonTreeAttr attr) {
		this.attr = attr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}