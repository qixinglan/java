package com.nci.dcs.jzgl.sync.service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYRemoveAdjustService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.sync.model.FxrySyncData;
import com.nci.dcs.jzgl.sync.model.FxrySyncInfo;
import com.nci.dcs.jzgl.sync.utils.XMLUtil;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncBianDong;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncFalv;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncGuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianDing;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncObject;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncPerson;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncTuoGuan;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncXingfa;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncYuZui;
import com.nci.sfj.common.util.Charsets;

@Service
public class SyncDataJobService {

	@Autowired
	private FXRYBaseInfoService fxryService;
	@Autowired
	protected FXRYRemoveAdjustService removeService;
	@Autowired
	private FxrySyncinfoService fxrySyncinfoService;
	@Autowired
	private FxrySyncDataService fxrySyncDataService;
	@Autowired
	private SyncService syncService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Description:
	 * 
	 * @author Shuzz
	 * @since 2014年12月16日下午4:05:41
	 * 
	 */
	public enum Type {
		JIBENG("JiBeng"), // 基本信息
		JIANLI("JianLi"), // 简历
		GUANXI("GuanXi"), // 社会关系
		FALV("Falv"), // 法律文书
		XINGFA("XingFa"), // 刑罚执行信息
		JIANDING("JianDing"), // 鉴定 or 解矫

		YUZUI("YuZui"), // 余罪或再罪有关情况
		BIANDONG("BianDong"), // 刑期变动情况
		TUOGUAN("TuoGuan");// 矫正脱管

		private String value;

		private Type(String v) {
			this.value = v;
		}

		public String getValue() {
			return this.value;
		}
	}

	/**
	 * Description:按类型删除服刑人员的加密后数据
	 * 
	 * @author Shuzz
	 * @since 2014年12月16日下午4:05:37
	 * @param type
	 * @param fxryId
	 */
	private void deleteData(String type, String fxryId) {
		List<FxrySyncData> dataList = fxrySyncDataService.findFxryDataByType(
				type, fxryId);
		if (CommonUtils.isNotNull(dataList)) {
			for (FxrySyncData data : dataList) {
				fxrySyncDataService.delete(data);
			}
		}
	}

