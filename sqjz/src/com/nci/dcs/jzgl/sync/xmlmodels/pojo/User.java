package com.nci.dcs.jzgl.sync.xmlmodels.pojo;

public class User {
	private String UID = "201311001";
	//测试密码
	//private String PWD = "123456";
	//正式密码
	private String PWD = "be8bda64ad91bda0c91ba70fb7b3eb";
	
	public User(){}
	public User(String id, String pwd){
		this.UID = id;
		this.PWD = pwd;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getPWD() {
		return PWD;
	}
	public void setPWD(String pWD) {
		PWD = pWD;
	}
}
