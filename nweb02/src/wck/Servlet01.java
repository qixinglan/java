package wck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Employee;

public class Servlet01 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url=request.getRequestURI();
		String action=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		if(action.equals("employee")){
			Employee e1=new Employee();
			e1.setAge(18);
			e1.setName("π»¡¡");
			e1.setSalary(8000);
			request.setAttribute("employee", e1);
			request.getRequestDispatcher("a2.jsp").forward(request, response);
		}
		
	}
	
}
