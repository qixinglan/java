package com.nci.dcs.em.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.DeviceFxryDao;
import com.nci.dcs.em.model.CcDeviceFxry;
@Service
@Transactional
public class DeviceFxryService extends BaseService<CcDeviceFxry, String>{
	@Autowired
	private DeviceFxryDao deviceFxryDao;
	
	@Override
	//新增
	public void create(CcDeviceFxry entity) {
		// TODO Auto-generated method stub
		deviceFxryDao.save(entity);
	}
	//改变所有deviceNumber状态
	public void changeStatusByDn(String deviceNumber,String status){
		List<CcDeviceFxry> deviceFxrys=deviceFxryDao.findByProperty("deviceNumber",deviceNumber);
		for(CcDeviceFxry d:deviceFxrys){
			d.setStatus(status);
			deviceFxryDao.save(d);
		}
	}
	//根据设备编号和状态找到该数据
	public CcDeviceFxry findByDeviceNumberAndStatus(String deviceNumber,String status){
		return deviceFxryDao.find("from CcDeviceFxry where deviceNumber=? and status=?", deviceNumber,status).get(0);
	}
	//统计某设备历史佩戴人分页显示
	public Page<CcDeviceFxry> searchHistory(Page<CcDeviceFxry> page) {
		return deviceFxryDao.findByCriteria(page);
	}
	//统计某设备曾经佩戴总人数
	public int totalFxryByDy(String deviceNumber) {
		// TODO Auto-generated method stub
		int num=deviceFxryDao.findInt("select count(distinct fxryId) from CcDeviceFxry where deviceNumber=?", deviceNumber);
		return num;
	}
	//统计全局，并非某一设备，历史佩戴人
		public List findTotalNumbers(String curOrgId,String rootOrgId,String useUnit,String startTime,String overTime,String useSfsUnit){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=null;
			Date overDate=null;
			//该段时间内佩戴人员数量
			String tjWeName="该段时间内累计佩戴人员数量";
			//该段时间内解除人员数量
			String tjReName="该段时间内累计解除人员数量";
			try {
				if(startTime==null||"".equals(startTime)||"null".equals(startTime)){
					//起始时间为空给它一个较早时间
					startDate=sdf.parse("1980-01-01");
				}else{
					startDate=sdf.parse(startTime);
				};
				if(overTime==null||"".equals(overTime)||"null".equals(overTime)){
					overDate=new Date();
				}else{
					overDate=sdf.parse(overTime);
				};
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Query query;
			List<Map<String,Object>> list;
			if(curOrgId.equals(rootOrgId)){
				if(useUnit==null||!(useUnit.matches("[0-9]+"))){
					query=deviceFxryDao.createQuery("select count(*) as num,'"+tjWeName+"' as name from CcDeviceFxry where ( wearTime <= ? and  wearTime  >=?) ",overDate,startDate);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list=query.list();
					query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjReName+"' as name from CcDeviceFxry where releaseTime >=? and  releaseTime <=? ",startDate,overDate);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list.addAll(query.list());
					
				}else{
					if(useSfsUnit==null||!useSfsUnit.matches("[0-9]+")){
						query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjWeName+"' as name from CcDeviceFxry where  wearTime <= ? and wearTime  >=?  and useUnit=? ",overDate,startDate,useUnit);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						list=query.list();
						query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjReName+"' as name from CcDeviceFxry  where releaseTime >=? and  releaseTime <=? and useUnit=? ",startDate,overDate,useUnit);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						list.addAll(query.list());
					}
					else{
						query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjWeName+"' as name from CcDeviceFxry where  wearTime <= ? and wearTime  >=?  and useUnit=? and useSfsUnit=?",overDate,startDate,useUnit,useSfsUnit);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						list=query.list();
						query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjReName+"' as name from CcDeviceFxry  where releaseTime >=? and  releaseTime <=? and useUnit=? and useSfsUnit=?",startDate,overDate,useUnit,useSfsUnit);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						list.addAll(query.list());
					}
				}
			}else{
				if(useSfsUnit==null||!useSfsUnit.matches("[0-9]+")){
					query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjWeName+"'as name from CcDeviceFxry  where  wearTime <= ? and wearTime  >=?  and useUnit=? ",overDate,startDate,curOrgId);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list=query.list();
					query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjReName+"' as name from CcDeviceFxry  where releaseTime >=? and  releaseTime <=? and useUnit=? ",startDate,overDate,curOrgId);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list.addAll(query.list());
				}
				else{
					query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjWeName+"'as name from CcDeviceFxry  where  wearTime <= ? and wearTime  >=?  and useUnit=? and useSfsUnit=?",overDate,startDate,curOrgId,useSfsUnit);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list=query.list();
					query=deviceFxryDao.createQuery("select count(*) as num ,'"+tjReName+"' as name from CcDeviceFxry  where releaseTime >=? and  releaseTime <=? and useUnit=? and useSfsUnit=?",startDate,overDate,curOrgId,useSfsUnit);
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					list.addAll(query.list());
				}
			}
			return list;
		}
	@Override
	public void update(CcDeviceFxry entity) {
		// TODO Auto-generated method stub
		deviceFxryDao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CcDeviceFxry get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CcDeviceFxry> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcDeviceFxry> findPaged(Page<CcDeviceFxry> page, CcDeviceFxry entity) {
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
	public Page<CcDeviceFxry> findPaged(Page<CcDeviceFxry> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcDeviceFxry entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public DeviceFxryDao getDeviceFxryDao() {
		return deviceFxryDao;
	}

	public void setDeviceFxryDao(DeviceFxryDao deviceFxryDao) {
		this.deviceFxryDao = deviceFxryDao;
	}

}
