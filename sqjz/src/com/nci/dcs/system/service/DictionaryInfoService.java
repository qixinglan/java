package com.nci.dcs.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.directwebremoting.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.system.dao.DictionaryDetailDao;
import com.nci.dcs.system.dao.DictionaryInfoDao;
import com.nci.dcs.system.model.DictionaryDetail;
import com.nci.dcs.system.model.DictionaryInfo;
import com.nci.dcs.system.model.DictionaryKeyValue;

@Service
@Transactional
public class DictionaryInfoService extends BaseService<DictionaryInfo, String> {
	private Logger logger = Logger.getLogger(DictionaryInfoService.class);
	@Autowired
	private DictionaryInfoDao dictInfoDao;
	@Autowired
	private DictionaryDetailDao dictionaryDetailDao;
	private Map<String, List<DictionaryKeyValue>> cached_dict;
	private Date lastModified;

	public Map<String, List<DictionaryKeyValue>> getDict() {
		if (cached_dict == null && cached_dict.size() == 0) {
			loadCache(false);
		}
		return cached_dict;
	}

	public List<DictionaryKeyValue> findItems(String code) {
		if (cached_dict != null && cached_dict.containsKey(code)) {
			return cached_dict.get(code);
		}
		return null;
	}

	public Map<String, String> findItemsMap(String code) {
		List<DictionaryKeyValue> list = findItems(code);
		Map<String, String> map = new HashMap<String, String>();
		if (list != null) {
			for (DictionaryKeyValue keypair : list) {
				map.put(keypair.getCode(), keypair.getName());
			}
		}
		return map;
	}

	@PostConstruct
	public void getCache() {
		loadCache(false);
	}

	public synchronized void loadCache(boolean reload) {
		if (cached_dict != null && cached_dict.size() > 0 && !reload) {
			return;
		}
		try {
			logger.info("加载字典缓存");
			{
				cached_dict = new HashMap<String, List<DictionaryKeyValue>>();
				List<DictionaryInfo> dicts = this.find();
				for (DictionaryInfo dict : dicts) {
					Set<DictionaryDetail> items = dict.getDictionaryDetails();
					String key = dict.getCode();
					if (items == null) {
						continue;
					}
					List<DictionaryKeyValue> list = new ArrayList();
					for (DictionaryDetail item : items) {
						DictionaryKeyValue keyvalue = new DictionaryKeyValue();
						keyvalue.setName(item.getName());
						keyvalue.setCode(item.getCode());
						keyvalue.setUsing(item.IsUsing());
						keyvalue.setParent(item.getParentID());
						list.add(keyvalue);
					}
					cached_dict.put(key, list);
					lastModified = new Date();
				}
			}
			logger.info("加载字典缓存成功:" + cached_dict.size());
		} catch (Exception e) {
			logger.error("加载字典缓存失败:", e);
		}
	}

	public Date getLastModified() {
		return lastModified;
	}

	@Override
	public List<DictionaryInfo> find() {
		return dictInfoDao.getAll();
	}

	@Override
	public void create(DictionaryInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DictionaryInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DictionaryInfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DictionaryInfo> findPaged(Page<DictionaryInfo> page,
			DictionaryInfo entity) {
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
	public Page<DictionaryInfo> findPaged(Page<DictionaryInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(DictionaryInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getDictMapByKey(String key) {
		Map<String, String> result = new HashMap<String, String>();
		List<DictionaryKeyValue> list = getDict().get(key);
		for (DictionaryKeyValue ll : list) {
			if(ll.isUsing()){
				result.put(ll.getCode(), ll.getName());
			}
		}
		return result;
	}
}
