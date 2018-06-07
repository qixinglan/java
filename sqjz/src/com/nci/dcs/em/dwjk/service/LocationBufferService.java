package com.nci.dcs.em.dwjk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.em.dwjk.dao.LocationBufferDao;
import com.nci.dcs.em.dwjk.model.CcLocationInfoBuffer;

/***
 * 
 * 项目名称： sqjz
 * 包名称： com.nci.dcs.em.dwjk.service
 * 类名称: LocationBufferService
 * 类描述：
 * 创建人： yang
 * 创建时间: 2016-6-14下午04:26:13
 * 修改人： yang
 * 修改时间: 2016-6-14下午04:26:13
 * 修改备注：
 *
 */
@Service
@Transactional
public class LocationBufferService extends BaseService<CcLocationInfoBuffer, String> {
	@Autowired
	LocationBufferDao dao;

	@Override
	public void create(CcLocationInfoBuffer entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CcLocationInfoBuffer entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CcLocationInfoBuffer get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcLocationInfoBuffer> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcLocationInfoBuffer> findPaged(Page<CcLocationInfoBuffer> page,
			CcLocationInfoBuffer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<CcLocationInfoBuffer> findPaged(Page<CcLocationInfoBuffer> page,
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
	public Page<CcLocationInfoBuffer> findPaged(Page<CcLocationInfoBuffer> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PropertyFilter> buildPropertyFilter(CcLocationInfoBuffer entity,
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

	public Page<CcLocationInfoBuffer> getAllXY(Page<CcLocationInfoBuffer> page, String hql,
			Object... values) {
		StringBuffer sqlBf = new StringBuffer();
		sqlBf.append(" from CcLocationInfoBuffer as l where fid=?");
		if (values.length > 1) {
			sqlBf.append("and locTime>? and locTime<?");
		}
		return dao.find(page, sqlBf.toString(), values);
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcLocationInfoBuffer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CcLocationInfoBuffer> findHistory(String fxryId,Object... obj) throws ParseException {
		return dao.findHistory(fxryId, obj);
	}
	
	public int getDiffDays(String startTime, String endTime){
		int days = -1;
		SimpleDateFormat sdf = DateTimeFmtSpec.getDateFormat();
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(startTime));
			long start = calendar.getTimeInMillis();
			calendar.setTime(sdf.parse(endTime));
			long end = calendar.getTimeInMillis();
			days = Integer.parseInt(String.valueOf((end - start)/(1000*3600*24)));
		}catch(Exception e){
			logger.error(e.getLocalizedMessage());
		}
		
		return days;
	}
	
	public int getDiffDays(String startTime){
		SimpleDateFormat sdf = DateTimeFmtSpec.getDateFormat();
		String endTime = sdf.format(new Date());
		
		return getDiffDays(startTime, endTime);
	}
}
