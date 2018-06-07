package com.nci.dcs.monitor.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.monitor.server.model.DataBaseMonitor;
import com.nci.dcs.monitor.server.model.LbsMonitor;
import com.nci.dcs.monitor.server.model.ManageMonitor;
import com.nci.dcs.monitor.server.model.NetworkMonitor;
import com.nci.dcs.monitor.server.model.ServiceMonitor;
import com.nci.dcs.monitor.server.service.DataBaseMonitorService;
import com.nci.dcs.monitor.server.service.LbsServiceMonitorService;
import com.nci.dcs.monitor.server.service.ManageMonitorService;
import com.nci.dcs.monitor.server.service.NetworkMonitorService;
import com.nci.dcs.monitor.server.service.ServiceMonitorService;
import com.opensymphony.xwork2.Action;

public class ServiceMonitorAction  extends BaseAction<ServiceMonitor>  {
	/**
	 * @name 
	 * @author caolj
	 * @date 2016年6月17日 下午3:02:41
	 * @message:
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> zlmap;
	public Map<String,Object> getZlmap() {
		return zlmap;
	}

	public void setZlmap(Map<String,Object> zlmap) {
		this.zlmap = zlmap;
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ManageMonitorService manageService;
	@Autowired
	private LbsServiceMonitorService lbsService;
	@Autowired
	private NetworkMonitorService networkService;
	@Autowired
	private DataBaseMonitorService dbService;
	@Autowired
	private ServiceMonitorService service;
	
	
	public String search(){
		Page<ServiceMonitor> page = this.getRequestPage();
		try {
			service.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String report() throws Throwable {
		//获取配置表中的应用服务器IP
		List<ManageMonitor> manageList = manageService.findByCriteria(Restrictions.eq("type", "server"));
		if(manageList == null || manageList.size()<=0){
			log.debug("在CC_MONITOR_MANAGE配置应用服务器IP");
			throw new Exception();
		}
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		Date startTime = null, endTime = null;
		startTime = DateTimeFmtSpec.getDateTimeFormat().parse(stime+":00");
		endTime = DateTimeFmtSpec.getDateTimeFormat().parse(etime+":00");
		zlmap = new HashMap<String, Object>();
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.le("createTime", endTime));
		criterions.add(Restrictions.ge("createTime", startTime));
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("createTime"));
		
		String flag = request.getParameter("flag");
		if(flag!=null && "location".equals(flag)){
			//查询时间段内的定位信息数量--一小时内多少条
			getLocationStatus(startTime, endTime);
		}else{
			//查询时间段内的服务器状态
			getServerStatus(manageList, criterions, orders);
			//查询时间段内的网络状态
			getNetWorkStatus(manageList, criterions, orders);
			//查询时间段内的LBS状态
			getLbsStatus(manageList, criterions, orders);
			//查询时间段内的数据库状态
			getDbStatus(manageList, criterions, orders);
		}
		
		return SUCCESS;
	}
	/**
	 * @name 获取LBS定位信息统计图
	 * @param criterions
	 * @param orders
	 * @author caolj
	 * @date 2016年8月26日 上午11:35:59
	 * @message:
	 */
	@SuppressWarnings({ "rawtypes" })
	public void getLocationStatus(Date startDate,Date endDate) {
		List<Category> searchTimes = new ArrayList<Category>();//封装数据
		List<DataSet> dateSets = new ArrayList<DataSet>();
		DataSet ds = new DataSet("定位数量统计");
		List<Data> datas = new ArrayList<Data>();
		//半小时时间间隔的定位数据
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
//		calendar.add(Calendar.HOUR_OF_DAY, -23);
//		Date startDate = calendar.getTime();
//		Date endDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime =sdf.format(startDate);
		String endTime = sdf.format(endDate);
		String sql = "select a, count(a)  from (select to_char(t.loc_time, 'yyyy-MM-dd hh24') a, t.loc_time b  from CC_LOCATION_INFO t  order by t.loc_time desc) tt"
				+ " where tt.b >= to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss') and tt.b <= to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss') group by a";
		List list = service.findBySql(sql);
		Map<String,String> dataMap = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			Object[] temp = (Object[]) list.get(i);
			dataMap.put(String.valueOf(temp[0]), String.valueOf(temp[1]));
		}
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH");
		String str = "";
		while (endDate.compareTo(calendar.getTime()) >= 0) {
			str = sdf1.format(calendar.getTime());
			Category category = new Category(str);
			searchTimes.add(category);
			Data data = new Data(dataMap.get(str)==null?"0":dataMap.get(str));
			datas.add(data);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}
		ds.setData(datas);
		dateSets.add(ds);
		zlmap.put("locationCategory", searchTimes);
		zlmap.put("locationDataset", dateSets);
	}
	/**
	 * @name 查询时间段内的服务器状态
	 * @param manageList
	 * @param criterions
	 * @param orders
	 * @author caolj
	 * @date 2016年8月12日 上午12:09:07
	 * @message:
	 */
	public void getServerStatus(List<ManageMonitor> manageList, List<Criterion> criterions, List<Order> orders) {
		List<Category> searchTimes = new ArrayList<Category>();//图形化展示X轴数据，月-日-时-分
		List<String> searchTimesT = new ArrayList<String>();//封装数据，年-月-日-时-分+IP，
		List<DataSet> dateSets = new ArrayList<DataSet>();
		List<ServiceMonitor> serList = service.findByCriteria(criterions,orders);
		//获取时间轴
		Date temp = null;
		String ip = "";
		String time = "";
		String serIP1 = manageList.get(0).getCode();
		String serIP2 = "";
		if(manageList.size()>1){
			serIP2 = manageList.get(1)==null?"":manageList.get(1).getCode();
		}
		DataSet ds1 = new DataSet(serIP1+"服务器CPU使用率");
		List<Data> datas1 = new ArrayList<Data>();
		DataSet ds3 = new DataSet(serIP1+"服务器内存使用率");
		List<Data> datas3 = new ArrayList<Data>();
		DataSet ds2 = new DataSet(serIP2+"服务器CPU使用率");
		List<Data> datas2 = new ArrayList<Data>();
		DataSet ds4 = new DataSet(serIP2+"服务器内存使用率");
		List<Data> datas4 = new ArrayList<Data>();
		Map<String,String> searchMap = new HashMap<String,String>();
		Map<String,Map<String,String>> searchT = new HashMap<String,Map<String,String>>();
		for(ServiceMonitor ser : serList){
			temp = ser.getCreateTime();
			time = DateTimeFmtSpec.getMinutesFormat().format(temp.getTime());
			if(!searchMap.containsKey(time)){
				Category category = new Category(time.substring(5, time.length()));
				searchTimes.add(category);
				searchTimesT.add(time);
			}
			searchMap.put(time, time);
			ip = ser.getIp();
			Map<String,String> searchV = new HashMap<String,String>();
			searchV.put("cpu", ser.getCpuRatio().replace("%", ""));
			searchV.put("wlnc", ser.getWlncRatio().replace("%", ""));
			searchT.put(time+ip, searchV);
		}
		setSesarchTimeValue(searchTimesT, datas1, datas2, datas3, datas4, serIP1, serIP2, searchT, "1");
		ds1.setData(datas1);
		ds3.setData(datas3);
		ds2.setData(datas2);
		ds4.setData(datas4);
		dateSets.add(ds1);
		dateSets.add(ds3);
		if(serIP2 != null && !"".equals(serIP2)){
			dateSets.add(ds2);
			dateSets.add(ds4);
		}
		zlmap.put("serverCategory", searchTimes);
		zlmap.put("serverDataset", dateSets);
	}
	/**
	 * @name 查询时间段内的网络状态
	 * @param manageList
	 * @param criterions
	 * @param orders
	 * @author caolj
	 * @date 2016年8月12日 上午12:09:07
	 * @message:
	 */
	public void getNetWorkStatus(List<ManageMonitor> manageList, List<Criterion> criterions, List<Order> orders) {
		List<Category> searchTimes = new ArrayList<Category>();
		List<String> searchTimesT = new ArrayList<String>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		List<NetworkMonitor> netList = networkService.findByCriteria(criterions,orders);
		//获取时间轴
		Date temp = null;
		String ip = "";
		String time = "";
		String serIP1 = manageList.get(0).getCode();
		String serIP2 = "";
		if(manageList.size()>1){
			serIP2 = manageList.get(1)==null?"":manageList.get(1).getCode();
		}
		DataSet ds1 = new DataSet(serIP1+"服务器网络状态");
		List<Data> datas1 = new ArrayList<Data>();
		DataSet ds2 = new DataSet(serIP2+"服务器网络状态");
		List<Data> datas2 = new ArrayList<Data>();
		Map<String,String> searchMap = new HashMap<String,String>();
		Map<String,Map<String,String>> searchT = new HashMap<String,Map<String,String>>();
		for(NetworkMonitor ser : netList){
			temp = ser.getCreateTime();
			time = DateTimeFmtSpec.getMinutesFormat().format(temp.getTime());
			if(!searchMap.containsKey(time)){
				Category category = new Category(time.substring(5, time.length()));
				searchTimes.add(category);
				searchTimesT.add(time);
			}
			searchMap.put(time, time);
			ip = ser.getIp();
			Map<String,String> searchV = new HashMap<String,String>();
			searchV.put("cpu", String.valueOf(100-Double.parseDouble(ser.getLossRatio().replace("%", ""))));
			searchT.put(time+ip, searchV);
		}
		setSesarchTimeValue(searchTimesT, datas1, datas2, null, null, serIP1, serIP2, searchT, "0");
		ds1.setData(datas1);
		ds2.setData(datas2);
		dateSets.add(ds1);
		if(serIP2 != null && !"".equals(serIP2)){
			dateSets.add(ds2);
		}
		zlmap.put("netCategory", searchTimes);
		zlmap.put("netDataset", dateSets);
	}
	/**
	 * @name 查询时间段内的LBS状态
	 * @param manageList
	 * @param criterions
	 * @param orders
	 * @author caolj
	 * @date 2016年8月12日 上午12:09:07
	 * @message:
	 */
	public void getLbsStatus(List<ManageMonitor> manageList, List<Criterion> criterions, List<Order> orders) {
		List<Category> searchTimes = new ArrayList<Category>();
		List<String> searchTimesT = new ArrayList<String>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		List<LbsMonitor> lbsList = lbsService.findByCriteria(criterions,orders);
		//获取时间轴
		Date temp = null;
		String ip = "";
		String time = "";
		String serIP1 = manageList.get(0).getCode();
		String serIP2 = "";
		if(manageList.size()>1){
			serIP2 = manageList.get(1)==null?"":manageList.get(1).getCode();
		}
		DataSet ds1 = new DataSet(serIP1+"服务器网络状态");
		List<Data> datas1 = new ArrayList<Data>();
		DataSet ds2 = new DataSet(serIP2+"服务器网络状态");
		List<Data> datas2 = new ArrayList<Data>();
		Map<String,String> searchMap = new HashMap<String,String>();
		Map<String,Map<String,String>> searchT = new HashMap<String,Map<String,String>>();
		for(LbsMonitor ser : lbsList){
			temp = ser.getCreateTime();
			time = DateTimeFmtSpec.getMinutesFormat().format(temp.getTime());
			if(!searchMap.containsKey(time)){
				Category category = new Category(time.substring(5, time.length()));
				searchTimes.add(category);
				searchTimesT.add(time);
			}
			searchMap.put(time, time);
			ip = ser.getIp();
			Map<String,String> searchV = new HashMap<String,String>();
			searchV.put("cpu", ser.getRatio().replace("%", ""));
			searchT.put(time+ip, searchV);
		}
		setSesarchTimeValue(searchTimesT, datas1, datas2, null, null, serIP1, serIP2, searchT, "0");
		ds1.setData(datas1);
		ds2.setData(datas2);
		dateSets.add(ds1);
		if(serIP2 != null && !"".equals(serIP2)){
			dateSets.add(ds2);
		}
		zlmap.put("lbsCategory", searchTimes);
		zlmap.put("lbsDataset", dateSets);
	}
	/**
	 * @name 查询时间段内的数据库状态
	 * @param manageList
	 * @param criterions
	 * @param orders
	 * @author caolj
	 * @date 2016年8月12日 上午12:09:07
	 * @message:
	 */
	public void getDbStatus(List<ManageMonitor> manageList, List<Criterion> criterions, List<Order> orders) {
		List<Category> searchTimes = new ArrayList<Category>();
		List<String> searchTimesT = new ArrayList<String>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		List<DataBaseMonitor> dbList = dbService.findByCriteria(criterions,orders);		
		//获取时间轴
		Date temp = null;
		String ip = "";
		String time = "";
		String serIP1 = manageList.get(0).getCode();
		String serIP2 = "";
		if(manageList.size()>1){
			serIP2 = manageList.get(1)==null?"":manageList.get(1).getCode();
		}
		DataSet ds1 = new DataSet(serIP1+"数据库连接状态");
		List<Data> datas1 = new ArrayList<Data>();
		DataSet ds2 = new DataSet(serIP2+"数据库连接状态");
		List<Data> datas2 = new ArrayList<Data>();
		Map<String,String> searchMap = new HashMap<String,String>();
		Map<String,Map<String,String>> searchT = new HashMap<String,Map<String,String>>();
		for(DataBaseMonitor ser : dbList){
			temp = ser.getCreateTime();
			time= DateTimeFmtSpec.getMinutesFormat().format(temp.getTime());
			if(!searchMap.containsKey(time)){
				Category category = new Category(time.substring(5, time.length()));
				searchTimes.add(category);
				searchTimesT.add(time);
			}
			searchMap.put(time, time);
			ip = ser.getIp();
			Map<String,String> searchV = new HashMap<String,String>();
			searchV.put("cpu", ser.getPortStatus().equals("1")?"100":"0");
			searchT.put(time+ip, searchV);
		}
		setSesarchTimeValue(searchTimesT, datas1, datas2, null, null, serIP1, serIP2, searchT, "0");
		ds1.setData(datas1);
		ds2.setData(datas2);
		dateSets.add(ds1);
		if(serIP2 != null && !"".equals(serIP2)){
			dateSets.add(ds2);
		}
		zlmap.put("dbCategory", searchTimes);
		zlmap.put("dbDataset", dateSets);
	}

	public void setSesarchTimeValue(List<String> searchTimes,
			List<Data> datas1, List<Data> datas2, List<Data> datas3,
			List<Data> datas4, String serIP1, String serIP2,
			Map<String, Map<String, String>> searchT, String flag) {
		String c1Time = "";
		String c2Time = "";
		Map<String, String> searchV = null;
		for (String cTime : searchTimes) {
			c1Time = cTime + serIP1;
			c2Time = cTime + serIP2;
			if (searchT.containsKey(c1Time)) {
				searchV = searchT.get(c1Time);
				Data dataCpu = new Data((String) searchV.get("cpu"));
				datas1.add(dataCpu);
				if("1".equals(flag)){
					Data dataNc = new Data((String)searchV.get("wlnc"));
					datas3.add(dataNc);
				}
			} else {
				datas1.add(null);
				if("1".equals(flag)){
					datas3.add(null);
				}
			}
			if (searchT.containsKey(c2Time)) {
				searchV = searchT.get(c2Time);
				Data dataCpu = new Data((String) searchV.get("cpu"));
				datas2.add(dataCpu);
				if("1".equals(flag)){
					Data dataNc = new Data((String)searchV.get("wlnc"));
					datas4.add(dataNc);
				}
			} else {
				datas2.add(null);
				if("1".equals(flag)){
					datas4.add(null);
				}
			}
		}
	}
	@Override
	public String list() throws Throwable {
		return Action.SUCCESS;
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
