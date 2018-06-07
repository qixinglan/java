package com.nci.dcs.monitor.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.monitor.server.dao.ServiceMonitorDao;
import com.nci.dcs.monitor.server.model.ServiceMonitor;
import com.sun.management.OperatingSystemMXBean;


@Service
@Transactional
public class ServiceMonitorService extends BaseService<ServiceMonitor, String>{
	
	@Autowired
	private ServiceMonitorDao dao;
	@Autowired
	private ManageMonitorService manageService;

	private Logger log = LoggerFactory.getLogger(getClass());
	private static final int CPUTIME = 30;
	private static final int PERCENT = 100;
	private static final int FAULTLENGTH = 10;
	
	//获取数据
	public ServiceMonitor getMonotorInfoBean() throws Exception {
		ServiceMonitor bean = new ServiceMonitor();
		bean.setCreateTime(new Date());
		int kb=1024;
		//可用内存
		long totalMemory = Runtime.getRuntime().totalMemory()/kb;
		//剩余内存
		long freeMemory = Runtime.getRuntime().freeMemory()/kb;
		//最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory()/kb;
		//操作系统
		String osName = System.getProperty("os.name");
		//总物理内存
		long totalMemorySize = 0;
		//剩余物理内存
		long freePhysicalMemorySize = 0;
		//已使用物理内存
		long usedMemory = 0;
		//线程总数
		ThreadGroup parentThread;
		for(parentThread = Thread.currentThread().getThreadGroup();parentThread.getParent()!=null;parentThread = parentThread.getParent());
		long totalThread = parentThread.activeCount();
		//cpu使用率
		double cpuRatio = 0;
		String ip = manageService.getIp();
		if(osName.toLowerCase().startsWith("windows")){
			OperatingSystemMXBean osmxb = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
			totalMemorySize = osmxb.getTotalPhysicalMemorySize()/kb;
			freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize()/kb;
			usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize())/kb;
			cpuRatio = getCpuForWin();
		}else{
			Map<String, Object> memMap= getCpuForLinux();
			cpuRatio = (Double)memMap.get("usage");
			totalMemorySize = Long.parseLong((String)memMap.get("totalMemorySize"));
			freePhysicalMemorySize = Long.parseLong((String)memMap.get("freePhysicalMemorySize"));
			usedMemory = Long.parseLong((String)memMap.get("usedMemory"));
		}
		double wlncRatio = Double.valueOf(PERCENT*usedMemory/totalMemorySize).doubleValue();
		bean.setTotalMemory(totalMemory);
		bean.setFreeMemory(freeMemory);
		bean.setMaxMemory(maxMemory);
		bean.setOsName(osName);
		bean.setTotalMemorySize(totalMemorySize);
		bean.setFreePhysicalMemorySize(freePhysicalMemorySize);
		bean.setUsedMemory(usedMemory);
		bean.setTotalThread(totalThread);
		DecimalFormat df = new DecimalFormat("##.##");
		bean.setCpuRatio(df.format(cpuRatio)+"%");
		bean.setIp(ip);
		bean.setWlncRatio(df.format(wlncRatio)+"%");
		return bean;
	}
	//linuxCpu使用率
	public Map<String,Object> getCpuForLinux(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader brStat = null;
		StringTokenizer tokenStat = null;
		Map<String,Object> res = new HashMap<String,Object>();
		try{
			Process process = Runtime.getRuntime().exec("top -b -n 1");
			is = process.getInputStream();
			isr =new InputStreamReader(is);
			brStat = new BufferedReader(isr);
			brStat.readLine();
			brStat.readLine();
			tokenStat= new StringTokenizer(brStat.readLine());
			tokenStat.nextToken();
			String cpuUsage = tokenStat.nextToken();
			Double usage = Double.valueOf(cpuUsage.substring(0,cpuUsage.indexOf("%")));
			res.put("usage", usage);
			String mem = brStat.readLine();
			mem = mem.replace("Mem:", "").replace("k", "").replace(" ", "");
			String[] memArray = mem.split(",");
			for(String temp : memArray){
				if(temp.indexOf("total")>=0){
					temp = temp.replace("total", "");
					res.put("totalMemorySize", temp);
				}else if(temp.indexOf("used")>=0){
					temp = temp.replace("used", "");
					res.put("usedMemory", temp);
				}else if(temp.indexOf("free")>=0){
					temp = temp.replace("free", "");
					res.put("freePhysicalMemorySize", temp);
				}
			}
			return res;
		}catch(IOException ioe){
			log.error("", ioe);
			freeResource(is,isr,brStat);
			return res;
		}finally{
			freeResource(is,isr,brStat);
		}
	}
	public void freeResource(InputStream is, InputStreamReader isr, BufferedReader br){
		try{
			if(is!=null)
				is.close();
			if(isr!=null)
				isr.close();
			if(br!=null)
				br.close();
		}catch(IOException ioe){
			log.error("", ioe);
		}
	}
	//windowsCpu使用率
	public double getCpuForWin(){
		try{
			String procCMD = System.getenv("windir")+"\\system32\\wbem\\wmic.exe "
					+ "process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCMD));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCMD));
			if(c0!=null && c1!=null){
				long idletime = c1[0]-c0[0];
				long busytime = c1[1]-c0[1];
				idletime = idletime<0?(idletime*-1):idletime;
				busytime = busytime<0?(busytime*-1):busytime;
				double ratio = Double.valueOf(PERCENT*(busytime)/(busytime+idletime)).doubleValue();
				return ratio;
			}else{
				return 0.0;
			}
		}catch(Exception e){
			log.error("", e);
			e.printStackTrace();
			return 0.0;
		}
	}
	//读取CPU使用率
	private long[] readCpu(final Process proc) {
		long[] res = new long[2];
		try{
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if(line == null || line.length()<FAULTLENGTH){
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while((line = input.readLine())!=null){
				if(line.length()<wocidx){
					continue;
				}
				String caption = substring(line,capidx,cmdidx-1).trim();
				String cmd = substring(line,cmdidx,kmtidx-1).trim();
				if(cmd.indexOf("wmic.exe")>=0){
					continue;
				}
				if(caption.equals("System Idle Process")||caption.equals("System")){
					idletime+=Long.valueOf(substring(line,kmtidx,rocidx-1).trim()).longValue();
					idletime+=Long.valueOf(substring(line,umtidx,wocidx-1).trim()).longValue();
					continue;
				}
				kneltime+=Long.valueOf(substring(line,kmtidx,rocidx-1).trim()).longValue();
				usertime+=Long.valueOf(substring(line,umtidx,wocidx-1).trim()).longValue();
			}
			res[0] = idletime;
			res[1] = kneltime+usertime;
			return res;
		}catch(Exception e){
			log.error("", e);
			e.printStackTrace();
		}finally{
			try{
				proc.getInputStream().close();
			}catch(Exception e){
				log.error("", e);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String substring(String src,int s,int e){
		byte[] b = src.getBytes();
		String tgt = "";
		for(int i=s;i<=e;i++){
			tgt += (char)b[i];
		}
		return tgt;
	}
	/**
	 * @name 定时任务
	 * @author caolj
	 * @date 2016年6月17日 下午2:42:15
	 * @message:
	 */
	public void runServiceMonitor() throws Exception{
		try{
			ServiceMonitor bean = getMonotorInfoBean();
			create(bean);
			String cpu = bean.getCpuRatio();
			cpu = cpu.replace("%", "");
			double cpud = Double.parseDouble(cpu);
			//异常情况发送短息通知
			if(cpud>90){
				String content = "服务器"+bean.getIp()+"异常，请及时检查！";
				manageService.sendMessage(content);
			}
		}catch(Exception e){
			log.error("", e);
		}
	}
	
	public Page<ServiceMonitor> findPaged(Page<ServiceMonitor> page) {
		return dao.findByCriteria(page);
	}
	
	public Criteria createCriteria(List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = dao.createCriteria(criterions);
		if (CommonUtils.isNotNull(orders)) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		return criteria;
	}

	public List findBySql(String sql,Object... value){
		Query q = dao.createSQLQuery(sql,value);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceMonitor> findByCriteria(List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = createCriteria(criterions, orders);
		if (null != criteria) {
			return criteria.list();
		}
		return null;
	}
	
	
	@Override
	public void create(ServiceMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(ServiceMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(ServiceMonitor entity){
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

	public void delete(ServiceMonitor entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public ServiceMonitor get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<ServiceMonitor> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ServiceMonitor> findPaged(Page<ServiceMonitor> page,
			ServiceMonitor entity) {
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
	public Page<ServiceMonitor> findPaged(Page<ServiceMonitor> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ServiceMonitor entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
