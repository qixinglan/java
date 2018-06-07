package com.nci.sfj.location;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.sfj.location.connect.DefaultWindow;
import com.nci.sfj.location.connect.SlipWindow;
import com.nci.sfj.location.timeout.exception.RequestTimeoutException;
import com.nci.sfj.location.timeout.exception.ResendTimeTooMuchException;
import com.nci.sfj.location.timeout.exception.SessionClosedWithRequestLiveException;

public class LocationServerHandler extends IoHandlerAdapter {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private LocationMinaService locationMinaService;

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		if (cause instanceof RequestTimeoutException) {
			Object message = ((RequestTimeoutException) cause)
					.getMessageFromException();
			session.write(message);
		} else if (cause instanceof ResendTimeTooMuchException) {
			Object message = ((ResendTimeTooMuchException) cause)
					.getMessageFromException();
			if (message instanceof LocationMessage) {
				SlipWindow window = (SlipWindow) session
						.getAttribute(SlipWindow.KEY_SLIPWINDOW);
				window.take(((LocationMessage) message).getSequenceId());
			}

		} else if (cause instanceof SessionClosedWithRequestLiveException) {
			this.sessionClosed(session);
		} else {
			log.error(null, cause);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof LocationMessage) {
			LocationMessage loc = (LocationMessage) message;
			if (LocationConstants.TYPE_DATA == loc.getType()) {
				try {
					String content = loc.getContent();
					if (content.split("\\|").length == 3) {
						locationMinaService.locationReceived(content
								.split("\\|"));
					}
					LocationMessage res = new LocationMessage(
							LocationConstants.TYPE_DATA_ACK,
							loc.getSequenceId());
					session.write(res);
				} catch (Exception e) {
					log.error("处理LBS定位信息发生异常", e);
				}
			} else if (LocationConstants.TYPE_DATA_ACK == loc.getType()) {
				SlipWindow window = (SlipWindow) session
						.getAttribute(SlipWindow.KEY_SLIPWINDOW);
				window.take(loc.getSequenceId());
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		if ((message instanceof LocationMessage)
				&& (((LocationMessage) message).getType() == LocationConstants.TYPE_DATA)) {
			SlipWindow window = (SlipWindow) session
					.getAttribute(SlipWindow.KEY_SLIPWINDOW);
			window.put(message);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		SlipWindow window = (SlipWindow) session
				.getAttribute(SlipWindow.KEY_SLIPWINDOW);
		if (window == null) {
			DefaultWindow windows = new DefaultWindow();
			LocationMessageFactory locSubmitWrapper = (LocationMessageFactory) SpringContextUtil
					.getBean("locSubmitWrapper");
			windows.setFactory(locSubmitWrapper);
			windows.setWindowSize(128);
			session.setAttribute(SlipWindow.KEY_SLIPWINDOW, windows);

		}
	}

	public void sessionCreated(IoSession session) throws Exception {
		// Empty handler
	}
}