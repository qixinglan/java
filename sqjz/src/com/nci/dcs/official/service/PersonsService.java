package com.nci.dcs.official.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.ReflectionUtils;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.dao.OrganizationInfoDao;
import com.nci.dcs.official.dao.PersonsDao;
import com.nci.dcs.official.dto.PersonsKeyValue;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.system.dao.UserDao;
import com.nci.dcs.system.model.User;

import edu.emory.mathcs.backport.java.util.Arrays;

@Service
@Transactional
public class PersonsService extends BaseService<Persons, String> {
	@Autowired
	private PersonsDao personsDao;
	@Autowired
	private OrganizationInfoDao organizationInfoDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 判断是否非空
	 * 
	 * @param obj
	 *            数据对象，预期输入的是整型或字符型的数据
	 * @return
	 */
	private boolean isNotNull(Object obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (obj != null) {
			String str = String.valueOf(obj);
			if (!"".equals(str) && str != null) {
				flag = true;
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<PersonsKeyValue>> getPersonJsons(String orgIds,
			String personType, String hasUser) {
		Map<String, List<PersonsKeyValue>> map = new HashMap<String, List<PersonsKeyValue>>();
		List<Persons> persons = new ArrayList<Persons>();
		List<SearchRule> rules = new ArrayList<SearchRule>();
		List<PersonsKeyValue> keyvalue = new ArrayList<PersonsKeyValue>();
		try {
			if (isNotNull(orgIds)) {
				SearchRule sr = new SearchRule();
				sr.setField("org.orgId");
				sr.setOp("in");
				sr.setData(orgIds);
				rules.add(sr);
			}
			if (isNotNull(personType)) {
				SearchRule sr = new SearchRule();
				sr.setField("persontype");
				sr.setOp("eq");
				sr.setData(personType);
				rules.add(sr);
			}
			if (isNotNull(hasUser)) {
				SearchRule sr = new SearchRule();
				sr.setField("user.id");
				if ("1".equals(hasUser)) {
					sr.setOp("nn");
				} else {
					sr.setOp("nu");
				}
				rules.add(sr);
			}
			persons = personsDao.organitizCriteria(rules).list();
			for (Persons person : persons) {
				keyvalue.add(new PersonsKeyValue(person));
			}
			map.put("0", keyvalue);
		} catch (Exception e) {
			logger.error(null, e);
		}
		return map;
	}

	public void saveOrUpate(Persons entity) {
		Persons per = null;
		if (entity.getId() != null) {
			per = personsDao.get(entity.getId());
		}
		if (per == null) {
			per = entity;
		} else {
			try {
				ReflectionUtils.copyWithOutNull(per, entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		personsDao.save(per);
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年6月21日下午12:48:38
	 * @param col
	 *            显示字段
	 * @param searchRules
	 *            查询条件
	 * @param oth
	 *            不在查询条件,但在显示字段中的外键
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List find(String col, List<SearchRule> searchRules) {
		List<String> selectCols = new ArrayList<String>(Arrays.asList(col
				.split(",")));
		return personsDao.findBySpecialCriteria(selectCols, searchRules);
	}

	@Override
	public void create(Persons entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Persons entity) {
		// TODO Auto-generated method stub

	}

	public void delete(List<String> ids) {
		for (String id : ids) {
			this.delete(id);
		}
	}

	@Override
	public void delete(String id) {
		List<User> users=userDao.findByProperty("person.id", id);
		if(CommonUtils.isNotNull(users)){
			for(User user:users){
				user.setPerson(null);
				userDao.save(user);
			}
		}
		personsDao.delete(id);
	}

	@Override
	public Persons get(String id) {
		// TODO Auto-generated method stub
		return personsDao.get(id);
	}

	@Override
	public List<Persons> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Persons> findPaged(Page<Persons> page) {
		return personsDao.findByCriteria(page);
	}

	public Page<Persons> findPersonPaged(Page<Persons> page,
			List<SearchRule> searchRules) {
		return personsDao.findBySeachRule(page, searchRules);
	}

	@Override
	public Page<Persons> findPaged(Page<Persons> page, Persons entity) {
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
	public Page<Persons> findPaged(Page<Persons> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Persons entity) {
		return null;
	}
	
	/**
	 * @name 查询下级机构人员信息
	 * @param orgId
	 * @return
	 * @author clj
	 * @date 2014年9月15日 上午9:34:15 
	 * @message：
	 */
	public List<Persons> getPersonsBySupOrg(String orgId,String orgType, String duty){
		return personsDao.getPersonsBySupOrg(orgId, orgType, duty);
	}
}
