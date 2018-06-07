package com.nci.dcs.em.dwjk.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.em.dwjk.dao.FxryLocationOnMapDao;
import com.nci.dcs.em.dwjk.dao.LocationDao;
import com.nci.dcs.em.dwjk.model.CcLocationInfo;
import com.nci.dcs.em.dwjk.model.ViewFxryOnMap;
import com.nci.dcs.system.model.User;
import com.nci.sfj.webservice.dao.ViewQxsfjTjxxDao;
import com.nci.sfj.webservice.dao.ViewQxsfjTjxxNoTgryDao;
import com.nci.sfj.webservice.dao.ViewSfjTjxxDao;
import com.nci.sfj.webservice.dao.ViewSfjTjxxDaoNoTgryDao;
import com.nci.sfj.webservice.model.ViewQxsfjTjxx;
import com.nci.sfj.webservice.model.ViewQxsfjTjxxNoTgry;
import com.nci.sfj.webservice.model.ViewSfjTjxx;
import com.nci.sfj.webservice.model.ViewSfjTjxxNoTgry;

@Service
@Transactional
public class LocationService extends BaseService<CcLocationInfo, String> {
	@Autowired
	LocationDao dao;
	@Autowired
	FxryLocationOnMapDao mapDao;
	@Autowired
	private ViewQxsfjTjxxDao viewQxsfjTjxxDao;
	@Autowired
	private ViewQxsfjTjxxNoTgryDao viewQxsfjTjxxNoTgryDao;
	@Autowired
	private ViewSfjTjxxDao viewSfjTjxxDao;
	@Autowired
	private ViewSfjTjxxDaoNoTgryDao viewSfjTjxxDaoNoTgryDao;
	
	public List<Object[]> getData() {
		return dao
				.createSQLQuery(
						"Select 'CC_FXRY_BASEINFO',count(1) from CC_FXRY_BASEINFO UNION ALL Select 'CC_FXRY_DEVICE',count(1) from CC_FXRY_DEVICE ")
				.list();
	}

	public List<Object[]> getStaData(User user) {
		String sqlBf;
		/*if("2".equals(user.getIsws())){
			sqlBf= "select * from view_sfj_tjdata_notgry order by org_id";
		}else{*/
			sqlBf = "select * from view_sfj_tjdata order by org_id";
		/*}*/
		return dao.createSQLQuery(sqlBf.toString()).list();
	}

