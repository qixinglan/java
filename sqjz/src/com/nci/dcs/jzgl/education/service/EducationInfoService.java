package com.nci.dcs.jzgl.education.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.education.dao.EducationInfoDao;
import com.nci.dcs.jzgl.education.model.EducationInfo;

@Service
@Transactional
public class EducationInfoService  extends BaseService<EducationInfo, String> {

	@Autowired
	private EducationInfoDao dao;
	
	@Override
	public void create(EducationInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(EducationInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public EducationInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<EducationInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<EducationInfo> findPaged(Page<EducationInfo> page,
			EducationInfo entity) {
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
	public Page<EducationInfo> findPaged(Page<EducationInfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(EducationInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @name 查询列表信息
	 * @param page
	 * @return
	 * @author clj
	 * @date 2015年5月26日 下午2:02:48 
	 * @message：
	 */
	public Page<EducationInfo> findPaged(Page<EducationInfo> page) {
		return dao.findByCriteria(page);
	}
	
	public List findBySql(String sql,Object... value){
		Query q = dao.createSQLQuery(sql,value);
		return q.list();
	}
}
