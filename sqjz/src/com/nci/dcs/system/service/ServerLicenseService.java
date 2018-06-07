package com.nci.dcs.system.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.EncodingUtil;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.system.dao.LogDao;
import com.nci.dcs.system.dao.ServerLicenseDao;
import com.nci.dcs.system.model.ServerLicense;

@Service
@Transactional
public class ServerLicenseService extends BaseService<ServerLicense, Integer> {

	@Autowired
	private ServerLicenseDao serverLicenseDao;
	@Autowired
	private LogDao logDao;

	/**
	 * Description:获取授权信息
	 * 
	 * @author Shuzz
	 * @param sysCode
	 * @return
	 * @since 2015年8月25日下午3:00:05
	 */
	public List<Date> serverAuthenticate(String sysCode) {
		List<Date> result = new ArrayList<Date>();
		List<ServerLicense> licenses = serverLicenseDao.findByProperty(
				"sysCode", sysCode);
		try {
			if (CommonUtils.isNotNull(licenses)) {
				String startTime = licenses.get(0).getStartTime();
				String endTime = licenses.get(0).getEndTime();
				Date start = null, end = null;
				start = DateTimeFmtSpec.getDateFormat().parse(
						EncodingUtil.aesDecrypt(startTime,
								EncodingUtil.encryptKey, EncodingUtil.ivKey));
				end = DateTimeFmtSpec.getDateFormat().parse(
						EncodingUtil.aesDecrypt(endTime,
								EncodingUtil.encryptKey, EncodingUtil.ivKey));
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(end);
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				result.add(start);
				result.add(calendar.getTime());
			}
		} catch (Exception e) {
			result = null;
			logger.error("获取授权时间失败", e);
		}
		return result;
	}

	public void addLicense(String sysCode, String start, String end) {
		List<ServerLicense> licenses = serverLicenseDao.findByProperty(
				"sysCode", sysCode);
		ServerLicense serverLicense = null;
		if (CommonUtils.isNotNull(licenses)) {
			serverLicense = licenses.get(0);
		} else {
			serverLicense = new ServerLicense();
			serverLicense.setSysCode(sysCode);
		}
		serverLicense.setStartTime(start);
		serverLicense.setEndTime(end);
		serverLicenseDao.save(serverLicense);
	}

	public Date getDataBaseTime() {
		return (Date) serverLicenseDao.createSQLQuery(
				"select sysdate from dual").uniqueResult();
	}

	@Override
	public void create(ServerLicense entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ServerLicense entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ServerLicense get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServerLicense> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ServerLicense> findPaged(Page<ServerLicense> page,
			ServerLicense entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void audit(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<ServerLicense> findPaged(Page<ServerLicense> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ServerLicense entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
