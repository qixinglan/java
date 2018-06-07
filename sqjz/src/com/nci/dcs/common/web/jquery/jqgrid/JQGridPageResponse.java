package com.nci.dcs.common.web.jquery.jqgrid;

import java.util.List;

import com.nci.dcs.common.utils.Page;

public class JQGridPageResponse {
	private int page = 0;
	private int total = 0;
	private int records = 0;
	private boolean repeatitems = false;
	//private Object userdata = null;
	private List rows = null;

	public JQGridPageResponse(Page data){
		this.rows = data.getResult();
		this.total = data.getTotalPages();
		this.page = data.getPageNo();
		this.records = data.getTotalCount();
	}
	
	public JQGridPageResponse(List rows){
		this.rows = rows;
		this.total = 1;
		this.page = 1;
		this.records = rows.size();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public boolean isRepeatitems() {
		return repeatitems;
	}

	public void setRepeatitems(boolean repeatitems) {
		this.repeatitems = repeatitems;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
