package com.tarena.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class Service implements Serializable {

	private Integer serviceId;
	//由于新增的关联属性。可以体现2张表的关系，所以accountId就多余了
	//必须去掉，否则会导致Hibernate认为存在多余的关联关系。报错
	//private Integer accountId;
	private String unixHost;
	private String osUserName;
	private String loginPassword;
	private String status;
	private Timestamp createDate;
	private Timestamp pauseDate;
	private Timestamp closeDate;
	private Integer costId;
	
	
	//关联属性，封装对应的Account，多对一
	//该数据由Hibernate自动查出并注入。
	private Account account;

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
//	public Integer getAccountId() {
//		return accountId;
//	}
//	public void setAccountId(Integer accountId) {
//		this.accountId = accountId;
//	}
	public String getUnixHost() {
		return unixHost;
	}
	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}
	public String getOsUserName() {
		return osUserName;
	}
	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getPauseDate() {
		return pauseDate;
	}
	public void setPauseDate(Timestamp pauseDate) {
		this.pauseDate = pauseDate;
	}
	public Timestamp getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	
}
