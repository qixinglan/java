package com.tarena.elts.net;

import java.io.Serializable;

/**
 * �������Կͻ��˻�Ӧ���ݵķ�װ
 */
public class Response implements Serializable{
	//�ỰID  ���ݿͻ��˵Ĳ�ͬ��Ӧ�Ĳ�һ��
	private String sessionID;
	//��Ӧ���ͻ��˵�����
	private Object value;
	//��Ӧ���ͻ����쳣
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
