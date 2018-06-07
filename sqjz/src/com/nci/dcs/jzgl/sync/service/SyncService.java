package com.nci.dcs.jzgl.sync.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.model.FXRYComplicityInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYDevice;
import com.nci.dcs.jzgl.dagl.model.FXRYEscortInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYExecuteInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYImprisonmentChange;
import com.nci.dcs.jzgl.dagl.model.FXRYMoreCrime;
import com.nci.dcs.jzgl.dagl.model.FXRYOutManageInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYRemoveAdjust;
import com.nci.dcs.jzgl.dagl.model.FXRYResumeInfo;
import com.nci.dcs.jzgl.dagl.model.FXRYSocialRelations;
import com.nci.dcs.jzgl.dagl.model.LegalInstrument;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYComplicityInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYDeviceService;
import com.nci.dcs.jzgl.dagl.service.FXRYEscortInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYExecuteInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYImprisonmentChangeService;
import com.nci.dcs.jzgl.dagl.service.FXRYMoreCrimeService;
import com.nci.dcs.jzgl.dagl.service.FXRYOutManageInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYRemoveAdjustService;
import com.nci.dcs.jzgl.dagl.service.FXRYResumeInfoService;
import com.nci.dcs.jzgl.dagl.service.FXRYSocialRelationsService;
import com.nci.dcs.jzgl.dagl.service.LegalInstrumentService;
import com.nci.dcs.jzgl.dagl.util.FXRYState;
import com.nci.dcs.jzgl.sync.handler.ISyncHandler;
import com.nci.dcs.jzgl.sync.utils.FormatUtil;
import com.nci.dcs.jzgl.sync.utils.SyncException;
import com.nci.dcs.jzgl.sync.utils.SyncHelper;
import com.nci.dcs.jzgl.sync.utils.XMLUtil;
import com.nci.dcs.jzgl.sync.xmlmodels.Result;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncBianDong;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncDelete;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncDelete.DeleteType;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncFalv;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncGuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianDing;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncJianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncObject;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncPerson;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncTuoGuan;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncXingfa;
import com.nci.dcs.jzgl.sync.xmlmodels.SyncYuZui;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Addr;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.BianDong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Falv;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GongTong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.GuanXi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianDing;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.JianLi;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.Person;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.TuoGuan;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.XSJJ;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.XingFa;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YaSong;
import com.nci.dcs.jzgl.sync.xmlmodels.pojo.YuZui;

/**
 * @author xxz
 *
 */
@Service
public class SyncService {
	private static Logger logger = Logger.getLogger(SyncService.class);
	
	private void checkResult(Result r, ISyncHandler h) throws SyncException{
		if (!r.isSuccess()){ 
			throw new SyncException(h.getName() + "失败:" + r.getDesc()); 
		}
	}
	
	
	/**
	 * @param fxryId
	 * @param deleteFirst 是否先从司法部删除人员所有信息，true删除
	 * @throws SyncException
	 */
	public void syncAll(String fxryId, boolean deleteFirst) throws SyncException{
		FXRYBaseinfo baseinfo = fxryService.get(fxryId);
		String state = baseinfo.getState();
		if (FXRYState.ZJ.equals(state) || FXRYState.JJ.equals(state)){
			ISyncHandler handler = null;
			Result r = null;
			//是否删除司法部信息
			if (deleteFirst){
				handler = SyncHelper.createDeleteHandler();
				r = syncDelete(DeleteType.ALL, fxryId, "", handler);
				checkResult(r, handler);
			}
			
			//人员基本信息
			handler = SyncHelper.createPersonHandler();
			r = syncPerson(fxryId, handler);
			checkResult(r, handler);
			
			//简历
			handler = SyncHelper.createJianLiHandler();
			r = syncJianLi(fxryId, handler);
			checkResult(r, handler);

			//社会关系
			handler = SyncHelper.createGuanXiHandler();
			r = syncGuanXi(fxryId, handler);
			checkResult(r, handler);
			
			//法律文书
			handler = SyncHelper.createFalvHandler();
			r = syncFalv(fxryId, handler);
			checkResult(r, handler);
			
			//刑罚执行信息
			handler = SyncHelper.createXingFaHandler();
			r = syncXingFa(fxryId, handler);
			checkResult(r, handler);
			
			if (FXRYState.JJ.equals(state)){
				//解矫
				handler = SyncHelper.createJianDingHandler();
				r = syncJianDing(fxryId, handler);
				checkResult(r, handler);
			}
		}
		else{
			throw new SyncException("当前人员状态不是在矫或解矫，不能上报");
		}
	}
	
