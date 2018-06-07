package com.nci.dcs.monitor.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.monitor.server.dao.NetworkMonitorDao;
import com.nci.dcs.monitor.server.model.ManageMonitor;
import com.nci.dcs.monitor.server.model.NetworkMonitor;


@Service
@Transactional
public class NetworkMonitorService extends BaseService<NetworkMonitor, String>{
	
	@Autowired
	private NetworkMonitorDao dao;
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
	public List<NetworkMonitor> findByCriteria(List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = createCriteria(criterions, orders);
		if (null != criteria) {
			return criteria.list();
		}
		return null;
	}
	/**
	 * @name 定时任务
	 * @author caolj
	 * @date 2016年6月20日 下午4:42:15
	 * @message:
	 */
	public void runNetworkMonitor(){
		try{
			NetworkMonitor network  = new NetworkMonitor();
			network.setCreateTime(new Date());
			List<ManageMonitor> manageList = manageService.findByCriteria(Restrictions.eq("type", "qzj"));
			if(manageList == null || manageList.size()<=0){
				log.debug("在CC_MONITOR_MANAGE配置前置机IP地址");
				throw new Exception();
			}
			String ip = manageList.get(0).getCode();
			Map<String,String> resMap = manageService.getNetWorkStatus(ip);
			network.setSent(resMap.get("sent"));
			network.setReceived(resMap.get("received"));
			network.setLost(resMap.get("lost"));
			network.setLossRatio(resMap.get("lossRatio"));
			String reqIp = manageService.getIp();
			network.setIp(reqIp);
			create(network);
			int lost = Integer.parseInt(network.getLost());
			//异常情况发送短息通知
			if(lost != 0){
				String content = reqIp+"与前置机网络通讯异常，请及时检查！";
				manageService.sendMessage(content);
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
	
	public Page<NetworkMonitor> findPaged(Page<NetworkMonitor> page) {
		return dao.findByCriteria(page);
	}
	
	@Override
	public void create(NetworkMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(NetworkMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(NetworkMonitor entity){
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

	public void delete(NetworkMonitor entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public NetworkMonitor get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<NetworkMonitor> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<NetworkMonitor> findPaged(Page<NetworkMonitor> page,
			NetworkMonitor entity) {
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
	public Page<NetworkMonitor> findPaged(Page<NetworkMonitor> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(NetworkMonitor entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
