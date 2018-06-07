package com.nci.dcs.em.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.em.model.AreaInfo;

@Repository
@Transactional
public class AreaInfoDao extends HibernateDao<AreaInfo, String> {

	public List<AreaInfo> findBj() {
		return find("from AreaInfo where parentID=?", "1");
	}
}
