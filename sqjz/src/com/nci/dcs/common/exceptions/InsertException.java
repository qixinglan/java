package com.nci.dcs.common.exceptions;

public class InsertException extends Exception {

	private String message = "";
	
	public InsertException(){			
	}
	
	public InsertException(String message){
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return "出现插入异常...详细信息：" + message;
	}

}
