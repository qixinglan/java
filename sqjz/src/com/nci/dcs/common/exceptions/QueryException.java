package com.nci.dcs.common.exceptions;

public class QueryException extends Exception {

	private String message = "";
	
	public QueryException(){			
	}
	
	public QueryException(String message){
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return "出现查询异常...详细信息：" + message;
	}
	
}
