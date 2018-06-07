package com.tarena.elts.net;

import java.io.Serializable;

/**
 * �ͻ�������������������ݵķ�װ
 */

public class Request implements Serializable{
	//ʶ�����ƣ��ỰID�����ǰ�һ���ͻ����� ���������������ӽ�һ���Ự��
	private String sessionID;
	//ҵ�����ƣ�ҵ�񷽷�����
	private String method;
	//���������б�
	private Class[] parameterTypes;
	//���������б�
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

