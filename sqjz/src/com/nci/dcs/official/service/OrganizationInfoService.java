package com.nci.dcs.official.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.page.JsonTree;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.ReflectionUtils;
import com.nci.dcs.official.dao.OrganizationInfoDao;
import com.nci.dcs.official.dto.OrganizationKeyValue;
import com.nci.dcs.official.model.OrganizationInfo;

@Service
@Transactional
public class OrganizationInfoService extends
		BaseService<OrganizationInfo, String> {
	@Autowired
	private OrganizationInfoDao organizationInfoDao;
	private Date lastModified;
	private Map<String, List<OrganizationKeyValue>> cached_org;

	@SuppressWarnings("rawtypes")
	public List getChildrenIds(String id) {
		return organizationInfoDao.getChildrenIds(id);
	}
	//根据单位名称检查单位是否存在
	public boolean checkByCname(String cname){
		if(organizationInfoDao.findByProperty("cname",cname)!=null){
			return true;
		}
		return false;
	}
	/**
	 * @name 获取本级、下一级机关代码
	 * @param id
	 * @return
	 * @author clj
	 * @date 2014年8月29日 上午11:24:02
	 * @message：
	 */
	public List<?> getBxyjOrgIds(String id) {
		return organizationInfoDao.getBxyjOrgIds(id);
	}

	@SuppressWarnings("unchecked")
	public String getChildrenIdsString(String id) {
		List<String> ids = getChildrenIds(id);
		StringBuffer sb = new StringBuffer();
		for (String tid : ids) {
			sb.append(tid + ",");
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return "";
	}

	@PostConstruct
	public void getCache() {
		loadCache(false);
	}

	private void orgOrgInfo(OrganizationKeyValue org, String parentId) {
		List<OrganizationInfo> subOrgs = organizationInfoDao
				.find("from OrganizationInfo where supOrg.orgId = ? and source='1' order by orgType asc,orgId asc",
						parentId);
		for (OrganizationInfo subOrg : subOrgs) {

			if (org.getChildren() == null) {
				org.setChildren(new ArrayList<OrganizationKeyValue>());
			}
			List<OrganizationKeyValue> ll = org.getChildren();
			OrganizationKeyValue sub = new OrganizationKeyValue(subOrg);
			orgOrgInfo(sub, subOrg.getOrgId());
			ll.add(sub);
		}
	}

	public synchronized void loadCache(boolean reload) {
		if (cached_org != null && cached_org.size() > 0 && !reload) {
			return;
		}
		try {
			OrganizationInfo rootOrg = this.findRoot();
			if (rootOrg != null) {
				OrganizationKeyValue root = new OrganizationKeyValue(rootOrg);
				orgOrgInfo(root, rootOrg.getOrgId());
				cached_org = new HashMap<String, List<OrganizationKeyValue>>();
				List<OrganizationKeyValue> list = new ArrayList<OrganizationKeyValue>();
				list.add(root);
				cached_org.put("0", list);
				lastModified = new Date();
			}
		} catch (Exception e) {
			logger.error("初始化机构出错", e);
			cached_org = null;
		}
	}

	public List<JsonTree> findJsonByParentId(String parentId) {
		Criteria criteria = getTreeCriteria();
		criteria.add(Restrictions.eq("supOrg.orgId", parentId));
		List<OrganizationInfo> orgs = criteria.list();
		List<JsonTree> results = new ArrayList<JsonTree>();
		for (OrganizationInfo org : orgs) {
			JsonTree json = orgJsonTree(org);
			results.add(json);
		}
		return results;
	}

	public HashMap<String, String> loadOrg() {
		String hql = "from OrganizationInfo where orgType=3 or orgType = 2 or orgType=1 order by orgType asc";
		List<OrganizationInfo> orgs = organizationInfoDao.find(hql);
		HashMap<String, String> results = new HashMap<String, String>();
		for (OrganizationInfo org : orgs) {
			results.put(org.getOrgId(), org.getCname());
		}
		return results;
	}

	public void saveOrUpate(OrganizationInfo entity) {
		OrganizationInfo org = null;
		if (entity.getOrgId() != null) {
			org = organizationInfoDao.get(entity.getOrgId());
		}
		if (org == null) {
			org = entity;
		} else {
			try {
				ReflectionUtils.copyWithOutNull(org, entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		organizationInfoDao.save(org);
	}

	public List<OrganizationInfo> findByProperty(String propertyName,
			Object value) {
		return organizationInfoDao.findByProperty(propertyName, value);
	}

	public OrganizationInfo findRoot() {
		return organizationInfoDao.findRoot();
	}

	@Override
	public void create(OrganizationInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(OrganizationInfo entity) {
		// TODO Auto-generated method stub

	}

	public String delete(List<String> ids) {
		int result = 0;
		boolean flag = true;
		for (String id : ids) {
			OrganizationInfo org = organizationInfoDao.get(id);
			if (org.getSubOrg().size() > 0) {
				flag = false;
				result = result | 1;
			}
			if (org.getPersons().size() > 0) {
				flag = false;
				result = result | 2;
			}
			if (org.getSource() != null) {
				flag = false;
				result = result | 4;
			}
		}
		if (flag) {
			for (String id : ids) {
				organizationInfoDao.delete(id);
			}
		}
		return Integer.toBinaryString(result);
	}

	public List<OrganizationInfo> findByCriteria(final Criterion... criterions) {
		return organizationInfoDao.findByCriteria(criterions);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrganizationInfo get(String id) {
		// TODO Auto-generated method stub
		return organizationInfoDao.get(id);
	}

	public String getOrgName(String orgId) {
		OrganizationInfo org = get(orgId);
		if (org != null) {
			return org.getCname();
		}
		return null;
	}

	@Override
	public List<OrganizationInfo> find() {
		// TODO Auto-generated method stub
		return organizationInfoDao.getAll();
	}

	public Page<OrganizationInfo> findPaged(Page<OrganizationInfo> page) {
		return organizationInfoDao.findByCriteria(page);
	}

	@Override
	public Page<OrganizationInfo> findPaged(Page<OrganizationInfo> page,
			OrganizationInfo entity) {
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
	public Page<OrganizationInfo> findPaged(Page<OrganizationInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(OrganizationInfo entity) {
		return null;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Map<String, List<OrganizationKeyValue>> getCached_org() {
		if (cached_org == null || cached_org.size() == 0) {
			loadCache(false);
		}
		return cached_org;
	}

	public List<OrganizationInfo> findSfsBySup(String supOrgId) {
		return organizationInfoDao.findByCriteria(
				Restrictions.eq("supOrg.orgId", supOrgId),
				Restrictions.or(Restrictions.eq("orgType", "2"),
						Restrictions.eq("orgType", "3")));
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2015年6月1日上午9:58:55
	 * @param org
	 * @return
	 */
	private JsonTree orgJsonTree(OrganizationInfo org) {
		if (null == org) {
			return null;
		}
		JsonTree json = new JsonTree(org.getOrgId(), org.getCname(),
				org.getOrgType());
		json.setData(org.getCname());
		if (org.getSubOrg() != null && org.getSubOrg().size() > 0) {
			json.setState("closed");
		} else {
			json.setState("opend");
		}
		return json;
	}

	private Criteria getTreeCriteria() {
		Criteria criteria = organizationInfoDao.createCriteria(Restrictions.eq(
				"source", "1"));
		criteria.addOrder(Order.asc("rank"));
		criteria.addOrder(Order.asc("orgId"));
		return criteria;
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2015年6月1日上午10:15:00
	 * @param orgType
	 * @return
	 */
	public List<JsonTree> findJsonByOrgType(String orgType) {
		Criteria criteria = getTreeCriteria();
		criteria.add(Restrictions.eq("orgType", orgType));
		List<OrganizationInfo> orgs = criteria.list();
		List<JsonTree> results = new ArrayList<JsonTree>();
		for (OrganizationInfo org : orgs) {
			JsonTree json = orgJsonTree(org);
			results.add(json);
		}
		return results;
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2015年6月1日上午10:15:02
	 * @return
	 */
	public List<JsonTree> findJsonForSj() {
		Criteria criteria = getTreeCriteria();
		criteria.add(Restrictions.ne("orgType", "2"));
		criteria.add(Restrictions.eq("supOrg.orgId", "1"));
		List<OrganizationInfo> orgs = criteria.list();
		List<JsonTree> results = new ArrayList<JsonTree>();
		for (OrganizationInfo org : orgs) {
			JsonTree json = orgJsonTree(org);
			results.add(json);
		}
		return results;
	}
}
