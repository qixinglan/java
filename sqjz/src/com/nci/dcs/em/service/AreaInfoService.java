package com.nci.dcs.em.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.AreaInfoDao;
import com.nci.dcs.em.model.AreaInfo;
import com.nci.dcs.official.dto.JsonObj;

@Service
@Transactional
public class AreaInfoService extends BaseService<AreaInfo, String> {
	@Autowired
	private AreaInfoDao areaInfoDao;
	private List<AreaInfo> cachedBj;

	@PostConstruct
	public void getCache() {
		loadCache(false);
	}

	public synchronized void loadCache(boolean reload) {
		if (cachedBj != null && cachedBj.size() > 0 && !reload) {
			return;
		}
		try {
			cachedBj = areaInfoDao.findBj();
		} catch (Exception e) {
			cachedBj = null;
		}
	}

	@Override
	public void create(AreaInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AreaInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public AreaInfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaInfo> find() {
		// TODO Auto-generated method stub
		return areaInfoDao.getAll();
	}

	public List<JsonObj> findJson() {
		// TODO Auto-generated method stub
		List<AreaInfo> areas = areaInfoDao.getAll();
		List<JsonObj> jsons = new ArrayList<JsonObj>();
		for (AreaInfo area : areas) {
			JsonObj json = new JsonObj();
			json.setCode(area.getAreaId());
			json.setCodeDesc(area.getAreaName());
			jsons.add(json);
		}
		return jsons;
	}

	@Override
	public Page<AreaInfo> findPaged(Page<AreaInfo> page, AreaInfo entity) {
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
	public Page<AreaInfo> findPaged(Page<AreaInfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(AreaInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AreaInfo> getCached() {
		if(cachedBj == null || cachedBj.size() <= 0 ){
			loadCache(true);
		}
		return cachedBj;
	}

	public void setCached(List<AreaInfo> cached) {
		this.cachedBj = cached;
	}

}
