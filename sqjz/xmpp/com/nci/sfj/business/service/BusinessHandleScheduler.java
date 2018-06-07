package com.nci.sfj.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Element;

import com.nci.sfj.xmpp.auth.AuthToken;

/**
 * Description:负责处理分发业务消息的service
 * 
 * @author Shuzz
 * 
 */
public class BusinessHandleScheduler {
	/**
	 * Description:业务信息处理线程
	 */
	private List<BusinessHandle> bhandles = new ArrayList<BusinessHandle>(0);
	/**
	 * Description:越界消息处理线程
	 */
	private List<CrossBorderHandle> chandles = new ArrayList<CrossBorderHandle>(
			0);
	/**
	 * Description:业务处理线程总数
	 */
	private int bthreadCount;
	/**
	 * Description:越界报警业务处理线程总数
	 */
	private int cthreadCount;

	/**
	 * Description:当前负责处理定位信息的业务线程序号
	 */
	private int location = 0;
	/**
	 * Description:当前负责处理基站信息的业务线程序号
	 */
	private int baseStation = 0;
	/**
	 * Description:当前负责处理报警信息的业务线程序号
	 */
	private int alarm = 0;
	/**
	 * Description:当前负责处理越界信息的业务线程序号
	 */
	private int cross = 0;

	/**
	 * Description:初始化各中业务处理的线程数量
	 * 
	 * @author Shuzz
	 * @since 2014年9月9日上午11:11:20
	 * @param bthreadCount
	 *            业务信息处理线程数量
	 * @param cthreadCount
	 *            越界消息处理线程数量
	 * @param xmppBusinessService
	 */
	public BusinessHandleScheduler(int bthreadCount, int cthreadCount,
			XmppBusinessService xmppBusinessService) {
		// 异常设置处理
		if (bthreadCount < 0) {
			bthreadCount = 5;
		}
		if (cthreadCount < 0) {
			cthreadCount = 5;
		}
		this.bthreadCount = bthreadCount;
		this.cthreadCount = cthreadCount;
		for (int i = 0; i < bthreadCount; i++) {
			BusinessHandle bh = new BusinessHandle(xmppBusinessService);
			bhandles.add(bh);
		}
		for (int i = 0; i < cthreadCount; i++) {
			CrossBorderHandle bh = new CrossBorderHandle(xmppBusinessService);
			chandles.add(bh);
		}
	}

	/**
	 * Description:新增一个需要处理的定位信息，循环分配线程
	 * 
	 * @author Shuzz
	 * @param element
	 *            定位信息XMLdom4j实体
	 * @param token
	 *            记录登录信息的token
	 */
	public void addLocationMessage(Element element, AuthToken token) {
		BusinessHandle handle = bhandles.get(location);
		handle.addLocationMessage(element, token);
		location = (location + 1) % bthreadCount;
	}

	/**
	 * Description:新增一个需要处理的基站信息，循环分配处理线程
	 * 
	 * @author Shuzz
	 * @param element
	 *            基站信息XMLdom4j实体
	 * @param token
	 *            记录登录信息的token
	 */
	public void addBaseStationMessage(Element element, AuthToken token) {
		BusinessHandle handle = bhandles.get(baseStation);
		handle.addBaseStationMessage(element, token);
		baseStation = (baseStation + 1) % bthreadCount;
	}

	/**
	 * Description:新增一个需要处理的报警信息，循环分配线程
	 * 
	 * @author Shuzz
	 * @param element
	 *            报警信息XMLdom4j实体
	 * @param token
	 *            记录登录信息的token
	 */
	public void addAlarmMessage(Element element, AuthToken token) {
		bhandles.get(alarm).addAlarmMessage(element, token);
		alarm = (alarm + 1) % bthreadCount;
	}

	/**
	 * Description:新增一个需要处理的越界信息，循环分配线程
	 * 
	 * @author Shuzz
	 * @since 2015年9月17日上午11:08:47
	 * @param x
	 *            经度
	 * @param y
	 *            纬度
	 * @param time
	 *            时间
	 * @param token
	 *            记录登录信息的token
	 * @param locId
	 *            定位信息的主键
	 */
	public void addCrossBorder(String x, String y, Date time, AuthToken token,
			String locId) {
		CrossBorderHandle handle = chandles.get(cross);
		handle.addCrossBorder(x, y, time, token, locId);
		cross = (cross + 1) % cthreadCount;
	}

	public int getBthreadCount() {
		return bthreadCount;
	}

	public void setBthreadCount(int bthreadCount) {
		this.bthreadCount = bthreadCount;
	}

	public int getCthreadCount() {
		return cthreadCount;
	}

	public void setCthreadCount(int cthreadCount) {
		this.cthreadCount = cthreadCount;
	}
}
