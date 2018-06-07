package com.nci.dcs.jzgl.cxtj.dto;

import java.util.List;

public class DataSet {
	private String seriesname;
	private List<Data> data;

	public DataSet(String seriesname) {
		this.seriesname = seriesname;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

}
