package com.nci.dcs.jzgl.fingerprint.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.FTPUtil;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.PropertyFilter.MatchType;
import com.nci.dcs.em.dwjk.dao.FxryBasicinfoDao;
import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;
import com.nci.dcs.jzgl.fingerprint.dao.FingerPrintDao;
import com.nci.dcs.jzgl.fingerprint.dao.FingerPrintManagerDao;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;
import com.nci.dcs.jzgl.fingerprint.model.FingerPrintCommpost;
import com.nci.dcs.official.dao.OrganizationInfoDao;
import com.nci.dcs.official.service.OrganizationInfoService;

@Service
@Transactional
public class FingerPrintManagerService extends BaseService<CcFingerprintMachine, String>{

	@Autowired
	FingerPrintManagerDao fingerPrintManagerDao;
	
	@Autowired
	FingerPrintDao  fingerPrintDao;
	
	@Autowired
	FxryBasicinfoDao  fxryBasicinfoDao; 
	
	@Autowired
	OrganizationInfoDao orgDao;
	
	private static String host=null;
	private static int port;
	private static String user;
	private static String pswd;
	
	@Override
	public void audit(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<PropertyFilter> buildPropertyFilter(CcFingerprintMachine entity1,CcFingerprintMachine entity2) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = null;
		if(StringUtils.isNotEmpty(entity1.getMachineId())){
			filter = new PropertyFilter();
			filter.setPropertyName("machineId");
			filter.setMatchType(MatchType.LIKE);
			filter.setValue(entity1.getMachineId());
			filters.add(filter);
		}
		
		return filters;
	}

	@Override
	public void create(CcFingerprintMachine entity) {
		//if(entity.getId()==null||"".equals(entity.getId())){
			entity.setId(CommonUtils.uuid());
			/*addTask(1, entity.getMachineId(), entity.getMachineIp(), null);
		}else{
			addTask(2, entity.getMachineId(), null, null);
			addTask(1, entity.getMachineId(), entity.getMachineIp(), null);
		}*/
		fingerPrintManagerDao.save(entity);
	}
	
	public void addTask(int tasktype,String params1,String params2,String params3){
		fingerPrintDao.addTask(tasktype,params1,params2,params3);
	}

	@Override
	public void delete(String id) {
		CcFingerprintMachine entity = get(id);
		//fingerPrintDao.addTask(2, entity.getMachineId(), null, null);
		fingerPrintManagerDao.delete(id);
	}
	
	//采集指纹
	public void collectPrint(String personId,String name,String orgId){
		List<CcFingerprintMachine> machines = queryMachine(orgId);
		for(CcFingerprintMachine machine : machines){
			fingerPrintDao.addTask(3, personId, name, machine.getMachineId());
		}
	}

	@Override
	public void disable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public List<CcFingerprintMachine> find(String orgId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<String> orgList = (List<String>) orgDao.getChildrenIds(orgId);
		return fingerPrintManagerDao.find("from CcFingerprintMachine where orgId in (:l0)", orgList);
	}
	public List <CcFingerprintMachine> find(String col,String where){
		return fingerPrintManagerDao.find("select "+col+" from CcFingerprintMachine ");
	}

	@Override
	public Page<CcFingerprintMachine> findPaged(Page<CcFingerprintMachine> page, CcFingerprintMachine entity) {
			return fingerPrintManagerDao.findByFilters(page, this.buildPropertyFilter(entity));
	}

	public Page<CcFingerprintMachine> findPaged(Page<CcFingerprintMachine> page) {
		return fingerPrintManagerDao.findByCriteria(page);
	}
	
	@Override
	public Page<CcFingerprintMachine> findPaged(Page<CcFingerprintMachine> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CcFingerprintMachine get(String id) {
		// TODO Auto-generated method stub
		return fingerPrintManagerDao.get(id);
	}

	@Override
	public void update(CcFingerprintMachine entity) {
		// TODO Auto-generated method stub
		
	}
	public List findByfilter(CcFingerprintMachine entity1,CcFingerprintMachine entity2){
		return fingerPrintManagerDao.findByFilters(this.buildPropertyFilter(entity1,entity2));
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcFingerprintMachine entity) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		return filters;
	}
	
	public List findBySql(String sql,Object... value){
		Query q = fingerPrintManagerDao.createSQLQuery(sql,value);
		return q.list();
	}

	public List<CcFingerprintMachine> queryMachine(String orgIds) {
		return fingerPrintManagerDao.find(" from CcFingerprintMachine where orgId = ? and status = 1",orgIds);
	}

	public String getCode(String prefix){
		int value = 0;
		try{
			value = fingerPrintManagerDao.getOrderCode();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			//logger.warn("生成获取服刑人员编号出错", e);
		}
		return prefix + String.format("%08d", value);
	}