	public SyncPerson syncPerson(String fxryId){
		SyncPerson syncPerson = new SyncPerson();
		syncPerson.setPerson(getPerson(fxryId));
		syncPerson.setJianli(getJianLi(fxryId));
		syncPerson.setGuanxi(getGuanXi(fxryId));
		return syncPerson;
	}
	//A、	人员基本信息同步
	public Result syncPerson(String fxryId, ISyncHandler handler) throws SyncException{
		SyncPerson syncPerson = syncPerson(fxryId);
		return (Result)doSync(syncPerson, handler, Result.class);
	}
	public SyncFalv syncFalv(String fxryId){
		SyncFalv sync = new SyncFalv();
		sync.setFalv(getFalv(fxryId));
		sync.setPersonId(fxryId);
		return sync;
	}
	//B、	法律文书信息同步
	public Result syncFalv(String fxryId, ISyncHandler handler) throws SyncException{
		SyncFalv sync =syncFalv(fxryId);
		return (Result)doSync(sync, handler, Result.class);
	}
	public SyncXingfa syncXingFa(String fxryId){
		SyncXingfa sync = new SyncXingfa();
		sync.setXingfa(getXingFa(fxryId));
		//---------20151116新增共同犯罪情况---------
		sync.setGongtong(getGongtong(fxryId));
		//---------20151116暂予监外执行罪犯押送情况---------
		sync.setYasong(getYaSong(fxryId));
		
		sync.setPersonId(fxryId);
		return sync;
	}
	//C、	刑罚信息同步
	public Result syncXingFa(String fxryId, ISyncHandler handler) throws SyncException{
		SyncXingfa sync = syncXingFa(fxryId);
		return (Result)doSync(sync, handler, Result.class);
	}
	public SyncJianDing syncJianDing(String fxryId) {
		SyncJianDing sync = new SyncJianDing();
		sync.setPersonId(fxryId);
		sync.setJianDing(getJianDing(fxryId));
		XSJJ x = new XSJJ();
		//TODO：是否同步到刑释解教系统，1：同步；0不同步
		x.setToXSJJ("0");
		//TODO:安置帮教地址 系统暂时没有
		sync.setXsjj(x);
		sync.setAddr(new Addr());
		return sync;
	}
	//D、	期满鉴定信息同步
	public Result syncJianDing(String fxryId, ISyncHandler handler) throws SyncException{
		SyncJianDing sync = syncJianDing(fxryId);
		return (Result)doSync(sync, handler, Result.class);
	}
	public SyncDelete syncDelete(DeleteType type, String fxryId, String itemId) {
		SyncDelete sync = new SyncDelete();
		sync.setPersonId(fxryId);
		sync.setListID(itemId);
		sync.setType(type);
		return sync;
	}
	//E、	信息删除
	/**
	 * @param DeleteType 删除类型
	 * @param fxryId 服刑人员ID
	 * @param itemId 子表ID，如简历ID，法律文书ID等
	 * @param handler
	 * @return
	 * @throws SyncException 
	 */
	public Result syncDelete(DeleteType type, String fxryId, String itemId, ISyncHandler handler) throws SyncException{
		SyncDelete sync =syncDelete(type, fxryId, itemId);
		return (Result)doSync(sync, handler, Result.class);
	}
	public SyncGuanXi syncGuanXi(String fxryId) {
		SyncGuanXi sync = new SyncGuanXi();
		sync.setPersonId(fxryId);
		sync.setGuanxi(getGuanXi(fxryId));
		return sync;
	}
	//G、家庭成员及主要社会关系
	public Result syncGuanXi(String fxryId, ISyncHandler handler) throws SyncException{
		SyncGuanXi sync = syncGuanXi(fxryId);
		return (Result)doSync(sync, handler, Result.class);
	}
	public SyncJianLi syncJianLi(String fxryId) {
		SyncJianLi sync = new SyncJianLi();
		sync.setPersonId(fxryId);
		sync.setJianli(getJianLi(fxryId));
		return sync;
	}
	//H、	个人简历
	public Result syncJianLi(String fxryId, ISyncHandler handler) throws SyncException{
		SyncJianLi sync = syncJianLi(fxryId);
		return (Result)doSync(sync, handler, Result.class);
	}
	
