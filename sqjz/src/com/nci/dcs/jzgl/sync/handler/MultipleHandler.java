package com.nci.dcs.jzgl.sync.handler;

import java.util.ArrayList;
import java.util.List;

import com.nci.dcs.jzgl.sync.utils.SyncException;

public class MultipleHandler  implements ISyncHandler{
	private List<ISyncHandler> handlers= new ArrayList<ISyncHandler>();
	private ISyncHandler master;
	public MultipleHandler(ISyncHandler master){
		this.master = master;
		handlers.add(master);
	}
	@Override
	public String getName() {
		return master.getName();
	}

	@Override
	public String getEncoding() {
		return master.getEncoding();
	}

	@Override
	public void sendRequest(String xml) throws SyncException {
		for (ISyncHandler handler : handlers){
			handler.sendRequest(xml);
		}
	}

	@Override
	public void sync() throws SyncException {
		for (ISyncHandler handler : handlers){
			handler.sync();
		}
	}

	@Override
	public String readResponse() throws SyncException {
		return master.readResponse();
	}

	@Override
	public void close() {
		for (ISyncHandler handler : handlers){
			handler.close();
		}
	}
	
	public void addHandler(ISyncHandler handler){
		this.handlers.add(handler);
	}
}
