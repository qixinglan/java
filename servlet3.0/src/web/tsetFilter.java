package web;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 注解式Filter
 * @author Administrator
 *
 */
@WebFilter(
		servletNames={"testServlet",""},//指定fiter应用于哪些servlet
		//<filter-mapping>
		 //  <filter-name>performance</filter-name>
		  //  <servlet-name>SomeServlet</servlet-name>
		  // </filter-mapping>
		description="",
		displayName="",
		 dispatcherTypes={
          DispatcherType.FORWARD,
          DispatcherType.INCLUDE,
          DispatcherType.REQUEST,
          DispatcherType.ERROR, DispatcherType.ASYNC
      },
		filterName="",//<filter-name>
		initParams={@WebInitParam(name="username",value="Tom"),@WebInitParam(name="age",value="18")},
		urlPatterns={"/testServlet","/helloServlet"}//<url-pattern>  =value={"/testServlet","/helloServlet"}
		)
/*
 * 触发过滤器的时机，默认是浏览器直接发出请求
 * FORWARD就是指通过RequestDispatcher的forward()而来的请求可以套用过滤器。
 * INCLUDE就是指通过RequestDispatcher的include()而来的请求可以套用过滤器。
 * ERROR是指由容器处理例外而转发过来的请求可以触发过滤器。
 * ASYNC是指异步处理的请求可以触发过滤器(之后还会说明异步处理)。
 *  <filter-mapping>
         <filter-name>SomeFilter</filter-name>
         <servlet-name>*.do</servlet-name>
         <dispatcher>REQUEST</dispatcher>
         <dispatcher>FORWARD</dispatcher>
         <dispatcher>INCLUDE</dispatcher>
         <dispatcher>ERROR</dispatcher>
         <dispatcher>ASYNC</dispatcher>
     </filter-mapping>
 */
public class tsetFilter  implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
