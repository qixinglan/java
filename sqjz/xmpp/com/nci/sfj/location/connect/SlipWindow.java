package com.nci.sfj.location.connect;

import java.util.List;

public interface SlipWindow {
	static final String KEY_SLIPWINDOW = "_SLIPWINDOW_";
	//private LinkedHashSet<> requests = new LinkedHashSet<>();
	public boolean sendable();
	public boolean put(Object msg);
	public Object take(Object key);
	public Object find(Object key);
	@SuppressWarnings("rawtypes")
	public List getAll();
	public int getMaxSize();
	public int size();
}
