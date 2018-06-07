package com.qixing.dao;

public class Page {
	private int currentPage=1;
	//总共行数
	private int rows;
	//总共页数
	private int totalPages;

	//	@Resource(name="db")
//	private Properties db;
//	 =Integer.parseInt(db.getProperty("pageSize"));
//	@Value("#{db.pageSize}")
	private int pageSize;//每页数目
	private int end;
	private int begin;
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalPages() {
		int b=rows%pageSize;
		int a=rows/pageSize;
		totalPages=(b==0?a:a+1);
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getEnd() {
		end=currentPage*pageSize+1;
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getBegin() {
		begin=(currentPage-1)*pageSize;
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
}
