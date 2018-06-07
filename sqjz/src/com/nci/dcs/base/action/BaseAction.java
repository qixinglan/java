package com.nci.dcs.base.action;

import java.lang.reflect.ParameterizedType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.Page;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageRequest;
import com.nci.dcs.common.web.jquery.jqgrid.JQGridPageResponse;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.Authorization;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.Role;
import com.nci.dcs.system.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * action基类
 * @author db2admin
 *
 * @param <T>
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class BaseAction<T> extends ActionSupport implements
		Preparable, ModelDriven, ServletRequestAware, ServletResponseAware {
	public static final String LIST = "list"; // list页面定义
	public static final String VIEW = "view"; // view页面定义
	protected final Logger logger = LogManager.getLogger(this.getClass()); // 为子类定义的logger
	

	protected T entity; // 模型对象
	protected Class<T> entityClass; // 模型对象对象类
	protected String entityClassName; // 模型对象对象类名

//	protected Invoker invoker; // Manager反射调用

	//如果使用jqgrid，使用getRequestPage()获取分页信息
	protected Page<T> page = new Page(15); // 分页对象
	protected Map mapRef; // 页面关联Map
	protected List listRef; // 页面关联List

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Object[] values;
	
	protected JQGridPageRequest jqgrid;
	private JQGridPageResponse jqgridPage;
	
	@Autowired
	private OrganizationInfoService orgService ;
	
	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}
	private static HashMap<String, String> ORG_MAP = null;


	public BaseAction() {
		super();
		// 通过反射获取entity的class
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		entityClassName = entityClass.getSimpleName();
	}
	
	public BaseAction(Class<T> type) {
		super();
		//允许Action的继承
		entityClass = type;
		entityClassName = entityClass.getSimpleName();
	}
	/**
	 * @see ModelDriven
	 */
	public T getModel() {
		return entity;
	}


	/**
	 * 显示对象列表页
	 */
	public abstract String list() throws Throwable;

	/**
	 * 显示对象查看页
	 */
	public abstract String view() throws Throwable;

	/**
	 * 显示新增或修改页
	 */
	@Override
	public
	abstract String input() throws Exception ;
//	{
//		onDisplayPrepare();
//		return INPUT;
//	}

	/**
	 * 删除对象
	 */
	public abstract String delete() throws Throwable;
//	{
//		if (getId() != null)
//			getInvoker().delete(getManager(), getId());
//		return SUCCESS;
//	}

	/**
	 * 启用对象
	 */
	public abstract String enable() throws Throwable ;
//	{
//		if (getId() != null)
//			getInvoker().enable(getManager(), getId());
//		return SUCCESS;
//	}

	/**
	 * 废除对象
	 */
	public abstract  String disable() throws Throwable;
//	{
////		if (getId() != null)
////			getInvoker().disable(getManager(), getId());
//		return SUCCESS;
//	}

	/**
	 * 审核对象
	 */
	public abstract String audit() throws Throwable;
//	{
////		if (getId() != null)
////			getInvoker().audit(getManager(), getId(), null);
//		return SUCCESS;
//	}

	/**
	 * 新增或修改对象保存
	 */
	public abstract String add() throws Throwable;
//	{
////		getInvoker().create(getManager(), entity);
//		return SUCCESS;
//	}
//	
	/**
	 * 新增或修改对象保存
	 */
	public abstract String update() throws Throwable;
//	{
////		getInvoker().update(getManager(), entity);
//		return SUCCESS;
//	}

	/**
	 * 批量删除对象
	 */
	public abstract String deleteBulk() throws Throwable ;
//	{
//		Object[] ids = getIds();
//		if (ids != null && ids.length > 0)
//			for (int i = 0; i < ids.length; i++)
//			{
//				//getInvoker().delete(getManager(), ids[i]);
//			}
//				
//		return SUCCESS;
//	}

	/**
	 * 批量启用对象
	 */
	public abstract String enableBulk() throws Throwable ;
//	{
//		Object[] ids = getIds();
//		if (ids != null && ids.length > 0)
//			for (int i = 0; i < ids.length; i++)
//			{
//				//getInvoker().enable(getManager(), ids[i]);
//			}
//				
//		return SUCCESS;
//	}

	/**
	 * 批量废除对象
	 */
	public abstract String disableBulk() throws Throwable ;
//	{
//		Object[] ids = getIds();
//		if (ids != null && ids.length > 0)
//			for (int i = 0; i < ids.length; i++)
//			{
//				//getInvoker().disable(getManager(), ids[i]);
//			}
//				
//		return SUCCESS;
//	}

	/**
	 * 批量审核对象
	 */
	public abstract String auditBulk() throws Throwable ;
