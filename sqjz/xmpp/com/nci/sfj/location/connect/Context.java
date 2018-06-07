package com.nci.sfj.location.connect;

import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

public class Context {
	public Context(ConnectionFactory factory, int maxSize, int mode) {
		super();
		this.cachedConnectiones = new HashSet<IoSession>();
		this.factory = factory;
		this.maxSize = maxSize;
		this.mode = mode;
	}

	public Set<IoSession> cachedConnectiones;
	public final ConnectionFactory factory;
	boolean stop;
	int maxSize;
	int mode = 0; // 0:长连接 1:短连接
	int creating = 0;

	public String toString() {
		return new StringBuilder().append(
				"FlowControl.Context(maxSize:" + maxSize + ";size:"
						+ cachedConnectiones + ";mode:" + mode + ")")
				.toString();
	}
}