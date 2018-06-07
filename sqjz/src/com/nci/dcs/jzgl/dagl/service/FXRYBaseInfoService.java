package com.nci.dcs.jzgl.dagl.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.directwebremoting.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.em.model.CcDzjgsbDevice;
import com.nci.dcs.em.model.CcSimDevice;
import com.nci.dcs.em.model.CcDeviceFxry;
import com.nci.dcs.em.model.CcDeviceRecord;
import com.nci.dcs.em.service.DeviceFxryService;
import com.nci.dcs.em.service.DeviceRecordService;
import com.nci.dcs.em.service.DzjgsbDeviceService;
import com.nci.dcs.em.service.SimDeviceService;
import com.nci.dcs.em.service.SuperviseDeviceService;
import com.nci.dcs.jzgl.dagl.dao.FXRYBaseInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYDevice;
import com.nci.dcs.jzgl.dagl.model.FXRYReportOrg;
import com.nci.dcs.jzgl.dagl.util.Constants.DeviceStatus;
import com.nci.dcs.jzgl.dagl.util.Constants.FXRYStateChangeType;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;
import com.nci.dcs.jzgl.fingerprint.service.FingerPrintManagerService;

@Service
@Transactional
public class FXRYBaseInfoService extends BaseService<FXRYBaseinfo, String> {
	private Logger logger = Logger.getLogger(FXRYBaseInfoService.class);
	@Autowired 
	private SimDeviceService simDeviceService;
	@Autowired
	DeviceFxryService deviceFxryService;
	@Autowired
	DeviceRecordService deviceRecordService;
	@Autowired
	private FXRYBaseInfoDao dao;
	@Autowired
	private DzjgsbDeviceService dzjgsbDeviceService;
	@Autowired
	private FXRYReportOrgService reportService;

	@Autowired
	private FXRYDeviceService deviceService;

	@Autowired
	SuperviseDeviceService equipServcie;

	@Autowired
	FXRYOutManageInfoService outManageService;

	@Autowired
	AddressService addressService;

