package com.nci.dcs.jzgl.sync.xmlmodels;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("Result")
public class Result {
	private String State;
	private String ID;
	private String Desc;
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	
	public boolean isSuccess(){
		return "success".equalsIgnoreCase(this.State);
	}
}
