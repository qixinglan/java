package com.nci.dcs.em.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.dwjk.model.ViewAlarminfo;
import com.nci.dcs.em.service.AlarmInfoManagerService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

import edu.emory.mathcs.backport.java.util.Arrays;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AlarmInfoManagerAction extends BaseAction<ViewAlarminfo> {

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

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<ViewAlarminfo> alarmList;
	public ViewAlarminfo alarmInfo;
	public int personAlarCount;
	public int personAlarCount1;
	@Autowired
	private AlarmInfoManagerService service;
	@Autowired
	private OrganizationInfoService orgService;
	@Autowired
	private DictionaryInfoService dictionaryService;

	public DictionaryInfoService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryInfoService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public AlarmInfoManagerService getService() {
		return service;
	}

	public void setService(AlarmInfoManagerService service) {
		this.service = service;
	}

	public String search() {
		String orgId = request.getParameter("orgId");
		boolean flag = false;
		String sorgId = null;
		String filters = this.getJqgrid().getFilters();
		if (null != filters && filters.contains("\"field\":\"executeUnit\"")) {
			try {
				String[] tempFilters = filters
						.split("\"field\":\"executeUnit\"");
				String[] datas = tempFilters[1].split("\"data\":\"");
				sorgId = datas[1].substring(0, datas[1].indexOf("\""));
				if (!CommonUtils.isNull(sorgId)) {
					OrganizationInfo org = orgService.get(sorgId);
					if (org != null && "2".equals(org.getOrgType())) {
						int end=0;
						if(tempFilters[1].indexOf("{")>-1){
							end=tempFilters[1].indexOf("{");
						}else{
							end=tempFilters[1].indexOf("}")+1;
						}
						String newFilters = tempFilters[0].substring(0,tempFilters[0].length()-1)
								+ tempFilters[1].substring(end);
						this.getJqgrid().setFilters(newFilters);
						flag = true;
					}
				}
			} catch (Exception e) {

			}
		}
		this.getRequestPage();
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		if (flag && !CommonUtils.isNull(sorgId)) {
			page.getCriterions().add(
					Restrictions.in("executeUnit",
							orgService.getChildrenIds(sorgId)));
		} else {
			if (orgId != null && !orgId.equals("1"))
				page.getCriterions().add(
						Restrictions.in("executeUnit",
								orgService.getChildrenIds(orgId)));
		}
		service.findPaged(page);
		return "success";
	}

	public String getAlarmByOrg() {
		User user = getUser();
		String orgId = user.getWunit().getBm();
		String jgType = user.getWunit().getOrgType();
		getRequestPage();
		service.getAlarmByOrg(orgId, jgType, page);
		return SUCCESS;
	}

	public String getCountByPerson() {
		User user = getUser();
		String jgId = user.getWunit().getBm();
		String jgType = user.getWunit().getOrgType();
		personAlarCount = service.getCountByJGID(jgId, jgType);
		// personAlarCount = service.getCountByPerson(personId);
		return SUCCESS;
	}

	public String getCountByPersonAlarm() {
		User user = getUser();
		String jgId = user.getWunit().getBm();
		String jgType = user.getWunit().getOrgType();
		personAlarCount1 = service.getCountByJGIDAndTime(jgId, jgType);
		// personAlarCount = service.getCountByPerson(personId);
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		// TODO Auto-generated method stub
		// alarmList = service.find();
		return "success";
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		alarmInfo = service.get(id);
		return SUCCESS;
	}

	/**
	 * Description:未处理报警列表,只有司法局处理
	 * 
	 * @author Shuzz
	 * @return
	 * @since 2015年4月1日上午11:28:47
	 */
	public String todoList() {
		this.getRequestPage();
		//检查用户是否有查看特管人员权限特管
		if("2".equals(getUser().getIsws())){
			page.getCriterions().add(Restrictions.eq("isTgry","2"));
		}
		page.getCriterions().add(Restrictions.eq("executeUnit", getCurOrgId()));
		page.getCriterions().add(Restrictions.eq("status", "2"));
		service.findPaged(page);
		return "success";
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

	private final String[] headers = new String[] { "序号", "报警级别", "报警类型",
			"报警时间", "姓名", "执行机关", "矫正类别", "状态", "处理时间", "处理人/方式", "" };

	private final String[] wclHeaders = new String[] { "序号", "报警级别", "报警类型",
			"报警时间", "姓名", "" + "执行机关", "矫正类别" };

	public String createFile() throws UnsupportedEncodingException {
		CreateFileUtil util = CreateFileUtil.getInstance();
		// String sortName = request.getParameter("sortName");

		List res = null;
		LinkedList<String> headTable = null;
		if (request.getParameter("type") != null) {
			headTable = new LinkedList(Arrays.asList(wclHeaders));
			getRequestPage();
			Page<ViewAlarminfo> resPage = service.getAlarmByPerson(getUserId(),
					page);
			res = resPage.getResult();
		} else {
			headTable = new LinkedList(Arrays.asList(headers));
			String[] head = jqgrid.getCols().split(",");
			List<SearchRule> rules = new ArrayList<SearchRule>();
			res = service.findPaged(this.fillPageWithJQGridRequest(),
					this.parseJQGridRequest(rules), Arrays.asList(head));
		}

		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		// service.findByfilter(entity1, entity2);

		for (int i = 0; i < res.size(); i++) {
			Object[] cc = (Object[]) res.get(i);
			LinkedList content = new LinkedList();
			content.add(i);
			for (int j = 0; j < cc.length; j++) {
				if (cc[j] != null && cc[j] instanceof Date) {
					cc[j] = sdf.format(cc[j]);
				}
				if (cc[j] != null && j == 0) {
					cc[j] = dictionaryService.findItemsMap("BJJB").get(cc[j]);
				}
				if (cc[j] != null && j == 1) {
					cc[j] = dictionaryService.findItemsMap("BJLX").get(cc[j]);
				}
				if (cc[j] != null && j == 4) {
					cc[j] = getOrgName((String) cc[j]);
				}
				if (cc[j] != null && j == 5) {
					cc[j] = dictionaryService.findItemsMap("JZLB").get(cc[j]);
				}
				if (cc[j] != null && j == 6) {
					cc[j] = dictionaryService.findItemsMap("CLZT").get(cc[j]);
				}
				content.add(cc[j]);
			}
			contentList.add(content);
		}

		try {
			String fileName = util.create(headTable, contentList);
			excel = new Excel(util.getFileInputStream(fileName), fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
