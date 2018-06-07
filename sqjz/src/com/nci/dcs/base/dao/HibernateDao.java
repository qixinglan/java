package com.nci.dcs.base.dao;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.AssociationType;
import org.hibernate.type.DateType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.common.utils.ReflectionUtils;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;

/**
 * 封装Spring扩展功能的Hibernat范型基类.
 * 
 * 扩展功能包括分页查询,按属性过滤条件列表查询等. 可在Service层直接使用,也可以扩展范型DAO子类使用.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 * 
 */
@Repository
@Transactional
public class HibernateDao<T, PK extends Serializable> extends
		SimpleHibernateDao<T, PK> {
	private SimpleDateFormat dfTimestamp = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	private SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 用于扩展的DAO子类使用的构造函数.
	 * 
	 * 通过子类的范型定义取得对象类型Class. eg. public class UserDao extends
	 * SimpleHibernateDao<User, Long>
	 */
	public HibernateDao() {
		super();
	}

	/**
	 * 用于Service层直接使用SimpleHibernateDAO的构造函数. eg. SimpleHibernateDao<User, Long>
	 * userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public HibernateDao(SessionFactory sessionFactory, Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// 分页查询函数 //

	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page) {
		return findByCriteria(page);
	}

	/**
	 * 按HQL分页查询. 不支持自动获取总结果数,需用户另行执行查询.
	 * 
	 * @param page
	 *            分页参数.仅支持pageSize 和firstResult,忽略其他参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数.
	 * 
	 * @return 分页查询结果,附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> find(final Page<T> page, final String hql,
			final Object... values) {
		Assert.notNull(page);

		Query q = createQuery(hql, values);
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *            其中autoCount指定是否动态获取总结果数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findByCriteria(final Page<T> page) {
		Assert.notNull(page);
		return findByCriteria(createCriteria(page.getCriterions()), page);
	}

	public Page<T> findPageByCriteria(Criteria criteria, final Page<T> page) {
		return findByCriteria(criteria, page);
	}

	protected Page<T> findByCriteria(Criteria criteria, final Page<T> page) {
		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(criteria, page);
			page.setTotalCount(totalCount);
		}

		setPageParameter(criteria, page);
		List result = criteria.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Page<T> page) {
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		c.setFirstResult(page.getFirst());
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length,
					"分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	protected int countCriteriaResult(final Criteria c, final Page<T> page) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl,
					"orderEntries");
			ReflectionUtils
					.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// Projections.rowCount();
		// impl.setProjection(Projections.rowCount());
		Object o = impl.setProjection(Projections.rowCount()).uniqueResult();
		// 执行Count查询
		int totalCount = 0;
		if (o != null) {
			totalCount = (Integer) o;
		}

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	// 属性条件查询函数 //

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * 
	 * @param matchType
	 *            目前支持的取值为"EQUAL"与"LIKE". 后续我加了 BETWEEN, IN, NOTEQUAL;
	 */
	public List<T> findByProperty(final String propertyName,
			final Object value, String matchTypeStr) {
		MatchType matchType = Enum.valueOf(MatchType.class, matchTypeStr);
		Criterion criterion = buildPropertyCriterion(propertyName, value,
				matchType);
		return findByCriteria(criterion);
	}

	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> findByFilters(final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findByCriteria(criterions);
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findByFilters(final Page<T> page,
			final List<PropertyFilter> filters) {
		List<Criterion> criterions = buildPropertyFilterCriterionsList(filters);
		page.setCriterions(criterions);
		return findByCriteria(page);
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected List<Criterion> buildPropertyFilterCriterionsList(
			List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			// modified by Jennie on 12/11/11
			boolean multiValue = false;
			String propertyValue = "";
			try {
				propertyValue = (String) filter.getValue();
				multiValue = StringUtils.contains(propertyValue, "|");
			} catch (Throwable t) {

			}
			if (!multiValue) { // properValue中只有一个属性的情况.
				Criterion criterion = buildPropertyCriterion(
						filter.getPropertyName(), filter.getValue(),
						filter.getMatchType(), filter.getList());
				criterionList.add(criterion);
			} else {// properValue中包含多个属性的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				String[] params = StringUtils.split(propertyValue, '|');

				for (String param : params) {
					Criterion criterion = buildPropertyCriterion(
							filter.getPropertyName(), param,
							filter.getMatchType(), filter.getList());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList;
	}

	protected Criterion[] buildPropertyFilterCriterions(
			List<PropertyFilter> filters) {
		List<Criterion> criterionList = buildPropertyFilterCriterionsList(filters);
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 * 
	 * @param value
	 *            格式 "%value%"(或"value"),"value%","%value"
	 */
	protected Criterion buildPropertyCriterion(final String propertyName,
			final Object value, MatchType matchType) {
		Assert.hasText(propertyName);
		Criterion criterion = null;
		String v = value.toString();
		if (MatchType.EQUAL.equals(matchType)) {
			criterion = Restrictions.eq(propertyName, value);
		}

		// added by Jennie on 12/11/11
		if (MatchType.LE.equals(matchType)) {
			criterion = Restrictions.le(propertyName, value);
		}
		// added by Jennie on 12/11/11
		if (MatchType.GE.equals(matchType)) {
			criterion = Restrictions.ge(propertyName, value);
		}

		// modified by Jennie on 2013/3/13
		if (MatchType.LIKE.equals(matchType)) {
			if (!v.contains("/%") && !v.contains("/_")) {
				MatchMode matchMode = MatchMode.ANYWHERE;
				if (StringUtils.isNotEmpty(value.toString())) {
					if (value.toString().startsWith("%")
							&& (!value.toString().endsWith("%") || value
									.toString().endsWith("/%"))) {
						matchMode = MatchMode.END;
						v = value.toString().substring(1);
					} else if (!value.toString().startsWith("%")
							&& value.toString().endsWith("%")
							&& !value.toString().endsWith("/%")) {
						matchMode = MatchMode.START;
						v = value.toString().substring(0,
								value.toString().length() - 1);
					}
				}
				criterion = Restrictions.like(propertyName, v, matchMode);
			} else {
				String columnName = propertyName;
				char[] chars = v.toCharArray();
				StringBuffer sb = new StringBuffer();
				if (!"".equals(columnName)) {
					// '/%','/_'以外的'/'转成文本字符'/'
					for (int i = 0; i < chars.length; i++) {
						if (chars[i] == '/') {
							if (i < (chars.length - 1)
									&& !(chars[i + 1] == '%' || chars[i + 1] == '_')
									|| i == chars.length - 1)
								sb.append('/');
						}
						sb.append(chars[i]);
					}
					v = sb.toString();
					if (!v.toString().startsWith("%")
							&& !(v.toString().endsWith("%") && !v.toString()
									.endsWith("/%"))) {
						v = "%" + v + "%";
					}
					// 通过hibernate的属性名获取对应的数据库表列名
					PropertyDescriptor[] pds = PropertyUtils
							.getPropertyDescriptors(entityClass);
					for (PropertyDescriptor pd : pds) {
						sb = new StringBuffer();
						sb.append(Character.toUpperCase(columnName.charAt(0)));
						if (columnName.length() > 1)
							sb.append(columnName.substring(1));
						String methodName = "get" + sb.toString();
						Method method = pd.getReadMethod();
						if (method.getName().equals(methodName)) {
							Column c = method.getAnnotation(Column.class);
							if (c != null) {
								columnName = c.name();
								break;
							}
						}
					}
				}
				// 拼装定义有转义字符'/'的查询条件criterion
				String rawSql = " {alias}." + columnName + " like ? ";
				if (v.contains("/%") || v.contains("/_")) {
					rawSql += "escape'/' ";
				}
				criterion = Restrictions.sqlRestriction(rawSql, v,
						Hibernate.STRING);
			}
		}

		// @author czhou 不等于
		if (MatchType.NOTEQUAL.equals(matchType)) {
			criterion = Restrictions.ne(propertyName, value);
		}
		// @author yangjianxiang 时间段条件过滤
		if (MatchType.BETWEEN.equals(matchType)) {

			String aa = (String) value;
			String value1 = aa.substring(0, aa.indexOf("|"));
			String value2 = aa.substring(aa.indexOf("|") + 1, aa.length());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = sdf.parse(value1);
				Date d2 = sdf.parse(value2);

				criterion = Restrictions.between(propertyName, d1, d2);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return criterion;
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 * 
	 * @param value
	 *            格式 "%value%"(或"value"),"value%","%value"
	 */
	protected Criterion buildPropertyCriterion(final String propertyName,
			final Object value, MatchType matchType, final ArrayList list) {
		Assert.hasText(propertyName);
		Criterion criterion = null;
		String v = value.toString();
		if (MatchType.EQUAL.equals(matchType)) {
			criterion = Restrictions.eq(propertyName, value);
		}

		// added by Jennie on 12/11/11
		if (MatchType.LE.equals(matchType)) {
			criterion = Restrictions.le(propertyName, value);
		}
		// added by Jennie on 12/11/11
		if (MatchType.GE.equals(matchType)) {
			criterion = Restrictions.ge(propertyName, value);
		}

		// modified by Jennie on 2013/3/13
		if (MatchType.LIKE.equals(matchType)) {
			if (!v.contains("/%") && !v.contains("/_")) {
				MatchMode matchMode = MatchMode.ANYWHERE;
				if (StringUtils.isNotEmpty(value.toString())) {
					if (value.toString().startsWith("%")
							&& (!value.toString().endsWith("%") || value
									.toString().endsWith("/%"))) {
						matchMode = MatchMode.END;
						v = value.toString().substring(1);
					} else if (!value.toString().startsWith("%")
							&& value.toString().endsWith("%")
							&& !value.toString().endsWith("/%")) {
						matchMode = MatchMode.START;
						v = value.toString().substring(0,
								value.toString().length() - 1);
					}
				}
				criterion = Restrictions.like(propertyName, v, matchMode);
			} else {
				String columnName = propertyName;
				char[] chars = v.toCharArray();
				StringBuffer sb = new StringBuffer();
				if (!"".equals(columnName)) {
					// '/%','/_'以外的'/'转成文本字符'/'
					for (int i = 0; i < chars.length; i++) {
						if (chars[i] == '/') {
							if (i < (chars.length - 1)
									&& !(chars[i + 1] == '%' || chars[i + 1] == '_')
									|| i == chars.length - 1)
								sb.append('/');
						}
						sb.append(chars[i]);
					}
					v = sb.toString();
					if (!v.toString().startsWith("%")
							&& !(v.toString().endsWith("%") && !v.toString()
									.endsWith("/%"))) {
						v = "%" + v + "%";
					}
					// 通过hibernate的属性名获取对应的数据库表列名
					PropertyDescriptor[] pds = PropertyUtils
							.getPropertyDescriptors(entityClass);
					for (PropertyDescriptor pd : pds) {
						sb = new StringBuffer();
						sb.append(Character.toUpperCase(columnName.charAt(0)));
						if (columnName.length() > 1)
							sb.append(columnName.substring(1));
						String methodName = "get" + sb.toString();
						Method method = pd.getReadMethod();
						if (method.getName().equals(methodName)) {
							Column c = method.getAnnotation(Column.class);
							if (c != null) {
								columnName = c.name();
								break;
							}
						}
					}
				}
				// 拼装定义有转义字符'/'的查询条件criterion
				String rawSql = " {alias}." + columnName + " like ? ";
				if (v.contains("/%") || v.contains("/_")) {
					rawSql += "escape'/' ";
				}
				criterion = Restrictions.sqlRestriction(rawSql, v,
						Hibernate.STRING);
			}
		}
		if (MatchType.ABSLIKE.equals(matchType)) {
			criterion = Restrictions.like(propertyName, v);
		}
		// @author czhou 不等于
		if (MatchType.NOTEQUAL.equals(matchType)) {
			criterion = Restrictions.ne(propertyName, value);
		}
		// @author zfy
		if (MatchType.NOTNULL.equals(matchType)) {
			criterion = Restrictions.isNotNull(propertyName);
		}
		if (MatchType.ISNULL.equals(matchType)) {
			criterion = Restrictions.isNull(propertyName);
		}

		// @author yangjianxiang in条件过滤
		if (MatchType.IN.equals(matchType)) {
			criterion = Restrictions.in(propertyName, list);
		}
		// @author yangjianxiang 时间段条件过滤
		if (MatchType.BETWEEN.equals(matchType)) {

			String aa = (String) value;
			String value1 = aa.substring(0, aa.indexOf("|"));
			String value2 = aa.substring(aa.indexOf("|") + 1, aa.length());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date d1 = sdf.parse(value1);
				Date d2 = sdf.parse(value2);
				criterion = Restrictions.between(propertyName, d1, d2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return criterion;
	}

	// @author yangjianxiang createSQLQuery支持写sql语句
	public Query createSQLQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createSQLQuery(hql); // createSQLQuery
		if (values != null) {// if add by xuhua
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Criteria organitizCriteria(List<SearchRule> rules) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(entityClass);
		try {
			ClassMetadata cm = session.getSessionFactory().getClassMetadata(
					entityClass);
			Map<String, Rule> ruleMap = new HashMap<String, Rule>();
			for (SearchRule searchRule : rules) {
				String field = searchRule.getField();
				String path = null, property = null;
				if (field.lastIndexOf('.') > 0) {
					path = field.substring(0, field.lastIndexOf('.'));
					property = field.substring(field.lastIndexOf('.') + 1);
				} else {
					path = "";
					property = field;

				}
				Rule rule = findRule(path, ruleMap);
				rule.add(searchRule);
			}

			Rule rule = findRule("", ruleMap);
			for (SearchRule searchRule : rule.rules) {
				Type type = getFieldType(cm, searchRule.getField());
				criteria.add(translateRule(entityClass.getSimpleName(),
						searchRule.getField(), searchRule.getField(),
						searchRule, type));
			}
			HashSet<String> done = new HashSet<String>();
			ruleMap.remove("");
			for (String path : ruleMap.keySet()) {
				if (done.contains(path))
					continue;
				criteria.createAlias(path, path.replace("\\.", "_"),
						SimpleHibernateDao.JOINTYPE_LEFT_OUTER_JOIN);
				for (SearchRule searchRule : ruleMap.get(path).rules) {
					Type type = getFieldType(cm, searchRule.getField(),
							(SessionFactoryImplementor) session
									.getSessionFactory());
					criteria.add(translateRule(entityClass.getSimpleName(),
							searchRule.getField(), searchRule.getField()
									.replace(path, path.replace("\\.", "_")),
							searchRule, type));
				}
			}
		} catch (Throwable t) {
			throw new HibernateException("findBySeachRule throws a throwable.",
					t);
		}
		return criteria;
	}

	public Criteria organitizCriteria(List<SearchRule> rules,
			List<String> colNames, String orderBys, String orders,
			boolean orderBySetted) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(entityClass);
		// Where
		try {
			// rule
			ClassMetadata cm = session.getSessionFactory().getClassMetadata(
					entityClass);
			Map<String, Rule> ruleMap = new HashMap<String, Rule>();
			for (SearchRule searchRule : rules) {
				String field = searchRule.getField();
				String path = null, property = null;
				if (field.lastIndexOf('.') > 0) {
					path = field.substring(0, field.lastIndexOf('.'));
					property = field.substring(field.lastIndexOf('.') + 1);
				} else {
					path = "";
					property = field;

				}
				//@guliang解决搜索类型为cn（like），输入条件为%，会搜索出所有数据
				if(searchRule.getOp().equals("cn")){
					searchRule.setData(searchRule.getData().replace("%", "\\%"));
				}
				Rule rule = findRule(path, ruleMap);
				rule.add(searchRule);
			}

			Rule rule = findRule("", ruleMap);
			for (SearchRule searchRule : rule.rules) {
				Type type = getFieldType(cm, searchRule.getField());
				criteria.add(translateRule(entityClass.getSimpleName(),
						searchRule.getField(), searchRule.getField(),
						searchRule, type));
			}

			HashSet<String> done = new HashSet<String>();
			done.add("");
			ruleMap.remove("");
			for (String path : ruleMap.keySet()) {
				if (done.contains(path))
					continue;
				String alias = path.replace("\\.", "_");
				if (alias.contains(".")) {
					String[] aliases = alias.split("\\.");
					String temp = aliases[0];
					criteria.createAlias(temp, temp, JOINTYPE_LEFT_OUTER_JOIN);
					for (int j = 1; j < aliases.length; j++) {
						temp = temp + "." + aliases[j];
						criteria.createAlias(temp, temp,
								JOINTYPE_LEFT_OUTER_JOIN);
					}
				} else {
					criteria.createAlias(alias, alias, JOINTYPE_LEFT_OUTER_JOIN);
				}
				for (SearchRule searchRule : ruleMap.get(path).rules) {
					Type type = getFieldType(cm, searchRule.getField(),
							(SessionFactoryImplementor) session
									.getSessionFactory());
					criteria.add(translateRule(entityClass.getSimpleName(),
							searchRule.getField(), searchRule.getField()
									.replace(path, path.replace("\\.", "_")),
							searchRule, type));
				}
				done.add(path);
			}

			// OrderBy
			if (orderBySetted && orderBys != null && !orderBys.equals("")) {
				String[] orderByArray = StringUtils.split(orderBys, ',');
				String[] orderArray = StringUtils.split(orders, ',');
				Assert.isTrue(orderByArray.length == orderArray.length,
						"分页多重排序参数中,排序字段与排序方向的个数不相等");
				for (int i = 0; i < orderByArray.length; i++) {
					int index = orderByArray[i].lastIndexOf(".");
					String order;
					if (index < 0) {
						order = orderByArray[i];
					} else {
						String property = orderByArray[i].substring(index + 1);
						String path = orderByArray[i].substring(0, index);
						String alias = path.replace("\\.", "_");
						if (!done.contains(alias)) {
							if (alias.contains(".")) {
								String[] aliases = alias.split("\\.");
								String temp = aliases[0];
								criteria.createAlias(temp, temp,
										JOINTYPE_LEFT_OUTER_JOIN);
								for (int j = 1; j < aliases.length; j++) {
									temp = temp + "." + aliases[j];
									criteria.createAlias(temp, temp,
											JOINTYPE_LEFT_OUTER_JOIN);
								}
							} else {
								criteria.createAlias(alias, alias,
										JOINTYPE_LEFT_OUTER_JOIN);
							}
							done.add(path);
						}
						order = alias + "." + property;
					}
					if (Page.ASC.equals(orderArray[i])) {
						criteria.addOrder(Order.asc(order));
					} else {
						criteria.addOrder(Order.desc(order));
					}
				}
			}
			// colNames
			if (colNames != null && colNames.size() > 0) {
				ProjectionList list = Projections.projectionList();
				for (int i = 0; i < colNames.size(); i++) {
					String columnInArray = colNames.get(i);
					int index = columnInArray.lastIndexOf(".");
					if (index < 0) {
						list.add(Projections.property(columnInArray));
					} else {
						String property = columnInArray.substring(index + 1);
						String path = columnInArray.substring(0, index);
						String alias = path.replace("\\.", "_");
						if (!done.contains(alias)) {
							criteria.createAlias(path, alias,
									JOINTYPE_LEFT_OUTER_JOIN);
							done.add(path);
						}
						list.add(Projections.property(alias + "." + property));
					}
				}
				criteria.setProjection(list);
			}
		} catch (Throwable t) {
			throw new HibernateException("findBySeachRule throws a throwable.",
					t);
		}
		return criteria;
	}

	public Page<T> findBySeachRule(final Page<T> page, List<SearchRule> rules) {
		Criteria criteria = organitizCriteria(rules, null, page.getOrderBy(),
				page.getOrder(), page.isOrderBySetted());
		return findByCriteria(criteria, page);
	}

	public Page<T> findBySeachRule(final Page<T> page, List<SearchRule> rules,
			List<String> colNames) {
		Criteria criteria = organitizCriteria(rules, colNames,
				page.getOrderBy(), page.getOrder(), page.isOrderBySetted());
		return findByCriteria(criteria, page);
	}

	private Rule findRule(String path, Map<String, Rule> map) {
		if (!map.containsKey(path)) {
			map.put(path, new Rule(path));
		}
		return map.get(path);
	}

	class Rule {
		String path;
		List<SearchRule> rules;

		public Rule(String path) {
			super();
			this.path = path;
			rules = new ArrayList<SearchRule>();
		}

		public void add(SearchRule rule) {
			rules.add(rule);
		}
	}

	public Type getFieldType(ClassMetadata cm, String field,
			SessionFactoryImplementor sf) {
		int index = field.indexOf(".");
		String property, aliasProperty = null;
		if (index > 0) {
			property = field.substring(0, index++);
			aliasProperty = field.substring(index);
		} else {
			property = field;
		}
		Type type = cm.getPropertyType(property);
		;
		if (aliasProperty != null) {
			if (type instanceof AssociationType) {
				String entityName = ((AssociationType) type)
						.getAssociatedEntityName(sf);
				cm = sf.getClassMetadata(entityName);
				return getFieldType(cm, aliasProperty, sf);
			} else {
				throw new PropertyNotFoundException(entityClass.getName() + "."
						+ field);
			}
		}
		return type;
	}

	public Type getFieldType(ClassMetadata cm, String field) {
		return cm.getPropertyType(field);
	}

	protected Criterion translateRule(String clazz, String field,
			String property, SearchRule rule, Type type) throws Exception {
		String op = rule.getOp().toLowerCase();
		Object data = getValue(rule.getData().trim(), type, clazz, field);

		if ("eq".equals(op)) {
			return Property.forName(rule.getField()).eq(data);
		} else if ("ne".equals(op)) {
			return Property.forName(rule.getField()).ne(data);
		} else if ("lt".equals(op)) {
			return Property.forName(rule.getField()).lt(data);
		} else if ("le".equals(op)) {
			return Property.forName(rule.getField()).le(data);
		} else if ("gt".equals(op)) {
			return Property.forName(rule.getField()).gt(data);
		} else if ("ge".equals(op)) {
			return Property.forName(rule.getField()).ge(data);
		} else if ("bw".equals(op)) {
			return Property.forName(rule.getField()).like((String) data,
					MatchMode.START);
		} else if ("ew".equals(op)) {
			return Property.forName(rule.getField()).like((String) data,
					MatchMode.END);
		} else if ("cn".equals(op)) {
			return Property.forName(rule.getField()).like((String) data,
					MatchMode.ANYWHERE);
		} else if ("bn".equals(op)) {
			return Restrictions.not(Property.forName(rule.getField()).like(
					(String) data, MatchMode.START));
		} else if ("en".equals(op)) {
			return Restrictions.not(Property.forName(rule.getField()).like(
					(String) data, MatchMode.END));
		} else if ("nc".equals(op)) {
			return Restrictions.not(Property.forName(rule.getField()).like(
					(String) data, MatchMode.ANYWHERE));
		} else if ("nu".equals(op)) {
			return Property.forName(rule.getField()).isNull();
		} else if ("nn".equals(op)) {
			return Property.forName(rule.getField()).isNotNull();
		} else if ("dle".equals(op)) {
			if (data instanceof Date) {
				data = new Date(((Date) data).getTime() + new Date(1).getTime());
			}
			return Property.forName(rule.getField()).le(data);
		} else if ("dge".equals(op)) {
			if (data instanceof Date) {
				data = new Date(((Date) data).getTime() + new Date(1).getTime());
			}
			return Property.forName(rule.getField()).ge(data);
		} else if ("in".equals(op)) {
			if (data instanceof String) {
				Object[] qdata = ((String) data).split(",");
				return Restrictions.in(rule.getField(), qdata);
			}
			return null;
		} else if ("eqornu".equals(op)) {
			return Restrictions.or(Property.forName(rule.getField()).eq(data),
					Property.forName(rule.getField()).isNull());
		} else {
			return null;
		}
	}

	private Object getValue(String data, Type type, String clazz, String field)
			throws ParseException {
		if (type instanceof DateType) {
			return data.length() > 12 ? dfTimestamp.parseObject(data) : dfDate
					.parse(data);
		} else if (type instanceof org.hibernate.type.NullableType) {
			return ((org.hibernate.type.NullableType) type)
					.fromStringValue(data);
		}
		throw new PropertyValueException(type.getName(), clazz, field);
	}
}
