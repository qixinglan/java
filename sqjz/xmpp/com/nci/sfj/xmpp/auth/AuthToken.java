/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.nci.sfj.xmpp.auth;

import java.util.Date;

import com.nci.dcs.common.utils.CommonUtils;

/**
 * This class represents a token that proves a user's authentication.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class AuthToken {

	private String orgId;
	private String userId;
	private String fxryId;
	private String deviceId;
	private String deviceNumber;
	private String userName;
	private Date loginTime;
	private Date locationTime;
	private Date alertTime;

	/**
	 * @param pseronId
	 * @param userId
	 * @param orgId
	 * @param deviceId
	 */
	public AuthToken(String orgId, String userId, String fxryId,
			String deviceId, String deviceNumber, String userName,
			Date loginTime) {
		this.orgId = orgId;
		this.userId = userId;
		this.fxryId = fxryId;
		this.deviceId = deviceId;
		this.deviceNumber = deviceNumber;
		this.userName = userName;
		this.loginTime = loginTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getUserId() {
		return userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getFxryId() {
		return fxryId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public String getUserName() {
		return userName;
	}

	public boolean isClient() {
		return !CommonUtils.isNull(deviceNumber);
	}

	public boolean isUser() {
		return !CommonUtils.isNull(userName);
	}

	public void setFxryId(String fxryId) {
		this.fxryId = fxryId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public Date getLocationTime() {
		return locationTime;
	}

	public void setLocationTime(Date locationTime) {
		this.locationTime = locationTime;
	}

	public Date getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

}