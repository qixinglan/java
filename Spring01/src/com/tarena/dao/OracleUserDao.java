package com.tarena.dao;

import java.io.Serializable;

/**
 * ��Orale���ݿ�ʵ���û�ʵ��Ĺ���
 * ��Ҫ���ӵ����ݿ�
 */
public class OracleUserDao implements Serializable{
	private JdbcDateSource dateSource;
    //����bean���ԣ�deteSource
	public JdbcDateSource getDateSource() {
		return dateSource;
	}

	public void setDateSource(JdbcDateSource dateSource) {
		this.dateSource = dateSource;
	}

	@Override
	public String toString() {
		return "OracleUserDao [dateSource=" + dateSource + "]";
	}
	
}