	@SuppressWarnings("rawtypes")
	private Object doSync(SyncObject model, ISyncHandler handler, Class resultType) throws SyncException{
		String xml = "";
		try {
			//此处可以读取同步帐号设置，设置统一的帐号上传
			//model.setUser(user);
			xml = XMLUtil.format(model);
			handler.sendRequest(XMLUtil.getHeader(handler.getEncoding()) + xml);
			handler.sync();
			xml = handler.readResponse();
			if (null != xml) {
				logger.warn(xml);
				return XMLUtil.parse(xml);
			}
			return null;
		} catch (IOException e) {
			throw new SyncException("xml序列化或解析失败");
		}
		catch (SyncException e){
			throw e;
		}
		catch (Exception e) {
			throw new SyncException("服务器返回非预期的结果:" + xml);
		}
		finally{
			handler.close(); 
		}
	}
	
	@Autowired
	private FXRYBaseInfoService fxryService;
	public Person getPerson(String fxryId){
		FXRYBaseinfo baseinfo = fxryService.get(fxryId);
		Person person = new Person();
		person.setID(baseinfo.getId());
		person.setPName(baseinfo.getName());
		person.setUsedName(baseinfo.getUserdName());
		person.setSex(baseinfo.getSex());
		person.setNationality(baseinfo.getNation());
		person.setIDNum(baseinfo.getIdentityCard());
		person.setVillage(baseinfo.getNatureHome());
		person.setTheSame(baseinfo.getIssameHome());
		if (baseinfo.getHomeAddress() != null){
			person.setNativepro(baseinfo.getHomeAddress().getProvince());
			person.setNativecity(baseinfo.getHomeAddress().getCity());
			person.setNativecou(baseinfo.getHomeAddress().getCounty());
			person.setNativevill(baseinfo.getHomeAddress().getTown());
			person.setNativenum(baseinfo.getHomeAddress().getDetail());
		}
		if (baseinfo.getHouseAddress() != null){
			person.setProID(baseinfo.getHouseAddress().getProvince());
			person.setCityID(baseinfo.getHouseAddress().getCity());
			person.setCouID(baseinfo.getHouseAddress().getCounty());
			person.setVillID(baseinfo.getHouseAddress().getTown());
			person.setNumAddr(baseinfo.getHouseAddress().getDetail());
		}
		person.setTel(baseinfo.getPhoneNumber());
		person.setAge(FormatUtil.date(baseinfo.getBirthdate()));
		person.setAdult(baseinfo.getIsadult());
		person.setEducation(baseinfo.getEducationDegree());
		person.setZhengZhi(baseinfo.getPoliticsStatus());
		person.setZhengZhi_old(baseinfo.getPoliticsStatusOriginal());
		person.setHunYin(baseinfo.getMaritalState());
		person.setJianKang(baseinfo.getHealth());
		person.setJianKangContent(baseinfo.getHealthSpecific());
		person.setJiuYe(baseinfo.getWorkState());
		person.setDanWei(baseinfo.getWorkNuit());
		person.setDanWeiTel(baseinfo.getWorkNuitPhone());
		person.setHuZhao(FormatUtil.yesOrNo(baseinfo.getPassport()));
		person.setHuZhaoNo(baseinfo.getPassport());
		person.setHuiXiang(FormatUtil.yesOrNo(baseinfo.getHrPermit()));
		person.setHuiXiangNo(baseinfo.getHrPermit());
		person.setTaiBao(FormatUtil.yesOrNo(baseinfo.getTbIdentity()));
		person.setTaiBaoNo(baseinfo.getTbIdentity());
		person.setGangAo(FormatUtil.yesOrNo(baseinfo.getGatPermit()));
		person.setGangAoNo(baseinfo.getGatPermit());
		person.setChuanRan(FormatUtil.yesOrNo(baseinfo.getHealthContagion()));
		person.setChuanRanContent(baseinfo.getHealthContagion());
		person.setXinLi(baseinfo.getHealthMental());
		person.setJianDingJiGou(baseinfo.getAccreditingOrgan());
		person.setXinLiContent(baseinfo.getHealthMentalSpecific());
		return person;
	}
	
