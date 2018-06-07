package com.nci.sfj.location.connect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class ConnectionPool {
	private Logger logger = Logger.getLogger(ConnectionPool.class);

	private final String keyWindow = SlipWindow.KEY_SLIPWINDOW;
	private final String keyThreadLock = "_THREAD_LOCK_";
	private Map<String, Context> contextMap = new HashMap<String, Context>();

	private static final ConnectionPool inst = new ConnectionPool();

	protected ConnectionPool() {
		if (logger.isDebugEnabled())
			new DebugInfo().start();
	}

	public static ConnectionPool getPool(String key, ConnectionFactory factory,
			int maxSize, int mode) {
		inst.addConext(key, factory, maxSize, mode);
		return inst;
	}

	public static Context getContext(String key) {
		return inst.contextMap.get(key);
	}

	public static ConnectionPool getInstance() {
		return inst;
	}

	/**
	 * 获取连接
	 * 
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public IoSession getConnection(String key) throws Throwable {
		// 获取上下文
		if (!contextMap.containsKey(key)) {
			synchronized (contextMap) {
				if (!contextMap.containsKey(key)) {
					logger.error("ConnectionPool#没有初始化KEY（" + key + "）.");
					return null;
				}
			}
		}
		Context context = contextMap.get(key);
		IoSession session = null;
		if (context.mode == 0) {
			session = getLongConnection(context);
		} else
			session = getShortConnection(context);
		return session;
	}

	public boolean lock(IoSession session) {
		if (null == session)
			return false;
		synchronized (session) {
			Thread thread = (Thread) session.getAttribute(keyThreadLock);
			if (thread != null) {
				return thread == Thread.currentThread();
			}
			session.setAttribute(keyThreadLock, Thread.currentThread());
		}
		return true;
	}

	public void release(IoSession session) {
		if (lock(session)) {
			synchronized (session) {
				session.removeAttribute(keyThreadLock);
			}
		}
	}

	public boolean addConext(String key, ConnectionFactory factory,
			int maxSize, int mode) {
		Context context = new Context(factory, maxSize, mode);
		synchronized (contextMap) {
			if (contextMap.containsKey(key))
				return false;
			contextMap.put(key, context);
			return true;
		}
	}

	public void removeConext(String key) {
		synchronized (contextMap) {
			contextMap.remove(key);
		}
	}

	public boolean isSendable(IoSession session) {
		return false;
	}

	public IoSession getLongConnection(Context context) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("ConnectionPool#" + context + "#获取长连接.");
		}
		// 获取缓存的连接
		Set<IoSession> cachedConnectiones = context.cachedConnectiones;
		IoSession usableSession = null;
		for (IoSession session : cachedConnectiones) {
			if (session.isConnected() && !session.isWriteSuspended()
					&& !session.isReadSuspended()) {
				// 依次判断每个连接是否可用
				SlipWindow window = (SlipWindow) session
						.getAttribute(keyWindow);
				synchronized (session) {
					if (window.sendable()) {
						if (lock(session))
							usableSession = session;
					}
				}
			} else {
				synchronized (context.cachedConnectiones) {
					context.cachedConnectiones.remove(session);
				}
			}
		}
		if (logger.isDebugEnabled()) {
			if (usableSession == null)
				logger.debug("ConnectionPool#" + context + "#无可用链接.");
			else
				logger.debug("ConnectionPool#" + context + "#SessionId:."
						+ usableSession);
		}

		// 新建连接
		boolean create = false;
		synchronized (context) {
			if (usableSession == null) {
				if (cachedConnectiones.size() < context.maxSize) {
					if (cachedConnectiones.size() < context.maxSize) {
						if (context.creating == 0) {
							// 需要新建链接
							create = true;
							context.creating++;
						} else {
							// 已在新建连接，不新建.
						}
					}
				}
			}
			if (create) {
				try {
					if ((usableSession = context.factory.createConnection()) != null) {
						context.cachedConnectiones.add(usableSession);
					}
				} finally {
					context.creating--;
				}
			}
		}
		return usableSession;
	}

	public IoSession getShortConnection(Context context) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("ConnectionPool#" + context + "#获取短连接.");
		}
		// 获取缓存的连接
		boolean create = false;
		IoSession usableSession = null;
		synchronized (context) {
			if (context.cachedConnectiones.size() + context.creating < context.maxSize) {
				context.creating++;
				create = true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("ConnectionPool#" + context + "#超出最大连接数.");
				}
			}
			if (create) {
				try {
					usableSession = context.factory.createConnection();
				} finally {
					context.creating--;
				}

			}
		}
		return usableSession;
	}

	public void connectionClosed(String key, IoSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("ConnectionPool#" + contextMap.get(key) + "#移除连接。");
		}
		Context context = contextMap.get(key);
		synchronized (context.cachedConnectiones) {
			context.cachedConnectiones.remove(session);
		}
	}

	public class DebugInfo extends Thread {
		public DebugInfo() {
			this.setName("ConnectionPool.DebugInfo:Init.");
		}

		public void run() {
			Logger logger = ConnectionPool.getInstance().logger;
			try {
				while (true) {
					Thread.sleep(5000);
					for (String key : ConnectionPool.getInstance().contextMap
							.keySet()) {
						Set<IoSession> sessions = ConnectionPool
								.getContext(key).cachedConnectiones;
						logger.debug("Key:" + key + "#Size:" + sessions.size());
						for (IoSession session : sessions)
							logger.debug("Session:" + key + "#" + session);
					}
				}
			} catch (InterruptedException e) {
			}
		}
	}

	public static void closeSession(String key, IoSession session) {
		Context context = ConnectionPool.getContext(key);
		synchronized (context.cachedConnectiones) {
			context.cachedConnectiones.remove(session);
			session.close(true);
		}
	}
}
