
package com.nci.dcs.em.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.WritAfixxDao;
import com.nci.dcs.em.model.CcWritAffix;;;

@Service
@Transactional
public class WritAfixxService  extends BaseService<CcWritAffix, String>{

	@Autowired
	private WritAfixxDao writAfixxdao;
	
	@Override
	public void create(CcWritAffix entity) {
		// TODO Auto-generated method stub
		writAfixxdao.save(entity);
	}

	@Override
	public void update(CcWritAffix entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		writAfixxdao.delete(id);
		
	}

	@Override
	public CcWritAffix get(String id) {
		// TODO Auto-generated method stub
		return writAfixxdao.get(id);
	}

	@Override
	public List<CcWritAffix> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcWritAffix> findPaged(Page<CcWritAffix> page,
			CcWritAffix entity) {
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
	public Page<CcWritAffix> findPaged(Page<CcWritAffix> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcWritAffix entity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
