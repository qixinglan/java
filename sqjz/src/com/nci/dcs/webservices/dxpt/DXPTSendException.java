package com.nci.dcs.webservices.dxpt;

public class DXPTSendException extends Exception {
	public DXPTSendException(String msg){
		super("短信平台发送短信错误," + msg);
	}
}
