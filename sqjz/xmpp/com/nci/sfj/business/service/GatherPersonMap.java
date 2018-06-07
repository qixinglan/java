package com.nci.sfj.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GatherPersonMap {
	private static GatherPersonMap instance;
	private Map<String, Map<String, GatherTime>> gatherPersons = new HashMap<String, Map<String, GatherTime>>(
			0);

	public static GatherPersonMap getInstance() {
		if (instance == null) {
			synchronized (GatherPersonMap.class) {
				instance = new GatherPersonMap();
			}
		}
		return instance;
	}

	/**
	 * Description:向两两聚集map中新增该人员的数据
	 * 
	 * @author Shuzz
	 * @since 2014年7月24日下午1:59:58
	 * @param per1
	 *            人员id1
	 * @param per2
	 *            人员id2
	 * @param time
	 *            报警时间
	 * @return
	 */
	public synchronized long addPerson(String per1, String per2, Date time) {
		Map<String, GatherTime> p1 = gatherPersons.get(per1);
		GatherTime gt = null;
		if (p1 == null) {
			p1 = new HashMap<String, GatherTime>(0);
			gatherPersons.put(per1, p1);
		}
		if (p1.get(per2) != null) {
			gt = p1.get(per2);
			gt.setEndTime(time);
		} else {
			gt = new GatherTime();
			gt.setStartTime(time);
			gt.setEndTime(time);
			p1.put(per2, gt);
		}
		Map<String, GatherTime> p2 = gatherPersons.get(per2);
		if (p2 == null) {
			p2 = new HashMap<String, GatherTime>(0);
			gatherPersons.put(per2, p2);
		}
		p2.put(per1, gt);
		return gt.getEndTime().getTime() - gt.getStartTime().getTime();
	}
}
