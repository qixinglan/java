package com.nci.dcs.jzgl.bbgl.service;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.bbgl.dao.StatisticsFxryDao;
import com.nci.dcs.jzgl.bbgl.model.CcStatisticsFxry;
import com.nci.dcs.jzgl.bbgl.dto.WeekStaticData;

@Service
@Transactional
public class StatisticsFxryService extends BaseService<CcStatisticsFxry, String>{
	private static int COUNT = 0;//据目前0个月没有报表
	@Autowired
	private StatisticsFxryDao dao;

	public StatisticsFxryDao getDao() {
		return dao;
	}

	public void setDao(StatisticsFxryDao dao) {
		this.dao = dao;
	}

	@Override
	public void create(CcStatisticsFxry entity) {
		dao.save(entity);
	}
	

	@Override
	public void update(CcStatisticsFxry entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public CcStatisticsFxry get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<CcStatisticsFxry> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CcStatisticsFxry> findPaged(Page<CcStatisticsFxry> page,
			CcStatisticsFxry entity) {
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
	public Page<CcStatisticsFxry> findPaged(Page<CcStatisticsFxry> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcStatisticsFxry entity) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 检查是有已有报表
	 * @param year
	 * @param month
	 * @param orgId
	 * @return
	 */
	public String checkTable(int year, int month, String orgId){
		List res = dao.findByCriteria(
				Restrictions.eq("year", year),
				Restrictions.eq("month", month),
				Restrictions.eq("orgid", orgId));
		if(res.size()>0){
			return "请先生成上月报表!";
		}else{
			return null;
		}
	}
	/**
	 * 获取上月报表
	 * @param year
	 * @param month
	 * @param orgId
	 * @return
	 */
	public CcStatisticsFxry getLastMonthTable(int year, int month, String orgId) {
		List<CcStatisticsFxry> res = dao.findByCriteria(
				Restrictions.eq("year", getYear(year,month)),
				Restrictions.eq("month", getMonth(month)),
				Restrictions.eq("orgid", orgId));
		if(res.size()>0)
			return (CcStatisticsFxry)res.get(0);
		else
			return null;
	}
	/**
	 * 获取上一个月的年份
	 * @param year
	 * @param month
	 * @return
	 */
	private int getYear(int year, int month){
		if(month==1){
			return year-1;
		}else
			return year;
	}
	/**
	 * 获取上一个月月份
	 * @param month
	 * @return
	 */
	private int getMonth(int month){
		if(month==1){
			return 12;
		}else{
			return month-1;
		}
	}

	/**
	 * 检查数据库中是否有该单位的以前报表
	 * @param orgId
	 * @return
	 */
	public boolean dbHasDataByOrgId(String orgId) {
		List res = dao.find("FROM CcStatisticsFxry WHERE orgId=?", orgId);
		int count = res.size();
		if(count<0){
			return false;
		}else{
			return true;
		}
		
	}

	public List getChildTable(String superOrg,int month,int year) {
		List res = dao.find("FROM CcStatisticsFxry WHERE superOrg=? and month=? and year=? ", superOrg,month,year);
		return res;
	}
	
	public List<WeekStaticData> getWeekStaticData(String currentDate){
		
		List<WeekStaticData> list=new ArrayList<WeekStaticData>();
		WeekStaticData s=new WeekStaticData();
		s.setOrgID("1");
		s.setOrgName("北京市");
		s.setCurrDate(currentDate);
		long addData=0,subData=0,guanzhi=0,huanxing=0,jiashi=0,zanjianwai=0,weekAddNum=0,weekSubNum=0,xiaoji=0;
		CallableStatement stmt=null;
		try
		{
           stmt=dao.getSession().connection().prepareCall("{call CC_STATISTICS_FXRY_PER_WEEK(?,?)}");
           stmt.setString(1,currentDate);
           stmt.registerOutParameter(2, -10);
           stmt.execute();
           ResultSet rs=(ResultSet)stmt.getObject(2);
           while(rs.next()){
        	  WeekStaticData item=new WeekStaticData();
        	  item.setOrgID(rs.getString("org_id"));
        	  item.setOrgName(rs.getString("org_name"));
        	  item.setCurrDate(currentDate);
        	  item.setAddData(rs.getString("addData"));
        	  addData+=Long.parseLong(rs.getString("addData"));
        	  item.setSubData(rs.getString("subData"));
        	  subData+=Long.parseLong(rs.getString("subData"));
        	  item.setGuanZhi(rs.getString("guanzhi"));
        	  guanzhi+=Long.parseLong(rs.getString("guanzhi"));
        	  item.setHuanXing(rs.getString("huanxing"));
        	  huanxing+=Long.parseLong(rs.getString("huanxing"));
        	  item.setJiaShi(rs.getString("jiashi"));
        	  jiashi+=Long.parseLong(rs.getString("jiashi"));
        	  item.setZanJianWai(rs.getString("zanjianwai"));
        	  zanjianwai+=Long.parseLong(rs.getString("zanjianwai"));
        	  
        	  item.setXiaoJi(rs.getString("xiaoji"));
        	  xiaoji+=Long.parseLong(rs.getString("xiaoji"));
        	  
        	  item.setWeekAddNum(rs.getString("weekaddnum"));
        	  weekAddNum+=Long.parseLong(rs.getString("weekaddnum"));
        	  item.setWeekSubNum(rs.getString("weeksubnum"));
        	  weekSubNum+=Long.parseLong(rs.getString("weeksubnum"));
        	  list.add(item);
           }
           s.setAddData(String.valueOf(addData));
           s.setSubData(String.valueOf(subData));
           s.setGuanZhi(String.valueOf(guanzhi));
           s.setHuanXing(String.valueOf(huanxing));
           s.setJiaShi(String.valueOf(jiashi));
           s.setXiaoJi(String.valueOf(xiaoji));
           s.setZanJianWai(String.valueOf(zanjianwai));
           s.setWeekAddNum(String.valueOf(weekAddNum));
           s.setWeekSubNum(String.valueOf(weekSubNum));
           list.add(s);
           rs.close();
           rs=null;
           stmt.close();
           stmt=null;           
        }
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return list;
	}
}
