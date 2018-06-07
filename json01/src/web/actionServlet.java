package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import bean.Stock;

public class actionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if(action.equals("/gupiao")){
			Stock stock=new Stock(123,23.5,"¿Æ¼¼");
		JSONObject jsonobj=JSONObject.fromObject(stock);
			String jsonstr=jsonobj.toString();
			System.out.println(jsonstr);
			request.setAttribute("gupiao", jsonstr);
			System.out.println(request.getAttribute("gupiao"));
			request.getRequestDispatcher("gupiao.jsp").forward(request, response);
		}
		out.close();
	}

}
