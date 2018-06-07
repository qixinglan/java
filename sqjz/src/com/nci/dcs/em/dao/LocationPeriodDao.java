package com.nci.dcs.em.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SysSetting;

@Repository
@Transactional
public class LocationPeriodDao extends HibernateDao<LocationPeriod, String> {

	public void deleteBySys(SysSetting entity) {
		this.createQuery("delete LocationPeriod where sysSetting.settingId=?",
				entity.getSettingId()).executeUpdate();
	}
}