	@Autowired
	private FingerPrintManagerService fingerService;
	//绑定设备
			public void equip(String deviceNumber,FXRYBaseinfo real,String userId,String curOrgId){
				String fxryId=real.getId();
				String orgId=real.getResponseOrg();
				if(StringUtils.isBlank(deviceNumber)){
					return;
				}
				//服刑人员绑定设备
				real.setDeviceCode(deviceNumber);
				dao.save(real);
				CcDzjgsbDevice dzjgsbDevice=dzjgsbDeviceService.findByDeviceNumber(deviceNumber);
				dzjgsbDevice.setStatus(Constants.DEVICE_USEDSTATUS);//状态修改为已用
				dzjgsbDevice.setUseSfsUnit(orgId);//设置司法所
				dzjgsbDevice.setWearPerson(fxryId);//佩戴人id
				dzjgsbDeviceService.update(dzjgsbDevice);
				//设备被使用后，设置sim卡号使用单位司法所
				CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(dzjgsbDevice.getPhoneNumber());
				simDevice.setUseSfsUnit(orgId);
				simDeviceService.update(simDevice);
				//新增设备记录——佩戴
				CcDeviceRecord deviceRecord=new CcDeviceRecord();
				deviceRecord.setDeviceNumber(deviceNumber);//设备编号
				deviceRecord.setOperateName(Constants.DEVICELIFE_WEAR);//操作名称
				deviceRecord.setOperateDate(new Date());//操作日期
				deviceRecord.setSimNumber(dzjgsbDevice.getPhoneNumber());//sim卡号
				deviceRecord.setUseUnit(dzjgsbDevice.getUseUnit());//使用单位司法局
				deviceRecord.setUseSfsUnit(orgId);//使用单位司法所
				deviceRecord.setOperatePersion(userId);//操作人id
				//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
				deviceRecord.setOperateUnit(curOrgId);//操作人单位
				deviceRecord.setWearPersion(fxryId);
				deviceRecordService.add(deviceRecord);
				//device——fxry添加记录
				//解绑之前的状态
				deviceFxryService.changeStatusByDn(deviceNumber,Constants.RELATED_NOTBINDSTATUS);
				CcDeviceFxry deviceFxry=new CcDeviceFxry();
				deviceFxry.setUseUnit(dzjgsbDevice.getUseUnit());
				deviceFxry.setUseSfsUnit(orgId);
				deviceFxry.setWearTime(new Date());
				deviceFxry.setFxryName(real.getName());
				deviceFxry.setCreatePersion(userId);
				deviceFxry.setDeviceNumber(deviceNumber);
				deviceFxry.setStatus(Constants.RELATED_BINDSTATUS);//绑定状态
				deviceFxry.setFxryId(fxryId);
				deviceFxryService.create(deviceFxry);
			}
			//解绑设备
			public void unequip(String deviceNumber,String userId,String curOrgId,FXRYBaseinfo entity){
				if(StringUtils.isBlank(deviceNumber)){
					return;
				}
				// 服刑人员清空设备编号,
				entity.setDeviceCode(null);
				dao.save(entity);
				logger.debug(String.format("解除设备绑定:%s(id=%s,设备编号:%s)",
						entity.getName(), entity.getId(), entity.getDeviceCode()));
				CcDzjgsbDevice dzjgsbDevice=dzjgsbDeviceService.findByDeviceNumber(deviceNumber);
				dzjgsbDevice.setStatus(Constants.DEVICE_RELEASESTATUS);//状态修改为已解绑
				//dzjgsbDevice.setUseSfsUnit(null);//不用设置司法所为空，解绑之后还在司法所，拿回来维修之后才为空
				dzjgsbDevice.setWearPerson(null);//佩戴人为空
				dzjgsbDeviceService.update(dzjgsbDevice);
				//设备解绑后，设置sim卡号使用单位司法所为空
				CcSimDevice simDevice=simDeviceService.findSimDeviceByPhoneNumber(dzjgsbDevice.getPhoneNumber());
				//simDevice.setUseSfsUnit(null);不用设置司法所为空，解绑之后还在司法所，拿回来维修之后才为空
				simDeviceService.update(simDevice);
				//新增设备记录——解除佩戴
				CcDeviceRecord deviceRecord=new CcDeviceRecord();
				deviceRecord.setDeviceNumber(deviceNumber);//设备编号
				deviceRecord.setOperateName(Constants.DEVICELIFE_REMOVEWEAR);//操作名称
				deviceRecord.setOperateDate(new Date());//操作日期
				deviceRecord.setSimNumber(dzjgsbDevice.getPhoneNumber());//sim卡号
				deviceRecord.setUseUnit(dzjgsbDevice.getUseUnit());//使用单位司法局
				deviceRecord.setUseSfsUnit(dzjgsbDevice.getUseSfsUnit());//使用单位司法所
				deviceRecord.setOperatePersion(userId);//操作人id
				//deviceRecord.setOperatePersionName(((User)session.getAttribute("user")).getName());
				deviceRecord.setOperateUnit(curOrgId);//操作人单位
				//deviceRecord.setWearPersion(fxryId);
				deviceRecordService.add(deviceRecord);
				//device——fxry添加记录
				//解绑之前的状态
				CcDeviceFxry deviceFxry=deviceFxryService.findByDeviceNumberAndStatus(deviceNumber,Constants.RELATED_BINDSTATUS);
				deviceFxry.setReleaseTime(new Date());
				deviceFxryService.update(deviceFxry);//添加解绑日期
				deviceFxryService.changeStatusByDn(deviceNumber,Constants.RELATED_NOTBINDSTATUS);
				}
	public void createWithEquip(FXRYBaseinfo entity) throws Exception {
		create(entity);
		//equip(entity.getDeviceCode(), entity);原先的方法equip不用了
	}

	private void equip(String deviceCode, FXRYBaseinfo entity) throws Exception {
		if (entity == null || StringUtils.isBlank(deviceCode)) {
			return;
		}
		try {
			if (StringUtils.isBlank(entity.getDeviceCode())
					&& !deviceCode.equals(entity.getDeviceCode())) {
				// 如果绑定了其它设备，先解绑
				unequip(entity);
			}
			FXRYDevice device = new FXRYDevice();
			device.setDeviceId(deviceCode);
			device.setFxryId(entity.getId());
			device.setStartTime(new Date());
			device.setStatus(DeviceStatus.USING);
			device.setFxryOrgId(entity.getResponseOrg());
			deviceService.create(device);
			entity.setDeviceCode(deviceCode);
			dao.save(entity);
			if (!equipServcie.equip(entity)) {
				throw new Exception("监管设备绑定出错:" + deviceCode + "已被占用！");
			}
		} catch (Exception e) {
			logger.error("设备绑定出错:" + e.getMessage());
			throw new Exception("设备绑定出错");
		}
	}