	public String getFingerPicture(String userId) {
		StringBuffer filenames = new StringBuffer(); 
		String path = PathUtils.getRealPath()+"upload"
				+File.separator+"fingerprint"+File.separator;
		
		userId =userId.substring(10)+File.separator+userId;
		
		if(host==null){
			getFTPConfig();
		}
		File doc = new File(path+File.separator+userId+File.separator);
		if(!doc.exists()){
			doc.mkdirs();
		}
		FTPUtil ftp = new FTPUtil(host, port, user, pswd);
		ftp.connect();
		if(ftp.isExistDir(userId)){
		//if(ftp.isExistDir("1")){
			ftp.connect();
			List<String> fileList = ftp.getFileList("/"+userId);
			//List<String> fileList = ftp.getFileList("/1");
			for(String file : fileList){
				filenames.append(file).append(",");
				ftp.connect();
				ftp.downloadFile("/"+userId+"/"+file, path+File.separator+userId+File.separator+file);
				//ftp.downloadFile("/1/"+file, path+file);
			    ftp.disconnect();
			}
		}
		return filenames.toString();
	}
	
	private void getFTPConfig(){
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(getClass()
					.getResource("/").getPath() + "fingerPrintConfig.properties"));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		try {
			properties.load(in);
			host = properties.getProperty("host");
			port = Integer.parseInt(properties.getProperty("port"));
			user = properties.getProperty("user");
			pswd = properties.getProperty("pswd");
			logger.info("获取指纹仪数据库配置成功");
		} catch (IOException e) {
			logger.info("获取指纹仪数据库配置失败");
			logger.error(e.getMessage());
		}
	}
	/**
	 * 获取图片，
	 * @param userId
	 * @param date
	 * @param type 1：登记指纹 	2：登记头像 	3：报道头像
	 * @return
	 */
	public String getReportPicture(String userId,String picName,int type) {
		if(host==null){
			getFTPConfig();
		}
		userId =userId.substring(10)+File.separator+userId;
		String remotePath = File.separator+userId+File.separator;
		StringBuffer filenames = new StringBuffer(); 
		String localPath = PathUtils.getRealPath()+"upload"
				+File.separator+"fingerprint"+remotePath;
		
		File doc = new File(localPath+File.separator+"AttPhoto");
		if(!doc.exists()){
			doc.mkdirs();
		}
		
		FTPUtil ftp = new FTPUtil(host, port, user, pswd);
		ftp.connect();
		switch(type){
		case 1://指纹登记
			if(ftp.isExistDir(userId)){
				ftp.connect();
				List<String> fileList = ftp.getFileList(File.separator+userId);
				for(String file : fileList){
					if("11.jpg".equals(file))
						continue;
					filenames.append(file).append(",");
					ftp.connect();
					ftp.downloadFile(remotePath+file, localPath+file);
				    ftp.disconnect();
				}
			}
			return filenames.toString();
		case 2://头像登记
			if(ftp.isExistDir(userId)){
				ftp.connect();
				List<String> fileList = ftp.getFileList(File.separator+userId);
				for(String file : fileList){
					if(!"11.jpg".equals(file))
						continue;
					filenames.append(file).append(",");
					ftp.connect();
					ftp.downloadFile(remotePath+file, localPath+file);
				    ftp.disconnect();
				}
			}
			return filenames.toString();
		case 3://报道
			if(ftp.isExistDir(userId)){
				ftp.connect();
				List<String> fileList = ftp.getFileList(remotePath+"AttPhoto");
				String locFilePath = "";
				for(String file : fileList){
					locFilePath = localPath+"AttPhoto"+File.separator+file;
					File locFile = new File(locFilePath);
					if(!locFile.exists()){
						filenames.append(file).append(",");
						ftp.connect();
						ftp.downloadFile(remotePath+"AttPhoto"+File.separator+file, locFilePath);
						//ftp.deleteRemoteFile(File.separator+userId+File.separator+file);
					    ftp.disconnect();
					}
				}
			}
			break;
		}
		return filenames.toString();
	}

	public List<FingerPrintCommpost> getTask() throws SQLException {
		return fingerPrintDao.getTask();
	}
	
	public List<CcFxryBaseinfo> getFxryIdByPrintNo(String printNo){
		return fxryBasicinfoDao.find("from CcFxryBaseinfo t where t.fingerprintno=?",printNo);
	}
	
	public void updatePost(int postId,int status){
		fingerPrintDao.updatePost(postId,status);
	}

	@Override
	public List<CcFingerprintMachine> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public void reCollectPrint(String reCollect, String orgId, String fingerNo) {
		if(reCollect!=null&&reCollect.equals("true")){
			List<CcFingerprintMachine> outList = queryMachine(orgId);
			for(CcFingerprintMachine machine :outList){
				addTask(4,fingerNo, machine.getMachineId(), null);//删除
			}
		}
	}

}
