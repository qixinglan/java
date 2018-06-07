package com.nci.dcs.data.action;


import java.text.SimpleDateFormat;
import java.util.Date;


public class TjfxAction {
	
	private String year = new String();
	private String year1;
	private String year2;
	
	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String report() throws Throwable {
		try{
			year1 = year + "-01-01 00:00:00";
			year2 = year + "-12-31 23:59:59";
			
		}catch(Throwable t){
			throw new Exception(t.getMessage());
		}
		return "success";
	
	}
	
	

}
