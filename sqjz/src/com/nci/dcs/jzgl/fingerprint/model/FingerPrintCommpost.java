package com.nci.dcs.jzgl.fingerprint.model;

import java.util.Date;

public class FingerPrintCommpost {
	private int postid;
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getPosttype() {
		return posttype;
	}
	public void setPosttype(int posttype) {
		this.posttype = posttype;
	}
	public String getParams1() {
		return params1;
	}
	public void setParams1(String params1) {
		this.params1 = params1;
	}
	public String getParams2() {
		return params2;
	}
	public void setParams2(String params2) {
		this.params2 = params2;
	}
	public String getParams3() {
		return params3;
	}
	public void setParams3(String params3) {
		this.params3 = params3;
	}
	public String getParams4() {
		return params4;
	}
	public void setParams4(String params4) {
		this.params4 = params4;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}
	private int posttype;
	private String params1;
	private String params2;
	private String params3;
	private String params4;
	private Date addtime;
	private int status;
	private Date finishtime;

}
