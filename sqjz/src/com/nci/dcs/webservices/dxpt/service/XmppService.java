package com.nci.dcs.webservices.dxpt.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.sfj.business.dao.SdeviceXmppDao;
import com.nci.sfj.business.dao.XmppDeviceInfoDao;
import com.nci.sfj.business.dao.XmppLogDao;
import com.nci.sfj.business.model.SdeviceXmpp;
import com.nci.sfj.business.model.XmppDeviceInfo;
import com.nci.sfj.business.model.XmppLog;

@Service
@Transactional
public class XmppService extends BaseService<XmppLog, String> {
	@Autowired
	private XmppLogDao xmppLogDao;
	@Autowired
	private SdeviceXmppDao sdeviceXmppDao;
	@Autowired
	private XmppDeviceInfoDao xmppDeviceInfoDao;

	@PostConstruct
	public void orgDevice() {
		List<SdeviceXmpp> devices = sdeviceXmppDao.findByCriteria(Restrictions
				.eq("deviceType", "1"));
		for (SdeviceXmpp device : devices) {
			XmppDeviceInfo info = xmppDeviceInfoDao.findDevice(
					device.getDeviceNumber(), "1");
			if (info == null) {
				info = new XmppDeviceInfo();
				info.setDeviceNumber(device.getDeviceNumber());
				info.setType("1");
			}
			info.setIsLogin("2");
			xmppDeviceInfoDao.save(info);
		}
	}

	public Page<XmppLog> findLogPaged(Page<XmppLog> page) {
		return xmppLogDao.findByCriteria(page);
	}

	public Page<XmppDeviceInfo> findInfoPaged(Page<XmppDeviceInfo> page) {
		return xmppDeviceInfoDao.findByCriteria(page);
	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(XmppLog entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}

	@Override
	public void create(XmppLog entity) {
	}

	@Override
	public void delete(String id) {
		xmppLogDao.delete(id);
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<XmppLog> find() {
		return xmppLogDao.getAll();
	}

	@Override
	public Page<XmppLog> findPaged(Page<XmppLog> page, XmppLog entity) {
		return null;
	}

	public Page<XmppLog> findPaged(Page<XmppLog> page) {
		return xmppLogDao.findByCriteria(page);
	}

	@Override
	public Page<XmppLog> findPaged(Page<XmppLog> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XmppLog get(String id) {
		return xmppLogDao.get(id);
	}

	@Override
	public void update(XmppLog entity) {
	}
}
