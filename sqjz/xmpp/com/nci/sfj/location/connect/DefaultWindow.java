package com.nci.sfj.location.connect;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nci.sfj.location.timeout.MessageFactory;

public class DefaultWindow implements SlipWindow {
	private static final Logger logger = Logger.getLogger(DefaultWindow.class);
	private List<Object> window = new LinkedList<Object>();;
	private int windowSize;
	private MessageFactory factory;

	@Override
	public synchronized boolean sendable() {
		logger.info("当前窗口使用量：" + window.size() + ";可用："
				+ (windowSize > window.size()));
		return windowSize > window.size();
	}

	@Override
	public synchronized boolean put(Object msg) {
		if (find(factory.getRequestId(msg)) == null) {
			if (sendable())
				return window.add(msg);
		}
		return false;
	}

	@Override
	public synchronized Object take(Object key) {
		for (Object obj : window) {
			if (key.equals(factory.getRequestId(obj))) {
				window.remove(obj);
				return obj;
			}
		}
		return null;
	}

	public synchronized Object find(Object key) {
		for (Object obj : window) {
			if (key.equals(factory.getRequestId(obj))) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public synchronized int getMaxSize() {
		return windowSize;
	}

	@Override
	public synchronized int size() {
		return window.size();
	}

	public int getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}

	public MessageFactory getFactory() {
		return factory;
	}

	public void setFactory(MessageFactory factory) {
		this.factory = factory;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAll() {
		return window;
	}
}
