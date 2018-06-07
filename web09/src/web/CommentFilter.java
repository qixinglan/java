package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter implements Filter{
	FilterConfig config;
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println(1);
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String content=request.getParameter("content");
		PrintWriter out=response.getWriter();
		int size=Integer.parseInt(config.getInitParameter("a"));
		if(content.length()>size){
			out.println("<h>评论长度非法，字数过多</h>");
			System.out.println(size);
		}
		else{
			arg2.doFilter(arg0, arg1);
		}
		System.out.println(2);
	}
	/*
	 * 容器启动的时候就会创建过滤器实例
	 * 接下来
	 */

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		config=arg0;
		System.out.println(3);
	}

}
