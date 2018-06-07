package com.nci.sfj.business.service;

import java.util.Set;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.em.dao.SysSettingDao;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SysSetting;
import com.nci.sfj.business.model.AlarmTypeXmpp;
import com.nci.sfj.common.util.XMPPConstants;
import com.nci.sfj.xmpp.auth.AuthToken;

/**
 * Description:提供至移动终端的Service
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
@Service
@Transactional
public class SettingService {
	@Autowired
	private DbService dbService;
	@Autowired
	private SysSettingDao sysSettingDao;

	public Element getSetting(String deviceNumber) {
		Element result = DocumentFactory.getInstance().createElement("result");
		try {
			String settingId = dbService.findSysSettingByDevice(deviceNumber);
			String frequency = "frequency(";
			if(settingId!=null){
				SysSetting set = sysSettingDao.get(settingId);
				Set<LocationPeriod> locs = set.getLocPeriods();
				if (locs.size() > 0) {
					for (LocationPeriod loc : locs) {
						frequency = frequency + loc.getStartTime() + "@"
								+ loc.getEndTiem() + "@" + loc.getSpace() + "#";
					}
					frequency = frequency.substring(0, frequency.length() - 1);
				}
			}
			frequency = frequency + ")";
			AlarmTypeXmpp alarmType = dbService
					.findAlramTypeByType(XMPPConstants.ALARM_TYPE_SLIENT);
			Integer time = new Integer(1);
			if (alarmType.getSilentTime() != null) {
				time = alarmType.getSilentTime();
			}
			time = time * 60;
			String silent = "silent(0:00@24:00@" + time + ")";
			AuthToken token = dbService.findFxryByDevice(deviceNumber);
			boolean run = false;
			if (token != null && !CommonUtils.isNull(token.getFxryId())) {
				run = true;
			}
			String running = "running(" + run + ")";
			result.setText(frequency + "%" + silent + "%" + running);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
