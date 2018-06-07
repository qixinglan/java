package com.nci.dcs.base.xmpp.impl;

import java.util.Map;

import com.nci.dcs.base.xmpp.XmppCallback;
import com.nci.dcs.base.xmpp.XmppWork;

public class DefaultXmppCallback<T> implements XmppCallback<T> {
	
	private Map<T, ? extends XmppWork<T>> workMap;
	
	public DefaultXmppCallback(Map<T, ? extends XmppWork<T>> workMap1) {
		super();
		this.workMap = workMap1;
	}

	@Override
	public void process(T id, Integer action, Integer state, String text,
			Object attachment) {
		workMap.get(id).setCurrentProcess(new DefaultProcess(action, state,text));
	}

	@Override
	public void finish(T id, Integer action, Integer state, String text,
			Object attachment) {
		workMap.get(id).setCurrentProcess(new DefaultProcess(action, state,text));
	}

	@Override
	public void timeout(T id, Integer action, Integer state, String text,
			Object attachment) {
		workMap.get(id).setCurrentProcess(new DefaultProcess(action, state,text));
	}
}
