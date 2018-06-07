package com.nci.dcs.em.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.id.uuid.UUID;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.base.xmpp.WorkProcess;
import com.nci.dcs.base.xmpp.XmppCallback;
import com.nci.dcs.base.xmpp.impl.DefaultXmppCallback;
import com.nci.dcs.base.xmpp.impl.DefaultXmppWork;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.EncodingUtil;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.common.utils.StrutsSessionManager;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dao.DevicePairDao;
import com.nci.dcs.em.dao.DevicePairHistoryDao;
import com.nci.dcs.em.dao.EmSuperviseDeviceDao;
import com.nci.dcs.em.model.CcDevicePair;
import com.nci.dcs.em.model.CcSuperviseDevice;
import com.nci.dcs.em.model.ViewDevicePair;
import com.nci.dcs.jzgl.dagl.dao.FXRYBaseInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYDevice;
import com.nci.dcs.jzgl.dagl.service.FXRYDeviceService;
import com.nci.dcs.system.model.User;
import com.nci.sfj.common.util.Config;
import com.nci.sfj.xmpp.sender.EmMessageSendService;

@Service
@Transactional
public class SuperviseDeviceService extends
		BaseService<CcSuperviseDevice, String> {

	@Autowired
	private EmSuperviseDeviceDao emSuperviseDeviceDao;
	@Autowired
	private FXRYBaseInfoDao fxryBaseInfoDao;
	@Autowired
	private DevicePairDao devicePairDao;
	@Autowired
	private FXRYDeviceService fxryDeviceService;
	@Autowired
	private EmMessageSendService emMessageSendService;
	@Autowired
	private DevicePairHistoryDao devicePairHistoryDao;

	@Override
	public void create(CcSuperviseDevice entity) {
		// if
		// (org.apache.commons.lang.StringUtils.isEmpty(entity.getDeviceId()))
		// entity.setDeviceId(UUID.randomUUID().toString().replace("-", ""));
		emSuperviseDeviceDao.save(entity);
	}

	@Override
	public void update(CcSuperviseDevice entity) {
		emSuperviseDeviceDao.save(entity);
	}

	@Override
	public void delete(String id) {
		CcDevicePair pair = emSuperviseDeviceDao.get(id).getDevicePair();
		pair.setEndDate(new Date());
		devicePairDao.save(pair);
		emSuperviseDeviceDao.delete(pair.getMachine());
		emSuperviseDeviceDao.delete(pair.getWatch());
	}

	public CcDevicePair getDevicePair(String id) {
		return emSuperviseDeviceDao.get(id).getDevicePair();
	}

	@Override
	public CcSuperviseDevice get(String id) {
		return emSuperviseDeviceDao.get(id);
	}

	public List<CcSuperviseDevice> find(String col, String where) {
		return emSuperviseDeviceDao.find("select " + col
				+ " from CcLawEnforcement ");
	}

	@Override
	public Page<CcSuperviseDevice> findPaged(Page<CcSuperviseDevice> page,
			CcSuperviseDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<CcSuperviseDevice> findPaged(Page<CcSuperviseDevice> page) {
		return emSuperviseDeviceDao.findByCriteria(page);
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
	public Page<CcSuperviseDevice> findPaged(Page<CcSuperviseDevice> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(CcSuperviseDevice entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmSuperviseDeviceDao getSuperviseDeviceDao() {
		return emSuperviseDeviceDao;
	}

	public void setSuperviseDeviceDao(EmSuperviseDeviceDao superviseDeviceDao) {
		this.emSuperviseDeviceDao = superviseDeviceDao;
	}

	public void delete(String[] ids) {
		for (String id : ids)
			delete(id);
	}

	@Override
	public List<CcSuperviseDevice> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CcSuperviseDevice> findPaged(
			final Page<CcSuperviseDevice> page, List<SearchRule> searchRules) {
		return emSuperviseDeviceDao.findBySeachRule(page, searchRules)
				.getResult();
	}

	public List<CcSuperviseDevice> findPaged(
			final Page<CcSuperviseDevice> page, List<SearchRule> searchRules,
			List<String> columnNames) {
		return emSuperviseDeviceDao.findBySeachRule(page, searchRules,
				columnNames).getResult();
	}

	/**
	 * 修改设备状态为未用
	 * 
	 * @param fxry
	 *            FXRYBaseinfo实体，包含Report
	 * @return 如果设备不可用，返回false；如果给定的编号不存在，throw
	 *         nullpointerexception；如果设备并成功修改状态，返回true
	 */
	public boolean equip(FXRYBaseinfo fxry) {
		boolean flag = changeDeviceStatus(fxry.getDeviceCode(),
				Constants.STATUS_UNUSED, Constants.STATUS_USED,
				fxry.getResponseOrg(), fxry.getId());
		if (flag) {
			emMessageSendService.equip(fxry);
			// fxry.getReport().getPhone() 责任干警电话
		}
		return flag;
	}

	/**
	 * 修改设备状态，如果配对、腕表、主机的状态都为expectedStatus，则将状态修改为newStatus
	 * 
	 * @param fxryId
	 * @param machineNumber
	 *            主机编号
	 * @param expectedStatus
	 *            当前的状态
	 * @param newStatus
	 *            新状态
	 * @return 如果配对、腕表、主机的状态不都为expectedStatus，返回false；如果给定的编号不存在，throw
	 *         nullpointerexception；如果设备并成功修改状态，返回true
	 */
	public boolean changeDeviceStatus(String machineNumber,
			String expectedStatus, String newStatus, String useOrg,
			String fxryid) {
		CcSuperviseDevice machine = (CcSuperviseDevice) ((SQLQuery) emSuperviseDeviceDao
				.createSQLQuery(
						"select * from CC_SUPERVISE_DEVICE where DEVICE_NUMBER=? for update skip locked",
						machineNumber)).addEntity(CcSuperviseDevice.class)
				.uniqueResult();
		if (machine == null) {
			machine = (CcSuperviseDevice) emSuperviseDeviceDao
					.createCriteria(
							Restrictions.eq("deviceNumber", machineNumber),
							Restrictions.eq("deviceType",
									Constants.TYPE_MACHINE))
					.setLockMode(LockMode.NONE).uniqueResult();
			Assert.notNull(machine, "无法找到编号为" + machineNumber + "的主机设备。");
			Assert.isNull(machine, "编号为" + machineNumber + "的主机设备已被锁定，请稍后再试。");

		}
		// 修改原pair纪录状态截至时间
		CcDevicePair pair = machine.getDevicePair();
		CcSuperviseDevice watch = pair.getWatch();
		if (!machine.getDeviceStatus().equals(expectedStatus)
				|| !pair.getStatus().equals(expectedStatus)
				|| !watch.getDeviceStatus().equals(expectedStatus)) {
			logger.warn("无法将编号" + machineNumber + "主机的状态从" + expectedStatus
					+ "修改为" + newStatus + "，主机状态：" + machine.getDeviceStatus()
					+ ";腕表编号：" + watch.getDeviceNumber() + "；腕表状态："
					+ watch.getDeviceStatus() + ";配对编号:"
					+ pair.getPairDeviceNumber() + ";配对状态：" + pair.getStatus()
					+ ";");
			return false;
		}
		HttpSession session = StrutsSessionManager.getSession();
		User user = LoginInfoUtils.getUser(session);
		pair.setEndDate(new Date());
		pair.setMdforg(user.getWunit().getBm());
		pair.setMdfperson(user.getId());

		// 生成新的pair纪录
		CcDevicePair newPair = pairStatusChanged(pair, newStatus);
		newPair.setFxryid(fxryid);
		newPair.setOrg(useOrg);
		newPair.setFxryid(fxryid);
		watch.setDevicePair(newPair);
		watch.setDeviceStatus(newStatus);
		watch.setOrg(useOrg);
		machine.setDevicePair(newPair);
		machine.setDeviceStatus(newStatus);
		machine.setOrg(useOrg);

		devicePairDao.save(pair);
		devicePairDao.save(newPair);
		emSuperviseDeviceDao.save(watch);
		emSuperviseDeviceDao.save(machine);
		return true;
	}

	public CcDevicePair pairStatusChanged(CcDevicePair pair, String status) {
		CcDevicePair newPair = new CcDevicePair();
		newPair.setId(UUID.randomUUID().toString().replace("-", ""));
		newPair.setMachine(pair.getMachine());
		newPair.setWatch(pair.getWatch());
		newPair.setOrg(pair.getOrg());
		newPair.setPairDeviceNumber(pair.getPairDeviceNumber());
		newPair.setBeginDate(pair.getEndDate());
		newPair.setStatus(status);
		newPair.setCrtorg(pair.getMdforg());
		newPair.setCrtperson(pair.getMdfperson());

		return newPair;
	}

	/**
	 * 修改设备状态为已用
	 * 
	 * @param fxryId
	 * @param machineNumber
	 * @return 如果设备状态不是在用，返回false；如果给定的编号不存在，throw
	 *         nullpointerexception；如果设备并成功修改状态，返回true
	 */
	public boolean unequip(String machineNumber) {
		boolean flag = changeDeviceStatus(machineNumber, Constants.STATUS_USED,
				Constants.STATUS_UNUSED, null, null);
		if (flag) {
			emMessageSendService.unequip(machineNumber);
		}
		return flag;
	}

	/**
	 * Description:查询已用主机设备数是否超过授权数
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年8月13日上午10:04:04
	 */
	public boolean queryMachineEquipable() {
		Criteria criteria = emSuperviseDeviceDao.createCriteria(
				Restrictions.eq("deviceStatus", "03"),
				Restrictions.eq("deviceType", "1"));
		criteria.setProjection(Projections.rowCount());
		int totalCount = 0;
		try {
			totalCount = (Integer) criteria.uniqueResult();
		} catch (Exception e) {
			logger.warn("获取在用主机设备数出错:\n", e);
		}
		int maxCount = 5000;
		/*try {
			String encodeMax = Config.getString("xmpp.device.num.max", "");
			String count = EncodingUtil.aesDecrypt(encodeMax,
					EncodingUtil.encryptKey, EncodingUtil.ivKey);
			maxCount = Integer.parseInt(count);
		} catch (Exception e) {
			logger.warn("获取在用主机设备数出错:\n", e);
		}*/

		return totalCount < maxCount;
	}

	/**
	 * 查询未装备、可用的主机设备
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryEquipableMachine(String orgId) {
		return emSuperviseDeviceDao
				.createSQLQuery(
						"select d.device_Type as \"deviceType\", d.device_Number as \"deviceNumber\", d.pair_Device_Number as \"pairDeviceNumber\", d.device_Status as \"deviceStatus\" from CC_SUPERVISE_DEVICE d left join CC_DEVICE_PAIR p on p.PAIR_DEVICE_NUMBER=d.PAIR_DEVICE_NUMBER and p.END_DATE IS NULL where p.status='02' and d.device_Status='02' and d.device_Type='1' and d.procure_unit=? for update skip locked",
						orgId)
				.setResultTransformer(
						AliasToEntityMapResultTransformer.INSTANCE).list();
	}

	public Page<ViewDevicePair> searchHistory(Page<ViewDevicePair> page) {

		return devicePairHistoryDao.findByCriteria(page);
	}

	public EmSuperviseDeviceDao getEmSuperviseDeviceDao() {
		return emSuperviseDeviceDao;
	}

	public void setEmSuperviseDeviceDao(
			EmSuperviseDeviceDao emSuperviseDeviceDao) {
		this.emSuperviseDeviceDao = emSuperviseDeviceDao;
	}

	public DevicePairDao getDevicePairDao() {
		return devicePairDao;
	}

	public void setDevicePairDao(DevicePairDao devicePairDao) {
		this.devicePairDao = devicePairDao;
	}

	public XmppCallback<String> getXmppCallback() {
		return handler;
	}

	public String checkDevicePair(String pairId, String machineId,
			String watchId) {
		boolean canSave = true;
		String info = "";
		CcSuperviseDevice watch = (CcSuperviseDevice) emSuperviseDeviceDao
				.findUnique("from CcSuperviseDevice where  deviceNumber=?",
						watchId);
		CcSuperviseDevice machine = (CcSuperviseDevice) emSuperviseDeviceDao
				.findUnique("from CcSuperviseDevice where  deviceNumber=?",
						machineId);
		CcDevicePair pair = (CcDevicePair) devicePairDao.findUnique(
				"from CcDevicePair where pairDeviceNumber=?", pairId);
		/*
		 * 1. 腕表、主机的配对编号应该相同 2. 状态应该是不可用
		 */
		if (pair != null) {
			info += "编号为" + pairId + "的配对编号已存在;\r\n";
			canSave = false;
		}
		if (watch != null) {
			info += "编号为" + machineId + "的设备编号已存在;\r\n";
			canSave = false;
		}
		if (machine != null) {
			info += "编号为" + watchId + "的设备编号已存在;\r\n";
			canSave = false;
		}

		if (canSave) {
		} else {
			return info;
		}
		return null;
	}

	public List<String> imprt(List<CcDevicePair> pairs) {
		List<String> result = new ArrayList<String>();
		CcSuperviseDevice watch = null;
		CcSuperviseDevice machine = null;
		CcDevicePair pair = null;
		boolean canSave = true;
		for (CcDevicePair nPair : pairs) {
			canSave = true;
			watch = (CcSuperviseDevice) emSuperviseDeviceDao.findOne(
					"from CcSuperviseDevice where  deviceNumber=?", nPair
							.getWatch().getDeviceNumber());
			machine = (CcSuperviseDevice) emSuperviseDeviceDao.findOne(
					"from CcSuperviseDevice where   deviceNumber=?", nPair
							.getMachine().getDeviceNumber());
			pair = (CcDevicePair) devicePairDao.findOne(
					"from CcDevicePair where pairDeviceNumber=?",
					nPair.getPairDeviceNumber());
			String info = "第" + ((int) nPair.getNo()) + "行:";
			/*
			 * 1. 腕表、主机的配对编号应该相同 2. 状态应该是不可用
			 */
			if (pair != null) {
				info += "编号为" + pair.getPairDeviceNumber() + "的配对编号已存在;";
				canSave = false;
			}
			if (watch != null) {
				info += "编号为" + watch.getDeviceNumber() + "的设备编号已存在;";
				canSave = false;
			}
			if (machine != null) {
				info += "编号为" + machine.getDeviceNumber() + "的设备编号已存在;";
				canSave = false;
			}
			if (nPair.getMachine().getConnectPhone() == null) {
				info += "编号为" + machine.getDeviceNumber() + "的主机电话为空;";
				canSave = false;
			} else {
				Pattern p = Pattern.compile("\\d{11}");
				Matcher m = p.matcher(nPair.getMachine().getConnectPhone());
				if (!m.matches()) {
					info += "编号为" + nPair.getMachine().getDeviceNumber()
							+ "的主机电话号码不正确;";
					canSave = false;
				}
				;
			}

			if (canSave) {
				devicePairDao.save(nPair);
				emSuperviseDeviceDao.save(nPair.getWatch());
				emSuperviseDeviceDao.save(nPair.getMachine());
			} else {
				result.add(info);
			}
		}
		return result;
	}

	public FXRYBaseinfo getFxryByCode(String deviceNumber) {
		List<FXRYBaseinfo> fxrys = fxryBaseInfoDao.findByProperty("deviceCode",
				deviceNumber);
		if (CommonUtils.isNotNull(fxrys)) {
			return fxrys.get(0);
		}
		return null;
	}

	public void add(CcDevicePair pair, CcSuperviseDevice watch,
			CcSuperviseDevice machine, CcDevicePair newPair) {
		devicePairDao.save(pair);
		if (newPair != null) {
			devicePairDao.save(newPair);
		}
		create(watch);
		create(machine);
	}

	public void addPair(CcDevicePair pair) {
		devicePairDao.save(pair);
	}

	public Page<CcSuperviseDevice> findEmSupervisePaged(
			Page<CcSuperviseDevice> page, List<SearchRule> searchRules) {
		return emSuperviseDeviceDao.findBySeachRule(page, searchRules);
	}

	/**
	 * Description:查找其设备佩戴日期
	 * 
	 * @author Shuzz
	 * @since 2014年8月8日下午4:32:49
	 * @param fxryId
	 * @return
	 */
	public Date findAdornDate(String fxryId) {
		FXRYDevice device = fxryDeviceService.getByFXRYId(fxryId);
		if (device != null) {
			return device.getStartTime();
		}
		return null;
	}

	/**
	 * 绑定任务，返回一个标识，网页可以通过ajax提交标识，
	 * 从getBindProcesses方法获取所有的绑定状态;这个方法不需要阻塞线程，占用数据库连接，但需要前台用ajax定时查询状态
	 * 
	 * @param machineNumber
	 * @param watchNumber
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public String makePair(String machineNumber, String watchNumber,
			String phoneNumber) throws Exception {
		String info = blank;
		CcSuperviseDevice watch = (CcSuperviseDevice) emSuperviseDeviceDao
				.findUnique("from CcSuperviseDevice where  deviceNumber=?",
						watchNumber);
		CcSuperviseDevice machine = (CcSuperviseDevice) emSuperviseDeviceDao
				.findUnique("from CcSuperviseDevice where  deviceNumber=?",
						machineNumber);
		CcDevicePair pair = watch.getDevicePair();
		if (watch.getDeviceStatus().equals(Constants.STATUS_USED)) {
			info += "编号" + machineNumber + "腕表的状态为已使用，不能进行绑定操作;\r\n";
		}
		if (machine.getDeviceStatus().equals(Constants.STATUS_USED)) {
			info += "编号" + watchNumber + "主机的状态为已使用，不能进行绑定操作;\r\n";
		}
		if (pair.getStatus().equals(Constants.STATUS_USED)) {
			info += "编号" + watchNumber + "配对的状态为已使用，不能进行绑定操作;\r\n";
		}
		if (bindTasks.size() > 100) {
			info += "当前绑定任务已满，请稍后尝试。";
		}
		if (info != blank) {
			throw new Exception(info);
		}

		CcDevicePair newPair = changeStatues(watch, machine, pair,
				Constants.STATUS_PAIRING, true);

		// 绑定前后状态应该不变
		XmppBindTask task = new XmppBindTask(pair.getStatus(),
				pair.getStatus(), machineNumber, watchNumber,
				newPair.getPairDeviceNumber());
		synchronized (task) {
			try {
				task.setFuture(scheduledExecutorService.schedule(task,
						maxClearMapDelay, TimeUnit.MILLISECONDS));
				// 这里调用XMPP协议的方法，下面是个测试
				task.setId(emMessageSendService.pairDevice(
						machine.getDeviceNumber(), watch.getDeviceNumber()));
				bindTasks.put(task.getId(), task);
			} catch (RejectedExecutionException e) {
				info += "当前绑定任务已满，请稍后尝试。";
				handler.failed(task);
				throw new Exception(info);
			}
		}
		return task.getId();
	}

	/**
	 * Action 可以调用这个方法获取所有的状态
	 * 
	 * @param id
	 *            任务标识
	 * @param result
	 *            返回所有的历史状态
	 * @return 当前任务是否已经完成
	 */
	public boolean getBindProcesses(String id, List<WorkProcess> result) {
		XmppBindTask task = bindTasks.get(id);
		if (task != null && task.finished()) {
			task.getFuture().cancel(false);
			bindTasks.remove(id);
		}
		result.addAll(bindTasks.get(id).retireProcessHistory());
		return task.finished();
	}

	/*
	 * 修改状态的方法
	 */
	private CcDevicePair changeStatues(CcSuperviseDevice watch,
			CcSuperviseDevice machine, CcDevicePair pair, String state,
			boolean save) {
		watch.setDeviceStatus(state);
		machine.setDeviceStatus(state);
		pair.setEndDate(new Date());
		CcDevicePair newPair = pairStatusChanged(pair, state);
		if (save) {
			devicePairDao.save(pair);
			devicePairDao.save(newPair);
			emSuperviseDeviceDao.save(watch);
			emSuperviseDeviceDao.save(machine);
		}
		return newPair;
	}

	private CcDevicePair changeStatues(String watchNumber,
			String machineNumber, String pairNumber, String state, boolean save) {
		return changeStatues(getWatch(watchNumber), getMachine(machineNumber),
				getPair(pairNumber), state, save);
	}

	private final CcSuperviseDevice getWatch(String watchNumber) {
		return (CcSuperviseDevice) emSuperviseDeviceDao.findUnique(
				"from CcSuperviseDevice where  deviceNumber=?", watchNumber);

	}

	private final CcSuperviseDevice getMachine(String machineNumber) {
		return (CcSuperviseDevice) emSuperviseDeviceDao.findUnique(
				"from CcSuperviseDevice where  deviceNumber=?", machineNumber);
	}

	private final CcDevicePair getPair(String pairNumber) {
		return (CcDevicePair) devicePairDao
				.findUnique(
						"from CcDevicePair where endDate is null and pairDeviceNumber=?",
						pairNumber);
	}

	// 判断结果是否为空的临时变量
	private static final String blank = "";

	/*
	 * 如果用户没有等待XMPP任务处理完成就将页面关闭，MAP中可能会有垃圾数据，以下用于清除当前状态Map中的任务，1小时超时时间
	 */
	private Long maxClearMapDelay = 60 * 60 * 1000L;
	private ThreadGroup threadGroup = new ThreadGroup(
			"SuperviseDeviceService-XmppBindTask");
	private static final String THREAD_PREFIX = "SuperviseDeviceService-XmppBindTask";
	private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(
			5, new ThreadFactory() {
				private final AtomicInteger id = new AtomicInteger(0);;

				@Override
				public Thread newThread(Runnable r) {
					id.compareAndSet(100000, 0);
					return new Thread(threadGroup, r, THREAD_PREFIX
							+ id.getAndAdd(1));
				}
			});

	/**
	 * 所有的绑定任务
	 */
	private Map<String, XmppBindTask> bindTasks = new ConcurrentHashMap<String, XmppBindTask>();
	private XmppBindHandler handler = new XmppBindHandler();

	/**
	 * 调用XMPP的回调对象
	 */
	private class XmppBindHandler extends DefaultXmppCallback<String> {
		public XmppBindHandler() {
			super(SuperviseDeviceService.this.bindTasks);
		}

		@Override
		public void process(String id, Integer action, Integer state,
				String text, Object attachment) {
			super.process(id, action, state, text, attachment);
		}

		@Override
		public void finish(String id, Integer action, Integer state,
				String text, Object attachment) {
			super.finish(id, action, state, text, attachment);
			// TODO 需要修改为成功、失败
			if (state == 1) {
				success((XmppBindTask) bindTasks.get(id).finish());
			} else {
				failed((XmppBindTask) bindTasks.get(id).finish());
			}
		}

		@Override
		public void timeout(String id, Integer action, Integer state,
				String text, Object attachment) {
			super.timeout(id, action, state, text, attachment);
			failed(bindTasks.get(id));
		}

		/**
		 * 任务成功，将设备状态设置为新的状态
		 * 
		 * @param task
		 */
		protected void success(XmppBindTask task) {
			SuperviseDeviceService.this.changeStatues(task.getWatchNumber(),
					task.getMachineNumber(), task.getPairNumber(),
					task.getNewState(), true);
		}

		/**
		 * 任务失败，将设备状态设置为原来的状态
		 * 
		 * @param task
		 */
		protected void failed(XmppBindTask task) {
			SuperviseDeviceService.this.changeStatues(task.getWatchNumber(),
					task.getMachineNumber(), task.getPairNumber(),
					task.getOldState(), true);
		}
	}

	private class XmppBindTask extends DefaultXmppWork<String> implements
			Runnable {
		private String oldState;
		private String newState;
		private String machineNumber;
		private String watchNumber;
		private String pairNumber;
		/**
		 * 清除map中垃圾数据的定时任务
		 */
		private ScheduledFuture<?> future;

		public XmppBindTask(String oldState, String newState,
				String machineNumber, String watchNumber, String pairNumber) {
			this.oldState = oldState;
			this.newState = newState;
			this.machineNumber = machineNumber;
			this.watchNumber = watchNumber;
			this.pairNumber = pairNumber;
		}

		public String getOldState() {
			return oldState;
		}

		public String getMachineNumber() {
			return machineNumber;
		}

		public String getWatchNumber() {
			return watchNumber;
		}

		public String getPairNumber() {
			return pairNumber;
		}

		public ScheduledFuture<?> getFuture() {
			return future;
		}

		public void setFuture(ScheduledFuture<?> future) {
			this.future = future;
		}

		@Override
		public void run() {
			if (!finished()) {
				SuperviseDeviceService.this.handler.failed(this);
			}
			SuperviseDeviceService.this.bindTasks.remove(this.getId());
		}

		public String getNewState() {
			return newState;
		}
	}
}