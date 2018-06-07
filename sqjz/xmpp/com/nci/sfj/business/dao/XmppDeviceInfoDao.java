package com.nci.sfj.business.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.sfj.business.model.XmppDeviceInfo;

/**
 * Description:记录通信设备状况信息的DAO层
 * 
 * @author Shuzz
 */
@Repository
public class XmppDeviceInfoDao extends HibernateDao<XmppDeviceInfo, String> {
	/**
	 * Description:根据设备编号和类型获取其登录状态
	 * 
	 * @author Shuzz
	 * @param deviceNumber
	 *            设备编号
	 * @param deviceType
	 *            设备类型 (1:腕表端;2:移动执法终端端)
	 * @return 记录设备登录状态的实体
	 */
	public XmppDeviceInfo findDevice(String deviceNumber, String deviceType) {
		List<XmppDeviceInfo> infos = findByCriteria(
				Restrictions.eq("deviceNumber", deviceNumber),
				Restrictions.eq("type", deviceType));
		if (!CommonUtils.isNull(infos)) {
			return infos.get(0);
		}
		return null;
	}
}
