package com.nci.dcs.homepage.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.homepage.todo.dao.TodoModuleDao;
import com.nci.dcs.homepage.todo.model.TodoModule;

@Service
public class TodoService extends BaseService<TodoModule, String> {

	@Autowired
	private TodoModuleDao todoModuleDao;

	/**
	 * 获取用户可见的所有快捷操作
	 * 
	 * @return
	 */
	public List<TodoModule> getAllModules() {
		return todoModuleDao.getAll();
	}

	@Override
	public void create(TodoModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TodoModule entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public TodoModule get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoModule> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TodoModule> findPaged(Page<TodoModule> page, TodoModule entity) {
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
	public Page<TodoModule> findPaged(Page<TodoModule> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(TodoModule entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
