package com.nci.sfj.location.connect;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.sfj.location.LocationMinaService;

public class LocationConnectionFactory implements ConnectionFactory {
	@Autowired
	private LocationMinaService locationMinaService;

	@Override
	public IoSession createConnection() {
		IoSession session = null;
		try {
			// 打开连接
			session = locationMinaService.createConnection();
			return session;
		} catch (Exception e) {
			noticeFailed("主动定位创建链接失败，链路异常！" + e.getMessage(), session);
		}
		return null;
	}

	@Override
	public void authorited(IoSession session, boolean b, String status) {
	}

	protected void noticeFailed(String message, IoSession session) {

		if (session != null) {
			try {
				session.close(true);
			} catch (Exception e) {
			}
		}
	}
}