	@Autowired
	protected FXRYResumeInfoService resumeService;
	public List<JianLi> getJianLi(String fxryId){
		List<FXRYResumeInfo> resumes = resumeService.getByFXRYId(fxryId);
		List<JianLi> jianlis = new ArrayList<JianLi>();
		int i=1;
		if (resumes != null && resumes.size() > 0){
			for (FXRYResumeInfo resume : resumes){
				JianLi jianli = new JianLi();
				jianli.setListID(Integer.toString(i++));
				jianli.setStartDate(FormatUtil.date(resume.getStartTime()));
				String endTime ="至今";
				if(null!=resume.getEndTime()){
					endTime=FormatUtil.date(resume.getEndTime());
				}
				jianli.setEndDate(endTime);
				jianli.setDanWei(resume.getWorkUnit());
				jianli.setZhiWu(resume.getJob());
				jianlis.add(jianli);
			}
		}
		return jianlis;
	}
	
	@Autowired
	protected FXRYSocialRelationsService relationsService;
	public List<GuanXi> getGuanXi(String fxryId){
		List<FXRYSocialRelations> relations = relationsService.getByFXRYId(fxryId);
		List<GuanXi> guanxis = new ArrayList<GuanXi>();
		int i=1;
		if (relations != null && relations.size() > 0){
			for (FXRYSocialRelations relation : relations){
				GuanXi guanxi = new GuanXi();
				//ListID必须为非0整数
				guanxi.setListID(Integer.toString(i++));
				guanxi.setXingMing(relation.getName());
				guanxi.setGuanXi(relation.getRelation());
				guanxi.setDanWei(relation.getAddress());
				guanxi.setTel(relation.getPhoneNumber());
				guanxis.add(guanxi);
			}
		}
		return guanxis;
	}
	
	@Autowired
	protected LegalInstrumentService legalService;
	public Falv getFalv(String fxryId){
		LegalInstrument legal = legalService.getByFXRYId(fxryId);
		Falv falv = new Falv();
		falv.setZhenCha(legal.getInvestigateUnit());
		falv.setJuLiuDate(FormatUtil.date(legal.getDateDetention()));
		falv.setLiAnDate(FormatUtil.date(legal.getDateRecord()));
		falv.setDaiBu(legal.getPermitArrestUnit());
		falv.setDaiBuDate(FormatUtil.date(legal.getDateArrest()));
		falv.setZhenJieDate(FormatUtil.date(legal.getDateEndInvestigate()));
		falv.setJianCha(legal.getPublicProsecution());
		falv.setQiSuDate(FormatUtil.date(legal.getDateIndictment()));
		falv.setGongSuShu(legal.getIndictmentNumber());
		falv.setShenPan(legal.getTrialUnit());
		falv.setPanJueShu(legal.getJudgmentNumber());
		falv.setPanJueDate(FormatUtil.date(legal.getDateJudgment()));
		falv.setTongZhiShu(legal.getInformNumber() + FormatUtil.date(legal.getDateInform()));
		falv.setJiYa(legal.getOrgdetentionAddress());
		falv.setJiaoFu(FormatUtil.date(legal.getDateExecute()));
		falv.setPanJue(legal.getDecideUnit());
		falv.setWenShuType(legal.getWritType());
		falv.setWenShu(legal.getWritNumber());
		falv.setWenShuDate(FormatUtil.date(legal.getWritEffectiveDate()));
		falv.setFanZuiShiShi(legal.getMajorCrime());
		falv.setXingZhengChuFa(legal.getAdministrativePenalty());
		falv.setXingShiChufa(legal.getCriminalPunshment());
		return falv;
	}
	
