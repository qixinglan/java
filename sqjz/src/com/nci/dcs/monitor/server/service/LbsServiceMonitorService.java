package com.nci.dcs.monitor.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.axis.client.Call;
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
import com.nci.dcs.monitor.server.dao.LbsServiceMonitorDao;
import com.nci.dcs.monitor.server.model.LbsMonitor;
import com.nci.dcs.monitor.server.model.ManageMonitor;


@Service
@Transactional
public class LbsServiceMonitorService extends BaseService<LbsMonitor, String>{
	
	@Autowired
	private LbsServiceMonitorDao dao;
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
	public List<LbsMonitor> findByCriteria(List<Criterion> criterions, List<Order> orders) {
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
	public void runLbsMonitor(){
		try{
			//获取定位信息
			LbsMonitor lbs = new LbsMonitor();
			lbs.setCreateTime(new Date());
			List<ManageMonitor> manageList = manageService.findByCriteria(Restrictions.eq("type", "lbs"));
			if(manageList == null || manageList.size()<=0){
				log.debug("在CC_MONITOR_MANAGE配置LBS服务器IP地址");
				throw new Exception();
			}
			String status = "0";
			String ratio = "0%";
			String success = "0";
			String fail = "0";
			String amount = "0";
			//webservice请求定位信息
			List<ManageMonitor> manageLists = manageService.findByCriteria(Restrictions.eq("type", "lbsWebservice"));
			if(manageLists == null || manageLists.size()<=0){
				log.debug("在CC_MONITOR_MANAGE配置LBS服务器WebService地址");
			}else{
				String webservice = manageLists.get(0).getCode();
				String[] web = webservice.split(",");
				String url = web[0];
				String name = web[1];
				org.apache.axis.client.Service service = new org.apache.axis.client.Service();
				Call call;
				try {
					call = (Call) service.createCall();
					call.setTargetEndpointAddress(new java.net.URL(url));
					call.setOperationName(name);
					String res = (String)call.invoke(new Object[]{ });
					if(CommonUtils.isNull(res)){
						String[] resa = res.split(",");
						status = resa[0];
						ratio = resa[1]+"%";
						success = resa[2];
						fail = resa[3];
						amount = resa[4];
					}
				} catch (Exception e) {
					log.error("", e);
					e.printStackTrace();
				}
			}
			String ip = manageList.get(0).getCode();
			Map<String,String> resMap = manageService.getNetWorkStatus(ip);
			lbs.setSent(resMap.get("sent"));
			lbs.setReceived(resMap.get("received"));
			lbs.setLost(resMap.get("lost"));
			lbs.setLossRatio(resMap.get("lossRatio"));
			String reqIp = manageService.getIp();
			lbs.setStatus(status);
			lbs.setRatio(ratio);
			lbs.setSuccess(success);
			lbs.setFail(fail);
			lbs.setAmount(amount);
			lbs.setIp(reqIp);
			create(lbs);
			int lost = Integer.parseInt(lbs.getLost());
			int ratioInt = Integer.parseInt(ratio.replace("%", ""));
			//异常情况发送短息通知
			if(lost != 0 || ratioInt<90){
				String content = "LBS定位异常，请及时检查！";
				manageService.sendMessage(content);
			}
			//半小时时间间隔的定位数据
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.MINUTE, -30);
//			Date startd = calendar.getTime();
//			Date endd = new Date();
		}catch(Exception e){
			log.error("", e);
			e.printStackTrace();
		}
	}
	
	public Page<LbsMonitor> findPaged(Page<LbsMonitor> page) {
		return dao.findByCriteria(page);
	}
	
	@Override
	public void create(LbsMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(LbsMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(LbsMonitor entity){
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

	public void delete(LbsMonitor entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public LbsMonitor get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<LbsMonitor> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LbsMonitor> findPaged(Page<LbsMonitor> page,
			LbsMonitor entity) {
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
	public Page<LbsMonitor> findPaged(Page<LbsMonitor> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(LbsMonitor entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
