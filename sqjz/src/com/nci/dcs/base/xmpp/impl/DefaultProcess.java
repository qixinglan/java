package com.nci.dcs.base.xmpp.impl;

import com.nci.dcs.base.xmpp.WorkProcess;

public class DefaultProcess implements WorkProcess{
	private Integer action;
	private Integer state;
	private String text;

	public DefaultProcess(Integer action, Integer state, String text) {
		super();
		this.action = action;
		this.state = state;
		this.text = text;
	}

	@Override
	public Integer getAction() {
		return action;
	}

	@Override
	public Integer getState() {
		return state;
	}

	@Override
	public String getText() {
		return text;
	}
}