	public void equip(String deviceCode, String fxryId) throws Exception {
		equip(deviceCode, dao.get(fxryId));
	}

	private void unequip(FXRYBaseinfo entity) {
		if (entity == null || StringUtils.isBlank(entity.getDeviceCode())) {
			return;
		}
		// 修改设备状态
		equipServcie.unequip(entity.getDeviceCode());
		// 修改人员-设备使用记录
		FXRYDevice device = deviceService.getByFXRYId(entity.getId());
		device.setStatus(DeviceStatus.UNUSE);
		if (null != device.getUnbindStatus()) {
			device.setUnbindStatus("2");
		}
		device.setEndTime(new Date());
		deviceService.update(device);
		// 清空设备编号
		entity.setDeviceCode(null);
		dao.save(entity);
		logger.debug(String.format("解除设备绑定:%s(id=%s,设备编号:%s)",
				entity.getName(), entity.getId(), entity.getDeviceCode()));
	}

	public void unequip(String id) {
		unequip(get(id));
	}

	@Override
	public void create(FXRYBaseinfo entity) {
		entity.setState(FXRYState.ZJ);
		FXRYReportOrg report = entity.getReport();
		if (report != null) {
			entity.setResponseOrg(report.getOrgId());
		}
		dao.save(entity);
		if (report != null) {
			report.setFxryId(entity.getId());
			report.setRecordOrgId(entity.getRecordOrgId());
			reportService.updateByFxryId(report);
		}

		/*List<CcFingerprintMachine> machineList = fingerService
				.queryMachine(entity.getResponseOrg());
		for (CcFingerprintMachine machine : machineList) {
			fingerService.addTask(5, entity.getFingerPrintNo(),
					machine.getMachineId(), null);// 上传到司法所设备上
		}*/
	}

