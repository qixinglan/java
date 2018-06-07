package com.nci.dcs.homepage.report.model;

import java.util.List;

import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;

public class ReportData {
	private List<ReportList> listData;
	private List<Category> category;
	private List<DataSet> dataset;
	private String detail="";

	public List<ReportList> getListData() {
		return listData;
	}

	public void setListData(List<ReportList> listData) {
		this.listData = listData;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<DataSet> getDataset() {
		return dataset;
	}

	public void setDataset(List<DataSet> dataset) {
		this.dataset = dataset;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
