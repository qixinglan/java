package com.nci.dcs.jzgl.sync.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.sync.dao.FxrySyncDataDao;
import com.nci.dcs.jzgl.sync.model.FxrySyncData;

@Service
@Transactional
public class FxrySyncDataService extends BaseService<FxrySyncData, String> {

	@Autowired
	private FxrySyncDataDao fxrySyncDataDao;

	public Page<FxrySyncData> findPaged(Page<FxrySyncData> page) {
		return fxrySyncDataDao.findByCriteria(page);
	}

	public List<FxrySyncData> findFxryDataByType(String type, String fxryId) {
		Criteria criteria = fxrySyncDataDao.createCriteria(
				Restrictions.eq("fxryId", fxryId),
				Restrictions.eq("dataType", type));
		return criteria.list();
	}

	@Override
	public void create(FxrySyncData entity) {
		fxrySyncDataDao.save(entity);
	}

	@Override
	public void update(FxrySyncData entity) {
	}

	@Override
	public void delete(String id) {
	}
	public void delete(FxrySyncData entity) {
		fxrySyncDataDao.delete(entity);
	}
	@Override
	public FxrySyncData get(String id) {
		return fxrySyncDataDao.get(id);
	}

	@Override
	public List<FxrySyncData> find() {
		return null;
	}

	@Override
	public Page<FxrySyncData> findPaged(Page<FxrySyncData> page,
			FxrySyncData entity) {
		return fxrySyncDataDao.findByCriteria(page);
	}

	@Override
	public void enable(String id) throws Exception {

	}

	@Override
	public void disable(String id) throws Exception {

	}

	@Override
	public void audit(String id) throws Exception {

	}

	@Override
	public Page<FxrySyncData> findPaged(Page<FxrySyncData> page, String hql,
			Object... values) {
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxrySyncData entity) {
		return null;
	}

	public void callProcedure(String procedure) {
		CallableStatement stmt = null;
		try {
			stmt = fxrySyncDataDao.getSession().connection()
					.prepareCall("{call " + procedure + "}");
			stmt.execute();
		} catch (HibernateException e) {
			logger.error("", e);
		} catch (SQLException e) {
			logger.error("", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
				stmt = null;
			}
		}
	}
}
