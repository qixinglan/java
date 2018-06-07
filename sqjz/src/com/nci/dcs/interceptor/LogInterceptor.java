package com.nci.dcs.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.util.UrlPathHelper;

import com.nci.dcs.common.utils.Constants;
import com.nci.dcs.common.utils.LoginInfoUtils;
import com.nci.dcs.common.utils.SpringContextUtil;
import com.nci.dcs.system.model.Log;
import com.nci.dcs.system.model.User;
import com.nci.dcs.system.service.AuthorizationService;
import com.nci.dcs.system.service.LogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LogInterceptor implements Interceptor{	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LogInterceptor.class);
	public void destroy() {		
	}
	public void init() {		
	}

	public String intercept(ActionInvocation ai) throws Exception {
		logger.debug("进入操作日志拦截器");
		// 取得request和session，用于获取USER对象和URL地址
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		// 仅包含package和ActionName的路径
		String lookupPath = new UrlPathHelper().getLookupPathForRequest(request);
		
		//URL过滤
		String actionName = ai.getInvocationContext().getName();
//		Map p = ai.getInvocationContext().getParameters();
		HttpServletRequest req = (HttpServletRequest) ai.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
//		String url = req.getRequestURL().toString();
		String param = req.getQueryString();
		if(param!=null){
			String paramUp = param.toUpperCase();
			try{
				if(param.contains("<")||param.contains(">")||paramUp.contains("ALERT")||paramUp.contains("SCRIPT")){
					return "scriptError";
				}
			}catch(Exception e) {
				logger.error("系统检测到非法链接地址，抛出异常:"+e.getMessage());
			}
		}
		

//		// 获得所有参数信息
//		String queryInfo = request.getQueryString();
//
//		// 取得code和id参数
//		String code = null;
//		if (queryInfo != null) {
//			String[] params = queryInfo.split("&");
//			for (String param : params) {
//				if (param.indexOf("code") >= 0) {
//					code = param.substring(param.indexOf("=") + 1);
//				}
//			}
//		}
		// 通过lookupPath得到Package和ActionName
		//String packageName = lookupPath.substring(0, lookupPath.lastIndexOf("/"));
		//String actionName = lookupPath.substring(lookupPath.lastIndexOf("/") + 1);		
		/**
		 * 预留获取模块名称和操作类型
		 */
		AuthorizationService aService = (AuthorizationService)SpringContextUtil.getBean("authorizationService");
		String mkmc = aService.getNameByUrl(lookupPath);//模块名称
		//int czlx=Constants.LOG_OPER_SELECT;//操作类型
		int rzlx = Constants.LOG_OPER;//日志类型
		if(!mkmc.contains("getCountByPersonAlarm")){
			User user = LoginInfoUtils.getUser(session);
			if (user == null){
				logger.error("找不到登录用户信息，日志拦截器不记录操作日志:" + request.getRequestURI());
			}
			else{
				//记录日志
				LogService logService = (LogService)SpringContextUtil.getBean("logService");
				Log log = new Log();
				log.setYhm(user.getName());
				if(null!=user.getWunit()){
					log.setOrgId(user.getWunit().getBm());
				}
				log.setMkm(mkmc);
				//log.setCzlx(czlx);
				log.setRzlx(rzlx);
				log.setCzsj(new Date());
				logService.create(log);
			}
		}
		return ai.invoke();
	}
}
