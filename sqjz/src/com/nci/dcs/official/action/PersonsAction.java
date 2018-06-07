package com.nci.dcs.official.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.dto.PersonsKeyValue;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.official.service.PersonsService;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.DictionaryInfoService;

/**
 * Description:系统默认设置action
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
public class PersonsAction extends BaseAction<Persons> {
	@Autowired
	private PersonsService personsService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private DictionaryInfoService dictionaryInfoService;

	private String fileName;
	private InputStream targetFile;
	public AjaxFormResult ajaxFormResult;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	/**
	 * 
	 */
	private static final long serialVersionUID = 6768766342622199640L;

	/**
	 * Description:列表条件
	 * 
	 * @author Shuzz
	 * @since 2014年6月19日下午4:06:03
	 * @return
	 */
	private List<SearchRule> defaultRule() {
		List<SearchRule> result = new ArrayList<SearchRule>();
		String curOrgId = getCurOrgId();
		boolean flag = true;
		String jqFilter = jqgrid.getFilters();
		if (isNotNull(jqFilter) && jqFilter.contains("searchOrg")) {
			flag = false;
		}
		if (flag) {
			if (isNotNull(curOrgId) && !rootOrgId.equals(curOrgId)) {
				String ids = organizationInfoService
						.getChildrenIdsString(curOrgId);
				if (isNotNull(ids)) {
					SearchRule rule = new SearchRule();
					rule.setOp("in");
					rule.setField("org.orgId");
					rule.setData(ids);
					result.add(rule);
				}
			}
		}
		String orgId = request.getParameter("orgId");
		if (isNotNull(orgId)) {
			SearchRule sr = new SearchRule();
			sr.setField("org.orgId");
			sr.setOp("eq");
			sr.setData(orgId);
			result.add(sr);
		}
		SearchRule srsource = new SearchRule();
		srsource.setField("org.source");
		srsource.setOp("eq");
		srsource.setData("1");
		result.add(srsource);
		return result;
	}

	/**
	 * Description:JQGrid列表查询
	 * 
	 * @author Shuzz
	 * @since 2014年6月19日下午4:05:45
	 * @return
	 * @throws Throwable
	 */
	public String search() throws Throwable {
		try {
			// 目前暂未实现登录功能呢，暂无User，
			// 增加后台过滤字段
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			boolean flag = true;
			String shearchOrg = (String) request.getSession().getAttribute(
					"seachOrg");
			request.getSession().removeAttribute("seachOrg");
			for (SearchRule rule : searchs) {
				if (rule.getField().equals("org.orgId")
						&& rule.getOp().equals("cn")) {
					if (!rootOrgId.equals(rule.getData())) {
						String ids = organizationInfoService
								.getChildrenIdsString(rule.getData());
						if (isNotNull(ids)) {
							rule.setField("org.orgId");
							rule.setOp("in");
							rule.setData(ids);
						} else {
							searchs.remove(rule);
						}
					} else {
						rule.setField("org.orgId");
						rule.setOp("nn");
					}
					flag = false;
				}
			}
			if (flag && !CommonUtils.isNull(shearchOrg)) {
				SearchRule rule = new SearchRule();
				if (!rootOrgId.equals(shearchOrg)) {
					String ids = organizationInfoService
							.getChildrenIdsString(shearchOrg);
					if (isNotNull(ids)) {
						rule.setField("org.orgId");
						rule.setOp("in");
						rule.setData(ids);
						searchs.add(rule);
					}
				}
			}

			personsService.findPersonPaged(this.fillPageWithJQGridRequest(),
					searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Description:查看或者编辑某人信息
	 * 
	 * @author Shuzz
	 */
	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		entity = personsService.get(id);
		orgAddView(entity.getOrg());
		if (entity.getBirthday() != null) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			request.setAttribute("birth", sf.format(entity.getBirthday()));
		} else {
			request.setAttribute("birth", "未填");
		}
		return SUCCESS;
	}

	public String save() throws Throwable {
		try {
			if (entity.getId() != null && entity.getId().equals("")) {
				entity.setId(null);
			}
			personsService.saveOrUpate(entity);
			ajaxFormResult = new AjaxFormResult(true, "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			ajaxFormResult = new AjaxFormResult(false, "");
		}
		return SUCCESS;
	}

	/**
	 * Description:获取人员列表JSON,用于前台下拉选人
	 * 
	 * @author Shuzz
	 * @since 2014年6月19日上午10:13:58
	 * @return
	 */
	public String json() {
		try {
			String orgId = request.getParameter("orgId");
			String personType = request.getParameter("pType");
			String hasUser = request.getParameter("hasUser");
			boolean isUnion = request.getParameter("isUnion").equals("true") ? true
					: false;
			if (!isNotNull(orgId)) {
				orgId = organizationInfoService.findRoot().getOrgId();
			}
			String orgIds;
			if (isUnion) {
				orgIds = organizationInfoService.getChildrenIdsString(orgId);
			} else {
				orgIds = orgId;
			}
			Map<String, List<PersonsKeyValue>> persons = personsService
					.getPersonJsons(orgIds, personType, hasUser);
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(persons);
			this.response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}

	/**
	 * Description:批量删除机构人员 将 String delete（）方法改成void deleteRY方法，否则会报找不到相应视图的错误
	 * 
	 * @author xll
	 */
	public void deleteRY() throws Throwable {
		String perId = request.getParameter("perId");
		if (isNotNull(perId)) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(Arrays.asList(perId.split(",")));
			personsService.delete(ids);
		}
	}

	private void orgAddView(OrganizationInfo org) {
		if (org != null) {
			String orgType = org.getOrgType();
			if ("0".equals(org.getUnit())) {
				orgType = org.getSupOrg().getOrgType();
			} else if (org.getCname().contains("中途之家")) {
				orgType = "10";
			}
			request.setAttribute("orgType", orgType);
		}
	}

	/**
	 * Description:新增机构人员
	 * 
	 * @author Shuzz
	 */
	@Override
	public String add() throws Throwable {
		String orgId = request.getParameter("orgId");
		OrganizationInfo org = organizationInfoService.get(orgId);
		entity.setOrg(org);
		orgAddView(org);
		return SUCCESS;
	}

	@Override
	public String list() throws Throwable {
		String seachOrg = request.getParameter("searchOrg");
		request.getSession().setAttribute("seachOrg", seachOrg);
		request.setAttribute("seachOrg", seachOrg);
		return SUCCESS;
	}

	private OrganizationInfo getUserOrg() {
		User user = getUser();
		OrganizationInfo org;
		if (user != null) {
			org = organizationInfoService.get(user.getWunit().getBm());
		} else {
			org = organizationInfoService.findRoot();
		}
		return org;
	}

	private List<SearchRule> excelRule() throws UnsupportedEncodingException {
		List<SearchRule> result = new ArrayList<SearchRule>();
		// 获取当前登录人所在的机构
		// OrganizationInfo org = getUserOrg();
		// String orgId = org.getOrgId();

		// 获得的Filter必须要进行转码，否则中文显示乱码
		String filters = new String(request.getParameter("filters").getBytes(
				"ISO-8859-1"), "utf-8");
		// 获取页面传递过来的机构ID
		String shearchOrg = request.getParameter("OrgId");
		if (isNotNull(filters) && !filters.equals("undefined")) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = jsonObject.getJSONArray("rules");
			SearchRule[] rules = (SearchRule[]) JSONArray.toArray(jsonArray,
					SearchRule.class);
			if (rules != null && rules.length > 0) {
				for (SearchRule rule : rules) {
					result.add(rule);
				}
			}
		}
		String ids = "";
		if (!rootOrgId.equals(shearchOrg)) {
			// rootOrgId=1 是市局；如果不是市局，则可能是区县局或者司法所；如果是区县局，则查询下所有的司法所；
			ids = organizationInfoService.getChildrenIdsString(shearchOrg);
		}

		if (ids != null && (!"".equalsIgnoreCase(ids))) {
			SearchRule sr = new SearchRule();
			sr.setField("org.orgId");
			sr.setOp("in");
			sr.setData(ids);
			result.add(sr);
		}
		// org.source eq 1，在机构表中，source=1为有效单位，0为无效单位。因此查询人时，这是一个隐含条件
		SearchRule srsource = new SearchRule();
		srsource.setField("org.source");
		srsource.setOp("eq");
		srsource.setData("1");
		result.add(srsource);
		return result;
	}

	/**
	 * Description:导出excel
	 * 
	 * @author Shuzz
	 * @since 2014年6月21日下午12:48:19
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String excel() throws Throwable {
		CreateFileUtil util = CreateFileUtil.getInstance();
		// String[] head = new String(request.getParameter("colNames").getBytes(
		// "ISO-8859-1"), "gb2312").split(",");
		String[] head = new String[] { "序号", "工作单位", "姓名", "原工作单位", "性别",
				"出生日期", "政治面貌", "学历", "职务/职级", "人员类别", "手机" };
		String sortName = request.getParameter("sortNames");
		LinkedList<String> headTable = new LinkedList<String>(
				Arrays.asList(head));
		// headTable.add("序号");
		// for (int i = 3; i < head.length; i++)
		// headTable.add(head[i]);
		List res = personsService.find(sortName, excelRule());
		Map xb = dictionaryInfoService.findItemsMap("XB");
		Map zzmm = dictionaryInfoService.findItemsMap("ZZMM");
		Map xl = dictionaryInfoService.findItemsMap("WHCD");
		Map zwzj = dictionaryInfoService.findItemsMap("ZWZJ");
		Map rylb = dictionaryInfoService.findItemsMap("RYLB");
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		for (int i = 0; i < res.size(); i++) {
			Object[] cc = (Object[]) res.get(i);
			LinkedList content = new LinkedList();
			content.add(i);
			for (int j = 0; j < cc.length; j++) {
				if (j == 3) {
					cc[j] = xb.get((String) cc[j]);
				}
				if (j == 5) {
					cc[j] = zzmm.get((String) cc[j]);
				}
				if (j == 6) {
					cc[j] = xl.get((String) cc[j]);
				}
				if (j == 7) {
					cc[j] = zwzj.get((String) cc[j]);
				}
				if (j == 8) {
					cc[j] = rylb.get((String) cc[j]);
				}
				if (cc[j] != null && cc[j] instanceof Date) {
					cc[j] = sdf.format(cc[j]);
				}
				content.add(cc[j]);
			}
			contentList.add(content);
		}

		try {
			fileName = util.create(headTable, contentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
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

	// 查询人员列表
	public String searchRYLB() throws Throwable {
		try {
			// 目前暂未实现登录功能呢，暂无User，
			// 增加后台过滤字段
			List<SearchRule> searchs = this
					.parseJQGridRequest(new ArrayList<SearchRule>());
			boolean flag = true;
			// 获取点击树节点传递过来的机构；如果刚入页面或者刷新页面，没有点击树节点，默认传递当前登录人所在的机构
			String shearchOrg = request.getParameter("orgId");
			if (shearchOrg != null && (!"".equalsIgnoreCase(shearchOrg))) {
				SearchRule rule = new SearchRule();
				if (!rootOrgId.equals(shearchOrg)) {
					// rootOrgId=1 是市局；如果不是市局，则可能是区县局或者司法所；如果是区县局，则查询下所有的司法所；
					String ids = organizationInfoService
							.getChildrenIdsString(shearchOrg);
					if (ids != null && (!"".equalsIgnoreCase(ids))) {
						rule.setField("org.orgId");
						rule.setOp("in");
						rule.setData(ids);
						searchs.add(rule);
					}
				}
			}
			SearchRule srsource = new SearchRule();
			srsource.setField("org.source");
			srsource.setOp("eq");
			srsource.setData("1");
			searchs.add(srsource);
			personsService.findPersonPaged(this.fillPageWithJQGridRequest(),
					searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String delete() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}