	public List<Object[]> getCoutyData(String cCode,User user) {
		List<Object[]> list=new ArrayList<Object[]>();
		try{
			if("2".equals(user.getIsws())){
				Criteria criteria=viewQxsfjTjxxNoTgryDao.createCriteria(Restrictions.eq("supOrg", cCode));
				criteria.addOrder(Order.asc("orgId"));
				List<ViewQxsfjTjxxNoTgry>tjxxs=criteria.list();
				for(ViewQxsfjTjxxNoTgry tjxx:tjxxs){
					Object[] objects=new Object[8];
					objects[0]=tjxx.getOrgId();
					objects[1]=tjxx.getCname();
					objects[2]=tjxx.getZjry();
					objects[3]=tjxx.getJkry();
					objects[4]=tjxx.getWclbj();
					objects[5]=tjxx.getGisx();
					objects[6]=tjxx.getGisy();
					objects[7]=tjxx.getSupOrg();
					list.add(objects);
				}
			}else{
				Criteria criteria=viewQxsfjTjxxDao.createCriteria(Restrictions.eq("supOrg", cCode));
				criteria.addOrder(Order.asc("orgId"));
				List<ViewQxsfjTjxx>tjxxs=criteria.list();
				for(ViewQxsfjTjxx tjxx:tjxxs){
					Object[] objects=new Object[8];
					objects[0]=tjxx.getOrgId();
					objects[1]=tjxx.getCname();
					objects[2]=tjxx.getZjry();
					objects[3]=tjxx.getJkry();
					objects[4]=tjxx.getWclbj();
					objects[5]=tjxx.getGisx();
					objects[6]=tjxx.getGisy();
					objects[7]=tjxx.getSupOrg();
					list.add(objects);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询区县统计数据出错", e);
		}
		return list;
	}
	
	public List<Object[]> getCityData(User user) {
		List<Object[]> list=new ArrayList<Object[]>();
		try{
			if("2".equals(user.getIsws())){
				Criteria criteria = viewSfjTjxxDaoNoTgryDao.createCriteria();
				criteria.addOrder(Order.asc("orgId"));
				List<ViewSfjTjxxNoTgry>tjxxs = criteria.list();
				for(ViewSfjTjxxNoTgry tjxx : tjxxs){
					Object[] objects = new Object[8];
					objects[0] = tjxx.getOrgId();
					objects[1] = tjxx.getCname();
					objects[2] = tjxx.getZjry();
					objects[3] = tjxx.getJkry();
					objects[4] = tjxx.getWclbj();
					objects[5] = tjxx.getGisx();
					objects[6] = tjxx.getGisy();
					objects[7] = "";
					list.add(objects);
				}
			}else{
				Criteria criteria = viewSfjTjxxDao.createCriteria();
				criteria.addOrder(Order.asc("orgId"));
				List<ViewSfjTjxx>tjxxs = criteria.list();
				for(ViewSfjTjxx tjxx : tjxxs){
					Object[] objects = new Object[8];
					objects[0] = tjxx.getOrgId();
					objects[1] = tjxx.getCname();
					objects[2] = tjxx.getZjry();
					objects[3] = tjxx.getJkry();
					objects[4] = tjxx.getWclbj();
					objects[5] = tjxx.getGisx();
					objects[6] = tjxx.getGisy();
					objects[7] = "";
					list.add(objects);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("查询区县统计数据出错", e);
		}
		return list;
	}

	public List getAllGisData(List<Criterion> params) {
		Criteria criteria = mapDao.getSession().createCriteria(ViewFxryOnMap.class);
		if (params != null) {
			for (Criterion c : params) {
				criteria.add(c);
			}
		}
		criteria.addOrder(Order.desc("ac"));
		return criteria.list();
		//return dao.createSQLQuery(sqlBf.toString()).list();
	}
	
	/***
	 * 
	 * 方法名称: getPagedGisData
	 * 方法描述：
	 * 创建人： yang
	 * 创建时间: 2016-9-8下午05:02:52
	 * 修改人： yang
	 * 修改时间: 2016-9-8下午05:02:52
	 * 修改备注：
	 * 参数： @param params
	 * 参数： @return
	 *
	 */
	public Page getPagedGisData(List<Criterion> params, Page page) {
		Criteria criteria = mapDao.getSession().createCriteria(ViewFxryOnMap.class);
		if (params != null) {
			for (Criterion c : params) {
				criteria.add(c);
			}
		}
		criteria.addOrder(Order.desc("ac"));
		page = dao.findPageByCriteria(criteria, page);
		
		return page;
	}

	public List<Object[]> getAlarmDetailOnGis(String id) {
		StringBuffer sqlBf = new StringBuffer();
		sqlBf.append(
				"Select  t.alarm_time,t.alarm_type,b.gis_x,b.gis_y,address from cc_alarm_info t Left Join (Select Id,gis_x,gis_y,'address' As address  From cc_fxry_baseinfo Where Id='")
				.append(id)
				.append("') b On t.fxry_id = b.Id where t.fxry_id='")
				.append(id).append("' and t.status = 2 and rownum<5");
		return dao.createSQLQuery(sqlBf.toString()).list();
	}

	@Override
	public void create(CcLocationInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CcLocationInfo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CcLocationInfo get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcLocationInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcLocationInfo> findPaged(Page<CcLocationInfo> page,
			CcLocationInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<CcLocationInfo> findPaged(Page<CcLocationInfo> page,
			Object... values) {
		// TODO Auto-generated method stub
		return dao.findByCriteria(page);
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
	public Page<CcLocationInfo> findPaged(Page<CcLocationInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PropertyFilter> buildPropertyFilter(CcLocationInfo entity,
			String st, String et) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = null;
		if (StringUtils.isNotEmpty(entity.getLocId())) {
			filter = new PropertyFilter();
			filter.setPropertyName("locId");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getLocId());
			filters.add(filter);
		}
		if (st != null && et != null) {
			filter = new PropertyFilter();
			filter.setPropertyName("locTime");
			filter.setMatchType(MatchType.BETWEEN);
			filter.setValue(st + "|" + et);
			filters.add(filter);
		}
		return filters;
	}

	public Page<CcLocationInfo> getAllXY(Page<CcLocationInfo> page, String hql,
			Object... values) {
		StringBuffer sqlBf = new StringBuffer();
		sqlBf.append(" from CcLocationInfo as l where fid=?");
		if (values.length > 1) {
			sqlBf.append("and locTime>? and locTime<?");
		}
		return dao.find(page, sqlBf.toString(), values);
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcLocationInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CcLocationInfo> findHistory(String fxryId,Object... obj) throws ParseException {
		return dao.findHistory(fxryId, obj);
	}
}
