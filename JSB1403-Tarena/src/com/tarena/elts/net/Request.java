package com.tarena.elts.net;

import java.io.Serializable;

/**
 * 客户端向服务器的请求数据的封装
 */

public class Request implements Serializable{
	//识别令牌，会话ID（我们把一个客户端向 服务器的所有连接叫一个会话）
	private String sessionID;
	//业务名称（业务方法名）
	private String method;
	//参数类型列表
	private Class[] parameterTypes;
	//参数内容列表
	private Object[] parameters;
	public Request() {
		super();
	}
	public Request(String method, Class[] parameterTypes, Object[] parameters) {
		super();
		this.method = method;
		this.parameterTypes = parameterTypes;
		this.parameters = parameters;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Class[] getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(Class[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
}