	@Override
	public void update(FXRYBaseinfo entity) {
		addressService.saveOrUpdate(entity.getHomeAddress());
		addressService.saveOrUpdate(entity.getHouseAddress());
		entity.setHouseAddressId(entity.getHouseAddress().getId());
		entity.setHomeAddressId(entity.getHomeAddress().getId());
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYBaseinfo get(String id) {
		FXRYBaseinfo entity = dao.get(id);
		return getAssociateInfo(entity);
	}

	@Override
	public List<FXRYBaseinfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FXRYBaseinfo> find(String orgIds, String state) {
		// TODO Auto-generated method stub
		String sql = "from FXRYBaseinfo where state=? and responseOrg = ?";
		return dao.createQuery(sql, state, orgIds).list();
	}

	@Override
	public Page<FXRYBaseinfo> findPaged(Page<FXRYBaseinfo> page,
			FXRYBaseinfo entity) {
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
	public Page<FXRYBaseinfo> findPaged(Page<FXRYBaseinfo> page, String hql,
			Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYBaseinfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<FXRYBaseinfo> findPaged(Page<FXRYBaseinfo> page) {
		return dao.findByCriteria(page);
	}

	public FXRYBaseinfo getByCode(String code) {
		FXRYBaseinfo entity = dao.findOneByProperty("code", code);
		return getAssociateInfo(entity);
	}

	private FXRYBaseinfo getAssociateInfo(FXRYBaseinfo entity) {
		entity.setReport(reportService.getByFXRYId(entity.getId()));
		if (!StringUtils.isBlank(entity.getHomeAddressId())) {
			try {
				entity.setHomeAddress(addressService.get(entity
						.getHomeAddressId()));
			} catch (Exception e) {
			}
		}
		if (!StringUtils.isBlank(entity.getHouseAddressId())) {
			try {
				entity.setHouseAddress(addressService.get(entity
						.getHouseAddressId()));
			} catch (Exception e) {
			}
		}
		return entity;
	}

	public String getCode(String prefix) {
		int value = 0;
		try {
			String result = (String) dao.findOne(
					"select max(code) from FXRYBaseinfo where code like ?",
					prefix + '%');
			String suffix = result.substring(prefix.length());
			value = Integer.parseInt(suffix);
		} catch (Exception e) {
			// logger.warn("生成获取服刑人员编号出错", e);
		}
		++value;
		return prefix + String.format("%04d", value);
	}

	public FXRYBaseinfo changeStatus(String id, String newState, String orgId,
			FXRYStateChangeType type) throws FXRYStateChangeException {
		try {
			FXRYBaseinfo info = this.get(id);
			if (type == FXRYStateChangeType.SAME_ORG
					&& !orgId.equals(info.getResponseOrg())) {
				throw new FXRYStateChangeException("服刑人员矫正单位与登录人员单位不一致,无操作权限");
			}
			String oldState = info.getState();
			if (FXRYState.ZC.equals(newState)) {
				// 转出
				if (FXRYState.JJ.equals(oldState)
						|| FXRYState.TG.equals(oldState)) {
					throw new FXRYStateChangeException("已脱管或解矫的服刑人员不可转出！");
				}
			} else if (FXRYState.TG.equals(newState)) {
				if (FXRYState.JJ.equals(oldState)) {
					throw new FXRYStateChangeException("已解矫的服刑人员不可脱管！");
				}
			} else if (FXRYState.JJ.equals(newState)) {
			} else if (FXRYState.ZJ.equals(newState)) {
			}
			if (type == FXRYStateChangeType.CHANGE_ORG) {
				info.setResponseOrg(orgId);
			}
			info.setState(newState);
			dao.save(info);
			return info;
		} catch (FXRYStateChangeException e) {
			throw e;
		} catch (Exception e) {
			throw new FXRYStateChangeException("登录人员无操作权限或服刑人员数据不完整");
		}
	}
	
	public FXRYBaseinfo changeStatus(String id, String newState, String orgId,
			FXRYStateChangeType type ,String zfdc) throws FXRYStateChangeException {
		try {
			FXRYBaseinfo info = this.get(id);
			String oldState = info.getState();
			if (FXRYState.ZC.equals(newState)) {
				// 转出
				if (FXRYState.JJ.equals(oldState)
						|| FXRYState.TG.equals(oldState)) {
					throw new FXRYStateChangeException("已脱管或解矫的服刑人员不可转出！");
				}
			} else if (FXRYState.TG.equals(newState)) {
				if (FXRYState.JJ.equals(oldState)) {
					throw new FXRYStateChangeException("已解矫的服刑人员不可脱管！");
				}
			} else if (FXRYState.JJ.equals(newState)) {
			} else if (FXRYState.ZJ.equals(newState)) {
			}
			if (type == FXRYStateChangeType.CHANGE_ORG) {
				info.setResponseOrg(orgId);
			}
			info.setState(newState);
			dao.save(info);
			return info;
		} catch (FXRYStateChangeException e) {
			throw e;
		} catch (Exception e) {
			throw new FXRYStateChangeException("登录人员无操作权限或服刑人员数据不完整");
		}
	}

	/**
	 * Description:司法所间转移人员时转移其设备记录
	 * 
	 * @author Shuzz
	 * @param fxryId
	 * @since 2015年4月7日上午11:26:16
	 */
	public void changeEquip(String fxryId, String orgId) {
		FXRYBaseinfo info = this.get(fxryId);
		if(!CommonUtils.isNull(info.getDeviceCode())){
			FXRYDevice device = deviceService.getByFXRYId(fxryId);
			device.setStatus(DeviceStatus.UNUSE);
			if (null == device.getUnbindStatus()) {
				device.setUnbindStatus("10");
			} else {
				device.setUnbindStatus("11");
			}
			device.setEndTime(new Date());
			deviceService.update(device);
			FXRYDevice nowdevice = new FXRYDevice();
			nowdevice.setDeviceId(device.getDeviceId());
			nowdevice.setFxryId(fxryId);
			nowdevice.setStartTime(new Date());
			nowdevice.setStatus(DeviceStatus.USING);
			nowdevice.setFxryOrgId(orgId);
			nowdevice.setUnbindStatus("01");
			deviceService.create(nowdevice);
		}
	}
	public FXRYBaseinfo getById(String id){
		return dao.get(id);
	}
    //更新
	public void save(FXRYBaseinfo entity){
		dao.save(entity);
	}
	public void updateFxryPic(FXRYBaseinfo entity) {
		dao.save(entity);
	}
}
