package com.nci.dcs.monitor.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.monitor.server.dao.ManageMonitorDao;
import com.nci.dcs.monitor.server.model.ManageMonitor;
import com.nci.dcs.webservices.dxpt.DXPTSendException;
import com.nci.dcs.webservices.dxpt.service.DXPTService;


@Service
@Transactional
public class ManageMonitorService extends BaseService<ManageMonitor, String>{
	
	@Autowired
	private ManageMonitorDao dao;
	
	@Autowired
	private DXPTService dxptService;
	
	public Page<ManageMonitor> findPaged(Page<ManageMonitor> page) {
		return dao.findByCriteria(page);
	}
	
	private Logger log = LoggerFactory.getLogger(getClass());
	// 获取Linux下的IP地址
	public String getIp() {
		String res = "";
		//操作系统
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().startsWith("windows")){
			InetAddress add;
			try {
				add = InetAddress.getLocalHost();
				res = add.getHostAddress();
			} catch (UnknownHostException e) {
				log.error("", e);
				e.printStackTrace();
			}
		}else{
			Enumeration<NetworkInterface> allNet;// 网络接口枚举类
			try {
				allNet = NetworkInterface.getNetworkInterfaces();// 获取网络接口
				InetAddress ip = null;
				while (allNet.hasMoreElements()) {
					NetworkInterface net = allNet.nextElement();
					Enumeration<InetAddress> addrs = net.getInetAddresses();// 网络地址枚举类
					while (addrs.hasMoreElements()) {
						ip = addrs.nextElement();
						if (ip != null && (ip instanceof Inet4Address)) {
							res = ip.getHostAddress();
							break;
						}
					}
					if (res != null && !res.equals("")) {
						break;
					}
				}
			} catch (SocketException e) {
				log.error("", e);
				res = "";
			}
		}
		return res;
	}
	
	public void sendMessage(String content){
		String name = "系统监控";
		try {
			List<ManageMonitor> manageListf = this.findByCriteria(Restrictions.eq("type", "phoneFlag"));
			if(manageListf != null && manageListf.size()>0){
				ManageMonitor tempf = manageListf.get(0);
				String flag = tempf.getCode();
				if(flag!=null && "1".equals(flag)){//是否发送短信标记，1发送
					List<ManageMonitor> manageList = this.findByCriteria(Restrictions.eq("type", "phone"));
					if(manageList != null && manageList.size()>0){
						for(ManageMonitor temp : manageList){
							dxptService.sendPlatformMessage(temp.getCode(), name,content);
						}
					}
				}
			}
		} catch (DXPTSendException e) {
			log.error("",e);
			e.printStackTrace();
		}
	}
	public Map<String,String> getNetWorkStatus(String ip){
		//操作系统
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().startsWith("windows")){
			return getNetworkForWin(ip);
		}else{
			return getNetworkForLinux(ip);
		}
	}
	public Map<String,String> getNetworkForWin(String ip){
		Map<String,String> resMap = new HashMap<String,String>();
		InputStream is = null;
		InputStreamReader isr = null;
		LineNumberReader lnr = null;
		try {
			Process process = Runtime.getRuntime().exec("ping "+ip);
			is = process.getInputStream();
			isr = new InputStreamReader(is); 
			lnr = new LineNumberReader(isr);
			String line = "";
			String packets = "";
			while((line = lnr.readLine())!=null){
				line = line.replace(" ", "");
				if(line.indexOf("Packets:")>=0){
					//XP\Linux Packets: Sent = 4, Received = 4, Lost = 0 (0% loss)
					packets = line;
					break;
				}else if(line.indexOf("数据包:")>=0 || line.indexOf("数据包：")>=0){
					//Win7\WinServer替换中文， 数据包: 已发送 = 4，已接收 = 4，丢失 = 0 (0% 丢失)
					line = line.replace("：", ":").replace("，", ",").replace("（", "(").replace("）", ")");
					packets = line.replace("数据包", "Packets").replace("已发送", "Sent").replace("已接收", "Received").replaceFirst("丢失", "Lost").replace("丢失", "loss");
					break;
				}
			}
			packets = packets.replace("Packets:", "").replace(" ", "");
			String data = packets.substring(0, packets.indexOf("("));
			String[] str = data.split(",");
			Map<String,String> resData = new HashMap<String,String>(3);
			for(String temp : str){
				String[] strT = temp.split("=");
				resData.put(strT[0], strT[1]);
			}
			String sent = resData.get("Sent");
			String received = resData.get("Received");
			String lost = resData.get("Lost");
			String lossRatio = packets.substring(packets.indexOf("(")+1, packets.indexOf(")"));
			lossRatio = lossRatio.replace("loss", "");
			lost = String.valueOf(Integer.parseInt(sent) - Integer.parseInt(received));
			resMap.put("sent", sent);
			resMap.put("received", received);
			resMap.put("lost", lost);
			resMap.put("lossRatio", lossRatio);
		}catch(IOException ioe){
			log.error("", ioe);
			freeResource(is,isr,lnr);
		}finally{
			freeResource(is,isr,lnr);
		}
		return resMap;
	}
	//linux
	public Map<String,String> getNetworkForLinux(String ip){
		Map<String,String> resMap = new HashMap<String,String>();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader brStat = null;
		try{
			Process process = Runtime.getRuntime().exec("ping -c 4 "+ip);
			is = process.getInputStream();
			isr =new InputStreamReader(is);
			brStat = new BufferedReader(isr);
			String res = "";
			while((res = brStat.readLine()) != null){
				if(res.indexOf("received")>=0){
					break;
				}
			}
			String sent = "4";
			String received = "";
			String lost = "";
			String lossRatio = "";
			String[] resArray = res.split(",");
			for(String temp : resArray){
				if(temp.indexOf("received")>=0){
					received = temp.replace("received", "");
					received = received.replace(" ", "");
				}else if(temp.indexOf("loss")>=0){
					lossRatio = temp.replace("loss", "");
					lossRatio = lossRatio.replace("packet", "");
					lossRatio = lossRatio.replace(" ", "");
				}
			}
			lost = String.valueOf(Integer.parseInt(sent) - Integer.parseInt(received));
			resMap.put("sent", sent);
			resMap.put("received", received);
			resMap.put("lost", lost);
			resMap.put("lossRatio", lossRatio);
		}catch(IOException ioe){
			log.error("", ioe);
			freeResource(is,isr,brStat);
		}finally{
			freeResource(is,isr,brStat);
		}
		return resMap;
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
	
	public List<ManageMonitor> findByCriteria(final Criterion... criterions) {
		return dao.findByCriteria(criterions);
	}
	
	@Override
	public void create(ManageMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void update(ManageMonitor entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	public void saveOrUpdate(ManageMonitor entity){
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

	public void delete(ManageMonitor entity) {
		if (entity != null){
			dao.delete(entity);
		}
	}

	
	@Override
	public ManageMonitor get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}
	@Override
	public List<ManageMonitor> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ManageMonitor> findPaged(Page<ManageMonitor> page,
			ManageMonitor entity) {
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
	public Page<ManageMonitor> findPaged(Page<ManageMonitor> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ManageMonitor entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