	@Autowired
	protected FXRYExecuteInfoService executeService;
	@Autowired
	private FXRYDeviceService fxryDeviceService;
	
	public XingFa getXingFa(String fxryId){
		FXRYExecuteInfo info = executeService.getByFXRYId(fxryId);
		XingFa xf = new XingFa();
		xf.setJzType(info.getAdjustType());
		xf.setFanZuiLeiXing(info.getCrimeType());
		xf.setZuiMing(FormatUtil.mutipleDictForZuiMing(info.getAccusation()));
		xf.setXingFa(info.getOriPunishment());
		xf.setXingQi(info.getOriPeriod());
		xf.setQiXian(info.getImprisonmentPeriod());
		xf.setAddition(FormatUtil.mutipleDict(info.getAddpunishment()));
		xf.setXingQi_S(FormatUtil.date(info.getDateSOri()));
		xf.setXingQi_E(FormatUtil.date(info.getDateEOri()));
		xf.setBoQuanQiXian(info.getNonpoliticalPeriod());
		xf.setGongAnName(info.getReceiveUnit());
		xf.setBoQuan_S(FormatUtil.date(info.getDateSNonpolitical()));
		xf.setBoQuan_E(FormatUtil.date(info.getDateENonpolitical()));
		xf.setJieShouRen(info.getReceivePerson());
		xf.setYiJiaoDate(FormatUtil.date(info.getDateTransfer()));
		xf.setSiShi(FormatUtil.mutipleDict(info.getSisType()));
		xf.setLeiFan(info.getIsRecidivism());
		xf.setSanShe(FormatUtil.mutipleDict(info.getSansType()));
		xf.setGongTongFanZui(info.getIsalone());
		xf.setJinZhiLing(info.getIsforbidden());
		xf.setJinZhiLingDate_S(FormatUtil.date(info.getDateSForbidden()));
		xf.setJinZhiLingDate_E(FormatUtil.date(info.getDateEForbidden()));
		xf.setJinZhiLingContent(info.getForbidden());
		xf.setJzQiXian(info.getAdjustPeriod());
		xf.setJzDate_S(FormatUtil.date(info.getDateSAdjust()));
		xf.setJzDate_E(FormatUtil.date(info.getDateEAdjust()));
		//xf.setFaLvWenShu(info);法律文书
		xf.setBaoDaoQingKuang(info.getReportInfo());
		xf.setJieShouFangShi(info.getReceiveType());
		xf.setBaoDaoDate(FormatUtil.date(info.getDateReceive()));
		xf.setRemark(info.getRemark());
		xf.setIfGroup(FormatUtil.yesOrNo(info.getGroupInfo()));
		xf.setGrouppeo(FormatUtil.mutipleDict(info.getGroupInfo()));
		//查询是否曾经佩戴电子监管设备
		List<FXRYDevice> fxryDevices=fxryDeviceService.getAllByFxryId(fxryId);
		String xinxihua="";
		if(CommonUtils.isNull(fxryDevices)){
			xinxihua=FormatUtil.yesOrNo("");	
		}else{
			xinxihua=FormatUtil.yesOrNo("yes");
		}
		xf.setXinxihua(xinxihua);
		xf.setShouji(xinxihua);
		return xf;
	}
	
