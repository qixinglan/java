package com.nci.dcs.jzgl.education.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.education.dao.FxryEducationDao;
import com.nci.dcs.jzgl.education.model.FxryEducation;

@Service
@Transactional
public class FxryEducationService  extends BaseService<FxryEducation, String> {

	@Autowired
	private FxryEducationDao dao;
	
	@Override
	public void create(FxryEducation entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(FxryEducation entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public FxryEducation get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FxryEducation> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FxryEducation> findPaged(Page<FxryEducation> page,
			FxryEducation entity) {
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
	public Page<FxryEducation> findPaged(Page<FxryEducation> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FxryEducation entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @name 查询列表信息
	 * @param page
	 * @return
	 * @author caolj
	 * @date 2015年4月2日 上午11:38:45
	 * @message:
	 */
	public Page<FxryEducation> findPaged(Page<FxryEducation> page) {
		return dao.findByCriteria(page);
	}
	
	/**
	 * @name 查询月份对应的人员数量、期数信息
	 * @param month
	 * @return
	 * @author caolj
	 * @date 2015年4月2日 上午11:38:29
	 * @message:
	 */
	public List<FxryEducation> findByCriteria(Date startTime, Date endTime) {
		List<FxryEducation> resList =  dao.findByCriteria(Restrictions.and(Restrictions
				.lt("month", endTime),Restrictions
				.ge("month", startTime)));
		return resList;
	}
}
