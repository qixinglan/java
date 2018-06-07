package com.nci.dcs.official.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.page.JsonTree;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.official.dto.OrganizationKeyValue;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Bmb;

/**
 * Description:系统默认设置action
 * 
 * @author Shuzz
 * @since 2014年5月23日上午9:51:54
 */
public class OrganizationAction extends BaseAction<OrganizationInfo> {
	@Autowired
	private OrganizationInfoService organizationInfoService;
	private List<JsonTree> jsonTree;
	public OrganizationInfo data;
	public AjaxFormResult ajaxFormResult;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6768766342622199640L;

	/**
	 * 重载机构数据字典
	 * 
	 * @name
	 * @return
	 * @author clj
	 * @date 2014年8月28日 下午1:59:22
	 * @message：
	 */
	public String reload() {
		organizationInfoService.loadCache(true);
		return "success";
	}

	@Override
	public String list() throws Throwable {
		Bmb bmb = getCurOrg();
		if (bmb == null) {
			OrganizationInfo org = organizationInfoService.findRoot();
			request.setAttribute("orgName", org.getCname());
			request.setAttribute("orgId", org.getOrgId());
			request.setAttribute("postalCode", org.getOrgType());
		} else {
			request.setAttribute("orgName", bmb.getMc());
			request.setAttribute("orgId", bmb.getBm());
			request.setAttribute("postalCode", bmb.getOrgType());
		}
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String search() throws Throwable {
		try {
			// 目前暂未实现登录功能呢，暂无User，
			// 增加后台过滤字段
			page = this.getRequestPage();
			String orgId = null;
			if (request.getParameter("orgId") != null) {
				orgId = request.getParameter("orgId");
			} else {
				orgId = getCurOrgId();
			}
			if (isNotNull(orgId) && !orgId.equals(rootOrgId)) {
				List ids = organizationInfoService.getChildrenIds(orgId);
				if (ids != null && ids.size() > 0) {
					page.getCriterions().add(Restrictions.in("orgId", ids));
				}
			}
			page.getCriterions().add(Restrictions.eq("source", "1"));
			organizationInfoService.findPaged(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String jgtree() throws Throwable {
		try {
			String parentId = request.getParameter("parentId");
			if (rootOrgId.equals(parentId)) {
				jsonTree = organizationInfoService.findJsonForSj();
			} else if ("FFFFFFFF".equals(parentId)) {
				jsonTree = organizationInfoService.findJsonByOrgType("2");
			} else {
				jsonTree = organizationInfoService.findJsonByParentId(parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String edit() throws Throwable {
		String id = request.getParameter("id");
		entity = organizationInfoService.get(id);
		if (entity != null) {
			String orgType = entity.getOrgType();
			if ("0".equals(entity.getUnit())) {
				orgType = entity.getSupOrg().getOrgType();
			} else if (entity.getCname().contains("中途之家")) {
				orgType = "10";
			}
			request.setAttribute("orgType", orgType);
		}
		return SUCCESS;
	}

	public String get() throws Throwable {
		String id = request.getParameter("id");
		data = organizationInfoService.get(id);
		return SUCCESS;
	}

	public String save() throws Throwable {
		try {
			organizationInfoService.saveOrUpate(entity);
			ajaxFormResult = new AjaxFormResult(true, "");
			// organizationInfoService.loadCache(true);
		} catch (Exception e) {
			ajaxFormResult = new AjaxFormResult(false, "");
		}
		return SUCCESS;
	}

	public String json() {
		Date ifModified = HttpUtil.ifModified2Date(this.request
				.getHeader("If-Modified-Since"));
		if (organizationInfoService.getLastModified() == null
				|| ifModified.before(organizationInfoService.getLastModified())) {
			try {
				Map<String, List<OrganizationKeyValue>> orgs = organizationInfoService
						.getCached_org();
				this.response.setHeader("Last-Modified", HttpUtil
						.date2LastModified(organizationInfoService
								.getLastModified()));
				this.response.setHeader("Content-Type",
						"text/json;charset=utf-8");
				this.response.setHeader("Cache-Control", "max-age=0");
				JSONObject result = JSONObject.fromObject(orgs);
				this.response.getWriter().write(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.response.setStatus(304);
		}
		return "none";
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		entity = organizationInfoService.get(id);
		String orgType=entity.getOrgType();
		if("1".equalsIgnoreCase(orgType)){
			//说明是 市局，没有上级机构
			entity.setSupOrgCname("");
		}else{
			entity.setSupOrgCname(entity.getSupOrg().getCname());
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String orgIds = request.getParameter("orgIds");
		if (isNotNull(orgIds)) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(Arrays.asList(orgIds.split(",")));
			String flag = organizationInfoService.delete(ids);
			Map<String, String> content = new HashMap<String, String>();
			content.put("0", flag);
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(content);
			this.response.getWriter().write(result.toString());
		}
		return "none";
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
		// User user = getUser();
		// OrganizationInfo org = null;
		// String orgName = "";
		// if (user != null) {
		// orgName = user.getWunit().getMc();
		// org = new OrganizationInfo();
		// org.setOrgId(user.getWunit().getBm());
		// } else {
		// org = organizationInfoService.findRoot();
		// orgName = org.getCname();
		// }
		OrganizationInfo org = new OrganizationInfo();
		org.setOrgId(request.getParameter("supOrgId"));
		entity.setSupOrg(org);
		// request.setAttribute("orgName", orgName);
		return SUCCESS;
	}

	public String getPosition() {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		return SUCCESS;
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

	public List<JsonTree> getJsonTree() {
		return jsonTree;
	}

	public void setJsonTree(List<JsonTree> jsonTree) {
		this.jsonTree = jsonTree;
	}
}