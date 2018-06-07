package com.nci.dcs.jzgl.dagl.service;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.AddressSelectDao;
import com.nci.dcs.jzgl.dagl.model.AddressSelect;


@Service
@Transactional
public class AddressSelectService extends BaseService<AddressSelect, String>{
	
	@Autowired
	private AddressSelectDao dao;
	
	@Override
	public void create(AddressSelect entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(AddressSelect entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AddressSelect get(String id) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(id)){
			return null;
		}
		return dao.get(id);
	}
	
	public List<AddressSelect> getByParentId(String pid){
		return dao.find("from AddressSelect where parentid=? order by to_number(id)", pid);
	}
	
	@Override
	public List<AddressSelect> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AddressSelect> findPaged(Page<AddressSelect> page,
			AddressSelect entity) {
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
	public Page<AddressSelect> findPaged(Page<AddressSelect> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(AddressSelect entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
