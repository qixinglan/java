package com.nci.dcs.common.utils;

import java.util.ArrayList;

/**
 * 与具体ORM实现无关的属性过滤条件封装类.
 * 
 * PropertyFilter主要记录页面中简单的搜索过滤条件,比Hibernate的Criterion要简单很多.
 * 可按项目扩展其他对比方式如大于、小于及其他数据类型如数字和日期.
 * 
 * @author db2admin
 */
public class PropertyFilter {

	/**
	 * 属性比较类型.
	 */
	public enum MatchType {
		EQUAL, LIKE , BETWEEN, IN, NOTEQUAL, GE, LE,ABSLIKE ,NOTNULL,ISNULL;
	}

	private String propertyName;
	private Object value;
	private MatchType matchType = MatchType.EQUAL;
	private Object[] values;
	@SuppressWarnings("unchecked")
	private ArrayList list=new ArrayList();

	public PropertyFilter() {
	}

	public PropertyFilter(String propertyName, Object value, MatchType matchType) {
		this.propertyName = propertyName;
		this.value = value;
		this.matchType = matchType;
	}

	/**
	 * 获取属性名称,可用'|'分隔多个属性,此时属性间是or的关系.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置属性名称,可用'|'分隔多个属性,此时属性间是or的关系.
	 */
	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(final MatchType matchType) {
		this.matchType = matchType;
	}
	
	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	@SuppressWarnings("unchecked")
	public ArrayList getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(ArrayList list) {
		this.list = list;
	}
}
