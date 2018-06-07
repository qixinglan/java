package com.nci.dcs.jzgl.bbgl.dto;

public class WeekStaticData {
   
	public WeekStaticData(){
		
	}
	
	private String orgID;
	private String orgName;
	private String currDate;
	private String addData;
	private String subData;
	private String guanzhi;
	private String huanxing;
	private String jiashi;
	private String xiaoJi;
	private String zanjianwai;
	private String weekAddNum;
	private String weekSubNum;
	
	public String getOrgID() {
		return orgID;
	}
	
	public void setOrgID(String orgID) {
		this.orgID =orgID;
	}
	
	public String getAddData() {
		return addData;
	}
	
	public void setAddData(String addData) {
		this.addData =addData;
	}
	
	public String getSubData() {
		return subData;
	}
	
	public void setSubData(String subData) {
		this.subData =subData;
	}
	
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getCurrDate() {
		return this.currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	
	
	public String getGuanZhi() {
		return this.guanzhi;
	}

	public void setGuanZhi(String guanzhi) {
		this.guanzhi = guanzhi;
	}
	
	public String getHuanXing() {
		return this.huanxing;
	}

	public void setHuanXing(String huanxing) {
		this.huanxing = huanxing;
	}
	
	public String getJiaShi() {
		return this.jiashi;
	}

	public void setJiaShi(String jiashi) {
		this.jiashi = jiashi;
	}
	
	public String getZanJianWai() {
		return this.zanjianwai;
	}

	public void setZanJianWai(String zanjianwai) {
		this.zanjianwai = zanjianwai;
	}
	
	
	public String getXiaoJi() {
		return this.xiaoJi;
	}

	public void setXiaoJi(String xiaoJi) {
		this.xiaoJi = xiaoJi;
	}
	
	
	
	public String getWeekAddNum() {
		return this.weekAddNum;
	}

	public void setWeekAddNum(String weekAddNum) {
		this.weekAddNum = weekAddNum;
	}
	
	public String getWeekSubNum() {
		return this.weekSubNum;
	}

	public void setWeekSubNum(String weekSubNum) {
		this.weekSubNum = weekSubNum;
	}
}
