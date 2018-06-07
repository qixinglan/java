package com.nci.sfj.transmit.model;

public class DataQuery {
	
	private String userName;
	private String orgType;
	private String orgID;
	private String commonValue;
	private String ryID;
	private String startTime;
	private String endTime;


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the orgType 
	 * 机构类型
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType the orgType to set
	 * 机构类型
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	/**
	 * @return the orgID
	 */
	public String getOrgID() {
		return orgID;
	}

	/**
	 * @param orgID the orgID to set
	 */
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	
	/**
	 * @return 共用属性字段 
	 */
	public String getCommonValue() {
		return commonValue;
	}

	/**
	 * @param 共用属性字段   
	 */
	public void setCommonValue(String commonValue) {
		this.commonValue = commonValue;
	}

	/**
	 * @return the ryID
	 */
	public String getRyID() {
		return ryID;
	}

	/**
	 * @param ryID the ryID to set
	 */
	public void setRyID(String ryID) {
		this.ryID = ryID;
	}
	
	/**
	 * 
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
