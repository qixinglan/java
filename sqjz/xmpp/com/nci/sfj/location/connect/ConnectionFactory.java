package com.nci.sfj.location.connect;

import org.apache.mina.core.session.IoSession;

public interface ConnectionFactory {
	public IoSession createConnection();
	public void authorited(IoSession session, boolean b, String status);
}