//	{
//		Object[] ids = getIds();
//		if (ids != null && ids.length > 0)
//			for (int i = 0; i < ids.length; i++)
//			{
//				//getInvoker().audit(getManager(), ids[i], null);
//			}
//				
//		return SUCCESS;
//	}

	/**
	 * @see Preparable
	 */
	public void prepare() throws Exception {
		if (getId() != null) {
			//entity = (T) getInvoker().get(getManager(), getId());
			if (entity == null)
				throw new IllegalArgumentException("id对应的对象不存在");
		} else{
			entity = entityClass.newInstance();
		}
		onPrepare();
	}
	
	/**
	 * 根据模块名称判断当前用户是否具有操作权限
	 * @param name
	 * @return
	 */
	public boolean hasAuthorization(String name){
		Set<Role> roles = getRoles();
		if(roles != null){
			for(Iterator<Role> ite = roles.iterator(); ite.hasNext();){
				Role role = ite.next();
				Set<Authorization> authorizations = role.getAuthorization();
				if(authorizations != null){
					for(Iterator<Authorization> ite1 = authorizations.iterator(); ite1.hasNext();){
						Authorization authorization = ite1.next();
						if(StringUtils.isNotEmpty(authorization.getName()) && name.equals(authorization.getName())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public Set<Role> getRoles(){
		//夏先智 该方法获取当前登录的用户角色
		return this.getUser().getRoles();
	}
	

//	/**
//	 * 获取Manager反射调用
//	 */
//	protected Invoker getInvoker() {
//		if (invoker == null)
//			invoker = new DefaultInvoker();
//		return invoker;
//	}

//	/**
//	 * 获取Manager,子类必须实现
//	 */
//	protected abstract Object getManager();

	/**
	 * 获取主键id,子类必须实现
	 */
	public abstract Object getId();

	/**
	 * 获取主键id数组,子类必须实现
	 */
	public abstract Object[] getIds();

	/**
	 * @see #prepare()
	 */
	public void onPrepare() throws Exception {
		// 可在子类中重载,在prepare()中调用.
	}

	/**
	 * @see #list()
	 * @see #input()
	 */
	public void onDisplayPrepare() throws Throwable {
		// 可在子类中重载.准备显示页面需要的辅助对象,在list(),input()中调用.
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map getMapRef() {
		return mapRef;
	}

	public List getListRef() {
		return listRef;
	}
	
	/**
	 * 获取登录用户
	 * @return
	 */
	protected User getUser(){
		User user = LoginInfoUtils.getUser(request.getSession());
		return user;
	}
	
	public void addActionError(String anErrorMessage) {
		// 这里要先判断一下，是我们要替换的错误，才处理
		if (anErrorMessage.startsWith("the request was rejected because its size")) {
			Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);
			String s1 = "";
			if (m.find())
				s1 = m.group();
			String s2 = "";
			if (m.find())
				s2 = m.group();
			int actualValue = Integer.valueOf(s1);
			int limitValue = Integer.valueOf(s2);
			// 偷梁换柱，将信息替换掉
			DecimalFormat decimal = new DecimalFormat(".0000");			
//			super.addFieldError("myFile", "您上传的文件大小是（" + (actualValue>>20) + "M）超过允许的大小（" + (limitValue>>20) + "M）");
			super.addActionError("您上传的文件大小是（" + Double.parseDouble(decimal.format(actualValue/(1024.0*1024.0))) + "M）超过允许的大小（10M），请重新上传!");
//			super.addActionMessage("您上传的文件大小是（" + (actualValue>>20) + "M）超过允许的大小（" + (limitValue>>20) + "M）");
			// 也可以改为在Field级别的错误
			// super.addFieldError("file","你上传的文件大小（" + s1 + "）超过允许的大小（" + s2 + 
			// "）");
		} else {// 否则按原来的方法处理
			super.addActionError(anErrorMessage);
		}
	}
	/**
	 * 判断是否非空
	 * 
	 * @param obj
	 *            数据对象，预期输入的是整型或字符型的数据
	 * @return
	 */
	public boolean isNotNull(Object obj) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (obj != null) {
			String str = String.valueOf(obj);
			if (!"".equals(str) && str != null) {
				flag = true;
			}
		}
		return flag;
	}

	public JQGridPageRequest getJqgrid() {
		return jqgrid;
	}

	public void setJqgrid(JQGridPageRequest jqgrid) {
		this.jqgrid = jqgrid;

	}

	public JQGridPageResponse getJqgridPage() {
		return this.jqgridPage != null ? this.jqgridPage : new JQGridPageResponse(this.page);
	}

	public void setJqgridPage(JQGridPageResponse jqgridPage) {
		this.jqgridPage = jqgridPage;
	}

	//获取并初始化分页对象，一次请求一次调用
	public Page<T> getRequestPage() {
		List c = new ArrayList();
		page.setCriterions(c);
		if (jqgrid != null){
			this.page.setPageNo(jqgrid.getPageStart());
			this.page.setPageSize(jqgrid.getLimit());
			this.page.setOrderBy(jqgrid.getSortCol());
			this.page.setOrder(jqgrid.getSortOrder());
			this.page.setAutoCount(true);
			List criter = jqgrid.getCriterions(entityClass);
			if (criter == null){
				criter = new ArrayList();
			}
			this.page.setCriterions(criter);
		}
		return this.page;
	}
	
	public Page<T> fillPageWithJQGridRequest() {
		if (jqgrid != null){
			this.page.setPageNo(jqgrid.getPageStart());
			this.page.setPageSize(jqgrid.getLimit());
			this.page.setOrderBy(jqgrid.getSortCol());
			this.page.setOrder(jqgrid.getSortOrder());
			this.page.setAutoCount(true);
		}
		return page;
	}
	
	public List<SearchRule> parseJQGridRequest(List<SearchRule> addtional){
		List<SearchRule> result = JQGridSearchRuleParser.getSearchRules(this.jqgrid.getFilters());
		if (addtional != null)
			result.addAll(addtional);
		return result;
	}

	public T getEntity() {
		return entity;
	}
	
	public Bmb getCurOrg(){
		//获取当前登录用户的工作单位
		User user = this.getUser();
		if (user != null){
			return user.getWunit();
		}
		return null;
	}
	public String getOrgName(String bm){
		if(ORG_MAP == null){
			loadOrg();
		}
		return ORG_MAP.get(bm) ;
	}
	private void loadOrg(){
		ORG_MAP = orgService.loadOrg();
	}
	
	public String getUserId(){
		User user = this.getUser();
		return user == null ? "" : user.getId();
	}
	
	public String getCurOrgId(){
		Bmb org = this.getCurOrg();
		return org == null ? "" : org.getBm();
	}
	protected String rootOrgId="1";
}