package com.nci.sfj.xmpp.sender;

public abstract class EmMessageReceive {
	public abstract void resultReceive(Type type, String id);

	public enum Type {
		SUCCESS, ERROR, TIMEOUT, NOSUCHEM;
	}
}
