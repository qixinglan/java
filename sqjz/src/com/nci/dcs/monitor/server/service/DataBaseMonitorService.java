package com.nci.dcs.monitor.server.service;

import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.monitor.server.dao.DataBaseMonitorDao;
import com.nci.dcs.monitor.server.model.DataBaseMonitor;
import com.nci.dcs.monitor.server.model.ManageMonitor;


@Service
@Transactional
public class DataBaseMonitorService extends BaseService<DataBaseMonitor, String>{
	
	@Autowired
	private DataBaseMonitorDao dao;
	@Autowired
	private ManageMonitorService manageService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public Criteria createCriteria(List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = dao.createCriteria(criterions);
		if (CommonUtils.isNotNull(orders)) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<DataBaseMonitor> findByCriteria(List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = createCriteria(criterions, orders);
		if (null != criteria) {
			return criteria.list();
		}
		return null;
	}
	
	/**
	 * @name 定时任务
	 * @author caolj
	 * @date 2016年7月12日 下午4:42:15
	 * @message:
	 */
	public void runDataBaseMonitor(){
		//监控端口状态
		try{
			DataBaseMonitor data = new DataBaseMonitor();
			data.setCreateTime(new Date());
			List<ManageMonitor> manageList = manageService.findByCriteria(Restrictions.eq("type", "db"));
			if(manageList == null || manageList.size()<=0){
				log.debug("在CC_MONITOR_MANAGE配置数据库服务器IP地址");
				throw new Exception();
			}
			//与数据库的网络通信状态
			String ip1 = manageList.get(0).getCode();
			String ip2 = "";
			Map<String,String> resMap = manageService.getNetWorkStatus(ip1);
			data.setDb1Sent(resMap.get("sent"));
			data.setDb1Received(resMap.get("received"));
			data.setDb1Lost(resMap.get("lost"));
			data.setDb1LossRatio(resMap.get("lossRatio"));
			if(manageList.size()>1){
				ip2 = manageList.get(1).getCode();
				Map<String,String> resMap1 = manageService.getNetWorkStatus(ip2);
				data.setDb2Sent(resMap1.get("sent"));
				data.setDb2Received(resMap1.get("received"));
				data.setDb2Lost(resMap1.get("lost"));
				data.setDb2LossRatio(resMap1.get("lossRatio"));
			}
			
			//C3P0获取数据库连接状态
			String portStatus = "1";//正常
			Context context = new InitialContext();
			Context envCtx = (Context) context.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/sfjjk");
			if(ds!=null){
				String maxLink = "";
				String sumLink = "";
				String idleLink = "";
				String nowLink = "";
				if(ds instanceof ComboPooledDataSource){
					ComboPooledDataSource ds1 = (ComboPooledDataSource) ds;
					maxLink = String.valueOf(ds1.getMaxPoolSize());
					sumLink = String.valueOf(ds1.getNumConnections());
					idleLink = String.valueOf(ds1.getNumIdleConnections());
					nowLink = String.valueOf(ds1.getNumBusyConnections());
				}
//				System.out.println(ds1.getMaxPoolSize());//最大连接数
//				System.out.println(ds1.getMinPoolSize());//最小连接
//				System.out.println(ds1.getNumBusyConnections());//正在使用的连接
//				System.out.println(ds1.getNumIdleConnections());//空闲的连接
//				System.out.println(ds1.getNumConnections());//总连接
				data.setMaxLink(maxLink);
				data.setSumLink(sumLink);
				data.setIdleLink(idleLink);
				data.setNowLink(nowLink);
			}else{
				portStatus = "0";//不正常
			}
			data.setPortStatus(portStatus);
			String reqIp = manageService.getIp();
			data.setIp(reqIp);
			String db1PortStatus = "0";//不正常
			String db2PortStatus = "0";//不正常
			//数据源异常情况发送短息通知
			if("0".equals(portStatus)){
				String content = reqIp+"与数据库连接异常，请及时检查！";
				manageService.sendMessage(content);
			}
			//网络异常情况发送短息通知
			int lost1 = Integer.parseInt(data.getDb1Lost());
			if(lost1 != 0){
				String content = "与数据库服务器"+ip1+"通讯异常，请及时检查！";
				manageService.sendMessage(content);
			}else{
				//网络连通情况下，测试1521端口情况
				if(!checkPort(ip1)){
					String content = "数据库服务器"+ip1+":1521端口通讯异常，请及时检查！";
					manageService.sendMessage(content);
				}else{
					db1PortStatus = "1";
				}
			}
			if(ip2 != null && !"".equals(ip2)){
				int lost2 = Integer.parseInt(data.getDb2Lost());
				if(lost2 != 0){
					String content = "与数据库服务器"+ip2+"通讯异常，请及时检查！";
					manageService.sendMessage(content);
				}else{
					//网络连通情况下，测试1521端口情况
					if(!checkPort(ip2)){
						String content = "数据库服务器"+ip2+":1521端口通讯异常，请及时检查！";
						manageService.sendMessage(content);
					}else{
						db2PortStatus = "1";
					}
				}
			}
			data.setDb1PortStatus(db1PortStatus);
			data.setDb2PortStatus(db2PortStatus);
			create(data);
		}catch(Exception e){
			log.error("", e);
		}
	}
	
	public boolean checkPort(String ip) {
		boolean flag = true;
		try{
			Socket scoket = new Socket(ip,1521);
			boolean run = scoket.isConnected();
			if(!run){
				 flag = false;//不正常
			}
			scoket.close();
		}catch(Exception e){
			log.error("", e);
			flag = false;//不正常
		}
		return flag;
	}
	
	public Page<DataBaseMonitor> findPaged(Page<DataBaseMonitor> page) {
		return dao.findByCriteria(page);
	}
	
	@Override
	public void create(DataBaseMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(DataBaseMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(DataBaseMonitor entity){
		if (entity == null){
			return;
		}
		if (StringUtils.isBlank(entity.getId())){
			entity.setId(null);
		}
		dao.save(entity);
	}
	
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	public void delete(DataBaseMonitor entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public DataBaseMonitor get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<DataBaseMonitor> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<DataBaseMonitor> findPaged(Page<DataBaseMonitor> page,
			DataBaseMonitor entity) {
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
	public Page<DataBaseMonitor> findPaged(Page<DataBaseMonitor> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(DataBaseMonitor entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
