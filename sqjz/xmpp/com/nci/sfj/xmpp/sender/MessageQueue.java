package com.nci.sfj.xmpp.sender;

import java.util.ArrayList;
import java.util.Collection;

public class MessageQueue {
	private ArrayList<Object> queue = new ArrayList<Object>();
	public synchronized boolean addAtTail(Object obj){
		return queue.add(obj);
	}
	public synchronized int size(){
		return queue.size();
	}
	public synchronized void addAtHead(Object obj){
		queue.add(0, obj);
	}
	@SuppressWarnings("unchecked")
	public synchronized void addAtHead(@SuppressWarnings("rawtypes") Collection objs){
		queue.addAll(0, objs);
	}
	public synchronized Object peek(){
		return queue.size() > 0 ? queue.get(0) : null;
	}
	public synchronized Object take(){
		return queue.size() > 0 ? queue.remove(0) : null;
	}
	public synchronized boolean remove(Object obj){
		return queue.remove(obj);
	}
}
