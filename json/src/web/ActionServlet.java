package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import bean.Stock;

public class ActionServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri=request.getRequestURI();
		String active=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if(active.equals("/json2")){
			Stock stock1=new Stock();
			stock1.setCode("100");
			stock1.setName("Ñô»ª¿Æ¼¼");
			stock1.setPrice(25.3);
			JSONObject jsonobj=JSONObject.fromObject(stock1);
			String jsonstr=jsonobj.toString();
			request.setAttribute("stock", jsonstr);
			request.getRequestDispatcher("json2.jsp").forward(request, response);
		}
	}	
}
