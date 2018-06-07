package com.nci.dcs.jzgl.dagl.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nci.dcs.base.service.BaseService;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.utils.PropertyFilter;
import com.nci.dcs.jzgl.dagl.dao.FXRYTransferInfoDao;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYTransferInfo;
import com.nci.dcs.jzgl.dagl.util.Constants.FXRYStateChangeType;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.dagl.util.FXRYStateChangeException;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;
import com.nci.dcs.jzgl.fingerprint.service.FingerPrintManagerService;
@Service
@Transactional
public class FXRYTransferInfoService extends BaseService<FXRYTransferInfo, String>{
	
	@Autowired
	private FXRYTransferInfoDao dao;
	
	@Autowired
	FXRYBaseInfoService baseinfoService;
	
	@Autowired
	private FXRYReportOrgService reportService;
	
	@Autowired
	private FingerPrintManagerService fingerService;
	
	@Override
	public void create(FXRYTransferInfo entity) {
		dao.save(entity);
	}

	@Override
	public void update(FXRYTransferInfo entity) {
		dao.save(entity);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public FXRYTransferInfo get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<FXRYTransferInfo> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<FXRYTransferInfo> findPaged(Page<FXRYTransferInfo> page,
			FXRYTransferInfo entity) {
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
	public Page<FXRYTransferInfo> findPaged(Page<FXRYTransferInfo> page,
			String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyFilter> buildPropertyFilter(FXRYTransferInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public FXRYTransferInfo getByFXRYId(String fxryId){
		Criterion owner = Restrictions.eq("fxryId", fxryId);
		Criterion rtime = Restrictions.isNull("receiveTime");
		return dao.findOneByCriteria(owner, rtime);
	}
	
	public void deleteByFXRYId(String fxryId){
		FXRYTransferInfo entity = getByFXRYId(fxryId);
		if (entity != null){
			dao.delete(entity);
		}
	}
	/**
	 * Description:区县司法局接收
	 *
	 * @author Shuzz
	 * @param fxryId
	 * @param orgId
	 * @throws FXRYStateChangeException
	 * @since 2015年4月9日上午10:15:43
	 */
	public void sfjMoveIn(String fxryId, String orgId) throws FXRYStateChangeException{
		try{
			FXRYTransferInfo entity = getByFXRYId(fxryId);
			if (!entity.getReceiveOrgId().equals(orgId)){
				throw new FXRYStateChangeException("非指定的接收单位,无权限接收");
			}
			entity.setReceiveTime(new Date());
			dao.save(entity);
			baseinfoService.changeStatus(fxryId, FXRYState.ZJ, orgId, FXRYStateChangeType.CHANGE_ORG);
			
//			moveFingerPrint(entity);
		}
		catch (FXRYStateChangeException e){
			throw e;
		}
		catch(Exception e){
			throw new FXRYStateChangeException("找不出服刑人员的转出信息,请刷新待转入人员列表后再试");
		}
	}
	public void sfsMoveIn(String fxryId, String orgId) throws FXRYStateChangeException{
		try{
			FXRYTransferInfo entity = getByFXRYId(fxryId);
			if (!entity.getReceiveOrgId().equals(orgId)){
				throw new FXRYStateChangeException("非指定的接收单位,无权限接收");
			}
			entity.setReceiveTime(new Date());
			dao.save(entity);
			baseinfoService.changeStatus(fxryId, FXRYState.ZJ, orgId, FXRYStateChangeType.CHANGE_ORG);
			try{
				baseinfoService.changeEquip(entity.getFxryId(),orgId);
			}
			catch(Exception e){
				throw new FXRYStateChangeException("修改设备状态出错");
			}
//			moveFingerPrint(entity);
		}
		catch (FXRYStateChangeException e){
			throw e;
		}
		catch(Exception e){
			throw new FXRYStateChangeException("找不出服刑人员的转出信息,请刷新待转入人员列表后再试");
		}
	}
	
	/**
	 * 转移指纹信息到接收单位
	 */
	private void moveFingerPrint(FXRYTransferInfo entity){
		FXRYBaseinfo basicinfo = baseinfoService.get(entity.getFxryId());
		List<CcFingerprintMachine> recList = fingerService.queryMachine(entity.getReceiveOrgId());
		for(CcFingerprintMachine machine : recList){
			fingerService.addTask(5, basicinfo.getFingerPrintNo(), machine.getMachineId(), null);//上传
		}
		List<CcFingerprintMachine> outList = fingerService.queryMachine(entity.getOutOrgId());
		for(CcFingerprintMachine machine :outList){
			fingerService.addTask(4, basicinfo.getFingerPrintNo(), machine.getMachineId(), null);//删除
		}
	}
	
	public void cancelSfsMoveOut(String fxryId, String orgId) throws FXRYStateChangeException{
		baseinfoService.changeStatus(fxryId, FXRYState.ZJ, orgId, FXRYStateChangeType.SAME_ORG);
		try{
			FXRYTransferInfo entity = getByFXRYId(fxryId);
			dao.delete(entity);
		}
		catch(Exception e){
			
		}
	}
	/**
	 * Description:服刑人员离京
	 *
	 * @author Shuzz
	 * @param entity
	 * @throws FXRYStateChangeException
	 * @since 2014年11月26日下午2:25:55
	 */
	public void sfjMoveOutBJ(FXRYTransferInfo entity,String orgId,String deviceCode,String userId) throws FXRYStateChangeException{
		FXRYBaseinfo FxryBaseinfo=baseinfoService.get(entity.getFxryId());
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.LJ, null, FXRYStateChangeType.CHANGE_ORG);
		try{
			baseinfoService.unequip(deviceCode, userId, orgId,FxryBaseinfo);
		}
		catch(Exception e){
			throw new FXRYStateChangeException("解绑设备出错");
		}
		reportService.deleteByFXRYId(entity.getFxryId());//跨区县转出需要清空报到信息
		dao.save(entity);
	}
	public void sfjMoveOut(FXRYTransferInfo entity, String orgId,String deviceCode,String userId) throws FXRYStateChangeException{
		FXRYBaseinfo FxryBaseinfo=baseinfoService.get(entity.getFxryId());
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.ZC, orgId, FXRYStateChangeType.CHANGE_ORG);
		try{
			baseinfoService.unequip(deviceCode, userId, orgId,FxryBaseinfo);
		}
		catch(Exception e){
			throw new FXRYStateChangeException("解绑设备出错");
		}
		reportService.deleteByFXRYId(entity.getFxryId());//跨区县转出需要清空报到信息
		dao.save(entity);
	}
	
	public void sfsMoveOut(FXRYTransferInfo entity, String orgId) throws FXRYStateChangeException{
		baseinfoService.changeStatus(entity.getFxryId(), FXRYState.ZC, orgId, FXRYStateChangeType.SAME_ORG);
		try{
			dao.save(entity);
		}
		catch(Exception e){
			throw new FXRYStateChangeException("服务错误");
		}
	}
	
	public Page<FXRYTransferInfo> findPaged(Page<FXRYTransferInfo> page){
		return dao.findByCriteria(page);
	}
}
