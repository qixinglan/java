package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.AddressDao;
import com.nci.dcs.jzgl.dagl.model.Address;


@Service
@Transactional
public class AddressService extends BaseService<Address, String>{
	
	@Autowired
	private AddressDao dao;
	
	@Override
	public void create(Address entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(Address entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(Address entity){
		if (entity == null){
			return;
		}
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(null);
		}
		dao.save(entity);
	}
	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	public void delete(Address entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public Address get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<Address> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Address> findPaged(Page<Address> page,
			Address entity) {
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
	public Page<Address> findPaged(Page<Address> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(Address entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
