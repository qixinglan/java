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
 * ע��ʽFilter
 * @author Administrator
 *
 */
@WebFilter(
		servletNames={"testServlet",""},//ָ��fiterӦ������Щservlet
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
 * ������������ʱ����Ĭ���������ֱ�ӷ�������
 * FORWARD����ָͨ��RequestDispatcher��forward()����������������ù�������
 * INCLUDE����ָͨ��RequestDispatcher��include()����������������ù�������
 * ERROR��ָ���������������ת��������������Դ�����������
 * ASYNC��ָ�첽�����������Դ���������(֮�󻹻�˵���첽����)��
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
