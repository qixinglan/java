package com.nci.sfj.location.timeout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.util.WriteRequestFilter;

import com.nci.sfj.location.timeout.exception.RequestTimeoutException;
import com.nci.sfj.location.timeout.exception.ResendTimeTooMuchException;
import com.nci.sfj.location.timeout.exception.SessionClosedWithRequestLiveException;

/**
 * Description:用于检测请求超时的过滤器
 * 
 * @author Shuzz
 * @since 2014-1-24上午10:20:11
 */
public class RequestTimerOutFilter extends WriteRequestFilter {

	private MessageFactory messageFactory;

	private static final int DEFAULT_SIZE = Runtime.getRuntime()
			.availableProcessors() / 4 + 1;

	private static final ScheduledThreadPoolExecutor timeoutScheduler = new ScheduledThreadPoolExecutor(
			DEFAULT_SIZE);

	private final AttributeKey REQUEST_STORE = new AttributeKey(getClass(),
			"requestStore");

	public RequestTimerOutFilter(MessageFactory messageFactory) {
		super();
		this.messageFactory = messageFactory;
	}

	@SuppressWarnings("unchecked")
	private Map<Object, Object> getRequestStore(IoSession session) {
		return (Map<Object, Object>) session.getAttribute(REQUEST_STORE);
	}

	@Override
	public void sessionOpened(NextFilter nextFilter, IoSession session)
			throws Exception {
		// session创建时组织存放request的map放到session中，该map可能包含以前未收到回复的request
		session.setAttribute(REQUEST_STORE,
				messageFactory.createRequestStore(session));
		nextFilter.sessionOpened(session);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {
		// session关闭时将未收到回复的request保存起来以备session重新打开时使用
		Map<Object, Object> requestStore = getRequestStore(session);
		List<Object> send = new ArrayList<Object>();
		for (Object requestKey : requestStore.keySet()) {
			Map<Object, ScheduledFuture<?>> m = (Map<Object, ScheduledFuture<?>>) requestStore
					.get(requestKey);
			for (Object key : m.keySet()) {
				ScheduledFuture<?> scheduledFuture = m.get(key);
				if (scheduledFuture != null) {
					scheduledFuture.cancel(false);
				}
				send.add(key);
			}
		}
		if (send.size() > 0) {
			SessionClosedWithRequestLiveException cause = new SessionClosedWithRequestLiveException(
					send);
			nextFilter.exceptionCaught(session, cause);
		} else {
			nextFilter.sessionClosed(session);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		if (messageFactory.isResponse(message)) {
			// 获取该response回复的requestId
			Object requestId = messageFactory.getResponseId(message);
			if (requestId != null) {
				Map<Object, Object> requestStore = getRequestStore(session);
				synchronized (requestStore) {
					if (requestStore.get(requestId) != null) {
						Map<Object, ScheduledFuture<?>> m = (Map<Object, ScheduledFuture<?>>) requestStore
								.get(requestId);
						requestStore.remove(requestId);
						for (Object key : m.keySet()) {
							ScheduledFuture<?> scheduledFuture = m.get(key);
							if (scheduledFuture != null) {
								scheduledFuture.cancel(false);
							}
						}
					}
				}
			}
		}
		// 所有消息最终均交由下一层处理
		nextFilter.messageReceived(session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		Object message = writeRequest.getMessage();
		// 如果发送的消息是需要定时进行处理的这将其放到map中并开启定时器
		if (messageFactory.isRequest(message)) {
			Map<Object, Object> requestStore = getRequestStore(session);
			Object oldValue = null;
			Object requestId = messageFactory.getRequestId(message);
			synchronized (requestStore) {
				oldValue = requestStore.get(requestId);
				if (oldValue == null) {
					Map<Object, ScheduledFuture<?>> m = new HashMap<Object, ScheduledFuture<?>>();
					TimeoutTask timeoutTask = new TimeoutTask(nextFilter,
							message, session, requestId,
							messageFactory.getResendTime());
					ScheduledFuture<?> scheduledFuture = timeoutScheduler
							.scheduleAtFixedRate(timeoutTask,
									messageFactory.getPeriod(),
									messageFactory.getPeriod(),
									TimeUnit.SECONDS);
					m.put(message, scheduledFuture);
					requestStore.put(requestId, m);
				}
			}
		}
		nextFilter.messageSent(session, writeRequest);
	}

	private class TimeoutTask implements Runnable {
		private final NextFilter filter;

		private final Object request;

		private final IoSession session;

		private final Object requestId;
		private int resendTime;

		private TimeoutTask(NextFilter filter, Object request,
				IoSession session, Object requestId, int resendTime) {
			this.filter = filter;
			this.request = request;
			this.session = session;
			this.requestId = requestId;
			this.resendTime = resendTime;
		}

		@SuppressWarnings("unchecked")
		public void run() {
			Map<Object, Object> requestStore = getRequestStore(session);
			boolean timedOut = false;
			synchronized (requestStore) {
				if (requestStore.get(requestId) != null) {
					Map<Object, ScheduledFuture<?>> m = (Map<Object, ScheduledFuture<?>>) requestStore
							.get(requestId);
					if (m.containsKey(request)) {
						timedOut = true;
					}
				}
			}
			if (timedOut) {
				resendTime--;
				Exception e;
				if (resendTime > 0) {
					// 由业务处理层去处理重发
					e = new RequestTimeoutException(request);
				} else {
					synchronized (requestStore) {
						Map<Object, ScheduledFuture<?>> m = (Map<Object, ScheduledFuture<?>>) requestStore
								.get(requestId);
						requestStore.remove(requestId);
						for (Object key : m.keySet()) {
							ScheduledFuture<?> scheduledFuture = m.get(key);
							if (scheduledFuture != null) {
								scheduledFuture.cancel(false);
							}
						}
					}
					e = new ResendTimeTooMuchException(request);
				}
				filter.exceptionCaught(session, e);
			}
		}
	}

	@Override
	protected Object doFilterWrite(final NextFilter nextFilter,
			IoSession session, WriteRequest writeRequest) throws Exception {
		return writeRequest.getMessage();
	}

	public MessageFactory getMessageFactory() {
		return messageFactory;
	}

	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}
}