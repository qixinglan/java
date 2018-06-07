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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.em.model.CcDzjgsbDevice;
//different from online
//import com.nci.sfj.business.model.XmppJustice;
import com.nci.sfj.business.service.DbService;
//different from online
//import com.nci.sfj.common.util.XMPPConstants;
//different from online
//import com.nci.sfj.xmpp.exception.ForbiddenException;
import com.nci.sfj.xmpp.exception.UnauthenticatedException;

/**
 * This class is to provide the methods associated with user authentication.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class AuthManager {

	private static final Log log = LogFactory.getLog(AuthManager.class);

	private static final Object DIGEST_LOCK = new Object();

	private static MessageDigest digest;

	static {
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			log.error("Internal server error", e);
		}
	}

	/**
	 * Authenticates a user with a username, token, and digest, and returns an
	 * AuthToken.
	 * 
	 * @param username
	 *            the username
	 * @param token
	 *            the token
	 * @param digest
	 *            the digest
	 * @return an AuthToken
	 * @throws UnauthenticatedException
	 *             if the username and password do not match
	 */
	//different from online
	//public static boolean authenticateClinet(String username, String token,
	public static boolean authenticate(String username, String token,
			String digest, String resource) throws UnauthenticatedException {
		if (username == null || token == null || digest == null) {
			throw new UnauthenticatedException();
		}
		username = username.trim();
		boolean flag = true;
		DbService dbService = (DbService) SpringContextUtil
				.getBean("dbService");
		CcDzjgsbDevice device = dbService.findSuperviseDeviceByNumber(username);
		if (device == null) {
			throw new UnauthenticatedException();
		}
		return flag;
	}

	//different from online
	/*
	public static boolean authenticateJustice(String username, String token,
			String password, String resource, String imei)
			throws UnauthenticatedException, ForbiddenException {
		// 首先验证设备是否授权
		if (imei == null) {
			throw new ForbiddenException();
		}
		imei = imei.trim();
		DbService dbService = (DbService) SpringContextUtil
				.getBean("dbService");
		XmppJustice justice = dbService.findJustice(imei);
		if (justice == null
				|| !XMPPConstants.VAILDATE_TRUE_MD5.equals(justice
						.getValidate())) {
			throw new ForbiddenException();
		}
		// 其次验证用户是否授权
		if (username == null || token == null || digest == null) {
			throw new UnauthenticatedException();
		}
		username = username.trim();
		boolean flag = true;
		UserXmpp user = dbService.findUserByName(username);
		if (user == null) {
			throw new UnauthenticatedException();
		}
		return flag;
	}
	*/

	/**
	 * Returns true if plain text password authentication is supported according
	 * to JEP-0078.
	 * 
	 * @return true if plain text password authentication is supported
	 */
	public static boolean isPlainSupported() {
		return true;
	}

	/**
	 * Returns true if digest authentication is supported according to JEP-0078.
	 * 
	 * @return true if digest authentication is supported
	 */
	public static boolean isDigestSupported() {
		return true;
	}

	private static String createDigest(String token, String password) {
		synchronized (DIGEST_LOCK) {
			digest.update(token.getBytes());
			return Hex.encodeHexString(digest.digest(password.getBytes()));
		}
	}

}
