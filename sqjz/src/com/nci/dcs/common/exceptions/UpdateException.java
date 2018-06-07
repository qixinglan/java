package com.nci.dcs.common.exceptions;

public class UpdateException extends Exception {

	private String message = "";
	
	public UpdateException(){			
	}
	
	public UpdateException(String message){
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return "出现更新异常...详细信息：" + message;
	}

}
