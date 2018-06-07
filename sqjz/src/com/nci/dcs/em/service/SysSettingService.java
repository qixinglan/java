package com.nci.dcs.em.service;

import java.math.BigDecimal;    //add by notebook
import java.util.Calendar;      //add by notebook
import java.util.Date;          //add by notebook
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;   //add by notebook
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.dao.LocationCountDao;    //add by notebook
import com.nci.dcs.em.dao.LocationPeriodDao;
import com.nci.dcs.em.dao.SysSettingDao;
import com.nci.dcs.em.model.LocationCount;     //add by notebook
import com.nci.dcs.em.model.LocationPeriod;
import com.nci.dcs.em.model.SettingArea;
import com.nci.dcs.em.model.SysSetting;

@Service
@Transactional
public class SysSettingService extends BaseService<SysSetting, String> {

	@Autowired
	private SysSettingDao sysSettingDao;
	@Autowired
	private LocationPeriodDao locationPeriodDao;
	@Autowired
	private LocationCountDao locationCountDao;   //add by notebook

	public void update(SysSetting entity, Set<LocationPeriod> locPeriods,
			Set<SettingArea> setAreas) {
		// TODO Auto-generated method stub
		try {
			locationPeriodDao.deleteBySys(entity);
			locationPeriodDao.createQuery(
					"delete SettingArea where sysSetting.settingId=?",
					entity.getSettingId()).executeUpdate();
			entity.setLocPeriods(locPeriods);
			entity.setSetAreas(setAreas);
			sysSettingDao.save(entity);
			countLocationNum(entity);    //add by notebook
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//add by notebook
	private void saveOrgLocationCount(String orgId, long count) {
		LocationCount locationCount = locationCountDao.findOneByProperty(
				"orgId", orgId);
		if (null == locationCount) {
			locationCount = new LocationCount();
			locationCount.setOrgId(orgId);
		}
		locationCount.setCount(new BigDecimal(count));
		locationCountDao.save(locationCount);
	}

	//add by notebook
	private void saveFxryLocationCount(String fxryId, long count) {
		LocationCount locationCount = locationCountDao.findOneByProperty(
				"fxryId", fxryId);
		if (null == locationCount) {
			locationCount = new LocationCount();
			locationCount.setFxryId(fxryId);
		}
		locationCount.setCount(new BigDecimal(count));
		locationCountDao.save(locationCount);
	}

	//add by notebook
	private long countPeriod(Set<LocationPeriod> periods) {
		long result = 0;
		try {
			for (LocationPeriod period : periods) {
				String[] temp = null;
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.SECOND, 0);
				temp = period.getStartTime().split(":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(temp[1]));
				Date start = calendar.getTime();

				temp = period.getEndTiem().split(":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(temp[1]));
				if (calendar.getTime().before(start)) {
					calendar.add(Calendar.DAY_OF_YEAR, 1);
				}
				BigDecimal distance = new BigDecimal(
						(calendar.getTimeInMillis() - start.getTime())
								/ (1000 * 60));
				result += distance.divide(new BigDecimal(period.getSpace()),
						BigDecimal.ROUND_HALF_EVEN).longValue();
			}
		} catch (Exception e) {
			result = 144;
		}
		return result;
	}

	//add by notebook
	@SuppressWarnings("unchecked")
	private void countLocationNum(SysSetting setting) {
		long total = 144;
		if (CommonUtils.isNull(setting.getFxryId())) {
			// 不是针对单个服刑人员进行设定的
			if (!"1".equals(setting.getOrgId())) {
				if (CommonUtils.isNotNull(setting.getLocPeriods())) {
					total = countPeriod(setting.getLocPeriods());
				} else {
					SysSetting sysSetting = sysSettingDao.findOneByProperty(
							"orgId", "1");
					if (null != sysSetting
							&& CommonUtils
									.isNotNull(sysSetting.getLocPeriods())) {
						total = countPeriod(sysSetting.getLocPeriods());
					}
				}
				saveOrgLocationCount(setting.getOrgId(), total);
			} else {
				if (CommonUtils.isNotNull(setting.getLocPeriods())) {
					total = countPeriod(setting.getLocPeriods());
				}
				String sql = "select org_id from cc_organization_info oi "
						+ "where oi.org_type='2' and oi.org_id not in "
						+ "(select distinct(org_id) from CC_SYS_SETTING t "
						+ "left join cc_location_period lp on t.setting_id=lp.setting_id "
						+ "where t.org_id is not null and t.fxry_id is null and lp.setting_id is not null and t.org_id !='1')";
				List<String> orgs = sysSettingDao.createSQLQuery(sql).list();
				for (String org : orgs) {
					saveOrgLocationCount(org, total);
				}
			}
		} else {
			if (CommonUtils.isNotNull(setting.getLocPeriods())) {
				// 针对单个服刑人员，且设置了时间周期
				total = countPeriod(setting.getLocPeriods());
				saveFxryLocationCount(setting.getFxryId(), total);
			}
		}
	}

	public String getXzqh(String id) {
		SysSetting sys = sysSettingDao.get(id);
		if (null != sys) {
			return getXzqh(sys);
		}
		return "";
	}

	private String getXzqh(SysSetting sys) {
		String result = "";
		for (SettingArea setArea : sys.getSetAreas()) {
			result = result + setArea.getAreaInfo().getAreaName() + ",";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	private String getSysset(SysSetting sys) {
		String result = "";
		if (sys.getRailType().equals("1")) {
			result = "北京";
		} else if (sys.getRailType().equals("2")) {
			result = getXzqh(sys);
		} else {
			result = sys.getSettingId();
		}
		return result;
	}

	public SysSetting getFxrySet(String fxryId) {
		return sysSettingDao.findOneByProperty("fxryId", fxryId);
	}

	public SysSetting getQxjSet(String orgId) {
		String sql = "select t.org_id,t.org_type from CC_ORGANIZATION_INFO t start with t.org_id=?"
				+ "connect by prior t.sup_org_id=t.org_id";
		List orgs = sysSettingDao.createSQLQuery(sql, orgId).list();
		for (Object o : orgs) {
			Object[] object1 = (Object[]) o;
			if ("2".equals((String) object1[1])) {
				return sysSettingDao.findOneByProperty("orgId",
						(String) object1[0]);
			}
		}
		return null;
	}

	public SysSetting getSjSet(String orgId) {
		String sql = "select t.org_id,t.org_type from CC_ORGANIZATION_INFO t start with t.org_id=?"
				+ "connect by prior t.sup_org_id=t.org_id";
		List orgs = sysSettingDao.createSQLQuery(sql, orgId).list();
		for (Object o : orgs) {
			Object[] object1 = (Object[]) o;
			if ("1".equals((String) object1[1])) {
				return sysSettingDao.findOneByProperty("orgId",
						(String) object1[0]);
			}
		}
		return null;
	}

	/**
	 * Description:根据服刑人员id和其所属机构的id组织其越界判断范围
	 * 
	 * @author Shuzz
	 * @since 2014年6月27日上午9:30:44
	 * @param fxryId
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getFxrySetting(String fxryId, String orgId) {
		String sql = "select t.org_id from CC_ORGANIZATION_INFO t start with t.org_id=?"
				+ "connect by prior t.sup_org_id=t.org_id";
		List orgIds = sysSettingDao.createSQLQuery(sql, orgId).list();
		String result = "";
		if (orgIds != null && orgIds.size() >= 2) {
			SysSetting sjsys = sysSettingDao.findOneByProperty("orgId",
					orgIds.get(orgIds.size() - 1));
			SysSetting qxjsys = sysSettingDao.findOneByProperty("orgId",
					orgIds.get(orgIds.size() - 2));
			SysSetting rysys = sysSettingDao
					.findOneByProperty("fxryId", fxryId);
			if (rysys != null) {
				if (!rysys.getStatus().equals("1"))
					return "";
				else if (rysys.getRailType().equals("2")) {
					return getXzqh(rysys);
				} else if (rysys.getRailType().equals("3")) {
					return fxryId;
				}
			}
			if (qxjsys != null) {
				result = getSysset(qxjsys);
			} else if (sjsys != null) {
				result = getSysset(sjsys);
			}
		}
		return result;
	}

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年5月23日下午2:17:19
	 * @param orgId
	 * @return
	 */
	public SysSetting getByOrgID(String orgId) {
		return sysSettingDao.findUniqueByProperty("orgId", orgId);
	}

	public SysSetting getByFxry(String fxryId) {
		return sysSettingDao.findUniqueByProperty("fxryId", fxryId);
	}

	/**
	 * Description:获取设置了采集周期的其他单位id
	 * 
	 * @author Shuzz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getOtherOrg() {
		String sql = "select distinct(org_id) from CC_SYS_SETTING t"
				+ " left join cc_location_period lp on t.setting_id=lp.setting_id"
				+ " where t.org_id is not null and t.fxry_id is null and lp.setting_id is not null and t.org_id !=?";
		return sysSettingDao.createSQLQuery(sql, "1").list();
	}

	@Override
	public void create(SysSetting entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(SysSetting entity) {
		// TODO Auto-generated method stub
		sysSettingDao.save(entity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SysSetting get(String id) {
		// TODO Auto-generated method stub
		return sysSettingDao.get(id);
	}

	@Override
	public List<SysSetting> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysSetting> findPaged(Page<SysSetting> page, SysSetting entity) {
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
	public Page<SysSetting> findPaged(Page<SysSetting> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(SysSetting entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
