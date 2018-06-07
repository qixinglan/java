package com.nci.sfj.location.sender;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.sfj.common.util.Config;
import com.nci.sfj.location.LocationMessage;
import com.nci.sfj.location.connect.ConnectionPool;
import com.nci.sfj.xmpp.exception.ConnectionException;
import com.nci.sfj.xmpp.sender.MessageQueue;

public class LocationSender implements Runnable {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean pause = false;
	private MessageQueue qAlarm = new MessageQueue();
	MessageQueue[] queues = new MessageQueue[] { qAlarm };
	private Thread senderThread;
	private Integer currentPriority = 0;
	private Long lastSend = 0L;

	public LocationSender() {
		checkAlive();
	}

	/**
	 * 如果sender线程不正常，新建一个sender线程
	 */
	protected void checkAlive() {
		synchronized (LocationSender.class) {
			if (senderThread == null || !senderThread.isAlive()) {
				senderThread = new Thread(this);
				senderThread.start();
			}
		}
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年6月11日上午10:22:37
	 */
	public void notifySend() {
		checkAlive();
		synchronized (senderThread) {
			senderThread.notifyAll();
		}
	}

	protected void sleep(long mili) throws InterruptedException {
		senderThread.wait(mili);
	}

	protected boolean allQueueFree() {
		for (MessageQueue queue : queues) {
			if (queue.size() > 0)
				return false;
		}
		return true;
	}

	/**
	 * sender线程
	 * 
	 * sender的执行逻辑： 1.依次遍历所有的队列，并发送队列中的消息。 2.如果某一个队列空了，调用fetcher获取消息。
	 * 3.如果遍历队列的过程没有发送任何消息，休息一段时间。 4.如果遍历过程中发现所有的消息队列都是空的，休息一段时间。
	 */
	@Override
	public void run() {
		while (!pause) {
			try {
				boolean empty = true;
				boolean sendAny = false;
				for (int i = 0; i < queues.length; i++) {
					if (logger.isDebugEnabled())
						if (queues[0].size() > 0)
							i = 0;
					synchronized (currentPriority) {
						currentPriority = i;
					}
					if (queues[i].size() != 0) {
						empty = false;
						sendAny = send(queues[i], i);
					} else {
					}
				}
				if (empty) {
					synchronized (senderThread) {
						try {
							if (allQueueFree())
								sleep(60 * 1000);
						} catch (InterruptedException e) {
						}
					}
				} else if (!sendAny) {
					synchronized (senderThread) {
						sleep(100);
					}
				}
			} catch (InterruptedException e) {
			} catch (ConnectionException e) {
				synchronized (senderThread) {
					// sleep(60 * 1000);
				}
			}
		}
	}

	protected long calcWaitTime() {
		int delay = Config.getInt("xmpp.LBS.send.delay", 500);
		long wait = lastSend + delay - System.currentTimeMillis();
		if (wait < 0) {
			wait = 0;
		}
		return wait;
	}

	/**
	 * 发送队列中的消息 先进行流量控制，如果可以发送，直接发送； 如果不满足流量，根据优先级进行处理。
	 * 
	 * @param submits
	 * @param pri
	 *            优先级
	 * @return
	 * @throws InterruptedException
	 * @throws ConnectionException
	 */
	protected boolean send(MessageQueue submits, int pri)
			throws InterruptedException, ConnectionException {
		boolean sendAny = false;
		while (submits.size() > 0) {
			Object submit = submits.peek();
			long wait = calcWaitTime();

			// 优先级是0，等待流量可用
			if (pri == 0 && wait != 0) {
				Thread.sleep(wait);
				wait = 0;
			}
			if (wait == 0) {
				sendAny = true;
				submits.remove(submit);
				send(submit);
			}
		}
		return sendAny;
	}

	protected void send(Object submit) throws ConnectionException {
		try {
			if (submit instanceof LocationMessage) {
				LocationMessage message = (LocationMessage) submit;
				IoSession session = ConnectionPool.getInstance().getConnection(
						"loc");
				if (session == null) {
					throw new ConnectionException("该用户目前不在线。");
				}
				session.write(message);
				lastSend = System.currentTimeMillis();
			}
		} catch (ConnectionException e) {
			throw e;
		} catch (Throwable t) {
			throw new ConnectionException();
		}
	}

	public void addAlarmMessage(Object message) {
		queues[0].addAtTail(message);
	}

	/**
	 * 直接发送消息 不进行任何
	 * 
	 * @param submits
	 * @throws ConnectionException
	 */
	public void sendWithoutQueuing(Object submit) throws ConnectionException {
		send(submit);
	}
}
