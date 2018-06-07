package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AccountLimtException;
import model.AccountNotExitException;
import model.applyService;

public class actionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if(action.equals("/apply")){
			String username=request.getParameter("username");
			double money=Double.parseDouble(request.getParameter("money"));
			applyService a=new applyService();
			try {
				String number=a.apply(username, money);
				request.setAttribute("successindex", number);
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error0", "系统异常，请稍后重试");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} catch (AccountNotExitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error1", "指定的用户名不存在");
				request.getRequestDispatcher("apply.jsp").forward(request, response);
			} catch (AccountLimtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error2", "您的余额不足");
				request.getRequestDispatcher("apply.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.close();
	}

}
