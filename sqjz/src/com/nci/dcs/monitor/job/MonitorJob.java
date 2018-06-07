package com.nci.dcs.monitor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nci.dcs.monitor.server.service.DataBaseMonitorService;
import com.nci.dcs.monitor.server.service.LbsServiceMonitorService;
import com.nci.dcs.monitor.server.service.NetworkMonitorService;
import com.nci.dcs.monitor.server.service.ServiceMonitorService;
/**
 *@模块名称：监控运行定时任务
 * @author caolj
 *@date 2016年6月17日 下午3:27:17
 */
@Component
public class MonitorJob {
	@Autowired
	private ServiceMonitorService serviceMonitorService;
	@Autowired
	private DataBaseMonitorService dataBaseMonitorService;
	@Autowired
	private LbsServiceMonitorService lbsServiceMonitorService;
	@Autowired
	private NetworkMonitorService networkMonitorService;

	public void runServiceMonitor() {
		try {
			serviceMonitorService.runServiceMonitor();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void runNetworkMonitor() {
		try {
			networkMonitorService.runNetworkMonitor();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void runLbsMonitor() {
		try {
			lbsServiceMonitorService.runLbsMonitor();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	public void runDataBaseMonitor() {
		try {
			dataBaseMonitorService.runDataBaseMonitor();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
