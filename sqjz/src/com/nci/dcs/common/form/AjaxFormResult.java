package com.nci.dcs.common.form;

public class AjaxFormResult {
	private boolean success = false;
	private String msg;
	public AjaxFormResult(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
	public static AjaxFormResult error(String errorMsg){
		return new AjaxFormResult(false, errorMsg);
	}
	
	public static AjaxFormResult success(String msg){
		return new AjaxFormResult(true, msg);
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
}
