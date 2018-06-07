package com.nci.dcs.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.system.dao.AuthorizationDao;
import com.nci.dcs.system.model.Authorization;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.model.User;

@Service
@Transactional
public class AuthorizationService extends BaseService<Authorization, Integer> {
	@Autowired
	private AuthorizationDao authorizationDao;

	@Override
	public void audit(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Authorization entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if (entity != null) {
			// System.out.println("note1,entity.getId():"+entity.getId());
			// //eg.内功--352
			if (entity.getName() != null && !entity.getName().equals("")) {
				// System.out.println("note2");
				PropertyFilter filter = new PropertyFilter();
				filter.setMatchType(MatchType.LIKE);
				filter.setPropertyName("name");
				filter.setValue(entity.getName().trim().replaceAll("%", "/%")
						.replaceAll("_", "/_"));
				filters.add(filter);
			}
			if (entity.getId() != null && !entity.getId().equals("")) {
				// System.out.println("note3");
				PropertyFilter filter2 = new PropertyFilter();
				filter2.setMatchType(MatchType.EQUAL);
				filter2.setPropertyName("parent.id");
				filter2.setValue(entity.getId());
				filters.add(filter2);
			}
		}
		// System.out.println("note4");
		return filters;
	}

	@Override
	public void create(Authorization entity) {
		authorizationDao.getSession().clear();
		authorizationDao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		authorizationDao.delete(id);
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
	public List<Authorization> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Authorization> findPaged(Page<Authorization> page,
			Authorization entity) {
		authorizationDao.findByFilters(page, this.buildPropertyFilter(entity));
		return null;
	}

	@Override
	public Page<Authorization> findPaged(Page<Authorization> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Authorization get(Integer id) {
		return authorizationDao.get(id);
	}

	@Override
	public void update(Authorization entity) {
		authorizationDao.getSession().clear();
		authorizationDao.save(entity);
	}

	// zjc adds . 2013-10-16 16:25
	public String findByParentId(String pNodeId) {
		String hql = "from Authorization where pid = '" + pNodeId + "'";
		List<Authorization> list = authorizationDao.find(hql);
		StringBuffer mystrTree = new StringBuffer(); // 待拼凑的JSON串
		mystrTree.append("[");
		for (Iterator<Authorization> i = list.iterator(); i.hasNext();) {
			Authorization authorization = i.next();
			mystrTree.append("{");
			mystrTree.append("\"attr\":");

			mystrTree.append("{\"id\":\"").append(authorization.getId())
					.append("\",");

			mystrTree.append("\"name\":\"").append(authorization.getName())
					.append("\"},");

			mystrTree.append("\"data\":\"").append(authorization.getName()) // 此处data记录树上显示的节点名称
					.append("\"");

			// 如果有下一级的菜单，则设置为closed的folder形式
			if (authorization.getChildren().size() > 0) {
				mystrTree.append(",\"state\":\"closed\"").append(
						",\"icon\":\"folder\"");
			}
			mystrTree.append("}");
			if (i.hasNext())
				mystrTree.append(",");
		}
		mystrTree.append("]");
		return mystrTree.toString();
	}

	// zjc adds.2013-10-17
	public List<Authorization> findPagedInit(Page<Authorization> page,
			Integer parentId) {
		String hql = "from Authorization where pid = '" + parentId.toString()
				+ "'";
		List<Authorization> list = authorizationDao.find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// zjc adds.2013-10-23
	public String getNameByUrl(String url) {
		String name = new String();
		String hql = "from Authorization where address like '%" + url + "%'";
		List<Authorization> list = authorizationDao.find(hql);
		if (list != null && list.size() == 1) {
			name = list.get(0).getName();
		} else {
			name = url;
		}
		return name;
	}

	// zjc adds.2013-10-25
	List<Authorization> lists = new ArrayList<Authorization>();

	public List<Authorization> getLists() {
		return lists;
	}

	public void setLists(List<Authorization> lists) {
		this.lists = lists;
	}

	public List<Authorization> findChildrenActions(Integer id) {

		addChildrenAction(id);
		return lists;
	}

	public void addChildrenAction(Integer id) {
		String hql = "from Authorization where pid = '" + id.toString() + "'";
		List<Authorization> tmplists = authorizationDao.find(hql);
		for (Authorization auth : tmplists) {
			lists.add(auth);
		}
	}

	// 根据传入的url，返回权限对象
	public Authorization findAuthByUrl(String url) {
		List<Authorization> list = new ArrayList<Authorization>();
		String hql = "from Authorization where address = '" + url + "'";
		list = authorizationDao.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// 根据传入的url前缀，返回权限对象
	public Authorization findAuthByUrlPrefix(String url) {
		List<Authorization> list = new ArrayList<Authorization>();
		String hql = "from Authorization where address like '" + url + "%'";
		list = authorizationDao.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// 得到刚刚添加的权限对象
	public Authorization getNew() {
		List<Authorization> list = new ArrayList<Authorization>();
		String hql = "from Authorization where id!=0 order by cjsj desc";
		list = authorizationDao.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	// 根据权限名称查询
	public List<Authorization> findByName(String name) {
		String hql = "from Authorization where name = ? ";
		return authorizationDao.find(hql, name);
	}

}
