package com.nci.dcs.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.util.Assert;

import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;

/**
 * 
 * @author Jennie on 12-7-18
 *
 * @param <T>
 * @param <PK>
 */
public class ExtensionalDao<T, PK extends Serializable> extends HibernateDao<T, PK> {
	
	@SuppressWarnings("unchecked")
	public List<T> findByFilters(final List<PropertyFilter> filters, final String[] orderByArray, final String[] orderArray) {
		
		Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
		
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		Criteria c = createCriteria(criterions);
		
		for (int i = 0; i < orderByArray.length; i++) {
			if (Page.ASC.equals(orderArray[i])) {
				c.addOrder(Order.asc(orderByArray[i]));
			} else {
				c.addOrder(Order.desc(orderByArray[i]));
			}
		}
		
		return c.list();
	}
}
