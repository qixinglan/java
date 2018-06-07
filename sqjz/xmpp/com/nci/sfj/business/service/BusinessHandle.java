package com.nci.sfj.business.service;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nci.sfj.xmpp.auth.AuthToken;
import com.nci.sfj.xmpp.sender.MessageQueue;

/**
 * Description:负责处理报警和定位信息的线程，内部包含两个队列分别处理，报警优先级比定位高
 * 
 * @author Shuzz
 * 
 */
public class BusinessHandle implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean pause = false;
	/**
	 * Description:报警信息的队列
	 */
	private MessageQueue qAlarm = new MessageQueue();
	/**
	 * Description:定位信息的队列
	 */
	private MessageQueue qLocation = new MessageQueue();
	/**
	 * Description:基站信息的队列
	 */
	private MessageQueue qBaseStation = new MessageQueue();
	MessageQueue[] queues = new MessageQueue[] { qAlarm, qLocation,
			qBaseStation };
	private Thread senderThread;
	private Integer currentPriority = 0;
	private XmppBusinessService xmppBusinessService;

	public BusinessHandle(XmppBusinessService xmppBusinessService) {
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
	@Override
	public void run() {
		while (!pause) {
			try {
				boolean empty = true;
				// 依次遍历所有的队列，并发送队列中的消息。
				for (int i = 0; i < queues.length; i++) {
					if (logger.isDebugEnabled())
						if (queues[0].size() > 0)
							i = 0;
					synchronized (currentPriority) {
						currentPriority = i;
					}
					if (queues[i].size() != 0) {
						// 每个队列只发第一个消息
						empty = false;
						Object submit = queues[i].peek();
						queues[i].remove(submit);
						xmppBusinessService.handleBusiness(i, submit);
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

	/**
	 * Description:激活休眠中的线程进行业务处理
	 * 
	 * @author Shuzz
	 */
	private void notifySend() {
		checkAlive();
		synchronized (senderThread) {
			senderThread.notifyAll();
		}
	}

	/**
	 * Description:向队列末尾加入报警信息
	 * 
	 * @author Shuzz
	 * @param element
	 * @param token 记录登录信息的token
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAlarmMessage(Element element, AuthToken token) {
		List message = new ArrayList();
		message.add(element);
		message.add(token);
		queues[0].addAtTail(message);
		// 增加完信息后激活线程进行处理
		notifySend();
	}

	/**
	 * Description:向队列末尾加入定位信息
	 * 
	 * @author Shuzz
	 * @param element
	 * @param token 记录登录信息的token
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addLocationMessage(Element element, AuthToken token) {
		List message = new ArrayList();
		message.add(element);
		message.add(token);
		queues[1].addAtTail(message);
		// 增加完信息后激活线程进行处理
		notifySend();
	}

	/**
	 * Description:向队列末尾加入基站信息
	 * 
	 * @author Shuzz
	 * @param element 
	 * @param token 记录登录信息的token
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addBaseStationMessage(Element element, AuthToken token) {
		List message = new ArrayList();
		message.add(element);
		message.add(token);
		queues[2].addAtTail(message);
		// 增加完信息后激活线程进行处理
		notifySend();
	}

	public int getAlarmSize() {
		return qAlarm.size();
	}

	public int getLocationSize() {
		return qLocation.size();
	}
}