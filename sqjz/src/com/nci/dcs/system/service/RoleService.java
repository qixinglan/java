package com.nci.dcs.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.system.dao.RoleDao;
import com.nci.dcs.system.model.Role;

@Service
@Transactional
public class RoleService extends BaseService<Role, Integer> {
	@Autowired
	private RoleDao roleDao;

	public Page<Role> findPersonPaged(Page<Role> page,
			List<SearchRule> searchRules) {
		return roleDao.findBySeachRule(page, searchRules);
	}

	@Override
	public void audit(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Role entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if (entity != null && entity.getName() != null
				&& !entity.getName().equals("")) {
			PropertyFilter filter = new PropertyFilter();
			filter.setPropertyName("name");
			filter.setValue(entity.getName().trim().replaceAll("%", "/%")
					.replaceAll("_", "/_"));
			filter.setMatchType(MatchType.LIKE);
			filters.add(filter);
		}
		return filters;
	}

	@Override
	public void create(Role entity) {
		roleDao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		roleDao.delete(id);

	}

	@Override
	public void disable(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enable(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Role> find() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}

	@Override
	public Page<Role> findPaged(Page<Role> page, Role entity) {
		roleDao.findByFilters(page, this.buildPropertyFilter(entity));
		return null;
	}

	@Override
	public Page<Role> findPaged(Page<Role> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role get(Integer id) {
		return roleDao.get(id);
	}

	@Override
	public void update(Role entity) {
		roleDao.save(entity);
	}

}
