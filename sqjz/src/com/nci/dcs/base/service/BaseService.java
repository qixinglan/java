package com.nci.dcs.base.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;

/**
 * 业务层的基类
 */
public abstract class BaseService<T, PK extends Serializable> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
//	protected LogService logService;
	/**
	 * 创建对象,子类必须实现
	 */
	public abstract void create(T entity);

	/**
	 * 更新对象,子类必须实现
	 */
	public abstract void update(T entity);

	/**
	 * 删除对象,子类必须实现
	 */
	public abstract void delete(PK id);

	/**
	 * 根据主键id查询对象,子类必须实现
	 */
	public abstract T get(PK id);

	/**
	 * 查询所有对象列表,子类必须实现
	 */
	public abstract List<T> find();

	/**
	 * 根据输入条件进行分页查询,并可以判断有效性,子类必须实现
	 */
	public abstract Page<T> findPaged(final Page<T> page, final T entity);
	

	public abstract void enable(PK id) throws Exception;

	public abstract void disable(PK id) throws Exception;

	public abstract void audit(PK id) throws Exception;

	public abstract Page<T> findPaged(final Page<T> page, final String hql, final Object... values) ;
		
	public abstract List<PropertyFilter> buildPropertyFilter(T entity); 

//	public void saveLog(Log log){
////		logService.create(log);
//	}
}
