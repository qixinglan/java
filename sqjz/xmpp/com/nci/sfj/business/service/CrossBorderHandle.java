package com.nci.sfj.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.sender.MessageQueue;

/**
 * Description:负责处理越界信息的线程，内部包含一个队列
 * 
 * @author Shuzz
 * @since 2014年9月9日上午11:07:26
 * 
 */
public class CrossBorderHandle implements Runnable {
	private XmppBusinessService xmppBusinessService;
	private Thread senderThread;
	private boolean pause = false;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private MessageQueue qCrossBorder = new MessageQueue();
	MessageQueue[] queues = new MessageQueue[] { qCrossBorder };

	public CrossBorderHandle(XmppBusinessService xmppBusinessService) {
		this.xmppBusinessService = xmppBusinessService;
		checkAlive();
	}

	/**
	 * 如果sender线程不正常，新建一个sender线程
	 */
	protected void checkAlive() {
		synchronized (BusinessHandle.class) {
			if (senderThread == null || !senderThread.isAlive()) {
				senderThread = new Thread(this);
				senderThread.start();
			}
		}
	}

	protected void sleep(long mili) throws InterruptedException {
		senderThread.wait(mili);
	}

	/**
	 * Description:判断所有线程是否都空闲
	 * 
	 * @return
	 */
	protected boolean allQueueFree() {
		for (MessageQueue queue : queues) {
			if (queue.size() > 0)
				return false;
		}
		return true;
	}

	/**
	 * Description:消息按优先级进行处理
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		while (!pause) {
			try {
				boolean empty = true;
				// 依次遍历所有的队列，并发送队列中的消息。
				for (int i = 0; i < queues.length; i++) {
					if (queues[i].size() != 0) {
						empty = false;
						Object submit = queues[i].peek();
						queues[i].remove(submit);
						List l = (List) submit;
						xmppBusinessService.calCrossBorder((String) l.get(0),
								(String) l.get(1), (Date) l.get(2),
								(AuthToken) l.get(3), (String) l.get(4));
					}
				}
				if (empty) {
					synchronized (senderThread) {
						try {
							// 如果遍历队列的过程没有发送任何消息,且所有的消息队列都是空的，休息一段时间
							if (allQueueFree())
								sleep(60 * 1000);
						} catch (InterruptedException e) {
						}
					}
				}
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
	}

	private void notifySend() {
		checkAlive();
		synchronized (senderThread) {
			senderThread.notifyAll();
		}
	}

	/**
	 * Description:向队列末尾加入越界信息
	 * 
	 * @author Shuzz
	 * @param x
	 *            经度
	 * @param y
	 *            纬度
	 * @param time
	 *            定位时间
	 * @param token
	 *            记录登录信息的token
	 * @param locId
	 *            定位信息的主键
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addCrossBorder(String x, String y, Date time, AuthToken token,
			String locId) {
		List message = new ArrayList();
		message.add(x);
		message.add(y);
		message.add(time);
		message.add(token);
		message.add(locId);
		queues[0].addAtTail(message);
		// 增加完信息后激活线程进行处理
		notifySend();
	}

	public int getCrossBorderSize() {
		return qCrossBorder.size();
	}
}
