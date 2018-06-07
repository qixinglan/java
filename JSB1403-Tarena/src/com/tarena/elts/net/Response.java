package com.tarena.elts.net;

import java.io.Serializable;

/**
 * 服务器对客户端回应数据的封装
 */
public class Response implements Serializable{
	//会话ID  根据客户端的不同回应的不一样
	private String sessionID;
	//回应给客户端的数据
	private Object value;
	//回应给客户端异常
	private Exception e;
	public Response() {
		super();
	}
	public Response(Object value) {
		super();
		this.value = value;
	}
	public Response(Exception e) {
		super();
		this.e = e;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
}
