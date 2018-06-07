package com.nci.dcs.em.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.zhcx.model.ViewCcFxryBaseinfo;
import com.nci.dcs.em.zhcx.service.ZhcxFxryMessageService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.service.DictionaryInfoService;

/**
 * Description:综合查询actio
 * 
 * @author clj
 * @since 2014年7月30日上午10:02:33
 */
public class ZhcxFxryMessageAction  extends BaseAction<ViewCcFxryBaseinfo>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 649169781010898645L;
	@Autowired
	private OrganizationInfoService organizationInfoService;
//	private String fileName;
	
	private Excel excel;
	class Excel {
		private InputStream inputStream;
		private String name;

		public Excel(InputStream inputStream, String name) {
			super();
			this.inputStream = inputStream;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
	}
	public Excel getExcel() {
		return excel;
	}

	public void setExcel(Excel excel) {
		this.excel = excel;
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private OrganizationInfoService orgService;

	@Autowired
	private ZhcxFxryMessageService service;
	
	@Autowired
	private DictionaryInfoService dictionaryService;
	/**
	 * 获取Service
	 * @return
	 */
	public ZhcxFxryMessageService getService() {
		return this.service;
	}
	
	public DictionaryInfoService getDictionaryService() {
		return dictionaryService;
	}
	/**
	 * 设置Service
	 * @return
	 */
	public void setService(ZhcxFxryMessageService service) {
		this.service = service;
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
	public String search(){
//		List<OrganizationInfo> findSfsBySup = organizationInfoService.findSfsBySup("192");
		String orgId = request.getParameter("orgId");
		this.getRequestPage();
		if(orgId != null&& !orgId.equals("1"))
			page.getCriterions().add(Restrictions.in("responseOrg", orgService.getChildrenIds(orgId)));
		service.findPaged(page);
		return "success";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createFile() throws UnsupportedEncodingException{
		CreateFileUtil util = CreateFileUtil.getInstance();
		String[] head = jqgrid.getCols().split(",");
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		String[] headers = new String[]{"序号","执行机关","矫正类别","姓名","性别",
							"心理是否健康","户籍性质","政治面貌","原政治面貌",
							"文化程度","婚姻状况","就业就学情况","犯罪类型",
							"原判刑罚","附加刑","是否“四史”","是否“三涉”",
							"是否累犯","是否共同犯罪","是否被宣告禁止令","社区服刑人员接收日期",
							"社区服刑人员接收方式","报到情况","是否建立矫正小组","矫正小组人员组成情况",
							"是否定位监控"
							};
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		try {
			List<SearchRule> rules = new ArrayList<SearchRule>();
//			String orgId = getCurOrgId();
//			if (!rootOrgId.equals(orgId)) {
//				SearchRule sr = new SearchRule();
//				sr.setField("responseOrg");
//				sr.setOp("eq");
//				sr.setData(orgId);
//				rules.add(sr);
//			}
			List res = service.findPaged(
					 this.fillPageWithJQGridRequest(),
					 this.parseJQGridRequest(rules), Arrays.asList(head));
			for(int i=0;i<res.size();i++){
				Object[] cc = (Object[])res.get(i);
				LinkedList content = new LinkedList();
				content.add(i);
				for(int j=0;j<cc.length;j++){
					if(cc[j]!=null&&cc[j] instanceof Date){
						cc[j] = sdf.format(cc[j]);
					}
					if(cc[j]!=null&&j==1){
						cc[j] = dictionaryService.findItemsMap("JZLB").get(cc[j]);
					}
					if(cc[j]!=null&&j==3){
						cc[j] = dictionaryService.findItemsMap("XB").get(cc[j]);
					}
					if(cc[j]!=null&&j==4){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					if(cc[j]!=null&&j==5){
						cc[j] = dictionaryService.findItemsMap("HJXZ").get(cc[j]);
					}
					if(cc[j]!=null&&j==6){
						cc[j] = dictionaryService.findItemsMap("ZZMM").get(cc[j]);
					}
					if(cc[j]!=null&&j==7){
						cc[j] = dictionaryService.findItemsMap("ZZMM").get(cc[j]);
					}
					if(cc[j]!=null&&j==8){
						cc[j] = dictionaryService.findItemsMap("WHCD").get(cc[j]);
					}
					if(cc[j]!=null&&j==9){
						cc[j] = dictionaryService.findItemsMap("HYZK").get(cc[j]);
					}
					if(cc[j]!=null&&j==10){
						cc[j] = dictionaryService.findItemsMap("JYJX").get(cc[j]);
					}
					if(cc[j]!=null&&j==11){
						cc[j] = dictionaryService.findItemsMap("FZLX").get(cc[j]);
					}
					if(cc[j]!=null&&j==12){
						cc[j] = dictionaryService.findItemsMap("XFLX").get(cc[j]);
					}
					if(cc[j]!=null&&j==13){
						cc[j] = dictionaryService.findItemsMap("FJX").get(cc[j]);
					}
					if(cc[j]!=null&&j==14){
						cc[j] = dictionaryService.findItemsMap("SS").get(cc[j]);
					}
					if(cc[j]!=null&&j==15){
						cc[j] = dictionaryService.findItemsMap("SANS").get(cc[j]);
					}
					if(cc[j]!=null&&j==16){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					if(cc[j]!=null&&j==17){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					if(cc[j]!=null&&j==18){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					if(cc[j]!=null&&j==20){
						cc[j] = dictionaryService.findItemsMap("JSFS").get(cc[j]);
					}
					if(cc[j]!=null&&j==21){
						cc[j] = dictionaryService.findItemsMap("BDQK").get(cc[j]);
					}
					if(cc[j]!=null&&j==22){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					if(cc[j]!=null&&j==23){
						cc[j] = dictionaryService.findItemsMap("JZXZRYQK").get(cc[j]);
					}
					if(cc[j]!=null&&j==24){
						cc[j] = dictionaryService.findItemsMap("SF").get(cc[j]);
					}
					content.add(cc[j]);
				}
				contentList.add(content);
			}
			String fileName = util.create(headTable, contentList);
			excel = new Excel(util.getFileInputStream(fileName), fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
