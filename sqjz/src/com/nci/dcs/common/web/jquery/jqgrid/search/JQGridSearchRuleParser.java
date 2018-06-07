package com.nci.dcs.common.web.jquery.jqgrid.search;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.nci.dcs.common.sql.SQLStringUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;

import edu.emory.mathcs.backport.java.util.Arrays;

public class JQGridSearchRuleParser {
	private static Logger logger = Logger
			.getLogger(JQGridSearchRuleParser.class);
	private String json;

	public JQGridSearchRuleParser(String json) {
		this.json = json;
	}

	public List<Criterion> parse(Class type) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(this.json);
			JSONArray jsonArray = jsonObject.getJSONArray("rules");
			SearchRule[] rules = (SearchRule[]) JSONArray.toArray(jsonArray,
					SearchRule.class);
			// SearchFilters filters =
			// (SearchFilters)JSONObject.toBean(jsonObject,
			// SearchFilters.class);
			List<Criterion> criterions = new ArrayList<Criterion>();
			for (SearchRule rule : rules) {
				try {
					if(null!=rule){
						criterions.add(translateRule(rule, type));
					}
				} catch (Exception e) {
					logger.warn("解析查询条件" + rule.getField() + "失败:", e);
				}
			}
			return criterions;
		} catch (Exception e) {
			return null;
		}
	}
	private Field getField(Class modelType, String fieldName){
		if (modelType == null || modelType == Object.class){
			return null;
		}
		Field field = null;
		try {
			if (fieldName.contains(".")) {
				Class temp = modelType;
				String[] fieldNames = fieldName.split("\\.");
				int i = 0;
				for (; i < fieldNames.length - 1; i++) {
					temp = temp.getDeclaredField(fieldNames[i]).getType();
				}
				field = temp.getDeclaredField(fieldNames[i]);
			} else {
				field = modelType.getDeclaredField(fieldName);
			}
		} catch (Exception e) {
		}
		if (field != null){
			return field;
		}
		return getField(modelType.getSuperclass(), fieldName);
	}
	
	protected Criterion translateRule(SearchRule rule, Class type)
			throws Exception {
		String fieldName = rule.getField();
		Field field = null;
		field=getField(type, fieldName);
		String op = rule.getOp().toLowerCase();
		String data = rule.getData().trim();
		if ("eq".equals(op)) {
			return Restrictions.eq(fieldName, getValue(field, data));
		} else if ("ne".equals(op)) {
			return Restrictions.ne(fieldName, getValue(field, data));
		} else if ("lt".equals(op)) {
			return Restrictions.lt(fieldName, getValue(field, data));
		} else if ("le".equals(op)) {
			return Restrictions.le(fieldName, getValue(field, data));
		} else if ("gt".equals(op)) {
			return Restrictions.gt(fieldName, getValue(field, data));
		} else if ("ge".equals(op)) {
			return Restrictions.ge(fieldName, getValue(field, data));
		} else if ("bw".equals(op)) {
			return Restrictions.like(fieldName, (String) getValue(field, data),
					MatchMode.START);
		} else if ("ew".equals(op)) {
			return Restrictions.like(fieldName, (String) getValue(field, data),
					MatchMode.END);
		} else if ("cn".equals(op)) {
			return Restrictions.like(fieldName, (String) getValue(field, data),
					MatchMode.ANYWHERE);
		} else if ("bn".equals(op)) {
			return Restrictions.not(Restrictions.like(fieldName,
					(String) getValue(field, data), MatchMode.START));
		} else if ("en".equals(op)) {
			return Restrictions.not(Restrictions.like(fieldName,
					(String) getValue(field, data), MatchMode.END));
		} else if ("nc".equals(op)) {
			return Restrictions.not(Restrictions.like(fieldName,
					(String) getValue(field, data), MatchMode.ANYWHERE));
		} else if ("nu".equals(op)) {
			return Restrictions.isNull(fieldName);
		} else if ("nn".equals(op)) {
			return Restrictions.isNotNull(fieldName);
		} else if ("mdircn".equals(op)) {
			String[] mdir = data.split(",");
			List<Criterion> temp = new ArrayList<Criterion>();
			for (String dir : mdir) {
				temp.add(Restrictions.like(fieldName,
						(String) getValue(field, dir), MatchMode.ANYWHERE));
			}
			List<Criterion> orTemp = new ArrayList<Criterion>();
			do {
				for (int i = 0; i < temp.size(); i = i + 2) {
					if (i + 1 < temp.size()) {
						orTemp.add(Restrictions.and(temp.get(i),
								temp.get(i + 1)));
					} else {
						orTemp.add(temp.get(i));
					}
				}
				temp = orTemp;
				orTemp = new ArrayList<Criterion>();
			} while (temp.size() > 1);
			if (temp.size() > 0) {
				return temp.get(0);
			}
			return null;
		}
		else if ("in".equals(op)) {
			if (data instanceof String) {
				Object[] qdata = ((String) data).split(",");
				return Restrictions.in(rule.getField(), qdata);
			}
			return null;
		}
		else {
			return null;
		}
	}

	protected Object getValue(Field field, String value) throws Exception {
		if (field.getType() == String.class) {
			return SQLStringUtils.escape(value);
		} else if (field.getType() == Date.class) {
			try {
				return DateTimeFmtSpec.getDateTimeFormat().parse(value);
			} catch (Exception e) {
				try {
					return DateTimeFmtSpec.getMinutesFormat().parse(value);
				} catch (Exception e2) {
					try {
						return DateTimeFmtSpec.getDateFormat().parse(value);
					} catch (Exception e1) {
						return DateTimeFmtSpec.getMonthFormat().parse(value);
					}
				}
			}
		} else {
			Method method = field.getType().getMethod("valueOf", String.class);
			return method.invoke(String.class, value);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<SearchRule> getSearchRules(String json) {
		if (StringUtils.isEmpty(json))
			return new ArrayList<SearchRule>();
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("rules");
		SearchRule[] rules = (SearchRule[]) JSONArray.toArray(jsonArray,
				SearchRule.class);
		ArrayList<SearchRule> result = new ArrayList<SearchRule>(
				Arrays.asList(rules));
		return result;
	}

	public List<Criterion> parse(List<SearchRule> rules, Class type) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		try {
			for (SearchRule rule : rules) {
				try {
					criterions.add(translateRule(rule, type));
				} catch (Exception e) {
					logger.warn("解析查询条件" + rule.getField() + "失败:", e);
				}
			}
		} catch (Exception e) {
		}
		return criterions;
	}
}
