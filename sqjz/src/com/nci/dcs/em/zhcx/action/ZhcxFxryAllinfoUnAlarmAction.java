package com.nci.dcs.em.zhcx.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.zhcx.model.ViewCcFxryAllinfoUnAlarm;
import com.nci.dcs.em.zhcx.service.ZhcxFxryAllinfoUnAlarmService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

/**
 * Description:综合查询action
 * 
 * @author 
 * @since 
 */
public class ZhcxFxryAllinfoUnAlarmAction  extends BaseAction<ViewCcFxryAllinfoUnAlarm>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 649169781010898645L;
	
	@Autowired
	private DictionaryInfoService dictionaryInfoService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private String fileName;
	private InputStream targetFile;
	private Map<String,Object> zlmap;

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private OrganizationInfoService orgService;
			
	@Autowired
	private ZhcxFxryAllinfoUnAlarmService unalarmservice;
	
	@Autowired
	private DictionaryInfoService dictionaryService;
	/**
	 * 获取Service
	 * @return
	 */
	public ZhcxFxryAllinfoUnAlarmService getService() {
		return this.unalarmservice;
	}
	
	public DictionaryInfoService getDictionaryService() {
		return dictionaryService;
	}
	/**
	 * 设置Service
	 * @return
	 */
	public void setService(ZhcxFxryAllinfoUnAlarmService service) {
		this.unalarmservice = service;
	}

	public void setDictionaryService(DictionaryInfoService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	@Override
	public String list() throws Throwable {
		return "success";
	}

	@Override
	public String view() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String unAlarmSearch(){
		try{
			String orgId = request.getParameter("orgId");
			String org = null;
			this.getRequestPage();
			String filters = this.getJqgrid().getFilters();
			if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
				String[] tempFilters = filters.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				org = datas[1].substring(0, datas[1].indexOf("\""));
				OrganizationInfo orgInfo = orgService.get(org);
				List asd = page.getCriterions();
				if(orgInfo!=null&&(orgInfo.getSupOrg()!=null&&orgInfo.getSupOrg().getOrgId().equals("1"))){
					asd.remove(0);
					page.getCriterions().add(Restrictions.in("responseOrg", orgService.getChildrenIds(org)));
				}
			}
			if(orgId != null&& !orgId.equals("1")){
				page.getCriterions().add(Restrictions.in("responseOrg", orgService.getChildrenIds(orgId)));
			}
			//判断当前登录用户是否具有查询特管人员权限(移至前台)
//			User user =getUser();
//			if(!"1".equals(user.getIsws())&&!"3".equals(user.getIsws())){
//				page.getCriterions().add(Restrictions.ne("istgry", "1"));
//			}
			unalarmservice.findPaged(page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}

	public InputStream getTargetFile() {
		CreateFileUtil util = CreateFileUtil.getInstance();
		try {
			return util.getFileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @name 统计图数据
	 * @return
	 * @throws Throwable
	 * @date 2016年8月31日 上午8:58:41
	 * @message:
	 */
	public String searchTongjiByFiltersUnAlarm() throws Throwable {
		Bmb org = getCurOrg();
		String orgId = org.getBm();
		String filters = request.getParameter("filters");
		
		JQGridSearchRuleParser parser = new JQGridSearchRuleParser(filters);
		List<Criterion> criterions = parser.parse(ViewCcFxryAllinfoUnAlarm.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.alias(Projections.rowCount(),"code"));
		Map<String, String> orgMap = new HashMap<String, String>();
		if (!org.isSfs()) {
			List<OrganizationInfo> orgs = organizationInfoService.findSfsBySup(orgId);
			for (OrganizationInfo orgInfo : orgs) {
				orgMap.put(orgInfo.getOrgId(),orgInfo.getCname()==null?"不详":orgInfo.getCname());
			}
		} else {
			orgMap.put(orgId, org.getMc()==null?"不详":org.getMc());
		}
		if(org.isSj()){
			projectionList.add(Projections.groupProperty("supOrgId"));
			listRef = new ArrayList();
			List<Criterion> criterions2 = parser.parse(ViewCcFxryAllinfoUnAlarm.class);
			
			OrganizationInfo orgInfo = new OrganizationInfo();
			if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
				String[] tempFilters = filters.split("\"field\":\"responseOrg\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				String org1 = datas[1].substring(0, datas[1].indexOf("\""));
				orgInfo = orgService.get(org1);
				List asd = page.getCriterions();
			}else{
				orgInfo.setOrgId(orgId);
				orgInfo.setOrgType("0");
			}
			if(orgInfo.getOrgType().equals("2")||orgInfo.getOrgType().equals("3")){
				criterions.remove(0);
				if(orgInfo.getOrgType().equals("3")){
					criterions.remove(0);
				}
				criterions.add(Restrictions.or(Restrictions.eq("supOrgId",orgInfo.getOrgId()), Restrictions.eq("responseOrg",orgInfo.getOrgId())));
				Criteria criteria= unalarmservice.createCriteria(criterions,
						ViewCcFxryAllinfoUnAlarm.class, projectionList,null);
				List<Object> list1=criteria.list();
				int tjs = 0;
				//求和返回总数
				for(int i=0;i<list1.size();i++){
					Object[] ob = (Object[]) list1.get(i);
					tjs += Integer.parseInt(String.valueOf(ob[0]));
				}
				Object[] obj = {tjs,orgInfo.getCname()};
				listRef.add(obj);
				criterions= new ArrayList<Criterion>();
				criterions.addAll(criterions2);
			}else{
				for (String key : orgMap.keySet()) {
					criterions.add(Restrictions.or(Restrictions.eq("supOrgId",key), Restrictions.eq("responseOrg",key)));
					Criteria criteria= unalarmservice.createCriteria(criterions,
							ViewCcFxryAllinfoUnAlarm.class, projectionList,null);
					List<Object> list1=criteria.list();//获取区县下的人员数量和区县下的所有司法所人员数量
					int tjs = 0;
					//求和返回总数
					for(int i=0;i<list1.size();i++){
						Object[] ob = (Object[]) list1.get(i);
						tjs += Integer.parseInt(String.valueOf(ob[0]));
					}
					Object[] obj = {tjs,orgMap.get(key)};
					listRef.add(obj);
					criterions= new ArrayList<Criterion>();
					criterions.addAll(criterions2);
				}
			}
		}else{
			if(org.isSfs()){
				criterions.add(Restrictions.eq("responseOrg",orgId));
				projectionList.add(Projections.groupProperty("responseOrg"));
			}else{
				criterions.add(Restrictions.eq("supOrgId",orgId));
				projectionList.add(Projections.groupProperty("responseOrg"));
			}
			Criteria criteria= unalarmservice.createCriteria(criterions,
					ViewCcFxryAllinfoUnAlarm.class, projectionList,null);
			
			List<Object> list1=criteria.list();
			Map<String,String> dataMap = new HashMap<String,String>();
			for(int i=0;i<list1.size();i++){
				Object[] temp = (Object[]) list1.get(i);
				dataMap.put(String.valueOf(temp[1]), String.valueOf(temp[0]));
			}
			
			listRef = new ArrayList();
			for (String key : orgMap.keySet()) {
				Object[] obj = {dataMap.get(key)==null?"0":dataMap.get(key),orgMap.get(key)};
				listRef.add(obj);
			}
			
		}
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String searchTongjiUnAlarm() throws Throwable {
		Bmb org = getCurOrg();
		String orgId = org.getBm();
		this.getRequestPage();
		String filters = this.getJqgrid().getFilters();
		List<SearchRule> result = JQGridSearchRuleParser
				.getSearchRules(this.jqgrid.getFilters());// 获取所有searchRule
		
		List<Criterion> criterions = page.getCriterions();
		List<Criterion> criterions2 = new ArrayList<Criterion>();//用于获取区县下的人员数量和区县下的所有司法所人员数量
		
		criterions2.addAll(criterions);//复制查询条件，方便查询区县下的人员数量
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.alias(Projections.rowCount(),"code"));
		OrganizationInfo orgInfo = new OrganizationInfo();
		if (null != filters && filters.contains("\"field\":\"responseOrg\"")) {
			String[] tempFilters = filters.split("\"field\":\"responseOrg\"");
			String[] datas = tempFilters[1].split("\"data\":\"");
			String org1 = datas[1].substring(0, datas[1].indexOf("\""));
			orgInfo = orgService.get(org1);
			List asd = page.getCriterions();
		}else{
			orgInfo.setOrgId(orgId);
		}
		if(org.isSj()){
			if(result.size()==0||result.get(0).toString()=="1"){
				projectionList.add(Projections.groupProperty("supOrgId"));
				criterions.add(Restrictions.ne("supOrgId",orgId));
			}
			if(orgInfo.getOrgType()!=null){
				if(orgInfo.getOrgType().equals("2")){//在市局用户选择区县
					criterions.remove(0);
					projectionList.add(Projections.groupProperty("supOrgId"));
					criterions.add(Restrictions.eq("supOrgId",orgInfo.getOrgId()));
				}
				if(orgInfo.getOrgType().equals("3")){//在市局用户选择司法所
					criterions.remove(0);
					criterions.remove(0);
					projectionList.add(Projections.groupProperty("responseOrg"));
					criterions.add(Restrictions.eq("responseOrg",orgInfo.getOrgId()));
				}
			}else{
				projectionList.add(Projections.groupProperty("supOrgId"));
				criterions.add(Restrictions.ne("supOrgId",orgId));
			}
		}
		if(org.isQxsfj()){
			projectionList.add(Projections.groupProperty("responseOrg"));
			criterions.add(Restrictions.eq("supOrgId",orgId));
		}
		if(org.isSfs()){
			projectionList.add(Projections.groupProperty("responseOrg"));
			criterions.add(Restrictions.eq("responseOrg",orgId));
		}
		Criteria criteria= unalarmservice.createCriteria(criterions,
				ViewCcFxryAllinfoUnAlarm.class, projectionList,null);
		List<Object> list1=criteria.list();
		ProjectionList projectionList2 = Projections.projectionList();
		projectionList2.add(Projections.alias(Projections.rowCount(),"code"));
		projectionList2.add(Projections.groupProperty("responseOrg"));
		criterions2.add(Restrictions.eq("supOrgId",orgId));//查询区县下的人员数量
		Criteria criteria2= unalarmservice.createCriteria(criterions2,
				ViewCcFxryAllinfoUnAlarm.class, projectionList2,null);
		List<Object> list2=criteria2.list();
		Map<String,String> dataMap = new HashMap<String,String>();//封装，分配到各区县统计数据
		for(int i=0;i<list2.size();i++){
			Object[] temp = (Object[]) list2.get(i);
			dataMap.put(String.valueOf(temp[1]), String.valueOf(temp[0]));
		}
		criteria.setFirstResult(page.getFirst());
		criteria.setMaxResults(page.getPageSize());
		
		List<ViewCcFxryAllinfoUnAlarm> listAll=criteria.list();
		
		List<ViewCcFxryAllinfoUnAlarm> fxryList = new ArrayList<ViewCcFxryAllinfoUnAlarm>();
		for (Object obj : listAll) {
			Object[] object = (Object[]) obj;
			ViewCcFxryAllinfoUnAlarm temp = new ViewCcFxryAllinfoUnAlarm();
			temp.setResponseOrg(object[1]==null?"":String.valueOf(object[1]));
			//求和区县下的人员数量和区县下的所有司法所人员数量
			if(org.isSj()){
				temp.setCode(String.valueOf( Integer.parseInt(String.valueOf(object[0])) + Integer.parseInt(dataMap.get(String.valueOf(object[1]))==null?"0":dataMap.get(String.valueOf(object[1])))));
			}else{
				temp.setCode(String.valueOf( Integer.parseInt(String.valueOf(object[0]))));
			}
			fxryList.add(temp);
		}
		ViewCcFxryAllinfoUnAlarm tempqb = new ViewCcFxryAllinfoUnAlarm();
		int qb = 0;
		for(int i=0;i<fxryList.size();i++){
			qb=qb+Integer.parseInt(fxryList.get(i).getCode());
		}
		tempqb.setCode(String.valueOf(qb));
		tempqb.setResponseOrg("qb");
		fxryList.add(tempqb);
		
		page.setTotalCount(list1.size());
		page.setResult(fxryList);
		
		return SUCCESS;
	}
	public String contrastDate() throws Throwable {
		String datevalue1 = request.getParameter("birthdate1");
		String datevalue2 = request.getParameter("birthdate2");
		Date birthdate1=null;
		Date birthdate2=null;
		String year1=null;
		String year2=null;
		if(datevalue1!=""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			birthdate1 = sdf.parse(datevalue1); 
			year1=toYear(birthdate1)==0?"":String.valueOf(toYear(birthdate1));
		}
		if(datevalue2!=""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			birthdate2 = sdf.parse(datevalue2); 
			year2=toYear(birthdate2)==0?"":String.valueOf(toYear(birthdate2));
		}
		zlmap = new HashMap<String, Object>();
		zlmap.put("nl1", year1);
		zlmap.put("nl2", year2);
		
		return SUCCESS;
	}
	public int toYear(Date date){
		Date newdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int res = -1;
		while (newdate.compareTo(calendar.getTime()) >= 0) {
			res++;
			calendar.add(Calendar.YEAR, 1);
		}
		
		return res;

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String exportExcel() throws UnsupportedEncodingException{
		CreateFileUtil util = CreateFileUtil.getInstance();
		int records=Integer.parseInt(request.getParameter("records"));
		String[] head=request.getParameter("colnames").split(",");
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		Map<String,String> colname=new HashMap();
		colname.put("responseOrg", "执行机关");
		colname.put("name", "姓名");
		colname.put("userdName", "曾用名");
		colname.put("sex", "性别");
		colname.put("nation", "民族");
		colname.put("identityCard", "身份证号");
		colname.put("birthdate", "出生日期");
		colname.put("isadult", "是否成年");
		colname.put("reportTime", "到区县报到时间");
		colname.put("isdeviceCode", "是否电子监管");
		colname.put("health", "健康状况");
		colname.put("healthSpecific", "具体健康状况");
		colname.put("healthContagion", "具体传染病史");
		colname.put("psychosis", "是否有精神病");
		colname.put("healthMental", "心理是否健康");
		colname.put("healthMentalSpecific", "具体心理健康状况");
		colname.put("accreditingOrgan", "心理鉴定机构");
		colname.put("politicsStatus", "政治面貌");
		colname.put("politicsStatusOriginal", "原政治面貌");
		colname.put("phoneNumber", "联系电话");
		colname.put("natureHome", "户籍性质");
		colname.put("houseAddress", "居住地详细地址");
		colname.put("homeAddress", "户籍详细地址");
		colname.put("educationDegree", "文化程度");
		colname.put("maritalState", "婚姻状况");
		colname.put("workState", "就业就学情况");
		colname.put("workNuit", "现工作单位");
		colname.put("workNuitPhone", "单位联系电话");
		colname.put("passport", "护照号码");
		colname.put("hrPermit", "回乡证号码");
		colname.put("hkIdentity", "香港身份证号码");
		colname.put("amIdentity", "澳门身份证号码");
		colname.put("tbIdentity", "台胞证号码");
		colname.put("gatPermit", "港澳台通行证号码");
		colname.put("adjustType", "矫正类别");
		colname.put("adjustPeriod", "矫正期限");
		colname.put("dateSAdjust", "矫正起始日期");
		colname.put("dateEAdjust", "矫正结束日期");
		colname.put("crimeType", "犯罪类型");
		colname.put("accusation", "具体罪名");
		colname.put("oriPunishment", "原判刑罚");
		colname.put("dateSOri", "原判刑起始日期");
		colname.put("dateEOri", "原判刑结束日期");
		colname.put("oriPeriod", "原判刑期");
		colname.put("imprisonmentPeriod", "有期徒刑期限");
		colname.put("addpunishment", "附加刑");
		colname.put("nonpoliticalPeriod", "剥夺政治权利期限");
		colname.put("dateSNonpolitical", "剥夺政治权利起始日期");
		colname.put("dateENonpolitical", "剥夺政治权利结束日期");
		colname.put("receiveUnit", "接收剥夺政治权利人员公安机关名称");
		colname.put("receivePerson", "接收人");
		colname.put("dateTransfer", "移交日期");
		colname.put("sisType", "是否“四史”");
		colname.put("sansType", "是否“三涉”");
		colname.put("isRecidivism", "是否累犯");
		colname.put("slfzType", "是否“三类犯罪”");
		colname.put("isalone", "是否共同犯罪");
		colname.put("isforbidden", "是否被宣告禁令");
		colname.put("forbidden", "禁止令内容");
		colname.put("dateSForbidden", "禁止令起始日期");
		colname.put("dateEForbidden", "禁止令结束日期");
		colname.put("dateReceive", "社区服刑人员接收日期");
		colname.put("receiveType", "社区服刑人员接收方式");
		colname.put("reportInfo", "报到情况");
		colname.put("groupInfo", "矫正小组人员组成情况");
		colname.put("removeReason", "解除/终止矫正原因");
		colname.put("removeDate", "解除/终止矫正日期");
		colname.put("jailType", "收监执行类型");
		colname.put("jailDate", "收监执行日期");
		colname.put("jailReason", "收监执行原因");
		colname.put("deathDate", "死亡日期");
		colname.put("deathReason", "死亡原因");
		colname.put("deathReasonDetail", "具体死因");
		colname.put("performance", "矫正期间表现");
		colname.put("manner", "认罪态度");
		colname.put("istrained", "是否参加职业技能培训");
		colname.put("hascertificate", "是否获得职业技能证书");
		colname.put("issanwu", "是否三无人员");
		colname.put("risk", "危险性评估");
		colname.put("familyContact", "家庭联系情况");
		colname.put("remark", "特殊情况备注及帮教建议");
		colname.put("", "矫正期间表现鉴定意见");
		//colname.put("ydDeviceNumber", "当前佩戴电子监管设备"); 
		colname.put("wearTime", "佩戴电子监管设备时间范围");
		colname.put("releaseTime", "摘除电子监管设备时间范围");
		colname.put("alarmType", "报警类型");
		colname.put("alarmTime", "报警时间范围");
		String []headNames=Arrays.copyOf(head,head.length);
		for(int i=0;i<headNames.length;i++){
			Set<String> set=colname.keySet();
			for(String e:set){
				if(e.equals(headNames[i])){
					headNames[i]=colname.get(e);
				}
			}
		}
		try {
			List<SearchRule> defaultRules=null;// = new ArrayList<SearchRule>();
			Page page=this.fillPageWithJQGridRequest();
			String filters =new String( this.getJqgrid().getFilters().getBytes(
					"ISO-8859-1"), "utf-8");//get请求中文乱码解决,主要中文搜索问题。例如姓名
			this.getJqgrid().setFilters(filters);
			List<SearchRule> rules=this.parseJQGridRequest(defaultRules);
			//对单位查询条件特殊处理
			Iterator irules=rules.listIterator();
			boolean havaResponseOrgsfsRule=false;
			boolean havaResponseOrgRule=false;
			while(irules.hasNext()){
				SearchRule r=(SearchRule) irules.next();
				if(r.getField().equals("responseOrgsfs")){
					irules.remove();
					havaResponseOrgsfsRule=true;
					continue;
				}
				if(r.getField().equals("responseOrg")){
					String orgType=orgService.get(r.getData()).getOrgType();
					if(orgType.equals("2")){
						r.setOp("in");
						String data=orgService.getChildrenIdsString(r.getData()).toString();
						r.setData(data);
					}
					havaResponseOrgRule=true;
					continue;
				}
			}
			if(!havaResponseOrgsfsRule&&!havaResponseOrgRule){
				if(!getCurOrgId().equals("1")){
					SearchRule sr = new SearchRule();
					sr.setField("responseOrg");
					sr.setOp("in");
					String data=orgService.getChildrenIdsString(getCurOrgId());
					sr.setData(data);
					rules.add(sr);
				}
			}
			//单位查询条件特殊处理结束
			page.setPageSize(200);//每页最大只能设置200
			int countPages = records/200;
			if ( records%200> 0) {
				countPages++;
			}
			int bigPages=records/2000;
			if ( records%2000> 0) {
				bigPages++;
			}
			Map<String,List<Object[]>> map=new HashMap();
			Object colo=null;
			Object ViewCcFxryAllinfoUnAlarm[]=null;
			for(int i=1;i<=bigPages;i++){
				List res=new ArrayList();
				for(int j=(i-1)*10+1;j<(i-1)*10+11;j++){
					page.setPageNo(j);
					res.addAll(unalarmservice.findPaged(
							 page,
							 rules,Arrays.asList(head)));
					if(j==countPages){
						break;
					}
				}
				List<Object[]> list=new ArrayList();
				for(int k=0;k<res.size();k++){
					if(head.length!=1){
						ViewCcFxryAllinfoUnAlarm=(Object[])res.get(k);
					}else{//若是只选择一列导出，返回不再是数组而是string
						ViewCcFxryAllinfoUnAlarm=new Object[]{res.get(k)};
					}
					for(int t=0;t<head.length;t++){
						if(ViewCcFxryAllinfoUnAlarm[t]==null){
							ViewCcFxryAllinfoUnAlarm[t]="";
						}else{//数据格式化
							if(head[t].equals("responseOrg")){//单位格式化
								colo=(Object)getOrgName((String)ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("sex")){//性别格式化
								colo=dictionaryInfoService.findItemsMap("XB").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("nation")){//民族格式化
								colo=dictionaryInfoService.findItemsMap("MZ").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("birthdate")
									||head[t].equals("dateSAdjust")  
									||head[t].equals ("dateEAdjust")
									||head[t].equals ("dateSOri")
									||head[t].equals ("dateEOri")
									||head[t].equals ("dateSNonpolitical")
									||head[t].equals ("dateENonpolitical")
									||head[t].equals ("dateTransfer")
									||head[t].equals ("dateSForbidden")
									||head[t].equals ("dateEForbidden")
									||head[t].equals ("dateReceive")
									||head[t].equals ("removeDate")
									||head[t].equals ("jailDate")
									||head[t].equals ("deathDate")){//日期格式化
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
								ViewCcFxryAllinfoUnAlarm[t]=((Date)ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=sdf.format(ViewCcFxryAllinfoUnAlarm[t]);
								continue;
							}
							if(head[t].equals("isadult")//是否成年格式化
									||head[t].equals("psychosis") //是否有精神病格式化
									||head[t].equals("healthMental") //心理是否健康格式化
									||head[t].equals("isRecidivism") //是否累犯格式化 
									||head[t].equals("slfzType") // 是否“三类犯罪”格式化
									||head[t].equals("isalone")  //是否共同犯罪格式化
									||head[t].equals("isforbidden") //是否被宣告禁令格式化 
									||head[t].equals("isgroupInfo") //是否建立矫正小组格式化 
									||head[t].equals("istrained")//是否参加职业技能培训格式化  
									||head[t].equals("hascertificate")//是否获得职业技能证书格式化  
									||head[t].equals("issanwu")//是否三无人员格式化
									//||head[t].equals("ydDeviceNumber")//当前是否佩戴电子监管设备格式化
									||head[t].equals("isdeviceCode")//是否电子监管格式化
									){//是否成年格式化
								colo=dictionaryInfoService.findItemsMap("SF").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							/*if(head[t].equals("isdeviceCode")){//是否电子监管格式化
								ViewCcFxryAllinfoUnAlarm[t]=dictionaryInfoService.findItemsMap("SF").get(ViewCcFxryAllinfoUnAlarm[t]);
							}*/
							if(head[t].equals("health")){//健康状况格式化
								colo=dictionaryInfoService.findItemsMap("JKZK").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("healthContagion")){//具体传染病史格式化
								colo=dictionaryInfoService.findItemsMap("CRB").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("politicsStatus")||head[t].equals("politicsStatusOriginal")){//（原）政治面貌格式化
								colo=dictionaryInfoService.findItemsMap("ZZMM").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("natureHome")){//户籍性质格式化
								colo=dictionaryInfoService.findItemsMap("HJXZ").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("educationDegree")){//文化程度格式化
								colo=dictionaryInfoService.findItemsMap("WHCD").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("maritalState")){//婚姻状况格式化
								colo=dictionaryInfoService.findItemsMap("HYZK").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("workState")){//就业就学情况格式化
								colo=dictionaryInfoService.findItemsMap("JYJX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("adjustType")){//矫正类别格式化
								colo=dictionaryInfoService.findItemsMap("JZLB").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("crimeType")){//犯罪类型格式化
								colo=dictionaryInfoService.findItemsMap("FZLX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("accusation")){//具体罪名格式化
								colo=dictionaryInfoService.findItemsMap("JTZM").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("oriPunishment")){//原判刑罚格式化
								colo=dictionaryInfoService.findItemsMap("XFLX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("imprisonmentPeriod")){//有期徒刑期限格式化
								colo=dictionaryInfoService.findItemsMap("YQTXQX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("addpunishment")){//附加刑格式化
								colo=dictionaryInfoService.findItemsMap("FJX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("nonpoliticalPeriod")){//剥夺政治权利期限格式化
							    colo=dictionaryInfoService.findItemsMap("BDZZQLQX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("sisType")){//是否“四史”格式化
								colo=dictionaryInfoService.findItemsMap("SS").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("sansType")){//是否“三涉”格式化
								colo=dictionaryInfoService.findItemsMap("SANS").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("receiveType")){//接收方式格式化
								colo=dictionaryInfoService.findItemsMap("JSFS").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("reportInfo")){//报到情况格式化
								colo=dictionaryInfoService.findItemsMap("BDQK").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("groupInfo")){//矫正小组人员组成情况格式化
								colo=dictionaryInfoService.findItemsMap("JZXZRYQK").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("removeReason")){//解除/终止矫正原因格式化
								colo=dictionaryInfoService.findItemsMap("JJYY").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("jailType")){//收监执行类型格式化
								colo=dictionaryInfoService.findItemsMap("SJZXLX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("jailReason")){//收监执行原因格式化
								colo=dictionaryInfoService.findItemsMap("SJZXYY").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("deathReason")){//死亡原因格式化
								colo=dictionaryInfoService.findItemsMap("SWYY").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("deathReasonDetail")){//具体死因格式化
								colo=dictionaryInfoService.findItemsMap("JTSY").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("performance")){//矫正表现格式化
								colo=dictionaryInfoService.findItemsMap("JZBX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("manner")){//认罪态度格式化
								colo=dictionaryInfoService.findItemsMap("RZTD").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("risk")){//危险性评估格式化
								colo=dictionaryInfoService.findItemsMap("WXXPG").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("familyContact")){//家庭联系情况格式化
								colo=dictionaryInfoService.findItemsMap("JTLXQK").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
							if(head[t].equals("alarmType")){//报警类型格式化
								colo=dictionaryInfoService.findItemsMap("BJLX").get(ViewCcFxryAllinfoUnAlarm[t]);
								ViewCcFxryAllinfoUnAlarm[t]=(colo!=null?colo:"");
								continue;
							}
						}
					}
					list.add((ViewCcFxryAllinfoUnAlarm));
				}
				map.put(String.valueOf(i),list);
			}
			fileName=util.createComprehensiceExcel(
					 map,headNames
					 );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public OrganizationInfoService getOrganizationInfoService() {
		return organizationInfoService;
	}

	public void setOrganizationInfoService(
			OrganizationInfoService organizationInfoService) {
		this.organizationInfoService = organizationInfoService;
	}

	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}

	public Map<String, Object> getZlmap() {
		return zlmap;
	}

	public void setZlmap(Map<String, Object> zlmap) {
		this.zlmap = zlmap;
	}
	
	
	
	
}
