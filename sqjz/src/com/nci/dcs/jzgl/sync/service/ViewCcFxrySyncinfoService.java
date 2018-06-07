package com.nci.dcs.jzgl.sync.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYRemoveAdjustService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.sync.dao.ViewCcFxrySyncInfoDao;
import com.nci.dcs.jzgl.sync.model.FxrySyncInfo;
import com.nci.dcs.jzgl.sync.model.ViewCcFxrySyncInfo;

@Service
@Transactional
public class ViewCcFxrySyncinfoService extends
		BaseService<ViewCcFxrySyncInfo, String> {

	@Autowired
	private ViewCcFxrySyncInfoDao viewCcFxrySyncInfoDao;
	@Autowired
	private FxrySyncinfoService fxrySyncinfoService;
	@Autowired
	private FXRYBaseInfoService fxryService;
	@Autowired
	protected FXRYRemoveAdjustService removeService;

	public Page<ViewCcFxrySyncInfo> findPaged(Page<ViewCcFxrySyncInfo> page) {
		return viewCcFxrySyncInfoDao.findByCriteria(page);
	}

	private void createSyncInfo(String fxryId, String time) {
		FxrySyncInfo fxrySyncInfo = new FxrySyncInfo();
		fxrySyncInfo.setState("2");
		fxrySyncInfo.setFxryId(fxryId);
		fxrySyncInfo.setSaveTime(time);

		FXRYBaseinfo baseinfo = fxryService.get(fxryId);
		String realState = baseinfo.getState();
		fxrySyncInfo.setRealState(realState);
		fxrySyncInfo.setSyncOrg(baseinfo.getResponseOrg());
		String syncState = FXRYState.ZJ;
		if (FXRYState.JJ.equals(realState)) {
			FXRYRemoveAdjust rmAdjust = removeService.getByFXRYId(fxryId);
			if (rmAdjust != null) {
				if (null == rmAdjust.getJailReason()
						|| (!"3,8,9,10".contains(rmAdjust.getJailReason()) || "1"
								.equals(rmAdjust.getJailReason()))) {
					syncState = FXRYState.JJ;
				}
			}
		}
		fxrySyncInfo.setSyncState(syncState);
		fxrySyncinfoService.create(fxrySyncInfo);
	}

	public void fxrySync(String fxryId) {
		try {
			String time = DateTimeFmtSpec.getDateFormat().format(new Date());
			List<FxrySyncInfo> infos = fxrySyncinfoService.findPagedByCriteria(
					0, 10, null, Restrictions.eq("saveTime", time),
					Restrictions.eq("fxryId", fxryId));
			for (FxrySyncInfo info : infos) {
				fxrySyncinfoService.delete(info.getId());
			}
			createSyncInfo(fxryId, time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fxrySync() {
		try {
			String time = DateTimeFmtSpec.getDateFormat().format(new Date());
			viewCcFxrySyncInfoDao.createSQLQuery(
					"delete from CC_FXRY_SYNC_INFO where SAVE_TIME='" + time
							+ "'").executeUpdate();
			Criteria c = viewCcFxrySyncInfoDao.createCriteria();
			Object o = c.setProjection(Projections.rowCount()).uniqueResult();
			// 执行Count查询
			int totalCount = 0;
			if (o != null) {
				totalCount = (Integer) o;
			}
			c.setProjection(null);
			c.setMaxResults(50);
			c.addOrder(Order.asc("id"));
			int num = 0;
			while (num < totalCount) {
				c.setFirstResult(num);
				List<ViewCcFxrySyncInfo> list = c.list();
				for (ViewCcFxrySyncInfo sync : list) {
					createSyncInfo(sync.getId(), time);
				}
				if (CommonUtils.isNotNull(list)) {
					num = num + list.size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void create(ViewCcFxrySyncInfo entity) {
	}

	@Override
	public void update(ViewCcFxrySyncInfo entity) {
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public ViewCcFxrySyncInfo get(String id) {
		return viewCcFxrySyncInfoDao.get(id);
	}

	@Override
	public List<ViewCcFxrySyncInfo> find() {
		return null;
	}

	@Override
	public Page<ViewCcFxrySyncInfo> findPaged(Page<ViewCcFxrySyncInfo> page,
			ViewCcFxrySyncInfo entity) {
		return viewCcFxrySyncInfoDao.findByCriteria(page);
	}

	@Override
	public void enable(String id) throws Exception {

	}

	@Override
	public void disable(String id) throws Exception {

	}

	@Override
	public void audit(String id) throws Exception {

	}

	@Override
	public Page<ViewCcFxrySyncInfo> findPaged(Page<ViewCcFxrySyncInfo> page,
			String hql, Object... values) {
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(ViewCcFxrySyncInfo entity) {
		return null;
	}

}
