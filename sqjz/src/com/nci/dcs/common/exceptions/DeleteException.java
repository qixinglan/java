package com.nci.dcs.common.exceptions;

public class DeleteException extends Exception {

	private String message = "";
	
	public DeleteException(){			
	}
	
	public DeleteException(String message){
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return "出现删除异常...详细信息：" + message;
	}

}
