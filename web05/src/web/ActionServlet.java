package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import entity.Emp;

public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String url=request.getRequestURI();
		String action=url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
		System.out.println(url);
		System.out.println(action);
		if(action.equals("/list")){
			EmpDao dao=new EmpDao();
			try{
				List<Emp> emplist=dao.findall();
				request.setAttribute("emplist", emplist);
				RequestDispatcher rd=request.getRequestDispatcher("emplist1.jsp");
				rd.forward(request, response);
			}catch(Exception e){
//				e.printStackTrace();
//				 request.getRequestDispatcher("error.jsp")
//			      .forward(request, response);
				throw new ServletException(e);
			}
		}
		else if(action.equals("/update")){
			Emp emp=new Emp(request.getParameter("name"), 
					Double.parseDouble(request.getParameter("salary")),
					Integer.parseInt(request.getParameter("age")));
			EmpDao dao=new EmpDao();
			try {
				dao.update(emp);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
		else if(action.equals("/add")){
			Emp emp=new Emp(request.getParameter("name"), 
					Double.parseDouble(request.getParameter("salary")),
					Integer.parseInt(request.getParameter("age")));
			EmpDao dao=new EmpDao();
			
			try {
				dao.addEmp(emp);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
		else if(action.equals("/del")){
			String name=request.getParameter("name");
			EmpDao dao=new EmpDao();
			System.out.println(name);
			try {
				dao.deleteEmp(name);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	}

}
