package com.nci.dcs.base.xmpp.impl;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.base.xmpp.WorkProcess;
import com.nci.dcs.base.xmpp.XmppWork;

public class DefaultXmppWork<T> implements XmppWork<T> {
	
	private T id;
	private WorkProcess process;
	private List<WorkProcess> processes = new ArrayList<WorkProcess>();
	private boolean finished = true; 

	public DefaultXmppWork<T> setId(T id){
		this.id = id;
		return this;
	}
	
	@Override
	public T getId() {
		return id;
	}

	@Override
	public synchronized WorkProcess getCurrentProcess() {
		return process;
	}

	@Override
	public synchronized List<WorkProcess> retireProcessHistory() {
		List<WorkProcess> result = processes;
		processes = new ArrayList<WorkProcess>();
		return result;
	}

	@Override
	public synchronized void setCurrentProcess(WorkProcess process) {
		this.process = process;
		processes.add(process);
	}

	@Override
	public synchronized boolean finished() {
		return finished;
	}

	@Override
	public synchronized XmppWork<T> finish() {
		this.finished = true;
		return this;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
}
