package com.nci.sfj.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

public class XmppClientSession {
	private IoSession session;
	private Integer keyCount;
	private Map<String, String> keys = new HashMap<String, String>(0);

	public XmppClientSession(IoSession session) {
		this.session = session;
		this.keyCount = new Integer(0);
	}

	public Long getId() {
		return session.getId();
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public Integer getKeyCount() {
		return keyCount;
	}

	public void addKey(String key) {
		if (keys.get(key) == null) {
			keys.put(key, "");
			keyCount++;
		}
	}

	public void changeKey(String oldKey, String newKey) {
		removeKey(oldKey);
		addKey(newKey);
	}

	public void removeKey(String key) {
		if (keys.get(key) != null) {
			keys.remove(key);
			keyCount--;
		}
	}

	public Set<String> getKeys() {
		return keys.keySet();
	}

	public void clear() {
		this.session = null;
		this.keyCount = new Integer(0);
		keys = new HashMap<String, String>(0);
	}
}
