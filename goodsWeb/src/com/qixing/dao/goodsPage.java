package com.qixing.dao;

public class goodsPage extends Page{
	private int type1;
	private int type2;
	private String search;
	private String orderByClicks;
	private String orderByDate;
	private String orderByPrice;
	public String getOrderByClicks() {
		return orderByClicks;
	}
	public void setOrderByClicks(String orderByClicks) {
		this.orderByClicks = orderByClicks;
	}
	public String getOrderByDate() {
		return orderByDate;
	}
	public void setOrderByDate(String orderByDate) {
		this.orderByDate = orderByDate;
	}
	public String getOrderByPrice() {
		return orderByPrice;
	}
	public void setOrderByPrice(String orderByPrice) {
		this.orderByPrice = orderByPrice;
	}
	public int getType1() {
		return type1;
	}
	public void setType1(int type1) {
		this.type1 = type1;
	}
	public int getType2() {
		return type2;
	}
	public void setType2(int type2) {
		this.type2 = type2;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