	@Autowired
	protected FXRYRemoveAdjustService removeService;
	public JianDing getJianDing(String fxryId){
		FXRYRemoveAdjust rmAdjust = removeService.getByFXRYId(fxryId);
		JianDing jd = new JianDing();
		jd.setTheDate(FormatUtil.date(rmAdjust.getRemoveDate()));
		jd.setReason(rmAdjust.getRemoveReason());
		jd.setShouJianReason(rmAdjust.getJailReason());
		jd.setShouJianType(rmAdjust.getJailType());
		jd.setShouJianDate(FormatUtil.date(rmAdjust.getJailDate()));
		jd.setDieDate(FormatUtil.date(rmAdjust.getDeathDate()));
		jd.setDieReason(rmAdjust.getDeathReason());
		jd.setJuTiSiYin(rmAdjust.getDeathReasonDetail());
		jd.setBiaoXian(rmAdjust.getPerformance());
		jd.setRenZui(rmAdjust.getManner());
		jd.setJiNeng(rmAdjust.getIstrained());
		jd.setZhengShu(rmAdjust.getHascertificate());
		jd.setTeChang(rmAdjust.getSpeciality());
		jd.setSanWu(rmAdjust.getIssanwu());
		jd.setPingGu(rmAdjust.getRisk());
		jd.setLianXi(rmAdjust.getFamilyContact());
		jd.setYiJian(rmAdjust.getRemark());
		jd.setRemark("");
		return jd;
	}
	@Autowired
	private FXRYMoreCrimeService moreCrimeService;
	/**
	 * Description:生成余罪或再罪有关情况同步信息
	 * @author Shuzz
	 * @since 2015年11月11日下午4:03:44
	 * @param fxryId
	 * @return
	 */
	public SyncYuZui syncYuZui(String fxryId) {
		SyncYuZui sync = new SyncYuZui();
		sync.setPersonId(fxryId);
		List<YuZui> yuzuis=getYuZui(fxryId);
		if(CommonUtils.isNotNull(yuzuis)){
			sync.setYuzui(yuzuis);
		}else{
			sync=null;
		}
		return sync;
	}
	/**
	 * Description:组织余罪或再罪有关数据
	 * @author Shuzz
	 * @since 2015年11月11日下午4:05:15
	 * @param fxryId
	 * @return
	 */
	public List<YuZui> getYuZui(String fxryId) {
		List<YuZui> yuzuis = new ArrayList<YuZui>();
		List<FXRYMoreCrime> moreCrimes = moreCrimeService.getByFXRYId(fxryId);
		int i = 1;
		if (moreCrimes != null && moreCrimes.size() > 0) {
			for (FXRYMoreCrime moreCrime : moreCrimes) {
				YuZui yuZui = new YuZui();
				yuZui.setListID(Integer.toString(i++));
				yuZui.setSuoSheZuiMing(moreCrime.getCrimes());
				yuZui.setZhenCha(moreCrime.getInvestigateUnit());
				yuZui.setTheDate(FormatUtil.date(moreCrime.getTime()));
				yuZui.setShenPan(moreCrime.getJudgeUnit());
				yuZui.setZuiMing(moreCrime.getAccusation());
				yuZui.setXingQi(moreCrime.getPrisonTerm());
				yuzuis.add(yuZui);
			}
		}
		return yuzuis;
	}
	