	/**
	 * Description:按类型保存服刑人员的加密后数据
	 * 
	 * @author Shuzz
	 * @since 2014年12月16日下午4:05:36
	 * @param type 数据类型
	 * @param fxryId 服刑人员ID
	 * @param sync 同步数据实体
	 */
	private void createData(String type, String fxryId, SyncObject sync) {
		List<FxrySyncData> dataList = fxrySyncDataService.findFxryDataByType(
				type, fxryId);
		FxrySyncData data = null;
		Date dateTime = new Date();
		//数据状态：1、新增；2、更新；3、以同步至前置机
		if (CommonUtils.isNotNull(dataList)) {
			data = dataList.get(0);
			data.setDataState("2");
		} else {
			data = new FxrySyncData();
			data.setFxryId(fxryId);
			data.setDataType(type);
			data.setDataTime(dateTime);
			data.setDataState("1");
		}
		if (dateTime.compareTo(data.getDataTime()) >= 0) {
			try {
				//简单的压缩加密
				String xml = XMLUtil.format(sync);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);
				gzip.write(xml.getBytes(Charsets.UTF8));
				gzip.close();
				BASE64Encoder encoder = new BASE64Encoder();
				byte[] xmlDate = encoder.encode(out.toByteArray()).getBytes(
						Charsets.UTF8);
				data.setXmlData(xmlDate);
				fxrySyncDataService.create(data);
			} catch (Exception e) {
				logger.error("生成" + fxryId + "的" + type + "型同步数据发生异常", e);
			}
		}
	}

	/**
	 * Description:按同步接口生成某个服刑人员的数据
	 * 
	 * @author Shuzz
	 * @since 2014年12月16日下午4:05:32
	 * @param fxryId
	 */
	private void createData(String fxryId) {
		FXRYBaseinfo baseinfo = fxryService.get(fxryId);
		String state = baseinfo.getState();
		// 人员基本信息
		try {
			SyncPerson syncPerson = syncService.syncPerson(fxryId);
			createData(Type.JIBENG.getValue(), fxryId, syncPerson);
		} catch (Exception e) {
		}
		//人员基本信息已经带有简历和社会关系不再生成
		/*try {
			// 简历
			SyncJianLi syncJianLi = syncService.syncJianLi(fxryId);
			createData(Type.JIANLI.getValue(), fxryId, syncJianLi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 社会关系
			SyncGuanXi syncGuanXi = syncService.syncGuanXi(fxryId);
			createData(Type.GUANXI.getValue(), fxryId, syncGuanXi);
		} catch (Exception e) {
		}*/
		try {
			// 法律文书
			SyncFalv syncFalv = syncService.syncFalv(fxryId);
			createData(Type.FALV.getValue(), fxryId, syncFalv);
		} catch (Exception e) {
		}
		// 刑罚执行信息
		try {
			SyncXingfa syncXingfa = syncService.syncXingFa(fxryId);
			createData(Type.XINGFA.getValue(), fxryId, syncXingfa);
		} catch (Exception e) {
		}
		// 脱管信息
		if (FXRYState.TG.equals(state)) {
			try {
				SyncTuoGuan syncTuoGuan = syncService.syncTuoGuan(fxryId);
				if (null != syncTuoGuan) {
					createData(Type.TUOGUAN.getValue(), fxryId, syncTuoGuan);
				}
			} catch (Exception e) {
			}
		}

		if (FXRYState.JJ.equals(state)) {
			// 解矫
			try {
				FXRYRemoveAdjust rmAdjust = removeService.getByFXRYId(fxryId);
				if (rmAdjust != null) {
					//对于解除矫正不再判断收监的
					/*if (null == rmAdjust.getJailReason()
							|| (!"3,8,9,10".contains(rmAdjust.getJailReason()) || "1"
									.equals(rmAdjust.getJailReason()))) {
						SyncJianDing syncJianDing = syncService
								.syncJianDing(fxryId);
						createData(Type.JIANDING.getValue(), fxryId,
								syncJianDing);
					}*/
					SyncJianDing syncJianDing = syncService
							.syncJianDing(fxryId);
					createData(Type.JIANDING.getValue(), fxryId, syncJianDing);
					// ---------新增余罪或再罪有关情况---------
					SyncYuZui syncYuZui = syncService.syncYuZui(fxryId);
					if (null != syncYuZui) {
						createData(Type.YUZUI.getValue(), fxryId, syncYuZui);
					}
					// ---------新增刑期变动情况---------
					SyncBianDong syncBianDong = syncService
							.syncBianDong(fxryId);
					if (null != syncBianDong) {
						createData(Type.BIANDONG.getValue(), fxryId,
								syncBianDong);
					}
				} else {
					deleteData(Type.JIANDING.getValue(), fxryId);
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Description:生成同步需要同步的服刑人员的所有数据
	 * 
	 * @author Shuzz
	 * @since 2014年12月16日下午4:05:29
	 */
	public void createSyncData() {
		int totalCount = fxrySyncinfoService.countCriteriaResult(Restrictions
				.isNull("syncDataTime"));
		//分配查询需要同步的服刑人员
		while (totalCount > 0) {
			List<FxrySyncInfo> list = fxrySyncinfoService
					.findPagedByCriteria(0, 50, Order.asc("id"),
							Restrictions.isNull("syncDataTime"));
			for (FxrySyncInfo sync : list) {
				try {
					createData(sync.getFxryId());
					sync.setSyncDataTime(new Date());
					fxrySyncinfoService.create(sync);
				} catch (Exception e) {
					logger.error(null, e);
				}
			}
			if (CommonUtils.isNotNull(list)) {
				totalCount = fxrySyncinfoService
						.countCriteriaResult(Restrictions
								.isNull("syncDataTime"));
			}
		}
	}

	/**
	 * Description:在生成同步数据前将昨天的同步结果传到应用的数据库
	 * 
	 */
	public void beforeCreateSyncDate() {
		fxrySyncDataService.callProcedure("CC_FXRY_BEFORE_SYNC");
	}

	/**
	 * Description:将需要同步的人员信息以及其同步数据同步至司法部同步前置机数据库
	 * 
	 */
	public void afterCreateSyncDate() {
		fxrySyncDataService.callProcedure("CC_FXRY_SYNC");
	}
}
