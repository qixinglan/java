package com.nci.dcs.common.web.jquery.jqgrid;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;


public class JQGridPageRequest{
	private boolean search;
	private String nd;
	private String cols;
	private int pageSize;
	private int pageStart;
	private String sortCol;
	private String sortOrder;
	private String filters;
	
	
	public JQGridPageRequest(){
		
	}
	List<Criterion> criterions = null;
	List<Order> orders = null;
	
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getCols() {
		return cols;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public String getSortCol() {
		return sortCol != null ? sortCol : "";
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortOrder() {
		return sortOrder != null ? sortOrder : "";
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	

	public int getLimit() {
		return this.pageSize > 0 ? this.pageSize : 1;
	}
	
	public int getStart() {
		int start = (this.pageStart - 1) * getLimit();
		return start < 0 ? 0 : start; 
	}
	
	public List<Criterion> getCriterions(Class type) {
		if (this.criterions == null){
			JQGridSearchRuleParser parser = new JQGridSearchRuleParser(this.getFilters());
			this.criterions = parser.parse(type);
		}
		return this.criterions;
	}
	
	//jqGrid 4.5.0不支持多列排序，如果使用4.5.2以后版本，需要适配多列排序
	public List<Order> getOrders(Class type) {
		if (this.orders == null){
			try {
				if (this.sortCol != null && !this.sortCol.isEmpty() && type.getDeclaredField(this.sortCol) != null){
					Order order = null;
					if ("asc".equalsIgnoreCase(this.sortOrder)){
						order = Order.asc(this.sortCol);
					}
					else{
						order = Order.desc(this.sortCol);
					}
					List<Order> orders = new ArrayList();
					orders.add(order);
					this.orders = orders;
				}
			} catch (Exception e) {
			}
		}
		return this.orders;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}

	public List<String> getFields(Class type) {
		if (this.cols != null){
			String cols[] = this.cols.split(",");
			List<String> fields = new ArrayList();
			for (String col : cols){
				try {
					if (type.getDeclaredField(col) != null){
						fields.add(col.trim());
					}
				}catch (Exception e) {
				}
			}
			return fields;
		}
		return null;
	}
}
