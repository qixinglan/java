package com.nci.dcs.jzgl.sync.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nci.dcs.jzgl.sync.service.SyncDataJobService;

/**
 * Description:生成服刑人员数据的定时任务
 * 
 * @author Shuzz
 * @since 2014年12月15日下午4:22:14
 * 
 */
@Component
public class SyncJob {
	@Autowired
	private SyncDataJobService syncDataJobService;

	public void startTimer() {
		try {
			syncDataJobService.beforeCreateSyncDate();
			syncDataJobService.createSyncData();
			syncDataJobService.afterCreateSyncDate();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
