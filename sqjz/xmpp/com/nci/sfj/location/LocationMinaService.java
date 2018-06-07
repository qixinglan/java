package com.nci.sfj.location;

import java.net.InetSocketAddress;

import javax.annotation.Resource;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.sfj.business.service.BusinessHandleScheduler;
import com.nci.sfj.business.service.DbService;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.common.util.DateFormat;
import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.model.IQModelUtil;

@Service
public class LocationMinaService {
	@Resource
	private IoConnector locConnector;
	@Autowired
	private DbService dbService;
	@Autowired
	private BusinessHandleScheduler businessHandleScheduler;

	/**
	 * Description:接收到主动定位请求返回的定位信息
	 * 
	 * @author Shuzz
	 * @since 2014年9月5日上午11:18:58
	 * @param loc
	 */
	public void locationReceived(String[] loc) {
		String phone = loc[0];
		String x = loc[1];
		String y = loc[2];
		Double xx = DateFormat.tranStrToDoubleNull(x);
		Double yy = DateFormat.tranStrToDoubleNull(y);
		if (xx != null || yy != null) {
			AuthToken token = dbService.findFxryByDevicePhone(phone);
			if (token != null) {
				Element e = IQModelUtil.newLocation(x, y, "LBS", null);
				businessHandleScheduler.addLocationMessage(e, token);
			}
		}

	}

	/**
	 * Description:连接关闭后定时尝试重新连接
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2014-1-10下午7:09:57
	 */
	public IoSession createConnection() {
		IoSession session = locConnector
				.connect(
						new InetSocketAddress(Config
								.getString("location.server.ip"), Config
								.getInt("location.server.port")))
				.awaitUninterruptibly().getSession();
		return session;
	}

	public void createLocationConnection() {
		int linkTime = 0;
		boolean flag = true;
		while (flag) {
			try {
				Thread.sleep(linkTime * 1000);
				createConnection();
				flag = false;
			} catch (Exception e) {
				if (linkTime == 0) {
					linkTime = 5;
				} else if (linkTime * 2 < 90) {
					linkTime = linkTime * 2;
				} else {
					linkTime = 90;
				}
			}
		}
	}
}