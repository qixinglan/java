package com.nci.dcs.data.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.data.dao.JjszDao;
import com.nci.dcs.data.model.CcGatherAlarmIgnore;
import com.nci.dcs.data.model.CcIgnoreOrg;
import com.nci.dcs.data.model.CcIgnorePerson;
import com.nci.dcs.em.dao.IgnoreOrgDao;
import com.nci.dcs.em.dao.IgnorePersonDao;

@Service
@Transactional
public class JjszSevice extends BaseService<CcGatherAlarmIgnore, String> {

	private Map<String, Map<String, Ignore>> cached_ignore;
	@Autowired
	private JjszDao jjszDao;
	@Autowired
	private IgnorePersonDao ignorePersonDao;
	@Autowired
	private IgnoreOrgDao ignoreOrgDao;

	@PostConstruct
	private void getOrgRoot() {
		List<CcGatherAlarmIgnore> ignores = jjszDao.findByCriteria(Restrictions
				.ge("endTime", new Date()));
		cached_ignore = new HashMap<String, Map<String, Ignore>>(0);
		for (CcGatherAlarmIgnore ignore : ignores) {
			Ignore ig = new Ignore(ignore);
			Map<String, Ignore> map = cached_ignore.get(ignore.getOrgId());
			if (null == map) {
				map = new HashMap<String, JjszSevice.Ignore>(0);
				cached_ignore.put(ignore.getOrgId(), map);
			}
			map.put(ignore.getIgnoreId(), ig);
		}
	}

	@Override
	public void create(CcGatherAlarmIgnore entity) {
		// TODO Auto-generated method stub
		jjszDao.save(entity);
		addCache(entity.getOrgId(), entity);
		// jjszDao.getSession().clear();
	}

	@Override
	public void update(CcGatherAlarmIgnore entity) {
		// TODO Auto-generated method stub
		jjszDao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		CcGatherAlarmIgnore ignore = jjszDao.get(id);
		jjszDao.delete(id);
		removeCache(ignore.getOrgId(), id);
		// jjszDao.getSession().flush();
	}

	@Override
	public CcGatherAlarmIgnore get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcGatherAlarmIgnore> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CcGatherAlarmIgnore> FindByJgId(String JgId) {

		return jjszDao.findByProperty("orgId", JgId);
	}

	@Override
	public Page<CcGatherAlarmIgnore> findPaged(Page<CcGatherAlarmIgnore> page,
			CcGatherAlarmIgnore entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<CcGatherAlarmIgnore> findPaged(Page<CcGatherAlarmIgnore> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcGatherAlarmIgnore entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object[]> getfxryData(String cCode) {
		String sqlBf = "select * from view_fxry_idandname where orgId='"
				+ cCode + "'";
		return jjszDao.createSQLQuery(sqlBf.toString()).list();
	}

	public synchronized void removeCache(String orgId, String id) {
		Map<String, Ignore> map = cached_ignore.get(orgId);
		if (map != null) {
			map.remove(id);
			if (map.isEmpty()) {
				cached_ignore.remove(orgId);
			}
		}
	}

	public synchronized void addCache(String orgId, CcGatherAlarmIgnore ignore) {
		if (null != ignore && ignore.getEndTime().after(new Date())) {
			Map<String, Ignore> map = cached_ignore.get(orgId);
			Ignore ig = new Ignore(ignore);
			if (map == null) {
				map = new HashMap<String, Ignore>(0);
				cached_ignore.put(orgId, map);
			}
			map.put(ignore.getIgnoreId(), ig);
		}
	}

	public boolean isGatherIgnore(String orgId, String fxryId, String fxryId1,
			Date time) {
		Map<String, Ignore> map = cached_ignore.get(orgId);
		if(map!=null){
			for (String id : map.keySet()) {
				Ignore ignore = map.get(id);
				if (ignore.end.before(time)) {
					removeCache(orgId, id);
				} else if (ignore.start.before(time)) {
					if (ignore.ignores.containsKey(fxryId)
							&& ignore.ignores.containsKey(fxryId1)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	class Ignore {
		Ignore(CcGatherAlarmIgnore ignore) {
			this.start = ignore.getStartTime();
			this.end = ignore.getEndTime();
			this.ignores = new HashMap<String, String>();
			List<CcIgnoreOrg> orgs = ignoreOrgDao.findByCriteria(Restrictions
					.eq("ignoreId", ignore.getIgnoreId()));
			if (CommonUtils.isNotNull(orgs)) {
				for (CcIgnoreOrg org : orgs) {
					this.ignores.put(org.getOrgId(), "");
				}
			} else {
				List<CcIgnorePerson> persons = ignorePersonDao
						.findByCriteria(Restrictions.eq("ignoreId",
								ignore.getIgnoreId()));
				if (CommonUtils.isNotNull(persons)) {
					for (CcIgnorePerson per : persons) {
						this.ignores.put(per.getPersonId(), "");
					}
				}
			}
		}

		Map<String, String> ignores;
		Date start;
		Date end;
	}
}