	@Autowired
	private FXRYImprisonmentChangeService imprisonmentChangeService;
	/**
	 * Description:生成刑期变动情况同步信息
	 * @author Shuzz
	 * @since 2015年11月11日下午4:07:59
	 * @param fxryId
	 * @return
	 */
	public SyncBianDong syncBianDong(String fxryId) {
		SyncBianDong sync = new SyncBianDong();
		sync.setPersonId(fxryId);
		List<BianDong> bianDongs=getBiandong(fxryId);
		if(CommonUtils.isNotNull(bianDongs)){
			sync.setBiandong(bianDongs);
		}else{
			sync=null;
		}
		return sync;
	}
	/**
	 * Description:组织刑期变动有关数据
	 * @author Shuzz
	 * @since 2015年11月11日下午4:08:20
	 * @param fxryId
	 * @return
	 */
	public List<BianDong> getBiandong(String fxryId) {
		List<BianDong> bianDongs = new ArrayList<BianDong>();
		List<FXRYImprisonmentChange> imprisonmentChanges = imprisonmentChangeService
				.getByFXRYId(fxryId);
		int i = 1;
		if (imprisonmentChanges != null && imprisonmentChanges.size() > 0) {
			for (FXRYImprisonmentChange change : imprisonmentChanges) {
				BianDong bianDong = new BianDong();
				bianDong.setListID(Integer.toString(i++));
				bianDong.setType(change.getChangeType());
				bianDong.setTheDate(FormatUtil.date(change.getChangeDate()));
				bianDong.setFuDu(change.getRange());
				bianDong.setReason(change.getReason());
				bianDongs.add(bianDong);
			}
		}
		return bianDongs;
	}
	/**
	 * Description:生成矫正脱管同步信息
	 * @author Shuzz
	 * @since 2015年11月11日下午4:09:57
	 * @param fxryId
	 * @return
	 */
	public SyncTuoGuan syncTuoGuan(String fxryId) {
		SyncTuoGuan sync = new SyncTuoGuan();
		sync.setPersonId(fxryId);
		TuoGuan tuoGuan=getTuoGuan(fxryId);
		if(null!=tuoGuan){
			sync.setTuoguan(tuoGuan);
		}else {
			sync=null;
		}
		return sync;
	}
	@Autowired
	private FXRYOutManageInfoService outManageInfoService;
	/**
	 * Description:组织矫正脱管
	 * @author Shuzz
	 * @since 2015年11月11日下午4:08:20
	 * @param fxryId
	 * @return
	 */
	public TuoGuan getTuoGuan(String fxryId) {
		FXRYOutManageInfo outManageInfo = outManageInfoService
				.getByFXRYId(fxryId);
		TuoGuan tuoGuan = null;
		if (null != outManageInfo) {
			tuoGuan = new TuoGuan();
			tuoGuan.setTheDate(FormatUtil.date(outManageInfo.getStartDate()));
			tuoGuan.setReason(outManageInfo.getReason());
			tuoGuan.setRemark(outManageInfo.getDescription());
		}
		return tuoGuan;
	}

	@Autowired
	private FXRYEscortInfoService fxryEscortInfoService;

	/**
	 * Description:组织暂予监外执行罪犯押送情况数据
	 * @author Shuzz
	 * @since 2015年11月17日上午11:18:28
	 * @param fxryId
	 * @return
	 */
	private List<YaSong> getYaSong(String fxryId) {
		List<YaSong> yaSongs = new ArrayList<YaSong>();
		List<FXRYEscortInfo> escortInfos = fxryEscortInfoService
				.getByFXRYId(fxryId);
		int i = 1;
		if (CommonUtils.isNotNull(escortInfos)) {
			for (FXRYEscortInfo escortInfo : escortInfos) {
				YaSong yaSong = new YaSong();
				yaSong.setListID(Integer.toString(i++));
				yaSong.setYaSongDate(FormatUtil.date(escortInfo.getEscortDate()));
				yaSong.setPoliceName(escortInfo.getPoliceName());
				//单位及职务
				yaSong.setDanWei(escortInfo.getPoliceOffice() + " "
						+ escortInfo.getJob());
				yaSongs.add(yaSong);
			}
		}
		return yaSongs;
	}
	@Autowired
	private FXRYComplicityInfoService complicityInfoService;

	/**
	 * Description:组织共同犯罪情况数据
	 * @author Shuzz
	 * @since 2015年11月17日上午11:28:49
	 * @param fxryId
	 * @return
	 */
	private List<GongTong> getGongtong(String fxryId) {
		List<GongTong> gongTongs = new ArrayList<GongTong>();
		List<FXRYComplicityInfo> complicityInfos = complicityInfoService
				.getByFXRYId(fxryId);
		int i = 1;
		if (CommonUtils.isNotNull(complicityInfos)) {
			for (FXRYComplicityInfo complicityInfo : complicityInfos) {
				GongTong gongTong = new GongTong();
				gongTong.setListID(Integer.toString(i++));
				gongTong.setXingMing(complicityInfo.getName());
				gongTong.setSex(complicityInfo.getSex());
				gongTong.setAge(FormatUtil.date(complicityInfo.getBirthday()));
				gongTong.setZuiMing(complicityInfo.getAccusation());
				gongTong.setXingFa(complicityInfo.getPunishmentAddress());
				gongTongs.add(gongTong);
			}
		}
		return gongTongs;
	}
}
