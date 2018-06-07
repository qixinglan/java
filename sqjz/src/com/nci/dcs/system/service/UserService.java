package com.nci.dcs.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.SimpleHibernateDao;
import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.system.dao.UserDao;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class UserService extends BaseService<User, String> {
	@Autowired
	private UserDao userDao;

	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	public User findByLoginName(String name) {
		List<User> list = new ArrayList<User>();
		if (name != null) {
			String hql = "from User where userName = '" + name + "'";
			list = userDao.find(hql);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(User entity) {
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
	public void create(User entity) {
		Date now = new Date();
		entity.setCjsj(now);
		entity.setXgsj(now);
		userDao.save(entity);
	}

	@Override
	public void delete(String id) {
		userDao.delete(id);
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
	public List<User> find() {
		return userDao.getAll();
	}

	public Page<User> findPersonPaged(Page<User> page,
			List<SearchRule> searchRules) {
		return userDao.findBySeachRule(page, searchRules);
	}

	public Page<User> findPersonForRole(Page<User> page,
			List<SearchRule> searchRules, String roleId) {
		String orderby = page.getOrderBy();
		Criteria criteria = null;
		if(orderby!=null && orderby.equals("wunit.mc")){
			criteria = userDao.organitizCriteria(searchRules,null,page.getOrderBy(),page.getOrder(),page.isOrderBySetted());
		}else{
			criteria = userDao.organitizCriteria(searchRules);
		}
		return userDao.findForRole(page, criteria);
	}

	@Override
	public Page<User> findPaged(Page<User> page, User entity) {
		userDao.findByFilters(page, this.buildPropertyFilter(entity));
		return null;
	}

	@Override
	public Page<User> findPaged(Page<User> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public void update(User entity) {
		entity.setXgsj(new Date());
		userDao.getSession().clear();
		userDao.save(entity);
	}

}
