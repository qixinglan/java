package com.nci.dcs.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nci.dcs.common.utils.ReflectionUtils;


/**
 * 封装Hibernate原生API的CRUD范型基类.
 * 
 * 可在Service层直接使用,也可以扩展范型DAO子类使用.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate,直接使用Hibernate原生API.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T, PK extends Serializable> {
	public static final int JOINTYPE_LEFT_OUTER_JOIN = 1;
	public static final int JOINTYPE_NONE = -666;
	public static final int JOINTYPE_INNER_JOIN = 0;
	public static final int JOINTYPE_RIGHT_OUTER_JOIN = 2;
	public static final int JOINTYPE_FULL_JOIN = 4;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 用于扩展的DAO子类使用的构造函数.
	 * 
	 * 通过子类的范型定义取得对象类型Class.
	 * eg.
	 * public class UserDao extends SimpleHibernateDao<User, Long>
	 */
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于Service层直接使用SimpleHibernateDAO的构造函数.
	 * eg.
	 * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public SimpleHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity) {
		Assert.notNull(entity);
		try {
			
			Session session = getSession();
			if(session.contains(entity)){
//				session.refresh(entity);
				getSession().merge(entity);
			}
			session.saveOrUpdate(entity);
		} catch (HibernateException e) {
			getSession().merge(entity);
			System.out.println(e.getMessage());
		}
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		Assert.notNull(id);
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		Assert.notNull(id);
		return (T) getSession().get(entityClass, id);
	}

	/**
	 *	获取全部对象. 
	 */
	public List<T> getAll() {
		return findByCriteria();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return findByCriteria(criterion);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueByProperty(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values 数量可变的参数
	 */
	public List<T> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique(final String hql, final Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findOne(final String hql, final Object... values) {
		List list = createQuery(hql, values).list();
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 按HQL查询Integer类型结果. 
	 */
	public Integer findInt(final String hql, final Object... values) {		
		return findLong(hql, values).intValue();
		
	}

	/**
	 * 按HQL查询Long类型结果. 
	 */
	public Long findLong(final String hql, final Object... values) {
		return (Long) findUnique(hql, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * 
	 * @param values 数量可变的参数
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString);
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if(values[i] instanceof List){
					query.setParameterList("l"+i, (List)values[i]);
				}else{
					query.setParameter(i, values[i]);
				}
			}
		}
		return query;
	}
	

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> findByCriteria(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public T findOneByCriteria(final Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		List<T> list = criteria.list();
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public T findOneByProperty(final String propertyName, final Object value) {
		Criterion crit = Restrictions.eq(propertyName, value);
		return findOneByCriteria(crit);
	}
	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if (criterions != null){
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		return criteria;
	}
	
	public Criteria createCriteria(List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if (criterions != null){
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		return criteria;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;
		Object object = findUniqueByProperty(propertyName, newValue);
		return (object == null);
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass.getSimpleName() + " not define in HibernateSessionFactory.");
		return meta.getIdentifierPropertyName();
	}
}