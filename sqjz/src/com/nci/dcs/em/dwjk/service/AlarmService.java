package com.nci.dcs.em.dwjk.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dwjk.dao.AlarmDao;
import com.nci.dcs.em.dwjk.model.CcAlarmInfo;

@Service
@Transactional
public class AlarmService extends BaseService<CcAlarmInfo, String> {

	@Autowired
	private AlarmDao dao;
	public AlarmDao getDao() {
		return dao;
	}

	public void setDao(AlarmDao dao) {
		this.dao = dao;
	}

	
	public Page<CcAlarmInfo> findPaged(Page<CcAlarmInfo> page) {
		
		return dao.findByCriteria(page);
	}
	@Override
	public void create(CcAlarmInfo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CcAlarmInfo entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcAlarmInfo get(String id) {
		
		return dao.get(id);
	}

	@Override
	public List<CcAlarmInfo> find() {
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
	public Page<CcAlarmInfo> findPaged(Page<CcAlarmInfo> page, String hql,
			Object... values) {
		return dao.find(page, hql, values);
	}
	
	public List<Object> getSfsSta(String orgId){
		String strsql="select * from view_sfs_tjdata where orgId='"+orgId+"'";
		return dao.createSQLQuery(strsql.toString()).list();
	}
	/*public Page<CcAlarmInfo> findPaged(Page<CcAlarmInfo> page,
			CcAlarmInfo entity,Object...values) {
		return dao.findByFilters(page, this.addFilter(entity, values));
	}
	
	public List<PropertyFilter> addFilter(CcAlarmInfo entity,Object...values) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = null;
		if(StringUtils.isNotEmpty(entity.getFxryId())){
			filter = new PropertyFilter();
			filter.setPropertyName("fxryId");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getFxryId());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity.getStatus())){
			filter = new PropertyFilter();
			filter.setPropertyName("zt");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getStatus());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity.getAlarmLevel())){
			filter = new PropertyFilter();
			filter.setPropertyName("alarmLevel");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getAlarmLevel());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity.getAlarmType())){
			filter = new PropertyFilter();
			filter.setPropertyName("alarmType");
			filter.setMatchType(MatchType.EQUAL);
			filter.setValue(entity.getAlarmType());
			filters.add(filter);
		}
		if(StringUtils.isNotEmpty(entity.getHandler())){
			filter = new PropertyFilter();
			filter.setPropertyName("handler");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity.getHandler());
			filters.add(filter);
		}
		if(entity.getAlarmTime()!=null){
			filter = new PropertyFilter();
			filter.setPropertyName("alarmTime");
			filter.setMatchType(MatchType.BETWEEN);
			filter.setValue(values[0]);
			filters.add(filter);
		}
		if(entity.getHandleTime()!=null){
			filter = new PropertyFilter();
			filter.setPropertyName("handleTime");
			filter.setMatchType(MatchType.BETWEEN);
			if(values.length>1)
				filter.setValue(values[1]);
			else {
				filter.setValue(values[0]);
			}
			filters.add(filter);
		}
		
		return filters;
	}*/

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcAlarmInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcAlarmInfo> findPaged(Page<CcAlarmInfo> page,
			CcAlarmInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<CcAlarmInfo> findAlarmByFxryId(String fxryId, Object... obj) throws ParseException{
		return dao.getAlarmByFxryId(fxryId, obj);
	}

	public void dealAlarm(List ids, String username) {
		String hql="from CcAlarmInfo where alarmId in (:l0)";
		List<CcAlarmInfo> res =  dao.find(hql, ids);
		for(CcAlarmInfo alarm : res){
			alarm.setHandler(username);
			alarm.setHandleTime(new Date());
			alarm.setStatus("1");
			update(alarm);
		}
	}
	/**
	 * 未处理报警类型统计
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Integer> wclbjStatic(String id) {
		Map<String,Integer> res = new HashMap<String,Integer>();
		for(int i=1;i<8;i++){
			res.put(i+"", 0);
		}
		
		List<CcAlarmInfo> result=null;
		try {
			result = (List<CcAlarmInfo>)dao.getUntreatAlarmByFxryId(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(int i =0;i<result.size();i++){
			String alarmTypeKey = result.get(i).getAlarmType();
			res.put(alarmTypeKey, res.get(alarmTypeKey)+1);
		}
		return res;
	}
}
