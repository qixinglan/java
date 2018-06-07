package com.nci.dcs.system.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.exceptions.InsertException;
import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.HttpUtil;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.data.model.Jx;
import com.nci.dcs.data.model.Zw;
import com.nci.dcs.data.service.JxService;
import com.nci.dcs.data.service.ZwService;
import com.nci.dcs.official.model.OrganizationInfo;
import com.nci.dcs.official.model.Persons;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.official.service.PersonsService;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.DictionaryKeyValue;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.BmbService;
import com.nci.dcs.system.service.RoleService;
import com.nci.dcs.system.service.UserService;
import com.opensymphony.xwork2.Action;

public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;

	private List<Jx> jxList;
	private List<Zw> zwList;
	private String zw;
	private String jx;

	@Autowired
	private UserService service;
	@Autowired
	private JxService jxService;
	@Autowired
	private ZwService zwService;
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private PersonsService personsService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BmbService bmbService;

	private int isQuery = 0;
	private String tmpMc;
	private String mc;

	private String pw;

	private String width = "70%"; // 后台首页导航栏宽度
	private int xxfb = 1;
	private int wssc = 1;
	private int cgys = 1;
	private int jhjs = 1;
	private int tjfx = 1;
	private int zjxx = 1;
	private int xtgl = 1;

	private int isCA = 0;
	private String username;
	private String password;
	
	private AjaxFormResult ajaxFormResult;
	
	public String initAdd() throws Throwable {
		try {
			OrganizationInfo org = getUserOrg();
			request.setAttribute("orgId", org.getOrgId());
			request.setAttribute("roleList", roleService.find());
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public String add() throws Throwable {
		try {
			if (!isNotNull(entity.getId())) {
				entity.setId(null);
			}
			String name = entity.getName();
			Persons person = personsService.get(name);
			if (person != null) {
				entity.setName(person.getName());
				entity.setPerson(person);
			}
			String roleId = request.getParameter("role");
			if (isNotNull(roleId)) {
				 Set<Role> roleSet=new HashSet<Role>(0);
				String[] roleArray = roleId.split(",");
				for(String role: roleArray){
					Role r = roleService.get(Integer.parseInt(role));
					roleSet.add(r);
				}
				entity.setRoles(roleSet);
			}
			entity.setPassWord("123");
			service.create(entity);
		} catch (Throwable e) {
			throw new InsertException(e.getMessage());
		}
		return this.list();
	}

	@Override
	public String list() throws Throwable {
		try {
			if (isQuery == 1 && !mc.equals(tmpMc))
				mc = tmpMc;
			isQuery = 0;
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
	}

	/**
	 * Description:列表条件
	 * 
	 * @author Shuzz
	 * @since 2014年6月19日下午4:06:03
	 * @return
	 */
	private List<SearchRule> defaultRule() {
		List<SearchRule> result = new ArrayList<SearchRule>();
		String orgId = getCurOrgId();
		boolean flag = true;
		String jqFilter = jqgrid.getFilters();
		if (isNotNull(jqFilter) && jqFilter.contains("searchOrg")) {
			flag = false;
		}
		if (flag) {
			if (isNotNull(orgId) && !rootOrgId.equals(orgId)) {
				String ids = organizationInfoService
						.getChildrenIdsString(orgId);
				if (isNotNull(ids)) {
					SearchRule rule = new SearchRule();
					rule.setOp("in");
					rule.setField("wunit.bm");
					rule.setData(ids);
					result.add(rule);
				}
			}
		}
		SearchRule rule = new SearchRule();
		rule.setOp("eq");
		rule.setField("wunit.source");
		rule.setData("1");
		result.add(rule);
		return result;
	}

	public String search() throws Throwable {
		try {
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			for (SearchRule rule : searchs) {
				if (rule.getField().equals("searchOrg")
						&& rule.getOp().equals("nci")) {
					String ids = organizationInfoService
							.getChildrenIdsString(rule.getData());
					if (isNotNull(ids)) {
						rule.setField("wunit.bm");
						rule.setOp("in");
						rule.setData(ids);
					} else {
						searchs.remove(rule);
					}
				}
			}
			service.findPersonPaged(this.fillPageWithJQGridRequest(), searchs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String searchForRole() throws Throwable {
		try {
			String roleId = request.getParameter("roleId");
			List<SearchRule> searchs = this.parseJQGridRequest(defaultRule());
			// 筛选未授和已授当前角色权限用户
			SearchRule search = new SearchRule();
			search.setField("roles.id");
			search.setOp("eqornu");
			search.setData(roleId);
			searchs.add(search);
			this.fillPageWithJQGridRequest();
			String orderby = page.getOrderBy();
			if (orderby != null && orderby.equals("roles")) {
				page.setOrderBy("wunit.id");
				Page<User> userPage = service.findPersonForRole(page, searchs,
						roleId);
				String order = page.getOrder();
				List<User> userList = userPage.getResult();
				//按是否授权排序
				if (order != null && order.equals("asc")) {// 升序
					Collections.sort(userList, new Comparator<User>() {
						public int compare(User user1, User user2) {
							Integer a = user1.getRoles().size();
							Integer b = user2.getRoles().size();
							return b.compareTo(a);
						}
					});
				} else {// 降序
					Collections.sort(userList, new Comparator<User>() {
						public int compare(User user1, User user2) {
							Integer a = user1.getRoles().size();
							Integer b = user2.getRoles().size();
							return a.compareTo(b);
						}
					});
				}
			} else {
				service.findPersonForRole(page, searchs, roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		try {
			User u = service.get(entity.getId());
			CommonUtils.copyWithOutNull(u, entity);
			String roleId = entity.getRoleNames();
			if (isNotNull(roleId)) {
				u.getRoles().clear();
				Set<Role> roleSet=new HashSet<Role>(0);
					String[] roleArray = roleId.split(",");
					for(String role: roleArray){
						Role r = roleService.get(Integer.parseInt(role));
						roleSet.add(r);
					}
				u.setRoles(roleSet);
			}
			service.update(u);
			ajaxFormResult = AjaxFormResult.success("");
		} catch (Throwable e) {
			ajaxFormResult = AjaxFormResult.error(e.getMessage());
		}
		return this.list();
	}

	@Override
	public String view() throws Throwable {
		try {
			String id = request.getParameter("userId");
			entity = service.get(id);
			if (entity.getRoles().size() > 0) {
				String roleId="";
				for(Role temp: entity.getRoles()){
					roleId += temp.getId().toString()+",";
				}
				String role = roleId.substring(0, roleId.lastIndexOf(","));
				entity.setRoleNames(role);
				request.setAttribute("roleId", role);
			}
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return SUCCESS;
	}

	public String showInfo() throws Throwable {
		try {
			String id = request.getParameter("userId");
			entity = service.get(id);
			String roleName="";
			String name="";
			for(Role temp: entity.getRoles()){
				name = temp.getName();
				if(name!=null){
					roleName += name.toString()+",";
				}
			}
			String roleNames = roleName.equals("")?"":roleName.substring(0, roleName.lastIndexOf(","));
			entity.setRoleNames(roleNames);
			//request.setAttribute("roleNames", roleNames);
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return Action.SUCCESS;
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

	/**
	 * 用户登陆
	 * 
	 * @return
	 * @throws Throwable
	 */
	public String login() throws Throwable {
		String result = Action.SUCCESS;
		try {
			entity.setUserName(username);
			User user = service.findByLoginName(entity.getUserName());
			if (user != null) {
				if (isCA == 0 && !password.equals(user.getPassWord())) {
					request.setAttribute("message", "密码错误");
					result = Action.INPUT;
				} else {
					if(!"1".equals(user.getIsvalid())){
						result = Action.INPUT;
						request.setAttribute("message", "该用户已经被停用");
					}
					if (user.getWunit() != null) {
						if (user.getWunit().getUnit().equals("0")) {
							Bmb bmb = bmbService.get(user.getWunit()
									.getSupOrg());
							user.setWunit(bmb);
						}
						LoginInfoUtils.setUser(request.getSession(), user);
					} else {
						result = Action.INPUT;
						request.setAttribute("message", "该用户无机构");
					}
				}
			} else {
				result = Action.INPUT;
				request.setAttribute("message", "用户名不存在");
			}
			if (Action.SUCCESS.equals(result)) {
				if (CommonUtils.isNotNull(user.getRoles())) {
					Role role = user.getRoles().iterator().next();
					request.getSession().setAttribute("roleId", role.getId());
				}else{
					request.getSession().setAttribute("roleId", null);
				}
				request.getSession().setAttribute("roles", user.getRoles());
				request.getSession().setAttribute("org", user.getWunit());
			}
		} catch (Throwable e) {
			throw new QueryException(e.getMessage());
		}
		return result;
	}

	public String ssoLogin() throws Throwable {
		try {
			String causer = LoginInfoUtils.getCASUsername(request.getSession());
			if (causer != null && !causer.isEmpty()) {
				username = causer;
				isCA = 1;
				return login();
			}
		} catch (Exception e) {
		}
		throw new Exception("用户无权限登录！");
	}

	public String logout() {
		LoginInfoUtils.setUser(request.getSession(), null);
		request.getSession().setAttribute("roleId", null);
		request.getSession().setAttribute("roles", null);
		request.getSession().setAttribute("org", null);
		return SUCCESS;
	}
	
	public String getLogoutRedirect(){
		if (LoginInfoUtils.isCAS(request.getSession())){
			return request.getSession().getServletContext().getInitParameter("logoutUrl");
		}
		return LoginInfoUtils.getLoginUrl(request.getSession());
	}

	public String validatePassword() {
		try {
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			if (!getUser().getPassWord().equals(pw))
				ServletActionContext.getResponse().getWriter().printf("false");
			else
				ServletActionContext.getResponse().getWriter().printf("true");
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String userId = request.getParameter("userId");
		if (isNotNull(userId)) {
			List<String> ids = new ArrayList<String>();
			ids.addAll(Arrays.asList(userId.split(",")));
			for (String id : ids) {
				service.delete(id);
			}
		}
		return SUCCESS;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
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

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getJx() {
		return jx;
	}

	public void setJx(String jx) {
		this.jx = jx;
	}

	public List<Jx> getJxList() {
		return jxList;
	}

	public void setJxList(List<Jx> jxList) {
		this.jxList = jxList;
	}

	public List<Zw> getZwList() {
		return zwList;
	}

	public void setZwList(List<Zw> zwList) {
		this.zwList = zwList;
	}

	public int getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(int isQuery) {
		this.isQuery = isQuery;
	}

	public String getTmpMc() {
		return tmpMc;
	}

	public void setTmpMc(String tmpMc) {
		this.tmpMc = tmpMc;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getXxfb() {
		return xxfb;
	}

	public void setXxfb(int xxfb) {
		this.xxfb = xxfb;
	}

	public int getWssc() {
		return wssc;
	}

	public void setWssc(int wssc) {
		this.wssc = wssc;
	}

	public int getCgys() {
		return cgys;
	}

	public void setCgys(int cgys) {
		this.cgys = cgys;
	}

	public int getJhjs() {
		return jhjs;
	}

	public void setJhjs(int jhjs) {
		this.jhjs = jhjs;
	}

	public int getTjfx() {
		return tjfx;
	}

	public void setTjfx(int tjfx) {
		this.tjfx = tjfx;
	}

	public int getZjxx() {
		return zjxx;
	}

	public void setZjxx(int zjxx) {
		this.zjxx = zjxx;
	}

	public int getXtgl() {
		return xtgl;
	}

	public void setXtgl(int xtgl) {
		this.xtgl = xtgl;
	}

	public int getIsCA() {
		return isCA;
	}

	public void setIsCA(int isCA) {
		this.isCA = isCA;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 加载角色下拉列表
	 * @return
	 */
	public String roleJson(){
		try {
			//获取信息
			List<Role> roleList= roleService.find();
			//封装参数dicts
			Map<String, List<DictionaryKeyValue>> dicts = new HashMap<String,List<DictionaryKeyValue>>();
			List<DictionaryKeyValue> dictionaryKeyValueList = new ArrayList<DictionaryKeyValue>();
			for (Role item : roleList){
				DictionaryKeyValue keyvalue = new DictionaryKeyValue();
				keyvalue.setName(item.getName());
				keyvalue.setCode(item.getId().toString());
				keyvalue.setUsing(true);
				dictionaryKeyValueList.add(keyvalue);
			}
			dicts.put("ROLE", dictionaryKeyValueList);
			this.response.setHeader("Last-Modified", HttpUtil.date2LastModified(new Date()));
			this.response.setHeader("Content-Type", "text/json;charset=utf-8");
			this.response.setHeader("Cache-Control", "max-age=0");
			JSONObject result = JSONObject.fromObject(dicts);
			this.response.getWriter().write(result.toString());
		} catch (IOException e) {
		}
		return "none";
	}

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}
}
