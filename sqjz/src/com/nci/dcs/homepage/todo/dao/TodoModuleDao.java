package com.nci.dcs.homepage.todo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.dao.HibernateDao;
import com.nci.dcs.homepage.todo.model.TodoModule;

@Repository
@Transactional
public class TodoModuleDao extends HibernateDao<TodoModule, String> {

}
