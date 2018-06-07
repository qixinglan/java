package com.nci.dcs.jzgl.dagl.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.base.xmpp.impl.DefaultXmppWork;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.dagl.service.devicecheck.CPUHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.CloseStateHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.DeviceCheckHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.DiskHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.EQuantityHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.FrequencyHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.MemoryHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.NLocationHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.PhoneHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.ShakeHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.SilentHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.TimeHandle;
import com.nci.dcs.jzgl.dagl.service.devicecheck.WorkStateHandle;
import com.nci.sfj.business.model.AlarmTypeXmpp;
import com.nci.sfj.business.model.MsgWaitXmpp;
import com.nci.sfj.business.service.DbService;
import com.nci.sfj.common.util.XMPPConstants;
import com.nci.sfj.xmpp.sender.EmMessageReceive.Type;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

@Service
public class DeviceCheckService {
	@Autowired
	private EmMessageSendService emMessageSendService;
	@Autowired
	private DbService dbService;
	private Map<String, CheckThread> ctMap = new HashMap<String, CheckThread>(0);

	public String deviceCheck(int type, String deviceCode) {
		String result = "";
		boolean flag = false;
		String id = "";
		CheckThread ct = null;
		switch (type) {
		case 1:
			// 检测主机通信情况
			flag = emMessageSendService.checkOnline(deviceCode);
			result = flag + "@" + (flag ? "通信成功" : "通信失败");
			break;
		case 2:
			// 设置主机时间
			ct = new CheckThread(new TimeHandle(new Date()));
			id = emMessageSendService.setTime(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 3:
			// 设置腕表时间
			ct = new CheckThread(new TimeHandle(new Date()));
			id = emMessageSendService.setTime(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 4:
			// 设置采集周期
			ct = new CheckThread(new FrequencyHandle());
			List<MsgWaitXmpp> msg = dbService.findMsgWaits(deviceCode,
					XMPPConstants.FREQUENCY);
			if (CommonUtils.isNotNull(msg)) {
				id = emMessageSendService.setFrequencyByDevice(msg.get(0)
						.getContent(), deviceCode);
			} else {
				String settingId = dbService.findSysSettingByDevice(deviceCode);
				id = emMessageSendService.setFrequencyByDevice(settingId,
						deviceCode);
			}
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 5:
			// 设置静默时间
			AlarmTypeXmpp alarmType = dbService
					.findAlramTypeByType(XMPPConstants.ALARM_TYPE_SLIENT);
			Integer time = new Integer(1);
			if (alarmType.getSilentTime() != null) {
				time = alarmType.getSilentTime();
			}
			time = time * 60;
			ct = new CheckThread(new SilentHandle(time));
			id = emMessageSendService.setSilentListByDevice(time.toString(),
					deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 6:
			// 主机闪存状况
			ct = new CheckThread(new DiskHandle());
			id = emMessageSendService.getDiskInfo(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 7:
			// 主机电池状况
			ct = new CheckThread(new EQuantityHandle());
			id = emMessageSendService.getEQuantityInfo(deviceCode, "phone");
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 8:
			// 腕表电池状况
			ct = new CheckThread(new EQuantityHandle());
			id = emMessageSendService.getEQuantityInfo(deviceCode, "watch");
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 9:
			// 主机CUP状况
			ct = new CheckThread(new CPUHandle());
			id = emMessageSendService.getCPUInfo(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 10:
			// 主机内存状况
			ct = new CheckThread(new MemoryHandle());
			id = emMessageSendService.getMemoryInfo(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 11:
			// 主机手机号
			ct = new CheckThread(new PhoneHandle());
			id = emMessageSendService.getSIMInfo(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 12:
			// 腕表闭合状态
			ct = new CheckThread(new CloseStateHandle());
			id = emMessageSendService.getWatchCloseState(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 13:
			// 主机工作情况
			ct = new CheckThread(new WorkStateHandle());
			id = emMessageSendService.getWorkState(deviceCode, "phone");
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 14:
			// 腕表工作情况
			ct = new CheckThread(new WorkStateHandle());
			id = emMessageSendService.getWorkState(deviceCode, "watch");
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 15:// 主机震动状况
			ct = new CheckThread(new ShakeHandle());
			id = emMessageSendService.shakeTest(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		case 16:// 定位功能检测
			ct = new CheckThread(new NLocationHandle());
			id = emMessageSendService.getLocation(deviceCode);
			ctMap.put(id, ct);
			result = ct.waitResult();
			break;
		default:
			ct = null;
			result = "false@无该检测项";
			break;
		}
		return result;
	}

	public void receiveResult(String id, Type type, Element ele) {
		int i = 0;
		boolean flag = true;
		while (i < 2 && flag) {
			if (ctMap.get(id) != null) {
				flag = !flag;
				if (type.equals(Type.SUCCESS)) {
					ctMap.get(id).success(ele);
				} else if (type.equals(Type.ERROR)) {
					ctMap.get(id).error(ele);
				} else if (type.equals(Type.TIMEOUT)) {
					ctMap.get(id).timeout(ele);
				}

				ctMap.remove(id);
			} else {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}
			i++;
		}
	}

	class CheckThread extends DefaultXmppWork<String> implements Runnable {
		private Thread senderThread;
		private String result = "";
		private boolean timeOut = false;
		private DeviceCheckHandle handle;

		public CheckThread(DeviceCheckHandle handle) {
			this.handle = handle;
			setFinished(false);
			checkAlive();
		}

		protected void checkAlive() {
			if (senderThread == null || !senderThread.isAlive()) {
				senderThread = new Thread(this);
				senderThread.start();
			}
		}

		public void error(Element ele) {
			result = handle.error("", ele);
			notifyWiat();
		}

		public void success(Element ele) {
			result = handle.success("", ele);
			notifyWiat();
		}

		public void timeout(Element ele) {
			result = handle.timeout("", ele);
			notifyWiat();
		}

		private void notifyWiat() {
			finish();
			synchronized (senderThread) {
				senderThread.notifyAll();
			}
		}

		public void run() {
			while (!finished() && !timeOut) {
				try {
					synchronized (senderThread) {
						senderThread.wait(60 * 1000L);
						timeOut = true;
					}
				} catch (Exception e) {
					timeOut = true;
				}
			}
			if (!finished()) {
				handleTimeOut();
			}
		}

		private void handleTimeOut() {
			result = handle.timeout("", null);
		}

		public String waitResult() {
			run();
			return result;
		}
	}

	public void deviceClose(String deviceCode) {
		emMessageSendService.powerOff(deviceCode);
	}
}
